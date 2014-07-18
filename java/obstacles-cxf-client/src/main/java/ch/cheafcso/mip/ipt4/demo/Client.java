package ch.cheafcso.mip.ipt4.demo;

import javax.xml.bind.JAXBElement;

import org.w3c.dom.Element;

import org.apache.cxf.wsn.client.Consumer;
import org.apache.cxf.wsn.client.NotificationBroker;
import org.apache.cxf.wsn.client.Subscription;
import org.oasis_open.docs.wsn.b_2.NotificationMessageHolderType;

import _int.nato.standard.mip.mim_obstacle._1.oo._2.UnvalidatedObstacleReport;
import _int.nato.standard.mip.mim_obstacle._1.oo._2.ValidatedObstacleReport;

/**
 * 
 */
public final class Client {
    private Client() {
        //not constructed
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String wsnPort = "9000";
        if (args.length > 0) {
            wsnPort = args[0];
        }
        
        // Start a consumer that will listen for notification messages
        // We'll just print the text content out for now.
        Consumer consumer = new Consumer(new Consumer.Callback() {
            public void notify(NotificationMessageHolderType message) {
                System.out.println("---------- received message -------------");
                Object o = message.getMessage().getAny();
                System.out.println("type of any: " + o.getClass());
                if (o instanceof Element) {
                    System.out.println("element content: "  +((Element)o).getTextContent());
                }
                if (o instanceof JAXBElement) {
                    JAXBElement<?> elem = (JAXBElement<?>)o;
                    System.out.println("name:" + elem.getName());
                    System.out.println("value:" + elem.getValue());
                    System.out.println("declaredType:" + elem.getDeclaredType());
                    if ( elem.getValue() instanceof ValidatedObstacleReport ) {
                        ValidatedObstacleReport or = (ValidatedObstacleReport)elem.getValue();
                        System.out.println("obstacle report identifier: " + or.getIdentifier());
                        System.out.println("obstacle identifier: " + or.getObstacle().getIdentifier());
                    }
                }
                if (o instanceof ValidatedObstacleReport) {
                    ValidatedObstacleReport or = (ValidatedObstacleReport)o;
                    System.out.println("obstacle report identifier: " + or.getIdentifier());
                    System.out.println("obstacle identifier: " + or.getObstacleReport().get(0).getIdentifier());
                } 
                if (o instanceof UnvalidatedObstacleReport) {
                    UnvalidatedObstacleReport or = (UnvalidatedObstacleReport)o;
                    System.out.println("obstacle report identifier: " + or.getIdentifier());
                    System.out.println("obstacle identifier: " + or.getObstacle().getIdentifier());
                }
                System.out.println("---------- processing done ---------------");
            }
        }, "http://localhost:9001/MyConsumer", _int.nato.standard.mip.mim_obstacle._1.oo._2.ObjectFactory.class);
        
        
        // Create a subscription for a Topic on the broker
        NotificationBroker notificationBroker 
            = new NotificationBroker("http://localhost:" + wsnPort + "/wsn/NotificationBroker", _int.nato.standard.mip.mim_obstacle._1.oo._2.ObjectFactory.class);
        Subscription subscription = notificationBroker.subscribe(consumer, "Obstacles");

        
        
        // send obstacle report
        ValidatedObstacleReport obstacleReport = new ValidatedObstacleReport();
        ValidatedObstacleReport obstacle = new ValidatedObstacleReport();
        obstacle.setIdentifier( "very important obstacle" );
        obstacleReport.getObstacleReport().add(obstacle);
        obstacleReport.setIdentifier( "report about very important obstacle" );
        
        
        notificationBroker.notify("Obstacles", obstacleReport);
        
        // Just sleep for a bit to make sure the notification gets delivered
        // Thread.sleep(50000);
        
        // wait for enter
        System.out.print("press ENTER to terminate ...");
        System.in.read();
        
        // Cleanup and exit
        subscription.unsubscribe();
        consumer.stop();
        System.exit(0);
    }

}
