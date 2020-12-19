package common;

public class ValidateCommon {
	public static boolean checkRequiredFields(String... strings) {
		
		boolean validData = true;
		String[] strs = strings;
		for (int i = 0; i < strs.length; i++) {
			if(strs[i] == null || "".equals(strs[i])) {
				return false;
			}
		}	
		return validData;
	}
	
	public static String validateProduct(String tenHH, String dvt, String donGiaTK, String ghiChu) {
		
		if(!checkRequiredFields(tenHH, dvt, donGiaTK, ghiChu)) {
		return "Required Fields error.";
		}
		
		if(!donGiaTK.matches("[0-9]{4,9}")) {
			return "Invalid DGTK error.";
		}
		
		return "No error";
	}
}
