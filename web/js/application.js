/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('mApp', ['ui.router', 'ngResource', 'mApp.controllers', 'mApp.services', 'ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('mApp').config(function ($stateProvider) {
    $stateProvider.state('Profile', {
        url: '/profile',
        templateUrl: 'ethereum/profile.jsp',
        controller: 'ProfileController'

    }).state('SendTransaction', {
        url: '/sendtransaction',
        templateUrl: 'ethereum/SendTransaction.jsp',
        controller: 'SendTransactionController'

    }).state('getTransactionHashes', {
        url: '/getTransactionHashes',
        templateUrl: 'ethereum/getTransactionHashes.jsp',
        controller: 'getTransactionHashesController'

    }).state('Transactionreports', {
        url: '/Transactionreports',
        templateUrl: 'ethereum/Transactionhistory.jsp',
        controller: 'TransactionreportsController'

    }).state('AllUser', {
        url: '/AllUser',
        templateUrl: 'ethereum/AllUser.jsp',
        controller: 'AllUserController'

    });
}).run(function ($state) {
    $state.go('Profile');
});
