package com.tibame.tga105.rest.restorderstatusmodel;

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

public class RestOrderStatusDAO implements RestOrderStatusDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";

	String url = "jdbc:mysql://localhost:3306/coffeebean?serverTimezone=Asia/Taipei";

	String userid = "root";

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
		"INSERT INTO rest_order_status (order_status) VALUES ( ?)";
	private static final String GET_ALL_STMT = 
		"SELECT order_status_id,order_status FROM rest_order_status order by order_status_id";
	private static final String GET_ONE_STMT = 
		"SELECT order_status_id,order_status FROM rest_order_status where order_status_id = ?";
	private static final String DELETE_RESTORDERSTATUS = 
		"DELETE FROM rest_order_status where order_status_id = ?";
	private static final String UPDATE = 
		"UPDATE rest_order_status set order_status=? where order_status_id = ?";

	private static final String GET_Restorders_ByOrderstatusid_STMT = "SELECT order_id,user_id,rest_id,orderstatus_id,order_time,order_memo FROM rest_order where order_status_id = ? order by order_id";
	private static final String DELETE_RESTORDERs = "DELETE FROM rest_order where order_status_id = ?";
	
	
	@Override
	public void insert(RestOrderStatusVO restOrderStatusVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, restOrderStatusVO.getOrderstatus());

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
	public void update(RestOrderStatusVO restOrderStatusVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, restOrderStatusVO.getOrderstatus());
			pstmt.setInt(2, restOrderStatusVO.getOrderstatusid());
		

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
	public void delete(Integer orderstatusid) {
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
			con = DriverManager.getConnection(url, userid, passwd);
			
			
//			con = ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_RESTORDERs);
			pstmt.setInt(1, orderstatusid);
			updateCount_RESTORDERs = pstmt.executeUpdate();
			
			
			pstmt = con.prepareStatement(DELETE_RESTORDERSTATUS);
			pstmt.setInt(1, orderstatusid);
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除訂單狀態編號" + orderstatusid + "時,共有訂單" + updateCount_RESTORDERs
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
	public RestOrderStatusVO findByPrimaryKey(Integer orderstatusid) {

		RestOrderStatusVO restOrderStatusVO = null;
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
			con = DriverManager.getConnection(url, userid, passwd);
			
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orderstatusid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// dishVo 也稱為 Domain objects
				restOrderStatusVO = new RestOrderStatusVO();
				restOrderStatusVO.setOrderstatusid(rs.getInt("order_status_id"));
				restOrderStatusVO.setOrderstatus(rs.getString("order_status"));
			
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
		return restOrderStatusVO;
	}

	@Override
	public List<RestOrderStatusVO> getAll() {
		List<RestOrderStatusVO> list = new ArrayList<RestOrderStatusVO>();
		RestOrderStatusVO restOrderStatusVO = null;

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
			con = DriverManager.getConnection(url, userid, passwd);
			
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			

			while (rs.next()) {
				// dishVO 也稱為 Domain objects
				restOrderStatusVO = new RestOrderStatusVO();
				restOrderStatusVO.setOrderstatusid(rs.getInt("order_status_id"));
				restOrderStatusVO.setOrderstatus(rs.getString("order_status"));
		
				list.add(restOrderStatusVO); // Store the row in the list
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
	public Set<RestOrderVO> getRestordersByOrderstatusid(Integer orderstatusid) {
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
			con = DriverManager.getConnection(url, userid, passwd);
			
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Restorders_ByOrderstatusid_STMT);
			pstmt.setInt(1, orderstatusid);
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
