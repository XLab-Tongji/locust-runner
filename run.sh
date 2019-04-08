rm -f ./.env
touch ./.env

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

echo -e "PROM=${prom}\nPGW=${pgw}\nLR=${lr}\n" > .env

CURRENT_CONTAINER=$(docker ps -aq -f "name=ashwwf/locust-runner")
if [[ -n "$CURRENT_CONTAINER" ]]; then
    docker stop ${CURRENT_CONTAINER}
    docker rm ${CURRENT_CONTAINER}
fi

docker-compose up -d


