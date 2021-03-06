package com.cognixia.jump.library.dao;

import com.cognixia.jump.library.utility.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.models.Librarian;

public class LibrarianDAOImp implements LibrarianDAO
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
			Utility.searchUserNameUtility(lib.getUsername(), conn);
			pstmt.setString(1, lib.getUsername());
			pstmt.setString(2, lib.getPassword());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
			
		} catch(SQLException e) 
		{
			e.printStackTrace();
		} catch (UsernameAlreadyExistsException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteLibrarian(int libId)
	{
		try(PreparedStatement pstmt = conn.prepareStatement("delete from librarian where librarian_id = ?")) 
		{	
			pstmt.setInt(1, libId);
			
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
		try(
				PreparedStatement pstmt = conn.prepareStatement("update librarian set username = ?, password = ? where librarian_id = ?");
				PreparedStatement dbLib = conn.prepareStatement("select * from librarian where librarian_id = ?");
			) 
		{
			dbLib.setInt(1, lib.getId());
			
			ResultSet rs = dbLib.executeQuery();
			
			rs.next();
			
			String originalUsername = rs.getString("username");
			
			if(!originalUsername.equals(lib.getUsername()))
			{
				Utility.searchUserNameUtility(lib.getUsername(), conn);
			}
			
			
			pstmt.setString(1, lib.getUsername());
			pstmt.setString(2, lib.getPassword());
			pstmt.setInt(3, lib.getId());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
			
		} catch(SQLException | UsernameAlreadyExistsException e) 
		{
			e.printStackTrace();
		} 
		
		return false;
	}

	@Override
	public Librarian getLibrarianByUsername(String username)
	{
		Librarian lib = null;
		
		try (
				PreparedStatement pstmt = conn.prepareStatement("select * from librarian where username = ?");
			)
		{
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next())
			{
				int id = rs.getInt("librarian_id");
				String password = rs.getString("password");
				
				lib = new Librarian(id, username, password);
				
			}
			
		} catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return lib;
	}
	
//	public static void main(String[] args)
//	{
//		LibrarianDAOImp test = new LibrarianDAOImp();
//		List<Librarian> libList = test.getAllLibrarians();
//		
//		for(Librarian l: libList)
//		{
//			System.out.println(l);
//		}
//		
//		Librarian l = test.getLibrarianById(1);
//		l.setUsername("librarian2");
//		test.updateLibrarian(l);
//	}

}