/**
 * Clase para implementar el controlador de la reserva
 */

var moduloReservas = angular.module('modReservas', ['ngRoute', 'ngCookies', 'neurolab']);

moduloReservas.service('reservasServices', function($http, $cookies, $location){
	
	/**
	 * Método que hace llamado al web services de listar reserva
	 */
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
	},
	
	//Modificar
	this.modificar = function(reserva, login){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Reserva/modificar',
			method:'POST',
			params:{
				idReserva: reserva.idReserva,
				fechaReserva: reserva.fechaReserva,
				horaInicio: reserva.horaInicio,
				horaFinal: reserva.horaFinal,
				fechaEntrega: reserva.fechaEntrega,
				horaEntrega: reserva.horaEntrega,
				estadoreserva: reserva.estadoreserva,
				loginCrea: login			}
		});
	},
	
	//buscar
	this.buscar = function(id){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Reserva/obtener',
			method:'GET',
			params:{
				idReserva : id	
			}
		});
	}
	
});

moduloReservas.controller('reservaController', function($scope, $rootScope, $cookies, $location, reservasServices, productService){
	
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
	
	//Funciones para editar
	$scope.editar = function(reservaM){			
		$rootScope.reservaModificar = reservaM;
		$location.url('/editarReserva');
	};
	
	$scope.nombreUsuario = $cookies.nombreUsuario;
	
	$scope.modificar = function(reservaoMod) {
						
		reservasServices.modificar(reservaoMod,$scope.nombreUsuario).then(
			function success(data){
				alert('Se ha modificado la reserva');
				$location.url('/listaReservas')
			});
		};

		//Buscar reserva por id
		$scope.buscarReserva = function(idReserva){
			productService.addProduct(idReserva);
			$location.url('/buscarReserva');
		}
	
	//Función que me lleva al inicio
	$scope.back = function(){
		$location.url('/inicio');
	}
});


//controlador para buscar usuarios
moduloReservas.controller('buscarReserva', function($scope, $location, reservasServices, productService){
	
	$scope.products = productService.getProducts();
	alert($scope.products)

	reservasServices.buscar($scope.products).then(
		function success(data){		
			$scope.listaReservaResultante = data.data;
			console.log(data.data);
		});	
	
	alert($scope.listaReservaResultante);
	
	
	//Función que me lleva a la lista dispositivos
	$scope.backListD = function(){
		$location.url('/listaReservas');
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
moduloReservas.config(['$routeProvider', function($routeProvider){
	
	$routeProvider.when('/listaReservas', {
		templateUrl : 'ListaReservas.html',
		controller: 'reservaController'
	});
	
	$routeProvider.when('/guardarReserva', {
		templateUrl : 'GuardarReserva.html',
		controller: 'guardarReserva'
	});
	
	
	//Ruta editar
	$routeProvider.when('/editarReserva', {
		templateUrl : 'EditarReserva.html',
		controller: 'reservaController'
	});
	
	
	$routeProvider.when('/buscarReserva', {
		templateUrl : 'BuscarReserva.html',
		controller: 'buscarReserva'
	});
	
}]);