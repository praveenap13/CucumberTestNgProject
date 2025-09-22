pipeline {
    agent any

    tools {
        maven '3.8.6'    // Jenkins Maven tool name (set in "Global Tool Configuration")
        jdk 'JAVA_HOME'      // Jenkins JDK name (set in "Global Tool Configuration")
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'jenkinsadmin',
                    url: 'https://github.com/praveenap13/CucumberTestNgProject.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Publish Reports') {
            steps {
                // Publish TestNG results
                junit 'target/surefire-reports/emailable-report.html'

                // Publish Cucumber/Extent HTML report if generated
                publishHTML([
                allowMissing: false,
                 alwaysLinkToLastBuild: true,
                   keepAll: true,
                    reportDir: 'target/extent-reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Cucumber Test Results'
                ])
            }
        }
    }
}