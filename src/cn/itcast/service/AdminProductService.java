package cn.itcast.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.admin.Category;
import cn.itcast.admin.Product;
import cn.itcast.dao.AdminProductDao;
import cn.itcast.vo.Condition;

public class AdminProductService {

	//��ѯ������Ʒ
	public List<Product> findAllProduct() throws SQLException {
		// ��������dao��
		AdminProductDao dao = new AdminProductDao();
		List<Product> productList = dao.findAllProduct();
		return productList;
	}

	//��ѯ������Ʒ���
	public List<Category> findAllCategory() throws SQLException {
		//��������dao��
		AdminProductDao dao= new AdminProductDao();
		List<Category> categoryList= dao.findAllCategory();
		return categoryList;
	}

	//�����Ʒ
	public void addProduct(Product product) throws SQLException {
		//�������ݵ�dao��
		AdminProductDao dao= new AdminProductDao();
		dao.addProduct(product);		
	}
	
	//ͨ��pidɾ����Ʒ
	public void delProductById(String pid) throws SQLException {
		//�������ݵ�dao��
		AdminProductDao dao= new AdminProductDao();
		dao.delProductById(pid);
		
	}

	public Product findProductById(String pid) throws SQLException {
		//�������ݵ�dao��
		AdminProductDao dao= new AdminProductDao();
		Product product = dao.findProductById(pid);
		return product;
	}

	public void updateProduct(Product product) throws SQLException {
		//�������ݵ�dao��
		AdminProductDao dao= new AdminProductDao();
		dao.updateProduct(product);		
	}

	public List<Product> searchProduct(Condition condition) throws SQLException {
		//�������ݵ�dao��
		AdminProductDao dao= new AdminProductDao();
		List<Product> product= dao.searchProduct(condition);	
		return product;		
	}

}
