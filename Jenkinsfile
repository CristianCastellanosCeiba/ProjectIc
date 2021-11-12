pipeline {
    //Donde se va a ejecutar el Pipeline
    agent {
    label 'Slave_Mac'
}

//Opciones específicas de Pipeline dentro del Pipeline
options {
	buildDiscarder(logRotator(numToKeepStr: '3'))
	disableConcurrentBuilds()
}

//Una sección que define las herramientas “preinstaladas” en Jenkins (JDK, Gradle, Ant, Maven, Node), si no se usan en el proyecto no configura esta sección.
tools {
    jdk 'JDK8_Mac' //Versión preinstalada en la Configuración del Master
}
// Existen actualmente en el agente JDK (8, 11, 12)
// Para JDK 8: JDK8_Mac
// Para JDK 11: JDK11_Mac
// Para el JDK 12: No indicar variable JDK, está por default en la máquina

//Aquí comienzan los “ítems” del Pipeline
stages{

stage('Compile') {
    steps {
        echo "------------>Compile<------------"
        sh 'chmod +x ./gradlew'
        sh './gradlew build -x test'
    }
}

stage('Unit Tests') {
    steps{
        echo "------------>Unit Tests<------------"
        sh './gradlew clean'
        sh './gradlew test'
        sh './gradlew jacocoTestReport'
    }
}

stage('Static Code Analysis') {
    steps{
        echo '------------>Análisis de código estático<------------'
            withSonarQubeEnv('Sonar') {
                sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
            }
        }
    }
}

post {
    always {
        echo 'This will always run'
    }
    success {
        echo 'This will run only if successful'
        junit 'build/test-results/test/*.xml'
    }
    failure {
        echo 'This will run only if failed'
            mail (to: ‘cristian.castellanos@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")
    }
    unstable {
        echo 'This will run only if the run was marked as unstable'
    }
    changed {
        echo 'This will run only if the state of the Pipeline has changed'
        echo 'For example, if the Pipeline was previously failing but is now successful'
    }
}