# 2017-SGAF
Assignment for the 2017 edition of the "Web Development and the Semantic Web" course, by Rodolfo Costa do Prado and Jeferson de Oliveira Batista  
 
Project Requirements:  
WildFly 10  
mysql Server  
java EE 7  
JDBC driver  
Eclipse Neon  


Steps for importing and using the project(for a simple boot up, all can be changed at will, if tou have the knowlodge):

1 - Add to WildFly a datasource named: java:jboss/datasources/sgafDB  
2 - Connect the datasource to a database with named: sgafDB  
3 - To connect to the Database use a user: dwws with passowrd: 1234  
4 - Create the user in the database and give all rights to the sgafDB schema  
5 - Import Project as Maven Project on Eclipse  
6 - Run a Maven Update Project on Eclipse to download the jars  
7 - Deploy the Project to the wildfly server and Run it.  
8 - On the running app click and "Install System" and fill in the admin data.  
9 - Done :)  
  
Url para RDF dos Filmes: http://localhost:8080/SGAF-1.2.4/data/movies/  
Nome completo de Ator para usar nos testes: Harry Dean Stanton
