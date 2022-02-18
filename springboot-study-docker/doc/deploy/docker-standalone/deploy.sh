#!/bin/sh

usage() {
	echo "Usage: sh deploy.sh [run|mysql|redis|nginx|nacos|sentinel|stopmysql|stopredis|stopnginx|stopnacos|stopsentinel|stop|remove]"
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

nacos(){
  docker-compose up -d --build nacos
}

sentinel(){
  docker-compose up -d --build sentinel
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

stopnacos(){
	docker-compose stop nacos
}

stopsentinel(){
	docker-compose stop sentinel
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
"nacos")
	nacos
;;
"sentinel")
	sentinel
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
"stopnacos")
	stopnacos
;;
"stopsentinel")
	stopsentinel
;;
"remove")
	remove
;;
*)
	usage
;;
esac