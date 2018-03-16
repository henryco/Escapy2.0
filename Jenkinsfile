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
      parallel {
        stage('Tests') {
          steps {
            sh 'gradle check'
          }
        }
        stage('Report') {
          steps {
            junit 'desktop/build/reports/*.jar'
          }
        }
      }
    }
    stage('Archive ') {
      steps {
        archiveArtifacts(artifacts: 'desktop/build/libs/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
      }
    }
  }
}