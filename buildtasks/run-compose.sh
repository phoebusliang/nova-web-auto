##! /bin/bash
set -ex

echo " *** Start testing with docker compose*** "

CURRENT_FILE_DIR=$(cd $(dirname "${BASH_SOURCE[0]}") && pwd)
PROJECT_DIR=${CURRENT_FILE_DIR}/..
DOCKER_COMPOSE_YML=${PROJECT_DIR}/docker-compose.yml

docker-compose -f ${DOCKER_COMPOSE_YML} down
docker-compose -f ${DOCKER_COMPOSE_YML} build --no-cache
docker-compose -f ${DOCKER_COMPOSE_YML} run --rm web-test
docker-compose -f ${DOCKER_COMPOSE_YML} down