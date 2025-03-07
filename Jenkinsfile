pipeline{

    agent any

    stages{

        stage('Build test suite jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build docker image'){
            steps{
                bat "docker build -t=minjaeeu/selenium_docker_v4 ."
            }            
        }  

        stage('Push docker image'){
            environment{
                DOCKER_HUB = credentials('dockerhub-credentials')
            }
            steps{
                bat "docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%"
                bat "docker push minjaeeu/selenium_docker_v4"
            }            
        }                

    }

}