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
	public void ATestClean() 
	{
		logger.debug("TestClean()");
		
		try 
		{
			logger.debug("TestRetrieve");
			
			List<ETCampaign> campaigns = getAllCampaigns();

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
			
			//Associate (Post) an asset
			ETCampaignAsset createdAsset = createAsset(campaign.getId());
			
			String campaignID = createdAsset.getCampaignId();

			//Fetch (Get) the associated asset
			ETCampaignAsset responseAsset = getAllAssets(campaignID).get(0);
			
			Assert.assertNotNull(responseAsset);
			
			Assert.assertEquals(campaignID, responseAsset.getCampaignId());
			
			//Delete the campaign that this new asset was associated 
			deleteCampaign(campaign);
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestUnassociateAsset()
	{
		logger.debug("TestUnassociateAsset()");

		try 
		{
			ETCampaign campaign = createCampaign(TEST_CAMPAIGN_CODE);
			
			//Associate (Post) an asset
			ETCampaignAsset createdAsset = createAsset(campaign.getId());
			
			String campaignID = createdAsset.getCampaignId();

			//Fetch (Get) the associated asset
			ETCampaignAsset responseAsset = getAllAssets(campaignID).get(0);
			
			Assert.assertNotNull(responseAsset);
			
			Assert.assertEquals(campaignID, responseAsset.getCampaignId());
			
			//Delete
			assetService.delete(client, responseAsset);
			
			//Fetch (Get) the deleted associated asset ( should return null )
			List<ETCampaignAsset> emptyAssets = getAllAssets(campaignID);
			
			Assert.assertEquals(0, emptyAssets.size());
			
			deleteCampaign(campaign);
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void TestRetrieveSingle()
	{
		logger.debug("TestRetrieveSingle()");

		try 
		{
			ETCampaign campaign = createCampaign(TEST_CAMPAIGN_CODE);
			
			//Associate (Post) an asset
			ETCampaignAsset createdAsset = createAsset(campaign.getId());

			Assert.assertNotNull(createdAsset);
			
			String campaignID = createdAsset.getCampaignId();
			String assetId = createdAsset.getId();

			//Fetch (Get) the associated asset - main part of test
			ETCampaignAsset responseAsset = getAsset(campaignID, assetId);
			
			Assert.assertNotNull(responseAsset);
			
			Assert.assertEquals(campaignID, responseAsset.getCampaignId());
			
			//Delete
			assetService.delete(client, responseAsset);
			
			//Fetch (Get) the deleted associated asset ( should return null )
			List<ETCampaignAsset> emptyAssets = getAllAssets(campaignID);
			
			Assert.assertEquals(0, emptyAssets.size());
			
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

	private List<ETCampaignAsset> getAllAssets(String campaignId) throws ETSdkException 
	{
		ETServiceResponse<ETCampaignAsset> response = assetService.get(client, campaignId, null);
		
		Assert.assertNotNull(response);
		
		return response.getResults();
	}

	private ETCampaignAsset getAsset(String campaignId, String assetId) throws ETSdkException 
	{
		ETServiceResponse<ETCampaignAsset> response = assetService.get(client, campaignId, assetId);

		Assert.assertNotNull(response);
		
		Assert.assertNotNull(response.getResults());
		
		Assert.assertEquals(1, response.getResults().size());
		
		return response.getResults().size()==0?null:response.getResults().get(0);
	}
	
	private ETCampaignAsset createAsset(String compaignId) throws ETSdkException 
	{
		ETCampaignAsset asset = new ETCampaignAsset();
		
		asset.setCampaignId(compaignId);
		asset.setItemID("321");
		asset.setType("EMAIL");
		
		ETServiceResponse<ETCampaignAsset> response = assetService.post(client, asset);
		
		Assert.assertNotNull(response);
		
		return response.getResults().size()==0?null:response.getResults().get(0);
	}

	private List<ETCampaign> getAllCampaigns() throws ETSdkException 
	{
		ETServiceResponse<ETCampaign> response = campaignService.get(client);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		return response.getResults();
	}
}
