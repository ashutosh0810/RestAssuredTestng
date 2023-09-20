pipeline
{
    agent any
    triggers {
        cron ('55 * * * *')
    }
    stages{
        stage('checkout'){
            steps {
                git(
                url:'https://github.com/ashutosh0810/testRest.git',
                branch:'master'
                )
            }
        }
        stage('test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('clean code ') {
            steps {
                script  {
                    withSonarQubeEnv('sept_sonar') {
                        bat 'mvn clean test sonar:sonar'

                     }
                }
            }
        }

        stage('deploy') {
            steps {
                rtMavenDeployer (
                    id:'deployer',
                    serverId:'septJfrog',
                    releaseRepo:'test-sept',
                    snapshotRepo:'test-sept'
                )
                rtMavenRun (
                    pom:'pom.xml',
                    goals:'clean install',
                    deployerId:'deployer'
                )
                rtPublishBuildInfo (
                    serverId:'septJfrog'
                )
            }
        }
    }

}