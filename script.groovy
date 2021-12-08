#!/usr/bin/env groovy

def buildDocker() {
    echo 'Building the application and pushing to docker hub...'
    withCredentials([usernamePassword(credentialsId: 'docker-key', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh "docker build -t vladpartola/ruby-app:v1.0 ."
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh "docker push vladpartola/ruby-app:v1.0"
    }
}

def deployApp() {
    echo 'Deploying the application to AWS server...'
    def server = "ec2-user@18.195.44.16"
    def shellCmd = "/bin/bash ./server-script.sh"
    sshagent(['ec2-server-key']) {
        sh "ssh -o StrictHostKeyChecking=no ${server}"
        sh "scp docker-composeAWS.yml ${server}:/home/ec2-user/docker-compose.yml"
        sh "scp init.sql ${server}:/home/ec2-user/"
        sh "scp server-script.sh ${server}:/home/ec2-user/"
        sh "ssh ${server} ${shellCmd}"
    }

}

return this
