FROM java:8
ADD target/batch-service.jar .
EXPOSE 8675
CMD java -jar -Xmx512M batch-service.jar