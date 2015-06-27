
var fdaApp = angular.module('fda.app',[
    'ngRoute',
    'ngCookies',
    'ui.bootstrap',
    'angucomplete',
    'fda.controllers',
    'fda.services',
    'fda.directives',
    'fda.filters'
]);

//Configure routeProvider 
fdaApp.config(function($routeProvider){
    $routeProvider
    .when('/login', {
        templateUrl: 'pages/login.html',
        controller: 'loginController'
    })
    .when('/logout', {
        template: "",
        controller: 'logoutController'
    })
    .when('/registration', {
        templateUrl: 'pages/registration.html',
        controller: 'registrationController'
    })
    .when('/preference', {
        templateUrl: 'pages/preference.html',
        controller: 'preferenceController'
    })
    .when('/preference/:device/:name', {
        templateUrl: 'pages/preference.html',
        controller: 'preferenceController'
    })
    .otherwise({
        redirectTo: '/preference'
    });
});

fdaApp.run(function($rootScope, $location, $cookieStore, $http, $log){
 
    //Keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
 
    if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; 
    }
    
    $rootScope.$on('$locationChangeStart', function(event, next, current){
        //Redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(),['/login', '/registration']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
       
        if(restrictedPage && !loggedIn){
        	if(!$rootScope.redirectTo){
        		$rootScope.redirectTo = $location.path();
        	}
            $location.path('/login');
        }
    });
});

