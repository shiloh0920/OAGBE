package com.tibame.tga105.rest.restmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.dishmodel.DishVO;
import com.tibame.tga105.rest.restordermodel.RestOrderVO;

public class RestDAO implements RestDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	
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
		"INSERT INTO rest (rest_name,rest_address,rest_img) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT rest_id,rest_name,rest_address,rest_img FROM rest order by rest_id";
	private static final String GET_ONE_STMT = 
		"SELECT rest_id,rest_name,rest_address,rest_img FROM rest where rest_id = ?";
	private static final String DELETE_REST= 
		"DELETE FROM rest where rest_id = ?";
	private static final String UPDATE = 
		"UPDATE rest set rest_name=?, rest_address=?, rest_img=? where rest_id = ?";
	private static final String GET_Dishs_ByRestid_STMT = "SELECT dish_id,rest_id,dish_name,dish_price,dish_ity,dish_description,dish_spec,dish_img FROM dish where rest_id = ? order by dish_id";
	private static final String DELETE_DISHs = "DELETE FROM dish where rest_id = ?";
	
	private static final String GET_RestOrders_ByRestid_STMT = "SELECT order_id,user_id,rest_id,orderstatus_id,order_time,order_memo FROM rester_order where rest_id = ? order by order_id";
	private static final String DELETE_RESTORDERs = "DELETE FROM rest_order where rest_id = ?";
	
	@Override
	public void insert(RestVO restVO) {

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
			
		
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, restVO.getRestname());
			pstmt.setString(2, restVO.getRestaddress());
			pstmt.setBytes(3, restVO.getRestimg());
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
	public void update(RestVO restVO) {

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
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, restVO.getRestname());
			pstmt.setString(2, restVO.getRestaddress());
			pstmt.setBytes(3, restVO.getRestimg());
			pstmt.setInt(4, restVO.getRestid());
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
	public void delete(Integer restid) {
		int updateCount_DISHs = 0;
		int updateCount_RESTORDERs =0;
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
//			con = dataSource.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除
			pstmt = con.prepareStatement(DELETE_DISHs);
			pstmt.setInt(1, restid);
			updateCount_DISHs = pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE_RESTORDERs);
			pstmt.setInt(1, restid);
			updateCount_RESTORDERs = pstmt.executeUpdate();
			// 再刪除
			pstmt = con.prepareStatement(DELETE_REST);
			pstmt.setInt(1, restid);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除餐廳編號" + restid + "時,共有餐廳名稱" + updateCount_DISHs
					+ "人同時被刪除");
			System.out.println("刪除餐廳編號" + restid + "時,共有訂單名稱" + updateCount_RESTORDERs
					+ "幾筆同時被刪除");
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public RestVO findByPrimaryKey(Integer restid) {

		RestVO restVO = null;
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
//			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, restid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// restVo 也稱為 Domain objects
				restVO = new RestVO();
				restVO.setRestid(rs.getInt("rest_id"));
				restVO.setRestname(rs.getString("rest_name"));
				restVO.setRestaddress(rs.getString("rest_address"));
				restVO.setRestimg(rs.getBytes("rest_img"));
				
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
		return restVO;
	}

	@Override
	public List<RestVO> getAll() {
		List<RestVO> list = new ArrayList<RestVO>();
		RestVO restVO = null;

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
//			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// restVO 也稱為 Domain objects
				restVO = new RestVO();
				restVO.setRestid(rs.getInt("rest_id"));
				restVO.setRestname(rs.getString("rest_name"));
				restVO.setRestaddress(rs.getString("rest_address"));
				restVO.setRestimg(rs.getBytes("rest_img"));
				list.add(restVO); // Store the row in the list
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
	public Set<DishVO> getDishsByRestid(Integer restid) {
		Set<DishVO> set = new LinkedHashSet<DishVO>();
		DishVO dishVO = null;
	
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
//			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_Dishs_ByRestid_STMT);
			pstmt.setInt(1, restid);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				dishVO = new DishVO();
				dishVO.setDishid(rs.getInt("dish_id"));
				dishVO.setRestid(rs.getInt("rest_id"));
				dishVO.setDishname(rs.getString("dish_name"));
				dishVO.setDishprice(rs.getInt("dish_price"));
				dishVO.setDishity(rs.getInt("dish_ity"));
				dishVO.setDishdescription(rs.getString("dish_description"));
				dishVO.setDishspec(rs.getString("dish_spec"));
				dishVO.setDishimg(rs.getBytes("dish_img"));
				set.add(dishVO); // Store the row in the vector
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
	
	@Override
	public Set<RestOrderVO> getRestordersByRestid(Integer restid) {
		Set<RestOrderVO> set = new LinkedHashSet<RestOrderVO>();
		RestOrderVO restOrderVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
//	
			
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con = ds.getConnection();
//			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_RestOrders_ByRestid_STMT);
			pstmt.setInt(1, restid);
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
	
	 public RestVO getRestById(int restid) throws SQLException, ClassNotFoundException {
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        RestVO restVO = null;

	        try {
	        	
	        	
	        	try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn = DriverManager.getConnection(url, userid, passwd);
				
//	            conn = ds.getConnection();
//	        	conn = dataSource.getConnection();
	            ps = conn.prepareStatement("SELECT rest_id, rest_img FROM rest WHERE rest_id=?");
	            ps.setInt(1, restid);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                restVO = new RestVO();
	                restVO.setRestid(rs.getInt("rest_id"));
	                restVO.setRestimg(rs.getBytes("rest_img"));
	            }
	        } finally {
	            conn.close();
	            ps.close();
	            rs.close();
	        }

	        return restVO;
	    }
	
	
	
	
	
}