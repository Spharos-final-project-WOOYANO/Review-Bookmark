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
                    docker stop Review-Bookmark-Service || true
                    docker rm Review-Bookmark-Service || true
                    docker rmi Review-Bookmark-Service-Img || true
                    docker build -t Review-Bookmark-Service-Img:latest .
                '''
            }
        }
        stage('Deploy'){
            steps{
                sh 'docker run -d --name Review-Bookmark-Service -p 8080:8000 Review-Bookmark-Service-Img'
            }
        }
    }
}
