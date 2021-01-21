##! /bin/bash
set -ex

echo "Start building"

STATUS_CODE=$?

ENV=$1
MARKET=$2

#browser=chrome env=local ./gradlew -Dmarket=cn clean build runInParallel
browser=chrome env=$ENV ./gradlew -Dmarket=$MARKET clean build runInParallel

if [ ${STATUS_CODE} -eq 0 ]
then
    echo "Exit with successful code."
else
    echo "Exit with failed code."
fi
exit ${STATUS_CODE}