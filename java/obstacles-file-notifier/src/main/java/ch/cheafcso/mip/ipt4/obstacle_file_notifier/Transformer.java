package ch.cheafcso.mip.ipt4.obstacle_file_notifier;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.oasis_open.docs.wsn.b_2.NotificationMessageHolderType;
import org.oasis_open.docs.wsn.b_2.NotificationMessageHolderType.Message;
import org.oasis_open.docs.wsn.b_2.Notify;
import org.oasis_open.docs.wsn.b_2.TopicExpressionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Wrap an Obstacle Message with WS-Notification
 * 
 * @version 
 */
@Converter
public class Transformer {

    private static final transient Logger LOG = LoggerFactory.getLogger(Transformer.class);

    @Converter
    public Notify toNotify(_int.nato.standard.mip.mim_obstacle._1.oo._2.UnvalidatedObstacleReport report, Exchange exchange) throws Exception {
        Notify notify = new Notify();
       
        NotificationMessageHolderType nmht = new NotificationMessageHolderType();
        TopicExpressionType tet = new TopicExpressionType();
        // tet.setDialect("?"	);
        tet.getContent().add("Obstacles");
        nmht.setTopic(tet);
        Message message = new Message();
        message.setAny(report);
        nmht.setMessage(message);
        notify.getNotificationMessage().add(nmht);
        LOG.debug("About to return Notify with UnvalidatedObstacleReport");
        return notify;
    }


}
