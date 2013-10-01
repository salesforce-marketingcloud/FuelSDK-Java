//
// ETCampaignServiceTest.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETCampaignServiceTest{

	private static final String TEST_CAMPAIGN_CODE_PATCH = "TestCode_PATCH";
	private static final String TEST_CAMPAIGN_CODE = "TestCode";
	protected static Logger logger = Logger.getLogger(ETCampaignServiceTest.class);
	protected static ETCampaignService service;
	protected static ETClient client = null;
	protected static ETConfiguration configuration = null;
	
	
	@BeforeClass
	public static void setUp() throws ETSdkException {
		logger.debug("SetUp");
		configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
		
		service = new ETCampaignServiceImpl();
	}
	
	@Test
	public void TestClean() {
		logger.debug("TestClean()");
		
		try {
			
			logger.debug("TestRetrieve");
			
			List<ETCampaign> campaigns = retrieveAllCampaigns();

			logger.debug("Received Count during clean: " + campaigns.size());
			
			for( ETCampaign c: campaigns )
			{
				logger.debug("Received during Clean: " + c);
				if( c.getCampaignCode().startsWith(TEST_CAMPAIGN_CODE) || TEST_CAMPAIGN_CODE_PATCH.equals(c.getCampaignCode()))
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
	public void TestOrderByParameters() 
	{
		try 
		{
			logger.debug("TestOrderByParameters()");
			List<String> ids = new ArrayList<String>();
			
			//Create 5 unique Campaigns
			for( int i=0;i<5;++i )
			{
				TimeUnit.SECONDS.sleep(1);
				ETCampaign c = createCampaign(TEST_CAMPAIGN_CODE + i);
				ids.add(c.getId());
			}
			
			getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.Name_ASC );
			
			getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.Name_DESC );

			getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.ModifiedDate_ASC );

			getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.ModifiedDate_DESC );

			getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.CreatedDate_ASC );

			getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.CreatedDate_DESC );

			getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.CampaignCode_ASC );

			getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.CampaignCode_DESC );

			//TODO: turn back on when ID issue is fixed?
			//getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.Id_ASC.toString() );

			//getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.Id_DESC.toString() );
			
			//Delete all created Campaigns (cleanup)
			for( String id: ids )
			{
				ETCampaign c = new ETCampaign();
				c.setId( id );
				deleteCampaign(c);
			}
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestPagingParameters() 
	{
		try 
		{
			logger.debug("TestPagingParameters()");
			List<String> ids = new ArrayList<String>();
			
			//Create 5 unique Campaigns
			for( int i=5;i<10;++i )
			{
				ETCampaign c = createCampaign(TEST_CAMPAIGN_CODE + i);
				ids.add(c.getId());
			}
			
			//Query First page and 2 at a time
			//this should return 2 items and have more results
			getAllCampaignsWithPaging("1", "2", 2, true);

			//Query Second page and 2 at a time
			//this should return 2 items and have more results
			getAllCampaignsWithPaging("2", "2", 2, true);

			//Query Third page and 2 at a time
			//this should return 1 items and NOT have more results
			getAllCampaignsWithPaging("3", "2", 1, false);
			
			// --
			
			//Query First page and 3 at a time
			//this should return 3 items and have more results
			getAllCampaignsWithPaging("1", "3", 3, true);

			//Query Second page and 3 at a time
			//this should return 2 items and NOT have more results
			getAllCampaignsWithPaging("2", "3", 2, false);
			
			// --
			
			//Query First page and 5 at a time
			//this should return 5 items and NOT have more results
			getAllCampaignsWithPaging("1", "5", 5, false);
			
			//Delete all created Campaigns (cleanup)
			for( String id: ids )
			{
				ETCampaign c = new ETCampaign();
				c.setId( id );
				deleteCampaign(c);
			}
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestURLParameters() 
	{
		try 
		{
			logger.debug("TestURLParameters()");
			List<String> ids = new ArrayList<String>();
			
			//Create 5 unique Campaigns
			for( int i=10;i<15;++i )
			{
				ETCampaign c = createCampaign(TEST_CAMPAIGN_CODE + i);
				ids.add(c.getId());
			}
			
			//Query First page and 2 at a time
			//this should return 2 items and have more results
			getAllCampaignsWithPagingAndOrderby("1", "2", 2, true, ETCampaignService.ORDER_BY.Name_ASC);

			//Query Second page and 2 at a time
			//this should return 2 items and have more results
			getAllCampaignsWithPagingAndOrderby("2", "2", 2, true, ETCampaignService.ORDER_BY.Name_DESC);

			//Query Third page and 2 at a time
			//this should return 1 items and NOT have more results
			getAllCampaignsWithPagingAndOrderby("3", "2", 1, false, ETCampaignService.ORDER_BY.Name_ASC);

			// --

			//Query First page and 2 at a time
			//this should return 2 items and have more results
			getAllCampaignsWithPagingAndOrderby("1", "2", 2, true, ETCampaignService.ORDER_BY.ModifiedDate_ASC);

			//Query Second page and 2 at a time
			//this should return 2 items and have more results
			getAllCampaignsWithPagingAndOrderby("2", "2", 2, true, ETCampaignService.ORDER_BY.ModifiedDate_DESC);

			//Query Third page and 2 at a time
			//this should return 1 items and NOT have more results
			getAllCampaignsWithPagingAndOrderby("3", "2", 1, false, ETCampaignService.ORDER_BY.ModifiedDate_ASC);

			// --

			//TODO: turn back on when ID issue is fixed?
			
			//Query First page and 2 at a time
			//this should return 2 items and have more results
			//getAllCampaignsWithPagingAndOrderby("1", "2", 2, true, ETCampaignService.ORDER_BY.Id_DESC);

			//Query Second page and 2 at a time
			//this should return 2 items and have more results
			//getAllCampaignsWithPagingAndOrderby("2", "2", 2, true, ETCampaignService.ORDER_BY.Id_ASC);

			//Query Third page and 2 at a time
			//this should return 1 items and NOT have more results
			//getAllCampaignsWithPagingAndOrderby("3", "2", 1, false, ETCampaignService.ORDER_BY.Id_DESC);
			
			// --
			
			//Query First page and 3 at a time
			//this should return 3 items and have more results
			getAllCampaignsWithPagingAndOrderby("1", "3", 3, true, ETCampaignService.ORDER_BY.CampaignCode_ASC);

			//Query Second page and 3 at a time
			//this should return 2 items and NOT have more results
			getAllCampaignsWithPagingAndOrderby("2", "3", 2, false, ETCampaignService.ORDER_BY.CampaignCode_DESC);

			// --
			
			//Query First page and 3 at a time
			//this should return 3 items and have more results
			getAllCampaignsWithPagingAndOrderby("1", "3", 3, true, ETCampaignService.ORDER_BY.CreatedDate_ASC);

			//Query Second page and 3 at a time
			//this should return 2 items and NOT have more results
			getAllCampaignsWithPagingAndOrderby("2", "3", 2, false, ETCampaignService.ORDER_BY.CreatedDate_DESC);
			
			// --
			
			//Query First page and 5 at a time
			//this should return 5 items and NOT have more results
			getAllCampaignsWithPagingAndOrderby("1", "5", 5, false, ETCampaignService.ORDER_BY.ModifiedDate_ASC);
			
			// --
			
			//Query First page and 5 at a time
			//this should return 5 items and NOT have more results
			getAllCampaignsWithPagingAndOrderby("1", "5", 5, false, ETCampaignService.ORDER_BY.ModifiedDate_DESC);
			
			//Delete all created Campaigns (cleanup)
			for( String id: ids )
			{
				ETCampaign c = new ETCampaign();
				c.setId( id );
				deleteCampaign(c);
			}
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void TestDelete() {
		logger.debug("TestDelete()");
		
		try 
		{
			ETCampaign createdCampaign = createCampaign(TEST_CAMPAIGN_CODE);
			String createdCampaignID = createdCampaign.getId();
			ETFilter createdCampaignFilter = new ETSimpleFilter("id", ETFilterOperators.EQUALS, createdCampaignID);
			
			ETCampaign testCampaign1 = retrieveCampaign(createdCampaignFilter);
			
			Assert.assertEquals(TEST_CAMPAIGN_CODE, testCampaign1.getCampaignCode());
			
			deleteCampaign(testCampaign1);
			
			//Validate campaign was deleted
			List<ETCampaign> campaigns = retrieveAllCampaigns();
			
			for( ETCampaign c: campaigns )
			{
				if( createdCampaignID.equals(c.getId()) )
				{
					Assert.fail("Campaign with ID: " + createdCampaignID + " should have been deleted and was present!");
				}
			}
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void TestPost()
	{
		logger.debug("TestPost()");

		try 
		{
			ETCampaign createdCampaign = createCampaign(TEST_CAMPAIGN_CODE);
			String createdCampaignID = createdCampaign.getId();
			ETFilter createdCampaignFilter = new ETSimpleFilter("id", ETFilterOperators.EQUALS, createdCampaignID);
			
			ETCampaign testCampaign1 = retrieveCampaign(createdCampaignFilter);
			
			Assert.assertEquals(TEST_CAMPAIGN_CODE, testCampaign1.getCampaignCode());
			
			deleteCampaign(testCampaign1);
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestPatch() throws ETSdkException {
		logger.debug("TestPatch()");
		
		try {
			ETCampaign createdCampaign = createCampaign(TEST_CAMPAIGN_CODE);
			String createdCampaignID = createdCampaign.getId();
			ETFilter createdCampaignFilter = new ETSimpleFilter("id", ETFilterOperators.EQUALS, createdCampaignID);
			
			ETCampaign testCampaign1 = retrieveCampaign(createdCampaignFilter);
			Assert.assertNotNull(testCampaign1);

			//TEST begin - update existing Campaign 
			testCampaign1.setCampaignCode(TEST_CAMPAIGN_CODE_PATCH);

			Assert.assertEquals(TEST_CAMPAIGN_CODE_PATCH, testCampaign1.getCampaignCode());
			
			ETServiceResponse<ETCampaign> response = service.patch(client, testCampaign1);
			Assert.assertNotNull(response);
			Assert.assertTrue(response.getStatus());
			Assert.assertNotNull(response.getResults());
			Assert.assertEquals(1, response.getResults().size());
			Assert.assertEquals(TEST_CAMPAIGN_CODE_PATCH, response.getResults().get(0).getCampaignCode());
			
			ETCampaign testCampaign2 = retrieveCampaign(createdCampaignFilter);
			Assert.assertEquals(TEST_CAMPAIGN_CODE_PATCH, testCampaign2.getCampaignCode());
			//Test end
			
			deleteCampaign(testCampaign2);
			
		} catch (ETSdkException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	private void getAllCampaignsWithPagingAndOrderby(String page, String pageSize, int expectedNumOfItems, boolean hasMoreResults, ETCampaignService.ORDER_BY orderby ) throws ETSdkException
	{
		List<ETFilter> simpleFilters = new ArrayList<ETFilter>();
		ETComplexFilter filter = new ETComplexFilter();
		simpleFilters.add(new ETSimpleFilter(ETCampaignService.URL_PARAM.page.toString(), ETFilterOperators.EQUALS, page));
		simpleFilters.add(new ETSimpleFilter(ETCampaignService.URL_PARAM.pageSize.toString(), ETFilterOperators.EQUALS, pageSize));
		simpleFilters.add(new ETSimpleFilter(ETCampaignService.URL_PARAM.orderBy.toString(),  ETFilterOperators.EQUALS,  orderby.toString()));
		
		//TODO: cool to use Additional Operands? ignor left/right?
		filter.setAdditionalOperands(simpleFilters);
		
		ETServiceResponse<ETCampaign> response = null;
		response = service.get(client, filter);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(expectedNumOfItems, response.getResults().size());
		Assert.assertEquals(response.hasMoreResults(), hasMoreResults);
		
		validateOrderBy(orderby, response.getResults());
	}

	
	private void getAllCampaignsWithPaging(String page, String pageSize, int expectedNumOfItems, boolean hasMoreResults ) throws ETSdkException
	{
		List<ETFilter> simpleFilters = new ArrayList<ETFilter>();
		ETComplexFilter filter = new ETComplexFilter();
		simpleFilters.add(new ETSimpleFilter(ETCampaignService.URL_PARAM.page.toString(), ETFilterOperators.EQUALS, page));
		simpleFilters.add(new ETSimpleFilter(ETCampaignService.URL_PARAM.pageSize.toString(), ETFilterOperators.EQUALS, pageSize));
		
		//TODO: cool to use Additional Operands? ignor left/right?
		filter.setAdditionalOperands(simpleFilters);
		
		ETServiceResponse<ETCampaign> response = null;
		response = service.get(client, filter);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(expectedNumOfItems, response.getResults().size());
		Assert.assertEquals(response.hasMoreResults(), hasMoreResults);
	}

	private void getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY orderby ) throws ETSdkException
	{
		ETSimpleFilter filter = new ETSimpleFilter( ETCampaignService.URL_PARAM.orderBy.toString(),  ETFilterOperators.EQUALS,  orderby.toString() );
		
		ETServiceResponse<ETCampaign> response = null;
		response = service.get(client, filter);
		
		Assert.assertNotNull(response.getResults());
		
		validateOrderBy(orderby, response.getResults());
	}

	private ETCampaign createCampaign(String campaign) throws ETSdkException {
		ETCampaign etObject = new ETCampaign();
		etObject.setName("NAME-"+campaign);
		etObject.setDescription("DESCRIPTION-"+campaign);
		etObject.setCampaignCode(campaign);
		etObject.setColor("000fff");
		etObject.setFavorite(false);
		
		ETServiceResponse<ETCampaign> response =  service.post(client, etObject);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		return response.getResults().get(0);
	}
	
	protected List<ETCampaign> retrieveAllCampaigns() throws ETSdkException {
		ETServiceResponse<ETCampaign> response = service.get(client);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		return response.getResults();
	}
	
	protected ETCampaign retrieveCampaign(ETFilter f) throws ETSdkException {
		ETServiceResponse<ETCampaign> response = service.get(client, f);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1,response.getResults().size());
		Assert.assertNotNull(response.getResults().get(0));
		return response.getResults().get(0);
	}
	
	protected void deleteCampaign(ETCampaign etObject) throws ETSdkException
	{
		ETServiceResponse<ETCampaign> response = service.delete(client, etObject);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
	}

	private void validateOrderBy(ETCampaignService.ORDER_BY orderby, List<ETCampaign> results) throws ETSdkException 
	{
		ETCampaign prevCampaign = null;
		for( ETCampaign c: results )
		{
			if( prevCampaign == null )
			{
				prevCampaign = c;
				continue;
			}
			
			switch (orderby) 
			{
			case Name_ASC:
				Assert.assertTrue(prevCampaign.getName().compareTo(c.getName()) < 0);
				break;
			case Name_DESC:
				Assert.assertTrue(prevCampaign.getName().compareTo(c.getName()) > 0);
				break;
			case ModifiedDate_ASC:
				compareDate(prevCampaign.getModifiedDate(), c.getModifiedDate(), true);
				break;
			case ModifiedDate_DESC:
				compareDate(prevCampaign.getModifiedDate(), c.getModifiedDate(), false);
				break;
			case CreatedDate_ASC:
				compareDate(prevCampaign.getCreatedDate(), c.getCreatedDate(), true);
				break;
			case CreatedDate_DESC:
				compareDate(prevCampaign.getCreatedDate(), c.getCreatedDate(), false);
				break;
			case CampaignCode_ASC:
				Assert.assertTrue(prevCampaign.getCampaignCode().compareTo(c.getCampaignCode()) < 0);
				break;
			case CampaignCode_DESC:
				Assert.assertTrue(prevCampaign.getCampaignCode().compareTo(c.getCampaignCode()) > 0);
				break;
			case Id_ASC:
				Assert.assertTrue(prevCampaign.getId().compareTo(c.getId()) < 0);
				break;
			case Id_DESC:
				Assert.assertTrue(prevCampaign.getId().compareTo(c.getId()) > 0);
				break;
			default:
				break;
			}
		}
	}
	
	private void compareDate(String prevDate, String currentDate, boolean ascend) throws ETSdkException 
	{
		String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		
		try 
		{
			Date pDate = formatter.parse(prevDate);
			Date cDate = formatter.parse(currentDate);
			
			if( ascend )
			{
				logger.debug("ASC PrevDate: " + pDate + " : Current Date: " + cDate);
				//TODO: turn back on when DATE issue is fixed?
				//Assert.assertTrue( prevDate.before(date) );
			}
			else
			{
				logger.debug("DESC PrevDate: " + pDate + " : Current Date: " + cDate);
				//TODO: turn back on when DATE issue is fixed?
				//Assert.assertTrue( prevDate.after(date) );
			}
			
		} 
		catch (ParseException e) 
		{
			throw new ETSdkException("Date format should be: " + dateFormat, e);
		}
	}
	
}
