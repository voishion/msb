#!/bin/sh

usage() {
	echo "Usage: sh deploy.sh [run|logstash|elk|stop|remove]"
	exit 1
}

clearESFile(){
  rm -rf /Users/voishion/work/server/docker/elasticsearch/data/nodes
}
clearLogstashFile(){
  rm -f /Users/voishion/work/server/docker/logstash/tracking/*.txt
}

run(){
  clearESFile
  clearLogstashFile
  docker-compose up -d --build
}

logstash(){
  clearLogstashFile
  docker-compose up -d --build logstash
}

elk(){
  docker-compose up -d --build elk
}

stop(){
	docker-compose stop elk
	docker-compose stop logstash
	docker-compose stop kibana
  docker-compose stop elasticsearch
}

remove(){
	docker-compose rm elk
	docker-compose rm logstash
  docker-compose rm kibana
  docker-compose rm elasticsearch
}

case "$1" in
"run")
	run
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
"remove")
	remove
;;
*)
	usage
;;
esac