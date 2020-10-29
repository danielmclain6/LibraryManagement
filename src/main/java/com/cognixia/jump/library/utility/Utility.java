package com.cognixia.jump.library.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jump.library.dao.LibrarianDAO;
import com.cognixia.jump.library.dao.LibrarianDAOImp;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.dao.UsernameAlreadyExistsException;
import com.cognixia.jump.library.models.Librarian;
import com.cognixia.jump.library.models.Patron;

public class Utility
{
	public static boolean searchUserNameUtility(String username, Connection conn) throws UsernameAlreadyExistsException
	{
		try(
				PreparedStatement pstmt = conn.prepareStatement("select (select count(*) from librarian where username = ?) + (select count(*) from patron where username = ?) as username_count;")
		   )
		{
			pstmt.setString(1, username);
			pstmt.setString(2, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			int count = rs.getInt("username_count");
			
			if(count > 0)
			{
				throw new UsernameAlreadyExistsException(username);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static Object getUserByUsername(String username)
	{
		LibrarianDAO libDao = new LibrarianDAOImp();
		Librarian lib = libDao.getLibrarianByUsername(username);
		if(lib != null)
		{
			return lib;
		}
		else
		{
			PatronDAO patDao = new PatronDAOImp();
			Patron pat = patDao.getPatronByUsername(username);
			if(pat != null)
			{
				return pat;
			}
			else
			{
				return null;
			}
		}
	}
	
//	public static void main(String[] args)
//	{
//		Object test = getUserByUsername("librarian1");
//		
//		if(test instanceof Patron)
//		{
//			System.out.println("Woo its a patron");
//			Patron p = (Patron)test;
//			System.out.println(p);
//		}
//		else if(test instanceof Librarian)
//		{
//			System.out.println("Woo its a librarian");
//			Librarian l = (Librarian)test;
//			System.out.println(l);
//		}
//	}
}
