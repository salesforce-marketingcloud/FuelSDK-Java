package com.exacttarget.fuelsdk.soap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.ETSubscriberService;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETSubscriber;

public class ETSubscriberServiceTest {

	
	protected ETSubscriberService service;
	protected ETSubscriber etObject;
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
        
        service = new ETSubscriberServiceImpl();
		filter = new ETSimpleFilter("emailAddress", ETFilterOperators.EQUALS, "ryanjlowetest99@gmail.com");
		filterUpdated = new ETSimpleFilter("subscriberKey", ETFilterOperators.EQUALS, "ryanjlowetest993@gmail.com");
		
		etObject = new ETSubscriber();
		etObject.setEmailAddress("ryanjlowetest99@gmail.com");
		etObject.setSubscriberKey("ryanjlowetest99@gmail.com");    	
	}
	
	@Test
	public void TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETSubscriber> response =  service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		
		for (ETSubscriber ret : response.getResults()) {
			System.out.println(ret.toString());
		}
	}
	
	@Test
	public void TestCRUDService() throws ETSdkException
	{
		
		TestPost();
		
		ETSubscriber found = TestRetrieveSingle();
		
		TestPatch(found);
		
		ETSubscriber foundUpdated = TestRetrieveSingleUpdated();
		
		DeleteSingle(foundUpdated);
		
	}

	protected void TestPatch(ETSubscriber found) throws ETSdkException {
		
		found.setEmailAddress("ryanjlowetest993@gmail.com");
		service.patch(client, found);
		
	}	
	protected ETSubscriber TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<ETSubscriber> response = service.get(client, filter);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
		
	}
	
	protected ETSubscriber TestRetrieveSingleUpdated() throws ETSdkException {
		ETServiceResponse<ETSubscriber> response = service.get(client, filterUpdated);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
	}

	protected void TestPost() throws ETSdkException
	{
		ETServiceResponse<ETSubscriber> response =  service.post(client, etObject);
	}
		
	
	protected void DeleteSingle(ETSubscriber etObject) throws ETSdkException
	{
				
		ETServiceResponse<ETSubscriber> response2 =  service.delete(client, etObject);
		 
	}
	
}

