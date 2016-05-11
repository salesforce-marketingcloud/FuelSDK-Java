package com.exacttarget.fuelsdk;

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
    public ClearAttachmentsOutInterceptor() {
        super(Phase.SETUP_ENDING);
    }

    public void handleMessage(SoapMessage message) throws Fault {
        message.getExchange()
               .getOutMessage()
               .setContent(org.apache.cxf.attachment.AttachmentSerializer.class, null);
    }
}
