pipeline {
  agent any
 
  stages {

    stage("Maven Clean") {
      steps {
        script {
          sh "mvn -f'Spring/pom.xml' clean -DskipTests=true -Drevision=${VERSION}"
        }
      }
    }
    stage("Maven Compile") {
      steps {
        script {
          sh "mvn -f'Spring/pom.xml' compile -DskipTests=true -Drevision=${VERSION}"
        }
      }
    }
    stage("Maven test") {
      steps {
        script {
          sh "mvn -f'Spring/pom.xml' test -Drevision=${VERSION}"
        }
      }
    }
    stage("Maven Sonarqube") {
      steps {
        script {
          sh "mvn -f'Spring/pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=user -Drevision=${VERSION}"
        }
      }
    }
    stage("Maven Build") {
      steps {
        script {
          sh "mvn -f'Spring/pom.xml' package -DskipTests=true -Drevision=${VERSION}"
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
            echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${VERSION}";
            
        }
      }
    }
    stage('Pull the file off Nexus') {
      steps {
        dir('Spring') {
          withCredentials([usernameColonPassword(credentialsId: 'Nexus-Creds', variable: 'NEXUS_CREDENTIALS')]) {
            sh script: 'curl -u ${NEXUS_CREDENTIALS} -o ./target/tpachat.jar "$NEXUS_URL/repository/$NEXUS_REPOSITORY/com/esprit/examen/tpAchatProject/$VERSION/tpAchatProject-$VERSION.jar"'
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

 /* post {
        always {
            emailext body: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS:Check console output at $BUILD_URL to view the results.', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Build Result'
        }
    } */
}
