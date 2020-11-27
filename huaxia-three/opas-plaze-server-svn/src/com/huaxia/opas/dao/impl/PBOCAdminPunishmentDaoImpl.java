package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCAdminPunishmentDao;
import com.huaxia.opas.mapper.PBOCAdminPunishmentMapper;

@Repository
public class PBOCAdminPunishmentDaoImpl implements PBOCAdminPunishmentDao {

	@Resource
	private PBOCAdminPunishmentMapper adminPunishmentMapper;

	public PBOCAdminPunishmentMapper getAdminPunishmentMapper() {
		return adminPunishmentMapper;
	}

	public void setAdminPunishmentMapper(PBOCAdminPunishmentMapper adminPunishmentMapper) {
		this.adminPunishmentMapper = adminPunishmentMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getAdminPunishmentMapper().insertBatch(parameters);
	}
	
}
