#rm -f ./.env
#touch ./.env

prom=9090
pgw=9091
lr=8088

while getopts ':p:g:l:' OPT; do
    case $OPT in
        p)
            prom=$OPTARG;;
        g)
            pgw=$OPTARG;;
        l)
            lr=$OPTARG;;
    esac
done

#echo -e "PROM=${prom}\nPGW=${pgw}\nLR=${lr}\n" > .env
#source .env

export PROM=${prom} 
export PGW=${pgw}
export LR=${lr}

CURRENT_CONTAINER=$(docker ps -aq -f "name=locust-runner")
if [ -n "$CURRENT_CONTAINER" ]; then
    docker stop ${CURRENT_CONTAINER}
    docker rm ${CURRENT_CONTAINER}
fi

CURRENT_CONTAINER1=$(docker ps -aq -f "name=prometheus")
if [ -n "$CURRENT_CONTAINER1" ]; then
    docker stop ${CURRENT_CONTAINER1}
    docker rm ${CURRENT_CONTAINER1}
fi

CURRENT_CONTAINER2=$(docker ps -aq -f "name=pushgateway")
if [ -n "$CURRENT_CONTAINER2" ]; then
    docker stop ${CURRENT_CONTAINER2}
    docker rm ${CURRENT_CONTAINER2}
fi

CURRENT_CONTAINER2=$(docker ps -aq -f "name=alertmanager")
if [ -n "$CURRENT_CONTAINER2" ]; then
    docker stop ${CURRENT_CONTAINER2}
    docker rm ${CURRENT_CONTAINER2}
fi

docker-compose -f docker-compose-alert.yml up -d --build


