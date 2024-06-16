pipeline {
    agent any

    stages {
        stage('CheckOut Code') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/AmalSunny992/weather-app.git']])
            }
        }
        stage('Maven Build Tool') {
            steps {
        sh 'wget https://dlcdn.apache.org/maven/maven-3/3.9.7/binaries/apache-maven-3.9.7-bin.tar.gz'
        sh 'tar -xzvf /home/ubuntu//workspace/weatherapp/apache-maven-3.9.7-bin.tar.gz'
            }
        }
        stage('Compile Sample App') {
            steps {
            dir('/home/ubuntu//workspace/weatherapp/weatherapp/weatherapp_main'){
            sh '/home/ubuntu//workspace/weatherapp/apache-maven-3.9.7/bin/mvn compile'}
            }
        }
       stage('Test Sample App') {
            steps {
            dir('/home/ubuntu//workspace/weatherapp/weatherapp/weatherapp_main'){
            sh '/home/ubuntu//workspace/weatherapp/apache-maven-3.9.7/bin/mvn test'}
            }
        }
       stage('Package Sample App') {
            steps {
            dir('/home/ubuntu//workspace/weatherapp/weatherapp/weatherapp_main'){
            sh '/home/ubuntu//workspace/weatherapp/apache-maven-3.9.7/bin/mvn package'}
            }
        }
      stage('Upload Artifacts To Nexus') {
            steps {
            dir('/home/ubuntu//workspace/weatherapp/weatherapp/weatherapp_main/target'){
                nexusArtifactUploader artifacts: [[artifactId: 'testartifactid', classifier: '', file: 'weather-app.war', type: 'war']], credentialsId: 'Nexus-Credentials', groupId: 'com.test.app', nexusUrl: '198.19.144.146:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'apprepo', version: '1.1.0-SNAPSHOT'
                }
            }
        }
        stage('Tomcat Prerequisite Installation') {
            steps {
        sh 'sudo apt update'
        sh 'sudo apt install openjdk-17-jre -y'
            }
        }
        stage('Tomcat Server Installation') {
            steps {
        sh 'wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.89/bin/apache-tomcat-9.0.89.tar.gz'
        sh 'sudo tar -xzvf apache-tomcat-9.0.89.tar.gz'
        sh 'sudo chown -R tomcat:tomcat apache-tomcat-9.0.89'
        sh 'sudo sed -i "s/8080/8082/g" apache-tomcat-9.0.89/conf/server.xml'
            }
        }
        stage('Deploy App To Server') {
            steps {
        sh 'sudo cp /home/ubuntu//workspace/weatherapp/weatherapp/weatherapp_main/target/weather-app.war apache-tomcat-9.0.89/webapps/'
        sh 'sudo cp tomcat-users.xml apache-tomcat-9.0.89/conf/tomcat-users.xml'
        sh 'sudo cp context.xml apache-tomcat-9.0.89/webapps/manager/META-INF/context.xml'
        sh 'sudo "/home/ubuntu//workspace/weatherapp/apache-tomcat-9.0.89/bin/startup.sh"'
            }
        }
    }
}
