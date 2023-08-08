# SpringBOOTGradleDockerJenkins
Reference app for proving Jenkins Docker Image build and deploy

This Project illustrates the following:
- Gradle for application (Spring BOOT) uber jar creation;
- Hand-written Dockerfile (could use the Gradle Plug-in to create one);
- Jenkinsfile declaring jobs for calling Gradle for jar build, and Docker for image build;
- application.properties for logging and actuator configuration;
- Setting logback config file name to make app-unique, and thereby supporting overriding is shared classpath;
- Mapping of internal "/apps" directory to Docker host "/apps" (In Jenkinsfile docker run task);

Next:
- Renaming of application.properties to support externalisation in shared classpath;
- Externalisation of classpath;
- Clarify distinction between supplementing and overriding properties.
