Pre-requisites:
1. Install MySql
2. Install Apache Tomcat 7 or higher.
3. If you are using Eclipse, you can directly import these projects as "existing project" in Eclipse.

Content:
There are two projects, 
1. Legend : The Shopping website
2. SimpleBanking : The Bank website.
To get the working of the project correctly, both have to be deployed.
 

Configure Database:
1. There is a "jdbc.properites" file in Legend project where you can change the username, password of MySql
2. There is a "DBMS" folder which contains the dump sql file "eshopping.sql".
3. Import this dump into your MySql.

4. Similarly change the details in "databasedetails.properties" file of SimpleBanking project.
5. There is a "DBMS" folder which contains the dump file "bankeshopping.sql".
6. Import this dump into ypur MySql.

Note1: There is a Mailing feature built in the application. The email to any sender is sent from an ID: chanakya.MSIT@gmail.com,
This can be changed in the source code, Legend > com.legend.util.Mailer.java.  

Note2: Admin Credetials are:- ID: admin@gmail.com, Password: admin.
This can be chaged in the source code, Legend > com.legend.control.Login.java.

Deploy both the projects and Enjoy the best shooping Experience. :)
For best User Interface Experience, Run with your Internet Connected. 
 