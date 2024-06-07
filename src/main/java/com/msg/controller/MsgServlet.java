package com.msg.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.msg.model.*;

public class MsgServlet extends HttpServlet {
//	
//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		
//		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("actMsgNo");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.put("actMsgNo","請輸入留言編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/fornt-end/listmsg.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer actMsgNo = null;
//				try {
//					actMsgNo = Integer.valueOf(str);
//				} catch (Exception e) {
//					errorMsgs.put("actMsgNo","留言編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/fornt-end/listmsg.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				MsgService msgSvc = new MsgService();
//				MsgVO msgVO = msgSvc.getOneMsg(actMsgNo);
//				if (msgVO == null) {
//					errorMsgs.put("actMsgNo","查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/fornt-end/listmsg.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("msgVO", msgVO); // 資料庫取出的msgVO物件,存入req
//				String url = "/emp/listOneEmp.jsp"; //======這邊要改歐歐歐歐歐歐====
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//				/***************************1.接收請求參數****************************************/
//				Integer actMsgNo = Integer.valueOf(req.getParameter("actMsgNo"));
//				
//				/***************************2.開始查詢資料****************************************/
//				MsgService msgSvc = new MsgService();
//				MsgVO msgVO = msgSvc.getOneMsg(actMsgNo);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				String param = "?act_msg_no="  +msgVO.getActMsgNo()+
//						       "&act_msg="  +msgVO.getActMsg()+
//						       "&act_no="    +msgVO.getActNo()+
//						       "&men_no="+msgVO.getMenNo()+
//						       "&act_msg_time="    +msgVO.getActMsgTime()+
//						       "&msg_pic="   +msgVO.getMsgPic();
//				String url = "/emp/update_emp_input.jsp"+param; ////這邊也要改歐歐歐歐///
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//		}
//		
//		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer actMsgNo = Integer.valueOf(req.getParameter("actMsgNo").trim());
//				
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.put("ename","員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.put("ename","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.put("job","職位請勿空白");
//				}
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					errorMsgs.put("hiredate","請輸入日期");
//				}
//				
//				Double sal = null;
//				try {
//					sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("sal","薪水請填數字");
//				}
//				
//				Double comm = null;
//				try {
//					comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("comm","獎金請填數字");
//				}
//				
//				Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				MsgService msgSvc = new MsgService();
//				msgVO msgVO = msgSvc.updateMsg(act_msg, act_no, men_no, act_msg_time, msg_pic);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("msgVO", msgVO); // 資料庫update成功後,正確的的msgVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//		}
//
//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.put("ename","員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.put("ename","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.put("job","職位請勿空白");
//				}
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					errorMsgs.put("hiredate","請輸入日期");
//				}
//				
//				Double sal = null;
//				try {
//					sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("sal","薪水請填數字");
//				}
//				
//				Double comm = null;
//				try {
//					comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("comm","獎金請填數字");
//				}
//				
//				Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				MsgService msgSvc = new MsgService();
//				msgSvc.addMsg(act_msg, act_no, men_no, act_msg_time, msg_pic);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//		}
//		
//		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.接收請求參數***************************************/
//				Integer actMsgNo = Integer.valueOf(req.getParameter("actMsgNo"));
//				
//				/***************************2.開始刪除資料***************************************/
//				MsgService empSvc = new MsgService();
//				empSvc.deleteEmp(actMsgNo);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//		}
//	}

}
