# Espresso-TAU (Test Automation University) Course 

This repo contains the course material for the Test Automation University Course `Introduction to Android Automation with Espresso` 

## RUN BDD UI TESTS

### To run all End-to-End tests written with Cucumber and BDD execute : 

./gradlew connectedCheck -Pcucumber -Ptags="@e2e" 

also run ./gradlew connectedCheck -Pcucumber -Ptags="@smoke" to run all smoke BDD tests

### To run individual feature test execute : 

./gradlew connectedCheck -Pcucumber -Pscenario="Successful login"
