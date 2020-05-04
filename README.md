
# Espresso-TAU (Test Automation University) Course 

## Chapter8 - Android BDD with Cucumber and Espresso

## RUN BDD UI TESTS

### To run all End-to-End tests written with Cucumber and BDD execute : 

./gradlew connectedCheck -Pcucumber -Ptags="@e2e" 

also run ./gradlew connectedCheck -Pcucumber -Ptags="@smoke" to run all smoke BDD tests

### To run individual feature test execute : 

./gradlew connectedCheck -Pcucumber -Pscenario="Successful login"

### Resources
- Cucumber Android
https://github.com/cucumber/cucumber-android

- Gradle CLI
https://docs.gradle.org/current/userguide/command_line_interface.html

- Gradle Wrapper
https://docs.gradle.org/current/userguide/gradle_wrapper.html


### References Projects
- https://github.com/stoichoandreev/bdd



