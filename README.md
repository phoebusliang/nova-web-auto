# nova-web-auto

E2E testing for the NovaStar Cloud web application
=====================

## Prerequisites(if you want to run tests on local)

* Chromedriver latest version - you can download it from [here](https://sites.google.com/a/chromium.org/chromedriver/) or use a package manager of your choice to install it
* Chrome browser
* JDK 1.8
* gradle 5.6
* goto to the project dir and run `docker build -f Dockerfile -t nova:nova-web-test .` to build the image
## Execution
* Given `browser=chrome` means we plan to run tests with Chrome.
* `env` means we can choose Dev, SIT, UAT or Prod environment for different aim.
* `market` is convenient for running tests on different languages.

Linux/Mac: `browser=chrome env=local gradle -Dmarket=us clean build runInParallel`

or

`browser=chrome env=local ./gradlew -Dmarket=cn clean build runInParallel`

#### Run in docker
#### In order to make the test environment clean and reliable, we need to execute the test in docker, 
#### and in that case, we should pass the arguments from command line to identify specific languages and environments,
#### so all of them should be given from command line/jenkins
* Go to project dir and run `./buildtasks/run-test-docker.sh $1 $2`
  #### Example: ./buildtasks/run-test-docker.sh local cn (./buildtasks/run-test-docker.sh ENV MARKET)
* Run `./buildtasks/clean.sh` if you want to clean all container or images

## MockServer

Make E2E tests more stable and more scenario covered.

### Start MockServer
Need to start the server with `prod` argument passed from command line or CI.
`./gradlew -Dprod=icare startMockServer`

### Stop MockServer
`./gradlew stopMockServer`