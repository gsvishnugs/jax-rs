account-wa: Accounting RESTful API services using JAX-RS
==========================================================================
Author: Vishnu G S  
Technologies: CDI, JAX-RS, spring-security, hibernate5, infispan  
Summary: The `account-wa` exposes basic REST APIs that in turn can be utilized be variety of cient applications, bundled and deployed as a WAR.    
Target Product: WildFly 10.1.0.Final
Source: <https://github.com/gsvishnugs/jax-rs>  

What is it?
-----------

The `account-wa` use *CDI*, *JAX-RS*, *hibernate5* and *spring-security* to wire up a basic accounting application. The scenarios in this application in turn used for various machine learning application.


System requirements
-------------------

The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform 7 or later. 

All you need to build this project is Java 8.0 (Java SDK 1.8) or later and Maven 3.1.1 or later. See [Configure Maven for WildFly 10](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN_JBOSS_EAP7.md#configure-maven-to-build-and-deploy-the-quickstarts) to make sure you are configured correctly.

Use OpenShift incase if you plan to go live!


Use of WILDFLY_HOME
-------------------

In the following instructions, replace `WILDFLY_HOME` with the actual path to your WildFly installation. The installation path is described in detail here: [Use of WILDFLY_HOME and JBOSS_HOME Variables](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_OF_EAP7_HOME.md#use-of-eap_home-and-jboss_home-variables).


Start the WildFly Server
------------------------

1. Open a command prompt and navigate to the root of the WildFly directory.
2. The following shows the command line to start the server:

        For Linux:   WILDFLY_HOME/bin/standalone.sh
        For Windows: WILDFLY_HOME\bin\standalone.bat

 
Build and Deploy the Accounting REST services
----------------------------------------------

1. Make sure you have started the WildFly server as described above.
2. Open a command prompt and navigate to the root directory.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `target/account-wa.war` to the running instance of the server.


Access the application 
---------------------

The application is deployed to <http://localhost:8080/account-wa>.


Undeploy the Archive
--------------------

1. Make sure you have started the WildFly server as described above.
2. Open a command prompt and navigate to the root directory.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy
