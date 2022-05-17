#!/bin/sh
cat ./Dockerfile
docker build -t eclipse-temurin:17.0.2_8-jre-alpine-ffmpeg-bento4 . &&
docker tag eclipse-temurin:17.0.2_8-jre-alpine-ffmpeg-bento4 harbor.meixiu.mobi/library/eclipse-temurin:17.0.2_8-jre-alpine-ffmpeg-bento4 &&
docker push harbor.meixiu.mobi/library/eclipse-temurin:17.0.2_8-jre-alpine-ffmpeg-bento4