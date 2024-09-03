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
                sh 'mvn clean test'  // Changed from 'mvn clean install' to ensure tests are run
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
                    sh 'mvn clean test'  // Changed from 'mvn clean install' to ensure tests are run
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
                    sh 'mvn clean test'  // Changed from 'mvn clean install' to ensure tests are run
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
            // Clean up workspace after build
            cleanWs()
        }
    }
}