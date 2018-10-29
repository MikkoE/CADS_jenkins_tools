pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage('Dependencies'){
            steps{
                echo 'Hello World'
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
