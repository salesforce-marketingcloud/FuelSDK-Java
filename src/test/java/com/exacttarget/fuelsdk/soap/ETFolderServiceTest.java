//
// ETFolderServiceTest.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

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
import com.exacttarget.fuelsdk.ETFolderService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETComplexFilter;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETLogicalOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETFolder;

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETFolderServiceTest {

	protected static Logger logger = Logger.getLogger(ETFolderServiceTest.class);
	
	protected ETFolderService service;
	protected ETFolder parent;
	protected ETFilter filter;
	protected ETFilter filterUpdated;
	
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	private String NameOfTestFolder = "JavaSDKFolderTestName";
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
        
        service = new ETFolderServiceImpl();
		
		// Get the Parent Folder
		ETServiceResponse<ETFolder> response = ((ETFolderService)service).get(client, new ETComplexFilter(
				new ETSimpleFilter("name",ETFilterOperators.EQUALS, "Data Extensions"),
				new ETSimpleFilter("contentType",ETFilterOperators.EQUALS, "DataExtension"),
				ETLogicalOperators.AND));
		
		
		
		parent = response.getResults().get(0);
    	
	}
	
	@Test
	public void A_TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETFolder> response =  service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETFolder ret : response.getResults()) {
			logger.debug(ret.toString());
		}
	}
	
	@Test
	public void B_TestPost() throws ETSdkException {
		
		ETFolder folder = new ETFolder();
		folder.setActive(true);
		folder.setEditable(true);
		folder.setCustomerKey(NameOfTestFolder);
		folder.setName(NameOfTestFolder);
		folder.setDescription(NameOfTestFolder);
		folder.setContentType("DataExtension");
		folder.setParentFolder(parent);

		ETServiceResponse<ETFolder> response = service.post(client, folder);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestFolder);
		ETServiceResponse<ETFolder> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		
		for(ETFolder orgFound : responseFound.getResults()) {
			logger.debug(orgFound.toString());
		}
		
	}
	
	@Test
	public void C_TestPatch() throws ETSdkException {
		
		ETFolder folder = new ETFolder();
		folder.setCustomerKey(NameOfTestFolder);
		folder.setDescription("New Description");
		
		ETServiceResponse<ETFolder> response = service.patch(client, folder);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestFolder);
		ETServiceResponse<ETFolder> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		Assert.assertEquals("New Description", responseFound.getResults().get(0).getDescription());
		
	}
	
	@Test
	public void D_TestDelete() throws ETSdkException {
		
		ETFolder folder = new ETFolder();
		folder.setCustomerKey(NameOfTestFolder);
		
		ETServiceResponse<ETFolder> response = service.delete(client, folder);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was deleted
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestFolder);
		ETServiceResponse<ETFolder> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(0, responseFound.getResults().size());
		
	}
	
}

