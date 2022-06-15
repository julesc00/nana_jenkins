def buildApp() {
    echo "Building the application"
    echo "Building version is: ${VERSION}"
}

def testApp() {
    echo "Testing the application"
    echo "Deploying to testing environment to ${params.Cloud} Cloud, at region: ${params.REGION}"
}

def deployApp() {
    echo "Deploying the application"
}

return this