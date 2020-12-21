package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.HangHoa;
import model.bo.ShowProductListBO;

/**
 * Servlet implementation class ShowProductListServlet
 */
public class ShowProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
	
		if (session.getAttribute("accountInfor") == null) {
		     	   response.sendRedirect("login.jsp?error=1");
		}
		
		else {
			ShowProductListBO showProductListBO = new ShowProductListBO();
			//ArrayList<HangHoa> dsHangHoa = showProductListBO.GetDsHangHoa();
			
			String page = request.getParameter("page");
			int pageNumber = 1; //Mặc định trang 1 là trang đầu tiên
			
			if(page != null && "".equals(page)) {
				pageNumber = Integer.valueOf(page);
			}
			
			ArrayList<HangHoa> dsHangHoa = showProductListBO.getDsHangHoa(pageNumber);
			int totalPageNumber = showProductListBO.getTotalPageNumber();
			
			request.setAttribute("dsHangHoa", dsHangHoa);
			request.setAttribute("currentPageNumer", pageNumber);
			request.setAttribute("totalPageNumber", totalPageNumber);
			
			RequestDispatcher rd = null;
			request.setAttribute("dsHangHoa", dsHangHoa);
			rd = request.getServletContext()
					.getRequestDispatcher("/productList.jsp");
			rd.forward(request, response);
		}
		
		
	}
}
