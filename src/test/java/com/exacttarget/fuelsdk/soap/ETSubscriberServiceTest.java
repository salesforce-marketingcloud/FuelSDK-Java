package com.exacttarget.fuelsdk.soap;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETSubscriber;

public class ETSubscriberServiceTest extends ETServiceTest<ETSubscriber> {

	@Before
	public void setUp()
	        throws ETSdkException
    {
		super.setUp();
		service = new ETSubscriberServiceImpl();
		filter = new ETSimpleFilter("emailAddress", ETFilterOperators.EQUALS, "ryanjlowetest@gmail.com");
		etObject = new ETSubscriber();
		etObject.setEmailAddress("ryanjlowetest@gmail.com");
		etObject.setSubscriberKey("ryanjlowetest@gmail.com");
	}
}
