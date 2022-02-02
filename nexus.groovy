pipeline {
    stage('Pull the file off Nexus') {
        withCredentials([usernameColonPassword(credentialsId: 'nexus-admin-user', variable: 'NEXUS_CREDENTIALS')]) {
            sh script: 'curl -u ${NEXUS_CREDENTIALS} -o your_file.png "http://localhost:8083/repository/s3-repo/photos/your_file.png"'
        }
    }
    
stage('Upload file(s) to S3') {
            withAWS(region:'ap-south-1',credentials:'aws_root') {
                s3Upload(bucket:"your-bucket-name", file:'your_file.png');
            }
    }
}
