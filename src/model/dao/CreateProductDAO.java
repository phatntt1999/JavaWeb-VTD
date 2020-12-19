package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateProductDAO extends BaseDAO{

	public String insertProduct(String maHH, String tenHH, String dvt, String donGiaTK, String ghiChu) {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		String sql = "INSERT INTO HANGHOA (MaHH,TenHH,DonGiaThamKhao,DonViTinh"
			+ ",GhiChu) VALUES (?,?,?,?,?)";

		PreparedStatement pstmt = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, maHH);
			pstmt.setString(2, tenHH);
			pstmt.setDouble(3, Double.valueOf(donGiaTK));
			pstmt.setString(4, dvt);
			pstmt.setString(5, ghiChu);
			
			int x = pstmt.executeUpdate();
			System.out.println("Đã chèn số record: " + x);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			String errorMessage = e.getMessage();
			if(errorMessage != null && errorMessage.contains("Duplicate entry")) {
				return "Duplicate ID Error.";
			}
		} finally {
			closeConnection(connection, pstmt, null);
		}
		return "No error.";
	}

	public String getLastestMaHH() {
		String lastestMaHH = null;
		
		Connection connection = getConnection();
		String sql = "SELECT * FROM HangHoa where MaHH = (select max(MaHH) from HangHoa)";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				lastestMaHH = rs.getString("MaHH");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, rs);
		}
		return lastestMaHH;
	}
}