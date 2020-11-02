# We use a Docker multi-stage build here so that we only take the compiled native Spring Boot app from the first build container
FROM oraclelinux:7-slim
MAINTAINER Moises Cisneros
ADD . /build
WORKDIR /build/target/native-image
# Fire up our Spring Boot Native app by default
CMD ["./demo"]