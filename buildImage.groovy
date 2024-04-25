#!/usr/bin/env groovy

def call(){
    echo "building the docker image.."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', passwordVariable: 'PASS', usernameVariable: 'USER')]){
    sh 'docker build -t hemu07/hemali_repo:jma-5.0 .'
    sh "echo $PASS | docker login -u $USER --password-stdin"
    sh 'docker push hemu07/hemali_repo:jma-5.0'
    }
}