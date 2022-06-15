// My Jenkins environment variables are at localhost:8080/env-vars.html

pipeline {
    agent any
    environment {
        VERSION = "1.3.0"  // This can be extracted from my code + commit code.
        // Extracting credentials from Jenkins
        SERVER_CREDENTIALS = credentials("admin-user2")
    }

    // Parameters
    parameters {
        string(name: "Cloud", defaultValue: "AWS", description: "Cloud Ecosystem.")
        choice(name: "REGION", choices: ["us-east-1", "us-west2", "us-east-2"], description: "Region for deployment.")
        choice(name: "Environment", choices: ["dev", "qa", "prod"], description: "Environment to deploy to.")
        booleanParam(name: "executeTests", defaultValue: true, description: "")
    }

    stages {
        stage("init") {
            // Loading a script.groovy file
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }

        stage("build") {
            when {
                expression {
                    env.BRANCH_NAME == "main"
                }
            }
            steps {
                script {
                    gv.buildApp()
                }
            }
        }

        stage("test") {
            // Conditional
            when {
                expression {
                    env.BRANCH_NAME == "dev" || "main"
                }
            }
            steps {
                // For groovy syntax
                script {
                    gv.testApp()
                }
            }
        }

        stage("deploy") {
            when {
                expression {
                    env.BRANCH_NAME == "prod"
                }
            }
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }

    post {
        always {
            // Executed always
            echo "This will always show no matter what."
        }
        success {
            // Upon success execute whatever
            echo "This will show if successful"
        }
        failure {
            // Upon failure
            echo "This will show upon failure."
        }
    }
}