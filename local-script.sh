#!/usr/bin/env bash

cd /home/vlad/Main_folder/testapp/assignment-kittens-store/ | docker-compose up --detach
docker-compose exec app bundle exec rake db:setup db:migrate
docker-compose exec bundle exec rspec

echo "success"