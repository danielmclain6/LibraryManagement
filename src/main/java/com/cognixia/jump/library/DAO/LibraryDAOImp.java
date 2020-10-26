package com.cognixia.jump.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.library.connection.ConnectionManager;

public class LibraryDAOImp implements LibrarianDAO
{
	private Connection conn = ConnectionManager.getConnection();

	public List<Librarian> getAllLibrarians()
	{
		List<Librarian> libList = new ArrayList<>();
		
		
		try (
				PreparedStatement pstmt = conn.prepareStatement("select * from librarian");
				ResultSet rs = pstmt.executeQuery(); 
			)
		{
			while(rs.next())
			{
				int id = rs.getInt("librarian_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				Librarian lib = new Librarian(id, username, password);
				
				libList.add(lib);
			}
			
		} catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return libList;
	}
	
	public Librarian getLibrarianById(int libId) throws LibrarianNotFoundException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addLibrarian(Librarian lib)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteLibraian(int libId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateLibrarian(Librarian lib)
	{
		// TODO Auto-generated method stub
		return false;
	}

}