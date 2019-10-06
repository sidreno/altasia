## "Evaluacion: JAVA"

### rest-project 
This folder contains a Spring Boot project to create users into a HSQLDB and it has to be build-run using GRADLE.
HSQLDB is recreated every time the application is restarted, and there is only one record added on startup.

#### Project Folder Structure
```console
<rest-project>
+---.gradle
¦   +---4.10.2
¦   ¦   +---fileChanges
¦   ¦   +---fileContent
¦   ¦   +---fileHashes
¦   ¦   +---javaCompile
¦   ¦   +---taskHistory
¦   ¦   +---vcsMetadata-1
¦   +---buildOutputCleanup
¦   +---vcs-1
+---src
    +---main
    ¦   +---java
    ¦   ¦   +---global
    ¦   ¦       +---rest
    ¦   ¦           +---project
    ¦   ¦               +---config
    ¦   ¦               +---controller
    ¦   ¦               +---model
    ¦   ¦               ¦   +---user
    ¦   ¦               +---service
    ¦   ¦               ¦   +---impl
    ¦   ¦               +---starter
    ¦   ¦               +---user
    ¦   ¦               ¦   +---dao
    ¦   ¦               +---util
    ¦   ¦                   +---constants
    ¦   +---resources
    ¦       +---static
    ¦       +---templates
    +---test
        +---java
            +---global
                +---rest
                    +---project
                        +---starter
                        +---util
```

#### HSQLDB info
Only one table is in use "USER_INFO".

### rest-project-tests.postman_collection.json

This postman collection will allows to validate 4 of 5 possible scenarios:
1. "create-user-email-empty", Response is error because email in request cannot be empty
2. "create-user-email-invalid", Response is error because email format is not as expected
3. "create-user-email-already-registered", Response is error because email is already taken by an user stored in database
4. "create-user-password-invalid", Response is error because password does not complies with 1Capital+1lowercase+2numbers format

The 5th scenario is currently mocked (create-user[current-response-mocked]), persistence into HSQLDB has to be implemented as response is only showing the information stored on initialization.

