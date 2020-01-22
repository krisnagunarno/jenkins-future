#!/usr/bin/env groovy
def remote = [:]
    remote.name = 'target'
    remote.host = '172.20.10.4'
    remote.user = 'root'
    remote.password = 'future4.0'
    remote.allowAnyHosts = true
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sshCommand remote: remote, command: "cd /home/futureprogram"
                sshCommand remote: remote, command: "git clone https://github.com/ericsonrumuy7/Jenkins-future.git"
            }
        }
        stage('Test') {
            steps {
                sshCommand remote: remote, command: "mvn test"
            }
        }
        stage('Deliver') {
            steps {
                sshCommand remote: remote, command: "sh jenkins/scripts/deliver.sh"
            }
        }
    }
}

