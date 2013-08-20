package com.exacttarget.fuelsdk.soap;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETCrudServiceTest;
import com.exacttarget.fuelsdk.ETFolderService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETComplexFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETLogicalOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETFolder;

public class ETFolderServiceTest extends ETCrudServiceTest<ETFolder>{

	@Before
	public void setUp()
	        throws ETSdkException
    {
		super.setUp();
		service = new ETFolderServiceImpl();
		filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST FOLDER NAME");
		filterUpdated = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST FOLDER NAME UPDATED");
		
		etObject = new ETFolder();
		((ETFolder)etObject).setName("TEST FOLDER NAME");
		((ETFolder)etObject).setDescription("TEST Folder Description");
		((ETFolder)etObject).setActive(true);
		((ETFolder)etObject).setContentType("DataExtension");
		((ETFolder)etObject).setEditable(true);
		
		ETServiceResponse<ETFolder> response = ((ETFolderService)service).get(client, new ETComplexFilter(
				new ETSimpleFilter("name",ETFilterOperators.EQUALS, "Data Extensions"),
				new ETSimpleFilter("contentType",ETFilterOperators.EQUALS, "DataExtension"),
				ETLogicalOperators.AND));
		
		
		
		ETFolder parent = response.getResults().get(0);
		((ETFolder)etObject).setParentFolder(parent);
	}

	@Override
	protected void TestPatch(ETFolder found) throws ETSdkException {
		found.setName("TEST FOLDER NAME UPDATED");
		
		((ETCrudService)service).patch(client,  found);
		
	}
}
