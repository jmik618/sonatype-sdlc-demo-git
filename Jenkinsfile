pipeline {
    agent any

    tools {
        jdk 'Java 17'
        maven 'Maven 3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Unit Test') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Sonatype IQ Policy Evaluation') {
            steps {
                nexusPolicyEvaluation(
                    iqApplication: 'sonatype-sdlc-demo',
                    iqStage: 'build'
                )
            }
        }

        stage('Publish Artifact to Nexus Repository') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }

        success {
            echo 'SDLC demo completed successfully.'
        }

        failure {
            echo 'SDLC demo failed. Review Jenkins console output and Sonatype IQ policy report.'
        }
    }
}
