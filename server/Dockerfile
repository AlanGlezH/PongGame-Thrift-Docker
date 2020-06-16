
FROM ubuntu:20.04
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk
COPY gen-java /pongame/gen-java
COPY server pongame/server
COPY thrift pongame/thrift
WORKDIR /pongame/server
EXPOSE 8080
CMD java -cp ../thrift/libthrift-0.13.0.jar:../thrift/slf4j-api-1.7.30.jar:../thrift/slf4j-nop-1.7.30.jar:../gen-java:. ServerPong