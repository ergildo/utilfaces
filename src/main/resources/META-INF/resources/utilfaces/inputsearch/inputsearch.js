/**
 * @author ergildo.dia Input Seach - Input de pesquisa.
 */
PrimeFaces.widget.InputSearch = PrimeFaces.widget.BaseWidget.extend({

	init : function(cfg) {
		this._super(cfg);
		this.content = $(this.jqId);
		this.bindEvents();
	},

	bindEvents : function() {
		this.inputSearc = $(this.jqId + "_input");
		var $this = this;

		this.inputSearc.blur(function() {
			var value = $($this.inputSearc).val();
			$this.search(value);
		});
	},

	search : function(query) {
		var $this = this;
		var options = {
			source : this.id,
			process : this.id,
			update : this.id,
			formId : this.cfg.formId,
			onsuccess : function(responseXML, status, xhr) {
				PrimeFaces.ajax.Response.handle(responseXML, status, xhr, {
					widget : $this,
					handle : function(content) {
						this.content.html(content);
					}
				});

				return true;
			},
			oncomplete : function() {
				$this.bindEvents();
			}
		};

		options.params = [ {
			name : this.id + '_inputSearchRequest',
			value : query
		} ];
		PrimeFaces.ajax.AjaxRequest(options);
	}

});
