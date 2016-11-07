var utilfaces = {
	block : function(id) {

		var loaderIcon = PrimeFaces.getFacesResource(
				'core/images/ajax-loader.gif', 'utilfaces', null);
		var dialog = $(PrimeFaces.escapeClientId(id));
		dialog.block({
			message : '<img src="' + loaderIcon + '" />',
			css : {
				border : 'none',
				background : 'none',
				opacity : 0.1,
			}
		});

	},

	unBlock : function(id, args) {
		$(PrimeFaces.escapeClientId(id)).unblock();
		this.aplayFocus(args, id);
	},

	aplayFocus : function(args, context) {
		if (args.validationFailed) {
			PrimeFaces.focus(args.faliedComponent);
		} else {
			PrimeFaces.focus(null, context);
		}
	}
};


/**
 * 
 */
$('.util-disable-click').bind({
	click : function() {
		return false;
	}
});