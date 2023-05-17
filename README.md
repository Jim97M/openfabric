                              SETUP.

1. Replace the datasource and liquibase values in application-local.yaml file.

2. Build a docker image with name worker-image.
   
   -command: docker build -t worker-image .

3. Run your Image.
 
   --command: docker run -p 8080:8080 woker-image.


5. Build your spring boot application.
 
      ./gradlew build.

6. Run your springboot application.


     ./gradlew run.


      DANG!!! DONE.