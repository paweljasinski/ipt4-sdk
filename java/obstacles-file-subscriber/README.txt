Obstacles File Subscriber
=========================

This is an obstacles subscriber. Each message received on /Obstacles topic is written into the msg-in folder.

This subscriber bypasses WS-Notification mechanism and is connected directly with JMS broker acting as WS-Notification broker.
Broker uri: tcp://localhost:6000

  
In order to prepare subscriber:
  mvn compile
  
In order to run a subscriber:
  mvn exec:java -Psubscriber

In order to stop, press:
  Enter

This subscriber does not depend on mim obstacle schema and can be reused as-is for any messages conforming to WS-Notification.

This subscriber is based on camel-example-jms-file project from camel distribution.