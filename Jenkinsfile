pipeline {
  agent any
  stages {
    stage('Check') {
      steps {
        sh 'gradle check --stacktrace'
      }
    }
    stage('Build') {
      steps {
        sh 'gradle desktop:dist -x test --stacktrace'
      }
    }
    stage('Tests') {
      steps {
        sh 'gradle test --stacktrace'
      }
    }
    stage('Archive ') {
      steps {
        archiveArtifacts(artifacts: 'desktop/build/libs/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
      }
    }
    stage('Clean') {
      steps {
        sh 'pkill -f gradle'
      }
    }
  }
}