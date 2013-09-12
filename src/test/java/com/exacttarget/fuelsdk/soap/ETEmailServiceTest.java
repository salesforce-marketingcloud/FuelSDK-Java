package com.exacttarget.fuelsdk.soap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETEmailService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETEmail;

public class ETEmailServiceTest {

	protected ETEmailService service;
	protected ETEmail etObject;
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
        
        service = new ETEmailServiceImpl();
		filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST EMAIL");
		filterUpdated = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST EMAIL UPDATED");
		etObject = new ETEmail();
		etObject.setName("TEST EMAIL");
		etObject.setHtmlBody("<html><body>YEP</body></html>");
		etObject.setSubject("TEST EMAIL SUBJECT");
    	
	}
	
	@Test
	public void TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETEmail> response =  service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		
		for (ETEmail ret : response.getResults()) {
			System.out.println(ret.toString());
		}
	}
	
	@Test
	public void TestCRUDService() throws ETSdkException
	{
		
		TestPost();
		
		ETEmail found = TestRetrieveSingle();
		
		TestPatch(found);
		
		ETEmail foundUpdated = TestRetrieveSingleUpdated();
		
		DeleteSingle(foundUpdated);
		
	}

	protected void TestPatch(ETEmail found) throws ETSdkException {
		
		found.setName("TEST EMAIL UPDATED");
		service.patch(client, found);
		
	}	
	protected ETEmail TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<ETEmail> response = service.get(client, filter);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
		
	}
	
	protected ETEmail TestRetrieveSingleUpdated() throws ETSdkException {
		ETServiceResponse<ETEmail> response = service.get(client, filterUpdated);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
	}

	protected void TestPost() throws ETSdkException
	{
		ETServiceResponse<ETEmail> response =  service.post(client, etObject);
	}
		
	
	protected void DeleteSingle(ETEmail etObject) throws ETSdkException
	{
				
		ETServiceResponse<ETEmail> response2 =  service.delete(client, etObject);
		 
	}
	
}
