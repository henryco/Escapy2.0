pipeline {
  agent any
  stages {
    stage('Initial prepare') {
      steps {
        sh '''rm -f -r artifacts

'''
        sh 'gradle check --stacktrace'
      }
    }
    stage('Build') {
      steps {
        sh 'gradle desktop:dist -x test --stacktrace'
        sh '''mkdir artifacts
'''
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
        sh 'pkill -f gradle'
        sh 'rm -r artifacts'
      }
    }
  }
}