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

import java.lang.reflect.Method;
import java.util.List;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.deprecated.ETResponse;
import com.exacttarget.fuelsdk.deprecated.ETResult;
import com.exacttarget.fuelsdk.filter.ETComplexFilter;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;

@Deprecated
public abstract class ETSoapServiceImpl<T extends ETSoapObject> {
    //
    // This class is kinda nasty, but we really care about backward
    // compatibility.
    //
    // We have to use reflection to get access to the create,
    // retrieve, update, and delete methods because they are protected
    // and we are in a different package. NOTE ALSO that we use BOTH
    // the new ETFilter (com.exacttarget.fuelsdk.ETFilter) AND the old
    // ETFilter (com.exacttarget.fuelsdk.filter.ETFilter). We ALWAYS
    // reference the new ETFilter with its fully qualified class name.
    //

    private Method create = null;
    private Method retrieve = null;
    private Method update = null;
    private Method delete = null;

    public ETSoapServiceImpl()
        throws ETSdkException
    {
        try {
            create = ETSoapObject.class.getDeclaredMethod("create",
                                                          ETClient.class,
                                                          List.class);
        } catch (Exception ex) {
            throw new ETSdkException("could not get create method of "
                                     + ETSoapObject.class, ex);
        }

        try {
            retrieve = ETSoapObject.class.getDeclaredMethod("retrieve",
                                                            ETClient.class,  // client
                                                            com.exacttarget.fuelsdk.ETFilter.class,  // filter
                                                            Integer.class,   // page
                                                            Integer.class,   // pageSize
                                                            Class.class,     // type
                                                            String[].class); // properties
        } catch (Exception ex) {
            throw new ETSdkException("could not get retrieve method of "
                                     + ETSoapObject.class, ex);
        }

        try {
            update = ETSoapObject.class.getDeclaredMethod("update",
                                                          ETClient.class,
                                                          List.class);
        } catch (Exception ex) {
            throw new ETSdkException("could not get update method of "
                                     + ETSoapObject.class, ex);
        }

        try {
            delete = ETSoapObject.class.getDeclaredMethod("delete",
                                                          ETClient.class,
                                                          List.class);
        } catch (Exception ex) {
            throw new ETSdkException("could not get delete method of "
                                     + ETSoapObject.class, ex);
        }

        // so we can call the methods even though they're protected
        create.setAccessible(true);
        retrieve.setAccessible(true);
        update.setAccessible(true);
        delete.setAccessible(true);
    }

    @SuppressWarnings("unchecked")
    protected ETResponse<ETResult> create(ETClient client,
                                          Class<T> type,
                                          List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = null;

        try {
            // first argument of null means method is static
            response = (ETResponse<ETResult>) create.invoke(null,
                                                            client,
                                                            objects);
        } catch (Exception ex) {
            throw new ETSdkException("could not invoke create method on class " + type, ex);
        }

        return response;
    }

    @SuppressWarnings("unchecked")
    protected ETResponse<T> retrieve(ETClient client,
                                     Class<T> type,
                                     ETFilter filter,
                                     String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = null;

        //
        // Convert filter:
        //

        com.exacttarget.fuelsdk.ETFilter f = toNewFilter(filter);

        try {
            // first argument of null means method is static
            response = (ETResponse<T>) retrieve.invoke(null,
                                                       client,
                                                       f,
                                                       null,
                                                       null,
                                                       type,
                                                       properties);
        } catch (Exception ex) {
            throw new ETSdkException("could not invoke retrieve method on class " + type, ex);
        }

        return response;
    }

    @SuppressWarnings("unchecked")
    protected ETResponse<ETResult> update(ETClient client,
                                          Class<T> type,
                                          List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = null;

        try {
            // first argument of null means method is static
            response = (ETResponse<ETResult>) update.invoke(null,
                                                            client,
                                                            objects);
        } catch (Exception ex) {
            throw new ETSdkException("could not invoke update method on class " + type, ex);
        }

        return response;
    }

    @SuppressWarnings("unchecked")
    protected ETResponse<ETResult> delete(ETClient client,
                                          Class<T> type,
                                          List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = null;

        try {
            // first argument of null means method is static
            response = (ETResponse<ETResult>) delete.invoke(null,
                                                            client,
                                                            objects);
        } catch (Exception ex) {
            throw new ETSdkException("could not invoke delete method on class " + type, ex);
        }

        return response;
    }

    private com.exacttarget.fuelsdk.ETFilter toNewFilter(ETFilter filter) {
        com.exacttarget.fuelsdk.ETFilter f = new com.exacttarget.fuelsdk.ETFilter();
        if (filter instanceof ETSimpleFilter) {
            f.setProperty(((ETSimpleFilter) filter).getProperty());
            f.setOperator(((ETSimpleFilter) filter).getOperator().toString());
            for (String value : ((ETSimpleFilter) filter).getValues()) {
                f.addValue(value);
            }
        } else if (filter instanceof ETComplexFilter) {
            f.setOperator(((ETComplexFilter) filter).getOperator().toString());
            f.addFilter(toNewFilter(((ETComplexFilter) filter).getLeftOperand()));
            f.addFilter(toNewFilter(((ETComplexFilter) filter).getRightOperand()));
        }
        return f;
    }
}
