package com.exacttarget.fuelsdk.model.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.exacttarget.fuelsdk.internal.DataFolder;
import com.exacttarget.fuelsdk.internal.List;
import com.exacttarget.fuelsdk.model.ETFolder;
import com.exacttarget.fuelsdk.model.ETList;

import java.util.Arrays;

public class ObjectConverterTest {
    @Test
    public void testShouldConvertFromInternalObjectToETObject() throws Exception {
        final String listName = "Just a sample list";
        final String listDescription = "Great list description";
        final int listId = 123;

        List l = new List();
        l.setListName(listName);
        l.setDescription(listDescription);
        l.setID(listId);

        ETList out = ObjectConverter.convertToEtObject(l, ETList.class);
        assertNotNull(out);
        assertEquals(listName, out.getName());
        assertEquals(listDescription, out.getDescription());
        assertEquals(listId, out.getID().intValue());
    }

    @Test
    public void testShouldConvertFromETObjectToInternalObject() throws Exception {
        final String listName = "Just a sample list";
        final String listDescription = "Great list description";
        final int listId = 123;

        ETList l = new ETList();
        l.setName(listName);
        l.setDescription(listDescription);
        l.setID(listId);

        List out = ObjectConverter.convertFromEtObject(l, List.class);
        assertNotNull(out);
        assertEquals(listName, out.getListName());
        assertEquals(listDescription, out.getDescription());
        assertEquals(listId, out.getID().intValue());
    }

    @Test
    public void testShouldConvertComplexObjectFromETObjectToInternalObject() throws Exception {
        final String description = "Great list description";

        ETFolder parent = new ETFolder();
        parent.setID(2);

        ETFolder f = new ETFolder();
        f.setActive(true);
        f.setDescription(description);
        f.setID(1);
        f.setParentFolder(parent);

        DataFolder out = ObjectConverter.convertFromEtObject(f, DataFolder.class);
        assertNotNull(out);
        assertEquals(description, out.getDescription());
        assertSame(out.getParentFolder().getID(), f.getParentFolder().getID());
    }

    @Test
    public void testShouldFindSerializablePropertyNames() throws Exception {
        assertEquals(Arrays.asList("ListName", "Description"), ObjectConverter.findSerializablePropertyNames(ETList.class));
    }
}
