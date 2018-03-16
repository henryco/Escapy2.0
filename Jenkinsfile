pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        sh 'echo "Initialize"'
      }
    }
    stage('Build') {
      steps {
        sh '''gradle clean
gradle desktop:dist -x test'''
      }
    }
    stage('Tests') {
      steps {
        sh 'echo "Todo: tests"'
      }
    }
    stage('Archive ') {
      steps {
        archiveArtifacts(artifacts: 'desktop/build/libs/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
      }
    }
  }
}