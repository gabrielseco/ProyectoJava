/**
 * JAVASCRIPT PARA CONTROLADOR CURSOS
 */

'use strict';

	var myApp = angular.module("myApp", []);
	
        myApp.controller("MainCtrl", function($scope, $http) {
        	$http.get('../Servlet?submit=CursosJSON').success(function(data) {
                $scope.cursos= data;
              });            
        } );