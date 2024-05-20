package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import Controllers.AddIncomeServlet;

public class TransactionDTOImplementation implements TransactionDTO {

	private static  Connection conn=null;
	private static TransactionDTOImplementation instance;
	
	
	private TransactionDTOImplementation() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "sql123");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static TransactionDTOImplementation getInstance() {
		if(instance==null) {
			instance=new TransactionDTOImplementation();
		}
		return instance;
	}

	private static final String insertData="insert into transactions values(?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String getTotalIncome="SELECT total_income,total_expense,balance FROM transactions where user_id=? ORDER BY id DESC LIMIT 1;";
	private static final String display="select * from transactions where user_id = ? order by transaction_date,transaction_time ; ";
	private static final String getdetails="SELECT SUM(income),SUM(expense) FROM transactions WHERE user_id=? AND transaction_date BETWEEN DATE(?) AND DATE(?) ORDER BY id ;";
	private static final String getPassword="select user_password from users where mobile_number=?;";
	private static final String getUserId="select user_id from users where mobile_number=?;";
	private static final String addUser="insert into users values(?,?,?,?,?);";
	private static final String monthlyDetails="SELECT transaction_date,category,income,expense FROM transactions WHERE user_id=? AND transaction_date BETWEEN DATE(?) AND DATE(?) ORDER BY transaction_date,transaction_time ;";
	private static final String todayDetails="SELECT transaction_date,category,income,expense FROM transactions WHERE user_id=? AND transaction_date=DATE(?) ORDER BY transaction_date,transaction_time ;";

	@Override
	public int addIncome(Transaction T) {
		try {
			double totalIncome=T.getIncome();
			double totalExpense=0;
			PreparedStatement pstmt1=conn.prepareStatement(getTotalIncome);
			pstmt1.setInt(1,T.getUser().getId());
			ResultSet rs= pstmt1.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getDouble(1)+T.getIncome()+" "+rs.getDouble(2));
				totalIncome=rs.getDouble(1)+T.getIncome();
				totalExpense=rs.getDouble(2);
			}
			PreparedStatement pstmt=conn.prepareStatement(insertData);
			pstmt.setInt(1, 0);
			pstmt.setDouble(2, T.getIncome());
			pstmt.setDouble(3,T.getExpense());
			pstmt.setString(4,T.getCategory());
			pstmt.setString(5,T.getPaymentMethod());
			pstmt.setString(6,T.getNote());
			pstmt.setDate(7,T.getDate());
			pstmt.setTime(8,T.getTime());
			pstmt.setDouble(9,totalIncome);
			pstmt.setDouble(10,totalExpense);
			pstmt.setDouble(11,totalIncome-totalExpense);
			pstmt.setInt(12,T.getUser().getId());
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public int addExpense(Transaction T) {
		try {
			double totalIncome=0;
			double totalExpense=T.getExpense();
			PreparedStatement pstmt1=conn.prepareStatement(getTotalIncome);
			pstmt1.setInt(1,T.getUser().getId());
			ResultSet rs= pstmt1.executeQuery();
			if(rs.next()) {
				totalIncome=rs.getDouble(1);
				totalExpense=rs.getDouble(2)+T.getExpense();
			}
			PreparedStatement pstmt=conn.prepareStatement(insertData);
			pstmt.setInt(1, 0);
			pstmt.setDouble(2, T.getIncome());
			pstmt.setDouble(3,T.getExpense());
			pstmt.setString(4,T.getCategory());
			pstmt.setString(5,T.getPaymentMethod());
			pstmt.setString(6,T.getNote());
			pstmt.setDate(7,T.getDate());
			pstmt.setTime(8,T.getTime());
			pstmt.setDouble(9,totalIncome);
			pstmt.setDouble(10,totalExpense);
			pstmt.setDouble(11,totalIncome-totalExpense);
			pstmt.setInt(12,T.getUser().getId());
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<Transaction> display(User user) {
		try {
			PreparedStatement pstmt =conn.prepareStatement(display);
			pstmt.setInt(1, user.getId());
			ResultSet rs=pstmt.executeQuery();
			ArrayList<Transaction> transactionsList=new ArrayList<>();
			Transaction t=null;
			while(rs.next()) {
				t=new Transaction();
				t.setId(rs.getInt(1));
				t.setIncome(rs.getDouble(2));
				t.setExpense(rs.getDouble(3));
				t.setCategory(rs.getString(4));
				t.setPaymentMethod(rs.getString(5));
				t.setNote(rs.getString(6));
				t.setDate(rs.getDate(7));
				System.out.println(rs.getDate(7));
				t.setTime(rs.getTime(8));
				System.out.println(rs.getTime(8));
				t.setTotalIncome(rs.getDouble(9));
				t.setTotalExpense(rs.getDouble(10));
				t.setBalance(rs.getDouble(11));
				t.getUser().setId(rs.getInt(12));
				transactionsList.add(t);
			}
			PreparedStatement pstmt1=conn.prepareStatement(getTotalIncome);
			pstmt1.setInt(1,user.getId());
			ResultSet rs1= pstmt1.executeQuery();
			if(rs1.next() && !transactionsList.isEmpty()) {
				Transaction T = transactionsList.get(transactionsList.size()-1);
				T.setTotalIncome(rs1.getDouble(1));
				T.setTotalExpense(rs1.getDouble(2));
				T.setBalance(rs1.getDouble(3));
				
			}
			return transactionsList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}



	@Override
	public Transaction getDetail(Transaction t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstmt=conn.prepareStatement(getdetails);
			pstmt.setInt(1, t.getUser().getId());
			pstmt.setString(3,t.getEndpoint());
			pstmt.setString(2,t.getStartpoint());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				t.setTotalIncome(rs.getDouble(1));
				t.setTotalExpense(rs.getDouble(2));
				t.setBalance(rs.getDouble(1)-rs.getDouble(2));
				return t;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int login(User user) {
		PreparedStatement pstmt1;
		PreparedStatement pstmt2;
		try {
			pstmt1 = conn.prepareStatement(getPassword);
			pstmt1.setString(1,user.getMobile());
			ResultSet rs1= pstmt1.executeQuery();
			if(rs1.next()) {
				String pass=rs1.getString(1);
				String pass2=user.getPassword();
				if(pass.equals(pass2)) {
					pstmt2 = conn.prepareStatement(getUserId);
					pstmt2.setString(1,user.getMobile());
					ResultSet rs2= pstmt2.executeQuery();
					if(rs2.next()) {
						return rs2.getInt(1);
					}
				}else {
					return -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int register(User user) {
		PreparedStatement pstmt1;
		try {
			pstmt1 = conn.prepareStatement(addUser);
			pstmt1.setInt(1,0);
			pstmt1.setString(2,user.getName());
			pstmt1.setString(3,user.getEmail());
			pstmt1.setString(4,user.getMobile());
			pstmt1.setString(5,user.getPassword());
			return pstmt1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Transaction> month(Transaction t) {
		ArrayList<Transaction> transactionsList=new ArrayList<>();
		Transaction T=null;
		try {
			PreparedStatement pstmt=conn.prepareStatement(monthlyDetails);
			pstmt.setInt(1, t.getUser().getId());
			pstmt.setString(3,t.getEndpoint());
			pstmt.setString(2,t.getStartpoint());
			ResultSet rs=pstmt.executeQuery();
			double totalIncome=0;
			double totalExpense=0;
			double balance=0;
			while(rs.next()) {
				T=new Transaction();
				T.setDate(rs.getDate(1));
				T.setCategory(rs.getString(2));
				T.setIncome(rs.getDouble(3));
				T.setExpense(rs.getDouble(4));
				totalIncome+=rs.getDouble(3);
				totalExpense+=rs.getDouble(4);
				if(rs.isLast()) {
					T.setTotalIncome(totalIncome);
					T.setTotalExpense(totalExpense);
					T.setBalance(totalIncome-totalExpense);
				}
				transactionsList.add(T);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionsList;
	}

	@Override
	public ArrayList<Transaction> today(Transaction t) {
		ArrayList<Transaction> transactionsList=new ArrayList<>();
		Transaction T=null;
		try {
			PreparedStatement pstmt=conn.prepareStatement(todayDetails);
			pstmt.setInt(1, t.getUser().getId());
			pstmt.setString(2,t.getStartpoint());
			ResultSet rs=pstmt.executeQuery();
			double totalIncome=0;
			double totalExpense=0;
			double balance=0;
			while(rs.next()) {
				T=new Transaction();
				T.setDate(rs.getDate(1));
				T.setCategory(rs.getString(2));
				T.setIncome(rs.getDouble(3));
				T.setExpense(rs.getDouble(4));
				totalIncome+=rs.getDouble(3);
				totalExpense+=rs.getDouble(4);
				if(rs.isLast()) {
					T.setTotalIncome(totalIncome);
					T.setTotalExpense(totalExpense);
					T.setBalance(totalIncome-totalExpense);
				}
				transactionsList.add(T);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionsList;
	}

	
}
