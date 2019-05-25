# **WEBHOOK**

### JSON格式

Alertmanager将以下列JSON格式将HTTP POST请求发送到配置的端点：

```
{
  "version": "4",
  "groupKey": <string>,    // key identifying the group of alerts (e.g. to deduplicate)
  "status": "<resolved|firing>",
  "receiver": <string>,
  "groupLabels": <object>,
  "commonLabels": <object>,
  "commonAnnotations": <object>,
  "externalURL": <string>,  // backlink to the Alertmanager.
  "alerts": [
    {
      "status": "<resolved|firing>",
      "labels": <object>,
      "annotations": <object>,
      "startsAt": "<rfc3339>",
      "endsAt": "<rfc3339>",
      "generatorURL": <string> // identifies the entity that caused the alert
    },
    ...
  ]
}
```

// json格式说明文档：<https://github.com/prometheus/docs/blob/master/content/docs/alerting/notifications.md>

| Name              | Type                                                         | Notes                                                        |
| ----------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Receiver          | string                                                       | Defines the receiver's name that the notification will be sent to (slack, email etc.). |
| Status            | string                                                       | Defined as firing if at least one alert is firing, otherwise resolved. |
| Alerts            | [Alert](https://github.com/prometheus/docs/blob/master/content/docs/alerting/notifications.md#alert) | List of alert objects ([see below](https://github.com/prometheus/docs/blob/master/content/docs/alerting/notifications.md#alert)). |
| GroupLabels       | [KV](https://github.com/prometheus/docs/blob/master/content/docs/alerting/notifications.md#kv) | The labels these alerts were grouped by.                     |
| CommonLabels      | [KV](https://github.com/prometheus/docs/blob/master/content/docs/alerting/notifications.md#kv) | The labels common to all of the alerts.                      |
| CommonAnnotations | [KV](https://github.com/prometheus/docs/blob/master/content/docs/alerting/notifications.md#kv) | Set of common annotations to all of the alerts. Used for longer additional strings of information about the alert. |
| ExternalURL       | string                                                       | Backlink to the Alertmanager that sent the notification.     |

##### Alert

| Name         | Type       | Notes                                                        |
| ------------ | ---------- | ------------------------------------------------------------ |
| Status       | string     | Defines whether or not the alert is resolved or currently firing. |
| Labels       | KV（见下） | A set of labels to be attached to the alert.                 |
| Annotations  | KV         | A set of annotations for the alert.                          |
| StartsAt     | time.Time  | The time the alert started firing. If omitted, the current time is assigned by the Alertmanager. |
| EndsAt       | time.Time  | Only set if the end time of an alert is known. Otherwise set to a configurable timeout period from the time since the last alert was received. |
| GeneratorURL | string     | A backlink which identifies the causing entity of this alert. |

##### KV

`KV` is a set of key/value string pairs used to represent labels and annotations.

```
type KV map[string]string
```

Annotation example containing two annotations:

```
{
  summary: "alert summary",
  description: "alert description",
}
```





##### example

```

{
    "receiver": "alerting-receiver",
    "status": "resolved",
    "alerts": [
        {
            "status": "resolved",
            "labels": {
                "alertname": "NodeExporterUnavailable",
                // 可以用作抑制规则条件
                "instance": "0.0.0.0:port", 
                "job": "node",
                "severity": "High",
                "tags": "这里隐藏了部分数据"
            },
            "annotations": {
                "description": "NodeExporter在ip:port检测失败.",
                "summary": "ip:62003:NodeExporterUnavailable"
            },
            "startsAt": "2018-05-21T17:44:52.365135464+08:00",
            "endsAt": "2018-05-21T17:48:37.379420736+08:00",
            "generatorURL": "http://0.0.0.0:9090/graph?xxxxxxxxxxxxxxxx"
        },
        {
            "status": "resolved",
            "labels": {
                "alertname": "NodeExporterUnavailable",
                "instance": "ip:port",
                "job": "node",
                "severity": "High",
                "tags": "这里隐藏了部分数据"
            },
            "annotations": {
                "description": "NodeExporter在ip:port检测失败.",
                "summary": "ip:port:NodeExporterUnavailable"
            },
            "startsAt": "2018-05-21T17:44:52.365135464+08:00",
            "endsAt": "2018-05-21T17:48:22.379431709+08:00",
            "generatorURL": "http://0.0.0.0:9090/graph?xxxxxxxxxxxx"
        }
    ],
    "groupLabels": {
        "alertname": "NodeExporterUnavailable"
    },
    "commonLabels": {
        "alertname": "NodeExporterUnavailable",
        "job": "node",
        "severity": "High"
    },
    "commonAnnotations": {},
    "externalURL": "http://0.0.0.0:9093",
    "version": "4",
    "groupKey": "{}/{job=~\"^(?:node)$\"}:{alertname=\"NodeExporterUnavailable\"}"
}

```



### webhook接口示例

以 flask 框架为基础

```
from flask import Flask, request
import json
app = Flask(__name__)

@app.route('/send', methods=['POST'])
def send():
    try:
        data = json.loads(request.data)
        alerts =  data['alerts']
        for i in alerts:
            print('SEND SMS: ' + str(i))
    except Exception as e:
        print(e)
    return 'ok'

```

建议用jsonobject来进行解析



### 修改alertmanager.yaml配置

需要修改Alertmanager配置文件, 为default-receiver添加webhook配置

```
global:
  resolve_timeout: 5m
route:
  group_by: ['alertname']
  group_wait: 30s
  group_interval: 5m
  repeat_interval: 12h
  receiver: 'webhook'
receivers:
- name: 'webhook'
  webhook_configs:
  - url: 'yoursevice' 

```

这里的 url 要跟 service 提供的服务地址对应上

**路由**(route)：有的告警信息都会从配置中的顶级路由(route)进入路由树，根**据路由规则将告警信息发送给相应的接收器**。

**接收器**(receivers)：在Alertmanager中可以定义一组接收器，比如可以按照角色(比如系统运维，数据库管理员)来划分多个接收器。接收器可以关联邮件，Slack以及其它方式接收告警信息