pipeline {
    agent any
    environment {
        APP_NAME="af-batches"
        DEBUG_BLD="git log --oneline -1 | grep -c '[debug]'"
    }

    stages {
        stage('Quality Check') {
            parallel {
                stage('Unit Tests') {
                  steps {
                    script {
                       print env.getEnvironment()
                       print "${env.DEBUG_BLD}"
                       print "$DEBUG_BLD"
                    }
                    sh 'echo "run ng test"'
                  }
                }
                stage('Code Scan') {
                  steps {
                    sh 'echo "run ng lint"'
                  }
                }
            }
        }

        stage('Artifact Build') {
            when {
                anyOf {
                    branch 'master'
                    branch 'development'
                    environment name: 'DEBUG_BLD', value: '1'
                }
            }
            steps {
                sh "echo run mvn package -DskipTests"
                sh "mvn install -DskipTests"
            }
        }

        stage('Container Build') {
            when {
                anyOf{
                    branch 'master'
                    branch 'development'
                }
            }
            steps {
                sh '''DK_U=$(cat /opt/dk_auth | cut -d\':\' -f1)
echo "run docker build"
./mvnw dockerfile:build'''
            }
        }

        stage('Docker Push') {
            // this step could be integrated with 'Container Build'
            when {
                anyOf{
                    branch 'master'
                    branch 'development'
                }
            }
            steps {
                sh '''DK_U=$(cat /opt/dk_auth | cut -d\':\' -f1)
echo "push"
./mvnw dockerfile:push
echo "remove local image"
docker rmi $DK_U/$APP_NAME:latest'''
            }
        }

        stage('CF Push') {
            when {
                anyOf {
                    branch 'master'
                    branch 'development'
                }
            }
            steps {
                script {
                    if(env.BRANCH_NAME == 'master') {
                        env.SPACE = "production"
                    } else {
                        env.space = "development"
                    }
                    env.CF_DOCKER_PASSWORD=readFile("/run/secrets/CF_DOCKER_PASSWORD").trim()
                }

                sh 'cf target -s $SPACE'
                sh 'cf push'
            }
        }

        stage('Clean') {
            steps {
                cleanWs(cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenSuccess: true, cleanWhenUnstable: true, deleteDirs: true)
            }
        }
    }
}