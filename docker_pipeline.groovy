#!groovy

node {
  checkout scm
  docker.withRegistry('cads-docker.cpt.haw-hamburg.de:5000'){
    docker.image('cads-docker.cpt.haw-hamburg.de:5000/node')
  }

  stage('Test') {
    sh 'node --version'
  }
}
