package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.HangHoa;

public class ShowProductListDAO extends BaseDAO {

	public ArrayList<HangHoa> getDsHangHoa() {
		// TODO Auto-generated method stub
		ArrayList<HangHoa> returnedList = new ArrayList<HangHoa>(); //Khoi tao ArrayList HangHoa
		
		Connection connection = getConnection();
		String sql = "SELECT * FROM HangHoa";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			HangHoa item = null;
			rs.next();
			
			while(rs.next())
			{
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
		return returnedList; // invalid account
	}
	
	public ArrayList<HangHoa> getDsHangHoa(int pageNumber) {
		ArrayList<HangHoa> tempList = new ArrayList<HangHoa>();
		ArrayList<HangHoa> returnedList = new ArrayList<HangHoa>();
		
		Connection connection = getConnection();
		String sql = "SELECT mahh, tenhh, dongiathamkhao, donvitinh, ghichu FROM HangHoa ORDER BY mahh";		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			HangHoa item = null;
			
			while (rs.next()) {
				item = new HangHoa();
				item.setMaHH(rs.getString("mahh"));
				item.setTenHH(rs.getString("tenhh"));
				item.setDonGiaThamKhao(rs.getInt("dongiathamkhao"));
				item.setDonViTinh(rs.getString("donvitinh"));
				item.setGhiChu(rs.getString("ghichu"));
				
				tempList.add(item);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, rs);
		}	
		
		// Ở đây đang giả định là mỗi trang sẽ có 20 dòng.
		// Tính toán số trang:
		int pageQuantity = (int)Math.ceil(tempList.size() / 20.0);
		
		if (pageNumber > pageQuantity || pageNumber <=0) {
			return returnedList; // Trả về danh sách rỗng, vì pageNumber không hợp lệ
		} else {
			for (int i = (pageNumber - 1) * 20; (i < pageNumber * 20) && (i < tempList.size()); i++) {
				returnedList.add(tempList.get(i));
			}
		}
		return returnedList;
	}
	
	public ArrayList<HangHoa> getDsHangHoaBySQL(int pageNumber) {
		ArrayList<HangHoa> returnedList = new ArrayList<HangHoa>();
		
		Connection connection = getConnection();
		String sql = "SELECT * FROM (SELECT RowNum = ROW_NUMBER() OVER (ORDER BY mahh), * FROM HangHoa) AS tempTable WHERE RowNum > (? * (? - 1)) AND RowNum <= (? * (? - 1)) + ? ORDER BY mahh";		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "20");
			pstmt.setString(2, String.valueOf(pageNumber));
			pstmt.setString(3, "20");
			pstmt.setString(4, String.valueOf(pageNumber));
			pstmt.setString(5, "20");			
			
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

	
	public int getTotalPageNumber() {
		Connection connection = getConnection();
		String sql = "SELECT count(mahh) as tongsodong FROM HangHoa";		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalPageNumber = 0;
		
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				totalPageNumber = rs.getInt("tongsodong"); //Số record
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, rs);
		}
		
		return (int)Math.ceil(totalPageNumber / 20.0);
	}


}
