package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ValidateCommon;
import model.bean.HangHoa;

public class SearchProductDAO extends BaseDAO {
	
	public ArrayList<HangHoa> getDsHangHoa(String searchText) {
		ArrayList<HangHoa> returnedList = new ArrayList<HangHoa>();
		
		Connection connection = getConnection();
		String sql = "SELECT * FROM HangHoa WHERE MaHH LIKE ? OR TenHH LIKE ? OR donvitinh LIKE ? OR ghichu LIKE ? OR DonGiaThamKhaao = ?";		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%" );
			pstmt.setString(2, "%" + searchText + "%" );
			pstmt.setString(3, "%" + searchText + "%" );
			pstmt.setString(4, "%" + searchText + "%" );
			if (ValidateCommon.isValidDigitString(searchText)) {
				pstmt.setDouble(5, Double.valueOf(searchText));
			}
			else {
				pstmt.setDouble(5, -1);
			}
			
			rs = pstmt.executeQuery();
			
			HangHoa item = null;
			
			while (rs.next()) {
				item = new HangHoa();
				item.setMaHH(rs.getString("mahh"));
				item.setTenHH(rs.getString("tenhh"));
				item.setDonGiaThamKhao(rs.getInt("dongiathamkhao"));
				item.setDonViTinh(rs.getString("donvitinh"));
				item.setGhiChu(rs.getString("ghichu"));
				
				returnedList.add(item);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, rs);
		}	
		return returnedList;
	}
	
}
