//
// ETBounceEventServiceTest.java -
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

import com.exacttarget.fuelsdk.ETBounceEventService;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.model.ETBounceEvent;


@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETBounceEventServiceTest  {

	protected static Logger logger = Logger.getLogger(ETOpenEventServiceTest.class);
	
	protected ETBounceEventService service;
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
        
        service = new ETBounceEventServiceImpl();
    }
	
	@Test
	public void TestGetCollectionService() throws ETSdkException
	{
		ETResponse<ETBounceEvent> response = service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETBounceEvent ret : response.getResults()) {
			logger.debug(ret.toString());
		}
	}
}
