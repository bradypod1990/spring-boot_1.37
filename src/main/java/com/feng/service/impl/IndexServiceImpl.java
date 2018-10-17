package com.feng.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.feng.service.IndexService;
import com.feng.util.DynamicDataSourcePool;

import lombok.extern.slf4j.Slf4j;

@Service("IndexService")
@Slf4j
public class IndexServiceImpl implements IndexService {

    @Override
    public String hello() {
        log.info("hello world service");
        return "hello world service";
    }
    
    @Override
	public String createDataSource() {
		DynamicDataSourcePool dyPool = null;
		try {
			dyPool = new DynamicDataSourcePool("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/datamanage88", "root", "root");
			Connection conn = dyPool.getConnection();
			for(int i=0; i<50; i++) {
				PreparedStatement stste = conn.prepareStatement("select * from t_z_backup_meta_rdbms");
				ResultSet rs = stste.executeQuery();
				if(rs != null) {
					while(rs.next()) {
						log.info("id:" + rs.getString("id") + "; catalogId:" + rs.getString("catalog_id"));
					}
				}
				rs.close();
				stste.close();
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(dyPool != null) {
				dyPool.destroy();
			}
		}
		return null;
	}
}
