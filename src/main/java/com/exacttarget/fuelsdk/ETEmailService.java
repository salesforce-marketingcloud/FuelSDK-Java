//
// ETEmailService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import java.util.List;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETEmail;

public interface ETEmailService extends ETCrudService {

	public ETResponse<ETEmail> get(ETClient client)
	        throws ETSdkException;

	public ETResponse<ETEmail> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETResponse<ETEmail> post(ETClient client, ETEmail email)
	    	throws ETSdkException;

	public ETResponse<ETEmail> patch(ETClient client, ETEmail email)
			throws ETSdkException;

	public ETResponse<ETEmail> delete(ETClient client, ETEmail email)
	    	throws ETSdkException;
	
	public ETResponse<ETEmail> post(ETClient client, List<ETEmail> emails)
	    	throws ETSdkException;

	public ETResponse<ETEmail> patch(ETClient client, List<ETEmail> emails)
			throws ETSdkException;

	public ETResponse<ETEmail> delete(ETClient client, List<ETEmail> emails)
	    	throws ETSdkException;
}
