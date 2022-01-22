#!/bin/sh

usage() {
	echo "Usage: sh deploy.sh [run|mysql|nginx|stopmysql|stopnginx|stop|remove]"
	exit 1
}

run(){
  docker-compose up -d --build
}

mysql(){
  docker-compose up -d --build mysql
}

nginx(){
  docker-compose up -d --build nginx
}

stop(){
	docker-compose stop mysql
	docker-compose stop nginx
}

stopmysql(){
	docker-compose stop mysql
}

stopnginx(){
	docker-compose stop nginx
}

remove(){
	docker-compose rm mysql
	docker-compose rm nginx
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
"stopmysql")
	stopmysql
;;
"stopnginx")
	stopnginx
;;
"remove")
	remove
;;
*)
	usage
;;
esac