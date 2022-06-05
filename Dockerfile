FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar​
COPY ${JAR_FILE} report.jar​
ENTRYPOINT ["java","-jar","/report.jar"]

