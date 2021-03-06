<%@page import="model.bean.HangHoa"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Product List</title>
	</head>
	<body>
		<h1> TÊN CỬA HÀNG </h1>
	   <h2> QUẢN LÝ DANH MỤC HÀNG HÓA </h2>
	   
	   <%-- Check login status --%>
	   <%
           if (session.getAttribute("accountInfor") == null) {
        	   response.sendRedirect("login.jsp?error=1");
           }
           else {
       %>
       <%-- //End check login status --%>
       
       <%-- Print status when delete, add and edit --%>
       <%
       		String error = request.getParameter("message");
       %>
       <%= ("1".equals(error)? "Đã xóa thành công!" : "") %>
       <%= ("2".equals(error)? "Thêm mới thành công!" : "") %>
       <%= ("3".equals(error)? "Chỉnh sửa thành công!" : "") %>
       <%-- //End Print status when delete, add and edit --%>
       
       
	   <%-- Logic handling show product list --%>
	   <% ArrayList<HangHoa> dsHangHoa = (ArrayList<HangHoa>)request.getAttribute("dsHangHoa"); %>
	   
	   
	   <input type="button" onclick="location.href='ShowCreateProductServlet'" value="Tạo mới" />
	   
	   <% String searchText = request.getParameter("searchText") != null ? request.getParameter("searchText") : ""; %>
	   <form action="SearchProductServlet" method="post">
	   		<input type="text" name="searchText" value="<%=searchText%>"/>
	   		<input type="submit" name="Search" />
	   </form>
	   
	   <%-- Separate page --%>
	   <% 	int currentPageNumer = (Integer)request.getAttribute("currentPageNumer"); //Dữ liệu được trả từ server trả về
	   		int totalPageNumber = (Integer)request.getAttribute("totalPageNumber"); //Dữ liệu được trả từ server trả về
	   		
	   		System.out.print("currentPageNumer");
	   		System.out.print(currentPageNumer);
	   		
	   		System.out.print("totalPageNumber");
	   		System.out.print(totalPageNumber);
	   		
	   		
	   		
	   		int[] pageNumberList = new int[10]; //Tự set, client tự tính toán
	   		int pageQuantity = 0; //Tự tính toán trong lúc xử lý
	   		
	   		if(totalPageNumber <= 10){
	   			for (int j = 0; j < 10 && j < totalPageNumber; j++){
	   				pageNumberList[j] = j + 1;
	   				pageQuantity++;
	   			}
	   		}
	   		
	   		if (totalPageNumber > 10 && currentPageNumer <= 4) {
	      	  for (int j = 0; j < 10; j++) {
	      		  pageNumberList[j] = j + 1;
	      		  pageQuantity++;
	      	  }
	        }
	   		
	   		if (totalPageNumber > 10 && currentPageNumer >= totalPageNumber - 5){
	   			for (int j = 10; j > 0; j--){
	   				pageNumberList[10-j] = totalPageNumber - (j - 1);
	   				pageQuantity++;
	   			}
	   		}
	   		
	   		if	(totalPageNumber > 10 && currentPageNumer >= 5 && currentPageNumer <= (totalPageNumber - 5)){
	   			for(int j = 0; j < 10; j++){
	   				pageNumberList[j] = currentPageNumer - 3 + j;
	   				pageQuantity++;
	   			}	
	   		}
	   	%>
	   	<% 	if(currentPageNumer > 1){ %>
	   			<a href='ShowProductListServlet?page=1'>First</a>
	   			<a href='ShowProductListServlet?page=<%=currentPageNumer-1%>'>Previous</a>
	   	<% 	} 
		
	  	for(int k = 0; k < pageQuantity; k++)	{ 
	   			if	(pageNumberList[k] == currentPageNumer) { %>
	   			<a href='ShowProductListServlet?page=<%=pageNumberList[k]%>'><b><%=pageNumberList[k]%></b></a>
	   	<%} else { %>
	   			<a href='ShowProductListServlet?page=<%=pageNumberList[k]%>'><%=pageNumberList[k]%></a>
	   	<%	} 
	   	} 	%>
	   	
	   	<% if (currentPageNumer < totalPageNumber) { %>
		      <a href='ShowProductListServlet?page=<%=currentPageNumer+1%>'>Next</a>
		      <a href='ShowProductListServlet?page=<%=totalPageNumber%>'>Last</a>
   		<% } %>
   		
   		<%-- //End Separate page --%>
   		
	   <% int stt = (currentPageNumer - 1)*20 + 1; // 20 là số dòng trên 1 trang %>
	   <table border="1">
	      <tr>
	         <th>STT</th>
	         <th>Mã hàng</th>
	         <th>Tên hàng</th>
	         <th>Đơn giá tham khảo</th>
	         <th>Đơn vị tính</th>
	         <th>Ghi chú</th>
	         <th>Hành động</th>
	      </tr>
	      <% for (HangHoa hh : dsHangHoa) { %>
	      <tr>
	         <td><%= stt %></td>
	         <td><%= hh.getMaHH() %></td>
	         <td><%= hh.getTenHH() %></td>
	         <td><%= hh.getDonGiaThamKhao() %></td>
	         <td><%= hh.getDonViTinh() %></td>
	         <td><%= hh.getGhiChu()%></td>
	         <td>
	             <input type="button" 
	             		Onclick="location.href='ShowEditProductServlet?proId=<%=hh.getMaHH()%>'"
	             		value="Chỉnh sửa" />
	             <input type="button" 
	             		Onclick="deleteProduct('<%=hh.getMaHH()%>')" 
	             		value="Xóa" />
	             <a href="DeleteProductServlet?proId=<%= hh.getMaHH()%>" 
	             		Onclick="return confirm('Bạn có muốn xóa không?')">Xóa</a>
	         </td>
	         <% stt += 1; %>
	      </tr>
	      <% } %> 	<%-- Close for loop --%>
	     <% } %>  <%-- Close else condition --%>
	   </table> 
	   <%-- //End Logic handling show product list --%>
	   
	   <%-- Separate page --%>
	 <%-- <%
	   		int currentPageNumer = 1;
	    	boolean isLastPage = false;
	    	int totalPageNumber = 100;
	    	int[] pageNumberList = new int[10];
	    	int pageQuantity = 0;
	    	
	    	for (int j = 0; j < 10; j++){
	    		pageNumberList[j] = currentPageNumer + j;
	    		pageQuantity++;
	    	}
	   %>
	   <%
	   		for(int k = 0; k < pageQuantity; k++)	{ 
	   			if	(pageNumberList[k] == currentPageNumer) {%>
	   			<a><b><%=pageNumberList[k] %></b></a>
	   <%	} else { %>
	   			<a><%=pageNumberList[k] %></a>
	   <% 	} 
	   	}  %>  --%> 
	   	
	   <%-- //End Separate page --%>
	   
	   <div style="background-color: yellow; width: 100px"><a href="LogoutServlet">Đăng xuất</a></div>
	   
	   <script>
	   		function deleteProduct(proId)	{
	   			if(confirm('Bạn có muốn xóa không?'))	{
	   				location.href="DeleteProductServlet?proId=" + proId;
	   			}
	   		}
	   </script>	
	</body>
</html>