/**
 * 
 */
var moduloRoles = angular.module('modRoles', ['ngRoute', 'ngCookies', 'neurolab']);

moduloRoles.service('rolesServices', function($http, $cookies, $location){
	
	//listar 
	this.listaRoles = function(){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Rol/listar',
			method:'GET'
		
		});
	}
});



moduloRoles.controller('rolController', function($scope, $rootScope, $location, rolesServices,productService){
		
		//Lista roles
		rolesServices.listaRoles().then(
		function success(data){
			alert(data.data.rolJersey);
			$scope.listaRoles = data.data.rolJersey;	
		});

		//Función que me lleva al inicio
		$scope.back = function(){
			$location.url('/inicio');
		}
	});

	//Definir las rutas
moduloRoles.config(['$routeProvider', function($routeProvider){
				
		$routeProvider.when('/listaRoles', {
			templateUrl : 'ListaRol.html',
			controller: 'rolController'
		});
}]);