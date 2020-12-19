package model.bo;

import common.StringCommon;
import common.ValidateCommon;
import model.dao.CreateProductDAO;

public class CreateProductBO {
	CreateProductDAO createProductDAO = new CreateProductDAO();

	public String insertProduct(String tenHH, String dvt, String donGiaTK, String ghiChu) {
		// TODO Auto-generated method stub
		
		String returnedString = null;
		
		String tempValidate = ValidateCommon.validateProduct(tenHH, dvt, donGiaTK, ghiChu);
		if (!"No error".equals(tempValidate)) {
			return tempValidate;
		}
		
		//Lặp tối đa 10 lần để xử lỗi trùng MaHH
		for(int i = 1; i <=10; i++) {
			//Lấy mã HH mới nhất có trong DB
			String lastestMaHH = createProductDAO.getLastestMaHH();
			
			//Tách HH và số thứ tự ra riêng (eg: HH & 002)
			//Tăng số thứ tự lên 1
			//Gộp số thứ tự mới với HH lại với nhau.
			if (lastestMaHH == null) {
				lastestMaHH = "HH001";
			}
			else {
//				if(orderNumber >= 10 && orderNumber < 100) {
//					lastestMaHH = "HH0" + ++orderNumber;
//				}
//				else if(orderNumber > 0 && orderNumber < 10) {
//					lastestMaHH = "HH00" + ++orderNumber;
//				}
//				else {
//					orderNumber = Integer.valueOf(lastestMaHH.substring(2, 5));
//					lastestMaHH = "HH" + ++orderNumber;
//				}
				//Cách trên không mang tính tổng quát
				
				
				//Dùng thuật toán này trong Hàm StringCommon sẽ mang tinh tổng quát hơn
				long orderNumber = Long.valueOf(lastestMaHH.substring(2, 5));
				orderNumber++;
				lastestMaHH = "HH" + StringCommon.convertNumberToString(orderNumber, 3);
				
			}

			//Truyền mã hàng hóa mới vào trong CreateProductDAO.insertProduct
			String returnedMessage = createProductDAO.insertProduct(lastestMaHH, tenHH, dvt, donGiaTK, ghiChu);
			System.out.println("Lần " + i + " --- lastestMaHH --- " + lastestMaHH + 
					" --- returnedMessage --- " + returnedMessage);
			System.out.println("Đã chèn record: " + lastestMaHH);
			
			if("Duplicate ID Error.".contentEquals(returnedMessage)) {
				returnedString = "Duplicate ID Error";
				continue;
			}
			else if("No error.".equals(returnedMessage)){
				returnedString = "No error.";
				break;
			}
		}
		return returnedString;
	}
}
