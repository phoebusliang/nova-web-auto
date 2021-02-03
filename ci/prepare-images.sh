#!/usr/bin/env bash
set -x
DOCKERFILE="$(dirname $0)/../Dockerfile"
docker build -t=nova:nova-web-test -f $DOCKERFILE .