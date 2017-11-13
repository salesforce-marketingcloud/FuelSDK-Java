/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.ExtractDescription;
import com.exacttarget.fuelsdk.internal.ExtractParameterDescription;
import java.util.List;

/**
 * An <code>ETExtractDescription</code> object represents information describing a specific data extract in the Salesforce Marketing Cloud.
 */
@SoapObject(internalType = ExtractDescription.class)
public class ETExtractDescription extends ETSoapObject{

    @ExternalName("id")
    @InternalName("objectID")
    private String id = null;    

    @ExternalName("name")
    private String name;
    
    @ExternalName("parameters")
    private ExtractDescription.Parameters parameters;

    /**
     * @return the parameters
     */
    public ExtractDescription.Parameters getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(ExtractDescription.Parameters parameters) {
        this.parameters = parameters;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
