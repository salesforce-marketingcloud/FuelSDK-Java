pipeline {
    agent {
        docker {
            label 'docker'
            image 'maven:3.5.4-jdk-8-alpine'
            registryCredentialsId 'NEXUS_DOCKER_REGISTRY'
            args '-v /var/lib/jenkins/sbt/cache:/home/jenkins'
        }
    }

    parameters {
        booleanParam(
                name: 'PUBLISH',
                defaultValue: env.BRANCH_NAME == 'master',
                description: 'Whether or not to publish the artifact to the artifact repository'
        )
    }

    options {
        timestamps()
    }

    environment {
        NEXUS_CREDENTIALS = credentials('2e15e89e-542b-48f1-a187-af0eed217297')
        PUBLISH_USERNAME = "${env.NEXUS_CREDENTIALS_USR}"
        PUBLISH_PASSWORD = "${env.NEXUS_CREDENTIALS_PSW}"
        GIT_BRANCH = "${env.BRANCH_NAME}"
    }

    stages {
        stage('Verify') {
            steps {
                ansiColor('xterm') {
                    sh 'mvn -DskipTests verify'
                }
            }
        }

        stage('Publish') {
            when {
                expression { params.PUBLISH }
            }
            steps {
                ansiColor('xterm') {
                    sh 'mvn -DskipTests deploy -s .m2/settings.xml'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
