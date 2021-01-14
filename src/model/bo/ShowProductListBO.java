package model.bo;

import java.util.ArrayList;
import model.bean.HangHoa;
import model.dao.ShowProductListDAO;

public class ShowProductListBO {
	ShowProductListDAO showProductListDAO = new ShowProductListDAO();
	
//	public ArrayList<HangHoa> getDsHangHoa() {
//		// TODO Auto-generated method stub	
//		return showProductListDAO.getDsHangHoa();
//	}
	
	//getDsHangHoa cách 1. Đổ hết dữ liệu vào 1 ArrayList
	public ArrayList<HangHoa> getDsHangHoa(int pageNumber) {
		return showProductListDAO.getDsHangHoa(pageNumber);
	}
	
	//getDsHangHoa cách 2. Gọi trang nào request lên server trả về trang đó.
//	public ArrayList<HangHoa> getDsHangHoa(int pageNumber) {
//		return showProductListDAO.getDsHangHoaBySQL(pageNumber);
//	}
	

	public int getTotalPageNumber() {
		return showProductListDAO.getTotalPageNumber();
	}
	

}
