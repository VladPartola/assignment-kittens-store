#!/usr/bin/env bash

docker-compose up -d
docker-compose exec app bundle exec rake db:setup db:migrate

echo "success"