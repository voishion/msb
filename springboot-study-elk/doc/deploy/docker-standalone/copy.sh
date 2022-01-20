#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh [elk]"
	exit 1
}

elk(){
	echo "begin copy elk"
	rm -f ./elk/jar/*.jar
  cp -f ../../../target/springboot-study-*.jar ./elk/jar
  echo "finish copy elk"
}

case "$1" in
"elk")
	elk
;;
*)
	usage
;;
esac