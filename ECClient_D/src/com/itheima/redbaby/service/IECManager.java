package com.itheima.redbaby.service;

import java.util.List;

import com.itheima.redbaby.vo.ProdcutHistory;

public interface IECManager {
	 void addProductToHistory(ProdcutHistory history);
	 void clearProductHistory();
	 List<ProdcutHistory> getAllProductHistory();
}
