pipeline {
  agent any
  stages {
    stage('Check') {
      parallel {
        stage('Check') {
          steps {
            sh 'gradle check --stacktrace'
          }
        }
        stage('rm -r artifacts') {
          steps {
            sh '''rm -r artifacts
'''
          }
        }
      }
    }
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'gradle desktop:dist -x test --stacktrace'
          }
        }
        stage('mkdir artifacts') {
          steps {
            sh '''mkdir artifacts
'''
          }
        }
      }
    }
    stage('Tests') {
      steps {
        sh 'gradle test --stacktrace'
      }
    }
    stage('Prepare Artifacts') {
      steps {
        sh '''cp core/assets/Configuration.json artifacts/Configuration.json
'''
        sh 'cp -r res artifacts/res'
        sh '''cp desktop/build/libs/desktop-SNAPSHOT.jar artifacts/desktop-SNAPSHOT.jar
'''
      }
    }
    stage('Acrtifacts') {
      steps {
        archiveArtifacts(artifacts: 'desktop/build/libs/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
      }
    }
    stage('Clean') {
      steps {
        sh '''pkill -f gradle
rm -r artifacts'''
      }
    }
  }
}