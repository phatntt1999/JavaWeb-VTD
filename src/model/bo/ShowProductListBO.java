package model.bo;

import java.util.ArrayList;
import model.bean.HangHoa;
import model.dao.ShowProductListDAO;

public class ShowProductListBO {

	public ArrayList<HangHoa> GetDsHangHoa() {
		// TODO Auto-generated method stub
		ShowProductListDAO showProductListDAO = new ShowProductListDAO();
		
		return showProductListDAO.getDsHangHoa();
	}
	

}
