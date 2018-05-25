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
        sh 'gradle desktop:dist --stacktrace'
      }
    }
    
    
    stage('Prepare artifacts RELEASE') {
      when {
        branch 'release'
      }
      steps {
        sh 'rm -f -r artifacts'
        sh 'mkdir artifacts'
        sh 'cp core/assets/Configuration.json artifacts/Configuration.json'
        sh 'cp -r res artifacts/res'
        sh 'cp desktop/build/libs/desktop-RELEASE.jar artifacts/desktop-RELEASE.jar'
        sh 'rm -r -f release'
        sh 'mkdir release'
        sh 'zip -r release/desktop-RELEASE.zip artifacts'
        sh 'rm -r -f artifacts'
      }
    }
    
    stage('Prepare artifacts DEVELOPE') {
      when {
        branch 'develope'
      }
      steps {
        sh 'rm -f -r artifacts'
        sh 'mkdir artifacts'
        sh 'cp core/assets/Configuration.json artifacts/Configuration.json'
        sh 'cp -r res artifacts/res'
        sh 'cp desktop/build/libs/desktop-SNAPSHOT.jar artifacts/desktop-SNAPSHOT.jar'
        sh 'rm -r -f release'
        sh 'mkdir release'
        sh 'zip -r release/desktop-SNAPSHOT.zip artifacts'
        sh 'rm -r -f artifacts'
      }
    }
    
    
    
    stage('Acrtifacts RELEASE') {
      when {
        branch 'release'
      }
      steps {
        archiveArtifacts(artifacts: 'desktop/build/libs/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
        archiveArtifacts(artifacts: 'release/*.zip', onlyIfSuccessful: true)
        sh 'cp release/desktop-RELEASE.zip /home/Programs/Hblog/out/res/public/deploy/files/desktop-RELEASE.zip'
        sh 'cd /home/deploy-props/Hblog/scripts/ && ./release-update-version.sh'
      }
    }
    
    stage('Acrtifacts DEVELOPE') {
      when {
        branch 'develope'
      }
      steps {
        archiveArtifacts(artifacts: 'desktop/build/libs/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
        archiveArtifacts(artifacts: 'release/*.zip', onlyIfSuccessful: true)
        sh 'cp release/desktop-SNAPSHOT.zip /home/Programs/Hblog/out/res/public/deploy/files/desktop-SNAPSHOT.zip'
        sh 'cd /home/deploy-props/Hblog/scripts/ && ./build-update-version.sh'
      }
    }
    
    
    stage('Clean') {
      steps {
        sh '(pkill -f gradle) || true'
      }
    }
  }
  
  post {
    success {
      mail bcc: '', body: "<body><h2 style=\"color:green\">Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] deployed successful</h2> <h3>Commit: <a style=\"color:black\" href=\"https://github.com/henryco/Escapy2.0/commit/${env.GIT_COMMIT}\">${env.GIT_COMMIT}</a></h3> <br> <ul> <li><b><a href=\"${env.BUILD_URL}\">Build page reference</a></b></li> <li><b><a href=\"${env.GIT_URL}\">Gitub project reference</a></b></li></ul></body>", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] SUCCESS", to: "henrycodev@gmail.com"
    }
    failure {
      mail bcc: '', body: "<body><h2 style=\"color:red\">Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] failure</h2> <h3>Commit: <a style=\"color:black\" href=\"https://github.com/henryco/Escapy2.0/commit/${env.GIT_COMMIT}\">${env.GIT_COMMIT}</a></h3> <br> <ul> <li><b><a href=\"${env.BUILD_URL}\">Build page reference</a></b></li> <li><b><a href=\"${env.GIT_URL}\">Gitub project reference</a></b></li></ul></body>", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] FAILURE", to: "henrycodev@gmail.com"
    }
    unstable {
      mail bcc: '', body: "<body><h2 style=\"color:orange\">Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] unstable</h2> <h3>Commit: <a style=\"color:black\" href=\"https://github.com/henryco/Escapy2.0/commit/${env.GIT_COMMIT}\">${env.GIT_COMMIT}</a></h3> <br> <ul> <li><b><a href=\"${env.BUILD_URL}\">Build page reference</a></b></li> <li><b><a href=\"${env.GIT_URL}\">Gitub project reference</a></b></li></ul></body>", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] UNSTABLE", to: "henrycodev@gmail.com"
    }
  }
  
}
