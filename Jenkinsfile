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
    
}
}
