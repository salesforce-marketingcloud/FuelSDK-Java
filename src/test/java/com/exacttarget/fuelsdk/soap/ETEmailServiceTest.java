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
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETEmail ret : response.getResults()) {
			logger.debug(ret.toString());
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
		ETServiceResponse<ETEmail> response = service.patch(client, found);
		Assert.assertTrue(response.getStatus());
		
	}	
	protected ETEmail TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<ETEmail> response = service.get(client, filter);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		logger.debug(response.getResults().get(0));
		return response.getResults().get(0);
		
	}
	
	protected ETEmail TestRetrieveSingleUpdated() throws ETSdkException {
		ETServiceResponse<ETEmail> response = service.get(client, filterUpdated);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		logger.debug(response.getResults().get(0));
		return response.getResults().get(0);
	}

	protected void TestPost() throws ETSdkException
	{
		ETServiceResponse<ETEmail> response =  service.post(client, etObject);
		Assert.assertTrue(response.getStatus());
	}
		
	
	protected void DeleteSingle(ETEmail etObject) throws ETSdkException
	{
				
		ETServiceResponse<ETEmail> response =  service.delete(client, etObject);
		Assert.assertTrue(response.getStatus());
		 
	}
	
}
