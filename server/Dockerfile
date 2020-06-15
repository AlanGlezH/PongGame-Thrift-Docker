
FROM ubuntu:20.04
COPY gen-java /pongame/gen-java
WORKDIR /pongame/server
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk
EXPOSE 8080
CMD java -cp ../thrift/libthrift-0.13.0.jar:../thrift/slf4j-api-1.7.30.jar:../thrift/slf4j-nop-1.7.30.jar:../gen-java:. ServerPong