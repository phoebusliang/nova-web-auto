##! /bin/bash
set -ex

echo " *** Start testing in docker *** "

STATUS_CODE=$?

docker run -e TZ=Asia/Hong_Kong -w /test -v `dirname "$PWD"`:/root/.gradle -v `pwd`:/test nova:nova-web-test bash ./run-test.sh $1 $2
#docker run -it -w /test -v `dirname "$PWD"`:/root/.gradle -v `pwd`:/test nova:nova-web-test bash ./run-test.sh

if [ ${STATUS_CODE} -eq 0 ]
then
    echo "Exit with successful code."
else
    echo "Exit with failed code."
fi
exit ${STATUS_CODE}