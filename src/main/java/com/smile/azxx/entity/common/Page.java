package com.smile.azxx.entity.common;

import java.util.List;

public interface Page{

	public static int PAGE_SIZE = 15;

	/**
	 * 获取每页的条数
	 * 
	 * @return 
	 */
	public int getPageSize();

	public List getElements();

	public void setElements(List elements);

	public int getPageNo();

	public void setPageNo(int pageNo);

	public long getTotal();

	public void setTotal(long totals);

	public void setPageSize(int pageSize);
	
}
