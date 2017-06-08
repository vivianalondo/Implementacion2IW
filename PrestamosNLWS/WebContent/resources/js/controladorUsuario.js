/**
 * Clase para implementar los controladores y rutas de los usuarios
 * @author: Viviana Londoño
 * @version: 1.0
 */
var moduloUsuarios = angular.module('modUsuarios', ['ngRoute', 'ngCookies', 'neurolab']);


moduloUsuarios.service('usuariosServices', function($http, $cookies, $location){
	
	/**
	 * Función para hacer uso del servicio web listar usuarios
	 */
	this.listaUsuarios = function(){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Usuario/listar',
			method:'GET'
		});
	}
	
	/**
	 * Función para hacer uso del servicio web eliminar usuarios
	 */	
	this.eliminar = function(idUsuario){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Usuario/Eliminar',
			method:'GET',
			params:{
				identificacion: idUsuario
			}
		});
	}
	
	
	/**
	 * Función para hacer uso del servicio web buscar usuarios
	 */
	this.buscar = function(id){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Usuario/obtener',
			method:'GET',
			params:{
				identificacion : id	
			}
		});
	}
	
	
	/**
	 * Función para hacer uso del servicio web guardar usuarios
	 */
	this.guardar = function(user){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Usuario/registrar',
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

	/**
	 * Función para hacer uso del servicio web modificar usuarios
	 */
	this.modificar = function(user){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Usuario/modificar',
			method:'POST',
			params:{
						identificacion:user.identificacion,
						tipoDocumento:user.tipoDocumento,
						nombre:user.nombre,
						apellido:user.apellido,
						telefono:user.telefono,
						email:user.email,
						login:user.login,
						estadoUsuario: user.estadoUsuario,
						rol:user.rol
			}
		});
	}
	

	
	
});

/**
 * Controlador de usuarios
 */
moduloUsuarios.controller('usuarioController', function($scope, $rootScope, $location, $cookies, usuariosServices, productService, auth){
	
	$scope.nombreUsuario = $cookies.nombreUsuario;
	
	/**
	 * Función para controlar el listar usuario
	 */
	usuariosServices.listaUsuarios().then(
	function success(data){
		$scope.listaUsuarios = data.data.usuarioJersey;	
	});
	
	/**
	 * Función para controlar el buscar usuario
	 */
	$scope.buscarusuario = function(identificacion){
		productService.addProduct(identificacion);
		$location.url('/buscarusuario');
	}

	/**
	 * Función para controlar el eliminar usuario
	 */
	$scope.eliminar = function(usuario) {
		
		usuariosServices.eliminar(usuario.identificacion).then(
			function success(data){
				alert('Se ha eliminado el usuario');
				usuariosServices.listaUsuarios().then(
						function success(data){
							$scope.listaUsuarios = data.data.usuarioJersey;	
						});
			});
	};
	
	

	/**
	 * Función para controlar el editar usuario
	 */
	$scope.editar = function(usuarioM){			
		$rootScope.usuarioModificar = usuarioM;
		$location.url('/editarUsuario');
	};
	
	/**
	 * Función para controlar el modificar usuario
	 */
	$scope.modificaruser = function(usuarioMod) {				
		usuariosServices.modificar(usuarioMod).then(
			function success(data){
				alert('Se ha modificado el usuario');
				$location.url('/listaUsuarios')
			});
	};
	
	/**
	 * Función para controlar el modificar usuario
	 */
	$scope.modificaruserB = function(usuarioMod) {				
		usuariosServices.modificar(usuarioMod).then(
			function success(data){
				alert('Se ha modificado el usuario');
				$location.url('/buscarusuario')
			});
	};
	
		
	/**
	 * Función para controlar el cambio a la vista guardar usuario
	 */
	$scope.saveuser = function(){
		$location.url('/guardarusuario');
	}
	
	/**
	 * Función para controlar el cambio a la vista listar usuarios
	 */
	$scope.backListU = function(){
		$location.url('/listaUsuarios');
	}
	
	/**
	 * Función para controlar el cambio a la vista inicio
	 */
	$scope.back = function(){
		$location.url('/inicio');
	}
	
	$scope.listarDispositivos = function(){
		$location.url('/listaDispositivos');
	}
	
	//Ir al inicio
	$scope.home = function(){
		$location.url('/inicio');
	}
	
	//Ir a la lista de usuarios
	$scope.listarUsuarios = function(){
		$location.url('/listaUsuarios');
	}
	
	//Ir a la lista de reservas
	$scope.listarReservas = function(){
		$location.url('/listaReservas');
	}

	//Ir a la lista de roles
	$scope.listarRoles = function(){
		$location.url('/listaRoles');
	}

	//Cerrar sesión
	$scope.logout = function(){
		auth.logout();
	}
});


/**
 * Controlador para la búsqueda de usuarios
 */
moduloUsuarios.controller('busquedausuario', function($scope, $rootScope, $location, usuariosServices,productService){
	
	$scope.products = productService.getProducts();

	usuariosServices.buscar($scope.products).then(
		function success(data){		
			$scope.listaUsuariosResultante = data.data;
		});
	
	/**
	 * Función para eliminar dispositivo desde la vista de búsqueda
	 */
	$scope.eliminarB = function(usuario) {
		usuariosServices.eliminar(usuario.identificacion).then(
			function success(data){
				alert('El usuario ha sido eliminado correctamente');
				usuariosServices.buscar(usuario.identificacion).then(
						function success(data){	
							$scope.listaUsuariosResultante = data.data;
						});	
			});
	};
	
	/**
	 * Función para editar dispositivo desde la vista de búsqueda
	 */
	$scope.editarB = function(usuarioMo){
		$rootScope.banderaU = 1;
		$rootScope.usuarioModificar2 = usuarioMo;
		$location.url('/editarUsuario');
	};
	
	

	
	/**
	 * Función para cambiar a la vista listar usuarios
	 */
	$scope.backListU = function(){
		$location.url('/listaUsuarios');
	}
});

/**
 * Controlador para guardar usuario
 */
moduloUsuarios.controller('guardarusuario', function($scope, $location, $cookies, usuariosServices){
	
	//Variable para guardar el nombre de usuario logueado
	$scope.nombreUsuario = $cookies.nombreUsuario;
	
	/**
	 * Función para guardar usuario
	 */
	$scope.guardaruser = function() {
		usuariosServices.guardar($scope.usuario).then(
			function success(data){
				alert('Se ha guardado el usuario');
					$location.url('/listaUsuarios')
			});
	};
	
	/**
	 * Función para controlar el cambio a la vista listar usuarios
	 */
	$scope.backListD = function(){
		$location.url('/listaUsuarios');
	}
});




/**
 * Definición de rutas para el usuario
 */
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
		templateUrl : 'GuardarUsuario.html',
		controller: 'guardarusuario'
	});
	
	$routeProvider.when('/editarUsuario', {
		templateUrl : 'EditarUsuario.html',
		controller: 'usuarioController'
	});

	
}]);

