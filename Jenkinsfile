pipeline {
    agent any
    stages {
        stage('Check') {
            steps {
                git branch: 'develop',credentialsId:'0-shingo', url:'https://github.com/Spharos-final-project-WOOYANO/Review-Bookmark'
            }
        }
	stage('Secret-File Download') {
	    steps {
	        withCredentials([
		    file(credentialsId:'Review-Secret-File', variable: 'reviewSecret')
		    ])
	        {
		    sh "cp \$reviewSecret ./src/main/resources/application-secret.yml"
		}
  	    }
	}
        stage('Build'){
            steps{
                script {
                    sh '''
                        pwd
                        chmod +x ./gradlew
                        ./gradlew build
                    '''

                }

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
                sh 'docker run --restart=always --network spharos-network -e EUREKA_URL="${EUREKA_URL}" -d --name review-bookmark-service review-bookmark-service-img'
            }
        }
    }
}

