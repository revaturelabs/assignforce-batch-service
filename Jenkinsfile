pipeline {
    agent any
    environment {
        APP_NAME="af-batches"
        DEBUG_BLD=sh(script: "git log --oneline -1 | grep -co '[debug]'", returnStatus: true)
        DK_U=sh("$(cat /opt/dk_auth | cut -d':' -f1)")
    }

    stages {
        stage('Quality Check') {
            parallel {
                stage('Unit Tests') {
                  steps {
                    sh "echo $DK_U"
                    sh 'echo "run ng test"'
                    script {
                        if(DEBUG_BLD == '0') {
                            sh 'echo running debug build'
                        }
                    }
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
docker rmi $DK_U/$APP_NAME:$DK_TAG'''
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
                sh 'cf push -o $IMG --docker-username $DK_U'
            }
        }

        stage('Clean') {
            steps {
                cleanWs(cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenSuccess: true, cleanWhenUnstable: true, deleteDirs: true)
            }
        }
    }
}