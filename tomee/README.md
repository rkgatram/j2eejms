# TomEE sample application

This sample application demonstrates TomEE - Java EE applications with JMS.

## Building

`mvn package`

1. Go to RabbitMQ Web Console (http://localhost:15672/)
2. Create a Queue with the name "movie"
3. Navigate to exchange tab in RabbitMQ and click on "amq.direct"

   - In 'Add binding from this exchange' section:
     - give "To queue" value as "movie" (i.e., the queue that was created in step 2)
     - Enter "Routing key:" value as "movierouting"
    And then click on "Bind" button

4. Navigate to apache-tomee-plume-7.0.6/conf/context.xml. And add the below lines:

   <Resource id="ResourceRef_125180" name="jms/movieManagemenCF" auth="Container" type="javax.jms.ConnectionFactory" 
         factory="com.rabbitmq.jms.admin.RMQObjectFactory" 
        username="guest" 
        password="guest" 
     virtualHost="/" 
            host="localhost"/>

   <Resource id="ResourceRef_125181" name="jms/movieManagementQueue" auth="Container" type="javax.jms.Queue"
           factory="com.rabbitmq.jms.admin.RMQObjectFactory"
   destinationName="myQueue"
              amqp="true"
     amqpQueueName="movie"
     amqpExchangeName="amq.direct"
     amqpRoutingKey="movierouting"/>

5. Navigate to apache-tomee-plume-7.0.6/conf/tomee.xml. And add the below lines:

  <Resource id="movieDatabase" type="javax.sql.DataSource">
         JdbcDriver=com.mysql.jdbc.Driver
         JdbcUrl=jdbc:mysql://localhost:3306/moviefun
         UserName=abc
         Password=xxx
         JtaManaged=true
  </Resource>

6.  Add the below jars in apache-tomee-plume-7.0.6/lib folder
    amqp-client-3.6.2.jar
    geronimo-jms_1.1_spec-1.1.1.jar
    rabbitmq-jms-1.4.7.jar
    mysql-connector-java-8.0.18.jar

7.



