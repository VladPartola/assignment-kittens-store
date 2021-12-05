def buildDocker() {
    echo 'Building the application...'
    echo 'Pyshing the application to DockerHub...'
}

def runDockercompose() {
    echo 'Runing the docker-compose file...'
    echo 'Setting up databes...'
}

def testApp() {
    echo 'Testing the application...'
}

def deployApp() {
    echo 'Deploying the application to AWS server...'
}

def runApp() {
    echo 'Runing the application...'
}

return this
