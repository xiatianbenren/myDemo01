package cn.tedu.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	private static ComboPooledDataSource pool=new ComboPooledDataSource();
	private JDBCUtils(){}
	
//	获取连接池对象
	public static ComboPooledDataSource getPool(){
		return pool;
	}
//	获取连接
	public static Connection getConnection() throws SQLException{
		return pool.getConnection();
	}
//	关闭对象
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
