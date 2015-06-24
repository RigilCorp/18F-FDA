var fdaApp = angular.module('fda.app',[
    'ngRoute',
    'ngCookies',
    'fda.controllers',
    'fda.services',
    'fda.directives'
]);

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
    .otherwise({
        redirectTo: '/preference'
    });
});

fdaApp.run(function($rootScope, $location, $cookieStore, $http, $log){
    $log.info('Executing Run...');
    //Keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; 
    }
    
    $rootScope.$on('$locationChangeStart', function(event, next, current){
        //Redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(),['/login', '/registration']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if(loggedIn && $.inArray($location.path(),['/logout']) === -1){
            $location.path('/preference');
        }
        if(restrictedPage && !loggedIn){
            $location.path('/login');
        }
    });
});

