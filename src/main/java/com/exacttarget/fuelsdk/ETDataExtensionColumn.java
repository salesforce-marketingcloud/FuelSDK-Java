//
// This file is part of the Fuel Java SDK.
//
// Copyright (c) 2013, 2014, 2015, ExactTarget, Inc.
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
//
// * Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
//
// * Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// * Neither the name of ExactTarget, Inc. nor the names of its
// contributors may be used to endorse or promote products derived
// from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//

package com.exacttarget.fuelsdk;

import java.util.Date;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.DataExtensionField;

/**
 * An <code>ETDataExtensionColumn</code> object represents a
 * data extension column in the Salesforce Marketing Cloud.
 */

@SoapObject(internalType = DataExtensionField.class, unretrievable = {
    "ID", "Description", "DataExtension", "Precision"
})
public class ETDataExtensionColumn extends ETSoapObject {
    @ExternalName("id")
    private String id = null;
    @ExternalName("key")
    @InternalName("customerKey")
    private String key = null;
    @ExternalName("name")
    private String name = null;
    @ExternalName("description")
    private String description = null;
    @ExternalName("createdDate")
    private Date createdDate = null;
    @ExternalName("modifiedDate")
    private Date modifiedDate = null;
    @ExternalName("dataExtension")
    private ETDataExtension dataExtension = null;
    @ExternalName("type")
    @InternalName("fieldType")
    private Type type = null;
    @ExternalName("defaultValue")
    private String defaultValue = null;
    @ExternalName("isPrimaryKey")
    private Boolean isPrimaryKey = null;
    @ExternalName("isRequired")
    private Boolean isRequired = null;
    @ExternalName("length")
    @InternalName("maxLength")
    private Integer length = null;
    @ExternalName("precision")
    private Integer precision = null;
    @ExternalName("scale")
    private Integer scale = null;

    /** 
    * Class constructor, Initializes a new instance of the class.
    */    
    public ETDataExtensionColumn() {}

    /** 
    * @return The Identifier of the ETDataExtensionColumn object.
    */    
    @Override
    public String getId() {
        return id;
    }

    /** 
    * @param id     The Identifier of the ETDataExtensionColumn object.
    */    
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /** 
    * @return       The Customer Key of the ETDataExtensionColumn object.
    */     
    public String getKey() {
        return key;
    }

    /** 
    * @param key    The Customer Key of the ETDataExtensionColumn object.
    */      
    public void setKey(String key) {
        this.key = key;
    }

    /** 
    * @return     The name of the ETDataExtensionColumn object.
    */     
    public String getName() {
        return name;
    }

    /** 
    * @param name   The name of the ETDataExtensionColumn object.
    */     
    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    /** 
    * @return     The description of the ETDataExtensionColumn.
    */    
    public String getDescription() {
        return description;
    }

    /** 
    * @param description    The description of the ETDataExtensionColumn.
    */    
    public void setDescription(String description) {
        this.description = description;
    }

    /** 
    * @return     The created date of the ETDataExtensionColumn object.
    */
    public Date getCreatedDate() {
        return createdDate;
    }

    /** 
    * @param createdDate        The created date of the ETDataExtensionColumn object.
    */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /** 
    * @return     The modified date of the ETDataExtensionColumn object.
    */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /** 
    * @param modifiedDate       The modified date of the ETDataExtensionColumn object.
    */    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /** 
    * @return     The data extension of the ETDataExtensionColumn object.
    */
    public ETDataExtension getDataExtension() {
        return dataExtension;
    }

    /** 
    * @param dataExtension       The data extension of the ETDataExtensionColumn object.
    */    
    public void setDataExtension(ETDataExtension dataExtension) {
        this.dataExtension = dataExtension;
    }

    /** 
    * @return key       The type of the ETDataExtensionColumn object.
    * @see              Type
    */
    public Type getType() {
        return type;
    }

    /** 
    * @param type           The type of the ETDataExtensionColumn object.
    * @see                  Type
    */
    public void setType(Type type) {
        this.type = type;
    }

    /** 
    * @return     The default value of the ETDataExtensionColumn object.
    */
    public String getDefaultValue() {
        return defaultValue;
    }

    /** 
    * @param defaultValue       The default value of the ETDataExtensionColumn object.
    */    
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /** 
    * @return     true if the Data Extension Column is primary key, false otherwise.
    */
    public Boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    /** 
    * @param isPrimaryKey    true if the Data Extension Column is primary key, false otherwise.
    */    
    public void setIsPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    /** 
    * @return     true if the Data Extension Column is required, false otherwise.
    */
    public Boolean getIsRequired() {
        return isRequired;
    }

    /** 
    * @param isRequired    true if the Data Extension Column is required, false otherwise.
    */    
    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    /** 
    * @return     The length of the ETDataExtensionColumn.
    */
    public Integer getLength() {
        return length;
    }

    /** 
    * @param length    The length of the ETDataExtensionColumn.
    */    
    public void setLength(Integer length) {
        this.length = length;
    }

    /** 
    * @return     The precision of the ETDataExtensionColumn.
    */
    public Integer getPrecision() {
        return precision;
    }

    /** 
    * @param precision    The precision of the ETDataExtensionColumn.
    */    
    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    /** 
    * @return     The scale of the ETDataExtensionColumn.
    */
    public Integer getScale() {
        return scale;
    }

    /** 
    * @param scale    The scale of the ETDataExtensionColumn.
    */    
    public void setScale(Integer scale) {
        this.scale = scale;
    }

    /**
     * @deprecated
     * Use <code>getKey()</code>.
     */
    @Deprecated
    public String getCustomerKey() {
        return getKey();
    }

    /**
     * @deprecated
     * Use <code>setKey()</code>.
     */
    @Deprecated
    public void setCustomerKey(String customerKey) {
        setKey(customerKey);
    }

    /**
     *  Data Extension Column Types that can be used
     */
    public enum Type {
        BOOLEAN("Boolean"),
        DATE("Date"),
        DECIMAL("Decimal"),
        EMAIL_ADDRESS("EmailAddress"),
        LOCALE("Locale"),
        NUMBER("Number"),
        PHONE("Phone"),
        TEXT("Text");
        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        public static Type fromValue(String value) {
            for (Type v : Type.values()) {
                if (v.value.equals(value)) {
                    return v;
                }
            }
            throw new IllegalArgumentException(value);
        }
    }
}
