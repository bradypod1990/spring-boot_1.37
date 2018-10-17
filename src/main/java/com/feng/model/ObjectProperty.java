package com.feng.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonIgnoreProperties(ignoreUnknown=true)
public class ObjectProperty implements Serializable{

	private static final long serialVersionUID = 1L;

	//属性ID
	private String propertyId;
	//表名称
	private String objectName;
	//表中文名称
	private String objcetCname;
	//属性名称
	private String propertyName;
	//属性中文名称
	private String propertyCname;
	//数据类型
	private String dataType;
	//数据长度
	private String dataLength;
	//系统编码
	private String systemCode;

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjcetCname() {
		return objcetCname;
	}
	public void setObjcetCname(String objcetCname) {
		this.objcetCname = objcetCname;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyCname() {
		return propertyCname;
	}
	public void setPropertyCname(String propertyCname) {
		this.propertyCname = propertyCname;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	@Override
	public String toString() {
		return "ObjectProperty [propertyId=" + propertyId + ", objectName=" + objectName + ", objcetCname="
				+ objcetCname + ", propertyName=" + propertyName + ", propertyCname=" + propertyCname + ", dataType="
				+ dataType + ", dataLength=" + dataLength + ", systemCode=" + systemCode + "]";
	}

	
	
}
