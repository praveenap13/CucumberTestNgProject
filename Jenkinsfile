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
                junit 'target/surefire-reports/testng-results.xml'

                // Publish Cucumber/Extent HTML report if generated
                publishHTML([
                allowMissing: false,
                 alwaysLinkToLastBuild: true,
                   keepAll: true,
                    reportDir: 'target',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }
}