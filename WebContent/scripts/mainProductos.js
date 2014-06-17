/**
 * JAVASCRIPT PARA CONTROLADOR CURSOS
 */
'use strict';

	
	var myApp = angular.module("myApp", []);
		
	myApp.directive('productoListado',function(){
		return{
			restrict:'E',
			templateUrl:'productos-template.html',
			controller:function($scope, $http,$rootScope) {
	            $http.get('../Servlet?submit=ProductosJSON').success(function(data) {
	                $scope.productos = data;
	              });
	            $rootScope.carrito = 0.00;
	            $scope.add=function(secProducto,precio,nombre){
	            	
	            	var pedido = localStorage.getItem("pedido");
	            	var html = "";
	            	if(pedido === null){
	                    $rootScope.carrito += parseInt(precio);
	            		html += '{'
	            		       +'"nombre" :"'+nombre+'",'
	            		       +'"secProducto"  : "'+secProducto+'",'
	            		       +'"precio" : "'+precio+'"'
	            		       +'}';
	            		localStorage.setItem("pedido", html);
	            	}
	            	else{
	            		$rootScope.carrito +=parseInt(precio); 
	            		html+=localStorage.getItem("pedido");
	            		html+= ',{'
	            		       +'"nombre" :"'+nombre+'",'
	            		       +'"secProducto"  : "'+secProducto+'",'
	            		       +'"precio" : "'+precio+'"'
	            		       +'}';
	            		localStorage.setItem("pedido",html);
	            	}
	            };
			  }
			};
		});
	            	