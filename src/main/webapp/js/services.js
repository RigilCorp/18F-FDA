var fdaServices = angular.module('fda.services', []);

fdaServices.factory('UserService', [
	'$http',
	function($http) {
        var URL = '/rigil-18f/authentication';
		var service = {};
		
        function getByUsername(username) {
			
            return $http.post(URL, request).then(handleSuccess,
					handleError('Error getting user by username'));
		}

		// private functions

		function handleSuccess(data) {
			return data;
		}

		function handleError(error) {
			return function() {
				return {
					success : false,
					message : error
				};
			};
		}
	}]);

fdaServices.factory('FakeUserService', [ '$timeout', '$filter', '$q', '$log',
	function($timeout, $filter, $q, $log) {

		var users = [ {
			username : 'john@fake.com',
			password : 'johnfake'
		}, {
			username : 'mike@fake.com',
			password : 'mikefake'
		} ];

		function getByUsername(username) {
			var deferred = $q.defer();
			var filtered = $filter('filter')(users, {
				username : username
			});
			var user = filtered.length ? filtered[0] : null;
			$log.info('users', user);
			deferred.resolve(user);
			return deferred.promise;
		}

		var service = {};
		service.getByUsername = getByUsername;
		return service;
	}]);

fdaServices.factory('AuthenticationService', [ '$http', '$cookieStore',
	'$rootScope', '$timeout', '$log', 'FakeUserService',
	function($http, $cookieStore, $rootScope, $timeout, $log, UserService) {

        var URL = '/rigil-18f/authentication';
       
        var requestTemplate = {
           "enterpriseDocument": {
               "documentTimeStamp": "",
               "documentHeader": {
                   "documentServiceId": "",
                   "documentBusinessHeader": ""
               },
               "documentBody": {
                   "request": {
                       "requestMethod": "",
                       "requestCriteria": "",
                       "requestMessage": {
                           "authenticationRequest": {
                               "username": "",
                               "password": ""     
                             }
                           }
                       }
                   }
               }
           };
        
		function login(username, password, callback) {
           
            var request = angular.copy(requestTemplate);
    
            request.enterpriseDocument.documentBody.request.requestMethod = 'GET';
            request.enterpriseDocument.documentBody.request.requestMessage.authenticationRequest.username = username;
            request.enterpriseDocument.documentBody.request.requestMessage.authenticationRequest.password = password;
           
            $log.info('request: ', request)
            $http.post(URL, request)
            .success(function(data, status, headers, config) {
                var result = data.enterpriseDocument.documentBody.response.responseMessage.authenticationResponse.result;
                var response = {}
                if(result === 'SUCCESS'){
                     response.success = true;
                }
                else {
                    response.success = false,
                    response.message = "Invalid username or password"
                }
                callback(response);	
            })
            .error(function(data, status, headers, config) {
                response = {
                    success : false,
                     message : 'Unable to connect to server'
                };
                callback(response);	
            });
            
            
			/*$timeout(function() {
				var response = UserService.getByUsername(username);
				response.then(function(user) {
					if (user !== null && user.password === password) {
						
						response = {
							success : true
						};
					} else {
						response = {
							success : false,
							message : 'Username or password is incorrect'
						};
					}
					callback(response);
				});
			}, 1000);
*/
			/*
			 * Use this for real authentication
			 * ----------------------------------------------
			 */
			// $http.post('/api/authenticate', { username: username,
			// password:
			// password })
			// .success(function (response) {
			// callback(response);
			// });
		}

		 function setCredentials(username, password) {
			var authdata = Base64.encode(username + ':' + password);
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    authdata: authdata
                }
            };
 
            $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
            $cookieStore.put('globals', $rootScope.globals);
        }
		
		
		function clearCredentials() {
			$rootScope.globals = {};
			$cookieStore.remove('globals');
			$http.defaults.headers.common.Authorization = 'Basic ';
		}

		 // Base64 encoding service used by AuthenticationService
	    var Base64 = {
	 
	        keyStr: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=',
	 
	        encode: function (input) {
	            var output = "";
	            var chr1, chr2, chr3 = "";
	            var enc1, enc2, enc3, enc4 = "";
	            var i = 0;
	 
	            do {
	                chr1 = input.charCodeAt(i++);
	                chr2 = input.charCodeAt(i++);
	                chr3 = input.charCodeAt(i++);
	 
	                enc1 = chr1 >> 2;
	                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
	                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
	                enc4 = chr3 & 63;
	 
	                if (isNaN(chr2)) {
	                    enc3 = enc4 = 64;
	                } else if (isNaN(chr3)) {
	                    enc4 = 64;
	                }
	 
	                output = output +
	                    this.keyStr.charAt(enc1) +
	                    this.keyStr.charAt(enc2) +
	                    this.keyStr.charAt(enc3) +
	                    this.keyStr.charAt(enc4);
	                chr1 = chr2 = chr3 = "";
	                enc1 = enc2 = enc3 = enc4 = "";
	            } while (i < input.length);
	 
	            return output;
	        },
	 
	        decode: function (input) {
	            var output = "";
	            var chr1, chr2, chr3 = "";
	            var enc1, enc2, enc3, enc4 = "";
	            var i = 0;
	 
	            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
	            var base64test = /[^A-Za-z0-9\+\/\=]/g;
	            if (base64test.exec(input)) {
	                window.alert("There were invalid base64 characters in the input text.\n" +
	                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
	                    "Expect errors in decoding.");
	            }
	            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
	 
	            do {
	                enc1 = this.keyStr.indexOf(input.charAt(i++));
	                enc2 = this.keyStr.indexOf(input.charAt(i++));
	                enc3 = this.keyStr.indexOf(input.charAt(i++));
	                enc4 = this.keyStr.indexOf(input.charAt(i++));
	 
	                chr1 = (enc1 << 2) | (enc2 >> 4);
	                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
	                chr3 = ((enc3 & 3) << 6) | enc4;
	 
	                output = output + String.fromCharCode(chr1);
	 
	                if (enc3 != 64) {
	                    output = output + String.fromCharCode(chr2);
	                }
	                if (enc4 != 64) {
	                    output = output + String.fromCharCode(chr3);
	                }
	 
	                chr1 = chr2 = chr3 = "";
	                enc1 = enc2 = enc3 = enc4 = "";
	 
	            } while (i < input.length);
	 
	            return output;
	        }
	    };
		
		
		var service = {};
		service.login = login;
		service.clearCredentials = clearCredentials;
        service.setCredentials = setCredentials
		return service;

	}]);

