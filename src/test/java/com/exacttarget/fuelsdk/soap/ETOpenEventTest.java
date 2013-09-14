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
import com.exacttarget.fuelsdk.ETOpenEventService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.model.ETOpenEvent;

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETOpenEventTest  {

	protected static Logger logger = Logger.getLogger(ETOpenEventTest.class);
	
	protected ETOpenEventService service;
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
        
        service = new ETOpenEventServiceImpl();
    }
	
	@Test
	public void TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETOpenEvent> response = service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETOpenEvent ret : response.getResults()) {
			logger.debug(ret.toString());
		}
	}
}

