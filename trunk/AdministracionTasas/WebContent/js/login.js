$(document).ready(function(){

	//Inicio
	//$('#mensaje_login').hide();
	//$('#ajax_loading').hide();
	
	$( "#btnIngresar").button().click(function( event ) {
						validar();
	});

});

//Valida usuario y clave de usuario
function validar(){
	
	//Variables
	var userName = $("#username").val(); // codigo usuario
	var userPass = $("#password").val(); //clave
	
	//Validar usuario
	$.ajax({
		type: "post", 
		url: "./validarUsuarioServlet",
		beforeSend: function () {
			$("#mensaje_login").html('<label><img src="imagenes/carga.gif"/>  Validando datos con el servidor...</label>');
		},
		data: "username="+userName + "&password="+userPass,
		success: function(msj) {

					 if(msj == 1) {//Datos correctos
						window.location.href = './menuPrincipalServlet';
						//redireccionarPrincipal();
					 }else{	//Datos incorrectos
						var mensaje_login = msj;
						$('#mensaje_login').show();
						$('#mensaje_login').html(mensaje_login);
					 }	
		}
	});
			
};