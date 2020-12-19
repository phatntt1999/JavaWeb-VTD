package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckLoginDAO extends BaseDAO{

	public int getAccountRole(String tenDangNhap, String matKhau) {
		Connection connection = getConnection();
		String sql = "SELECT * FROM NhanVien WHERE username = ? AND password = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, tenDangNhap);
			pstmt.setString(2, matKhau);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if ("admin".equals(rs.getString("role"))) {
					return 1; // admin
				} else {
					return 2; // employee
				}
			} else {
				return 0;  // invalid account
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, rs);
		}
		
		return 0; // invalid account
	}
}
