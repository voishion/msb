docker build -t eclipse-temurin:17.0.2_8-jre-alpine-ffmpeg-bento4 .

docker run -it --name java_ffmpeg_bento4 eclipse-temurin:17.0.2_8-jre-alpine-ffmpeg-bento4

docker cp /Users/voishion/work/download/video/test.mp4 56703df3992c:/

# 查询fast start状态
mp4info test.mp4 | grep 'fast start:       no'