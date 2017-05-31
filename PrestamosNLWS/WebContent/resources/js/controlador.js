/**
 * 
 */
var appNeurolab = angular.module('neurolab', ['ngRoute', 'ngCookies']);

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
	},
	
	//funcion que validad el estado del usuario logueado
	this.validarEstado = function(){	
		if (typeof($cookies.nombreUsuario) == 'undefined'|| $cookies.nombreUsuario == "" ) {
			$location.url("/");
			return false;
		}
		
	}
});

//para llamar los servicios de dispositivo
appNeurolab.service('dispositivos', function($http){	
	//listar	
	this.listaDispositivos = function(){
		return $http({
			url:'http://localhost:8080/PrestamosNLWS/neurolab/Dispositivo/listar',
			method:'GET'
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
				console.log($scope.nombreUsuario);
				$location.url('/inicio');
			},
			function failure(data){
				alert(data.data);
			}
		)
	}
});

//Controlador de index
appNeurolab.controller('inicio', function($scope, $location, $cookies){
	
	$scope.nombreUsuario = $cookies.nombreUsuario;
	
	$scope.listarDispositivos = function(){
		$location.url('/listaDispositivos');
	}

});

//controlador para lista de dispositivos
appNeurolab.controller('listaDispositivos', function($scope, $location, dispositivos, usuarios){
	
		
		dispositivos.listaDispositivos().then(
		function success(data){
			alert(data.data);

			alert(data.data.dispositivoJersey);
			$scope.listaDispositivos = data.data.dispositivoJersey;	
		});
	
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
	
}]);

//Funcionalidad para que capture eventos
appNeurolab.run(function($rootScope, usuarios){
	$rootScope.$on ('$routeChangeStart', function(){
		usuarios.validarEstado();
	})
});
