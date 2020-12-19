package model.bo;

import common.ValidateCommon;
import model.bean.HangHoa;
import model.dao.EditProductDAO;

public class EditProductBO {
	EditProductDAO editProductDAO = new EditProductDAO();
	
	public HangHoa getProductInfor(String proId) {
		return editProductDAO.getProductInfor(proId);
	}

	public String editProduct(String maHH, String tenHH, String dvt, String donGiaTK, String ghiChu) {
		String returnedMessage = null;
		
		String tempValidate = ValidateCommon.validateProduct(tenHH, dvt, donGiaTK, ghiChu);
		if(!"No error".equals(tempValidate)) {
			return tempValidate;
		}
		
		returnedMessage = editProductDAO.ediProduct(maHH, tenHH, dvt, donGiaTK, ghiChu);
		
		return returnedMessage;
	}
}
