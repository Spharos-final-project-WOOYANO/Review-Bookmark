pipeline {
    agent any

    stages {
        stage('Check') {
            steps {
                git branch: 'develop',credentialsId:'jenkins-github-access-token', url:'https://github.com/Spharos-final-project-WOOYANO/Review-Bookmark'
            }
        }
        stage('Build'){
            steps{
                sh '''
                    chmod +x ./gradlew
                    ./gradlew build -x test
                '''
            }
        }
        stage('DockerSize'){
            steps {
                sh '''
                    docker stop review-bookmark-service || true
                    docker rm review-bookmark-service || true
                    docker rmi review-bookmark-service-img || true
                    docker build -t review-bookmark-service-img:latest .
                '''
            }
        }
        stage('Deploy'){
            steps{
                sh 'docker run -d --name review-bookmark-service -p 8005:8000 review-bookmark-service-img'
            }
        }
    }
}
