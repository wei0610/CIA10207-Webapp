package com.msg.model;

import java.sql.Date;

public class MsgVO implements java.io.Serializable {

	private Integer actMsgNo;
	private String actMsg;
	private Integer actNo;
	private Integer menNo;
	private Date actMsgTime;
	private Byte msgPic;

	public Integer getActMsgNo() {
		return actMsgNo;
	}

	public void setActMsgNo(Integer actMsgNo) {
		this.actMsgNo = actMsgNo;
	}

	public String getActMsg() {
		return actMsg;
	}

	public void setActMsg(String actMsg) {
		this.actMsg = actMsg;
	}

	public Integer getActNo() {
		return actNo;
	}

	public void setActNo(Integer actNo) {
		this.actNo = actNo;
	}

	public Integer getMenNo() {
		return menNo;
	}

	public void setMenNo(Integer menNo) {
		this.menNo = menNo;
	}

	public Date getActMsgTime() {
		return actMsgTime;
	}

	public void setActMsgTime(Date actMsgTime) {
		this.actMsgTime = actMsgTime;
	}

	public Byte getMsgPic() {
		return msgPic;
	}

	public void setMsgPic(Byte msgPic) {
		this.msgPic = msgPic;
	}

}
