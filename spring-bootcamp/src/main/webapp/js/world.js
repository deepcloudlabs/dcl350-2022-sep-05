var Country = function(country) {
	var self = this;
	self.code = ko.observable(country.code);
	self.name = ko.observable(country.name);
	self.continent = ko.observable(country.continent);
	self.population = ko.observable(country.population);
	self.surfaceArea = ko.observable(country.surfaceArea);
	self.gnp = ko.observable(country.gnp);
	self.updateModel = function(country) {
		self.code(country.code);
		self.name(country.name);
		self.population(country.population);
		self.surfaceArea(country.surfaceArea);
		self.continent(country.continent);
		self.gnp(country.gnp);
	}
	self.getModel = function() {
		return {
			code : self.code(),
			name : self.name(),
			population : self.population(),
			continent : self.continent(),
			gnp : self.gnp(),
			surfaceArea : self.surfaceArea()
		};
	}
}
var WorldViewModel = function() {
	var self = this;
	self.continents = ko.observableArray([]);
	self.countries = ko.observableArray([]);
	self.country = new Country({});
	self.status = ko.observable();
	$.ajax({
		method: 'GET',
		url: 'http://localhost:9200/world/api/v1/continents',
		success: function(continents) {
		   self.continents(continents);
	    }
	});
	

	self.list = function() {
		$.ajax({
			method: 'GET',
			contentType: 'jsonp',
			url: 'http://localhost:9200/world/api/v1/countries/'+self.country.continent(), 
			success: function(countries) {
			   self.countries(countries);
		    }
		});
	};
	self.sort = function(column) {
		self.countries.sort(function(country1, country2) {
			return country1[column].localeCompare(country2[column]);
		});
	};
	self.sortNumeric = function(column) {
		self.countries.sort(function(country1, country2) {
			return Number(country1[column]) - Number(country2[column]);
		});
	};
}
var vm = new WorldViewModel();
$(document).ready(function() {
	ko.applyBindings(vm);
});