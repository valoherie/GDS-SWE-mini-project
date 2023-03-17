
# GDS-SWE-mini-project


 This readme will be categorised into 3 sections:
 1.  [Setting Up](#setup)
 2.  [Project & Acceptance Criterias runthrough](#project-and-acceptance-criterias-runthrough)
		- [Acceptance Criteria 1](#acceptance-criteria-1)
		- [Acceptance Criteria 2](#acceptance-criteria-2)
		- [Acceptance Criteria 3](#acceptance-criteria-3)
 3.  [Current limitation & future enhancements](#current-limitation-and-future-enhancements)
   
   
 # Setup
 

## Spring Boot (REST services)

**
 I used spring boot and angular for the assignment. 
 For spring boot,  make sure you have maven setup. I personally am using intellij to run the application. Alternatively, you can use these commands as shown below:

     mvn spring-boot:run

> **IntelliJ:** 
![image](https://user-images.githubusercontent.com/23149027/225750357-8b72538e-3bb7-4ca4-8305-6974955571cd.png)


**

## Angular (UI)

Firstly, run the ***npm install*** command to install the dependencies needed for the project.
Once the dependencies have finished installing, run the **ng serve** command to start the project.

![image](https://user-images.githubusercontent.com/23149027/225757025-50342868-0fa5-4a64-a003-429933da7cba.png)

For the assignment I have used primeng library for the UI components. Documentation can be found here: https://primeng.org/

This is how it will look on **localhost:4200** like when both service and UI are running:
![enter image description here](https://user-images.githubusercontent.com/23149027/225738276-35845909-91e0-43b7-aa38-bcf0b3434972.png)


 #  Project and Acceptance Criterias runthrough
 
## Acceptance Criteria 1:

 - [x] **When the application starts up, it should be pre-loaded with testable data.**
	 
	 As required, the application is pre-loaded with seed data upon running. I am using H2 as the inmemory database. Database will be initialised using the schema.sql      and data.sql scripts.

	DB design:
    
    ![](https://user-images.githubusercontent.com/23149027/225742408-c62d851d-9f34-422c-920d-09abbb085586.png)
	> schema.sql 
    
	> ![enter image description
	> here](https://user-images.githubusercontent.com/23149027/225739198-12916808-982c-4f46-a1fe-676494c0ef08.png)
    
	> data.sql 
    
    ![enter image description here](https://user-images.githubusercontent.com/23149027/225747110-b4592f17-ef67-4bad-b25d-994c94fc7fcb.png)
		
	 When service has started, data will be loaded as shown in H2 database below:
			![enter image description here](https://user-images.githubusercontent.com/23149027/225742862-1a5d844f-61fc-4ec8-bc54-3ebec0503ecb.png )

    **

 - [x] **Expose /users endpoint that returns users with valid salary (0 <= salary <= 4000)**
 
    Endpoint is created in the User Controller.
    
    ![enter image description here](https://user-images.githubusercontent.com/23149027/225749514-24e79315-d762-4da9-8b10-c3f1b8ca38ad.png)

    UI: users endpoint is integrated and presented in a table
    
    ![image](https://user-images.githubusercontent.com/23149027/225757793-99799c30-907a-4f5b-89f3-12bbe972a483.png)

    
    ![image](https://user-images.githubusercontent.com/23149027/225753529-51ae16af-5c63-422d-b77a-1c31f31cee2e.png)

    **

 - [x] **Additional sub-criterias:**
   
   Sub-criterias are implemented in the getUsers() method in UserService.java.

    - 1.1: min and max

        If max is 3000, only 1 result is returned, which is John as he earns below $3000 and Mary's salary is $4000 while David's is $3250.6
        ![image](https://user-images.githubusercontent.com/23149027/225750891-2ec008fb-9c2f-411a-b658-c5facc6d78ea.png)

        If min is 3000, John's salary will not be returned.
        ![image](https://user-images.githubusercontent.com/23149027/225751234-7b231d7b-e9ee-4bb1-af81-a66e78057576.png)

        UI:
        A filter has been added in UI to determin the min and max salary:

        ![image](https://user-images.githubusercontent.com/23149027/225753637-c4fa5236-f229-41c8-ab5c-19f65be4c0d0.png)

        Upon clicking the "apply" button: the data will be filtered:

        ![image](https://user-images.githubusercontent.com/23149027/225754084-db4fc101-d47b-414f-820d-1050407be18b.png)
        ![image](https://user-images.githubusercontent.com/23149027/225754343-80d4e2fa-44ee-4ccc-b9d2-469c7b5c4fb5.png)
    
        **


    - 1.2: order. Also include illegal order parameters.

        If order is not Name or Salary, IllegalArgumentException will be thrown:
	
		```
		if (!sort.equalsIgnoreCase("Name") && !sort.equalsIgnoreCase("Salary")) {
		    throw new IllegalArgException("Sort only by Name or Salary");
		}
		```

        ![image](https://user-images.githubusercontent.com/23149027/225751564-0313c9e1-c6ec-45ab-a108-796099823122.png)

        If order is sorted by name:

        ![image](https://user-images.githubusercontent.com/23149027/225752457-66a986f2-4720-459f-a9dc-12ee092a5175.png)

        If order is sorted by salary:

        ![image](https://user-images.githubusercontent.com/23149027/225752544-10947f8e-3cc2-4a83-a98d-84791e2aa41b.png)

        In UI, when the "Name" or "Salary" column is clicked on, the column will be sorted in ascending order:

        Unsorted:

         ![image](https://user-images.githubusercontent.com/23149027/225754554-062d3521-d046-4d69-a87d-ab76f3bba7a2.png)

         Name, sorted:

         ![image](https://user-images.githubusercontent.com/23149027/225754662-85e0ae5d-d4f7-47bb-870b-72a9e541315f.png)

         Salary, sorted:

         ![image](https://user-images.githubusercontent.com/23149027/225754765-0eb5fd94-9def-453d-aebc-058b30bfa859.png)
       
         **
         
    - 1.3: offset and limit.

        offset:
        first result skipped

        ![image](https://user-images.githubusercontent.com/23149027/225752737-19390386-7b50-4c09-a9bc-6c07c94e5b8f.png)

        limit: 
        only one result returned

        ![image](https://user-images.githubusercontent.com/23149027/225752827-d6e9b83f-bf74-4094-bd83-17cc0b80cda6.png)

        offset AND limit:

        ![image](https://user-images.githubusercontent.com/23149027/225842209-0f358c3d-bc5f-43d3-8379-cad762bdb395.png)
	
	![image](https://user-images.githubusercontent.com/23149027/225842401-72019fed-90bd-4439-af2e-745e0cdf4f49.png)


        **
        
## Acceptance Criteria 2: 

 - [x] **Upload with a properly structured CSV file. You may include any data in the csv file.**

	Code can be found in UploadService.java. Endpoint is created in Upload Controller.

	If file is not in csv format, an error will be thrown:
	
	![image](https://user-images.githubusercontent.com/23149027/225758943-d21ee582-2450-4796-a445-70fc4be81d2c.png)

	 Properly structured CSV file with **Name** and **Salary** column, salary column accepts decimal points
	 
  	 ![image](https://user-images.githubusercontent.com/23149027/225758847-c775513a-37a2-46f7-899c-bdb0d8980f36.png)
	 
	 If upload is successful, it will return success:
	 
	 ![image](https://user-images.githubusercontent.com/23149027/225759252-ccdb48fd-9206-43f0-bef6-8c5c619f29c7.png)
	 
	 Upload can be done from UI:
	 
	 ![image](https://user-images.githubusercontent.com/23149027/225759768-c5b9106a-314c-43b0-b6e8-dbb8765252e6.png)

	 
	 /users will reflect the new results:
	 
	 ![image](https://user-images.githubusercontent.com/23149027/225759530-26fa5d42-bc81-4074-b77b-16676825dad1.png)

	 **

 - [x] **File should include some new data that is not in the database, and some that overwrites the database.**
	 Harry will be a new record in the database while Tom's data will be overwritten
	 
	 ![image](https://user-images.githubusercontent.com/23149027/225759834-93d10af3-9f44-4f91-84e9-a213d18aaab2.png)
	 
	 Uploading the file in UI. If the upload is successful it will prompt a success message and upon closing the dialog the grid will show the new results
	 ![image](https://user-images.githubusercontent.com/23149027/225759964-50ea12ec-8924-496f-87c4-a74d2551607d.png)
	 
	 Tom's salary got updated to 1500 while Harry got added to the database:
	 
	 ![image](https://user-images.githubusercontent.com/23149027/225760206-ae0e4e8c-50be-4bb7-82a7-6435de5f9f08.png)
	 
	 **

 - [x] **File should include rows with negative and 0.00 salary.**

	![image](https://user-images.githubusercontent.com/23149027/225760567-a17f9aaf-f8c7-49c2-9d8b-37eacc9208bb.png)
	
	**

 - [x] **/users should work as expected after the upload and that there are new results returned as well as
	previous results that have been overwritten. Negative rows should be ignored in the input and 0.0
	should be updated and returned.**

	Upon uploading, the negative row (Apple, -200) did not get added to the database wheareas Mango with 0.0 salary got added to the database.

	![image](https://user-images.githubusercontent.com/23149027/225761020-cb03be79-daec-473b-ac6c-f9e5809ecd80.png)


	**	
	
## Acceptance Criteria 3:

 - [x] **Upload with an improperly structured CSV file that should contain at least some good rows. File should be rejected and none of the good rows should have been applied.**
	 Adding additional column in the csv file:
	 
	![image](https://user-images.githubusercontent.com/23149027/225761605-1d3cad2e-909e-494b-adaa-dd8e289baa88.png)
	
	Validation is done and error message will be shown in UI when upload fails:
	![image](https://user-images.githubusercontent.com/23149027/225761748-d12a2b0d-5b07-4fe0-8131-489a7a33f616.png)


# Current limitation and future enhancements

## Enhancements
- File Upload to allow multiple uploads at a time
- Filtering support for all columns 
- Exporting /users as csv file for easy update/creation
- Better exception handling 
- Reduce api call frequency from UI
- Setting file size limit for file upload
- Multithreading for file upload for big data sets 
- UI/UX
- Displaying salary as currency in UI
	
  ** 

- What kind of testing would you perform on the application in addition to unit testing?
	- Code Quality Assurance tool such as JFrog, SonarQube
	- Vulnerability scanning tools such as Nexus
	- Regression testing tools to ensure the application still functions as expected after any code changes, updates, or improvements
	- Performance testing
- How would you scale up the system to handle potentially millions of files?
	- Server Scaling, Auto-scaling if application is on cloud, and load-balancing for on premise application 
	- Concurrency design in code, such as multithreading as it saves system memory and improves application performance
- CSV files can be very large and take a long time to process. This can be a problem using HTTP POST calls.
- How would you update the design to handle HTTP POST requests that can potentially take a long time to
execute?
	- Timeout requests if the requests take too long to finish executing.
	- Asynchronous process to allow request to execute in the background 
