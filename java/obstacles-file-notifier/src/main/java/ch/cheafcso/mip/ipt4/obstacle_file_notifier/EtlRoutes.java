package ch.cheafcso.mip.ipt4.obstacle_file_notifier;

import org.apache.camel.spring.SpringRouteBuilder;
import org.oasis_open.docs.wsn.b_2.Notify;
	
import _int.nato.standard.mip.mim_obstacle._1.oo._2.UnvalidatedObstacleReport;


/**
 * @version 
 */
public class EtlRoutes extends SpringRouteBuilder {
    public void configure() throws Exception {

        from("file:msg-out?delete=true")
            .convertBodyTo(UnvalidatedObstacleReport.class)
            .convertBodyTo(Notify.class)
            .to("cxf:bean:notify");

//            .to("file:target/customers?fileName=msg-${bean:guidgenerator.Generate}.xml");
           
    }
}
