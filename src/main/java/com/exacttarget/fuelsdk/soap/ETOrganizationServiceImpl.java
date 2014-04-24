//
// This file is part of the Fuel Java SDK.
//
// Copyright (C) 2013, 2014 ExactTarget, Inc.
// All rights reserved.
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify,
// merge, publish, distribute, sublicense, and/or sell copies
// of the Software, and to permit persons to whom the Software
// is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
// KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
// OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
// OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
// OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
// THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

package com.exacttarget.fuelsdk.soap;

import java.util.List;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETOrganizationService;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETResult;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETOrganization;

public class ETOrganizationServiceImpl extends ETCrudServiceImpl<ETOrganization>
    implements ETOrganizationService
{
    public ETResponse<ETOrganization> get(ETClient client, String... properties)
        throws ETSdkException
    {
        return super.get(client, ETOrganization.class, properties);
    }

    public ETResponse<ETOrganization> get(ETClient client, ETFilter filter, String... properties)
        throws ETSdkException
    {
        return super.get(client, ETOrganization.class, filter, properties);
    }

    public ETResponse<ETResult> post(ETClient client, ETOrganization organization)
        throws ETSdkException
    {
        return super.post(client, organization);
    }

    public ETResponse<ETResult> post(ETClient client, List<ETOrganization> organizations)
        throws ETSdkException
    {
        return super.post(client, organizations);
    }

    public ETResponse<ETResult> patch(ETClient client, ETOrganization organization)
        throws ETSdkException
    {
        return super.patch(client, organization);
    }

    public ETResponse<ETResult> patch(ETClient client, List<ETOrganization> organizations)
        throws ETSdkException
    {
        return super.patch(client, organizations);
    }

    public ETResponse<ETResult> delete(ETClient client, ETOrganization organization)
        throws ETSdkException
    {
        return super.delete(client, organization);
    }

    public ETResponse<ETResult> delete(ETClient client, List<ETOrganization> organizations)
        throws ETSdkException
    {
        return super.delete(client, organizations);
    }
}
