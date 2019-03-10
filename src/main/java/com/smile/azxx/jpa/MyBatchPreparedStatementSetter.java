/**
 * 
 */
package com.smile.azxx.jpa;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Cason
 *
 * @Date Apr 1, 2012
 */
public class MyBatchPreparedStatementSetter implements BatchPreparedStatementSetter {
	private List datas ;
	
	public MyBatchPreparedStatementSetter(List params){
		this.datas = params;
	}

	public int getBatchSize() {
		return datas.size();
	}
	//专题维护 zhangsai  (Blob)params[i][1]  
	public void setValues(PreparedStatement ps, int index) throws SQLException {
		Object[][] params = (Object[][]) datas.get(index);
		if(params.length>0){
			for(int i=0;i<params.length;i++){
				String ptype = params[i][0].toString();
				if("string".equalsIgnoreCase(ptype)){
					ps.setString(i+1, params[i][1]+"");
				}else if("int".equalsIgnoreCase(ptype)){
					ps.setInt(i+1, Integer.parseInt(params[i][1]+""));
				}else if("double".equalsIgnoreCase(ptype)){
					ps.setDouble(i+1, Double.parseDouble(params[i][1]+""));
				}else if("float".equalsIgnoreCase(ptype)){
					ps.setFloat(i+1, Float.parseFloat(params[i][1]+""));
				}else if("long".equalsIgnoreCase(ptype)){
					ps.setLong(i+1, Long.parseLong(params[i][1]+""));
				}else if("Blob".equalsIgnoreCase(ptype)){  
					ps.setBlob(i+1, (Blob)params[i][1]);
				}
			}
		}
	}

}
