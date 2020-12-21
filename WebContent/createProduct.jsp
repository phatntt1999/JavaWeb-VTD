<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=UTF-8">
		<title>Scofield - Login</title>
	</head>
	<body>
		TẠO MỚI HÀNG HÓA
		<br>
		<%
           if (session.getAttribute("accountInfor") == null) {
        	   response.sendRedirect("login.jsp?error=1");
           }
           else { 
       	%>
       	
       <% String error = request.getParameter("message"); %>
       <%=("1".equals(error)) ? "Lỗi trùng mã hàng hóa, bạn vui lòng thao tác lại!" : "" %>
       <%=("2".equals(error)) ? "Đã có lỗi không xác định được nguyên nhân, bạn thử vui lòng thao tác lại, hoặc liên hệ với người quản trị hệ thống!" : "" %>
       <%=("3".equals(error)) ? "Lỗi chưa nhập đầy đủ tên hàng hóa, đơn vị tính, đơn giá tham khảo!" : "" %>
       <%=("4".equals(error)) ? "Đơn giá tham khảo phải là số!" : "" %>
       
       
		<% String tenHH = request.getParameter("tenHH") != null ? request.getParameter("tenHH") : ""; %>
		<% String dvt = request.getParameter("dvt") != null ? request.getParameter("dvt") : ""; %>
		<% String donGiaTK = request.getParameter("donGiaTK") != null ? request.getParameter("donGiaTK") : ""; %>
		<% String ghiChu = request.getParameter("ghiChu") != null ? request.getParameter("ghiChu") : ""; %>
		
	    <form action="CreateProductServlet" method="post">
	        <!-- Mã hàng: <input type="text" name="maHH"> -->
	        Tên hàng: <input type="text" name="tenHH" id="tenHH" value="<%=tenHH%>">
	        Đơn vị tính: <input type="text" name="dvt" id="dvt" value="<%=dvt%>">
	        Đơn giá tham khảo: <input type="text" name="donGiaTK" id="donGiaTK" value="<%=donGiaTK%>">
	        Ghi chú: <input type="text" name="ghiChu" id="ghiChu" value="<%=ghiChu%>">
	      
	        <input type="submit" onclick="return true" value="Thêm">
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