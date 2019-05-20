# locust-runner

这是一个docker-compose项目。

### 1.启动compose项目

可以选择同时启动alertmanager或者仅启动项目：

#### run.sh

```
-p port of prometheus(default:9090)
-g port of pushgateway(default:9091)
-l port of locust-runner(default:8080)
```

#### run-alert.sh
会同时启动alertmanager

### 2.创建influxdb数据库

```bash
curl -XPOST http://localhost:8086/query --data-urlencode "q=CREATE DATABASE prometheus"
```

# Webhooks
