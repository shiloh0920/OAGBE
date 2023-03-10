package com.tibame.tga105.rest.restordermodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.tga105.rest.restorderlistmodel.RestOrderListDAO;
import com.tibame.tga105.rest.restorderlistmodel.RestOrderListVO;

public class RestOrderDAO implements RestOrderDAO_interface {

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
		"INSERT INTO rest_order (user_id,rest_id,order_status_id,order_time,order_memo) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT order_id,user_id,rest_id,order_status_id,order_time,order_memo FROM rest_order order by order_id";
	private static final String GET_ONE_STMT = 
		"SELECT order_id,user_id,rest_id,order_status_id,order_time,order_memo FROM rest_order where order_id = ?";
	private static final String DELETE_RESTORDER = 
		"DELETE FROM rest_order where order_id = ?";
	private static final String UPDATE = 
		"UPDATE rest_order set user_id=?, rest_id=?, order_status_id=?, order_time=?, order_memo=? where order_id = ?";
	
	private static final String GET_RestOrderLists_ByOrderid_STMT = "SELECT order_list_id,order_id,dish_id,dish_price,dish_qty FROM rest_order_list where order_id = ? order by order_list_id";
	private static final String DELETE_RESTORDERLISTs = "DELETE FROM rest_order_list where order_id = ?";
	
	@Override
	public int insert(RestOrderVO restOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int next_order_id = -1;
		try {

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			
			
//			con = ds.getConnection();
			String[] col = { "order_id" };
			pstmt = con.prepareStatement(INSERT_STMT, col);
			
			pstmt.setInt(1, restOrderVO.getUserid());
			pstmt.setInt(2, restOrderVO.getRestid());
			pstmt.setInt(3, restOrderVO.getOrderstatusid());
			pstmt.setTimestamp(4, restOrderVO.getOrdertime());
			pstmt.setString(5, restOrderVO.getOrdermemo());
			

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			} 
			
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
		return next_order_id;
	}

	@Override
	public void update(RestOrderVO restOrderVO) {

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

			pstmt.setInt(1, restOrderVO.getUserid());
			pstmt.setInt(2, restOrderVO.getRestid());
			pstmt.setInt(3, restOrderVO.getOrderstatusid());
			pstmt.setTimestamp(4, restOrderVO.getOrdertime());
			pstmt.setString(5, restOrderVO.getOrdermemo());
			pstmt.setInt(6, restOrderVO.getOrderid());

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
	public void delete(Integer orderid) {
		int updateCount_RESTORDERLISTs = 0;
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

			pstmt = con.prepareStatement(DELETE_RESTORDERLISTs);
			pstmt.setInt(1, orderid);
			updateCount_RESTORDERLISTs = pstmt.executeUpdate();

			pstmt = con.prepareStatement(DELETE_RESTORDER);
			pstmt.setInt(1, orderid);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除訂單編號" + orderid + "時,共有訂單明細" + updateCount_RESTORDERLISTs
					+ "數筆同時被刪除");
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
	public RestOrderVO findByPrimaryKey(Integer orderid) {

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
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orderid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// dishVo 也稱為 Domain objects
				restOrderVO = new RestOrderVO();
				restOrderVO.setOrderid(rs.getInt("order_id"));
				restOrderVO.setUserid(rs.getInt("user_id"));
				restOrderVO.setRestid(rs.getInt("rest_id"));
				restOrderVO.setOrderstatusid(rs.getInt("order_status_id"));
				restOrderVO.setOrdertime(rs.getTimestamp("order_time"));
				restOrderVO.setOrdermemo(rs.getString("order_memo"));
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
		return restOrderVO;
	}

	@Override
	public List<RestOrderVO> getAll() {
		List<RestOrderVO> list = new ArrayList<RestOrderVO>();
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
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			

			while (rs.next()) {
				// dishVO 也稱為 Domain objects
				restOrderVO = new RestOrderVO();
				restOrderVO.setOrderid(rs.getInt("order_id"));
				restOrderVO.setUserid(rs.getInt("user_id"));
				restOrderVO.setRestid(rs.getInt("rest_id"));
				restOrderVO.setOrderstatusid(rs.getInt("order_status_id"));
				restOrderVO.setOrdertime(rs.getTimestamp("order_time"));
				restOrderVO.setOrdermemo(rs.getString("order_memo"));
				list.add(restOrderVO); // Store the row in the list
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
	
//	    public DishVO getDishById(int dishid) throws SQLException, ClassNotFoundException {
//	        Connection conn = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//	        DishVO dishVO = null;
//
//	        try {
//	            conn = ds.getConnection();
//	            ps = conn.prepareStatement("SELECT dish_id, dish_img FROM dish WHERE dish_id=?");
//	            ps.setInt(1, dishid);
//	            rs = ps.executeQuery();
//
//	            if (rs.next()) {
//	                dishVO = new DishVO();
//	                dishVO.setDishid(rs.getInt("dish_id"));
//	                dishVO.setDishimg(rs.getBytes("dish_img"));
//	            }
//	        } finally {
//	            conn.close();
//	            ps.close();
//	            rs.close();
//	        }
//
//	        return dishVO;
//	    }
	@Override
	public Set<RestOrderListVO> getRestorderlistsByOrderid(Integer orderid) {
		Set<RestOrderListVO> set = new LinkedHashSet<RestOrderListVO>();
		RestOrderListVO restOrderListVO = null;
	
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
			pstmt = con.prepareStatement(GET_RestOrderLists_ByOrderid_STMT);
			pstmt.setInt(1, orderid);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				restOrderListVO = new RestOrderListVO();
				restOrderListVO.setOrderlistid(rs.getInt("orderlistid"));
				restOrderListVO.setOrderid(rs.getInt("orderid"));
				restOrderListVO.setDishid(rs.getInt("dishid"));
				restOrderListVO.setDishprice(rs.getInt("dishprice"));
				restOrderListVO.setDishqty(rs.getInt("dishqty"));
				set.add(restOrderListVO); // Store the row in the vector
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
	public void insertWithRestOrderList(RestOrderVO restOrderVO , List<RestOrderListVO> list) {

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
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單
			String cols[] = {"orderid"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);
						
			pstmt.setInt(1, restOrderVO.getUserid());
			pstmt.setInt(2, restOrderVO.getRestid());
			pstmt.setInt(3, restOrderVO.getOrderstatusid());
			pstmt.setTimestamp(4, restOrderVO.getOrdertime());
			pstmt.setString(5, restOrderVO.getOrdermemo());
			Statement stmt=	con.createStatement();

			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_orderid = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_orderid = rs.getString(1);
				System.out.println("自增主鍵值= " + next_orderid +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			RestOrderListDAO dao = new RestOrderListDAO();
			System.out.println("list.size()-A="+list.size());
			for (RestOrderListVO aRestorderlist : list) {
				aRestorderlist.setOrderid(new Integer(next_orderid)) ;
				dao.insert2(aRestorderlist,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_orderid + "時,共有訂單明細" + list.size()
					+ "幾筆同時被新增");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-restorder");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	
}
