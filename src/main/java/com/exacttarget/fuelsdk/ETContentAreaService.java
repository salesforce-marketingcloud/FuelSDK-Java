//
// ETContentAreaService.java -
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
import com.exacttarget.fuelsdk.model.ETContentArea;

public interface ETContentAreaService extends ETCrudService {

	public ETResponse<ETContentArea> get(ETClient client)
	        throws ETSdkException;

	public ETResponse<ETContentArea> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETResponse<ETContentArea> post(ETClient client, ETContentArea contentArea)
	    	throws ETSdkException;

    public ETResponse<ETContentArea> patch(ETClient client, ETContentArea contentArea)
    		throws ETSdkException;

    public ETResponse<ETContentArea> delete(ETClient client, ETContentArea contentArea)
	    	throws ETSdkException;
    
    public ETResponse<ETContentArea> post(ETClient client, List<ETContentArea> contentAreas)
			throws ETSdkException;
	
	public ETResponse<ETContentArea> patch(ETClient client, List<ETContentArea> contentAreas)
			throws ETSdkException;
	
	public ETResponse<ETContentArea> delete(ETClient client, List<ETContentArea> contentAreas)
			throws ETSdkException;
}

