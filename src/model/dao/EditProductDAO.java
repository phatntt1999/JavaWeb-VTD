package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.HangHoa;

public class EditProductDAO extends BaseDAO{
	public HangHoa getProductInfor(String proId) {
		HangHoa hangHoa = new HangHoa();

		Connection connection = getConnection();
		String sql = "SELECT * FROM HangHoa WHERE mahh = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, proId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				hangHoa.setMaHH(rs.getString("mahh"));
				hangHoa.setTenHH(rs.getString("tenhh"));
				hangHoa.setDonGiaThamKhao(rs.getInt("dongiathamkhao"));
				hangHoa.setDonViTinh(rs.getString("donvitinh"));
				hangHoa.setGhiChu(rs.getString("ghichu"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, rs);
		}
		return hangHoa;
	}

	public String ediProduct(String maHH, String tenHH, String dvt, String donGiaTK, String ghiChu) {
		Connection connection = getConnection();
		String sql = "UPDATE hanghoa SET TenHH = ?, DonGiaThamKhao = ?, DonViTinh = ?, GhiChu = ? WHERE maHH = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, tenHH);
			pstmt.setDouble(2, Double.valueOf(donGiaTK));
			pstmt.setString(3, dvt);
			pstmt.setString(4, ghiChu);
			pstmt.setString(5, maHH);

			int x = pstmt.executeUpdate();
			System.out.println("Đã update số record: " + x);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return "Duplicate ID Error";

		} finally {
			closeConnection(connection, pstmt, null);
		}

		return "No error";
	}
}
