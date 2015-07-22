var pxb = {
	call : function(url, params, callback) {
		$.ajax({
			async : false,
			url : pxbPath + url,
			data : params,
			success: callback
		});
	}
}