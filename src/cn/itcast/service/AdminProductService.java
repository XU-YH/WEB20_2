package cn.itcast.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.admin.Category;
import cn.itcast.admin.Product;
import cn.itcast.dao.AdminProductDao;
import cn.itcast.vo.Condition;

public class AdminProductService {

	//查询所有商品
	public List<Product> findAllProduct() throws SQLException {
		// 传递请求到dao层
		AdminProductDao dao = new AdminProductDao();
		List<Product> productList = dao.findAllProduct();
		return productList;
	}

	//查询所有商品类别
	public List<Category> findAllCategory() throws SQLException {
		//传递请求到dao层
		AdminProductDao dao= new AdminProductDao();
		List<Category> categoryList= dao.findAllCategory();
		return categoryList;
	}

	//添加商品
	public void addProduct(Product product) throws SQLException {
		//传递数据到dao层
		AdminProductDao dao= new AdminProductDao();
		dao.addProduct(product);		
	}
	
	//通过pid删除商品
	public void delProductById(String pid) throws SQLException {
		//传递数据到dao层
		AdminProductDao dao= new AdminProductDao();
		dao.delProductById(pid);
		
	}

	public Product findProductById(String pid) throws SQLException {
		//传递数据到dao层
		AdminProductDao dao= new AdminProductDao();
		Product product = dao.findProductById(pid);
		return product;
	}

	public void updateProduct(Product product) throws SQLException {
		//传递数据到dao层
		AdminProductDao dao= new AdminProductDao();
		dao.updateProduct(product);		
	}

	public List<Product> searchProduct(Condition condition) throws SQLException {
		//传递数据到dao层
		AdminProductDao dao= new AdminProductDao();
		List<Product> product= dao.searchProduct(condition);	
		return product;		
	}

}
