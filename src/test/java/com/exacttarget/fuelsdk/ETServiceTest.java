package com.exacttarget.fuelsdk;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETObject;

public abstract class ETServiceTest<T extends ETObject> {

	protected ETCrudService service;
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
	
	
	@Test
	public void TestCRUDService() throws ETSdkException
	{
		
		TestPost();
		
		T found = TestRetrieveSingle();
		
		TestPatch(found);
		
		T foundUpdated = TestRetrieveSingleUpdated();
		
		DeleteSingle(foundUpdated);
		
	}
	
	protected T TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<T> response = (ETServiceResponse<T>) service.get(client, etObject.getClass(), filter);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
		
	}
	
	protected T TestRetrieveSingleUpdated() throws ETSdkException {
		ETServiceResponse<T> response = (ETServiceResponse<T>) service.get(client, etObject.getClass(), filterUpdated);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
	}

	protected void TestPost() throws ETSdkException
	{
		ETServiceResponse<T> response =  service.post(client, etObject);
	}
	
	protected abstract void TestPatch(T found) throws ETSdkException;
		
	
	protected void DeleteSingle(T etObject) throws ETSdkException
	{
				
		ETServiceResponse<T> response2 = service.delete(client, etObject);
		 
	}
	
}
