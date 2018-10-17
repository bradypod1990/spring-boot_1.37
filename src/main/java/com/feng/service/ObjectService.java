package com.feng.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.feng.dao.ObjectPropertyDao;
import com.feng.model.ObjectProperty;
import com.feng.util.DatamanagePage;

@Service
public class ObjectService {

	
	@Autowired
	private ObjectPropertyDao objectPropertyDao;
	
	//IPage类中的很多属性都是为基础类型，在序列化时会存在问题
	//@Cacheable(value="zoufeng", key="#pageNum+'-'+#pageSize+'-'+#systemCode+'-'+#objectName")
	public IPage<ObjectProperty> queryObjectProperty(int pageNum, int pageSize, String systemCode, String objectName) {
		DatamanagePage<ObjectProperty> myPage = new DatamanagePage<ObjectProperty>(pageNum, pageSize);
		ObjectProperty objectProperty = new ObjectProperty();
		if(StringUtils.isNotEmpty(systemCode)) {
			objectProperty.setSystemCode(systemCode);
		}
		if(StringUtils.isNoneEmpty(objectName)) {
			objectProperty.setObjectName(objectName);
		}
		return objectPropertyDao.queryObjectProperty(myPage, objectProperty);
	}
}
