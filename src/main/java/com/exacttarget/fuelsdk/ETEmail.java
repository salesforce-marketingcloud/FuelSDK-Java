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
import com.exacttarget.fuelsdk.internal.Email;

/**
 * <p>
 * An <code>ETEmail</code> represents an email in the
 * Salesforce Marketing Cloud. Emails are managed in the
 * Marketing Cloud Email app under the "Content" menu:
 * </p>
 *
 * <img src="doc-files/ETEmail1.png" width="100%" />
 *
 * <p>
 * Via the SDK, you can create, retrieve, update, and delete emails.
 * </p>
 *
 * <p>
 * To create an email:
 * </p>
 *
 * <pre>
 *ETClient client = new ETClient();
 *ETEmail email = new ETEmail();
 *email.setKey("myemail");
 *email.setName("my email");
 *email.setSubject("This is my first email");
 *email.setHtmlBody("&lt;b&gt;This is the HTML body&lt;/b&gt;");
 *email.setTextBody("This is the text body");
 *ETResponse&lt;ETEmail&gt; response = client.create(email);
 *for (ETResult&lt;ETEmail&gt; result : response.getResults()) {
 *    System.out.println(result);
 *}
 * </pre>
 *
 * <p>
 * To retrieve an email:
 * </p>
 *
 * <pre>
 *ETClient client = new ETClient();
 *ETResponse&lt;ETEmail&gt; response = client.retrieve(ETEmail.class, "key=myemail");
 *for (ETResult&lt;ETEmail&gt; result : response.getResults()) {
 *    System.out.println(result);
 *}
 * </pre>
 *
 * <p>
 * To retrieve all emails:
 * </p>
 *
 * <pre>
 *ETClient client = new ETClient();
 *ETResponse&lt;ETEmail&gt; response = client.retrieve(ETEmail.class);
 *for (ETResult&lt;ETEmail&gt; result : response.getResults()) {
 *    System.out.println(result);
 *}
 * </pre>
 *
 * <p>
 * To update an email:
 * </p>
 *
 * <pre>
 *ETClient client = new ETClient();
 *ETEmail email = client.retrieveObject(ETEmail.class, "key=myemail");
 *email.setSubject("This is still my first email");
 *ETResponse&lt;ETEmail&gt; response = client.update(email);
 *for (ETResult&lt;ETEmail&gt; result : response.getResults()) {
 *    System.out.println(result);
 *}
 * </pre>
 *
 * <p>
 * To delete an email:
 * </p>
 *
 * <pre>
 *ETClient client = new ETClient();
 *ETResponse&lt;ETEmail&gt; response = client.delete(ETEmail.class, "key=myemail");
 *for (ETResult&lt;ETEmail&gt; result : response.getResults()) {
 *    System.out.println(result);
 *}
 * </pre>
 *
 * <p>
 * Note: You can (and should) reuse <code>ETClient</code> instances,
 * as instantiating an instance involves authenticating with the
 * Salesforce Marketing Cloud and is, thus, a heavyweight operation.
 * </p>
 *
 * @see <a href="../../../overview-summary.html#objects">Objects</a>
 *
 * @see <a href="../../../overview-summary.html#methods">Methods</a>
 *
 * @see <a href="http://help.exacttarget.com/en-US/technical_library/web_service_guide/objects/email/">
 *               http://help.exacttarget.com/en-US/technical_library/web_service_guide/objects/email/
 *      </a>
 */

@SoapObject(internalType = Email.class)
public class ETEmail extends ETSoapObject {
    @ExternalName("id")
    private String id = null;
    @ExternalName("key")
    @InternalName("customerKey")
    private String key = null;
    @ExternalName("name")
    private String name = null;
    @ExternalName("createdDate")
    private Date createdDate = null;
    @ExternalName("modifiedDate")
    private Date modifiedDate = null;
    @ExternalName("folderId")
    @InternalName("categoryID")
    private Integer folderId = null;
    @ExternalName("subject")
    private String subject = null;
    @ExternalName("htmlBody")
    private String htmlBody = null;
    @ExternalName("textBody")
    private String textBody = null;
    @ExternalName("isHtmlPaste")
    @InternalName("isHTMLPaste")
    private Boolean isHtmlPaste = null;
    @ExternalName("type")
    @InternalName("emailType")
    private Type type = null;

    /** 
    * Class constructor, Initializes a new instance of the class.
    */    
    public ETEmail() {}

    /** 
    * @return The Identifier of the ETEmail object.
    */    
    @Override
    public String getId() {
        return id;
    }

    /** 
    * @param id     The Identifier of the ETEmail object.
    */    
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /** 
    * @return       The Customer Key of the ETEmail object.
    */     
    public String getKey() {
        return key;
    }

    /** 
    * @param key    The Customer Key of the ETEmail object.
    */      
    public void setKey(String key) {
        this.key = key;
    }

    /** 
    * @return     The name of the ETEmail object.
    */     
    public String getName() {
        return name;
    }

    /** 
    * @param name   The name of the ETEmail object.
    */     
    public void setName(String name) {
        this.name = name;
    }

    /** 
    * @return     The created date of the ETEmail object.
    */
    public Date getCreatedDate() {
        return createdDate;
    }

    /** 
    * @param createdDate        The created date of the ETEmail object.
    */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /** 
    * @return     The modified date of the ETEmail object.
    */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /** 
    * @param modifiedDate       The modified date of the ETEmail object.
    */    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /** 
    * @return     The folder Id(category Id) of the ETEmail object.
    */
    public Integer getFolderId() {
        return folderId;
    }

    /** 
    * @param folderId   The folder Id(category Id) of the ETEmail object.
    */
    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    /** 
    * @return     The subject of the ETEmail object.
    */    
    public String getSubject() {
        return subject;
    }

    /** 
    * @param subject       The subject of the ETEmail object.
    */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /** 
    * @return     The HTML body of the ETEmail object.
    */    
    public String getHtmlBody() {
        return htmlBody;
    }

    /** 
    * @param htmlBody       The HTML body of the ETEmail object.
    */
    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    /** 
    * @return     The text body of the ETEmail object.
    */    
    public String getTextBody() {
        return textBody;
    }

    /** 
    * @param textBody           The text body of the ETEmail object.
    */
    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    /** 
    * @return     Returns true if it is HTML paste, false otherwise.
    */    
    public Boolean getIsHtmlPaste() {
        return isHtmlPaste;
    }

    /** 
    * @param isHtmlPaste           Sets if it is HTML paste or not.
    */
    public void setIsHtmlPaste(Boolean isHtmlPaste) {
        this.isHtmlPaste = isHtmlPaste;
    }

    /** 
    * @return key       The type of the ETEmail object.
    * @see              Type
    */
    public Type getType() {
        return type;
    }

    /** 
    * @param type           The type of the ETEmail object.
    * @see                  Type
    */
    public void setType(Type type) {
        this.type = type;
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
     * @deprecated
     * Use <code>getFolderId()</code>.
     */
    @Deprecated
    public Integer getCategoryId() {
        return getFolderId();
    }

    /**
     * @deprecated
     * Use <code>setFolderId()</code>.
     */
    @Deprecated
    public void setCategoryId(Integer categoryId) {
        setFolderId(categoryId);
    }
    
    /**
     *  Email Types that can be used
     */
    public enum Type {
        HTML("HTML"),
        TEXT("Text"),
        Normal("Normal");
        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
