
## Interface Specifications （接口规约）

### /workload

#### Description (接口描述)

Get the parameters locust needed and run it, display the results on the console.

| | |
|-|-|
| Request Method | Post |
| Authorization | Not required |

#### Parameters (参数)

| Name | Description | Required | Schema |
|:-:|:-:|:-:|:-|
| scenarioId | scenario specified | Yes | number(int) |
| clients | clients simulated | Yes | number(int) |
| hatchRate | clients hatched / s | Yes | number(int) |
| runTime | total runtime (minute) | Yes | number(int) |
| host | website URL | Yes | string |

#### Scenario (场景)
1. 注册
2. 浏览
3. 加购物车
4. 支付

#### Responses (返回结果)
nothing

#### Request Sample (示例请求)

```

{
  "clients": 10,
  "hatchRate":10,
  "host": "www.baidu.com",
  "runTime": 10,
  "scenarioId": 1
}
```
---





_以下接口在 ashwwf/locust-runner中失效_

### /result

#### Description (接口描述)

Get locust result.
| | |
|-|-|
| Request Method | Get |
| Authorization | Not required |

#### Parameters (参数)

| Name | Description | Required | Schema |
|:-:|:-:|:-:|:-|
| from | from time | Yes | string(str) |
| to | to time | Yes | string(str) |

#### Responses (返回结果)
result.csv

#### Request Sample (示例请求)

```
from = '2018-11-20 19:34:12'
to = '2018-11-21 19:34:12'
```
---


### /result/stream

#### Description (接口描述)

Get locust result string stream.
| | |
|-|-|
| Request Method | Get |
| Authorization | Not required |

#### Parameters (参数)

| Name | Description | Required | Schema |
|:-:|:-:|:-:|:-|
| from | from time | Yes | string(str) |
| to | to time | Yes | string(str) |

#### Responses (返回结果)
String

#### Request Sample (示例请求)

```
from = '2018-11-20 19:34:12'
to = '2018-11-21 19:34:12'
```
---