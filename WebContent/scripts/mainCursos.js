/**
 * JAVASCRIPT PARA CONTROLADOR CURSOS
 */

'use strict';

	var myApp = angular.module("myApp", []);
	
	myApp.directive('cursosListado',function(){
		return{
			restrict:'E',
			templateUrl:'cursos-template.html',
			controller:function($http,$scope){
				$http.get('../Servlet?submit=CursosJSON').success(function(data) {
	                $scope.cursos= data;
	              });
	        	$scope.inscribirseCurso=function(secCurso,precio){
	        		location.href='../Servlet?submit=InscribirseCurso&id='+secCurso+"&precio="+precio+"&idUs="+sessionStorage.getItem("idUsuario");
	        	};
			}
		};
	});