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

	public int getTotalPageNumber(String searchText) {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		String sql = "SELECT count(tenhh) as tongsodong FROM HangHoa WHERE MaHH LIKE ? OR TenHH LIKE ? OR donvitinh LIKE ? OR ghichu LIKE ? OR DonGiaThamKhao = ?";		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalPageNumber = 0;
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setString(2, "%" + searchText + "%");
			pstmt.setString(3, "%" + searchText + "%");
			pstmt.setString(4, "%" + searchText + "%");
			if (ValidateCommon.isValidDigitString(searchText)) {
				pstmt.setDouble(5, Double.valueOf(searchText));
			} else {
				pstmt.setDouble(5, -1); // Chèn 1 số âm bất kỳ
			}			
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				totalPageNumber = rs.getInt("tongsodong");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, rs);
		}
		
		return (int)Math.ceil(totalPageNumber / 20.0);
	}

	public ArrayList<HangHoa> getDsHangHoa(String searchText, int pageNumber) {
		// TODO Auto-generated method stub
		ArrayList<HangHoa> returnedList = new ArrayList<HangHoa>();
		
		Connection connection = getConnection();
		String sql = "SELECT * FROM (SELECT RowNum = ROW_NUMBER() OVER(ORDER BY mahh), * FROM HangHoa"
				+ " WHERE MaHH LIKE ? OR TenHH LIKE ? OR donvitinh LIKE ? OR ghichu LIKE ? OR "
				+ "DonGiaThamKhao = ?) AS tempTable WHERE RowNum > (? * (? - 1)) "
				+ "AND RowNum <= (? * (? - 1)) + ? ORDER BY mahh";		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setString(2, "%" + searchText + "%");
			pstmt.setString(3, "%" + searchText + "%");
			pstmt.setString(4, "%" + searchText + "%");
			if (ValidateCommon.isValidDigitString(searchText)) {
				pstmt.setDouble(5, Double.valueOf(searchText));
			} else {
				pstmt.setDouble(5, -1); // Chèn 1 số âm bất kỳ
			}
			
			pstmt.setString(6, "20");
			pstmt.setString(7, String.valueOf(pageNumber));
			pstmt.setString(8, "20");
			pstmt.setString(9, String.valueOf(pageNumber));
			pstmt.setString(10, "20");			
			
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
