package cn.itcast.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.admin.Category;
import cn.itcast.admin.Product;
import cn.itcast.service.AdminProductService;

public class AdminUpdateProductUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ��ȡ�༭��Ʒ��pid
		String pid = request.getParameter("pid");

		// ����pid��ѯ����
		AdminProductService service = new AdminProductService();
		Product product = null;
		try {
			product = service.findProductById(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// �����ݿ��ȡ������Ʒ���������
		List<Category> categoryList = null;
		try {
			categoryList = service.findAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("product", product);
		request.setAttribute("categoryList", categoryList);
		
		request.getRequestDispatcher("admin/product/edit.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}