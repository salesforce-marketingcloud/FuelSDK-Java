package com.exacttarget.fuelsdk.model.converter;

import org.apache.commons.beanutils.Converter;

import com.exacttarget.fuelsdk.internal.AccountTypeEnum;
import com.exacttarget.fuelsdk.internal.DataExtensionFieldType;
import com.exacttarget.fuelsdk.internal.DataSourceTypeEnum;
import com.exacttarget.fuelsdk.internal.DeliveryProfileDomainTypeEnum;
import com.exacttarget.fuelsdk.internal.DeliveryProfileSourceAddressTypeEnum;
import com.exacttarget.fuelsdk.internal.EmailType;
import com.exacttarget.fuelsdk.internal.EventType;
import com.exacttarget.fuelsdk.internal.LayoutType;
import com.exacttarget.fuelsdk.internal.ListClassificationEnum;
import com.exacttarget.fuelsdk.internal.ListTypeEnum;
import com.exacttarget.fuelsdk.internal.SalutationSourceEnum;
import com.exacttarget.fuelsdk.internal.SendClassificationTypeEnum;
import com.exacttarget.fuelsdk.internal.SendDefinitionListTypeEnum;
import com.exacttarget.fuelsdk.internal.SendPriorityEnum;
import com.exacttarget.fuelsdk.internal.SubscriberStatus;
import com.exacttarget.fuelsdk.model.ETAccountType;
import com.exacttarget.fuelsdk.model.ETDataExtensionFieldType;
import com.exacttarget.fuelsdk.model.ETDataSourceType;
import com.exacttarget.fuelsdk.model.ETDeliveryProfileDomainType;
import com.exacttarget.fuelsdk.model.ETDeliveryProfileSourceAddressType;
import com.exacttarget.fuelsdk.model.ETEmailType;
import com.exacttarget.fuelsdk.model.ETEventType;
import com.exacttarget.fuelsdk.model.ETLayoutType;
import com.exacttarget.fuelsdk.model.ETListClassification;
import com.exacttarget.fuelsdk.model.ETListType;
import com.exacttarget.fuelsdk.model.ETSalutationSource;
import com.exacttarget.fuelsdk.model.ETSendClassificationType;
import com.exacttarget.fuelsdk.model.ETSendDefinitionListType;
import com.exacttarget.fuelsdk.model.ETSendPriority;
import com.exacttarget.fuelsdk.model.ETSubscriberStatus;

public class ETEnumConverter implements Converter {

	@SuppressWarnings("rawtypes")
	public Object convert(Class type, Object value) {
		
        if (value == null) return null;
        if (type == EmailType.class) {
            return ETEmailType.valueOf((value).toString());
        } else if (type == SubscriberStatus.class) {
            return ETSubscriberStatus.valueOf((value).toString());
        } else if (type == ListClassificationEnum.class) {
            return ETListClassification.valueOf((value).toString());
        } else if (type == ListTypeEnum.class) {
            return ETListType.valueOf((value).toString());
        } else if (type == LayoutType.class) {
            return ETLayoutType.valueOf((value).toString());
        } else if (type == EventType.class) {
            return ETEventType.valueOf((value).toString());
        } else if (type == AccountTypeEnum.class) {
            return ETAccountType.valueOf((value).toString());
        } else if (type == DataExtensionFieldType.class) {
            return ETDataExtensionFieldType.valueOf((value).toString());
        } else if (type == ETDataExtensionFieldType.class) {
            return DataExtensionFieldType.valueOf((value).toString());
        } else if (type == DeliveryProfileDomainTypeEnum.class) {
            return ETDeliveryProfileDomainType.valueOf((value).toString());
        } else if (type == DeliveryProfileSourceAddressTypeEnum.class) {
            return ETDeliveryProfileSourceAddressType.valueOf((value).toString());
        } else if (type == SalutationSourceEnum.class) {
            return ETSalutationSource.valueOf((value).toString());
        } else if (type == SendClassificationTypeEnum.class) {
            return ETSendClassificationType.valueOf((value).toString());
        } else if (type == SendPriorityEnum.class) {
            return ETSendPriority.valueOf((value).toString());
        } else if (type == DataSourceTypeEnum.class) {
        	return ETDataSourceType.valueOf((value).toString());
        } else if (type == SendDefinitionListTypeEnum.class) {
        	return ETSendDefinitionListType.valueOf((value).toString());
        }
        return null;
	}

}
