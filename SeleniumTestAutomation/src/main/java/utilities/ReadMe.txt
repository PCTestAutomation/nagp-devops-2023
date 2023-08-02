1. BasePage.java file:
	Extent Report, Logger from Log4J and Explicit wait has been defined there.
2. DataProviderClass.java file: 
	All the excel related data-set has been implemented there
3. Common.java
	Navigating the application url from browser
4. PropertyFileReader.java
	Fetching the data from property files from dataset.properties
5. Run test cases from command line:
	5.1. For normal execution: mvn clean test -DmyTestNGFile=normalExecution.xml
	5.2. For Grouping execution: mvn clean test -DmyTestNGFile=groupingExecution.xml
6. Screenshots:
	Archieved Screenshots is a folder where all the old screenshots are available
	Current Screenshots is a folder where new screenshots are available
7. Test Results: (Extent Report)
	Archieved Test Results is a folder where all the old Test Results are available
	Current Test Results is a folder where new Test Results are available
8. MyTestListener.java: 
	Implemeneted the listener for passed and failed scenarios and define the properties in testng.xml file
9. ReadMe.txt file: All the information is listed here regarding the framework structure