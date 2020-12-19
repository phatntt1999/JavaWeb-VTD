<%@page import="common.StringCommon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.bean.HangHoa"%>
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=UTF-8">
		<title>Scofield - Login</title>
	</head>
	<body>
		CHỈNH SỬA HÀNG HÓA
		<br>
		<%
           if (session.getAttribute("accountInfor") == null) {
        	   response.sendRedirect("login.jsp?error=1");
           }
           else { 
       	%>
       	
       <% String error = request.getParameter("message"); %>
       <%=("2".equals(error)) ? "Đã có lỗi không xác định được nguyên nhân, bạn thử vui lòng thao tác lại, hoặc liên hệ với người quản trị hệ thống!" : "" %>
       <%=("3".equals(error)) ? "Lỗi chưa nhập đầy đủ tên hàng hóa, đơn vị tính, đơn giá tham khảo!" : "" %>
       <%=("4".equals(error)) ? "Đơn giá tham khảo phải là số!" : "" %>
       
       <% 
           	String maHH = null;
       		String tenHH = null;
       		String dvt = null;
       		String donGiaTK = null;
       		String ghiChu = null;
       		
       		if(error == null || "".equals(error)){ 
       			// Vào màn hình edit lần đầu
       			HangHoa hangHoa = (HangHoa)request.getAttribute("hangHoa");
       			
       			maHH = hangHoa.getMaHH();
       			tenHH = hangHoa.getTenHH();
       			dvt = hangHoa.getDonViTinh();
       			donGiaTK = StringCommon.convertDoubleToStringWithComma(hangHoa.getDonGiaThamKhao());
       			ghiChu = hangHoa.getGhiChu();
       		}
       		else{
       			// Quay lại màn hình edit khi có lỗi trả về
       			maHH = request.getParameter("maHH") != null? request.getParameter("maHH") : "";
       			tenHH = request.getParameter("tenHH") != null ? request.getParameter("tenHH") : "";
       			dvt = request.getParameter("dvt") != null ? request.getParameter("dvt") : "";
       			donGiaTK = request.getParameter("donGiaTK") != null ? request.getParameter("donGiaTK") : "";
       			ghiChu = request.getParameter("ghiChu") != null ? request.getParameter("ghiChu") : "";
       		}
       
       %>
		
	    <form action="EditProductServlet" method="post">
	        Mã hàng: <input type="text" value="<%=maHH%>" disabled="disabled">
	        <input type="hidden" name="maHH" value="<%=maHH%>">
	        Tên hàng: <input type="text" name="tenHH" id="tenHH" value="<%=tenHH%>">
	        Đơn vị tính: <input type="text" name="dvt" id="dvt" value="<%=dvt%>">
	        Đơn giá tham khảo: <input type="text" name="donGiaTK" id="donGiaTK" value="<%=donGiaTK%>">
	        Ghi chú: <input type="text" name="ghiChu" value="<%=ghiChu%>">
	      
	        <input type="submit" onclick="return true" value="Sửa">
	        <input type="reset" value="Hủy bỏ">
	    </form>
	    
	    <script>
	    	function validate() {
	    		var errorMessage = "";

	    		alert(errorMessage);
	    		alert(document.getElementById("dongiaTK").value);
	    		alert("ABC");
	    		
	    		if (document.getElementById("tenHH").value == ""){
	    			alert('Hãy nhập tên hàng hóa.');
	    			errorMessage = errorMessage + 'Hãy nhập tên hàng hóa.';
	    			alert("errorMessage");
	    			return false;
	    		}
	    		if (document.getElementById("dvt").value == ""){
	    			//alert('Hãy nhập đơn vị tính.');
	    			//return false
	    			errorMessage = errorMessage + ' Hãy nhập đơn vị tính.';
	    			alert("errorMessage");
	    			return false;
	    		}
	    		if (document.getElementById("dongiaTK").value == ""){
	    			//alert('Hãy nhập đơn giá tham khảo.');
	    			//return false
	    			errorMessage = errorMessage + ' Hãy nhập đơn giá tham khảo.';
	    			alert("errorMessage");
	    			return false;
	    		}
	    		if (!(/^\d+$/.test(document.getElementById("donGiaTK").value))) {
	 			   	errorMessage = errorMessage + 'Đơn giá tham khảo phải là một số nguyên';
	 			  	console.log(errorMessage);
	 		    }
	    		
	    		if (errorMessage != ""){
	    			alert(errorMessage);
	    		}
	    		
	    		
	    		return errorMessage == "";
	    			
	    	}
	    </script>
	    <% } %>
	</body>
</html>