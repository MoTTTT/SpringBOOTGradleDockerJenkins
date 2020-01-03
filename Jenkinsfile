#!/usr/bin/env groovy
pipeline {
    agent any
    options { disableConcurrentBuilds() }
    stages {
        stage('Permissions') {
            steps {
                sh 'chmod 775 *'
            }
        }
    

    stage('Build') {
	steps {
    	    sh './gradlew build'
	}
    }

    stage('Build Docker image') {
        steps {
	    sh '''
    	        docker build --no-cache -t hello .
    	    '''
	}
    }

    stage('Tag Docker image') {
        steps {
	    sh '''
        	docker tag hello:latest motsdockerid/hello:latest
    	    '''
	}
    }

    stage('Run Docker image First time') {
        when { branch "master" }
        steps {
	    sh '''
    		docker run -p 8090:8090 --name hello -t -d motsdockerid/hello:latest
    	    '''
	}
    }


    stage('Re-Run Docker image') {
        steps {
	    sh '''
		docker stop hello
    		docker rm hello
    		docker run -p 8090:8090 --name hello -t -d motsdockerid/hello:latest -v /logs:/apps/logs
    	    '''
	}
    }

    stage('Clean Docker images?') {
        when { branch "master" }
        steps {
	    sh '''
    		docker rmi -f $(docker images -q --filter dangling=true)
    	    '''
	}
    }


}
}