package common;

import java.text.DecimalFormat;

public class StringCommon {
	public static String convertNumberToString (long number, int digit) {
				number = number + 10000000000000000L; // 16 chữ số 0
				String returnedStr = String.valueOf(number); // Có chiều dài là 17 chữ số
				return returnedStr.substring(returnedStr.length() - digit);
	}
	
	public static String convertDoubleToStringWithComma(double d) {
		return new DecimalFormat("###,###").format(d);
	}
	
//	public static void main(String[] args) {
//		System.out.println(convertNumberToString(10, 3));
//	}
}
