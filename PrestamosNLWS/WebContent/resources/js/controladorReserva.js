/**
 * Clase para implementar el controlador de la reserva
 */

var moduloReservas = angular.module('modReservas', ['ngRoute', 'ngCookies', 'neurolab','modUsuarios']);


//Conjunto de servicios web de usuarios
moduloReservas.service('dispositivosdisponibles', function($http, $cookies, $location){
	//funcion que hace uso del servicio web autenticar de usuario
	this.listardisponibles = function(est, date){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Dispositivo/listardisponibles',
			method:'GET',
			params: {
				estado: est,
				fecha: date
			}
		});
	}
	
	
});

//Conjunto de servicios web de usuarios
moduloReservas.service('asociardispositivos', function($http, $cookies, $location){	
	/**
	 * Método que hace llamado al web services de guardar reserva
	 */	
	this.asociardispositivo = function(idreserva, iddispositivo, idestadoreserva){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/EquipoXReserva/registrar',
			method:'POST',
			params:{
				reserva: idreserva,
				dispositivo: iddispositivo,
				estadoReserva : idestadoreserva
			}
		});
	}
	
});

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
	
	/**
	 * Método que hace llamado al web services de eliminar reserva
	 */	
	this.eliminar = function(idD){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Reserva/eliminar',
			method:'POST',
			params:{
				idReserva: idD
			}
		});
	},
	
	/**
	 * Método que hace llamado al web services de guardar reserva
	 */	
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
	
	/**
	 * Método que hace llamado al web services de modificar reserva
	 */	
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
	
	/**
	 * Método que hace llamado al web services de buscar reserva
	 */	
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

/**
 * Controlador para reserva
 */
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
	$scope.asociar = function(idReserva,fecha){
		productService.addProduct(fecha);
		productService.addProduct2(idReserva);
		$location.url('/listaDispositivosActivos');
	}
	
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
				alert('SE HA MODIFICADO LA ESERVA EXITOSAMENTE');
				$location.url('/listaReservas')
			});
		};

		/**
		 * función para pasar a la vista buscar reserva
		 */
		$scope.buscarReserva = function(idReserva){
			productService.addProduct(idReserva);
			$location.url('/buscarReserva');
		}
	
	/**
	 * Función para regresar al inicio de la página
	 */
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
moduloReservas.controller('guardarReserva', function($scope, $location, $cookies, reservasServices,productService){
	
	console.log('entré al guardar')
	$scope.nombreUsuario = $cookies.nombreUsuario;
	console.log($cookies.nombreUsuario);
	console.log($scope.nombreUsuario);
	console.log('usuario guardar');
	
	$scope.guardar = function(reserva) {
		alert('acá en guardar reserva');
		reservasServices.guardar($scope.reserva, $scope.nombreUsuario).then(
			function success(data){
				alert('Se ha guardado la reserva');
				alert($scope.reserva.fechaReserva)
//				productService.addProduct($scope.reserva.fechaReserva);
				$location.url('/listaReservas');
				
			});
	};
	
	//Función que me lleva a la lista dispositivos
	$scope.backListD = function(){
		$location.url('/listaReservas');
	}
});

//controlador para lista de dispositivos
moduloReservas.controller('listaDispositivosActivos', function($scope, $location,$rootScope, dispositivos,dispositivosdisponibles,asociardispositivos, $cookies, usuarios, productService){
	
	$scope.nombreUsuario = $cookies.nombreUsuario;
	
	//Lista dispositivos
	$scope.products = productService.getProducts();
	$scope.products2 = productService.getProducts2();
	alert($scope.products)
	alert($scope.products2)
	
	dispositivosdisponibles.listardisponibles(1, $scope.products).then(
	function success(data){
		//alert(data.data.dispositivoJersey);
		$scope.listaDispositivosDisponibles = data.data.dispositivoJersey;	
	});
	
	//Funcón que me lleva al guardar
	$scope.Asociarreserva = function(iddispositivo){
		asociardispositivos.asociardispositivo($scope.products2, iddispositivo, 1)
		$location.url('/listaDispositivosActivos');
	}
	
	//$scope.listaDispositivosResultante
	$scope.buscardispositivo = function(nombre){	
		productService.addProduct(nombre);
		$location.url('/buscardispositivo');
	}
	//Función que me lleva al inicio
	$scope.back = function(){
		$location.url('/inicio');
	}
	
	//Función que me lleva a la lista dispositivos
	$scope.backListD = function(){
		$location.url('/listaDispositivos');
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
	
	$routeProvider.when('/listaDispositivosActivos', {
		templateUrl : 'AgregarDispostivos.html',
		controller: 'listaDispositivosActivos'
	});
	
	
	
}]);