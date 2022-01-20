#!/bin/sh

rm -rf /Users/voishion/work/server/docker/elasticsearch/data/*
rm -rf /Users/voishion/work/server/docker/logstash/tracking/*

docker-compose up -d --build