/**
 * 
 */


var filters = angular.module('fda.filters', []);

//Capitalize first letter of a word or whole word.
filters.filter('capitalize', function(){
	return function(input, all) {
	      return (!!input) ? input.replace(/([^\W_]+[^\s-]*) */g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();}) : '';
	 }
});