var resionList=[
	    {
	    	type:{id:"other",name:"other",value:"其他"},
	    	subtype:[
						{name:"otherSub",id:"otherSub0",value:"申请人人行信用报告中公积金信息明显不符"},
						{name:"otherSub",id:"otherSub1",value:"申请人人行信用报告中多条职业信心均无此公司"},
						{name:"otherSub",id:"otherSub2",value:"红盾、工商类网站核查无此企业"},
						{name:"otherSub",id:"otherSub3",value:"全国组织机构代码查询中心查无此企业"},
						{name:"otherSub",id:"otherSub4",value:"申请人所在单位在网上有大量招聘信息，招聘职位较高、要求较低、但一般注明须邮寄简历和身份证明文件复印件"},
						{name:"otherSub",id:"otherSub5",value:"申请人文化程度与所处公司、职位、行业严重不符"},
						{name:"otherSub",id:"otherSub6",value:"申请人职业领域专业问题无法解答"},
						{name:"otherSub",id:"otherSub7",value:"照会申请人时其不能顺利回答合理表外问题"},
						{name:"otherSub",id:"otherSub8",value:"电话核实中接电人不能分辨夹带的员工姓名"}
              ]
	    },
	    {
	    	type:{id:"falseIdCard",name:"falseIdCard",value:"身份证明文件虚假人行联系方式找到证件持有人否认办卡"},
	    	subtype:[
	 				{name:"falseIdCardSub",id:"falseIdCardSub0",value:"身份证明文件字体异常"},
					{name:"falseIdCardSub",id:"falseIdCardSub1",value:"身份证明文件照片异常"},
					{name:"falseIdCardSub",id:"falseIdCardSub2",value:"身份证明文件防伪点异常（须备注）"},
					{name:"falseIdCardSub",id:"falseIdCardSub3",value:"身份证明文件有效期异常"},
					{name:"falseIdCardSub",id:"falseIdCardSub4",value:"号码与姓名不匹配且经询问申请人无曾用名"}
	    	]
	    },
	    {
	    	type:{id:"falseApplication",name:"falseApplication",value:"申请附件虚假"},
	    	subtype:[
	 				{name:"falseApplicationSub",id:"falseApplicationSub0",value:"工作证明文件疑假"},
					{name:"falseApplicationSub",id:"falseApplicationSub1",value:"财力证明文件疑假"},
					{name:"falseApplicationSub",id:"falseApplicationSub2",value:"其他附件疑假"}
	    	]
	    },
	    {
	    	type:{id:"professionalFalse",name:"professionalFalse",value:"职业信息虚假"},
	    	subtype:[
	 				{name:"professionalFalseSub",id:"professionalFalseSub0",value:"侧核确认申请单位信息真实，但人力部门确认无此人"},
					{name:"professionalFalseSub",id:"professionalFalseSub1",value:"侧核确认申请单位名称真实有效，但具体地址或电话与申请表信息不符的"},
					{name:"professionalFalseSub",id:"professionalFalseSub2",value:"申请人或其家属、联系人明确表示申请人不在单位上班的且其所提供的单位证实申请人在职的"},
					{name:"professionalFalseSub",id:"professionalFalseSub3",value:"申请人文化程度与所处公司、职位、行业存在重大差距的（辅）"},
					{name:"professionalFalseSub",id:"professionalFalseSub4",value:"申请人人行信用报告中多条职业信息均无此公司且行业差距较大的（辅）"},
					{name:"professionalFalseSub",id:"professionalFalseSub5",value:"申请人人行信用报告中单位人力部门确认在职但与申请信息不一致的"}
	    	]
	    },
	    {
	    	type:{id:"applicationUnitFalse",name:"applicationUnitFalse",value:"申请单位信息虚假"},
	    	subtype:[
	 				{name:"applicationUnitFalseSub",id:"applicationUnitFalseSub0",value:"红盾、工商类政府公共查询网站核查无此企业（辅）"},
					{name:"applicationUnitFalseSub",id:"applicationUnitFalseSub1",value:"上级单位核查确认无此机构"},
					{name:"applicationUnitFalseSub",id:"applicationUnitFalseSub2",value:"监督机构确认无此机构"},
					{name:"applicationUnitFalseSub",id:"applicationUnitFalseSub3",value:"申请单位地址物业公司确认无此名称公司的"},
					{name:"applicationUnitFalseSub",id:"applicationUnitFalseSub4",value:"红盾、工商类网站核查企业执照被吊销"},
					{name:"applicationUnitFalseSub",id:"applicationUnitFalseSub5",value:"单位电话与单位名称不符或单位电话为申请人私人注册且非公司法人"},
					{name:"applicationUnitFalseSub",id:"applicationUnitFalseSub6",value:"致电申请表单位电话称非申请表单位或为私人宅电"}
	    	]
	    },
	    {
	    	type:{id:"identityTheftApplication",name:"identityTheftApplication",value:"身份被盗用申请"},
	    	subtype:[
    				{name:"identityTheftApplicationSub",id:"identityTheftApplicationSub0",value:"第三方信息联系到身份证持有人否认办卡"},
    				{name:"identityTheftApplicationSub",id:"identityTheftApplicationSub1",value:"人行职业信息与申请单位跨行业严重，且申请人不能回答人行中历史单位信息或其他正常信息"}
	    	]
	    },
	    {
	    	type:{id:"lllegalEntry",name:"lllegalEntry",value:"非法中介进件"},
	    	subtype:[
    				{name:"lllegalEntrySub",id:"lllegalEntrySub0",value:"网络查询该公司存在房贷、套现等信息"},
    				{name:"lllegalEntrySub",id:"lllegalEntrySub1",value:"申请表联系方式中出现融资、房贷、空卡套现等信息发布号码的"},
    				{name:"lllegalEntrySub",id:"lllegalEntrySub2",value:"申请人明确表示申请过程中有付费情况"},
    				{name:"lllegalEntrySub",id:"lllegalEntrySub3",value:"照会申请人时其不能顺利回答合理表外问题（辅)"},
    				{name:"lllegalEntrySub",id:"lllegalEntrySub4",value:"电话核实中接电人不能分辨夹带的员工姓名（辅）"},
    				{name:"lllegalEntrySub",id:"lllegalEntrySub5",value:"其他"}
	         ]
	    },
	    {
	    	type:{id:"iAuthorizeApplication",name:"iAuthorizeApplication",value:"非本人授权申请"},
	    	subtype:[
    				{name:"iAuthorizeApplicationSub",id:"iAuthorizeApplicationSub0",value:"与申请人确认手机号码不符"},
    				{name:"iAuthorizeApplicationSub",id:"iAuthorizeApplicationSub1",value:"致电申请手机号码称非本人"},
    				{name:"iAuthorizeApplicationSub",id:"iAuthorizeApplicationSub2",value:"家庭住址虚假的或多人同一家庭地址（卡片寄往家庭地址）"},
    				{name:"iAuthorizeApplicationSub",id:"iAuthorizeApplicationSub3",value:"申请表信息均真实但本人否认办卡，同一单位三件以上的"}
	         ]
	    },
	    {
	    	type:{id:"frautDecisionResult",name:"frautDecisionResult",value:"反欺诈决策结果"},
	    	subtype:[
    				{name:"frautDecisionResultSub",id:"frautDecisionResultSub0",value:"SNA中高风险"}
	         ]
	    }
]