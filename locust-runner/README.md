# MySQL
```mysql
CREATE TABLE `locust_result` (
  `scenario_id` int(11) DEFAULT NULL,
  `requests` int(11) DEFAULT NULL,
  `failures` int(11) DEFAULT NULL,
  `median_response_time` int(11) DEFAULT NULL,
  `average_response_time` int(11) DEFAULT NULL,
  `min_response_time` int(11) DEFAULT NULL,
  `max_response_time` int(11) DEFAULT NULL,
  `average_content_size` int(11) DEFAULT NULL,
  `request_per_second` int(11) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

# Deploy Sock Shop
```bash
# start
docker-compose -f deploy/docker-compose/docker-compose.yml up -d
# or
docker-compose -f deploy/docker-compose/docker-compose.yml -f deploy/docker-compose/docker-compose.logging.yml up -d

# clean
docker-compose -f deploy/docker-compose/docker-compose.yml down
```


# Deploy Application
```bash
## 部署locust文件到服务器
scp -r locust-files root@192.168.199.20:/home  
## 部署application到服务器
scp target/locust-runner-1.0.0.jar root@192.168.199.20:/home/locust-socks-shop

nohup java -jar locust-runner-1.0.0.jar &
```



# Docker Deploy Application
```bash
docker build -t locust-runner ./
docker run --restart=always -p 8088:8088 -d locust-runner
```

# docker update from wwf
```bash
docker pull ashwwf/locust-runner
# or you can use 
docker pull ashwwf/locustrunner-autobuild

docker run --restart=always -p 8088:8088 -d ashwwf/locust-runner
# -e or --env=[] to set environment variables
docker run -e PGW=host --restart=always -p 8088:8088 -d ashwwf/locust-runner
```

