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

        stage("build") {
            when {
                expression {
                    env.BRANCH_NAME == "main"
                    params.executeTests
                }
            }
            steps {
                echo "Building the application"
                echo "Building version is: ${VERSION}"
                // For one stage credential variable
                withCredentials([
                    usernamePassword(credentials: "admin-user2", usernameVariable: USER, passwordVariable: PWD)
                ]) {
                        sh "some script ${USER} ${PWD}"

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
                echo "Testing the application"
                echo "With credentials from ${SERVER_CREDENTIALS}"
                echo "Deploying to ${params.Cloud} at region: ${params.REGION}"
            }
        }

        stage("deploy") {
            when {
                expression {
                    env.BRANCH_NAME == "prod"
                }
            }
            steps {
                echo "Deploying the application"
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