pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage('Git checkout'){
            steps{
                echo 'testing checkout scm'
                git branch: 'FEATURE/MIKKOE/Quic-test',
                  credentialsId: 'dd63bda9-0183-423f-946a-1d4fd81713ee',
                  url: 'https://github.com/Transport-Protocol/inet-private.git'
            }
        }
        stage('Preparation'){
            steps{
                echo 'testing checkout scm'
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
        }
    }
}
