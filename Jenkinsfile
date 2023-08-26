pipeline {
  agent any
 
  stages {

    stage("Maven Clean") {
      steps {
        script {
          sh "mvn -f'Spring/pom.xml' clean -DskipTests=true "
        }
      }
    }
    stage("Maven Compile") {
      steps {
        script {
          sh "mvn -f'Spring/pom.xml' compile -DskipTests=true "
        }
      }
    }
    stage("Maven test") {
      steps {
        script {
          sh "mvn -f'Spring/pom.xml' test "
        }
      }
    }
    stage("Maven Sonarqube") {
      steps {
        script {
          sh "mvn -f'Spring/pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=user "
        }
      }
    }
    stage("Maven Build") {
      steps {
        script {
          sh "mvn -f'Spring/pom.xml' package -DskipTests=true "
        }
        echo ":$BUILD_NUMBER"
      }
    }
   /* stage("Publish to Nexus Repository Manager") {
      steps {
        script {
          pom = readMavenPom file: "Spring/pom.xml"
          
      }
    }
	}*/
 /*   stage('Pull the file off Nexus') {
      steps {
        dir('Spring') {
          withCredentials([usernameColonPassword(credentialsId: 'Nexus-Creds', variable: 'NEXUS_CREDENTIALS')]) {
            sh script: 'curl  ./target/tpachat.jar "http://192.168.202.130:8081/repository/tpAchatProject-1.jar"'
          }
        }
      }
    }*/
	/* stage("Deployment stage") {
            steps {
                script {
                pom = readMavenPom file: 'pom.xml';
                   echo "${pom.artifactId}-${pom.version}.${pom.packaging}"
                   sh "mvn deploy:deploy-file  -DskipTests=true -DgroupId=${pom.groupId} -DartifactId=${pom.artifactId} -Dversion=${pom.version}  -DgeneratePom=true -Dpackaging=${pom.packaging}  -DrepositoryId=deploymentRepo -Durl=http://192.168.202.130:8081/repository/maven-releases/ -Dfile=target/${pom.artifactId}-${pom.version}.${pom.packaging}"
                }
            }
        }  */ 
  /*  stage('Building Docker Image Spring') {
      steps {
        dir('Spring') {
          sh 'docker build -t $DOCKER_CREDS_USR/tpachatback .'
        }
      }
    } */
   /* stage('Building Docker Image Angular') {
      steps {
        dir('Angular/crud-tuto-front') {
          sh 'docker build -t $DOCKER_CREDS_USR/tpachatfront .'
        }
      }
    } */
    /* stage('Login to DockerHub') {
      steps {
        dir('Spring') {
          echo DOCKER_CREDS_USR
          sh('docker login -u $DOCKER_CREDS_USR -p $DOCKER_CREDS_PSW')
        }
      }
    } */
   /* stage('Push to DockerHub (Angular and Spring )') {
      steps {
        dir('Spring') {
          sh 'docker push $DOCKER_CREDS_USR/tpachatback'
          sh 'docker push $DOCKER_CREDS_USR/tpachatfront'
        }
      }
    } */
	  
  /*  stage('Docker Compose') {
      steps {
        sh 'docker-compose up -d'
      }
    }
  */
}
}
