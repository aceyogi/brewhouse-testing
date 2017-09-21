FROM dockerfile/java:oracle-java8
MAINTAINER Graeme Stevenson <aceyogi@gmail.com>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/ref-data-0.0.1-SNAPSHOT.jar"]

# Add the service itself
ADD target/ref-data-0.0.1-SNAPSHOT.jar /usr/share/myservice/ref-data-0.0.1-SNAPSHOT.jar

