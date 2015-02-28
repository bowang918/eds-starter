Ext.define('App.store.LogEvents', {
	extend: 'Ext.data.Store',
	model: 'App.model.LogEvent',
	autoLoad: false,
	remoteSort: true,
	remoteFilter: true,
	pageSize: 30,
	sorters: [ {
		property: 'eventDate',
		direction: 'DESC'
	} ]
});