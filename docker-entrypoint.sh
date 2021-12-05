#!/bin/sh

set -e
#check server.pid
if [ -f tmp/pids/server.pid ]; then
  rm tmp/pids/server.pid
fi
#run application on port 1234
bundle exec rackup --port 1234 --host 0.0.0.0
