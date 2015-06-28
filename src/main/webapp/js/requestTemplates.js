/**
 * Object return a copy of request contract.
 */

var requestTemplates = {

    fdaDataRequest : {
        "enterpriseDocument" : {
            "documentTimeStamp" : "",
            "documentHeader" : {
                "documentServiceId" : "FDADATA",
                "documentBusinessHeader" : ""
            },
            "documentBody" : {
                "request" : {
                    "requestMethod" : "",
                    "requestCriteria" : "",
                    "requestMessage" : {
                        "fdaDataRequest" : {
                            "dataCode" : "",
                            "dataName" : "",
                            "dataDesc" : ""
                        }
                    }
                }
            }
        }
    },
    createFdaDataRequest : function() {
        return angular.copy(this.fdaDataRequest);
    },
    authenticationRequest : {
        "enterpriseDocument" : {
            "documentTimeStamp" : "",
            "documentHeader" : {
                "documentServiceId" : "",
                "documentBusinessHeader" : ""
            },
            "documentBody" : {
                "request" : {
                    "requestMethod" : "",
                    "requestCriteria" : "",
                    "requestMessage" : {
                        "authenticationRequest" : {
                            "username" : "",
                            "password" : ""
                        }
                    }
                }
            }
        }
    },
    createAuthenticationRequest : function() {
        return angular.copy(this.authenticationRequest);
    },
    registrationRequest : {
        "enterpriseDocument" : {
            "documentTimeStamp" : "",
            "documentHeader" : {
                "documentServiceId" : "",
                "documentBusinessHeader" : ""
            },
            "documentBody" : {
                "request" : {
                    "requestMethod" : "",
                    "requestCriteria" : "",
                    "requestMessage" : {
                        "userRequestJsonDataObject" : {
                            "email" : "",
                            "firstName" : "",
                            "middleName" : "",
                            "lastName" : "",
                            "phone" : "",
                            "zipcode" : ""
                        }
                    }
                }
            }
        }
    },
    createRegistrationRequest : function() {
        return angular.copy(this.registrationRequest);
    },
    preferenceObject : {
        "fdaJsonDataObject" : {
            "dataName" : "",
            "dataCode" : ""
        }
    },
    createPreferenceObject : function() {
        return angular.copy(this.preferenceObject);
    },
    preferenceRequest : {
        "enterpriseDocument" : {
            "documentTimeStamp" : "",
            "documentHeader" : {
                "documentServiceId" : "",
                "documentBusinessHeader" : ""
            },
            "documentBody" : {
                "request" : {
                    "requestMethod" : "",
                    "requestCriteria" : "",
                    "requestMessage" : {
                        "userRequestJsonDataObject" : {
                            "email" : "",
                            "firstName" : "",
                            "middleName" : "",
                            "lastName" : "",
                            "phone" : "",
                            "zipcode" : "",
                            "preferencesList" : []

                        }
                    }
                }
            }
        }
    },
    createPreferenceRequest : function() {
        return angular.copy(this.preferenceRequest);
    }
}