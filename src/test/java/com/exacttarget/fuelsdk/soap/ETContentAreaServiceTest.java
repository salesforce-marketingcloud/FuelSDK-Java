package com.exacttarget.fuelsdk.soap;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceTest;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETContentArea;

public class ETContentAreaServiceTest extends ETServiceTest<ETContentArea> {

	@Before
	public void setUp()
	        throws ETSdkException
    {
		super.setUp();
		service = new ETContentAreaServiceImpl();
		filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST Content Area");
		filterUpdated = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST Content Area UPDATED");
		
		etObject = new ETContentArea();
		etObject.setName("TEST Content Area");
		etObject.setContent("TEST CONTENT AREA CONTENT");
	}

	@Override
	protected void TestPatch(ETContentArea found) throws ETSdkException {
		
		found.setName("TEST Content Area UPDATED");
		
		service.patch(client, found);
		
	}
}
