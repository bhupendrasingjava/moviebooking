#Stage 1
FROM maven:3.6.0-jdk-8
COPY eureka-server /app
WORKDIR /app
RUN mvn clean install

#Stage 2
FROM registry.comapanyname.com/base-images/alpine-java/alpine3.11-jre8:0.1
RUN mkdir app
EXPOSE 8094
COPY --from=0 /app/target/eureka-server-*.jar /app/
WORKDIR /app
RUN adduser bhupendra -D && chown -R bhupendra /app && chmod -R a+x /app
USER bhupendra
ENTRYPOINT java -jar eureka-server-*.jar

