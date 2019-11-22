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
                  git branch: 'FEATURE/DENISLUG/Quic-develop',
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
                    image.inside{

                        // first information showing
                        sh 'ls -l'

                        // installing omnetpp
                        sh 'wget https://github.com/omnetpp/omnetpp/releases/download/omnetpp-5.4.1/omnetpp-5.4.1-src-linux.tgz \
                            && tar -xzf omnetpp-5.4.1-src-linux.tgz \
                            && rm omnetpp-5.4.1-src-linux.tgz \
                            && mv omnetpp-5.4.1 omnetpp'
                        //sh 'PATH=$PATH:${pwd}/omnetpp/bin'
                        sh 'cd omnetpp && ./configure WITH_TKENV=no WITH_QTENV=no WITH_OSG=no WITH_OSGEARTH=no WITH_PARSIM=no'
                        sh 'cd omnetpp && make -j$(grep -c proc /proc/cpuinfo)'

                        // installing inet
                        sh 'cd inet-private && cat README.md'
                        sh 'cd inet-private && cat INSTALL'
                        sh 'cd inet-private && cat Makefile'
                        sh 'cd inet-private && make makefiles'
                        sh 'cd inet-private && make MODE=debug'

                        //running the tests
                        //first a inet testcandidate to validate running tests
                        //sh 'cd inet-private/tests/unit/ && ./runtest QUICPathChallengeResponse.test'
                        //sh 'cd inet-private/tests/packetdrill/quic/ && ./runtest'

                        //override the ned file
                        sh 'cd inet-private/examples/quic/basic && rm simpleQuicSetup.ned'
                        sh 'cp quic-tests/simpleQuicSetup.ned inet-private/examples/quic/basic/'

                        //trying to copy test for quic
                        sh 'cp quic-tests/QuicHandshake.test inet-private/tests/unit/'
                        sh 'cd inet-private/tests/unit/ && ls -l'
                        sh 'cd inet-private/tests/unit/ && ./runtest QuicHandshake.test'
                    }
                }
            }
        }
        stage('testresults'){
            steps{
                sh 'cd inet-private/tests/unit/work && ls -l'
                sh 'zip -r work_quicHandshake.zip QuicHandshake'
                sh 'archiveArtifacts artifacts: 'work_quicHandshake.zip', fingerprint: true'
            }
        }
    }
    post {
        success {
            echo 'Pipe Stage successfull'
        }
        failure {
            echo 'Pipe Stage failure'
            sh 'ls'
        }
        always {
            echo 'always here for you'
            cleanWs()
        }
    }
}
