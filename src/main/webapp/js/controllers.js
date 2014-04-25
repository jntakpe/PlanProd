ppApp.controller('HomeController', ['$scope', function ($scope) {
    "use strict";
}]);

ppApp.controller('LoginController', ['$scope', '$location', 'PPAuthService',
    function ($scope, $location, PPAuthService) {
        "use strict";
        $scope.login = function () {
            PPAuthService.login($scope.username, $scope.password, $scope.rememberMe, function () {
                $location.path('/home')
            });
        };

    }]);

ppApp.controller('LogoutController', [function () {
    "use strict";
}]);