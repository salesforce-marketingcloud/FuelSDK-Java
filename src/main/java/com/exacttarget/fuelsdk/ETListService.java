//
// ETListService.java -
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
import com.exacttarget.fuelsdk.model.ETList;

public interface ETListService extends ETCrudService
{
    public ETResponse<ETList> get(ETClient client)
        throws ETSdkException;

	public ETResponse<ETList> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETResponse<ETList> post(ETClient client, ETList list)
	    	throws ETSdkException;

	public ETResponse<ETList> patch(ETClient client, ETList list)
			throws ETSdkException;

	public ETResponse<ETList> delete(ETClient client, ETList list)
	    	throws ETSdkException;
	
	public ETResponse<ETList> post(ETClient client, List<ETList> lists)
			throws ETSdkException;
	
	public ETResponse<ETList> patch(ETClient client, List<ETList> lists)
			throws ETSdkException;
	
	public ETResponse<ETList> delete(ETClient client, List<ETList> lists)
			throws ETSdkException;

}
