![Jenkins Badge](https://img.shields.io/badge/Jenkins-D24939?logo=jenkins&logoColor=fff&style=for-the-badge)
![Apache Maven Badge](https://img.shields.io/badge/Apache%20Maven-C71A36?logo=apachemaven&logoColor=fff&style=for-the-badge)
![Amazon AWS Badge](https://img.shields.io/badge/Amazon%20AWS-232F3E?logo=amazonaws&logoColor=fff&style=for-the-badge)
![Sonatype Badge](https://img.shields.io/badge/Sonatype-1B1C30?logo=sonatype&logoColor=fff&style=for-the-badge)
![Amazon EC2 Badge](https://img.shields.io/badge/Amazon%20EC2-F90?logo=amazonec2&logoColor=fff&style=for-the-badge)
![Amazon S3 Badge](https://img.shields.io/badge/Amazon%20S3-569A31?logo=amazons3&logoColor=fff&style=for-the-badge)
![Apache Tomcat Badge](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?logo=apachetomcat&logoColor=000&style=for-the-badge)

# Hands On : Create a CI/CD Pipeline for a Weather APP

The project involves setting up Jenkins on AWS, creating a CI pipeline to build and deploy the weatherapp application, deploying Nexus as an artifact repository on an EC2 instance, and uploading/downloading .war files to/from Nexus and Elastic Beanstalk.

## Steps Involved

### Setup Jenkins Master and Slave Nodes

- Launch a Jenkins Master server on AWS Workspace.
- Create a Jenkins Slave node using an EC2 instance to execute jobs.
- Create CI Pipeline for weatherapp

  Screenshots :

  ![image](https://github.com/user-attachments/assets/169422802/932e9c42-6f6e-4eef-a68a-2fda669d0f43)


### Configure a Jenkins pipeline (Jenkinsfile) to:
- Build the weatherapp Java application using Maven.
- Execute tests and generate reports.
- Package the application into a .war file.
- Deploy Nexus Server on EC2 Instance

  [click to view Jenkinsfile for CI](./Jenkinsfile_CI)

  Screenshots :

  ![image](https://github.com/AmalSunny992/weather-app/assets/169422802/a3b76395-5658-4fa1-980d-6bf89123144d)

  ![image](https://github.com/AmalSunny992/weather-app/assets/169422802/d7935aab-ce86-4593-83ce-dc52fb43270c)



### Provision an EC2 instance on AWS to host Nexus.
- Install and configure Nexus as an artifact repository:
- Create a Nexus repository (WeatherAppArtefactory).
- Secure Nexus and configure necessary permissions.
- Set up Nexus credentials in Jenkins (Nexus_admin_cred).
- Upload weatherapp.war to Nexus Artifactory

Screenshots:

![image](https://github.com/AmalSunny992/weather-app/assets/169422802/50977a22-cfc0-429f-ae62-5bf053e1124a)

![image](https://github.com/AmalSunny992/weather-app/assets/169422802/6dc3f880-f0fb-45a5-ab68-384446f51b8d)


### Configure Jenkins to use nexusArtifactUploader to upload the .war file:
- Group ID: com.example
- Artifact ID: weatherappartifactory
- Version: 1.0.0-SNAPSHOT
- Repository: WeatherAppArtefactory
- Download Latest .war File from Nexus

  Screenshots:

  ![image](https://github.com/AmalSunny992/weather-app/assets/169422802/32029b57-ff8e-41dc-a135-b71e58e4a243)


### Implement a Jenkins pipeline stage to download the latest .war file from Nexus.
- Upload .war File to Elastic Beanstalk Tomcat Server

  [Click here to view Jenkinsfile for CD](./Jenkinsfile_CD)

### Configure Jenkins to deploy the latest .war file to Elastic Beanstalk:
- Configure AWS credentials in Jenkins.
- Use AWS CLI (aws) to deploy to Elastic Beanstalk.

  Screenshots:
  ![image](https://github.com/AmalSunny992/weather-app/assets/169422802/a4d9c9cc-e58a-40c0-b00e-1438386ab7ee)
  
  ![image](https://github.com/AmalSunny992/weather-app/assets/169422802/571a61a0-4370-4c76-a55f-b127cd92399d)

  ![image](https://github.com/AmalSunny992/weather-app/assets/169422802/57a6a2c8-e3f1-419b-a591-9e3d1280d95a)

  ![image](https://github.com/AmalSunny992/weather-app/assets/169422802/0cb65d0d-dfe0-466e-be65-b073f62d1216)

  ![image](https://github.com/AmalSunny992/weather-app/assets/169422802/eb688f71-2bee-4f05-a7d9-5e533ef46bb2)