fdaServices.factory('RegistrationService', ['$http','$log', function($http, $log){
    var service = {};
    
    var requestTemplate = {
        "enterpriseDocument": {
            "documentTimeStamp": "",
            "documentHeader": {
                "documentServiceId": "",
                "documentBusinessHeader": ""
            },
            "documentBody": {
                "request": {
                    "requestMethod": "",
                    "requestCriteria": "",
                    "requestMessage": {
                        "userRequest": {
                            "email": "",
                            "firstName": "",
                            "middleName": "",
                            "lastName": "",
                            "phone": "",
                            "zipcode": ""
                        }
                    }
                }
            }
        }
    };
    
    function register(userRegistrationData, callback){
        var request = angular.copy(requestTemplate);
        var url = '/rigil-18f/user';
        request.enterpriseDocument.documentBody.request.requestMethod = 'PUT';
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.firstName = userRegistrationData.firstname;
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.middleName = userRegistrationData.middlename;
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.lastName = userRegistrationData.lastname;
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.email = userRegistrationData.email;
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.phone = userRegistrationData.phone;
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.zipcode = userRegistrationData.zipcode;
        $log.info('request: ', request)
        $http.post(url, request)
        .success(function(data, status, headers, config) {
            var response = {
                success : true
            };
            callback(response);	
		})
        .error(function(data, status, headers, config) {
            response = {
                success : false,
			     message : 'Unable to save data.'
            };
            callback(response);	
		});
    }
    
    service.register = register;
    return service;
}]);