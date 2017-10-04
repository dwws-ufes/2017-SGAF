# 2017-SGAF
Assignment for the 2017 edition of the "Web Development and the Semantic Web" course, by Rodolfo Costa do Prado and Jeferson de Oliveira Batista

DataSource for the WildFly standalone.xml

<datasource jta="true" jndi-name="java:jboss/datasources/sgafDB" pool-name="sgafDB" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://localhost:3306/sgafDB</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>dwws</user-name>
                        <password>1234</password>
                    </security>
</datasource>
