#!/bin/bash

ps aux |grep locust-runner |grep -v grep |awk '{print $2}' |xargs kill

nohup java -jar locust-runner-1.0.0.jar > locust-runner.log 2> locust-runner.error &
