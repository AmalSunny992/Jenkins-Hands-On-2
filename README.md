# Hands On : Create a CI/CD Pipeline for a Weather APP

The project involves setting up Jenkins on AWS, creating a CI pipeline to build and deploy the weatherapp application, deploying Nexus as an artifact repository on an EC2 instance, and uploading/downloading .war files to/from Nexus and Elastic Beanstalk.

## Steps Involved

### Setup Jenkins Master and Slave Nodes

- Launch a Jenkins Master server on AWS Workspace.
- Create a Jenkins Slave node using an EC2 instance to execute jobs.
- Create CI Pipeline for weatherapp

### Configure a Jenkins pipeline (Jenkinsfile) to:
- Build the weatherapp Java application.
- Execute tests and generate reports.
- Package the application into a .war file.
- Deploy Nexus Server on EC2 Instance

### Provision an EC2 instance on AWS to host Nexus.
- Install and configure Nexus as an artifact repository:
- Create a Nexus repository (WeatherAppArtefactory).
- Secure Nexus and configure necessary permissions.
- Set up Nexus credentials in Jenkins (Nexus_admin_cred).
- Upload weatherapp.war to Nexus Artifactory

### Configure Jenkins to use nexusArtifactUploader to upload the .war file:
- Group ID: com.example
- Artifact ID: weatherappartifactory
- Version: 1.0.0-SNAPSHOT
- Repository: WeatherAppArtefactory
- Download Latest .war File from Nexus

### Implement a Jenkins pipeline stage to download the latest .war file from Nexus.
- Upload .war File to Elastic Beanstalk Tomcat Server

### Configure Jenkins to deploy the latest .war file to Elastic Beanstalk:
- Configure AWS credentials in Jenkins.
- Use AWS CLI (aws) to deploy to Elastic Beanstalk.
