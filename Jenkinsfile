pipeline {
  agent any
  stages {
    
    stage('Check') {
      steps {
        sh 'gradle clean'
        sh 'gradle check -x desktop:dist -x test --stacktrace'
      }
    }
    
    stage('Test') {
      steps {
        sh 'gradle test --stacktrace'
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
        sh './package.sh RELEASE'
      }
    }
    
    stage('Prepare artifacts DEVELOPE') {
      when {
        branch 'develope'
      }
      steps {
        sh './package.sh SNAPSHOT'
      }
    }
    
    
    
    stage('Acrtifacts RELEASE') {
      when {
        branch 'release'
      }
      steps {
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
        archiveArtifacts(artifacts: 'release/*.zip', onlyIfSuccessful: true)
        sh 'cp release/desktop-SNAPSHOT.zip /home/Programs/Hblog/out/res/public/deploy/files/desktop-SNAPSHOT.zip'
        sh 'cd /home/deploy-props/Hblog/scripts/ && ./build-update-version.sh'
      }
    }

    stage('Finish') {
      steps {
        sh 'echo FINISH'
      }
    }

  }
  
  post {

    always {
      
      junit(testResults: 'tests/build/test-results/*.xml', allowEmptyResults: true)
      sh 'rm -f -r test-arch'
      sh 'mkdir test-arch'
      sh '(zip -r test-arch/test-report.zip tests/build/reports) || true'
      archiveArtifacts(artifacts: 'test-arch/*.zip', allowEmptyArchive: true)

      sh 'gradle clean'
      sh '(pkill -f gradle) || true'
      sh '(rm -r artifacts) || true'
      sh '(rm -r release) || true'
    }

    success {
      script {
        if (env.GIT_BRANCH == "develope" || env.GIT_BRANCH == "release")
            mail bcc: '', body: "<body><h2 style=\"color:green\">Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] deployed successful</h2> <h3>Commit: <a style=\"color:black\" href=\"https://bitbucket.org/tinder-samurai/escapy2.0/commits/${env.GIT_COMMIT}\">${env.GIT_COMMIT}</a></h3> <br> <ul> <li><b><a href=\"${env.BUILD_URL}\">Build page reference</a></b></li> <li><b><a href=\"${env.GIT_URL}\">Bitbucket project reference</a></b></li></ul></body>", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] SUCCESS", to: "henrycodev@gmail.com"
      }
    }

    failure {
      script {
        if (env.GIT_BRANCH == "develope" || env.GIT_BRANCH == "release")
            mail bcc: '', body: "<body><h2 style=\"color:red\">Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] failure</h2> <h3>Commit: <a style=\"color:black\" href=\"https://bitbucket.org/tinder-samurai/escapy2.0/commits/${env.GIT_COMMIT}\">${env.GIT_COMMIT}</a></h3> <br> <ul> <li><b><a href=\"${env.BUILD_URL}\">Build page reference</a></b></li> <li><b><a href=\"${env.GIT_URL}\">Bitbucket project reference</a></b></li></ul></body>", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] FAILURE", to: "henrycodev@gmail.com"
      }
    }

    unstable {
      script {
        if (env.GIT_BRANCH == "develope" || env.GIT_BRANCH == "release")
            mail bcc: '', body: "<body><h2 style=\"color:orange\">Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] unstable</h2> <h3>Commit: <a style=\"color:black\" href=\"https://bitbucket.org/tinder-samurai/escapy2.0/commits/${env.GIT_COMMIT}\">${env.GIT_COMMIT}</a></h3> <br> <ul> <li><b><a href=\"${env.BUILD_URL}\">Build page reference</a></b></li> <li><b><a href=\"${env.GIT_URL}\">Bitbucket project reference</a></b></li></ul></body>", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "Escapy2.0 build [${env.BUILD_NUMBER}] [${env.GIT_BRANCH}] UNSTABLE", to: "henrycodev@gmail.com"
      }
    }

  }
  
}
