pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage('Preparation'){
            steps{
                sh 'ls'
            }
        }
        stage('Git checkout'){
            steps{
                echo 'testing checkout scm'
                git branch: 'FEATURE/MIKKOE/Quic-test',
                  credentialsId: 'fd377909-72a2-44f5-b89e-787344533514',
                  url: 'https://github.com/Transport-Protocol/inet-private.git'
            }
        }
        stage('docker-omnetpp'){
            steps{
                echo 'trying docker container'
                script{
                    def image = docker.image('niessan/omnetpp-inet')
                    image.pull()
                    image.inside() {

                        sh 'ls'
                        sh 'echo $PATH'
                        sh 'cat README.md'
                        sh 'cat INSTALL'
                    }
                }
            }
        }
    }
    post {
        success {
            echo 'Pipe Stage successfull'
        }
        failure {
            echo 'Pipe Stage failure'
        }
        always {
            echo 'always here for you'
            cleanWs()
        }
    }
}
