package com.msg.model;

import java.util.*;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MsgJDBCDAO implements MsgDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cia?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "0610";

	private static final String INSERT_STMT = 
			"INSERT INTO message (act_msg,act_no,men_no,act_msg_time,msg_pic) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT act_msg_no,act_msg,act_no,men_no,act_msg_time,msg_pic FROM message where act_msg_no";
	private static final String GET_ONE_STMT = 
			"SELECT act_msg_no,act_msg,act_no,men_no,act_msg_time,msg_pic FROM message where act_msg_no = ?";
	private static final String DELETE = 
			"DELETE FROM message where act_msg_no = ?";
	private static final String UPDATE = 
			"UPDATE message set act_msg=?, act_no=?, men_no=?, act_msg_time=?, msg_pic=? where act_msg_no = ?";

	@Override
	public void insert(MsgVO msgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		File imageFile = new File("C:\\CIA102_Workspace\\CIA10207-Webapp\\src\\main\\webapp\\images\\kuma1.jpg");
		
		byte[] imageData = new byte[(int) imageFile.length()];
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            fis.read(imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, msgVO.getActMsg());
			pstmt.setInt(2, msgVO.getActNo());
			pstmt.setInt(3, msgVO.getMenNo());
			pstmt.setDate(4, msgVO.getActMsgTime());
			pstmt.setBytes(5, imageData);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		        Class.forName(driver);
		        con = DriverManager.getConnection(url, userid, passwd);
		        pstmt = con.prepareStatement(UPDATE);

		        pstmt.setString(1, msgVO.getActMsg());
		        pstmt.setInt(2, msgVO.getActNo());
		        pstmt.setInt(3, msgVO.getMenNo());
		        pstmt.setDate(4, msgVO.getActMsgTime());

		        if (msgVO.getMsgPic() != null) {
		            pstmt.setByte(5, msgVO.getMsgPic());
		        } else {
		            pstmt.setNull(5, Types.BLOB);
		        }

		        pstmt.setInt(6, msgVO.getActMsgNo());

		        int count = pstmt.executeUpdate();

		        if (count == 0) {
		            System.out.println("No rows updated!");
		        } else {
		            System.out.println(count + " row(s) updated.");
		        }
		    } catch (ClassNotFoundException e) {
		        throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		    } catch (SQLException se) {
		        throw new RuntimeException("A database error occured. " + se.getMessage());
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
		            } catch (SQLException se) {
		                se.printStackTrace(System.err);
		            }
		        }
		    }
		
        
	}

	@Override
	public void delete(Integer actMsgNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, actMsgNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actMsgNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				msgVO = new MsgVO();
				msgVO.setActMsgNo(rs.getInt("act_msg_no"));
				msgVO.setActMsg(rs.getString("act_msg"));
				msgVO.setActNo(rs.getInt("act_no"));
				msgVO.setMenNo(rs.getInt("men_no"));
				msgVO.setActMsgTime(rs.getDate("act_msg_time"));
				msgVO.setMsgPic(rs.getByte("msg_pic"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				msgVO = new MsgVO();
				msgVO.setActMsgNo(rs.getInt("act_msg_no"));
				msgVO.setActMsg(rs.getString("act_msg"));
				msgVO.setActNo(rs.getInt("act_no"));
				msgVO.setMenNo(rs.getInt("men_no"));
				msgVO.setActMsgTime(rs.getDate("act_msg_time"));
				msgVO.setMsgPic(rs.getByte("msg_pic"));
				list.add(msgVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	
	
	public static void main(String[] args) {

		MsgJDBCDAO dao = new MsgJDBCDAO();
		

		// 新增
//		MsgVO msgVO1 = new MsgVO();
//		msgVO1.setActMsg("明天是我生日");
//		msgVO1.setActNo(9999999);
//		msgVO1.setMenNo(123333);
//		msgVO1.setActMsgTime(java.sql.Date.valueOf("2024-06-09"));
//		msgVO1.setMsgPic(null);
//		dao.insert(msgVO1);

////		// 修改
		MsgVO msgVO2 = new MsgVO();
		msgVO2.setActMsgNo(2);
		msgVO2.setActMsg("我討厭明天");
		msgVO2.setActNo(422222);
		msgVO2.setMenNo(000003);
		msgVO2.setActMsgTime(java.sql.Date.valueOf("2024-06-09"));
		msgVO2.setMsgPic(null);
		dao.update(msgVO2);
////
////		// 刪除
//		dao.delete(3);
//
//////		// 查詢
//		MsgVO msgVO3 = dao.findByPrimaryKey(1);
//		System.out.print(msgVO3.getActMsgNo() + ",");
//		System.out.print(msgVO3.getActMsg() + ",");
//		System.out.print(msgVO3.getActNo() + ",");
//		System.out.print(msgVO3.getMenNo() + ",");
//		System.out.print(msgVO3.getActMsgTime() + ",");
//		System.out.print(msgVO3.getMsgPic() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<MsgVO> list = dao.getAll();
//		for (MsgVO aMsg : list) {
//			System.out.print(aMsg.getActMsgNo() + ",");
//			System.out.print(aMsg.getActMsg() + ",");
//			System.out.print(aMsg.getActNo() + ",");
//			System.out.print(aMsg.getMenNo() + ",");
//			System.out.print(aMsg.getActMsgTime() + ",");
//			System.out.print(aMsg.getMsgPic() + ",\t");
////			System.out.print(aEmp.getDeptVO()); // join DeptVO
//			System.out.println();
//		}
	}

}


