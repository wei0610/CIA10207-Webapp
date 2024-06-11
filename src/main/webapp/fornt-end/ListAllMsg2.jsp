<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.msg.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    MsgDAO dao = new MsgDAO();
    List<MsgVO> list = dao.getAll();       // ���檺list�ܼ�(����)�N����page1.file����11����o�d�ߨ쪺�`���ơA�A��page1.file�i��������ݭn
    pageContext.setAttribute("list",list); // �N�W�@�檺list�ܼ�(����)�s�J��e����pageContext�A�A�ѩ��U����73���JSTL��forEach�C�L�X���G
%>


<html>
<head>
<title>�T����Ƹ�� - listAllmsg1_byDAO.jsp</title>

<style>
  table#table-1 {
    background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
    width: 800px;
    background-color: white;
    margin-top: 5px;
    margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
    <tr><td>
         <h3>�Ҧ����u��� - listAllEmp1_byDAO.jsp</h3>
         <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
    </td></tr>
</table>

<table>
    <tr>
        <th>���u�s��</th>
        <th>���u�m�W</th>
        <th>¾��</th>
        <th>���Τ��</th>
        <th>�~��</th>
        <th>����</th>
    </tr>
    <%@ include file="page1.file" %> 
    <c:forEach var="msgVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        <tr>
            <td>${msgVO.actMsgNo}</td>
            <td>${msgVO.actMsg}</td>
            <td>${msgVO.actNo}</td>
            <td>${msgVO.menNo}</td>
            <td>${msgVO.actMsgTime}</td>
            <td>${msgVO.msgPic}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>