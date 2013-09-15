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
import com.exacttarget.fuelsdk.ETEmailService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETEmail;

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETEmailServiceTest {

	protected static Logger logger = Logger.getLogger(ETEmailServiceTest.class);
	
	protected ETEmailService service;
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	private String NameOfTestEmail = "JavaSDKEmail";
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
        
        service = new ETEmailServiceImpl();
	}
	
	@Test
	public void A_TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETEmail> response =  service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETEmail ret : response.getResults()) {
			logger.debug(ret.toString());
		}
	}
	

	@Test
	public void B_TestPost() throws ETSdkException {
		
		ETEmail email = new ETEmail();
		email.setCustomerKey(NameOfTestEmail);
		email.setName(NameOfTestEmail);
		email.setSubject("Created with the Java SDK");
		email.setHtmlBody("<b>Some HTML Goes here</b>");
		email.setEmailType("HTML");
		email.setHtmlPaste(true);

		ETServiceResponse<ETEmail> response = service.post(client, email);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestEmail);
		ETServiceResponse<ETEmail> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		
		for(ETEmail orgFound : responseFound.getResults()) {
			logger.debug(orgFound.toString());
		}
		
	}
	
	@Test
	public void C_TestPatch() throws ETSdkException {
		
		ETEmail email = new ETEmail();
		email.setCustomerKey(NameOfTestEmail);
		email.setName(NameOfTestEmail);
		email.setSubject("Created with the SDK!!! Now with more !!!!");
		email.setHtmlBody("<b>Some HTML Content Goes here. NOW WITH NEW CONTENT</b>");
		email.setHtmlPaste(true);
		
		ETServiceResponse<ETEmail> response = service.patch(client, email);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestEmail);
		ETServiceResponse<ETEmail> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		Assert.assertEquals("Created with the SDK!!! Now with more !!!!", responseFound.getResults().get(0).getSubject());
		
	}
	
	@Test
	public void D_TestDelete() throws ETSdkException {
		
		ETEmail email = new ETEmail();
		email.setCustomerKey(NameOfTestEmail);
		
		ETServiceResponse<ETEmail> response = service.delete(client, email);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was deleted
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestEmail);
		ETServiceResponse<ETEmail> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(0, responseFound.getResults().size());
		
	}

}

