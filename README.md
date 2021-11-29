# Introduction
This is a sample advance to practice Web Test Automation and build a test automation framework in java. 

# Test Subject
Visit to website http://travel.agileway.net/. We login to home page and book a flight one trip or return trip. Then we will fill passenger's information and payment it.  

# Libraries and Frameworks
This sample using the following libraries and frameworks:
- Java JDK-1.8 as the programming language
- Selenium WebDriver as web browser automation
- Maven as build tool and package management
- Extent Report as the testing report strategy
- TestNG as a testing framework to support the test creation, execution and reporting

# Features
- Parallel testing
- Thread safe driver (using thread local) support for parallel testing.
- Log test actions to extent report.
- DataDriven Test support for execute in parallel.
- Retry a test case fail/skip.
- Cross Browser Testing through different browser like Firefox, Chrome, Edge, Safari.
- Cross Platform Testing on any of the popular operating systems like Windows, macOS, iOS and Android.
- Create Bug / Defect automatically in JIRA

# Parallel testing
- The parallel test execution is based on the parallel tests feature on TestNG. This is used by `testng.xml` test suite file which has the `parallel="tests"` attribute and value, whereas test item inside the test suite will execute in parallel.
```
<suite name="Agile Travel Project Test Suite" thread-count="4" parallel="tests" >
```
- Or can use for each test should be defined by a parameter, like:
```
<test name="Test - Edge" thread-count="2" parallel="methods">
```
# Thread safe driver
- To support for parallel testing, we must share webdriver in more than one thread. Code apply singleton and factory design pattern to create thread safe driver.
- File `DriverFactory.java` have 2 method is get and set driver. The same for file `ExtentFactory.java` in folder `src/main/java/factory`.

# Data-Driven Test
- Using `@DataProvider` annotation that helps us to write data-driven tests. This is an important feature provided by TestNG framework.
- Support for parallel test with parameter is `parallel=true`.
- Use `AgileTravel_TestData.xlsx` file in folder `src/test/resources/testData` as your dataproviders. To read/write excel file, you need import **APACHE POI** library to Pom.xml file.

# Retry a test case
- Sometimes test failed/skip not a bug for some reason such as out of internet, or environment error etc... We must rerun test it.
- We will use the `IRetryAnalyzer` interface (provided by TestNG framework), which allows you to rerun a failed/skip test method a set amount of times before declaring it as failed/skip.

![Cross Browser Testing](https://github.com/DTXin/Web_Test_Automation_Framework_Advance/blob/master/Reports/Image/Retry%20Test.png)

# Cross Browser Testing
- File `AbstractTest.java` in folder `src/main/java/` will execute code. 
- To execute code using maven. You open the windows command prompt and parameterize the xml file name `testng.xml`.   
```
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
```
- Result show.

![Cross Browser Testing](https://github.com/DTXin/Web_Test_Automation_Framework_Advance/blob/master/Reports/Image/Cross-Browser%20Test_Image.png)

![Cross Browser Testing](https://github.com/DTXin/Web_Test_Automation_Framework_Advance/blob/master/Reports/Image/Cross-Browser%20Test_Image%202.png)

# Cross Platform Testing with Browserstack
- File `AbstractTest_WithBrowserStack.java` in folder `src/main/java/` wil run code remote to BrowserStack. Testing on operating systems like Windows and macOS.
- To execute code using maven. You open the windows command prompt and parameterize the xml file name `testng_browserstack.xml`. 
```
mvn clean test -Dsurefire.suiteXmlFiles=testng_browserstack.xml
```
- Result show.

![Cross Platform Testing with Browserstack](https://github.com/DTXin/Web_Test_Automation_Framework_Advance/blob/master/Reports/Image/Browserstack_build.png)

# JIRA - Defect tracking tools
- File `JiraManager.java` in folder `src/main/java/ultilities` will connect to JIRA by using Jira Rest API.
- When have a test case return failure. We will create a issue about test case's bug and log defects in JIRA.

![Creatae A Issue](https://github.com/DTXin/Web_Test_Automation_Framework_Advance/blob/master/Reports/Image/Intergrate%20with%20Jira_Image1.png)

- Add attachment include: information of a issue and ccreenshot of failed Test Case to JIRA Issue.

![Creatae A Issue](https://github.com/DTXin/Web_Test_Automation_Framework_Advance/blob/master/Reports/Image/Intergrate%20with%20Jira_Image2.png)
