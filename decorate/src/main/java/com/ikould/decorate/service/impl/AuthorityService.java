package com.ikould.decorate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikould.decorate.entity.Authority;
import com.ikould.decorate.mapper.AuthorityMapper;
import com.ikould.decorate.service.IAuthorityService;

@Service("authorityService")
public class AuthorityService implements IAuthorityService {

	@Autowired
	AuthorityMapper authorityMapper;

	@Override
	public void insert(Authority authority) {
		authorityMapper.insert(authority);
	}

	@Override
	public void deleteById(int id) {
		authorityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Authority findById(int id) {
		return authorityMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Authority> findAll() {
		return authorityMapper.findAll();
	}

	@Override
	public Authority findByMac(String mac) {
		return authorityMapper.findByMac(mac);
	}

	@Override
	public void update(Authority authority) {
		authorityMapper.update(authority);
	}

}
