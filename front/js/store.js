/*jshint eqeqeq:false */
(function (window) {
	'use strict';

	/**
	 * Creates a new client side storage object and will create an empty
	 * collection if no collection already exists.
	 *
	 * @param {string} name The name of our DB we want to use
	 * @param {function} callback Our fake DB uses callbacks because in
	 * real life you probably would be making AJAX calls
	 */
	function Store(host, callback) {
		callback = callback || function () {};

		this._host = host;
		this._dbName = 'todos';

		/*if (!localStorage.getItem(name)) {
			var todos = [];

			localStorage.setItem(name, JSON.stringify(todos));
		}

		callback.call(this, JSON.parse(localStorage.getItem(name)));*/
		var xhr = new XMLHttpRequest();

		xhr.open('GET', this._host + '/todos', true);
		xhr.send();

		xhr.onload = function () {
			if (xhr.status === 200 && callback) {
				callback.call(this, JSON.parse(xhr.responseText));
			} else {
				callback.call(this, []);
			}
		};
	}

	/**
	 * Finds items based on a query given as a JS object
	 *
	 * @param {object} query The query to match against (i.e. {foo: 'bar'})
	 * @param {function} callback	 The callback to fire when the query has
	 * completed running
	 *
	 * @example
	 * db.find({foo: 'bar', hello: 'world'}, function (data) {
	 *	 // data will return any items that have foo: bar and
	 *	 // hello: world in their properties
	 * });
	 */
	Store.prototype.find = function (query, callback) {
		if (!callback) {
			return;
		}
		var xhr = new XMLHttpRequest();

		if (typeof(query.id)==='number') {
			xhr.open('GET', this._host + '/todo/'+query.id, true);
		} else if (query.completed === true) {
			xhr.open('GET', this._host + '/todos/completed', true);
		} else {
			xhr.open('GET', this._host + '/todos/active', true);
		}
		xhr.send();

		xhr.onload = function () {
			console.log(JSON.parse(xhr.responseText));
			if (xhr.status === 200 && callback) {
				callback.call(this, JSON.parse(xhr.responseText));
			} else {
				callback.call(this, []);
			}
		};
	};

	/**
	 * Will retrieve all data from the collection
	 *
	 * @param {function} callback The callback to fire upon retrieving data
	 */
	Store.prototype.findAll = function (callback) {
		callback = callback || function () {};
		//callback.call(this, JSON.parse(localStorage.getItem(this._dbName)));
		var xhr = new XMLHttpRequest();

		xhr.open('GET', this._host + '/todos', true);
		xhr.send();

		xhr.onload = function () {
			if (xhr.status === 200 && callback) {
				callback.call(this, JSON.parse(xhr.responseText));
			} else {
				callback.call(this, []);
			}
		};
	};

	/**
	 * Will save the given data to the DB. If no item exists it will create a new
	 * item, otherwise it'll simply update an existing item's properties
	 *
	 * @param {object} updateData The data to save back into the DB
	 * @param {function} callback The callback to fire after saving
	 * @param {number} id An optional param to enter an ID of an item to update
	 */
	Store.prototype.save = function (updateData, callback, id) {
		//var todos = JSON.parse(localStorage.getItem(this._dbName));

		callback = callback || function() {};

		var xhr = new XMLHttpRequest();

		if (id) {
			xhr.open('PUT', this._host + '/todo/' + id, true);
		} else {
			xhr.open('POST', this._host + '/todo', true);
		}
		xhr.send();

		xhr.onload = function () {
			if (xhr.status === 200 && callback) {
				callback.call(this, []);
			} else {
				callback.call(this, []);
			}
		};
/*
		// If an ID was actually given, find the item and update each property
		if (id) {
			for (var i = 0; i < todos.length; i++) {
				if (todos[i].id === id) {
					for (var key in updateData) {
						todos[i][key] = updateData[key];
					}
					break;
				}
			}

			localStorage.setItem(this._dbName, JSON.stringify(todos));
			callback.call(this, todos);
		} else {
			// Generate an ID
			updateData.id = new Date().getTime();

			todos.push(updateData);
			localStorage.setItem(this._dbName, JSON.stringify(todos));
			callback.call(this, [updateData]);
		}*/
	};

	/**
	 * Will remove an item from the Store based on its ID
	 *
	 * @param {number} id The ID of the item you want to remove
	 * @param {function} callback The callback to fire after saving
	 */
	Store.prototype.remove = function (id, callback) {
		var todos = JSON.parse(localStorage.getItem(this._dbName));

		for (var i = 0; i < todos.length; i++) {
			if (todos[i].id == id) {
				todos.splice(i, 1);
				break;
			}
		}

		localStorage.setItem(this._dbName, JSON.stringify(todos));
		callback.call(this, todos);
	};

	/**
	 * Will drop all storage and start fresh
	 *
	 * @param {function} callback The callback to fire after dropping the data
	 */
	Store.prototype.drop = function (callback) {
		var todos = [];
		localStorage.setItem(this._dbName, JSON.stringify(todos));
		callback.call(this, todos);
	};

	// Export to window
	window.app = window.app || {};
	window.app.Store = Store;
})(window);