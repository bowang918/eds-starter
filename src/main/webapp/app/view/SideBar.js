Ext.define('App.view.SideBar', {
	extend: 'Ext.tree.Panel',
	requires: [ 'App.view.poll.PollChart', 'App.view.user.Container', 'App.view.accesslog.TabPanel', 'App.view.logevent.List', 'App.view.config.Edit' ],
	title: i18n.navigation,
	itemId: 'menuTree',
	collapsible: true,
	layout: 'fit',
	minWidth: 100,
	maxWidth: 200,
	border: true,
	rootVisible: false,
	animate: false,
	store: Ext.create('App.store.Navigation')
});