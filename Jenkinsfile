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
    stage('Prepare artifacts') {
      steps {
        sh 'rm -f -r artifacts'
        sh 'mkdir artifacts'
        sh '''cp core/assets/Configuration.json artifacts/Configuration.json
'''
        sh 'cp -r res artifacts/res'
        sh 'cp desktop/build/libs/desktop-SNAPSHOT.jar artifacts/desktop-SNAPSHOT.jar'
        sh 'rm -r -f release'
        sh 'mkdir release'
        sh 'zip -r release/desktop-SNAPSHOT.zip artifacts'
        sh 'rm -r -f artifacts'
      }
    }
    stage('Acrtifacts') {
      steps {
        archiveArtifacts(artifacts: 'desktop/build/libs/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
        archiveArtifacts(artifacts: 'release/*.zip', onlyIfSuccessful: true)
        sh '''cp release/desktop-SNAPSHOT.zip /root/Programs/Hblog/out/res/public/deploy/desktop-SNAPSHOT.zip
'''
      }
    }
    stage('Clean') {
      steps {
        sh 'pkill -f gradle'
      }
    }
  }
}