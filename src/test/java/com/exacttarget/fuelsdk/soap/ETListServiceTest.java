package com.exacttarget.fuelsdk.soap;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETCrudServiceTest;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETList;
import com.exacttarget.fuelsdk.model.ETListType;

public class ETListServiceTest extends ETCrudServiceTest<ETList> {
	
	@Before
	public void setUp()
	        throws ETSdkException
    {
		super.setUp();
		service = new ETContentAreaServiceImpl();
		filter = new ETSimpleFilter("listName", ETFilterOperators.EQUALS, "TEST LIST FROM SDK");
		filterUpdated = new ETSimpleFilter("listName", ETFilterOperators.EQUALS, "TEST LIST FROM SDK UPDATED");
		etObject = new ETList();
		((ETList)etObject).setName("TEST LIST FROM SDK");
		((ETList)etObject).setListType(ETListType.PUBLIC);
	}

	@Override
	protected void TestPatch(ETList found) throws ETSdkException {
		found.setName("TEST LIST FROM SDK UPDATED");
		
		((ETCrudService)service).patch(client, found);
		
	}
}
