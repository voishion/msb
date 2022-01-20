#!/bin/sh
image=harbor.meixiu.mobi/library/vip-app-logstash:7.4.0
docker build -t ${image} ./
docker push ${image}