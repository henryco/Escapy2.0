pipeline {
  agent any
  stages {
    stage('Check') {
      steps {
        sh 'gradle check -x desktop:dist -x test --stacktrace'
      }
    }
    stage('Test') {
      steps {
        sh '(gradle test --stacktrace) || true'
        junit(testResults: 'build/test-results/*.xml', allowEmptyResults: true)
        sh 'rm -f -r test-arch'
        sh 'mkdir test-arch'
        sh '(zip -r test-arch/test-report.zip build/reports) || true'
        archiveArtifacts(artifacts: 'test-arch/*.zip', allowEmptyArchive: true)
      }
    }
    stage('Build') {
      steps {
        sh 'gradle desktop:dist -x test --stacktrace'
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
        sh 'cp release/desktop-SNAPSHOT.zip /home/Programs/Hblog/out/res/public/deploy/files/desktop-SNAPSHOT.zip'
        sh 'cd /home/Programs/Hblog/out/res/public/deploy/ && ./build-update-version.sh'
      }
    }
    stage('Clean') {
      steps {
        sh '(pkill -f gradle) || true'
      }
    }
  }
}