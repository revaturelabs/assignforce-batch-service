pipeline {
    agent any
    environment {
        APP_NAME="af-batches"
    }

    stages {
        stage('Quality Check') {
            parallel {
                stage('Unit Tests') {
                  steps {
                    sh 'echo "run mvn test"'
                    sh "mvn test"
                    script {
                        result = sh(script: "git log -1 | grep -co '[debug]'", returnStatus: true)
                        sh "echo 'result is '${result}"
                        if(result == 0 ) {
                            sh 'echo running debug build'
                            env.DEBUG_BLD=result
                        }
                    }
                  }
                }
                stage('Code Scan') {
                  steps {
                    sh 'echo "run quality gate"'
                  }
                }
            }
        }

        stage('Artifact Build') {
            when {
                anyOf {
                    branch 'master'
                    branch 'development'
                    environment name: 'DEBUG_BLD', value: '0'
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
                    environment name: 'DEBUG_BLD', value: '0'
                }
            }
            steps {
                script {
                    env.DK_U=readFile("/opt/dk_auth").trim().split(':')[0]
                    env.DK_TAG_GOAL='tag-latest'
                    env.DK_TAG='latest'

                    if(env.BRANCH_NAME == 'development' || env.DEBUG_BLD == '0') {
                        env.DK_TAG_GOAL='tag-dev'
                        env.DK_TAG='dev-latest'
                    }
                }
                sh '''echo "run docker build"
mvn dockerfile:tag@$DK_TAG_GOAL'''
            }
        }

        stage('Docker Push') {
            // this step could be integrated with 'Container Build'
            when {
                anyOf {
                    branch 'master'
                    branch 'development'
                    environment name: 'DEBUG_BLD', value: '0'
                }
            }
            steps {
                sh '''echo "push"
mvn dockerfile:push
echo "remove local image"
docker image rm $DK_U/$APP_NAME:$DK_TAG'''
            }
        }

        stage('CF Push') {
            when {
                anyOf {
                    branch 'master'
                    branch 'development'
                    environment name: 'DEBUG_BLD', value: '0'
                }
            }
            steps {
                script {
                    if(env.BRANCH_NAME == 'master') {
                        env.SPACE = "production"
                        env.IMG="${env.DK_U}/${env.APP_NAME}:latest"
                    } else if(env.BRANCH_NAME == 'development' || DEBUG_BLD == '0') {
                        env.SPACE = "development"
                        env.IMG="${env.DK_U}/${env.APP_NAME}:dev-latest"
                    }
                    env.CF_DOCKER_PASSWORD=readFile("/run/secrets/CF_DOCKER_PASSWORD").trim()
                }

                sh 'cf target -s $SPACE'
                sh '''cf push -o $IMG --docker-username $DK_U'''
            }
        }

        stage('Clean') {
            steps {
                cleanWs(cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenSuccess: true, cleanWhenUnstable: true, deleteDirs: true)
            }
        }
    }
}