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
        stage("Deploy and run app to AWS EC2 server") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
