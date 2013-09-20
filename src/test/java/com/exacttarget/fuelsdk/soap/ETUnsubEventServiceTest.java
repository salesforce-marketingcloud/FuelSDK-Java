//
// ETUnsubEventServiceTest.java -
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
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.ETUnsubEventService;
import com.exacttarget.fuelsdk.model.ETUnsubEvent;


@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETUnsubEventServiceTest  {

	protected static Logger logger = Logger.getLogger(ETOpenEventServiceTest.class);
	
	protected ETUnsubEventService service;
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
        
        service = new ETUnsubEventServiceImpl();
    }
	
	@Test
	public void TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETUnsubEvent> response = service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETUnsubEvent ret : response.getResults()) {
			logger.debug(ret.toString());
		}
	}
}
	
