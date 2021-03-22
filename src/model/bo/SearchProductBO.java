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

	public int getTotalPageNumber(String searchText) {
		// TODO Auto-generated method stub
		return searchProductDAO.getTotalPageNumber(searchText);
	}

	public ArrayList<HangHoa> getDsHangHoa(String searchText, int pageNumber) {
		// TODO Auto-generated method stub
		return searchProductDAO.getDsHangHoa(searchText, pageNumber);
	}


}
