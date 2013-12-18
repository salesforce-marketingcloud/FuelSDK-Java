//
// ETDataExtensionRowService.java -
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
import com.exacttarget.fuelsdk.model.ETDataExtensionRow;

public interface ETDataExtensionRowService extends ETCrudService {

	public ETResponse<ETDataExtensionRow> get(ETClient client, String dataExtensionCustomerKey, List<String> columns)
			throws ETSdkException;

	public ETResponse<ETDataExtensionRow> get(ETClient client, String dataExtensionCustomerKey, List<String> columns, ETFilter filter)
	        throws ETSdkException;

	public ETResponse<ETDataExtensionRow> post(ETClient client, ETDataExtensionRow row)
	    	throws ETSdkException;

    public ETResponse<ETDataExtensionRow> patch(ETClient client, ETDataExtensionRow row)
    		throws ETSdkException;

    public ETResponse<ETDataExtensionRow> delete(ETClient client, ETDataExtensionRow row)
	    	throws ETSdkException;
    
	public ETResponse<ETDataExtensionRow> post(ETClient client, List<ETDataExtensionRow> rows)
	    	throws ETSdkException;

    public ETResponse<ETDataExtensionRow> patch(ETClient client, List<ETDataExtensionRow> rows)
    		throws ETSdkException;

    public ETResponse<ETDataExtensionRow> delete(ETClient client, List<ETDataExtensionRow> rows)
	    	throws ETSdkException;
}
