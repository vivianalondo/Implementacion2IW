/**
 * 
 */
var appNeurolab = angular.module('neurolab', ['ngRoute', 'ngCookies', 'modUsuarios', 'modReservas']);

//factoria que controla la autentificación, devuelve un objeto
//$cookies para crear cookies
//$cookieStore para actualizar o eliminar
//$location para cargar otras rutas
appNeurolab.factory('auth', function($cookies,$cookieStore,$location)
{
  return{
      
	//funcion que validad el estado del usuario logueado
	validarEstado: function(){	
		if (typeof($cookies.nombreUsuario) == 'undefined'|| $cookies.nombreUsuario == "" ) {
			$location.url("/");
			return false;
		}		
	},

	//Función para cerrar sesión
    logout : function()
      {
          //al hacer logout eliminamos la cookie con $cookieStore.remove
          $cookieStore.remove("nombreUsuario"),
          //mandamos al login
          $location.path("/");
      },
      
  }
});


//Conjunto de servicios web de usuarios
appNeurolab.service('usuarios', function($http, $cookies, $location){
	//funcion que hace uso del servicio web autenticar de usuario
	this.autenticar = function(usuario, passwd){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Usuario/autenticar',
			method:'GET',
			params: {
		login: usuario,
		pass: passwd
			}
		});
	}
});

appNeurolab.service('productService', function() {
	  var productList = [];

	  var addProduct = function(newObj) {
	      productList.push(newObj);
	  };

	  var getProducts = function(){
	      return productList;
	  };

	  return {
	    addProduct: addProduct,
	    getProducts: getProducts
	  };

	});

//para llamar los servicios de dispositivo
appNeurolab.service('dispositivos', function($http){	
	//listar	
	this.listaDispositivos = function(){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Dispositivo/listar',
			method:'GET'
		});
	},
	
	//eliminar	
	this.eliminar = function(idD){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Dispositivo/eliminar',
			method:'POST',
			params:{
				idDispositivo: idD
			}
		});
	}
	
	//guardar
	this.guardar = function(dis){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Dispositivo/guardar',
			method:'POST',
			params:{
				nombre: dis.nombre,
				estado: dis.estadoDispositivo,
				descripcion : dis.descripcion
			}
		});
	}

	//buscar
	this.buscar = function(nombre){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Dispositivo/listarPorNombre',
			method:'GET',
			params:{
				nombreDispositivo : nombre	
			}
		});
	}
	
	
	this.modificar = function(dispositivo){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Dispositivo/modificar',
			method:'POST',
			params:{
				idDispositivo: dispositivo.idDispositivo,
				nombre: dispositivo.nombre,
				estado: dispositivo.estadoDispositivo,
				descripcion: dispositivo.descripcion			}
		});
	}

	
});

//coontrolador para el logueo del usuario
appNeurolab.controller('Login', function($scope, $location, $cookies, usuarios){
	
	$scope.nombreUsuario = '';
	$scope.passwd = '';
	$scope.login = function(){
		usuarios.autenticar($scope.nombreUsuario,
				$scope.passwd).then(
			function success(data){
				if (data.data != ''){
					alert(data.data);
					$scope.nombreUsuario = '';
					$scope.passwd='';
					return;
				}
				
				$cookies.nombreUsuario = $scope.nombreUsuario;//Guardar nombre de usuario en la cookie. Debo inyectar el $cookies
	//			console.log($scope.nombreUsuario);
				$location.url('/inicio');
			},
			function failure(data){
				alert(data.data);
			}
		)
	}
});

//Controlador de index
appNeurolab.controller('inicio', function($scope, $location, $cookies, auth){
	
	$scope.nombreUsuario = $cookies.nombreUsuario;
	console.log($cookies.nombreUsuario);
	console.log($scope.nombreUsuario);
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
	
	//Cerrar sesión
	$scope.logout = function(){
		auth.logout();
	}

});

//Controlador para guardar nuevo dispositivo
appNeurolab.controller('guardardispositivo', function($scope, $location, $cookies, dispositivos){
	
	$scope.nombreUsuario = $cookies.nombreUsuario;
	$scope.guardar = function() {
		alert('acá en guardar');
		dispositivos.guardar($scope.dispositivo).then(
			function success(data){
				alert('Se ha guardado el dispositivo');
				$scope.refrescar = function(){
					$location.url('/listaDispositivos')
				};
				
			});
	};
	
	//Función que me lleva a la lista dispositivos
	$scope.backListD = function(){
		$location.url('/listaDispositivos');
	}
});


//controlador para lista de dispositivos
appNeurolab.controller('listaDispositivos', function($scope, $location,$rootScope, dispositivos, $cookies, usuarios, productService){
	
	$scope.nombreUsuario = $cookies.nombreUsuario;
	
	//Lista dispositivos
	dispositivos.listaDispositivos().then(
	function success(data){
		//alert(data.data.dispositivoJersey);
		$scope.listaDispositivos = data.data.dispositivoJersey;	
	});
	

	//Función para eliminar dispositivo
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
	
	//Funcón que me lleva al guardar
	$scope.save = function(){
		$location.url('/guardardispositivo');
	}
	
	//$scope.listaDispositivosResultante
	$scope.buscardispositivo = function(nombre){
		
		productService.addProduct(nombre);
		$location.url('/buscardispositivo');
	}
	
	

	//Funciones para editar
	$scope.editar = function(dispositivoM){			
		$rootScope.dispositivoModificar = dispositivoM;
		$location.url('/editarDispositivo');
	};
	
	//Función para modificar
	$scope.modificar = function(dispositivoMod) {				
		dispositivos.modificar(dispositivoMod).then(
			function success(data){
				alert('Se ha modificado el dispositivo');
				$location.url('/listaDispositivos')
			});
	};
	
	
	//Función que me lleva al inicio
	$scope.back = function(){
		$location.url('/inicio');
	}
	
	//Función que me lleva a la lista dispositivos
	$scope.backListD = function(){
		$location.url('/listaDispositivos');
	}
});


//controlador para eliminar de dispositivos
appNeurolab.controller('busqueda', function($scope, $location, dispositivos,productService){
	
	$scope.products = productService.getProducts();
	
	dispositivos.buscar($scope.products).then(
		function success(data){				
			$scope.listaDispositivosResultante = data.data.dispositivoJersey;
		});	
	
	//Función que me lleva a la lista dispositivos
	$scope.backListD = function(){
		$location.url('/listaDispositivos');
	}
});


//Definir las rutas
appNeurolab.config(['$routeProvider', function($routeProvider){
	
	$routeProvider.when('/inicio', {
		templateUrl : 'Inicio.html',
		controller: 'inicio'
	});
	
	$routeProvider.when('/', {
		templateUrl : 'Login.html',
		controller: 'Login'
	})	;
	
	$routeProvider.when('/listaDispositivos', {
		templateUrl : 'ListaDispositivos.html',
		controller: 'listaDispositivos'
	});
	
	
	$routeProvider.when('/guardardispositivo', {
		templateUrl : 'GuardarDispositivos.html',
		controller: 'guardardispositivo'
	});
	
	//Ruta editar
	$routeProvider.when('/editarDispositivo', {
		templateUrl : 'EditarDispositivo.html',
		controller: 'listaDispositivos'
	});
	
	$routeProvider.when('/buscardispositivo', {
		templateUrl : 'Buscardispositivo.html',
		controller: 'busqueda'
	});
	
	
}]);

//Funcionalidad para que capture eventos
appNeurolab.run(function($rootScope, auth){
	$rootScope.$on ('$routeChangeStart', function(){
		auth.validarEstado();
	})
});
