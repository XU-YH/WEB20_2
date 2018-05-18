package cn.itcast.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.AdminProductService;

public class AdminDelProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҪɾ������Ʒpid
		String pid= request.getParameter("pid");
		
		//��pid���ݵ�service
		AdminProductService service= new AdminProductService();
		try {
			service.delProductById(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//�ض���
		response.sendRedirect(request.getContextPath()+"/adminProductList");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}