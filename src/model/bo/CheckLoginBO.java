package model.bo;

import model.dao.CheckLoginDAO;

public class CheckLoginBO {
	
	CheckLoginDAO checkLoginDAO = new CheckLoginDAO();

	public int getAccountRole(String tenDangNhap, String matKhau) {
		return checkLoginDAO.getAccountRole(tenDangNhap, matKhau);
	}

}
