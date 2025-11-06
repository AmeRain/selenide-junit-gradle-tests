timeout(30) {
    node("macOS-agent-zvuk") {
       echo "Download project"
       checkout scm: [
                $class: 'GitSCM',
                branches: [[name: '${BRANCH}']],
                userRemoteConfigs: [[
                         credentialsId: '55d22fc4-9b34-4351-b023-9dd40de65e50',
                         url: 'git@github.com:AmeRain/selenide-junit-gradle-tests.git'
                         ]]
                ]
       try {
       labelledShell(label: 'Run tests', script: '''
       chmod +x gradlew
       env isRemote=true ./gradlew clean test || true
       ''')
       } finally {
       allure([
                   includeProperties: true,
                   jdk              : '',
                   properties       : [],
                   reportBuildPolicy: 'ALWAYS',
                   results          : [[path: 'build/allure-results']]
               ])
      }
    }

}