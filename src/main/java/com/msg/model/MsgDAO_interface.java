package com.msg.model;

import java.util.*;

public interface MsgDAO_interface {
	
	public void insert(MsgVO msgVO);
    public void update(MsgVO msgVO);
    public void delete(Integer actMsgNo);
    public MsgVO findByPrimaryKey(Integer actMsgNo);
    public List<MsgVO> getAll();

}
