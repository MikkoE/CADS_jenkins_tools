#!/bin/sh

RESULT_DIRECTORY="../inet-private/tests/unit/work/QuicHandshake/"
DEST_DIRECTORY="../artifacts/"

current_time=$(date "+%Y.%m.%d-%H.%M.%S")
echo "Current Time: $current_time"

fileName="result_$current_time.tar.gz"
echo "Filename: $fileName"

tar -zcvf $fileName $RESULT_DIRECTORY

mv $fileName $DEST_DIRECTORY
