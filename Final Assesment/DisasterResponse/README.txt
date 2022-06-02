****************
* EmergencySim *
****************
Author: Caio Marteli 19598552 (2022) OOSE Assignment 2

Description:
Simulates emergency reports and receives messages from responders 

To run:

#Compile the whole program using gradle.
:~/$ ./gradlew run

OUTPUT:
**************************
* EMERGENCY RESPONSE SIM *
**************************

Please enter a file name:

#enter input file name(must be .txt) the program will automatically append the .txt

#provided files:
#input2 - short simulation
#input - long simulation
#input3 - duplicate events. Will be caught during validation and end simulation.

To test:
#To run PMD and test with JUnit test harness in src/test/java.
:~/$ ./gradlew check

#Find the following in root folder
criteria.txt
UML is named Emergency_AppUML.png
State Chart is named EmergencyApp_State_Chart.png