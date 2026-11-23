pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        PATH = "C:\\Program Files\\DockerDesktop\\resources\\bin;${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'docker'
        DOCKERHUB_REPO = 'janetjk/temp_demo'
        DOCKER_IMAGE_TAG = 'v1'
    }stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'YOUR REPO'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN')]) {
                    withSonarQubeEnv("${env.SONARQUBE_SERVER}") {
                        bat """
sonar-scanner ^
-Dsonar.projectKey=BMI_Cal ^
-Dsonar.sources=src ^
-Dsonar.projectName=BMI_Cal ^
-Dsonar.host.url=http://localhost:9000 ^
-Dsonar.token=%SONAR_TOKEN% ^
-Dsonar.java.binaries=target/classes
"""
                    }
                }
            }
        }
        stage('Check Dockerfile') {
            steps {
                bat 'dir'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
// Or specify Dockerfile path explicitly if needed
// docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}", "-f ./Dockerfile .")
                }
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}