#!/usr/bin/env groovy

def buildDocker() {
    echo 'Building the application and pushing to docker hub...'
    withCredentials([usernamePassword(credentialsId: 'docker-key', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh "docker build -t vladpartola/ruby-app:v1.0 ."
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh "docker push vladpartola/ruby-app:v1.0"
    }
}

def runDockercompose() {
    echo 'Runing the docker-compose file...'
    sh "pwd"
    sh "docker-compose up -d"
    echo 'Setting up databes...'
    sh "docker-compose exec -t app bundle exec rake db:setup db:migrate"
}

def testApp() {
    echo 'Testing the application...'
    sh "docker-compose exec -t app bundle exec rspec >> testlog.txt"
}

def deployApp() {
    echo 'Deploying the application to AWS server...'
    def server = "ec2-user@18.195.44.16"
    def shellCmd = "/bin/bash ./server-script.sh"
    sshagent(['ec2-server-key']) {
        sh "scp docker-composeAWS.yaml ${server}:/home/ec2-user"
        sh "scp server-script.sh ${server}:/home/ec2-user"
        sh "ssh -o StrictHostKeyChecking=no ${server} ${shellCmd}"
    }

}

def runApp() {
    echo 'Runing the application...'
}

return this
