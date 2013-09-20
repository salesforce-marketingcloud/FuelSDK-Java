//
// ETCampaignAssetServiceTest.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.exacttarget.fuelsdk.ETCampaignAssetService;
import com.exacttarget.fuelsdk.ETCampaignService;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETCampaign;
import com.exacttarget.fuelsdk.model.ETCampaignAsset;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETCampaignAssetServiceTest{

	private static final String TEST_CAMPAIGN_CODE_PATCH = "TestCode_PATCH";
	private static final String TEST_CAMPAIGN_CODE = "TestCode";
	protected static Logger logger = Logger.getLogger(ETCampaignAssetServiceTest.class);
	protected static ETCampaignService campaignService;
	protected static ETCampaignAssetService assetService;
	protected static ETClient client = null;
	protected static ETConfiguration configuration = null;
	protected ETFilter filter;
	protected ETFilter filterUpdated;
	
	
	@BeforeClass
	public static void setUp() throws ETSdkException {
		logger.debug("SetUp");
		configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
		
		campaignService = new ETCampaignServiceImpl();
		assetService = new ETCampaignAssetServiceImpl();
	}
	
	@Test
	public void ATestClean() {
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
					deleteCampaign(c);
				}
			}
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void TestAssociateAsset(){
		logger.debug("TestAssociateAsset()");

		try 
		{
			ETCampaign campaign = createCampaign(TEST_CAMPAIGN_CODE);
			
			ETCampaignAsset asset = new ETCampaignAsset();
			asset.setCampaignId(campaign.getId());
			asset.setItemID("321");
			asset.setType("EMAIL");
			
			ETServiceResponse<ETCampaignAsset> response = assetService.post(client, asset);
			
			Assert.assertNotNull(response);
			
			String campaignID = response.getResults().get(0).getCampaignId();
			
			response = assetService.get(client, new ETSimpleFilter("ID", ETFilterOperators.EQUALS, campaignID ));
			
			Assert.assertNotNull(response);

			ETCampaignAsset responseAsset = response.getResults().get(0);
			
			Assert.assertNotNull(responseAsset);
			
			Assert.assertEquals(campaignID, responseAsset.getCampaignId());
			
			deleteCampaign(campaign);
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestUnassociateAsset()
	{
		logger.debug("TestUnassociateAsset()");

		try {
			ETCampaign campaign = createCampaign(TEST_CAMPAIGN_CODE);
			
			ETCampaignAsset asset = new ETCampaignAsset();
			asset.setCampaignId(campaign.getId());
			asset.setItemID("321");
			asset.setType("EMAIL");
			
			ETServiceResponse<ETCampaignAsset> response = assetService.post(client, asset);
			
			Assert.assertNotNull(response);
			
			String campaignID = response.getResults().get(0).getCampaignId();
			
			//TODO: refactor the get
			response = assetService.get(client, new ETSimpleFilter("ID", ETFilterOperators.EQUALS, campaignID ));
			
			Assert.assertNotNull(response);
			
			ETCampaignAsset responseAsset = response.getResults().get(0);
			
			Assert.assertNotNull(responseAsset);
			
			String responseCampaignId = responseAsset.getCampaignId();
			
			Assert.assertEquals(campaignID, responseCampaignId);
			
			//Delete
			assetService.delete(client, responseAsset);
			
			response = null;
			
			//Validate it's been deleted
			response = assetService.get(client, new ETSimpleFilter("ID", ETFilterOperators.EQUALS, campaignID ));
			
			Assert.assertEquals(0,response.getResults().size());
			
			deleteCampaign(campaign);
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}
	private ETCampaign createCampaign(String campaign) throws ETSdkException 
	{
		ETCampaign etObject = new ETCampaign();
		etObject.setName("testCampaign");
		etObject.setDescription("testCampaign");
		etObject.setCampaignCode(campaign);
		etObject.setColor("000fff");
		etObject.setFavorite(false);
		
		ETServiceResponse<ETCampaign> response =  campaignService.post(client, etObject);
		Assert.assertNotNull(response);
		
		return response.getResults().get(0);
	}
	
	protected void deleteCampaign(ETCampaign etObject) throws ETSdkException
	{
		ETServiceResponse<ETCampaign> response = campaignService.delete(client, etObject);
		Assert.assertNotNull(response);
	}

	protected List<ETCampaign> TestRetrieve() throws ETSdkException {
		ETServiceResponse<ETCampaign> response = campaignService.get(client);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		return response.getResults();
	}

	protected ETCampaign TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<ETCampaign> response = campaignService.get(client, filter);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertNotNull(response.getResults().get(0));
		return response.getResults().get(0);
	}
}
