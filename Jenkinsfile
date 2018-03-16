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
      parallel {
        stage('Archive ') {
          steps {
            archiveArtifacts(artifacts: 'desktop/build/libs/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
          }
        }
        stage('Hblog deploy') {
          steps {
            sh '''cp desktop/build/libs/desktop-SNAPSHOT.jar /root/Programs/Hblog/out/res/public/deploy/Escapy_desktop_SNAPSHOT.jar
'''
          }
        }
      }
    }
    stage('Clean') {
      steps {
        sh 'pkill -f gradle'
      }
    }
  }
}