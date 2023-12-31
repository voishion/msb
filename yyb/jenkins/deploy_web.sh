#!/bin/bash
DATE=$(date +%Y-%m-%d-%H-%M-%S)
#目标服务器地址列表
WEB_SERVER="10.0.0.7 10.0.0.8"
#jenkins打包目录
SOURCE_DIR=/opt
#服务器部署目录
TARGET_DIR=/code
#文件名
NAME=${DATE}-${git_version}

#1.进入项目目录，将内容进行打包
get_code(){
  cd ${WORKSPACE} && \
  tar czf ${SOURCE_DIR}/web-${NAME}.tar.gz ./*
}

#2.将内容通过scp拷贝至web集群组
scp_web_server(){
  for host in $WEB_SERVER ;
  do
    scp ${SOURCE_DIR}/web-${NAME}.tar.gz root@${host}:${SOURCE_DIR}
    ssh root@${host} "mkdir -p ${TARGET_DIR}/web-${NAME} && \
                      tar xf ${SOURCE_DIR}/web-${NAME}.tar.gz -C ${TARGET_DIR}/web-${NAME} && \
                      rm -rf ${TARGET_DIR}/web && \
                      ln -s ${TARGET_DIR}/web-${NAME} ${TARGET_DIR}/web"
  done
}

#3.部署
deploy(){
  get_code
  scp_web_server
}

#4.回滚
rollback(){
  for host in $WEB_SERVER ;
  do
    rollback_file=$(ssh root@${host} "find ${TARGET_DIR}/ -maxdepth 1 -type d -name "web-*-${git_version}"")
    ssh root@${host} "rm -rf ${TARGET_DIR}/web && \
                      ln -s ${rollback_file} ${TARGET_DIR}/web"
  done
}

#部署时判断之前是否部署过该版本的commit，没有则部署，有则提示然后退出
#回退则不受限制
if [ $deploy_env == "deploy" ]; then
  if [ "${GIT_COMMIT}" == "${GIT_PREVIOUS_SUCCESSFUL_COMMIT}" ]; then
    echo "你已经部署过 ${git_version} 版本"
    exit 1
  else
    deploy
  fi
elif [ $deploy_env == "rollback" ]; then
  rollback
fi