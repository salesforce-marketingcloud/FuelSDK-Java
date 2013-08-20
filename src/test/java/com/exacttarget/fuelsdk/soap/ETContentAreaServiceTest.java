package com.exacttarget.fuelsdk.soap;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETCrudServiceTest;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETContentArea;

public class ETContentAreaServiceTest extends ETCrudServiceTest<ETContentArea> {

	@Before
	public void setUp()
	        throws ETSdkException
    {
		super.setUp();
		service = new ETContentAreaServiceImpl();
		filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST Content Area");
		filterUpdated = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST Content Area UPDATED");
		
		etObject = new ETContentArea();
		((ETContentArea)etObject).setName("TEST Content Area");
		((ETContentArea)etObject).setContent("TEST CONTENT AREA CONTENT");
	}

	@Override
	protected void TestPatch(ETContentArea found) throws ETSdkException {
		
		found.setName("TEST Content Area UPDATED");
		
		((ETCrudService)service).patch(client, found);
		
	}
}
