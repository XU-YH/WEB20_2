package cn.itcast.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.admin.Category;
import cn.itcast.admin.Product;
import cn.itcast.service.AdminProductService;
import cn.itcast.vo.Condition;

public class AdminSearchProductListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����request�ı���
		request.setCharacterEncoding("UTF-8");

		// ��request(��)�л�ȡ����
		Map<String, String[]> properties = request.getParameterMap();

		// ��װ����
		Condition condition = new Condition();
		try {
			BeanUtils.populate(condition, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// �����ݴ��ݵ�service��
		AdminProductService service = new AdminProductService();
		List<Product> productList = null;
		try {
			productList = service.searchProduct(condition);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//�����ݿ��ȡ������Ʒ���������
		List<Category> categoryList = null;
		try {
			categoryList = service.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("productList", productList);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("condition", condition);

		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}