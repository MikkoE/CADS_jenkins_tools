pipeline {
    agent any

    options {
        timestamps()
    }
        stage('test'){
            steps{
                script{
                    def image = docker.image('mhart/alpine-node:8.11.3')
                      image.pull()
                        image.inside() {
                          sh 'id'
                          sh 'ls -lrt'
                          sh 'node yarn install'
                        }
                }
        }
        stage('Preparation'){
            steps{
                echo 'installing omnetpp'
                sh 'chown -R 1000 /mydir'
                sh '/scripts/omnetinstall.sh'
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
        stage('Run opp_test'){
            steps{
                echo 'testing checkout scm'
            }
        }
        stage('Collect Data'){
            steps{
                echo 'testing checkout scm'
            }
        }
        stage('Clean Up'){
            steps{
                echo 'testing checkout scm'
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
