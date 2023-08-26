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
    stage("Publish to Nexus Repository Manager") {
      steps {
        script {
          pom = readMavenPom file: "Spring/pom.xml";
          filesByGlob = findFiles(glob: "Spring/target/*.${pom.packaging}");
          echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
          artifactPath = filesByGlob[0].path;
          artifactExists = fileExists artifactPath;
          if (artifactExists) {
            echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}";
            
        }
      }
    }
	}
    stage('Pull the file off Nexus') {
      steps {
        dir('Spring') {
          withCredentials([usernameColonPassword(credentialsId: 'Nexus-Creds', variable: 'NEXUS_CREDENTIALS')]) {
            sh script: 'curl -u ${NEXUS_CREDENTIALS} -o ./target/tpachat.jar "$NEXUS_URL/repository/$NEXUS_REPOSITORY/com/esprit/examen/tpAchatProject/1/tpAchatProject-1.jar"'
          }
        }
      }
    }
    stage('Building Docker Image Spring') {
      steps {
        dir('Spring') {
          sh 'docker build -t $DOCKER_CREDS_USR/tpachatback .'
        }
      }
    }
    stage('Building Docker Image Angular') {
      steps {
        dir('Angular/crud-tuto-front') {
          sh 'docker build -t $DOCKER_CREDS_USR/tpachatfront .'
        }
      }
    }
    stage('Login to DockerHub') {
      steps {
        dir('Spring') {
          echo DOCKER_CREDS_USR
          sh('docker login -u $DOCKER_CREDS_USR -p $DOCKER_CREDS_PSW')
        }
      }
    }
    stage('Push to DockerHub (Angular and Spring )') {
      steps {
        dir('Spring') {
          sh 'docker push $DOCKER_CREDS_USR/tpachatback'
          sh 'docker push $DOCKER_CREDS_USR/tpachatfront'
        }
      }
    }
    stage('Docker Compose') {
      steps {
        sh 'docker-compose up -d'
      }
    }
  
}
}
