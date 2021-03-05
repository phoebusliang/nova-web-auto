pipeline {
    agent any
    stages {
        stage('Checkout Latest Code') {
            steps {
                checkout scm
            }
        }
        stage('Run web end to end test') {
            steps {
                echo '***Start iCare API Automation***'
                sh 'buildtasks/run-test-docker.sh local cn'
            }

            post {
                always {
                    cucumber reportTitle: 'iCare web automation report',
                            fileIncludePattern: '**/*.feature.json',
                            sortingMethod: 'ALPHABETICAL',
                            trendsLimit: 100
                    echo '*** Clean Up ***'
                    sh 'sh buildtasks/clean.sh'
                }
            }
        }
    }
}