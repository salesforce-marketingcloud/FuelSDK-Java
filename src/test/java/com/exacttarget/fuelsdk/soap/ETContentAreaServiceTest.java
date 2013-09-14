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
import com.exacttarget.fuelsdk.ETContentAreaService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETContentArea;

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETContentAreaServiceTest {

	protected static Logger logger = Logger.getLogger(ETContentAreaServiceTest.class);
	
	protected ETContentAreaService service;
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	private String NameOfTestContentArea = "JavaSDKContentArea";
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
    	service = new ETContentAreaServiceImpl();
	}
	
	@Test
	public void A_TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETContentArea> response =  service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETContentArea ret : response.getResults()) {
			logger.debug(ret.toString());
		}
	}
	

	@Test
	public void B_TestPost() throws ETSdkException {
		
		ETContentArea contentArea = new ETContentArea();
		contentArea.setCustomerKey(NameOfTestContentArea);
		contentArea.setName(NameOfTestContentArea);
		contentArea.setContent("<b>Some HTML Content Goes here</b>");

		ETServiceResponse<ETContentArea> response = service.post(client, contentArea);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestContentArea);
		ETServiceResponse<ETContentArea> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		
		for(ETContentArea orgFound : responseFound.getResults()) {
			logger.debug(orgFound.toString());
		}
		
	}
	
	@Test
	public void C_TestPatch() throws ETSdkException {
		
		ETContentArea contentArea = new ETContentArea();
		contentArea.setCustomerKey(NameOfTestContentArea);
		contentArea.setContent("<b>Some HTML Content Goes here. NOW WITH NEW CONTENT</b>");
		
		ETServiceResponse<ETContentArea> response = service.patch(client, contentArea);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestContentArea);
		ETServiceResponse<ETContentArea> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		Assert.assertEquals("<b>Some HTML Content Goes here. NOW WITH NEW CONTENT</b>", responseFound.getResults().get(0).getContent());
		
	}
	
	@Test
	public void D_TestDelete() throws ETSdkException {
		
		ETContentArea contentArea = new ETContentArea();
		contentArea.setCustomerKey(NameOfTestContentArea);
		
		ETServiceResponse<ETContentArea> response = service.delete(client, contentArea);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was deleted
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestContentArea);
		ETServiceResponse<ETContentArea> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(0, responseFound.getResults().size());
		
	}

}
