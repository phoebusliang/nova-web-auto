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
        }
        stage('Publish Result') {
            steps {
                publishHTML(target: [
                        allowMissing         : false,
                        alwaysLinkToLastBuild: true,
                        keepAll              : false,
                        reportDir            : 'build/reports/cucumber-html-reports',
                        reportFiles          : 'overview-features.html',
                        reportName           : 'iCare end to end automation report',
                        reportTitles         : ''
                ])
            }
        }

        stage('Cleanup') {
            steps {
                sh 'sh buildtasks/cleanup.sh'
            }
        }
    }
}