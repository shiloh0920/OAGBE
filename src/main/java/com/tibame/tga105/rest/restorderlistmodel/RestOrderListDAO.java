package com.tibame.tga105.rest.restorderlistmodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RestOrderListDAO implements RestOrderListDAO_interface{
	

		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		private static final String INSERT_STMT = 
			"INSERT INTO rest_order_list (order_id,dish_id,dish_price,dish_qty) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT order_list_id,order_id,dish_id,dish_price,dish_qty FROM rest_order_list order by order_list_id";
		private static final String GET_ONE_STMT = 
			"SELECT order_list_id,order_id,dish_id,dish_price,dish_qty FROM rest_order_list where order_list_id = ?";
		private static final String DELETE = 
			"DELETE FROM rest_order_list where order_list_id = ?";
		private static final String UPDATE = 
			"UPDATE rest_order_list set order_id=?, dish_id=?, dish_price=?, dish_qty=? where order_list_id = ?";

		@Override
		public void insert(RestOrderListVO restOrderListVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, restOrderListVO.getOrderid());
				pstmt.setInt(2, restOrderListVO.getDishid());
				pstmt.setInt(3, restOrderListVO.getDishprice());
				pstmt.setInt(4, restOrderListVO.getDishqty());
				
				

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
		public void update(RestOrderListVO restOrderListVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, restOrderListVO.getOrderid());
				pstmt.setInt(2, restOrderListVO.getDishid());
				pstmt.setInt(3, restOrderListVO.getDishprice());
				pstmt.setInt(4, restOrderListVO.getDishqty());
				pstmt.setInt(5, restOrderListVO.getOrderlistid());
			

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
		public void delete(Integer orderlistid) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, orderlistid);

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
		public RestOrderListVO findByPrimaryKey(Integer orderlistid) {

			RestOrderListVO restOrderListVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, orderlistid);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// dishVo 也稱為 Domain objects
					restOrderListVO = new RestOrderListVO();
					restOrderListVO.setOrderlistid(rs.getInt("order_list_id"));
					restOrderListVO.setOrderid(rs.getInt("order_id"));
					restOrderListVO.setDishid(rs.getInt("dish_id"));
					restOrderListVO.setDishprice(rs.getInt("dish_price"));
					restOrderListVO.setDishqty(rs.getInt("dish_qty"));
				
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
			return restOrderListVO;
		}

		@Override
		public List<RestOrderListVO> getAll() {
			List<RestOrderListVO> list = new ArrayList<RestOrderListVO>();
			RestOrderListVO restOrderListVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				

				while (rs.next()) {
					// dishVO 也稱為 Domain objects
					restOrderListVO = new RestOrderListVO();
					restOrderListVO.setOrderlistid(rs.getInt("order_list_id"));
					restOrderListVO.setOrderid(rs.getInt("order_id"));
					restOrderListVO.setDishid(rs.getInt("dish_id"));
					restOrderListVO.setDishprice(rs.getInt("dish_price"));
					restOrderListVO.setDishqty(rs.getInt("dish_qty"));
					list.add(restOrderListVO); // Store the row in the list
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
		public void insert2 (RestOrderListVO restOrderListVO , Connection con) {

			PreparedStatement pstmt = null;

			try {

	     		pstmt = con.prepareStatement(INSERT_STMT);
				
	     		pstmt.setInt(1, restOrderListVO.getOrderid());
				pstmt.setInt(2, restOrderListVO.getDishid());
				pstmt.setInt(3, restOrderListVO.getDishprice());
				pstmt.setInt(4, restOrderListVO.getDishqty());
				
				Statement stmt=	con.createStatement();
	
				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-restorderlist");
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
			}

		}

}
