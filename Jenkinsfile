pipeline {
    agent any

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'framework-demo')
    }

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: "${BRANCH_NAME}",
                    url: 'https://github.com/sapnajog/demomyntra.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            echo 'Test execution completed'
        }
        success {
            echo 'All tests passed'
        }
        failure {
            echo 'Some tests failed'
        }
    }
}