package cn.tedu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.tedu.utils.JDBCUtils;
import cn.tedu.utils.WebUtils;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String password2=request.getParameter("password2");
		String nickname=request.getParameter("nickname");
		String email=request.getParameter("email");
		String valistr=request.getParameter("valistr");
		//�ǿ�У��
		if(WebUtils.isNull(username)){
			request.setAttribute("msg", "�û�������Ϊ��");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		if(WebUtils.isNull(password)){
			request.setAttribute("msg", "���벻��Ϊ��");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		if(WebUtils.isNull(password2)){
			request.setAttribute("msg", "ȷ�����벻��Ϊ��");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
//		��������Ƚ�
		if(!password2.equals(password)){
			request.setAttribute("msg", "�������벻һ��");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		if(WebUtils.isNull(nickname)){
			request.setAttribute("msg", "�ǳƲ���Ϊ��");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		if(WebUtils.isNull(email)){
			request.setAttribute("msg", "���䲻��Ϊ��");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		String reg="^\\w+@\\w+(\\.\\w+)+$";
		if(!email.matches(reg)){
			request.setAttribute("msg", "�����ʽ����ȷ");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		if(WebUtils.isNull(valistr)){
			request.setAttribute("msg", "��֤�벻��Ϊ��");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=JDBCUtils.getConnection();
			String sql="select * from user where username=? and password=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				request.setAttribute("msg", "�û�����ע��");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
				return;
			}
			String sql1="insert into user values(null,?,?,?,?)";
			ps=con.prepareStatement(sql1);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, nickname);
			ps.setString(4, email);
			ps.executeUpdate();
			
			response.getWriter().write("<h1 style='color:red;text-align:center'>��ϲ��ע��ɹ���3�����ת����ҳ</h1>");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(rs, ps, con);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
