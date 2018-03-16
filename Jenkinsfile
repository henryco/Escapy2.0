pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        sh '''echo "Initialize"
gradle clean'''
      }
    }
    stage('Build') {
      steps {
        sh 'gradle desktop:dist -x test'
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