Ext.define('App.view.user.Container', {
	extend: 'Ext.container.Container',
	requires: [ 'App.controller.User', 'App.view.user.Grid', 'App.view.user.Form' ],
	controller: 'App.controller.User',
	
	title: i18n.user_users,
	closable: true,
	
	layout: {
		type: 'border'
	},

	defaults: {
		split: true
	},

	initComponent: function() {
		this.items = [ Ext.create('App.view.user.Grid', {
			region: 'center'
		}) ];
		this.callParent(arguments);
	}

});
