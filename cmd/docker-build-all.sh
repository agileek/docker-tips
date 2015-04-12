#!/bin/bash

docker build -t docker-stop-test-base -f docker/Dockerfile-base docker
docker build -t docker-stop-test-bad -f docker/Dockerfile-cmd-bad docker
docker build -t docker-stop-test-good -f docker/Dockerfile-cmd-good docker