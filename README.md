# devchallenge.it---qa---1
Solution for dev challenge

Version 1.2

GENERAL USAGE NOTES

-support Java 1.8 version 
-support JUnit 4.12 version 
-support Unirest 1.4.9 version 
-the test was written on IntelliJ IDEA 2017.2.1. So I recommend run tests in this IDE All additonal libraries are set at build.gradle

RUNNING THE TEST

1. Recommends: For running test use IDE(IntelliJ IDEA 2017.2.1). Import all libraries from build.gradle. 
All tests are in the src/test/java/ directory in com.dev.challenge package. You can run them by clicking Run in the context menu.

2. CRUD for pet 

Run test CreateReadUpdateDeletePet in com.dev.challenge package in src/test/java directory. 
I write created method with specific ID, because the server doesn't return correct pet ID. 
You can change petID for other values or leave empty if server will return correct response.

3. Bonus task 

Run test CreateOrderInStore in com.dev.challenge package in src/test/java directory. In this test a new pet is created and it's 
added to new order. Then the created order is checked. I write method for specific ID, because the server doesn't return correct order ID. 
For creating order you can use specific order Id or default value.

4. Document with bugs

You can find bugs in the Google docs.

https://docs.google.com/spreadsheets/d/1gfi6GCcdrJezI50Z8UcSOdmjxPX1q2r-1kLh1hMrBGw/edit#gid=0
