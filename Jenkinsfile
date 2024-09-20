pipeline {

    agent any

    tools {
        maven 'Maven_3'
    }

    stages {
        stage('Checkout Repos') {
            steps {
                // UI Layer Repository
                checkout scm

                // API Layer Repository
                dir('ApiLayer') {
                    git url: 'https://github.com/Riaz11-coder/CucumberAPITest', branch: 'main'
                }

                // Database Layer Repository
                dir('DatabaseLayer') {
                    git url: 'https://github.com/Riaz11-coder/BookStoreDatabase', branch: 'main'
                }
            }
        }

        stage('Build and Test UI Layer') {
            steps {
                //browsestack credentials wrapped
                browserstack(credentialsId: 'c3dc1acf-10ce-4eb8-b7d3-3b4d44a21889') {
                    // Run tests using Maven
                    sh 'mvn clean test -Dmaven.test.failure.ignore=true || (echo "UI Layer tests failed"; exit 1)'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build and Test API Layer') {
            steps {
                dir('ApiLayer') {
                    sh 'mvn clean test -Dmaven.test.failure.ignore=true || (echo "API Layer tests failed"; exit 1)'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build and Test Database Layer') {
            steps {
                dir('DatabaseLayer') {
                    sh 'mvn clean test -Dmaven.test.failure.ignore=true || (echo "Database Layer tests failed"; exit 1)'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        always {
            browserStackReportPublisher 'automate'
            cleanWs()
        }
        failure {
            echo 'The pipeline failed. Please check the console output for details.'
        }
    }
}
