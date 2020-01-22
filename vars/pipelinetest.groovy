#!/usr/bin/env groovy

def deliverAgent = libraryResource 'deliver.sh'
def call(Map param){
        pipeline {
            agent {
                label "node1"
            }
            stages {
                stage('Build') {
                    steps {
                        sh 'mvn -B -DskipTests clean package'
                    }
                }
                stage('Test') {
                    steps {
                        sh 'mvn test'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Deliver') {
                    steps {
                        //sh "sh $deliverAgent $param.ip"
                         echo deliverAgent
                    }
                }
            }
        }
}
