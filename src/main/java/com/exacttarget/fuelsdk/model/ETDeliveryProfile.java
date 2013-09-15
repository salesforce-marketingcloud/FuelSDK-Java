package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.DeliveryProfile;

@InternalSoapType(type = DeliveryProfile.class)
public class ETDeliveryProfile extends BaseSoapSerializableObject implements ETObject{

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
	
	//@InternalSoapField(name = "privateDomain")
    //protected PrivateDomain privateDomain;
	
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
