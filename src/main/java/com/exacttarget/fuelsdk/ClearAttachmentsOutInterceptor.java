package com.exacttarget.fuelsdk;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.cxf.message.Message;
import org.apache.cxf.attachment.AttachmentSerializer;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

/**
 * This interceptor clears {@link AttachmentSerializer} reference
 * to ease garbage collection.
 */
public class ClearAttachmentsOutInterceptor extends AbstractSoapInterceptor {
    /** 
    * Class constructor.
    */
    public ClearAttachmentsOutInterceptor() {
        //super(Phase.SETUP_ENDING);
        super(Phase.POST_LOGICAL);
        //super(Phase.POST_MARSHAL);

    }

    /** 
    * @param message     The SOAP message to handle.
    */
    public void handleMessage(SoapMessage message) throws Fault {
        message.getExchange()
               .getOutMessage()
               .setContent(org.apache.cxf.attachment.AttachmentSerializer.class, null);
        
        Map<String, List> headers = new HashMap<String, List>();
        try {
            headers.put("User-Agent", Collections.singletonList(message.get("HTTP_HEADER_USER_AGENT")));
            
            message.put(Message.PROTOCOL_HEADERS, headers);
        } catch (Exception ce) {
            throw new Fault(ce);
        }
    }
    
    /**
     *
     * @param message
     */
/*    @Override
    public void handleFault(SoapMessage message) {
        System.out.println("mess="+message);
    } */   
}
