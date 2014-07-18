Obstacles File Notifier
=======================

This is an obstacles notifier. Each obstacles message appearing in msg-in folder is send into /Obstacles topic.


In order to prepare notifier:
  mvn compile

In order to run notifier:
  mvn camel:run

In order to send a message copy a file into msg-out folder
  cp src/data/instance1.xml msg-out

In order to stop press:
  Ctrl-C

Notifier depends on Obstacles schema (src/main/resources/schemas). In case of schema changes, it has to be updated.
This project is based on camel-example-etl.