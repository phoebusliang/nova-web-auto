# nova-web-auto

E2E testing for the NovaStar Cloud web application
=====================

## Prerequisites

* Chromedriver latest version - you can download it from [here](https://sites.google.com/a/chromium.org/chromedriver/) or use a package manager of your choice to install it
* Chrome browser
* JDK 1.8

## To run, you will need to install gradle, then run with different regions with different configuration file:
## NOTE: Now we support to run e2e tests for local and other envs (local: http://localhost:3000)

Linux: `browser=chrome env=local gradle -Dmarket=us clean build runInParallel`

or

`browser=chrome env=local ./gradlew -Dmarket=cn clean build runInParallel`

Windows: `export browser=chrome env=local && gradle -Dmarket=us clean build runInParallel`

## MockServer

TBD

# Notes

* the `browser` variable must be set before running the tests
* please run `gradle generateCucumberReport` to generate report if something failed when generating report automatically


# Run in docker
* Go to project dir and run `./buildtasks/run-test-docker.sh`
* Run `./buildtasks/clean.sh` if you want to clean all container or images

# Run with docker-compose if test depends on the other service

TBD
