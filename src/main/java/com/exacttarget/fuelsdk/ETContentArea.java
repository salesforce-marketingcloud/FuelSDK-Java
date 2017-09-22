/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.ContentArea;
import com.exacttarget.fuelsdk.internal.LayoutType;

/**
 * An <code>ETContentArea</code> object represents represents a defined section of reusable content in the Salesforce Marketing Cloud.
 * One or many ContentAreas can be defined for an Email object. A ContentArea is always acted upon in the context of an Email object.
 */
@SoapObject(internalType = ContentArea.class)
public class ETContentArea extends ETSoapObject{
    @InternalName("objectID")    
    private String id;    
    
    @ExternalName("key")
    private String key;
    
    @InternalName("customerKey")
    @ExternalName("customerKey")
    private String customerKey;    
    
    @ExternalName("content")
    private String content;
    
    @ExternalName("isBlank")
    private Boolean isBlank;
    
    @ExternalName("categoryID")
    private Integer categoryID;
    
    @ExternalName("name")
    private String name;
    
    @ExternalName("layout")
    private LayoutType layout;
    
    @ExternalName("isDynamicContent")
    private Boolean isDynamicContent;
    
    @ExternalName("isSurvey")
    private Boolean isSurvey;
    
    @InternalName("backgroundColor")
    private String backgroundColor;
    
    @InternalName("borderColor")
    private String borderColor;
    
    @InternalName("borderWidth")
    private Integer borderWidth;
    
    @InternalName("cellpadding")
    private Integer cellpadding;
    
    @InternalName("cellspacing")
    private Integer cellspacing;
    
    @InternalName("width")
    private String width;
    
    @InternalName("fontFamily")
    private String fontFamily;
    
    @InternalName("hasFontSize")
    private Boolean hasFontSize;
    
    @InternalName("isLocked")
    private Boolean isLocked;

    /**
     * @return the id
     */    
    @Override
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */    
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the isBlank
     */
    public Boolean getIsBlank() {
        return isBlank;
    }

    /**
     * @param isBlank the isBlank to set
     */
    public void setIsBlank(Boolean isBlank) {
        this.isBlank = isBlank;
    }

    /**
     * @return the categoryID
     */
    public Integer getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
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

    /**
     * @return the layout
     */
    public LayoutType getLayout() {
        return layout;
    }

    /**
     * @param layout the layout to set
     */
    public void setLayout(LayoutType layout) {
        this.layout = layout;
    }

    /**
     * @return the isDynamicContent
     */
    public Boolean getIsDynamicContent() {
        return isDynamicContent;
    }

    /**
     * @param isDynamicContent the isDynamicContent to set
     */
    public void setIsDynamicContent(Boolean isDynamicContent) {
        this.isDynamicContent = isDynamicContent;
    }

    /**
     * @return the isSurvey
     */
    public Boolean getIsSurvey() {
        return isSurvey;
    }

    /**
     * @param isSurvey the isSurvey to set
     */
    public void setIsSurvey(Boolean isSurvey) {
        this.isSurvey = isSurvey;
    }

    /**
     * @return the backgroundColor
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @param backgroundColor the backgroundColor to set
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @return the borderColor
     */
    public String getBorderColor() {
        return borderColor;
    }

    /**
     * @param borderColor the borderColor to set
     */
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @return the borderWidth
     */
    public Integer getBorderWidth() {
        return borderWidth;
    }

    /**
     * @param borderWidth the borderWidth to set
     */
    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * @return the cellpadding
     */
    public Integer getCellpadding() {
        return cellpadding;
    }

    /**
     * @param cellpadding the cellpadding to set
     */
    public void setCellpadding(Integer cellpadding) {
        this.cellpadding = cellpadding;
    }

    /**
     * @return the cellspacing
     */
    public Integer getCellspacing() {
        return cellspacing;
    }

    /**
     * @param cellspacing the cellspacing to set
     */
    public void setCellspacing(Integer cellspacing) {
        this.cellspacing = cellspacing;
    }

    /**
     * @return the width
     */
    public String getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * @return the fontFamily
     */
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * @param fontFamily the fontFamily to set
     */
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    /**
     * @return the hasFontSize
     */
    public Boolean getHasFontSize() {
        return hasFontSize;
    }

    /**
     * @param hasFontSize the hasFontSize to set
     */
    public void setHasFontSize(Boolean hasFontSize) {
        this.hasFontSize = hasFontSize;
    }

    /**
     * @return the isLocked
     */
    public Boolean getIsLocked() {
        return isLocked;
    }

    /**
     * @param isLocked the isLocked to set
     */
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * @return the customerKey
     */
    public String getCustomerKey() {
        return customerKey;
    }

    /**
     * @param customerKey the customerKey to set
     */
    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }
    
}
