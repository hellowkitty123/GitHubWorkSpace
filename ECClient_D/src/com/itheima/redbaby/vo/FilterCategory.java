package com.itheima.redbaby.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Administrator
 *
 */

public class FilterCategory implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6814450983755681988L;
	private String key;
	private List<ProductFilterVo> value = new ArrayList<ProductFilterVo>();

	public FilterCategory() {
	}

	public FilterCategory(String key, List<ProductFilterVo> value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<ProductFilterVo> getValue() {
		return value;
	}

	public void setValue(List<ProductFilterVo> value) {
		this.value = value;
	}

}
