package com.huaxia.opas.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 第三方 & 增强公安
 * 
 * @author zhiguo.li
 *
 */
public class Police implements Serializable {

	private static final long serialVersionUID = -249948571642804656L;
	
	// 申请件编号
	private String appId;

	// INPUT
	private Input input;

	// OUTPUT
	private Output output;

	// RTS
	private Rts rts;

	// 创建日期
	private String crtDate;

	// 创建人
	private String crtUser;

	// 最后操作日期
	private String lstUpdDate;

	// 最后更新人
	private String lstUpdUser;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public Rts getRts() {
		return rts;
	}

	public void setRts(Rts rts) {
		this.rts = rts;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(String lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public class Input {
		
		// 公民身份号码
		private String gmsfhm;
		
		// 姓名
		private String xm;

		public String getGmsfhm() {
			return gmsfhm;
		}

		public void setGmsfhm(String gmsfhm) {
			this.gmsfhm = gmsfhm;
		}

		public String getXm() {
			return xm;
		}

		public void setXm(String xm) {
			this.xm = xm;
		}
		
	}

	public class Output {
		
		// 公民身份号码
		private String gmsfhm;
		
		// 公民身份号码核查结果
		private String result_gmsfhm;
		
		// 姓名
		private String xm;
		
		// 姓名核查结果
		private String result_xm;
		
		// 曾用名
		private String cym;
		
		// 曾用名核查结果
		private String result_cym;
		
		// 性别
		private String xb;
		
		// 性别核查结果
		private String result_xb;
		
		// 民族
		private String mz;
		
		// 民族核查结果
		private String result_mz;
		
		// 出生日期
		private String csrq;
		
		// 出生日期核查结果
		private String result_csrq;
		
		// 所属省市县（区）
		private String ssssxq;
		
		// 所属省市县（区）核查结果
		private String result_ssssxq;
		
		// 籍贯
		private String jgssx;
		
		// 籍贯核查结果
		private String result_jgssx;
		
		// 出生地省市县（区）
		private String csdssx;
		
		// 出生地省市县（区）核查结果
		private String result_csdssx;
		
		// 住址
		private String zz;
		
		// 住址核查结果
		private String result_zz;
		
		private String pcsmc;
		
		private String result_pcsmc;
		
		// 服务处所
		private String fwcs;
		
		// 服务处所核查结果
		private String result_fwcs;
		
		// 婚姻状况
		private String hyzk;
		
		// 婚姻状况核查结果
		private String result_hyzk;
		
		// 文化程度
		private String whcd;
		
		// 文化程度核查结果
		private String result_whcd;
		
		// 相片
		private String xp;
		
		// 错误信息
		private String errorMessage;
		
		// 错误信息域
		private String errorMessageCol;

		public String getGmsfhm() {
			return gmsfhm;
		}

		public void setGmsfhm(String gmsfhm) {
			this.gmsfhm = gmsfhm;
		}

		public String getResult_gmsfhm() {
			return result_gmsfhm;
		}

		public void setResult_gmsfhm(String result_gmsfhm) {
			this.result_gmsfhm = result_gmsfhm;
		}

		public String getXm() {
			return xm;
		}

		public void setXm(String xm) {
			this.xm = xm;
		}

		public String getResult_xm() {
			return result_xm;
		}

		public void setResult_xm(String result_xm) {
			this.result_xm = result_xm;
		}

		public String getCym() {
			return cym;
		}

		public void setCym(String cym) {
			this.cym = cym;
		}

		public String getResult_cym() {
			return result_cym;
		}

		public void setResult_cym(String result_cym) {
			this.result_cym = result_cym;
		}

		public String getXb() {
			return xb;
		}

		public void setXb(String xb) {
			this.xb = xb;
		}

		public String getResult_xb() {
			return result_xb;
		}

		public void setResult_xb(String result_xb) {
			this.result_xb = result_xb;
		}

		public String getMz() {
			return mz;
		}

		public void setMz(String mz) {
			this.mz = mz;
		}

		public String getResult_mz() {
			return result_mz;
		}

		public void setResult_mz(String result_mz) {
			this.result_mz = result_mz;
		}

		public String getCsrq() {
			return csrq;
		}

		public void setCsrq(String csrq) {
			this.csrq = csrq;
		}

		public String getResult_csrq() {
			return result_csrq;
		}

		public void setResult_csrq(String result_csrq) {
			this.result_csrq = result_csrq;
		}

		public String getSsssxq() {
			return ssssxq;
		}

		public void setSsssxq(String ssssxq) {
			this.ssssxq = ssssxq;
		}

		public String getResult_ssssxq() {
			return result_ssssxq;
		}

		public void setResult_ssssxq(String result_ssssxq) {
			this.result_ssssxq = result_ssssxq;
		}

		public String getJgssx() {
			return jgssx;
		}

		public void setJgssx(String jgssx) {
			this.jgssx = jgssx;
		}

		public String getResult_jgssx() {
			return result_jgssx;
		}

		public void setResult_jgssx(String result_jgssx) {
			this.result_jgssx = result_jgssx;
		}

		public String getCsdssx() {
			return csdssx;
		}

		public void setCsdssx(String csdssx) {
			this.csdssx = csdssx;
		}

		public String getResult_csdssx() {
			return result_csdssx;
		}

		public void setResult_csdssx(String result_csdssx) {
			this.result_csdssx = result_csdssx;
		}

		public String getZz() {
			return zz;
		}

		public void setZz(String zz) {
			this.zz = zz;
		}

		public String getResult_zz() {
			return result_zz;
		}

		public void setResult_zz(String result_zz) {
			this.result_zz = result_zz;
		}

		public String getPcsmc() {
			return pcsmc;
		}

		public void setPcsmc(String pcsmc) {
			this.pcsmc = pcsmc;
		}

		public String getResult_pcsmc() {
			return result_pcsmc;
		}

		public void setResult_pcsmc(String result_pcsmc) {
			this.result_pcsmc = result_pcsmc;
		}

		public String getFwcs() {
			return fwcs;
		}

		public void setFwcs(String fwcs) {
			this.fwcs = fwcs;
		}

		public String getResult_fwcs() {
			return result_fwcs;
		}

		public void setResult_fwcs(String result_fwcs) {
			this.result_fwcs = result_fwcs;
		}

		public String getHyzk() {
			return hyzk;
		}

		public void setHyzk(String hyzk) {
			this.hyzk = hyzk;
		}

		public String getResult_hyzk() {
			return result_hyzk;
		}

		public void setResult_hyzk(String result_hyzk) {
			this.result_hyzk = result_hyzk;
		}

		public String getWhcd() {
			return whcd;
		}

		public void setWhcd(String whcd) {
			this.whcd = whcd;
		}

		public String getResult_whcd() {
			return result_whcd;
		}

		public void setResult_whcd(String result_whcd) {
			this.result_whcd = result_whcd;
		}

		public String getXp() {
			return xp;
		}

		public void setXp(String xp) {
			this.xp = xp;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getErrorMessageCol() {
			return errorMessageCol;
		}

		public void setErrorMessageCol(String errorMessageCol) {
			this.errorMessageCol = errorMessageCol;
		}

	}
	
	public class Rts {

		private String dn;
		
		private List<Row> rowList;

		public String getDn() {
			return dn;
		}

		public void setDn(String dn) {
			this.dn = dn;
		}

		public List<Row> getRowList() {
			return rowList;
		}

		public void setRowList(List<Row> rowList) {
			this.rowList = rowList;
		}
		
		public class Row {
			
			private RowInput rowInput;
			
			private RowOutput rowOutput;

			public RowInput getRowInput() {
				return rowInput;
			}

			public void setRowInput(RowInput rowInput) {
				this.rowInput = rowInput;
			}

			public RowOutput getRowOutput() {
				return rowOutput;
			}

			public void setRowOutput(RowOutput rowOutput) {
				this.rowOutput = rowOutput;
			}
			
			public class RowInput {
				
			}
			
			public class RowOutput {
				
				private String result_name;
				
				private String result_cardnum;
				
				private String result_case_code;
				
				private String result_gist_unit;
				
				private String result_area_name;
				
				private String result_performance;
				
				private String result_disreput_type_name;
				
				private String result_publish_date;

				public String getResult_name() {
					return result_name;
				}

				public void setResult_name(String result_name) {
					this.result_name = result_name;
				}

				public String getResult_cardnum() {
					return result_cardnum;
				}

				public void setResult_cardnum(String result_cardnum) {
					this.result_cardnum = result_cardnum;
				}

				public String getResult_case_code() {
					return result_case_code;
				}

				public void setResult_case_code(String result_case_code) {
					this.result_case_code = result_case_code;
				}

				public String getResult_gist_unit() {
					return result_gist_unit;
				}

				public void setResult_gist_unit(String result_gist_unit) {
					this.result_gist_unit = result_gist_unit;
				}

				public String getResult_area_name() {
					return result_area_name;
				}

				public void setResult_area_name(String result_area_name) {
					this.result_area_name = result_area_name;
				}

				public String getResult_performance() {
					return result_performance;
				}

				public void setResult_performance(String result_performance) {
					this.result_performance = result_performance;
				}

				public String getResult_disreput_type_name() {
					return result_disreput_type_name;
				}

				public void setResult_disreput_type_name(String result_disreput_type_name) {
					this.result_disreput_type_name = result_disreput_type_name;
				}

				public String getResult_publish_date() {
					return result_publish_date;
				}

				public void setResult_publish_date(String result_publish_date) {
					this.result_publish_date = result_publish_date;
				}
				
			}
			
		}
		
	}

}
