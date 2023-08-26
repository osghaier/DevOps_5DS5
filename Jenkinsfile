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
 
  
}
}
