##! /bin/bash
set -ex

echo " *** Start testing in docker *** "

STATUS_CODE=$?

docker run -w /test -v `dirname "$PWD"`:/root/.gradle -v `pwd`:/test nova:nova-web-test bash ./run-test.sh
#docker run -it -w /test -v `dirname "$PWD"`:/root/.gradle -v `pwd`:/test nova:nova-web-test bash ./run-test.sh
#docker run -it -w /test -v `dirname "$PWD"`:/root/.gradle -v `pwd`:/test phoebusliang:e2e-java bash ./run-test.sh
#docker run -it -w /test -v `dirname "$PWD"`:/root/.gradle -v `pwd`:/test lz:lz-test bash ./run-test.sh

if [ ${STATUS_CODE} -eq 0 ]
then
    echo "Exit with successful code."
else
    echo "Exit with failed code."
fi
exit ${STATUS_CODE}