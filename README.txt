Installation:
1. java jdk - tested on linux and windows with 1.7.0_21
make sure to set JAVA_HOME

2. maven - tested on linux with 2.2.1 and on windows with 3.0.5

2.1 ubuntu
sudo apt-get install maven2

2.2 windows
download latest version from http://maven.apache.org/download.cgi 
unpack
add bin subfolder to path
 

4. Build and run

Inside java there are 3 maven projects.
obstacles-cxf-client
obstacles-file-notifier
obstacles-file-subscriber

Please, reffer to README.txt for build and run instructions.
Please note:
In order to run any of the examples, you have to start broker. Broker is
locatedd in obstacles-cxf-client project. 

4.1 JMX Stats for topics/subscriptions
start jconsole

New Connection
Local Process
One with -Pwsn-server
Connect
Open MBeans tab

In tree view, select org.apache.cxf.service.wsn => WSNotificationBroker =>
Attributes => Subscriptions
Start client, hit Refresh (couple of times), observe client comming and going

In tree view, select org.apache.activemq => localhost => Topic => Obstacles =>
Attributes 
Start client, hit Refresh (couple of times), observe stats changing


5. eclipse and importing projects into eclipse
5.1 install eclipse JEE version (first on the download page) 
tested with Juno SR2 on linux and windows

5.2 install m2e plugin
Under Help->Install New Software add: http://download.eclipse.org/technology/m2e/releases 
Follow standard installation, restart
5.3 Import project
Under File->Import select Maven->Existing Maven Project 
Provide Root Directory pointing to obstacles-cxf-client folder
In Project Explorer, project context menu (right-click), Run-As:
  - maven clean
  - maven generate-sources

repeat for obstacles-file-subscriber and obstacle-file-notifier


Subversion reference:
repo: https://mantis.neo.defence.ruag.com:4443/svn/mip/trunk/ipt4-sdk 
username: guest
password: browseRepo!
