(function($) {
	$.fn.numberbox.defaults.filter = function(e) {
		var opts = $(this).numberbox('options');
		var s = $(this).numberbox('getText');
		if (e.which == 45) {
			return (s.indexOf('-') == -1 ? true : false);
		}
		
		var c = String.fromCharCode(e.which);
		if (c == opts.decimalSeparator) {
			return (s.indexOf(c) == -1 ? true : false);
		} else if (c == opts.groupSeparator) {
			return true;
		} else if ((e.which >= 48 && e.which <= 57 && e.ctrlKey == false && e.shiftKey == false) || e.which == 0 || e.which == 8) {
			return true;
		} else if (e.ctrlKey == true && (e.which == 99 || e.which == 118)) {
			return true;
		} else {
			return false;
		}
	}
})(jQuery);