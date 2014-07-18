package ch.cheafcso.mip.ipt4.obstacle_file_subscriber;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;


public final class Main {

    private Main() {        
    }
    
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:6000");
        context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("test-jms:topic:Obstacles").
                to("xslt:ch/cheafcso/mip/ipt4/obstacles_file_subscriber/strip-wsn.xsl").
                to("xslt:ch/cheafcso/mip/ipt4/obstacles_file_subscriber/remove-unused-namespaces.xsl").
                to("file://msg-in?fileName=Obstacles-${date:now:yyyy-MM-dd-HH-mm-ss.SSS}");
            }
        });
        context.start();
        System.out.print("press ENTER to terminate ...");
        System.in.read();
        context.stop();
    }
}