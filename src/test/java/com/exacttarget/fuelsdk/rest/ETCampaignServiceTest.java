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

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.hamcrest.CoreMatchers;
import org.junit.runners.MethodSorters;

import com.exacttarget.fuelsdk.ETCampaignService;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
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

    @Rule
    public ErrorCollector collector = new ErrorCollector();
	
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
			collector.addError(e);
		}
	}
	
	@Test
	public void TestOrderByParameters() 
	{
		logger.debug("TestOrderByParameters()");
		List<String> ids = new ArrayList<String>();
		
		try 
		{
			//Create 5 unique Campaigns
			for( int i=0;i<5;++i )
			{
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

			getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.Id_ASC );

			getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY.Id_DESC );
			
		} 
		catch (ETSdkException e) 
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
	public void TestPagingParameters() 
	{
		logger.debug("TestPagingParameters()");
		List<String> ids = new ArrayList<String>();
		
		try 
		{
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
			
		} 
		catch (ETSdkException e) 
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
	public void TestURLParameters() 
	{
		logger.debug("TestURLParameters()");
		List<String> ids = new ArrayList<String>();
		
		try 
		{
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

			//Query First page and 2 at a time
			//this should return 2 items and have more results
			getAllCampaignsWithPagingAndOrderby("1", "2", 2, true, ETCampaignService.ORDER_BY.Id_DESC);

			//Query Second page and 2 at a time
			//this should return 2 items and have more results
			getAllCampaignsWithPagingAndOrderby("2", "2", 2, true, ETCampaignService.ORDER_BY.Id_ASC);

			//Query Third page and 2 at a time
			//this should return 1 items and NOT have more results
			getAllCampaignsWithPagingAndOrderby("3", "2", 1, false, ETCampaignService.ORDER_BY.Id_DESC);
			
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
			
		} 
		catch (ETSdkException e) 
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
	public void TestDelete() {
		logger.debug("TestDelete()");
		
		try 
		{
			ETCampaign createdCampaign = createCampaign(TEST_CAMPAIGN_CODE);
			String createdCampaignID = createdCampaign.getId();
			ETFilter createdCampaignFilter = new ETSimpleFilter("id", ETFilterOperators.EQUALS, createdCampaignID);
			
			ETCampaign testCampaign1 = retrieveCampaign(createdCampaignFilter);
			
			collector.checkThat(TEST_CAMPAIGN_CODE, CoreMatchers.is(testCampaign1.getCampaignCode()));
			
			deleteCampaign(testCampaign1);
			
			//Validate campaign was deleted
			List<ETCampaign> campaigns = retrieveAllCampaigns();
			
			for( ETCampaign c: campaigns )
			{
				assertTrue("Campaign with ID: " + createdCampaignID + " should have been deleted and was present!", !createdCampaignID.equals(c.getId()));
			}
			
		} 
		catch (ETSdkException e) 
		{
			collector.addError(e);
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

			collector.checkThat(TEST_CAMPAIGN_CODE, CoreMatchers.is(testCampaign1.getCampaignCode()));
			
			deleteCampaign(testCampaign1);
			
		} 
		catch (ETSdkException e) 
		{
			collector.addError(e);
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
			assertNotNull("",testCampaign1);

			//TEST begin - update existing Campaign 
			testCampaign1.setCampaignCode(TEST_CAMPAIGN_CODE_PATCH);

			assertEquals("",TEST_CAMPAIGN_CODE_PATCH, testCampaign1.getCampaignCode());
			
			ETResponse<ETCampaign> response = service.patch(client, testCampaign1);
			assertNotNull("",response);
			assertTrue("",response.getStatus());
			assertNotNull("",response.getResults());
			assertEquals("",1, response.getResults().size());
			assertEquals("",TEST_CAMPAIGN_CODE_PATCH, response.getResults().get(0).getCampaignCode());
			
			ETCampaign testCampaign2 = retrieveCampaign(createdCampaignFilter);

			assertEquals("",TEST_CAMPAIGN_CODE_PATCH, testCampaign2.getCampaignCode());
			//Test end
			
			deleteCampaign(testCampaign2);
		} 
		catch (ETSdkException e) 
		{
			collector.addError(e);
		}
	}
	
	private void getAllCampaignsWithPagingAndOrderby(String page, String pageSize, int expectedNumOfItems, boolean hasMoreResults, ETCampaignService.ORDER_BY orderby ) throws ETSdkException
	{
		List<ETFilter> simpleFilters = new ArrayList<ETFilter>();
		ETComplexFilter filter = new ETComplexFilter();
		filter.setLeftOperand(new ETSimpleFilter(ETCampaignService.URL_PARAM.page.toString(), ETFilterOperators.EQUALS, page));
		filter.setRightOperand(new ETSimpleFilter(ETCampaignService.URL_PARAM.pageSize.toString(), ETFilterOperators.EQUALS, pageSize));
		
		simpleFilters.add(new ETSimpleFilter(ETCampaignService.URL_PARAM.orderBy.toString(),  ETFilterOperators.EQUALS,  orderby.toString()));

		filter.setAdditionalOperands(simpleFilters);
		
		ETResponse<ETCampaign> response = null;
		response = service.get(client, filter);
		assertNotNull("The call response.getResults() was expected to be NOT null:", response.getResults());
		assertEquals("Received Incorrect Number of of results: ", expectedNumOfItems, response.getResults().size());
		assertEquals("HasMoreResults: ", hasMoreResults, response.hasMoreResults());
		
		validateOrderBy(orderby, response.getResults());
	}

	
	private void getAllCampaignsWithPaging(String page, String pageSize, int expectedNumOfItems, boolean hasMoreResults ) throws ETSdkException
	{
		ETComplexFilter filter = new ETComplexFilter();
		filter.setLeftOperand(new ETSimpleFilter(ETCampaignService.URL_PARAM.page.toString(), ETFilterOperators.EQUALS, page));
		filter.setRightOperand(new ETSimpleFilter(ETCampaignService.URL_PARAM.pageSize.toString(), ETFilterOperators.EQUALS, pageSize));
		
		ETResponse<ETCampaign> response = service.get(client, filter);

		assertNotNull("The call response.getResults() was expected to be NOT null:", response.getResults());
		assertEquals("Received Incorrect Number of of results: ", expectedNumOfItems, response.getResults().size());
		assertEquals("HasMoreResults: ", hasMoreResults, response.hasMoreResults());
		assertTrue("Response Status should be True: ",response.getStatus());
	}

	private void getAllCampaignsWithOrderBy( ETCampaignService.ORDER_BY orderby ) throws ETSdkException
	{
		ETSimpleFilter filter = new ETSimpleFilter( ETCampaignService.URL_PARAM.orderBy.toString(),  ETFilterOperators.EQUALS,  orderby.toString() );
		
		ETResponse<ETCampaign> response = service.get(client, filter);

		assertNotNull("Reponse should be non-Null: ",response);
		
		if( !response.getStatus() )
		{
			assertNotNull("Reponse Message should be non-Null: ",response.getMessage());
			logger.error("Bad Response when calling orderBy=" + orderby.toString() + "- with message:" + response.getMessage());
			assertTrue("Bad Response when calling orderBy=" + orderby.toString() + "- with message:" + response.getMessage(),response.getStatus());
		}
		
		assertNotNull("Response Results should be non-Null:",response.getResults());
		assertTrue("Response Status should be True: ",response.getStatus());
		
		validateOrderBy(orderby, response.getResults());
	}

	private ETCampaign createCampaign(String campaign) throws ETSdkException 
	{
		ETCampaign etObject = new ETCampaign();
		etObject.setName("NAME-"+campaign);
		etObject.setDescription("DESCRIPTION-"+campaign);
		etObject.setCampaignCode(campaign);
		etObject.setColor("000fff");
		etObject.setFavorite(false);
		
		ETResponse<ETCampaign> response =  service.post(client, etObject);
		
		assertNotNull("Reponse should be non-Null: ",response);
		assertTrue("Response Status should be True: ",response.getStatus());
		
		return response.getResults().get(0);
	}
	
	protected List<ETCampaign> retrieveAllCampaigns() throws ETSdkException 
	{
		ETResponse<ETCampaign> response = service.get(client);

		assertNotNull("Reponse should be non-Null: ",response);
		assertTrue("Response Status should be True: ",response.getStatus());
		assertNotNull("Response Results should be non-Null:",response.getResults());
		return response.getResults();
	}
	
	protected ETCampaign retrieveCampaign(ETFilter f) throws ETSdkException 
	{
		ETResponse<ETCampaign> response = service.get(client, f);
		assertNotNull("Reponse should be non-Null: ",response);
		assertTrue("Response Status should be True: ",response.getStatus());
		assertNotNull("Response Results should be non-Null:",response.getResults());
		assertEquals("Recieved incorrect number of Results: ",1,response.getResults().size());
		assertNotNull("",response.getResults().get(0));
		return response.getResults().get(0);
	}
	
	protected void deleteCampaign(ETCampaign etObject) throws ETSdkException
	{
		ETResponse<ETCampaign> response = service.delete(client, etObject);
		
		assertNotNull("Reponse should be non-Null: ",response);
		assertTrue("Response Status should be True: ",response.getStatus());
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
				assertTrue("Name ASC: previous name should be alfabatized before current name: ", prevCampaign.getName().compareTo(c.getName()) < 0);
				break;
			case Name_DESC:
				assertTrue("",prevCampaign.getName().compareTo(c.getName()) > 0);
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
				assertTrue("",prevCampaign.getCampaignCode().compareTo(c.getCampaignCode()) < 0);
				break;
			case CampaignCode_DESC:
				assertTrue("",prevCampaign.getCampaignCode().compareTo(c.getCampaignCode()) > 0);
				break;
			case Id_ASC:
				assertTrue("",prevCampaign.getId().compareTo(c.getId()) < 0);
				break;
			case Id_DESC:
				assertTrue("",prevCampaign.getId().compareTo(c.getId()) > 0);
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
				assertTrue("Previous date ("+ pDate +") should've happened before Current Date ("+ cDate +").", pDate.before(cDate));
			}
			else
			{
				logger.debug("Previous date ("+ pDate +") should've happened after Current Date ("+ cDate +").");
				assertTrue("Previous date ("+ pDate +") should've happened after Current Date ("+ cDate +").", pDate.after(cDate));
			}
		} 
		catch (ParseException e) 
		{
			throw new ETSdkException("Date format should be: " + dateFormat, e);
		}
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
