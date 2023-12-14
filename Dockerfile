FROM alpine:3.19.0

LABEL maintainer="Bruno Nascimento <brunoenig@hotmail.com>"
LABEL description="Docker image for Order Manager Application."

EXPOSE 8080/tcp

RUN  apk update \
  && apk upgrade \
  && apk add openjdk8 \
  && mkdir /orderSystem

ARG SPRING_DATASOURCE_URL
ARG SPRING_DATASOURCE_USERNAME
ARG SPRING_DATASOURCE_PASSWORD

ARG SPRING_MAIL_USERNAME
ARG SPRING_MAIL_PASSWORD

COPY target/*.jar /orderSystem/order_manager.jar

CMD ["sh"]
ENTRYPOINT ["java", "-jar", "orderSystem/order_manager.jar"]