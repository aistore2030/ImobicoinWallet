angular.module('mApp.controllers', [])
        .controller('ProfileController', function ($scope, profile) {


            $scope.x = profile.get();
            $scope.UpdateDetails = function (x) {
                x.$update(function () {
                    alert("Success");
                    $scope.x = profile.get();
                    //$state.go('ViewMarginplan');
                });


            };
}).controller('SendTransactionController', function ($scope, trans) {

    $scope.x = trans.get();
    $scope.Submit = function (x) {
        //alert("Success");
        x.$update(function (msg) {
            alert(msg.Message);
         //   alert("Success");
            $scope.x = trans.get();

        });

    };

}).controller('getTransactionHashesController', function ($scope, hash) {

    $scope.TransactionHashes = hash.query();


}).controller('TransactionreportsController', function ($scope, transreport) {

    $scope.myTxt = transreport.query();


}).controller('AllUserController', function ($scope, AllUser) {

    $scope.myTxt = AllUser.query();
     $scope.disableUser = function (x) {

        x.case = "UnVerified";
        x.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = AllUser.query();
        });


    };
    $scope.enableUser = function (x) {

        x.case = "Verified";
        x.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = AllUser.query();
        });


    };

});