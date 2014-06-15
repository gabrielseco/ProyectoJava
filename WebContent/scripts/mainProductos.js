/**
 * JAVASCRIPT PARA CONTROLADOR CURSOS
 */
'use strict';

	
	var myApp = angular.module("myApp", []);
	
        myApp.controller("MainCtrl", function($scope, $http) {
            $http.get('../Servlet?submit=ProductosJSON').success(function(data) {
                $scope.productos = data;
              });
            $scope.currencySymbol='€';
            
        } );