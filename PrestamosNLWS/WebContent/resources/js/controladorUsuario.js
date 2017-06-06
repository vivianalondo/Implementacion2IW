/**
 * 
 */
var moduloUsuarios = angular.module('modUsuarios', ['ngRoute', 'ngCookies']);

moduloUsuarios.service('usuariosServices', function($http, $cookies, $location){
	
	//listar 
	this.listaUsuarios = function(){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Usuario/listar',
			method:'GET'
		});
	}
	
});


moduloUsuarios.controller('usuarioController', function($scope, $rootScope, $location, usuariosServices){
	
	//Lista dispositivos
	usuariosServices.listaUsuarios().then(
	function success(data){
		alert(data.data.usuarioJersey);
		$scope.listaUsuarios = data.data.usuarioJersey;	
	});
	/**
	//Función eliminar
	$scope.eliminar = function(dispositivo) {
		alert('acá en eliminar');
		dispositivos.eliminar(dispositivo.idDispositivo).then(
			function success(data){
				alert('Se ha eliminado el dispositivo');
				$location.url('/listaDispositivos');
				
			});
		};
		
		
		//Funciones para editar
		$scope.editar = function(dispositivoM){			
			$rootScope.dispositivoModificar = dispositivoM;
			$location.url('/editarDispositivo');
		};
		
		
		$scope.modificar = function(dispositivoMod) {
						
			dispositivos.modificar(dispositivoMod).then(
				function success(data){
					alert('Se ha modificado el dispositivo');
					$location.url('/listaDispositivos')
				});
			};
	*/
});


//Definir las rutas
moduloUsuarios.config(['$routeProvider', function($routeProvider){
	
	
	$routeProvider.when('/listaUsuarios', {
		templateUrl : 'ListaUsuarios.html',
		controller: 'usuarioController'
	});
	
	/**
	//Ruta editar
	$routeProvider.when('/editarDispositivo', {
		templateUrl : 'EditarDispositivo.html',
		controller: 'listaDispositivos'
	});
	
	//Ruta editar
	$routeProvider.when('/eliminarDispositivo', {
		templateUrl : 'EliminarDispositivos.html',
		controller: 'eliminarDispositivos'
	});*/
	
}]);

