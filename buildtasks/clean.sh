##! /bin/bash
set -ex

echo " *** Start to clean...*** "

STATUS_CODE=$?

docker rm -f $(docker ps -aq)

if [ ${STATUS_CODE} -eq 0 ]
then
    echo "Exit with successful code."
else
    echo "Exit with failed code."
fi
exit ${STATUS_CODE}