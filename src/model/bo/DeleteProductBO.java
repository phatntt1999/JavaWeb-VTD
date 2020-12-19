package model.bo;

import model.dao.DeleteProductDAO;

public class DeleteProductBO {

	public void deleteProduct(String proId) {
		DeleteProductDAO deleteProductDAO = new DeleteProductDAO();
		
		deleteProductDAO.deleteProduct(proId);
	}

}
