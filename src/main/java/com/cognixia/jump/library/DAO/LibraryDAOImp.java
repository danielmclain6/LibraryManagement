package com.cognixia.jump.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.models.Librarian;

public class LibraryDAOImp implements LibrarianDAO
{
	private Connection conn = ConnectionManager.getConnection();

	@Override
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
	
	@Override
	public Librarian getLibrarianById(int libId)
	{
		Librarian lib = null;
		
		try (
				PreparedStatement pstmt = conn.prepareStatement("select * from librarian where librarian_id = ?");
			)
		{
			pstmt.setInt(1, libId);
			
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next())
			{
				int id = rs.getInt("librarian_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				lib = new Librarian(id, username, password);
				
			}
			
		} catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return lib;
	}

	@Override
	public boolean addLibrarian(Librarian lib)
	{
		try(PreparedStatement pstmt = conn.prepareStatement("insert into librarian values(null,?,?)")) 
		{
			
			pstmt.setString(1, lib.getUsername());
			pstmt.setString(2, lib.getPassword());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
			
		} catch(SQLException e) 
		{
			e.printStackTrace();
		} 
		
		return false;
	}

	@Override
	public boolean deleteLibrarian(Librarian lib)
	{
		try(PreparedStatement pstmt = conn.prepareStatement("delete from librarian where librarian_id = ?")) 
		{	
			pstmt.setInt(1, lib.getId());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) 
			{
				return true;
			}
			
			
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateLibrarian(Librarian lib)
	{
		try(PreparedStatement pstmt = conn.prepareStatement("update librarian set username = ?, password = ? where librarian_id = ?")) 
		{
			pstmt.setString(1, lib.getUsername());
			pstmt.setString(2, lib.getPassword());
			pstmt.setInt(3, lib.getId());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
			
		} catch(SQLException e) 
		{
			e.printStackTrace();
		} 
		
		return false;
	}
	
//	public static void main(String[] args)
//	{
//		LibraryDAOImp test = new LibraryDAOImp();
//		List<Librarian> libs = test.getAllLibrarians();
//		
//		Librarian lib = test.getLibrarianById(1);
//
//		for(Librarian l: libs)
//		{
//			System.out.println(l);
//		}
//		
//		System.out.println(lib);
//	//	lib.setUsername("pickles");
//	//	test.addLibrarian(lib);
//		
//	//	lib.setUsername("noodles");
//		System.out.println(test.deleteLibrarian(lib));
//		libs = test.getAllLibrarians();
//		for(Librarian l: libs)
//		{
//			System.out.println(l);
//		}
//		
////		libs = test.getAllLibrarians();
////		
////		for(Librarian l: libs)
////		{
////			System.out.println(l);
////		}
//		
//	}

}