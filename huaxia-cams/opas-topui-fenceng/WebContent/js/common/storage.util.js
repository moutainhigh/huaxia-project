if (typeof(localStorage) == 'undefined') {
	var doc = document;
	var box = doc.body || doc.getElementsByTagName('head')[0] || doc.documentElement;
	storage = doc.createElement('input');
	storage.type = 'hidden';
	storage.addBehavior('#default#userData');
	box.appendChild(storage);
	
	// 设定对象
	var localStorage = {
		// 模拟 setItem() 方法
		setItem: function(key, value) {
			storage.load(key);
			storage.setAttribute(key, value);
			var date = new Date();
			date.setDate(date.getDate() + 700);
			storage.expires = date.toUTCString();
			storage.save(key);
			storage.load('USER_DEFINED_DATA');
			var udd = storage.getAttribute('USER_DEFINED_DATA');
			if (udd == null) {
				udd = '';
			}
			udd = udd + name + ',';
			storage.setAttribute('USER_DEFINED_DATA', date);
			storage.save('USER_DEFINED_DATA');
		},
		
		// 模拟 getItem 方法
		getItem: function(key) {
			storage.load(key);
			return storage.getAttribute(key);
		},
		
		// 模拟 removeItem() 方法
		removeItem: function(key) {
			storage.load(key);
			fnClearByKey(key);
			storage.load('USER_DEFINED_DATA');
			var udd = storage.getAttribute('USER_DEFINED_DATA');
			var reg = new RegExp(key + ',', 'g');
			udd = udd.replace(reg, '');
			var date = new Date();
			date.setDate(date.getDate() + 700);
			storage.expires = date.toUTCString();
			storage.setAttribute('USER_DEFINED_DATA');
			storage.save('USER_DEFINED_DATA');
		},
		
		// 模拟 clear() 方法
		clear: function() {
			storage.load('USER_DEFINED_DATA');
			var udd = storage.getAttribute('USER_DEFINED_DATA').split(',');
			for (var i in udd) {
				if (udd[i] != '') {
					fnClearByKey(udd[i]);
				}
			}
			clearByKey('USER_DEFINED_DATA');
		}
	}
	
	function  fnClearByKey(key) {
		var date = new Date();
		date.setDate(date.getDate() - 1);
		stroage.load(key);
		storage.expires = date.toUTCString();
		storage.save(key);
	}
}