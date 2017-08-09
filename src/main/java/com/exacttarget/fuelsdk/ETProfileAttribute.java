/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.Attribute;
import com.exacttarget.fuelsdk.internal.CompressionConfiguration;

/**
 * This class defines any additional attribute for a subscriber.
 */

@SoapObject(internalType = Attribute.class)
public class ETProfileAttribute extends ETSoapObject {
    @ExternalName("id")
    private String id = null;
    @ExternalName("name")
    @InternalName("name")
    private String name;
    @ExternalName("value")
    @InternalName("value")
    private String value;
    @ExternalName("compression")
    @InternalName("compression")
    private CompressionConfiguration compression;


    /** 
    * Class constructor, Initializes a new instance of the class.
    */
    public ETProfileAttribute(){}
    
    /** 
    * Class constructor, Initializes a new instance of the class.
    * @param name   The name of the attribute
    * @param value  The value of the attribute
    */
    public ETProfileAttribute(String name, String value){
        setName(name);
        setValue(value);
    }

    /** 
    * @return The Identifier of the ETList.
    */
    @Override
    public String getId() {
        return this.id;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /** 
    * @param id     The Identifier of the ETList.
    */
    @Override
    public void setId(String id) {
        this.id = id;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the compression
     */
    public CompressionConfiguration getCompression() {
        return this.compression;
    }

    /**
     * @param compression the compression to set
     */
    public void setCompression(CompressionConfiguration compression) {
        this.compression = compression;
    }
    
}
