The server side code is here for mini-dropbox.

To start enter following commands:

Set the local file location in application.properties file for property: file.local.dir.
Go in directory:
cd dropbox.

build the jar: gradle clean build.
Start server: nohup java -jar build/lins/dropbox-0.0.1-SNAPSHOT.jar &
