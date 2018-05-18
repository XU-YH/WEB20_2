package cn.itcast.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.admin.Product;
import cn.itcast.service.AdminProductService;

public class AdminAddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����request�ı���
		request.setCharacterEncoding("UTF-8");

		// ��request(��)�л�ȡ����
		Map<String, String[]> properties = request.getParameterMap();

		// ��װ����
		Product product = new Product();
		try {
			BeanUtils.populate(product, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		product.setPid(UUID.randomUUID().toString());
		product.setPimage("products/1/c_0013.jpg");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String pdate = format.format(new Date());
		product.setPdate(pdate);
		product.setPflag(0);
		
		//�����ݴ��ݵ�service��
		AdminProductService service= new AdminProductService();
		try {
			service.addProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//��ת���б�ҳ��
		response.sendRedirect(request.getContextPath()+"/adminProductList");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}