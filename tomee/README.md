# Boxfuse TomEE sample application

This sample application demonstrates Boxfuse used together with TomEE to easily deploy Java EE applications to VirtualBox and AWS.

## Building

`mvn package`

## Running on VirtualBox

`boxfuse run target/tomee-1.0.war`

## Running on AWS

`boxfuse run target/tomee-1.0.war -env=prod`
