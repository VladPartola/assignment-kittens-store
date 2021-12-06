#!/bin/bash

echo "------------Starting pipeline--------------"

docker-compose up -d
sleep 20
echo "----------Setting up database--------------"
docker-compose exec -T app bundle exec rake db:create
docker-compose exec -T app bundle exec rake db:migrate
docker-compose exec -T app bundle exec rake db:seed

echo "----------Testing the application----------"
docker-compose exec -T app bundle exec rspec >> testlog.txt

echo "--------Running deploy pipeline------------"
curl -u vlad:11301def1356894337070fb1a20ea85c58 http://127.0.0.1:8080/job/ruby_app_pipeline/build?token=start-pipeline

echo "--------Please check the Jenkins-----------"
