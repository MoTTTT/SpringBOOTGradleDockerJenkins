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
		docker stop hello
    		docker rm hello
    		docker run -p 8090:8090 --name hello -t -d motsdockerid/hello:latest
    		docker rmi -f $(docker images -q --filter dangling=true)
    	    '''
	}
    }




    stage('Update Docker UAT image') {
        when { branch "master" }
        steps {
	    sh '''
		docker login -u "<userid>" -p "<password>"
    	        docker build --no-cache -t person .
        	docker tag hello:latest motsdockerid/hello:latest
                docker push motsdockerid/hello:latest
		docker rmi person:latest
    	    '''
	}
    }

    stage('Update UAT container') {
        when { branch "master" }
        steps {
	    sh '''
	        docker login -u "<userid>" -p "<password>"
		docker pull amritendudockerhub/person:latest
		docker stop person
    		docker rm person
    		docker run -p 9090:9090 --name person -t -d amritendudockerhub/person
    		docker rmi -f $(docker images -q --filter dangling=true)
    	    '''
	}
    }

    stage('Release Docker image') {
	when { buildingTag() }
	steps {
    	    sh '''
		docker login -u "<userid>" -p "<password>"
        	docker build --no-cache -t person .
        	docker tag person:latest amritendudockerhub/person:${TAG_NAME}
    	        docker push amritendudockerhub/person:${TAG_NAME}
		docker rmi $(docker images -f “dangling=true” -q)
    	    '''
	}
    }
}
}