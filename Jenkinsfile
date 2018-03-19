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
    stage('Prepare Artifacts') {
      steps {
        archiveArtifacts(artifacts: 'desktop/build/libs/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
        sh '''rm -r artifacts
mkdir artifacts

cp -r res artifacts/res 
cp desktop/build/libs/desktop-SNAPSHOT.jar artifacts/desktop-SNAPSHOT.jar
cp core/assets/Configuration.json artifacts/Configuration.json

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