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

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.DeliveryProfile;

@InternalSoapType(type = DeliveryProfile.class)
public class ETDeliveryProfile extends ETSoapObject {

	@InternalSoapField(name = "name")
    protected String name;

	@InternalSoapField(name = "description")
    protected String description;

	@InternalSoapField(name = "sourceAddressType")
    protected ETDeliveryProfileSourceAddressType sourceAddressType;

	@InternalSoapField(name = "privateIP")
    protected String privateIP;

	@InternalSoapField(name = "domainType")
    protected ETDeliveryProfileDomainType domainType;

	@InternalSoapField(name = "headerSalutationSource")
    protected ETSalutationSource headerSalutationSource;

	@InternalSoapField(name = "footerSalutationSource")
    protected ETSalutationSource footerSalutationSource;

	public ETDeliveryProfile() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ETDeliveryProfileSourceAddressType getSourceAddressType() {
		return sourceAddressType;
	}

	public void setSourceAddressType(
			ETDeliveryProfileSourceAddressType sourceAddressType) {
		this.sourceAddressType = sourceAddressType;
	}

	public String getPrivateIP() {
		return privateIP;
	}

	public void setPrivateIP(String privateIP) {
		this.privateIP = privateIP;
	}

	public ETDeliveryProfileDomainType getDomainType() {
		return domainType;
	}

	public void setDomainType(ETDeliveryProfileDomainType domainType) {
		this.domainType = domainType;
	}

	public ETSalutationSource getHeaderSalutationSource() {
		return headerSalutationSource;
	}

	public void setHeaderSalutationSource(ETSalutationSource headerSalutationSource) {
		this.headerSalutationSource = headerSalutationSource;
	}

	public ETSalutationSource getFooterSalutationSource() {
		return footerSalutationSource;
	}

	public void setFooterSalutationSource(ETSalutationSource footerSalutationSource) {
		this.footerSalutationSource = footerSalutationSource;
	}

	@Override
	public String toString() {
		return "ETDeliveryProfile [name=" + name + ", description="
				+ description + ", sourceAddressType=" + sourceAddressType
				+ ", privateIP=" + privateIP + ", domainType=" + domainType
				+ ", headerSalutationSource=" + headerSalutationSource
				+ ", footerSalutationSource=" + footerSalutationSource
				+ ", id=" + id + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", customerKey="
				+ customerKey + "]";
	}



}
