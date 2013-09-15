package com.exacttarget.fuelsdk.model;

import java.util.Date;
import java.util.List;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.Account;

@InternalSoapType(type = Account.class)
public class ETOrganiation extends BaseSoapSerializableObject implements
		ETObject {

	@InternalSoapField(name="accountType")
	private ETAccountType accountType;
	
	@InternalSoapField(name="name")
	private String name;
	
	@InternalSoapField(name="address")
	private String address;
	
	@InternalSoapField(name="brandID")
	private Integer brandId;
	
	@InternalSoapField(name="businessName")
	private String businessName;
	
	@InternalSoapField(name="city")
	private String city;
	
	@InternalSoapField(name="country")
	private String country;
	
	@InternalSoapField(name="deletedDate")
	private Date deletedDate;
	
	@InternalSoapField(name="editionID")
	private Integer editionId;
	
	@InternalSoapField(name="email")
	private String email;
	
	@InternalSoapField(name="fax")
	private String fax;

	@InternalSoapField(name="fromName")
	private String fromName;
	
	@InternalSoapField(name="inheritAddress")
	private Boolean inheritAddress;
	
	@InternalSoapField(name="isActive")
	private Boolean isActive;
	
	@InternalSoapField(name="isTestAccount")
	private Boolean isTestAccount;
	
	@InternalSoapField(name="isTrialAccount")
	private Boolean isTrialAccount;
	
	@InternalSoapField(name="parentAccount", serializedName="ParentAccount.id")
	private ETOrganiation parentOrganization;
	
	@InternalSoapField(name="parentID")
	private Integer parentId;
	
	@InternalSoapField(name="parentName")
	private String parentName;
	
	@InternalSoapField(name="phone")
	private String phone;
	
	@InternalSoapField(name="privateLabelID")
	private Integer privateLabelId;
	
	@InternalSoapField(name="roles")
	private List<ETRole> roles;
	
	@InternalSoapField(name="state")
	private String state;
	
	@InternalSoapField(name="zip")
	private String zip;
	
	public ETOrganiation(){ }

	public ETAccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(ETAccountType accountType) {
		this.accountType = accountType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Integer getEditionId() {
		return editionId;
	}

	public void setEditionId(Integer editionId) {
		this.editionId = editionId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public Boolean getInheritAddress() {
		return inheritAddress;
	}

	public void setInheritAddress(Boolean inheritAddress) {
		this.inheritAddress = inheritAddress;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsTestAccount() {
		return isTestAccount;
	}

	public void setIsTestAccount(Boolean isTestAccount) {
		this.isTestAccount = isTestAccount;
	}

	public Boolean getIsTrialAccount() {
		return isTrialAccount;
	}

	public void setIsTrialAccount(Boolean isTrialAccount) {
		this.isTrialAccount = isTrialAccount;
	}

	public ETOrganiation getParentOrganization() {
		return parentOrganization;
	}

	public void setParentOrganization(ETOrganiation parentOrganization) {
		this.parentOrganization = parentOrganization;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPrivateLabelId() {
		return privateLabelId;
	}

	public void setPrivateLabelId(Integer privateLabelId) {
		this.privateLabelId = privateLabelId;
	}

	public List<ETRole> getRoles() {
		return roles;
	}

	public void setRoles(List<ETRole> roles) {
		this.roles = roles;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "ETOrganiation [accountType=" + accountType + ", name=" + name
				+ ", address=" + address + ", brandId=" + brandId
				+ ", businessName=" + businessName + ", city=" + city
				+ ", country=" + country + ", deletedDate=" + deletedDate
				+ ", editionId=" + editionId + ", email=" + email + ", fax="
				+ fax + ", fromName=" + fromName + ", inheritAddress="
				+ inheritAddress + ", isActive=" + isActive
				+ ", isTestAccount=" + isTestAccount + ", isTrialAccount="
				+ isTrialAccount + ", parentOrganization=" + parentOrganization
				+ ", parentId=" + parentId + ", parentName=" + parentName
				+ ", phone=" + phone + ", privateLabelId=" + privateLabelId
				+ ", roles=" + roles + ", state=" + state + ", zip=" + zip
				+ ", id=" + id + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", customerKey="
				+ customerKey + "]";
	}
	
	
}






