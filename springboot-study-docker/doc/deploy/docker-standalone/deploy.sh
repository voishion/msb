#!/bin/sh

usage() {
	echo "Usage: sh deploy.sh [run|mysql|redis|nginx|stopmysql|stopredis|stopnginx|stop|remove]"
	exit 1
}

run(){
  docker-compose up -d --build
}

mysql(){
  docker-compose up -d --build mysql
}

redis(){
  docker-compose up -d --build redis
}

nginx(){
  docker-compose up -d --build nginx
}

stop(){
	docker-compose stop
}

stopmysql(){
	docker-compose stop mysql
}

stopredis(){
	docker-compose stop redis
}

stopnginx(){
	docker-compose stop nginx
}

remove(){
	docker-compose rm
}

case "$1" in
"run")
	run
;;
"mysql")
	mysql
;;
"redis")
	redis
;;
"nginx")
	nginx
;;
"stop")
	stop
;;
"stopmysql")
	stopmysql
;;
"stopredis")
	stopredis
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