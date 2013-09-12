package com.exacttarget.fuelsdk.soap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETContentAreaService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETContentArea;

public class ETContentAreaServiceTest {

	protected ETContentAreaService service;
	protected ETContentArea etObject;
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
    	service = new ETContentAreaServiceImpl();
		filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST Content Area");
		filterUpdated = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST Content Area UPDATED");
		
		etObject = new ETContentArea();
		etObject.setName("TEST Content Area");
		etObject.setContent("TEST CONTENT AREA CONTENT");
	}
	
	@Test
	public void TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETContentArea> response =  service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		
		for (ETContentArea ret : response.getResults()) {
			System.out.println(ret.toString());
		}
	}
	
	@Test
	public void TestCRUDService() throws ETSdkException
	{
		
		TestPost();
		
		ETContentArea found = TestRetrieveSingle();
		
		TestPatch(found);
		
		ETContentArea foundUpdated = TestRetrieveSingleUpdated();
		
		DeleteSingle(foundUpdated);
		
	}

	protected void TestPatch(ETContentArea found) throws ETSdkException {
		
		found.setName("TEST Content Area UPDATED");
		
		((ETContentAreaService)service).patch(client, found);
		
	}
	
	protected ETContentArea TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<ETContentArea> response = service.get(client, filter);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
		
	}
	
	protected ETContentArea TestRetrieveSingleUpdated() throws ETSdkException {
		ETServiceResponse<ETContentArea> response = service.get(client, filterUpdated);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
	}

	protected void TestPost() throws ETSdkException
	{
		ETServiceResponse<ETContentArea> response =  service.post(client, etObject);
	}
		
	
	protected void DeleteSingle(ETContentArea etObject) throws ETSdkException
	{
				
		ETServiceResponse<ETContentArea> response2 =  service.delete(client, etObject);
		 
	}
}
