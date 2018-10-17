package com.feng.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.feng.model.ObjectProperty;
import com.feng.util.DatamanagePage;

@Repository
public interface ObjectPropertyDao extends BaseMapper<ObjectProperty>{

	public IPage<ObjectProperty> queryObjectProperty(@Param("opPage") DatamanagePage<ObjectProperty> opPage,@Param("op") ObjectProperty op);
}