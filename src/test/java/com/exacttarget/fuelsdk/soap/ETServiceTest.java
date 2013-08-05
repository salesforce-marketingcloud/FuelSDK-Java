package com.exacttarget.fuelsdk.soap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETObject;

public abstract class ETServiceTest<T extends ETObject> {

	protected ETCrudService service;
	protected T etObject;
	protected ETFilter filter;
	
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
	
	
	@Test
	public void TestCRUDService() throws ETSdkException
	{
		
		TestPost();
		
		TestRetrieveSingle();
		
		DeleteSingle();
		
	}
	
	protected void TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<T> response = (ETServiceResponse<T>) service.get(client, etObject.getClass(), filter);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		
	}

	protected void TestPost() throws ETSdkException
	{
		ETServiceResponse<T> response =  service.post(client, etObject);
	}
	
	protected void DeleteSingle() throws ETSdkException
	{
				
		ETServiceResponse<T> response2 = service.delete(client, etObject);
		 
	}
	
}
