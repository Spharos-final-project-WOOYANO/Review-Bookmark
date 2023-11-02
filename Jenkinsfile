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
		    file(credentialsId:'Wooyano-Secret-File', variable: 'secret')
		    ])
	        {
		    sh "cp \$secret ./src/main/resources/application-secret.yml"
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
                sh 'docker run --network spharos-network -d --name review-bookmark-service review-bookmark-service-img'
            }
        }
    }
}

