package com.msg.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MsgDAO implements MsgDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO message (act_msg,act_no,men_no,act_msg_time,msg_pic) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT act_msg_no,act_msg,act_no,men_no,act_msg_time,msg_pic FROM message order by act_msg_no)";
	private static final String GET_ONE_STMT = "SELECT act_msg_no,act_msg,act_no,men_no,act_msg_time,msg_pic FROM message where act_msg_no = ?";
	private static final String DELETE = "DELETE FROM message where act_msg_no = ?";
	private static final String UPDATE = "UPDATE message set act_msg=?, act_no=?, men_no=?, act_msg_time=?, msg_pic=? where act_msg_no = ?";

	@Override
	public void insert(MsgVO msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, msgVO.getActMsg());
			pstmt.setInt(2, msgVO.getActNo());
			pstmt.setInt(3, msgVO.getMenNo());
			pstmt.setDate(4, msgVO.getActMsgTime());
			pstmt.setByte(5, msgVO.getMsgPic());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(MsgVO msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, msgVO.getActMsg());
			pstmt.setInt(2, msgVO.getActNo());
			pstmt.setInt(3, msgVO.getMenNo());
			pstmt.setDate(4, msgVO.getActMsgTime());
			pstmt.setByte(5, msgVO.getMsgPic());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer actMsgNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, actMsgNo);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MsgVO findByPrimaryKey(Integer actMsgNo) {

		MsgVO msgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actMsgNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				msgVO = new MsgVO();
				msgVO.setActMsg(rs.getString("actMsg"));
				msgVO.setActNo(rs.getInt("actNo"));
				msgVO.setMenNo(rs.getInt("menNo"));
				msgVO.setActMsgTime(rs.getDate("msgTime"));
				msgVO.setMsgPic(rs.getByte("msgPic"));

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return msgVO;
	}

	@Override
	public List<MsgVO> getAll() {

		List<MsgVO> list = new ArrayList<MsgVO>();
		MsgVO msgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pstmt.setString(1, msgVO.getActMsg());
				pstmt.setInt(2, msgVO.getActNo());
				pstmt.setInt(3, msgVO.getMenNo());
				pstmt.setDate(4, msgVO.getActMsgTime());
				pstmt.setByte(5, msgVO.getMsgPic());
				list.add(msgVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

}
