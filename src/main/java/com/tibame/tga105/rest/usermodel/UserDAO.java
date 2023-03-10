package com.tibame.tga105.rest.usermodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.tga105.rest.restordermodel.RestOrderVO;

public class UserDAO implements UserDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";

	String url = "jdbc:mysql://localhost:3306/coffeebean?serverTimezone=Asia/Taipei";

	String memid = "root";

	String passwd = "password";
	
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	private static final String INSERT_STMT = 
		"INSERT INTO user (user_email,user_pwd,user_name,user_nickname,user_address,user_tel,user_vip_level_id,user_datetime,user_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT user_id,user_email,user_pwd,user_name,user_nickname,user_address,user_tel,user_vip_level_id,user_datetime,user_status FROM user order by user_id";
	private static final String GET_ONE_STMT = 
		"SELECT user_id,user_email,user_pwd,user_name,user_nickname,user_address,user_tel,user_vip_level_id,user_datetime,user_status FROM user where user_id = ?";
	private static final String DELETE_USER = 
		"DELETE FROM user where user_id = ?";
	private static final String UPDATE = 
		"UPDATE user set user_email=?, user_pwd=?, user_name=?, user_nickname=?, user_address=?, user_tel=?, user_vip_level_id=?, user_datetime=? ,user_status=? where user_id = ?";

	private static final String GET_Restorders_ByUserid_STMT = "SELECT order_id,user_id,rest_id,orderstatus_id,order_time,order_memo FROM rest_order where user_id = ? order by order_id";
	private static final String DELETE_RESTORDERs = "DELETE FROM rest_order where order_status_id = ?";
	
	
	@Override
	public void insert(UserVO userVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, memid, passwd);
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, userVO.getUseremail());
			pstmt.setString(2, userVO.getUserpwd());
			pstmt.setString(3, userVO.getUsername());
			pstmt.setString(4, userVO.getUsernickname());
			pstmt.setString(5, userVO.getUseraddress());
			pstmt.setString(6, userVO.getUsertel());
			pstmt.setInt(7, userVO.getUserviplevelid());
			pstmt.setDate(8, userVO.getUserdatetime());
			pstmt.setBoolean(9, userVO.getUserstatus());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(UserVO userVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, memid, passwd);
			
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, userVO.getUseremail());
			pstmt.setString(2, userVO.getUserpwd());
			pstmt.setString(3, userVO.getUsername());
			pstmt.setString(4, userVO.getUsernickname());
			pstmt.setString(5, userVO.getUseraddress());
			pstmt.setString(6, userVO.getUsertel());
			pstmt.setInt(7, userVO.getUserviplevelid());
			pstmt.setDate(8, userVO.getUserdatetime());
			pstmt.setBoolean(9, userVO.getUserstatus());
			pstmt.setInt(10, userVO.getUserid());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer userid) {
		int updateCount_RESTORDERs =0 ;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, memid, passwd);
			
//			con = ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_RESTORDERs);
			pstmt.setInt(1, userid);
			updateCount_RESTORDERs = pstmt.executeUpdate();
			
			
			pstmt = con.prepareStatement(DELETE_USER);
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除會員編號" + userid + "時,共有訂單" + updateCount_RESTORDERs
					+ "幾筆同時被刪除");

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public UserVO findByPrimaryKey(Integer userid) {

		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, memid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, userid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// userVo 也稱為 Domain objects
				userVO = new UserVO();
				userVO.setUserid(rs.getInt("user_id"));
				userVO.setUseremail(rs.getString("user_email"));
				userVO.setUserpwd(rs.getString("user_pwd"));
				userVO.setUsername(rs.getString("user_name"));
				userVO.setUsernickname(rs.getString("user_nickname"));
				userVO.setUseraddress(rs.getString("user_address"));
				userVO.setUsertel(rs.getString("user_tel"));
				userVO.setUserviplevelid(rs.getInt("user_vip_level_id"));
				userVO.setUserdatetime(rs.getDate("user_datetime"));
				userVO.setUserstatus(rs.getBoolean("user_status"));
			
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userVO;
	}

	@Override
	public List<UserVO> getAll() {
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO userVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, memid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			

			while (rs.next()) {
				// dishVO 也稱為 Domain objects
				userVO = new UserVO();
				userVO.setUserid(rs.getInt("user_id"));
				userVO.setUseremail(rs.getString("user_email"));
				userVO.setUserpwd(rs.getString("user_pwd"));
				userVO.setUsername(rs.getString("user_name"));
				userVO.setUsernickname(rs.getString("user_nickname"));
				userVO.setUseraddress(rs.getString("user_address"));
				userVO.setUsertel(rs.getString("user_tel"));
				userVO.setUserviplevelid(rs.getInt("user_vip_level_id"));
				userVO.setUserdatetime(rs.getDate("user_datetime"));
				userVO.setUserstatus(rs.getBoolean("user_status"));
		
				list.add(userVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
	@Override
	public Set<RestOrderVO> getRestordersByUserid(Integer userid) {
		Set<RestOrderVO> set = new LinkedHashSet<RestOrderVO>();
		RestOrderVO restOrderVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, memid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Restorders_ByUserid_STMT);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				restOrderVO = new RestOrderVO();
				restOrderVO.setOrderid(rs.getInt("orderid"));
				restOrderVO.setUserid(rs.getInt("userid"));
				restOrderVO.setRestid(rs.getInt("restid"));
				restOrderVO.setOrderstatusid(rs.getInt("orderstatusid"));
				restOrderVO.setOrdertime(rs.getTimestamp("ordertime"));
				restOrderVO.setOrdermemo(rs.getString("ordermemo"));
			
				set.add(restOrderVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}


}
