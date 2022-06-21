pipeline { 
    environment {
        registry = "quangnghi/student-app-client" 
        registryCredential = 'dockerhub' 
        dockerImage = '' 
    }
    agent any 
    stages { 
        stage('Cloning Git') { 
            steps { 
                git credentialsId: 'GIT_HUB_CREDENTIALS', url: 'https://github.com/quangnghi99/kubernetes-full-stack-example.git' 
            }
        } 
        stage('Building image') { 
            steps { 
                dir('react-student-management-web-app') {
                    script { 
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                    }
                }
                dir('spring-boot-student-app-api') {
                    sh 'mvn install'
                }
            }
        } 
        
        stage('Deploy image') { 
            steps { 
                script { 
                    docker.withRegistry( '', registryCredential ) { 
                        dockerImage.push() 
                    }
                }
                dir('spring-boot-student-app-api') {
                    sh 'mvn dockerfile:push'
                } 
            }
        }  
    }
}