package cn.tedu.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	private static ComboPooledDataSource pool=new ComboPooledDataSource();
	private JDBCUtils(){}
	
//	��ȡ���ӳض���
	public static ComboPooledDataSource getPool(){
		return pool;
	}
//	��ȡ����
	public static Connection getConnection() throws SQLException{
		return pool.getConnection();
	}
//	�رն���
	public static void close(ResultSet rs,PreparedStatement ps,Connection con){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs=null;
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ps=null;
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				con=null;
			}
		}
	}
}
