package com.exacttarget.fuelsdk.soap;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETCrudServiceTest;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETSubscriber;

public class ETSubscriberServiceTest extends ETCrudServiceTest<ETSubscriber> {

	@Before
	public void setUp()
	        throws ETSdkException
    {
		super.setUp();
		service = new ETSubscriberServiceImpl();
		filter = new ETSimpleFilter("emailAddress", ETFilterOperators.EQUALS, "ryanjlowetest99@gmail.com");
		filterUpdated = new ETSimpleFilter("subscriberKey", ETFilterOperators.EQUALS, "ryanjlowetest993@gmail.com");
		
		etObject = new ETSubscriber();
		((ETSubscriber)etObject).setEmailAddress("ryanjlowetest99@gmail.com");
		((ETSubscriber)etObject).setSubscriberKey("ryanjlowetest99@gmail.com");
		
	}

	@Override
	protected void TestPatch(ETSubscriber found) throws ETSdkException {
		
		found.setEmailAddress("ryanjlowetest993@gmail.com");
		
		((ETCrudService)service).patch(client, found);
		
	}
}
