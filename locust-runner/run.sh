#!/usr/bin/env bash

# automatic build docker image

PORT=8088
HOST=127.0.0.1
#set container port if have "-p" option
while getopts 'p:h:' OPT; do
    case $OPT in
        p)
            PORT=$OPTARG;;
        h)
	    HOST=$OPTARG;;
    esac
done

echo "$PORT"
echo "$HOST"

mvn clean
mvn package

CURRENT_CONTAINER=$(docker ps -aq -f "name=locust-runner")
if [[ -n "$CURRENT_CONTAINER" ]]; then
    docker stop ${CURRENT_CONTAINER}
    docker rm ${CURRENT_CONTAINER}
fi

docker build -t locust-runner ./
docker run --restart=always -d -p $PORT:8088 -e PGW=$HOST --name locust-runner locust-runner

