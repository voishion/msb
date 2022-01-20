#!/bin/sh

usage() {
	echo "Usage: sh deploy.sh [all|logstash|elk|stop|rm]"
	exit 1
}

all(){
	rm -rf /Users/voishion/work/server/docker/elasticsearch/data/*
  rm -rf /Users/voishion/work/server/docker/logstash/tracking/*
  docker-compose up -d --build
}

logstash(){
  rm -rf /Users/voishion/work/server/docker/logstash/tracking/*
  docker-compose up -d --build logstash
}

elk(){
  docker-compose up -d --build elk
}

# 关闭所有环境/模块
stop(){
	docker-compose stop
}

# 删除所有环境/模块
rm(){
	docker-compose rm
}

# 根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
"all")
	all
;;
"logstash")
	logstash
;;
"elk")
	elk
;;
"stop")
	stop
;;
"rm")
	rm
;;
*)
	usage
;;
esac