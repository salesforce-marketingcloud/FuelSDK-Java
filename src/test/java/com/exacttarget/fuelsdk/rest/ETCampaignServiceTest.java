package com.exacttarget.fuelsdk.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETCampaign;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETCampaignServiceTest{

	private static final String TEST_CAMPAIGN_CODE_PATCH = "TestCode_PATCH";
	private static final String TEST_CAMPAIGN_CODE = "TestCode";
	protected static Logger logger = Logger.getLogger(ETCampaignServiceTest.class);
	protected static ETCrudService service;
	protected static ETClient client = null;
	protected static ETConfiguration configuration = null;
	protected ETFilter filter;
	protected ETFilter filterUpdated;
	
	
	@BeforeClass
	public static void setUp() throws ETSdkException {
		logger.debug("SetUp");
		configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
		
		service = new ETCampaignServiceImpl();
		//filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "testCampaign");
		//filterUpdated = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "testCampaign");
	}
	
	@Test
	public void TestClean() {
		logger.debug("TestClean()");
		
		try {
			
			logger.debug("TestRetrieve");
			
			List<ETCampaign> campaigns = TestRetrieve();

			logger.debug("Received Count during clean: " + campaigns.size());
			
			for( ETCampaign c: campaigns )
			{
				logger.debug("Received during Clean: " + c);
				if( TEST_CAMPAIGN_CODE.equals(c.getCampaignCode()) || TEST_CAMPAIGN_CODE_PATCH.equals(c.getCampaignCode()))
				{
					logger.debug("Deleting during Clean: " + c);
					DeleteSingle(c);
				}
			}
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void TestDelete() {
		logger.debug("TestDelete()");
		
		try {
			createCampaign(TEST_CAMPAIGN_CODE);
			
			ETCampaign testCampaign1 = TestRetrieveSingle();
			
			Assert.assertEquals(TEST_CAMPAIGN_CODE, testCampaign1.getCampaignCode());
			
			DeleteSingle(testCampaign1);
			
			ETCampaign testCampaign2 = TestRetrieveSingle();
			
			Assert.assertNotEquals(TEST_CAMPAIGN_CODE, testCampaign2.getCampaignCode());
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}
	@Test
	public void TestPost(){
		logger.debug("TestPost()");

		try {
			createCampaign(TEST_CAMPAIGN_CODE);
			
			ETCampaign testCampaign1 = TestRetrieveSingle();
			
			Assert.assertEquals(TEST_CAMPAIGN_CODE, testCampaign1.getCampaignCode());
			
			DeleteSingle(testCampaign1);
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestPatch() throws ETSdkException {
		logger.debug("TestPatch()");
		
		try {
			createCampaign(TEST_CAMPAIGN_CODE);
			
			ETCampaign testCampaign1 = TestRetrieveSingle();
			Assert.assertNotNull(testCampaign1);

			testCampaign1.setCampaignCode(TEST_CAMPAIGN_CODE_PATCH);

			Assert.assertEquals(TEST_CAMPAIGN_CODE_PATCH, testCampaign1.getCampaignCode());
			
			ETServiceResponse<ETCampaign> response = service.patch(client, testCampaign1);
			Assert.assertNotNull(response);
			Assert.assertNotNull(response.getResults());
			Assert.assertEquals(1, response.getResults().size());
			Assert.assertEquals(TEST_CAMPAIGN_CODE_PATCH, response.getResults().get(0).getCampaignCode());
			
			ETCampaign testCampaign2 = TestRetrieveSingle();
			Assert.assertEquals(TEST_CAMPAIGN_CODE_PATCH, testCampaign2.getCampaignCode());
			
			DeleteSingle(testCampaign2);
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}

	private ETCampaign createCampaign(String campaign) throws ETSdkException {
		ETCampaign etObject = new ETCampaign();
		etObject.setName("testCampaign");
		etObject.setDescription("testCampaign");
		etObject.setCampaignCode(campaign);
		etObject.setColor("000fff");
		etObject.setFavorite(false);
		
		ETServiceResponse<ETCampaign> response =  service.post(client, etObject);
		Assert.assertNotNull(response);
		
		return etObject;
	}
	
	protected List<ETCampaign> TestRetrieve() throws ETSdkException {
		ETServiceResponse<ETCampaign> response = service.get(client, ETCampaign.class, filter);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		return response.getResults();
	}
	
	protected ETCampaign TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<ETCampaign> response = service.get(client, ETCampaign.class, filter);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertNotNull(response.getResults().get(0));
		return response.getResults().get(0);
	}
	
	protected void DeleteSingle(ETCampaign etObject) throws ETSdkException
	{
		ETServiceResponse<ETCampaign> response = service.delete(client, etObject);
		Assert.assertNotNull(response);
	}
}
