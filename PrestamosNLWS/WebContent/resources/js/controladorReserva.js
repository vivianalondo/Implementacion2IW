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
	},
	
	//eliminar	
	this.eliminar = function(idD){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Reserva/eliminar',
			method:'POST',
			params:{
				idReserva: idD
			}
		});
	},
	
	//guardar
	this.guardar = function(reserva, login){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Reserva/guardar',
			method:'POST',
			params:{
				fechaReserva: reserva.fechaReserva,
				horaInicio: reserva.horaInicio,
				horaFinal : reserva.horaFinal,
				loginCrea : login
			}
		});
	}
	
});

moduloReservas.controller('reservaController', function($scope, $rootScope, $location, reservasServices){
	
	//Lista reservas
	reservasServices.listaReservas().then(
	function success(data){
		alert(data.data.reservaJersey);
		$scope.listaReservas = data.data.reservaJersey;	
	});
	
	//Función para eliminar Reservas
	$scope.eliminar = function(reserva) {
		alert('acá en eliminar reserva');
		reservasServices.eliminar(reserva.idReserva).then(
			function success(data){
				alert('Se ha eliminado la reserva');
				reservasServices.listaReservas().then(
						function success(data){
							alert(data.data.reservaJersey);
							$scope.listaReservas = data.data.reservaJersey;	
						});
			});
	};
	
	//Funcón que me lleva al guardar
	$scope.save = function(){
		$location.url('/guardarReserva');
	}
	
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


//Controlador para guardar nuevo dispositivo
moduloReservas.controller('guardarReserva', function($scope, $location, $cookies, reservasServices){
	
	console.log('entré al guardar')
	$scope.nombreUsuario = $cookies.nombreUsuario;
	console.log($cookies.nombreUsuario);
	console.log($scope.nombreUsuario);
	console.log('usuario guardar');
	
	$scope.guardar = function() {
		alert('acá en guardar reserva');
		reservasServices.guardar($scope.reserva, $scope.nombreUsuario).then(
			function success(data){
				alert('Se ha guardado la reserva');
				$location.url('/listaReservas');
				
			});
	};
	
	//Función que me lleva a la lista dispositivos
	$scope.backListD = function(){
		$location.url('/listaReservas');
	}
});

//Definir las rutas
moduloUsuarios.config(['$routeProvider', function($routeProvider){
	
	$routeProvider.when('/listaReservas', {
		templateUrl : 'ListaReservas.html',
		controller: 'reservaController'
	});
	
	$routeProvider.when('/guardarReserva', {
		templateUrl : 'GuardarReserva.html',
		controller: 'guardarReserva'
	});
	
	/**
	//Ruta editar
	$routeProvider.when('/editarDispositivo', {
		templateUrl : 'EditarDispositivo.html',
		controller: 'listaDispositivos'
	});
	
	*/
	
}]);