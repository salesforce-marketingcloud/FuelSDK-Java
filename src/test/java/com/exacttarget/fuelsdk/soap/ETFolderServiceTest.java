package com.exacttarget.fuelsdk.soap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETFolderService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETComplexFilter;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETLogicalOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETFolder;

public class ETFolderServiceTest {

	protected ETFolderService service;
	protected ETFolder etObject;
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
        
        service = new ETFolderServiceImpl();
		filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST FOLDER NAME");
		filterUpdated = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST FOLDER NAME UPDATED");
		
		etObject = new ETFolder();
		etObject.setName("TEST FOLDER NAME");
		etObject.setDescription("TEST Folder Description");
		etObject.setActive(true);
		etObject.setContentType("DataExtension");
		etObject.setEditable(true);
		
		ETServiceResponse<ETFolder> response = ((ETFolderService)service).get(client, new ETComplexFilter(
				new ETSimpleFilter("name",ETFilterOperators.EQUALS, "Data Extensions"),
				new ETSimpleFilter("contentType",ETFilterOperators.EQUALS, "DataExtension"),
				ETLogicalOperators.AND));
		
		
		
		ETFolder parent = response.getResults().get(0);
		etObject.setParentFolder(parent);
    	
	}
	
	@Test
	public void TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETFolder> response =  service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		
		for (ETFolder ret : response.getResults()) {
			System.out.println(ret.toString());
		}
	}
	
	@Test
	public void TestCRUDService() throws ETSdkException
	{
		
		TestPost();
		
		ETFolder found = TestRetrieveSingle();
		
		TestPatch(found);
		
		ETFolder foundUpdated = TestRetrieveSingleUpdated();
		
		DeleteSingle(foundUpdated);
		
	}

	protected void TestPatch(ETFolder found) throws ETSdkException {
		
		found.setName("TEST FOLDER NAME UPDATED");
		service.patch(client, found);
		
	}	
	protected ETFolder TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<ETFolder> response = service.get(client, filter);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
		
	}
	
	protected ETFolder TestRetrieveSingleUpdated() throws ETSdkException {
		ETServiceResponse<ETFolder> response = service.get(client, filterUpdated);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
	}

	protected void TestPost() throws ETSdkException
	{
		ETServiceResponse<ETFolder> response =  service.post(client, etObject);
	}
		
	
	protected void DeleteSingle(ETFolder etObject) throws ETSdkException
	{
				
		ETServiceResponse<ETFolder> response2 =  service.delete(client, etObject);
		 
	}
	
}

