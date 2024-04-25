#!/usr/bin/env groovy

package com.example

class Docker implements Serializable {
//serializable is resuming from same point if job is paused and started again

    def script

    Docker(script) { // mmake available all the plugins and installations in this file
        this.script = script 
    }

    def buildDockerImage (String imageName) {
        script.echo "building the docker image.."
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-creds', passwordVariable: 'PASS', usernameVariable: 'USER')]){
            script.sh "docker build -t $imageName ."
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
            script.sh "docker push $imageName"
    }
}