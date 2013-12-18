//
// ETSentEventServiceTest.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSentEventService;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.model.ETSentEvent;


@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETSentEventServiceTest  {

	protected static Logger logger = Logger.getLogger(ETOpenEventServiceTest.class);
	
	protected ETSentEventService service;
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
        
        service = new ETSentEventServiceImpl();
    }
	
	@Test
	public void TestGetCollectionService() throws ETSdkException
	{
		ETResponse<ETSentEvent> response = service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETSentEvent ret : response.getResults()) {
			logger.debug(ret.toString());
		}
	}
}
	