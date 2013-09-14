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
import com.exacttarget.fuelsdk.ETListService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETList;
import com.exacttarget.fuelsdk.model.ETListType;

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETListServiceTest {
	
	protected static Logger logger = Logger.getLogger(ETListServiceTest.class);
	
	protected ETListService service;
	protected ETList etObject;
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
        
		service = new ETListServiceImpl();
		filter = new ETSimpleFilter("listName", ETFilterOperators.EQUALS, "TEST LIST FROM SDK");
		filterUpdated = new ETSimpleFilter("listName", ETFilterOperators.EQUALS, "TEST LIST FROM SDK UPDATED");
		etObject = new ETList();
		etObject.setName("TEST LIST FROM SDK");
		etObject.setListType(ETListType.PUBLIC);

    	
	}
	
	@Test
	public void TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETList> response =  service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETList ret : response.getResults()) {
			logger.debug(ret.toString());
		}
	}
	
	@Test
	public void TestCRUDService() throws ETSdkException
	{
		
		TestPost();
		
		ETList found = TestRetrieveSingle();
		
		TestPatch(found);
		
		ETList foundUpdated = TestRetrieveSingleUpdated();
		
		DeleteSingle(foundUpdated);
		
	}

	protected void TestPatch(ETList found) throws ETSdkException {
		
		found.setName("TEST LIST FROM SDK UPDATED");
		ETServiceResponse<ETList> response = service.patch(client, found);
		Assert.assertTrue(response.getStatus());
		
	}	
	protected ETList TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<ETList> response = service.get(client, filter);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		logger.debug(response.getResults().get(0));
		return response.getResults().get(0);
		
	}
	
	protected ETList TestRetrieveSingleUpdated() throws ETSdkException {
		ETServiceResponse<ETList> response = service.get(client, filterUpdated);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		logger.debug(response.getResults().get(0));
		return response.getResults().get(0);
	}

	protected void TestPost() throws ETSdkException
	{
		ETServiceResponse<ETList> response =  service.post(client, etObject);
		Assert.assertTrue(response.getStatus());
	}
		
	
	protected void DeleteSingle(ETList etObject) throws ETSdkException
	{
				
		ETServiceResponse<ETList> response =  service.delete(client, etObject);
		Assert.assertTrue(response.getStatus());
		 
	}
	
}

