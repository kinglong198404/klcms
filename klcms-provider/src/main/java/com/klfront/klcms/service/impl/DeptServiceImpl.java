package com.klfront.klcms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klfront.klcms.dao.DeptDao;
import com.klfront.klcms.entity.Dept;
import com.klfront.klcms.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService
{
	@Autowired
	private DeptDao dao;
	
	@Override
	public boolean add(Dept dept)
	{
		return dao.addDept(dept);
	}

	@Override
	public Dept get(Long id)
	{
		Dept dept =  dao.findById(id);
		return dept;
	}

	@Override
	public List<Dept> list()
	{
		return dao.findAll();
	}

}
