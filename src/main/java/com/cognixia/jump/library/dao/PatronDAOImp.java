package com.cognixia.jump.library.dao;

import com.cognixia.jump.library.utility.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.models.BookCheckoutWithBook;
import com.cognixia.jump.library.models.Patron;
import com.cognixia.jump.library.models.PatronHistory;

public class PatronDAOImp implements PatronDAO {
	private Connection conn = ConnectionManager.getConnection();

	@Override
	public List<Patron> getAllPatrons() {
		List<Patron> patList = new ArrayList<Patron>();

		try (PreparedStatement pstmt = conn.prepareStatement("select * from patron");
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				int id = rs.getInt("patron_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");

				Patron pat = new Patron(id, firstName, lastName, username, password, frozen);

				patList.add(pat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patList;
	}

	@Override
	public Patron getPatronById(int patId) {
		Patron pat = null;

		try (PreparedStatement pstmt = conn.prepareStatement("select * from patron where patron_id = ?");) {
			pstmt.setInt(1, patId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("patron_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");

				pat = new Patron(id, firstName, lastName, username, password, frozen);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pat;
	}

	@Override
	public boolean addPatron(Patron pat) {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into patron values(null,?,?,?,?,?)")) {
			Utility.searchUserNameUtility(pat.getUsername(), conn);
			pstmt.setString(1, pat.getFirst_name());
			pstmt.setString(2, pat.getLast_name());
			pstmt.setString(3, pat.getUsername());
			pstmt.setString(4, pat.getPassword());
			pstmt.setBoolean(5, pat.isAccount_frozen());

			int count = pstmt.executeUpdate();

			if (count > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UsernameAlreadyExistsException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deletePatron(Patron pat) {
		try (PreparedStatement pstmt = conn.prepareStatement("delete from patron where patron_id = ?")) {
			pstmt.setInt(1, pat.getId());

			int count = pstmt.executeUpdate();

			if (count > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updatePatron(Patron pat) {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update patron set first_name = ?, last_name = ?, username = ?, password = ?, account_frozen = ? where patron_id = ?");
				PreparedStatement dbPat = conn.prepareStatement("select * from patron where patron_id = ?");) {
			dbPat.setInt(1, pat.getId());

			ResultSet rs = dbPat.executeQuery();

			rs.next();

			String originalUsername = rs.getString("username");

			if (!originalUsername.equals(pat.getUsername())) {
				Utility.searchUserNameUtility(pat.getUsername(), conn);
			}
			pstmt.setString(1, pat.getFirst_name());
			pstmt.setString(2, pat.getLast_name());
			pstmt.setString(3, pat.getUsername());
			pstmt.setString(4, pat.getPassword());
			pstmt.setBoolean(5, pat.isAccount_frozen());
			pstmt.setInt(6, pat.getId());

			int count = pstmt.executeUpdate();

			if (count > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UsernameAlreadyExistsException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean unfreezePatron(Patron pat) {
		pat.setAccount_frozen(false);

		if (updatePatron(pat)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean freezePatron(Patron pat) {
		pat.setAccount_frozen(true);

		if (updatePatron(pat)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Patron getPatronByUsername(String username) {
		Patron pat = null;

		try (PreparedStatement pstmt = conn.prepareStatement("select * from patron where username = ?");) {
			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("patron_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");

				pat = new Patron(id, firstName, lastName, username, password, frozen);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pat;
	}

	@Override
	public PatronHistory getPatronHistoryById(int patId) {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"select * from patron inner join book_checkout on patron.patron_id = book_checkout.patron_id inner join book on book.isbn = book_checkout.isbn where book_checkout.patron_id = ?")) {
			PatronHistory ph = null;

			pstmt.setInt(1, patId);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				ph = new PatronHistory(rs.getInt("patron_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("username"), rs.getString("password"), rs.getBoolean("account_frozen"));
				BookCheckoutWithBook bc = new BookCheckoutWithBook(rs.getInt("checkout_id"), patId, rs.getString("isbn"),
						rs.getDate("checkedout"), rs.getDate("due_date"), rs.getDate("returned"));
				ph.addHistory(bc);
				while (rs.next()) {
					bc = new BookCheckoutWithBook(rs.getInt("checkout_id"), patId, rs.getString("isbn"),
							rs.getDate("checkedout"), rs.getDate("due_date"), rs.getDate("returned"));
					ph.addHistory(bc);
				}
			}
			return ph;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	@Override
	public int getPatronBooksCheckedout(int patId) {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"select count(*) as books_rented from patron inner join book_checkout on patron.patron_id = book_checkout.patron_id inner join book on book.isbn = book_checkout.isbn where patron.patron_id = ? and book.rented = 1")) {
		
			pstmt.setInt(1, patId);

			ResultSet rs = pstmt.executeQuery();

			return rs.getInt("books_rented");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}

	public List<Patron> getAllAvailablePatrons() {
		List<Patron> patrons = new ArrayList<>();
		ResultSet rs = null;
		try (PreparedStatement state = conn.prepareStatement("select * from patron where account_frozen = ?")) {
			state.setString(1, "false");
			rs = state.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("patron_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");

				patrons.add(new Patron(id, firstName, lastName, username, password, frozen));

			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return patrons;
	}

	public List<Patron> getAllFrozenPatrons() {
		List<Patron> patrons = new ArrayList<>();
		ResultSet rs = null;
		try (PreparedStatement state = conn.prepareStatement("select * from patron where account_frozen = ?")) {
			state.setString(1, "true");
			rs = state.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("patron_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");

				patrons.add(new Patron(id, firstName, lastName, username, password, frozen));

			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return patrons;
	}

//	public static void main(String[] args)
//	{
//		PatronDAOImp test = new PatronDAOImp();
//		PatronHistory ph = test.getPatronHistoryById(1);
//		System.out.println(ph);
//	}

}
