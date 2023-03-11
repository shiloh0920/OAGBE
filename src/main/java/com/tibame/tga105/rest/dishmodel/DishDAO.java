package com.tibame.tga105.rest.dishmodel;

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

import com.tibame.tga105.rest.restorderlistmodel.RestOrderListVO;


public class DishDAO implements DishDAO_interface {


//	 這個DAO有傳圖片
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	String driver = "com.mysql.cj.jdbc.Driver";

	String url = "jdbc:mysql://localhost:3306/coffeebean?serverTimezone=Asia/Taipei";

	String userid = "root";

	String passwd = "password";
	
	
	
	private static final String INSERT_STMT = 
		"INSERT INTO dish (rest_id,dish_name,dish_price,dish_ity,dish_description,dish_spec,dish_img) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT dish_id,rest_id,dish_name,dish_price,dish_ity,dish_description,dish_spec,dish_img FROM dish order by dish_id";
	private static final String GET_ONE_STMT = 
		"SELECT dish_id,rest_id,dish_name,dish_price,dish_ity,dish_description,dish_spec,dish_img FROM dish where dish_id = ?";
	private static final String DELETE_DISH = 
		"DELETE FROM dish where dish_id = ?";
	private static final String UPDATE = 
		"UPDATE dish set rest_id=?, dish_name=?, dish_price=?, dish_ity=?, dish_description=?, dish_spec=?, dish_img=? where dish_id = ?";

	
	private static final String GET_RestOrderLists_ByDishid_STMT = "SELECT order_list_id,order_id,dish_id,dish_price,dish_qty FROM rest_order_list where dish_id = ? order by order_list_id";
	
	private static final String DELETE_ORDERLISTs = "DELETE FROM rest_order_list where order_list_id = ?";
	
	
	@Override
	public void insert(DishVO dishVO) {

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

			pstmt.setInt(1, dishVO.getRestid());
			pstmt.setString(2, dishVO.getDishname());
			pstmt.setInt(3, dishVO.getDishprice());
			pstmt.setInt(4, dishVO.getDishity());
			pstmt.setString(5, dishVO.getDishdescription());
			pstmt.setString(6, dishVO.getDishspec());
			pstmt.setBytes(7, dishVO.getDishimg());

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
	public void update(DishVO dishVO) {

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

			pstmt.setInt(1, dishVO.getRestid());
			pstmt.setString(2, dishVO.getDishname());
			pstmt.setInt(3, dishVO.getDishprice());
			pstmt.setInt(4, dishVO.getDishity());
			pstmt.setString(5, dishVO.getDishdescription());
			pstmt.setString(6, dishVO.getDishspec());
			pstmt.setBytes(7, dishVO.getDishimg());
			pstmt.setInt(8, dishVO.getDishid());

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
	public void delete(Integer dishid) {
		int updateCount_OrderLists = 0;
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
			
			pstmt = con.prepareStatement(DELETE_ORDERLISTs);
			pstmt.setInt(1, dishid);
			updateCount_OrderLists = pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE_DISH);
			pstmt.setInt(1, dishid);
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除餐點編號" + dishid + "時,共有訂單明細" + updateCount_OrderLists
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
	public DishVO findByPrimaryKey(Integer dishid) {

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
			
			
			
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, dishid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// dishVo 也稱為 Domain objects
				dishVO = new DishVO();
				dishVO.setDishid(rs.getInt("dish_id"));
				dishVO.setRestid(rs.getInt("rest_id"));
				dishVO.setDishname(rs.getString("dish_name"));
				dishVO.setDishprice(rs.getInt("dish_price"));
				dishVO.setDishity(rs.getInt("dish_ity"));
				dishVO.setDishdescription(rs.getString("dish_description"));
				dishVO.setDishspec(rs.getString("dish_spec"));
				dishVO.setDishimg(rs.getBytes("dish_img"));
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
		return dishVO;
	}

	@Override
	public List<DishVO> getAll() {
		List<DishVO> list = new ArrayList<DishVO>();
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
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			

			while (rs.next()) {
				// dishVO 也稱為 Domain objects
				dishVO = new DishVO();
				dishVO.setDishid(rs.getInt("dish_id"));
				dishVO.setRestid(rs.getInt("rest_id"));
				dishVO.setDishname(rs.getString("dish_name"));
				dishVO.setDishprice(rs.getInt("dish_price"));
				dishVO.setDishity(rs.getInt("dish_ity"));
				dishVO.setDishdescription(rs.getString("dish_description"));
				dishVO.setDishspec(rs.getString("dish_spec"));
				dishVO.setDishimg(rs.getBytes("dish_img"));
				list.add(dishVO); // Store the row in the list
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
	
	    public DishVO getDishById(int dishid) throws SQLException, ClassNotFoundException {
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        DishVO dishVO = null;

	        try {
	        	
	        	try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn = DriverManager.getConnection(url, userid, passwd);
	        	
//	            conn = ds.getConnection();
	            ps = conn.prepareStatement("SELECT dish_id, dish_img FROM dish WHERE dish_id=?");
	            ps.setInt(1, dishid);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                dishVO = new DishVO();
	                dishVO.setDishid(rs.getInt("dish_id"));
	                dishVO.setDishimg(rs.getBytes("dish_img"));
	            }
	        } finally {
	            conn.close();
	            ps.close();
	            rs.close();
	        }

	        return dishVO;
	    }
	
	    @Override
		public Set<RestOrderListVO> getRestorderlistsByDishid(Integer dishid) {
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
				
//				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_RestOrderLists_ByDishid_STMT);
				pstmt.setInt(1, dishid);
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
	

}
