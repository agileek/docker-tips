#!/bin/bash

rm -f $PWD/dataFolder-bad-cmd
rm -f $PWD/dataFolder-good-cmd

docker rm docker-stop-test-bad-container
docker rm docker-stop-test-good-container

docker run --name docker-stop-test-bad-container -v $PWD/dataFolder-bad-cmd:/dataFolder -d docker-stop-test-bad
docker run --name docker-stop-test-good-container -v $PWD/dataFolder-good-cmd:/dataFolder -d docker-stop-test-good

sleep 5

docker stop docker-stop-test-bad-container
docker stop docker-stop-test-good-container

echo VERIFY in $PWD/dataFolder-bad-cmd and $PWD/dataFolder-good-cmd if data has been written