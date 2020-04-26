# Espresso-TAU (Test Automation University) Course 

This repo contains the course material for the Test Automation University Course `Introduction to Android Automation with Espresso` 

# Basic Idling Resource sample for Espresso

The centerpiece of Espresso is its ability to seamlessly synchronize all test operations with the application under test. By default, Espresso waits for UI events in the current message queue to be processed and default AsyncTasks* to complete before it moves on to the next test operation. This should address the majority of application/test synchronization in your application.

However, there are instances where applications perform background operations (such as communicating with web services) via non-standard means; for example: direct creation and management of threads.




