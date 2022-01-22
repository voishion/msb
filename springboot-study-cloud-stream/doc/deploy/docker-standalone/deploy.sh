#!/bin/sh

usage() {
	echo "Usage: sh deploy.sh [run|stop|remove]"
	exit 1
}

run(){
  docker-compose up -d --build
}

stop(){
	docker-compose stop
}

remove(){
	docker-compose rm
}

case "$1" in
"run")
	run
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