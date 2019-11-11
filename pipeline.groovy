pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage('Preparation'){
            steps{
                //sh 'ls'
                sh 'id -u jenkins'
            }
        }
        stage('Git checkout'){
            steps{
                dir('inet-private'){
                  echo 'checkout inet-private'
                  git branch: 'FEATURE/MIKKOE/Quic-test',
                    credentialsId: 'fd377909-72a2-44f5-b89e-787344533514',
                    url: 'https://github.com/Transport-Protocol/inet-private.git'
                  sh 'git submodule update --init'
                }
            }
        }
        stage('docker-omnetpp'){
            steps{
                echo 'trying docker container'
                script{
                    def image = docker.image('mikkoe/omnetpp-inet-docker')
                    image.pull()
                    image.withRun('-u root'){

                        // first information showing
                        sh 'ls -l'


                        // installing omnetpp
                        sh 'wget https://github.com/omnetpp/omnetpp/releases/download/omnetpp-5.4.1/omnetpp-5.4.1-src-linux.tgz \
                            && tar -xzf omnetpp-5.4.1-src-linux.tgz \
                            && rm omnetpp-5.4.1-src-linux.tgz \
                            && mv omnetpp-5.4.1 omnetpp'

                        sh 'echo PATH=$PATH:pwd/omnetpp/bin'
                        sh 'PATH=$PATH:pwd/omnetpp/bin'
                        //sh 'su cat /etc/sudoers'
                        //sh 'echo $PATH'
                        //sh 'whoami'
                        //sh 'id -u jenkins'
                        //sh 'cat README.md'
                        //sh 'cat INSTALL'
                        //sh 'cat Makefile'
                        //sh 'ls src/'
                        //sh 'make makefiles'
                        //sh 'make MODE=debug'
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
