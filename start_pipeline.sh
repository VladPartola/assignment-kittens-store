#!/usr/bin/env bash

echo "------------Starting pipeline--------------"

docker-compose up -d
docker-compose exec app bundle exec rake db:setup db:migrate

echo "----------Testing the application----------"
docker-compose exec bundle exec rspec >> testlog.txt

echo "--------Running deploy pipeline------------"
curl -u vlad:11301def1356894337070fb1a20ea85c58 http://127.0.0.1:8080/job/ruby_app_pipeline/build?token=start-pipeline

ehco "--------Please check the Jenkins-----------"