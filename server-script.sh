#!/usr/bin/env bash

docker-compose up --detach
docker-compose exec app bundle exec rake db:setup db:migrate

echo "success"