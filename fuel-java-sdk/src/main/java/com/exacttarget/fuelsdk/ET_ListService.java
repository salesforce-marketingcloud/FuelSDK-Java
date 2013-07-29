//
// ET_ListService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

public interface ET_ListService extends ET_CrudService<ET_List>
{
    public ET_ServiceResponse<ET_List> get(ET_Client client);
}
