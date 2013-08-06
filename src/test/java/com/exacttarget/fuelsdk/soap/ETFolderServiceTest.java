package com.exacttarget.fuelsdk.soap;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.internal.DataFolder;
import com.exacttarget.fuelsdk.model.ETFolder;

public class ETFolderServiceTest extends ETServiceTest<ETFolder>{

	@Before
	public void setUp()
	        throws ETSdkException
    {
		super.setUp();
		service = new ETSubscriberServiceImpl();
		filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST FOLDER NAME");
		filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST FOLDER NAME UPDATED");
		
		etObject = new ETFolder();
		etObject.setName("TEST FOLDER NAME");
		etObject.setActive(true);
		etObject.setContentType("DataExtension");
		etObject.setEditable(true);
		
		ETFolder parent = new ETFolder();
		parent.setId(100);
		etObject.setParentFolder(parent);
	}

	@Override
	protected void TestPatch(ETFolder found) throws ETSdkException {
		found.setName("TEST FOLDER NAME UPDATED");
		
		service.patch(client,  found);
		
	}
}
