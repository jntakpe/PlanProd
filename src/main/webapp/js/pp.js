var ppApp = angular.module('ppApp', ['http-auth-interceptor', 'ngResource', 'ngRoute']);

ppApp.config(['$routeProvider',
    function ($routeProvider) {
        "use strict";
        $routeProvider
            .when('/home', {templateUrl: 'views/home.html', controller: 'HomeController'})
            .when('/logout', {templateUrl: 'views/login.html', controller: 'LogoutController'})
            .otherwise({templateUrl: 'views/login.html', controller: 'LoginController'});
    }]).run(['$rootScope', '$location', 'PPAuthService', 'UserResource',
    function ($rootScope, $location, PPAuthService, UserResource) {
        "use strict";

        //Fonction utilisée lors d'un changement d'url pour vérifier si l'utilisateur est authentifié.
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            PPAuthService.authenticate(function () {
                $rootScope.authOK = true;
            });
        });

        //Fonction utilisée lorsque le serveur renvoi le code 401
        $rootScope.$on('event:auth-loginRequired', function (rejection) {
            $rootScope.authOK = false;
            if ($location.path() !== '/' && $location.path() !== "") {
                $location.path('/').replace();
            }
        });

        //Fonction utilisée lorsque l'utilisateur s'est authentifié
        $rootScope.$on('event:auth-authConfirmed', function () {
            $rootScope.authOK = true;
            $rootScope.utilisateur = UserResource.get();
            if ($location.path() === '/') { //Si la page de login est demandée alors que l'utilisateur est déjà loggé
                $location.path('/home').replace();
            }
        });

        //Fonction utilisée lorsque l'utilisateur se connecte
        $rootScope.$on('event:auth-loginConfirmed', function () {
            $rootScope.authOK = true;
            $rootScope.utilisateur = UserResource.get();
            $location.path('/home').replace();
        });

        //Fonction utilisée lorsque l'utilisateur se déconnecte
        $rootScope.$on('event:auth-loginCancelled', function () {
            $rootScope.authOK = false;
            $location.path('/');
        });

    }]);