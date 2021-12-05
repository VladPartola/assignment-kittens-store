#!/usr/bin/env bash

docker-compose up -d
docker-compose exec -T app bundle exec rake db:setup db:migrate

echo "success"
