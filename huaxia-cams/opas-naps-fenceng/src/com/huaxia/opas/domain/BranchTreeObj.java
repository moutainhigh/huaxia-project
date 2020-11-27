package com.huaxia.opas.domain;


public class BranchTreeObj extends BranchLevelParam{

	private static final long serialVersionUID = -5522768421977378596L;

	private String id;
	
	private String _parentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}
	
	public BranchTreeObj(BranchLevelParam branchLevelParam){
		
		super();
		
		this.setId(branchLevelParam.getProvId());
		
		this.set_parentId(branchLevelParam.getBranchParentId());
		
		this.setProvId(branchLevelParam.getProvId());
		
		this.setBranchParentId(branchLevelParam.getBranchParentId());
		
		this.setBranchLevel(branchLevelParam.getBranchLevel());
		
		this.setBranchCode(branchLevelParam.getBranchCode());
		
		this.setBranchName(branchLevelParam.getBranchName());
		
		this.setCrtDate(branchLevelParam.getCrtDate());
		
		this.setCrtTime(branchLevelParam.getCrtTime());
		
		this.setCrtUser(branchLevelParam.getCrtUser());
		
		this.setLstUpdUser(branchLevelParam.getLstUpdUser());
		
		this.setLstUpdDate(branchLevelParam.getLstUpdDate());
		
		this.setLstUpdTime(branchLevelParam.getLstUpdTime());
		
		this.setBatchDate(branchLevelParam.getBatchDate());
		
		this.setRecStatus(branchLevelParam.getRecStatus());
		
		this.setScrLevel(branchLevelParam.getScrLevel());
		
		this.setBranchAppOrgCode(branchLevelParam.getBranchAppOrgCode());
	
	}
	
	
}
