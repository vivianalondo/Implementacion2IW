/**
 * 
 */
var moduloUsuarios = angular.module('modUsuarios', ['ngRoute', 'ngCookies', 'neurolab']);



moduloUsuarios.service('usuariosServices', function($http, $cookies, $location){
	
	//listar 
	this.listaUsuarios = function(){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Usuario/listar',
			method:'GET'
		});
	}
	
	//eliminar	
	this.eliminar = function(idUsuario){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Usuario/Eliminar',
			method:'GET',
			params:{
				identificacion: idUsuario
			}
		});
	}
	
	
	//buscar
	this.buscar = function(id){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Usuario/obtener',
			method:'GET',
			params:{
				identificacion : id	
			}
		});
	}
	
	
	//guardar
	this.guardar = function(user){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Dispositivo/guardar',
			method:'POST',
			params:{
						identificacion:user.identificacion,
						tipoDocumento:user.tipoDocumento,
						nombre:user.nombre,
						apellido:user.apellido,
						telefono:user.telefono,
						email:user.email,
						login:user.login,
						pw:user.pw,
						estadoUsuario: user.estadoUsuario,
						rol:user.rol

			}
		});
	}

	

	
	
});


moduloUsuarios.controller('usuarioController', function($scope, $rootScope, $location, usuariosServices,productService){
	
	//Lista usuarios
	usuariosServices.listaUsuarios().then(
	function success(data){
		alert(data.data.usuarioJersey);
		$scope.listaUsuarios = data.data.usuarioJersey;	
	});
	
	

	//Función para eliminar usuario
	$scope.eliminar = function(usuario) {
		alert('acá en eliminar de usuario');
		usuariosServices.eliminar(usuario.identificacion).then(
			function success(data){
				alert('Se ha eliminado el usuario');
				usuariosServices.listaUsuarios().then(
						function success(data){
							alert(data.data.usuarioJersey);
							$scope.listaUsuarios = data.data.usuarioJersey;	
						});
			});
	};

	
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
	$scope.buscarusuario = function(identificacion){
		
		productService.addProduct(identificacion);
		$location.url('/buscarusuario');
	}
	
	//Funcón que me lleva al guardar
	$scope.saveuser = function(){
		$location.url('/guardarusuario');
	}
	
	
	//Función que me lleva al inicio
	$scope.back = function(){
		$location.url('/inicio');
	}
});


//controlador para buscar usuarios
moduloUsuarios.controller('busquedausuario', function($scope, $location, usuariosServices,productService){
	
	$scope.products = productService.getProducts();
//	alert($scope.products)
//	alert("editado")
	usuariosServices.buscar($scope.products).then(
		function success(data){		
			$scope.listaUsuariosResultante = data.data;
		});	
	
	alert($scope.listaUsuariosResultante);
	
	
	//Función que me lleva a la lista dispositivos
	$scope.backListD = function(){
		$location.url('/listaUsuarios');
	}
});

//Controlador para guardar nuevo usuario
moduloUsuarios.controller('guardarusuario', function($scope, $location, $cookies, usuariosServices){
	
	$scope.nombreUsuario = $cookies.nombreUsuario;
	$scope.guardaruser = function() {
		alert('acá en guardar de ususario');
		usuariosServices.guardar($scope.usuario).then(
			function success(data){
				alert('Se ha guardado el usuario');
				$scope.refrescar = function(){
					$location.url('/listaUsuarios')
				};
				
			});
	};
	
	//Función que me lleva a la lista dispositivos
	$scope.backListD = function(){
		$location.url('/listaUsuarios');
	}
});




//Definir las rutas
moduloUsuarios.config(['$routeProvider', function($routeProvider){
	
	
	$routeProvider.when('/listaUsuarios', {
		templateUrl : 'ListaUsuarios.html',
		controller: 'usuarioController'
	});
	
	$routeProvider.when('/buscarusuario', {
		templateUrl : 'BuscarUsuario.html',
		controller: 'busquedausuario'
	});
	
	$routeProvider.when('/guardarusuario', {
		templateUrl : 'BuscarUsuario.html',
		controller: 'guardarusuario'
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

