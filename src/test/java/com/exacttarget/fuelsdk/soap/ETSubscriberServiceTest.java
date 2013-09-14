package com.exacttarget.fuelsdk.soap;

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
import com.exacttarget.fuelsdk.ETSubscriberService;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETSubscriber;
import com.exacttarget.fuelsdk.model.ETSubscriberStatus;

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETSubscriberServiceTest {

	
	protected ETSubscriberService service;
	protected ETSubscriber etObject;
	protected ETFilter filter;
	protected ETFilter filterUpdated;
	
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	private String SubscriberTestEmail = "PHPSDKExample@bh.exacttarget.com";
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
        
        service = new ETSubscriberServiceImpl();
	}
	

	@Test
	public void A_TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETSubscriber> response =  service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETSubscriber ret : response.getResults()) {
			System.out.println(ret.toString());
		}
	}
	

	@Test
	public void B_TestPost() throws ETSdkException {
		
		ETSubscriber subscriber = new ETSubscriber();
		subscriber.setEmailAddress(SubscriberTestEmail);
		subscriber.setSubscriberKey(SubscriberTestEmail);
		
		ETServiceResponse<ETSubscriber> response = service.post(client, subscriber);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("SubscriberKey", ETFilterOperators.EQUALS, SubscriberTestEmail);
		ETServiceResponse<ETSubscriber> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		
		for(ETSubscriber orgFound : responseFound.getResults()) {
			
			System.out.println(orgFound.toString());
		}
		
		
	}
	
	@Test
	public void C_TestPatch() throws ETSdkException {
		
		ETSubscriber subscriber = new ETSubscriber();
		subscriber.setEmailAddress(SubscriberTestEmail);
		subscriber.setSubscriberKey(SubscriberTestEmail);
		subscriber.setStatus(ETSubscriberStatus.UNSUBSCRIBED);
		
		ETServiceResponse<ETSubscriber> response = service.patch(client, subscriber);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("SubscriberKey", ETFilterOperators.EQUALS, SubscriberTestEmail);
		ETServiceResponse<ETSubscriber> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		Assert.assertEquals(ETSubscriberStatus.UNSUBSCRIBED, responseFound.getResults().get(0).getStatus());
		
	}
	
	@Test
	public void D_TestDelete() throws ETSdkException {
		
		ETSubscriber subscriber = new ETSubscriber();
		subscriber.setSubscriberKey(SubscriberTestEmail);
		
		ETServiceResponse<ETSubscriber> response = service.delete(client, subscriber);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was deleted
		ETFilter filter = new ETSimpleFilter("SubscriberKey", ETFilterOperators.EQUALS, SubscriberTestEmail);
		ETServiceResponse<ETSubscriber> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(0, responseFound.getResults().size());
		
	}
	
}

