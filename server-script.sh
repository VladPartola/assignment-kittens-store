#!/usr/bin/env bash

docker-compose up -d
docker-compose exec -T app bundle exec rake db:create
docker-compose exec -T app bundle exec rake db:migrate
docker-compose exec -T app bundle exec rake db:seed

echo "success"
