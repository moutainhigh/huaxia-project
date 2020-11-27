/**
 * 生成指定行列数表格并将该表格放置在指定容器中
 * 
 * @param id
 *            容器ID
 * @param rnum
 *            行数
 * @param cnum
 *            列数
 */
function fnGenTable(id, rnum, cnum) {
	var prefix = id.substring(id.length - 2, id.length);
	var container = $(id);
	var table = $('<table border="1"></table>');
	table.appendTo(container);

	// 开始 - 表头初始化
	fnGenThead(rnum, cnum, table);
	// 结束 - 表头初始化

	// 开始 - 表尾初始化
	fnGenTbody(prefix, rnum, cnum, table);
	// 结束 - 表尾初始化

	// 开始 - 表尾初始化
	fnGenTfoot(prefix, table);
	// 结束 - 表尾初始化
}

/**
 * 生成表格头部
 * 
 * @param rnum
 *            行数
 * @param cnum
 *            列数
 * @param table
 *            表格对象
 */
function fnGenThead(rnum, cnum, table) {
	var thead = $('<thead></thead>');
	thead.appendTo(table);
	var tr = $('<tr></tr>');
	tr.appendTo(thead);
	for (var h = 0; h < tags.length; h++) {
		var th = $('<th>' + tags[h] + '</th>')
		th.appendTo(tr);
	}
}

/**
 * 生成表格体部
 * 
 * @param rnum
 *            行数
 * @param cnum
 *            列数
 * @param table
 *            表格对象
 */
function fnGenTbody(prefix, rnum, cnum, table) {
	var tbody = $('<tbody></tbody>');
	tbody.appendTo(table);
	for (var i = 1; i <= rnum; i++) {
		tr = $('<tr></tr>');
		tr.appendTo(tbody);
		// 第1列：月份
		var firstOfTbody = $('<td>' + (i < 10 ? '0' + i : i) + '</td>');
		firstOfTbody.appendTo(tr);
		var x = i < 10 ? '0' + i : i;
		for (var j = 1; j <= cnum; j++) {
			var y = j < 10 ? '0' + j : j;
			var td = $('<td><input type="text" id="' + prefix + (x + y)
					+ '" class="easyui-textbox"/></td>');
			td.appendTo(tr);
		}
	}
}

/**
 * 生成表格尾部
 * 
 * @param table
 *            表格对象
 */
function fnGenTfoot(prefix, table) {
	var tfoot = $('<tfoot></tfoot>');
	tfoot.appendTo(table);
	tr = $('<tr></tr>');
	tr.appendTo(tfoot);
	var firstOfTfoot = $('<th>均值</th>');
	firstOfTfoot.appendTo(tr);
	for (var f = 1; f < tags.length; f++) {
		var th = $('<th><input type="text" id="' + prefix + '13' + (f < 10 ? '0' + f : f)
				+ '" class="easyui-textbox" disabled="disabled" /></th>')
		th.appendTo(tr);
	}
}