package com.exacttarget.fuelsdk.model.converter;

import org.apache.commons.beanutils.Converter;

public class ETEnumConverter implements Converter {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object convert(Class type, Object value) 
	{
		
        if (value == null) return null;
        return Enum.valueOf(type, value.toString());
        
	}

}
