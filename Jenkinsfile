pipeline {
  agent any
  stages {
    stage('Check') {
      steps {
        sh 'gradle check'
      }
    }
    stage('Build') {
      steps {
        sh 'gradle desktop:dist -x test'
      }
    }
    stage('Tests') {
      steps {
        sh 'gradle test'
      }
    }
    stage('Archive ') {
      parallel {
        stage('Archive ') {
          steps {
            archiveArtifacts(artifacts: 'desktop/build/libs/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
          }
        }
        stage('Hblog deploy') {
          steps {
            sh 'echo '
          }
        }
      }
    }
  }
}