package com.exacttarget.fuelsdk.rest;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceTest;
import com.exacttarget.fuelsdk.model.ETCampaign;

public class ETCampaignServiceTest extends ETServiceTest<ETCampaign>{

	@Before
	public void setUp()
	        throws ETSdkException
    {
		super.setUp();
		service = new ETCampaignServiceImpl();
		//TODO filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST FOLDER NAME");
		//TODO filterUpdated = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST FOLDER NAME UPDATED");
		
		etObject = new ETCampaign();
		// TODO Set these
	}
	
	@Override
	protected void TestPatch(ETCampaign found) throws ETSdkException {
		// TODO
		
	}

}
