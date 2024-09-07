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
                   catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                       sh 'mvn clean test -Dcucumber.options="--tags ~@ignore"'
                   }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    cucumber buildStatus: 'UNSTABLE',
                    fileIncludePattern: '**/cucumber.json',
                    jsonReportDirectory: 'target'
                }
            }
        }

        stage('Build and Test API Layer') {
            steps {
                    catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                        dir('ApiLayer') {
                            sh 'mvn clean test -Dcucumber.options="--tags ~@ignore"'
                        }
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
                    catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                        dir('DatabaseLayer') {
                            sh 'mvn clean test'
                        }
                    }
            Z}
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Generate Cucumber Reports') {
                   steps {
                       cucumber buildStatus: 'UNSTABLE',
                                fileIncludePattern: '**/cucumber.json',
                                jsonReportDirectory: 'target'
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