pipeline{

    agent any

    stages{

        stage('Build test suite jar'){
            steps{
                bat "mvn clean package -DskipTest"
            }
        }

        stage('Build docker image'){
            steps{
                bat "docker build -t=minjaeeu/selenium_docker_v4 ."
            }            
        }  

        stage('Push docker image'){
            steps{
                bat "docker push minjaeeu/selenium_docker_v4"
            }            
        }                

    }

}