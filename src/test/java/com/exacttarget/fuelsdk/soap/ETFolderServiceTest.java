package com.exacttarget.fuelsdk.soap;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETFolderService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.ETServiceTest;
import com.exacttarget.fuelsdk.ETSubscriberService;
import com.exacttarget.fuelsdk.filter.ETComplexFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETLogicalOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETFolder;

public class ETFolderServiceTest extends ETServiceTest<ETFolder>{

	@Before
	public void setUp()
	        throws ETSdkException
    {
		super.setUp();
		service = new ETFolderServiceImpl();
		filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST FOLDER NAME");
		filterUpdated = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST FOLDER NAME UPDATED");
		
		etObject = new ETFolder();
		etObject.setName("TEST FOLDER NAME");
		etObject.setDescription("TEST Folder Description");
		etObject.setActive(true);
		etObject.setContentType("DataExtension");
		etObject.setEditable(true);
		
		ETServiceResponse<ETFolder> response = ((ETFolderService)service).get(client, new ETComplexFilter(
				new ETSimpleFilter("name",ETFilterOperators.EQUALS, "Data Extensions"),
				new ETSimpleFilter("contentType",ETFilterOperators.EQUALS, "DataExtension"),
				ETLogicalOperators.AND));
		
		
		
		ETFolder parent = response.getResults().get(0);
		etObject.setParentFolder(parent);
	}

	@Override
	protected void TestPatch(ETFolder found) throws ETSdkException {
		found.setName("TEST FOLDER NAME UPDATED");
		
		service.patch(client,  found);
		
	}
}
