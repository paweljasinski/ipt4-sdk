WS-Notification Obstacle client  
===============================

This project shows how to construct Obstacle client (Notifier and Subscriber).

This project is based on cxf WS-Notification example. 
This project has a knowledge of the Obstacle specific schema, once schema has been changed, project must be updated.
WS-Notification broker is included as part of standard cxf WS-Notification stack. It is necessary to run a broker before starting client.


In order to compile:

  mvn install

In order to start broker:

  mvn -Pwsn-server

  
In oder to start subscriber notifier

  mvn -Pclient
  
On startup, the client will sunscribe to /Obstacles topic.  It will then use the NotificationBroker
API's to send one Obstacle message.


In order to stop broker, press Ctrl-C in the console where it runs.

In order to stop client, press Enter in the console where it runs.
Please, note: stopping subscriber with Ctrl-C without restarting broker has a side effect of delivering multiple messages to the client.


