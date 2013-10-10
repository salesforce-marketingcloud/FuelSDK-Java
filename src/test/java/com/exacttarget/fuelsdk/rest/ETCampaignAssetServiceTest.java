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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runners.MethodSorters;

import com.exacttarget.fuelsdk.ETCampaignAssetService;
import com.exacttarget.fuelsdk.ETCampaignService;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETComplexFilter;
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

    @Rule
    public ErrorCollector collector = new ErrorCollector();
	
    @BeforeClass
	public static void setUp() throws ETSdkException {
		logger.debug("SetUp");
		configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
		
		campaignService = new ETCampaignServiceImpl();
		assetService = new ETCampaignAssetServiceImpl();
	}
	
	@Test
	public void ATestClean() throws ETSdkException {
		logger.debug("TestClean()");
		
		List<ETCampaign> campaigns = retrieveAllCampaigns();

		logger.debug("Received Count during clean: " + campaigns.size());
		
		for( ETCampaign c: campaigns )
		{
			logger.debug("Received during Clean: " + c);
			
			if( c.getCampaignCode().contains(TEST_CAMPAIGN_CODE) || c.getCampaignCode().contains(TEST_CAMPAIGN_CODE_PATCH) )
			{
				logger.debug("Deleting during Clean: " + c);
				deleteCampaign(c);
			}
		}
	
	}
	
	@Test
	public void TestAssociateAsset() throws ETSdkException
	{
		List<String> ids = new ArrayList<String>();

		logger.debug("TestAssociateAsset()");
		
		try 
		{
			//Create 5 unique Campaigns
			for( int i=0;i<14;++i )
			{
				ETCampaign c = createCampaign(TEST_CAMPAIGN_CODE + i);
				ids.add(c.getId());
			}
			
			int i = 0;
			for( String id: ids )
			{
				ETCampaignAsset asset = new ETCampaignAsset();
				asset.setCampaignId(id);
				asset.setItemID(id + i);
				String type = ETCampaignAssetService.TYPE.values()[i].toString();
				logger.debug("Association Type: " + type );
				asset.setType(type);
				
				//TEST begin
				ETServiceResponse<ETCampaignAsset> response = assetService.post(client, asset);
				
				assertNotNull("Response should not be null", response);
				assertTrue("Status should be True",response.getStatus());
				assertNotNull("Results should not be null",response.getResults());
				assertEquals("When associating Assets with type=\'" +type+"\' There should be 1 result",1,response.getResults().size());
				
				if( response.getResults().size() > 0 )
				{
					ETCampaignAsset returnedAsset = response.getResults().get(0);		
					String campaignID = returnedAsset.getCampaignId();
					String returnedId = returnedAsset.getId();
					
					ETCampaignAsset responseAsset = retrieveAsset(campaignID, returnedId);
					
					assertNotNull("Response from Asset Retrieve should not be null",responseAsset);
					
					assertEquals("CampaignIDs should match",campaignID, responseAsset.getCampaignId());
				}
				
				i++;
				//TEST end				
			}
		} 
		catch (Exception e) 
		{
			collector.addError(e);
		}
		finally
		{
			try 
			{
				//Delete all created Campaigns (cleanup)
				for( String id: ids )
				{
					ETCampaign c = new ETCampaign();
					c.setId( id );
					deleteCampaign(c);
				}
			} 
			catch (ETSdkException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void TestUnassociateAsset() throws ETSdkException
	{
		logger.debug("TestUnassociateAsset()");

		ETCampaign campaign = createCampaign(TEST_CAMPAIGN_CODE);
		
		ETCampaignAsset asset = new ETCampaignAsset();
		asset.setCampaignId(campaign.getId());
		asset.setItemID("321");
		asset.setType(ETCampaignAssetService.TYPE.LANDING_PAGE.toString());
		
		ETServiceResponse<ETCampaignAsset> response = assetService.post(client, asset);
		
		assertNotNull("",response);
		assertTrue("",response.getStatus());
		
		ETCampaignAsset returnedAsset = response.getResults().get(0);		
		String campaignID = returnedAsset.getCampaignId();
		String id = returnedAsset.getId();
		
		ETCampaignAsset responseAsset = retrieveAsset(campaignID, id);
		
		assertNotNull("",responseAsset);
		
		String responseCampaignId = responseAsset.getCampaignId();
		
		assertEquals("CampaignIDs should match.",campaignID, responseCampaignId);

		response = null;
		//Delete
		response = assetService.delete(client, responseAsset);

		assertNotNull("",response);
		assertTrue("",response.getStatus());
		
		response = null;
		
		//Validate it's been deleted
		response = assetService.get(client, new ETSimpleFilter("campaignId", ETFilterOperators.EQUALS, campaignID ));

		assertNotNull("",response);
		assertTrue("",response.getStatus());
		assertEquals("",0,response.getResults().size());
		
		deleteCampaign(campaign);
	}

	@Test
	public void TestRetrieveAllAssets()
	{
		logger.debug("TestRetrieveAllAssets()");
		String campaignId = null;
		
		try 
		{
			ETCampaign c = createCampaign(TEST_CAMPAIGN_CODE);
			campaignId = c.getId();
			
			//9, 10 were chosen because there were issues with other 'Association Types'
			for( int i=9; i<11; ++i )
			{
				ETCampaignAsset asset = new ETCampaignAsset();
				asset.setCampaignId(campaignId);
				asset.setItemID(campaignId + 77);
				String type = ETCampaignAssetService.TYPE.values()[i].toString();
				logger.debug("Association Type: " + type );
				asset.setType(type);
				
				ETServiceResponse<ETCampaignAsset> response = assetService.post(client, asset);
				
				assertNotNull("Response should not be null", response);
				assertTrue("Status should be True",response.getStatus());
				assertNotNull("Results should not be null",response.getResults());
				assertEquals("When associating Assets with type=\'" +type+"\' There should be 1 result",1,response.getResults().size());	
			}

			//Test that there are 2 Assets associated to the 1 campaign.
			ETServiceResponse<ETCampaignAsset> response = retrieveAllAssets(campaignId);
			
			assertNotNull("Response should not be null", response);
			assertTrue("Status should be True",response.getStatus());
			assertNotNull("Results should not be null",response.getResults());
			assertEquals("There should be 2 result",2,response.getResults().size());
			
			//Cleanup assets
			for( ETCampaignAsset asset: response.getResults() )
			{
				logger.debug("Unassociate Asset: " + asset.getId() + " from Campaign: " + asset.getCampaignId());
				assetService.delete(client, asset);
			}
			
		} 
		catch (Exception e) 
		{
			collector.addError(e);
		}
		finally
		{
			try 
			{
				if( campaignId != null )
				{
					ETCampaign c = new ETCampaign();
					c.setId( campaignId );
					deleteCampaign(c);
				}
			} 
			catch (ETSdkException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private ETCampaignAsset retrieveAsset(String campaignID, String id) throws ETSdkException 
	{
		ETServiceResponse<ETCampaignAsset> response;
		ETComplexFilter filter = new ETComplexFilter();
		List<ETFilter> filters = new ArrayList<ETFilter>();
		filters.add(new ETSimpleFilter("campaignId", ETFilterOperators.EQUALS, campaignID ));
		filters.add(new ETSimpleFilter("id", ETFilterOperators.EQUALS, id ));
		filter.setAdditionalOperands(filters);
		
		response = assetService.get(client, filter);
		
		assertNotNull("Response should not be null.",response);
		assertTrue("Response Status should be True.",response.getStatus());
		assertEquals("There should only be 1 Result.",1,response.getResults().size());
		
		ETCampaignAsset responseAsset = response.getResults().get(0);
		return responseAsset;
	}
	
	private ETServiceResponse<ETCampaignAsset> retrieveAllAssets(String campaignID) throws ETSdkException 
	{
		ETServiceResponse<ETCampaignAsset> response;
		ETSimpleFilter filter = new ETSimpleFilter("campaignId", ETFilterOperators.EQUALS, campaignID );
		
		response = assetService.get(client, filter);

		assertNotNull("Response should not be null.",response);
		assertTrue("Response Status should be True.",response.getStatus());
		
		return response;
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

		assertNotNull("Response should not be null.",response);
		assertTrue("Response Status should be True.",response.getStatus());
		
		return response.getResults().get(0);
	}
	
	protected void deleteCampaign(ETCampaign etObject) throws ETSdkException
	{
		ETServiceResponse<ETCampaign> response = campaignService.delete(client, etObject);
		
		assertNotNull("Response should not be null.",response);
		assertTrue("Response Status should be True.",response.getStatus());
	}

	protected List<ETCampaign> retrieveAllCampaigns() throws ETSdkException 
	{
		ETServiceResponse<ETCampaign> response = campaignService.get(client);

		assertNotNull("Response should not be null.",response);
		assertTrue("Response Status should be True.",response.getStatus());
		assertNotNull("",response.getResults());
		return response.getResults();
	}

	private void assertTrue( String msg, boolean arg)
	{
		collector.checkThat(msg, arg, CoreMatchers.equalTo(true));
	}
	
	private void assertNotNull(String msg, Object o )
	{
		collector.checkThat(msg, o != null, CoreMatchers.equalTo(true));
	}
	
	private void assertEquals(String msg, Object exp, Object actual)
	{
		collector.checkThat(msg, actual, CoreMatchers.equalTo(exp));
	}
}
