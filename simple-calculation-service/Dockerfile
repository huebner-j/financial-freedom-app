FROM adoptopenjdk/openjdk11:jdk-11.0.9.1_1-centos-slim

WORKDIR /app

COPY --chown=1001:1001 build/install/simple-calculation-service .
COPY --chown=1001:1001 config.yaml .

USER 1001

ENTRYPOINT ["bin/simple-calculation-service"]
CMD ["server", "config.yaml"]