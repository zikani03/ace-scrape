(function (){
    'use strict';
    
    var aceScrape = angular.module('aceScrape', ['restangular', 'ngRoute']);
    
    var MarketsController =  function($scope, Restangular) {        
            // This will query /markets and return a promise.
            var query = Restangular.one("markets");
            
            query.get().then(function(data) {
                $scope.markets = data.markets;
            });
        },
        MarketDetailController = function($scope, $location, Restangular, market) {
            
            $scope.market = market;
            
        },
        ContractsController = function($scope, Restangular) {
            
            $scope.contracts = Restangular.one("contracts").get().$contracts;
            
        },
        ContractDetailController =function($scope, $location, Restangular, contract) {
            
            $scope.contract = contract;
            
        };
        
    aceScrape.controller('MarketsController',MarketsController).
      controller('MarketDetailController', MarketDetailController).
      controller('ContractsController', ContractsController ).
      controller('ContractDetailController', ContractDetailController);


    aceScrape.config(function($routeProvider, RestangularProvider) {
            
        $routeProvider.
          when('/', {
            controller: MarketsController, 
            templateUrl:'markets.list.html'
          }).
          when('/markets/:name', {
            controller:MarketDetailController, 
            templateUrl:'markets.detail.html',
            resolve: {
              market: function(Restangular, $route){
                return Restangular.one('markets', $route.current.params.name).get();
              }
            }
          }).
          when('/contracts', {
              controller: ContractsController, 
              templateUrl:'contracts.list.html'
          }).
          when('/contracts/:ref', {
            controller:ContractDetailController, 
            templateUrl:'contracts.detail.html',
            resolve: {
              project: function(Restangular, $route){
                return Restangular.one('contracts', $route.current.params.ref).get();
              }
            }
          }).
          otherwise({redirectTo:'/'});
          
          RestangularProvider.setBaseUrl('http://localhost:4567');
          // To invalidate the server side cache and force the app to reload data from Ace Africa
          //RestangularProvider.setDefaultRequestParams({ 'eTag': new Date() })
          
          RestangularProvider.setRequestInterceptor(function(elem, operation, what) {
            if (operation === 'put') {
              elem._id = undefined;
              return elem;
            }
            return elem;
          });
      });
      
})(this);