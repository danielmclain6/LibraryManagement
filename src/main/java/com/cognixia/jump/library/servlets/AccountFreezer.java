package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.models.Patron;

/**
 * Servlet implementation class AccountFreezer
 */
@WebServlet("/freeze_patron")
public class AccountFreezer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatronDAO patronDao;
	
	@Override
	public void init() {
		patronDao = new PatronDAOImp();
	}

	@Override
	public void destroy() {
		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if(!(boolean)session.getAttribute("isLibrarian")) {
			response.sendRedirect("/LibraryManager");
		}
		String action = request.getServletPath();
		System.out.println("where we came from " + action);
		
		int id = Integer.parseInt(request.getParameter("id"));
		Patron patron = patronDao.getPatronById(id);
		boolean isFrozen = patron.isAccount_frozen();
		patron.setAccount_frozen(!isFrozen);
		patronDao.updatePatron(patron);
		
		response.sendRedirect("/LibraryManager/patrons?patron_id=" + patron.getId());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
