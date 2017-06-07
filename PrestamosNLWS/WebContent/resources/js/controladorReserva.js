/**
 * Clase para implementar el controlador de la reserva
 */

var moduloReservas = angular.module('modReservas', ['ngRoute', 'ngCookies', 'neurolab']);

moduloReservas.service('reservasServices', function($http, $cookies, $location){
	
	//listar 
	this.listaReservas = function(){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Reserva/listar',
			method:'GET'
		});
	}
	
});

moduloReservas.controller('reservaController', function($scope, $rootScope, $location, reservasServices){
	
	//Lista dispositivos
	reservasServices.listaReservas().then(
	function success(data){
		alert(data.data.reservaJersey);
		$scope.listaReservas = data.data.reservaJersey;	
	});
	
	//Función para eliminar Reservas
	$scope.eliminar = function(dispositivo) {
		alert('acá en eliminar');
		dispositivos.eliminar(dispositivo.idDispositivo).then(
			function success(data){
				alert('Se ha eliminado el dispositivo');
				dispositivos.listaDispositivos().then(
						function success(data){
							$scope.listaDispositivos = data.data.dispositivoJersey;	
						console.log($scope.listaDispositivos);
						});	
			});
	};
	
	/**
	 * 	
		
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
	
	//Función que me lleva al inicio
	$scope.back = function(){
		$location.url('/inicio');
	}
});

//Definir las rutas
moduloUsuarios.config(['$routeProvider', function($routeProvider){
	
	$routeProvider.when('/listaReservas', {
		templateUrl : 'ListaReservas.html',
		controller: 'reservaController'
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