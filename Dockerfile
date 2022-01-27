FROM hub.d.cisdigital.cn/library/jdk:centos7-jre8-skywalking

MAINTAINER hnbcao <hnbcao@qq.com>

# 设置虚拟机内存
ENV JAVA_MEM_OPTS="-Xms1024m -Xmx2048m"

# 禁用skywalking
ENV DISABLE_SKYWALKING=true

# skywalking地址（DISABLE_SKYWALKING=false才生效）
ENV AGENT_BACKEND=basic-skywalking-backend.paas:11800

# 创建默认application.yml文件
ENV USE_DEFAULT_APOLLO_CONFIG=true

ENV APP_NAME=bitmagic

# 镜像启动运行“${JAVA} ${AGENT_OPTS} ${JAVA_MEM_OPTS} ${JAVA_CUSTOM_OPTS} -jar app.jar”，
# 不需要新加CMD或者ENTRYPOINT

COPY bitmagic-web/target/bitmagic-web-*.jar /app.jar