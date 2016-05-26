package com.itheima.redbaby.dao;


 
import com.itheima.redbaby.vo.ProdcutHistory;

import android.test.AndroidTestCase;

public class ProductDaoTest extends AndroidTestCase {

 	public void testDelete() {
		ProductDao productDao = new ProductDao(getContext());
		productDao.delete(1);
	}

 	public void testAdd() {
		ProductDao productDao = new ProductDao(getContext());
		productDao.add(new ProdcutHistory(2, "奶粉", "http://192.168.1.139:8080/redbaby/images/product_04.jpg", 500.00, 400.00, 11, 360000));
 	}

 	public void testUpdate() {
 		ProductDao productDao = new ProductDao(getContext());
		productDao.update(new ProdcutHistory(2, "奶粉", "http://192.168.1.139:8080/redbaby/images/product_04.jpg", 500.00, 400.00, 11, 38000));
 	}

}
