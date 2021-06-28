#!/bin/bash
DATE=$(date +%Y-%m-%d-%H-%M-%S)
#目标服务器地址列表
WEB_SERVER="10.0.0.7 10.0.0.8"
SOURCE_DIR=/opt
TARGET_DIR=/code

#1.进入项目目录，将内容进行打包
get_code(){
  cd ${WORKSPACE} && \
  tar czf ${SOURCE_DIR}/web-${DATE}.tar.gz ./*
}

#2.将内容通过scp拷贝至web集群组
scp_web_server(){
  for host in $WEB_SERVER ;
  do
    scp ${SOURCE_DIR}/web-${DATE}.tar.gz root@${host}:${SOURCE_DIR}
    ssh root@${host} "mkdir -p ${TARGET_DIR}/web-${DATE} && \
                      tar xf ${SOURCE_DIR}/web-${DATE}.tar.gz -C ${TARGET_DIR}/web-${DATE} && \
                      rm -rf ${TARGET_DIR}/web && \
                      ln -s ${TARGET_DIR}/web-${DATE} ${TARGET_DIR}/web"
  done
}

#3.部署
deploy(){
  get_code
  scp_web_server
}

deploy