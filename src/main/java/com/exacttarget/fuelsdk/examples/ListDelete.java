//
// ListDelete.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.examples;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETListService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.model.ETList;
import com.exacttarget.fuelsdk.model.ETListType;

public class ListDelete {
    private static final String NAME = ListDelete.class.getSimpleName();
    private static final String[] ARGS = { "name" };

    public static void main(String[] args)
        throws ETSdkException
    {
        if (args.length != ARGS.length) {
            System.out.print("Usage: " + NAME);
            for (String arg : ARGS) {
                System.out.print(" " + arg);
            }
            System.out.println();
            System.exit(1);
        }

        String name = args[0];

        ETConfiguration configuration = new ETConfiguration();

        ETClient client = new ETClient(configuration);

        ETListService service = client.getListService();

        ETList list = new ETList();
        list.setName(name);
        list.setListType(ETListType.PUBLIC);

        service.delete(client, list);
    }
}
