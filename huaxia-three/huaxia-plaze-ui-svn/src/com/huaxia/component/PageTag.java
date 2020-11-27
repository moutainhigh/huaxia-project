package com.huaxia.component;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {

	private static final long serialVersionUID = 18801202744020923L;

	// 当前页码
	private int pageNo;

	// 每页显示条数
	private int pageSize;

	// 总页数
	private int pageCount;

	// 总记录数
	private int totalCount;

	@Override
	public int doEndTag() throws JspException {
		StringBuilder builder = new StringBuilder("<div class=\"page\">");

		// 总页数不大于5条
		if (pageCount < 6) {
			builder.append("<span>第" + pageNo + "页/共" + pageCount + "页</span>&nbsp;");
			// 如果当前页为第1页则"首页"和"上一页"不可点击
			if (pageNo == 1) {
				builder.append("<a class=\"page-button-disable\">首页</a>&nbsp;");
				builder.append("<a class=\"page-button-disable\">上一页</a>&nbsp;");
			} else {
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + 1 + "," + pageSize + ")\" class=\"page-button\">首页</a>&nbsp;");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + (pageNo > 1 ? pageNo - 1 : 1) + "," + pageSize + ")\" class=\"page-button\">上一页</a>&nbsp;");
			}
			for (int i = 1; i <= pageCount; i++) {
				if (i == pageNo) {
					builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + i + "," + pageSize + ")\" class=\"page-button page-button-color\">" + i + "</a>&nbsp;");
				} else {
					builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + i + "," + pageSize + ")\" class=\"page-button\">" + i + "</a>&nbsp;");
				}
			}
			if (pageNo >= pageCount) {
				builder.append("<a class=\"page-button-disable\">下一页</a>&nbsp;");
				builder.append("<a class=\"page-button-disable\">末页</a>");
			} else {
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + (pageNo + 1) + "," + pageSize + ")\" class=\"page-button\">下一页</a>&nbsp;");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + pageCount + "," + pageSize + ")\" class=\"page-button\">末页</a>");
			}
		} else {
			builder.append("<span>第" + pageNo + "页/共" + pageCount + "页</span>&nbsp;");
			if (pageNo == 1) {
				builder.append("<a class=\"page-button-disable\">首页</a>&nbsp;");
				builder.append("<a class=\"page-button-disable\">上一页</a>&nbsp;");
			} else {
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + 1 + "," + pageSize + ")\" class=\"page-button\">首页</a>&nbsp;");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + (pageNo > 1 ? pageNo - 1 : 1) + "," + pageSize + ")\" class=\"page-button\">上一页</a>&nbsp;");
			}
			if (pageNo - 2 <= 0) {
				for (int i = 1; i <= 5; i++) {
					if (i == pageNo) {
						builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + i + "," + pageSize + ")\" class=\"page-button page-button-color\">" + i + "</a>&nbsp;");
					} else {
						builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + i + "," + pageSize + ")\" class=\"page-button\">" + i + "</a>&nbsp;");
					}
				}
			} else if (pageCount - pageNo <= 2) {
				for (int i = 4; i >= 0; i--) {
					if (pageCount - i == pageNo) {
						builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + (pageCount - i) + "," + pageSize + ")\" class=\"page-button page-button-color\">" + (pageCount - i) + "</a>&nbsp;");
					} else {
						builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + (pageCount - i) + "," + pageSize + ")\" class=\"page-button\">" + (pageCount - i) + "</a>&nbsp;");
					}
				}
			} else {
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + (pageNo - 2) + "," + pageSize + ")\" class=\"page-button\">" + (pageNo - 2) + "</a>&nbsp;");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + (pageNo - 1) + "," + pageSize + ")\" class=\"page-button\">" + (pageNo - 1) + "</a>&nbsp;");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + pageNo + "," + pageSize + ")\" class=\"page-button page-button-color\">" + pageNo + "</a>&nbsp;");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + (pageNo + 1) + "," + pageSize + ")\" class=\"page-button\">" + (pageNo + 1) + "</a>&nbsp;");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + (pageNo + 2) + "," + pageSize + ")\" class=\"page-button\">" + (pageNo + 2) + "</a>&nbsp;");
			}
			
			if (pageNo >= pageCount) {
				builder.append("<a class=\"page-button-disable\">下一页</a>&nbsp;");
				builder.append("<a class=\"page-button-disable\">末页</a>");
			} else {
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + (pageNo + 1) + "," + pageSize + ")\" class=\"page-button\">下一页</a>&nbsp;");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"page(" + pageCount + "," + pageSize + ")\" class=\"page-button\">末页</a>");
			}
		}

		builder.append("</div>");
		JspWriter writer = pageContext.getOut();
		try {
			writer.println(new String(builder.toString().getBytes()));
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		// 计算总页数
		int count = totalCount % pageSize;
		if (count > 0) {
			pageCount = totalCount / pageSize + 1;
		} else if (count == 0) {
			pageCount = totalCount / pageSize;
		}

		// 校验参数pageNo是否在有效范围内
		if (pageNo < 1) {
			pageNo = 1;
		} else if (pageNo > pageCount) {
			pageNo = pageCount;
		}

		return SKIP_BODY;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
