package com.exacttarget.fuelsdk;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETObject;

public class ETGetServiceTest<T extends ETObject> {

	protected ETGetService service;
	protected T etObject;
	protected ETFilter filter;
	protected ETFilter filterUpdated;
	
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
    }
	
	@Test
	public void TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<T> response =  (ETServiceResponse<T>) service.get(client, etObject.getClass());
		
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		
		for (T ret : response.getResults()) {
			System.out.println(ret.toString());
		}
		
	}
}
