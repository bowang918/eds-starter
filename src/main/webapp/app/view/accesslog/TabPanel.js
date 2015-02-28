Ext.define('App.view.accesslog.TabPanel', {
	extend: 'Ext.tab.Panel',
	requires: [ 'App.view.accesslog.Grid', 'App.view.accesslog.UaGraph', 'App.view.accesslog.OsGraph' ],
	controller: 'App.controller.AccessLog',

	title: i18n.accesslog,
	closable: true,
	border: true,
	plain: true,
	padding: '2 0 0 0',

	initComponent: function() {
		this.items = [ {
			xtype: 'accesslog'
		}, {
			xtype: 'uagraph'
		}, {
			xtype: 'osgraph'
		} ];
		this.callParent(arguments);
	}

});
