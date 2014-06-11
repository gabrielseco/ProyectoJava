/**
 * JAVASCRIPT PARA CONTROLADOR CURSOS
 */
'use strict';

	var myApp = angular.module("myApp", []);
	
        myApp.controller("MainCtrl", function($scope, $http) {
               
            $http.get('../api/productos/productos.jsp').success(function(data) {
                $scope.productos = data;
              });
            $scope.orderProp = 'edad';
        } );