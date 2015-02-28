Ext.define('App.store.AccessLogs', {
	extend: 'Ext.data.Store',
	model: 'App.model.AccessLog',
	autoLoad: false,
	remoteSort: true,
	remoteFilter: true,
	pageSize: 30,
	sorters: [ {
		property: 'logIn',
		direction: 'DESC'
	} ]
});