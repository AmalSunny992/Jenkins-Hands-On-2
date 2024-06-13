# weather-app

pipeline {
    agent any

    stages {
        stage('CheckOut Code') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/AmalSunny992/Jenkins-Hands-On.git']])
            }
        }
        stage('Maven Build Tool') {
            steps {
        sh 'wget https://dlcdn.apache.org/maven/maven-3/3.9.7/source/apache-maven-3.9.7-src.tar.gz'
        sh 'tar -xzvf /var/lib/jenkins/workspace/test/apache-maven-3.9.7-src.tar.gz'
         }
      }
      stage('Compile Sample App') {
            steps {
            dir('/var/lib/jenkins/workspace/test/addressbook/addressbook_main'){
            sh'var/lib/jenkins/workspace/test/apache-maven-3.9.7/bin/mvn compile'}
            }
          }
       stage('Test Sample App') {
            steps {
            dir('/var/lib/jenkins/workspace/test/addressbook/addressbook_main'){
            sh'var/lib/jenkins/workspace/test/apache-maven-3.9.7/bin/mvn test'}
            }
          }
       stage('Package Sample App') {
            steps {
            dir('/var/lib/jenkins/workspace/test/addressbook/addressbook_main'){
            sh'var/lib/jenkins/workspace/test/apache-maven-3.9.7/bin/mvn package'}
            }
          }
      stage('Upload Artifacts To Nexus) {
            steps {
            dir('/var/lib/jenkins/workspace/test/addressbook/addressbook_main/target'){nexusArtifactUploader artifacts: [[artifactId: 'testartifactid', classifier: '', file: 'addressbook.war', type: 'war']], credentialsId: 'Nexus-Credentials', groupId: 'com.test.app', nexusUrl: '198.19.144.146:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'apprepo', version: '1.1.0-snapshot'}
            }
          }
        stage('Tomcat Prerequisite Installation' {
            steps {
        sh 'sudo apt update'
        sh 'sudo apt install openjdk-17-jre -y'
         }
      }
      stage('Tomcat Server Installation' {
            steps {
        wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.89/bin/apache-tomcat-9.0.89.tar.gz
        tar -xzvf apache-tomcat-9.0.89.tar.gz
        sudo chown -R username:username apache-tomcat-9.0.89
        sudo cp tomcat-users.xml apache-tomcat-9.0.89/conf/tomcat-users.xml
        sudo cp context.xml apache-tomcat-9.0.89/webapps/manager/META-INF/context.xml
        sudo sed -i "s/8080/8082/g" apache-tomcat-9.0.89/conf/server.xml
         }
      }
      stage('Deploy App To Server' {
            steps {
        sh 'sudo cp addressbook/addressbook_main/target/addressbook.war apache-tomcat-9.0.89/webapps/'
        sh 'sudo runuser -l '
         }
    }
}
