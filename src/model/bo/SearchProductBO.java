package model.bo;

import java.util.ArrayList;

import model.bean.HangHoa;
import model.dao.SearchProductDAO;

public class SearchProductBO {
	SearchProductDAO searchProductDAO = new SearchProductDAO();

	public ArrayList<HangHoa> getDsHangHoa(String searchText) {
		// TODO Auto-generated method stub
		return searchProductDAO.getDsHangHoa(searchText);
	}

}
