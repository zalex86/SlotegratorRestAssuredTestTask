# SlotegratorRestAssuredTestTask
## Test task for Slotegrator with 
## API RestAssured-TestNG-Allure-Maven framework and tests

This project is similar to the Qvalon backend tests project except it has just 8 tests instead of about 1500 tests. 

Thus, this project is much smaller and a bit simpler.

It has variables of configuration making it easier to be integrated into CI/CD systems with tests on different stands.

To run local tests it needs to add the following text to
**Run/Debug Configurations -> Edit configuration templates... -> TestNG -> VM options**
```
-Dhost="https://testslotegrator.com/"
-DtesterLogin="a.zaitsev.qa@gmail.com"
-DtesterPassword="pAWBNAU4EF6l"
```
It has *Singleton, Decorator, Observer, Pojo objects, Builder* patterns for making a Java project.

I've implemented Lombok, AssertJ libraries for an easier way of asserting Jsons. 

I've added the AllureReport plugin thus Allure reports can be checked in 
**Maven>testslotegrator>plugins>allure>allure:serve** after tests were finished.

*Also, the current stand doesn't work as expected at this point. I did my best to make **working** tests as is. 
*I better have followed the exact instructions and would have done validations as written in documentaions. 
But this would block all the tests because basic authorization doesn't work as documented and will affect all the tests.*
* Update: Currently, access to these URIs is forbidden.
