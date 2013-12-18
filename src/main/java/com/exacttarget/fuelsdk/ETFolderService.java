//
// ETFolderService.java -
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
import com.exacttarget.fuelsdk.model.ETFolder;

public interface ETFolderService extends ETCrudService {

	public ETResponse<ETFolder> get(ETClient client)
	        throws ETSdkException;

	public ETResponse<ETFolder> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETResponse<ETFolder> post(ETClient client, ETFolder folder)
	    	throws ETSdkException;

	public ETResponse<ETFolder> patch(ETClient client, ETFolder folder)
			throws ETSdkException;

	public ETResponse<ETFolder> delete(ETClient client, ETFolder folder)
	    	throws ETSdkException;
	
	public ETResponse<ETFolder> post(ETClient client, List<ETFolder> folders)
	    	throws ETSdkException;

	public ETResponse<ETFolder> patch(ETClient client, List<ETFolder> folders)
			throws ETSdkException;

	public ETResponse<ETFolder> delete(ETClient client, List<ETFolder> folders)
	    	throws ETSdkException;

}
