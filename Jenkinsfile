#!/usr/bin/env groovy

def gv

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("Build docker image for app and push to DockerHub") {
            steps {
                script {
                    gv.buildDocker()
                }
            }
        }
        stage("Run docker-compose and setting database for testing app") {
            steps {
                script {
                    gv.runDockercompose()
                }
            }
        }
        stage("Testing app") {
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("Deploy app to AWS EC2 server") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
        stage("Run application on AWS EC2 server") {
            steps {
                script {
                    gv.runApp()
                }
            }
        }
    }
}
