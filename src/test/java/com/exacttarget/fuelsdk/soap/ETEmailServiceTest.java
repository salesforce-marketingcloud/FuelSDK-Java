package com.exacttarget.fuelsdk.soap;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETEmail;

public class ETEmailServiceTest extends ETServiceTest<ETEmail> {

	@Before
	public void setUp()
	        throws ETSdkException
    {
		super.setUp();
		service = new ETContentAreaServiceImpl();
		filter = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST EMAIL");
		filterUpdated = new ETSimpleFilter("name", ETFilterOperators.EQUALS, "TEST EMAIL UPDATED");
		etObject = new ETEmail();
		etObject.setName("TEST EMAIL");
		etObject.setHtmlBody("<html><body>YEP</body></html>");
		etObject.setSubject("TEST EMAIL SUBJECT");
	}

	@Override
	protected void TestPatch(ETEmail found) throws ETSdkException {
		
		found.setName("TEST EMAIL UPDATED");
		service.patch(client, found);
		
	}
}
