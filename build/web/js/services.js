angular.module('mApp.services', [])
  .factory('profile', function ($resource) {

    return $resource('./Profile/:id', {id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
  
}).factory('trans', function ($resource) {

    return $resource('./Transaction/:id', {id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
  
}).factory('hash', function ($resource) {

    return $resource('./getTransactionHashes/:id', {id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
  
}).factory('transreport', function ($resource) {

    return $resource('./Transactionhistory/:id', {id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
  
}).factory('AllUser', function ($resource) {

    return $resource('./AllUser/:id', {id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
  
}).service('popupService', function ($window) {
    this.showPopup = function (message) {
        return $window.confirm(message);
    };
});