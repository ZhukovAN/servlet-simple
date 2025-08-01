# Proof-of-concept Java Servlet application
This application uses Java Servlet Spec 3.1 so you need to deploy it on Tomcat 8.5 (https://tomcat.apache.org/whichversion.html)
# Run code scanning tools
## SonarQube CLI
### Maven task
``` shell
mvn clean verify sonar:sonar -Dsonar.projectKey=java-servlet-simple -Dsonar.projectName='java-servlet-simple' -Dsonar.host.url=https://sonarqube.ptdemo.local/ -Dsonar.token=sqa_b01c44c121f04417e6d5dc48045f6cfb22e3c8d6
```
### Direct CLI call
``` shell
docker run --rm \
  --env SONAR_HOST_URL="https://sonarqube.ptdemo.local" \
  --env SONAR_TOKEN="sqa_b01c44c121f04417e6d5dc48045f6cfb22e3c8d6" \
  --volume "${PWD}/src:/usr/src" \
  dockerhub.net173.org/ptdemo/sonar-scanner-cli:5.0 \
  -Dsonar.projectKey=java-servlet-simple
```
## CodeQL
``` shell
codeql database create --language java .codeql/db
codeql database analyze --format sarif-latest --output .codeql/sarif.json .codeql/db
```
## SemGrep
```shell
docker run -it --rm \
  --volume "$(pwd):/src" \
  semgrep/semgrep \
  semgrep ci --gitlab-sast \
  --output report.json \
  --config="p/java"
```