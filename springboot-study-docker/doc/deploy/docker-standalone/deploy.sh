#!/bin/sh

usage() {
	echo "Usage: sh deploy.sh [run|mysql|db2|redis|nginx|nacos|sentinel|seata|rabbitmq|xxljobadmin|stopmysql|stopdb2|stopredis|stopnginx|stopnacos|stopsentinel|stopseata|stoprabbitmq|stopxxljobadmin|stop|remove]"
	exit 1
}

run(){
  docker-compose up -d --build
}

mysql(){
  docker-compose up -d --build mysql
}

db2(){
  docker-compose up -d --build db2
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

seata(){
  docker-compose up -d --build seata
}

rabbitmq(){
  docker-compose up -d --build rabbitmq
}

xxljobadmin(){
  docker-compose up -d --build xxl-job-admin
}

stop(){
	docker-compose stop
}

stopmysql(){
	docker-compose stop mysql
}

stopdb2(){
	docker-compose stop db2
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

stopseata(){
	docker-compose stop seata
}

stoprabbitmq(){
	docker-compose stop rabbitmq
}

stopxxljobadmin(){
	docker-compose stop xxl-job-admin
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
"db2")
	db2
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
"seata")
	seata
;;
"rabbitmq")
	rabbitmq
;;
"xxljobadmin")
	xxljobadmin
;;
"stop")
	stop
;;
"stopmysql")
	stopmysql
;;
"stopdb2")
	stopdb2
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
"stopseata")
	stopseata
;;
"stoprabbitmq")
	stoprabbitmq
;;
"stopxxljobadmin")
	stopxxljobadmin
;;
"remove")
	remove
;;
*)
	usage
;;
esac