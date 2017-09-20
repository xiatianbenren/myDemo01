package cn.tedu.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.utils.VerifyCode;

@WebServlet("/ValiImageServlet")
public class ValiImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Ê¹ä¯ÀÀÆ÷²»»º´æÍ¼Æ¬
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		
		VerifyCode very=new VerifyCode();
		very.drawImage(response.getOutputStream());
		String code=very.getCode();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
