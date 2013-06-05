<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.srluren.web.services.impl.SubProductoServicesImpl,java.util.*"%>
<%@ page import="org.srluren.web.services.impl.ProductoServicesImpl"%>
<%@ page import="org.srluren.web.beans.hb.SubProducto"%>
<%@ page import="org.srluren.web.beans.hb.Producto"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Caja Señor de Luren. Préstamos para la Micro y Pequeña
	Empresa de todos los sectores productivos, préstamos personales y de
	consumo.</title>
<link rel="stylesheet" href="resources/ui/themes/jquery.ui.all.css">
<script src="resources/ui/js/jquery-1.8.0.js"></script>

<!-- JQuery Validate -->
<link rel="stylesheet" type="text/css" href="css/SyntaxHighlighter.css" />
<script type="text/javascript" src="js/styletable.jquery.plugin.js"></script>

<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.validate.wrapper.js" type="text/javascript"></script>
<script src="js/validCampoFranz.js" type="text/javascript"></script>

<script src="js/messages_es.js" type="text/javascript"></script>
<script src="js/additional-methods.js" type="text/javascript"></script>
<script src="resources/ui/js/jquery.ui.core.js"></script>
<script src="resources/ui/js/jquery.ui.widget.js"></script>
<script src="resources/ui/js/jquery.ui.button.js"></script>
<script src="resources/ui/js/jquery.ui.position.js"></script>
<script src="resources/ui/js/jquery.ui.dialog.js"></script>
<script src="resources/ui/js/jquery.ui.datepicker.js"></script>
<script src="resources/ui/js/json2.js"></script>
<script src="js/jquery.geturlparam.js"></script>
<script src="Scripts/javascripts.js" type="text/javascript"></script>
<script src="Scripts/javascripts2.js" type="text/javascript"></script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<link href="css/styles.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/pro_dropdown_3.css" />

<style>
body {
	font-size: 78%;
}

select,.ui-select-menu {
	float: left;
	margin-right: 10px;
}

label,input,button,img{
	display: block;
	color: #039;
	font-family: Arial, Helvetica, sans-serif;
}

input.text {
	margin-bottom: 10px;
	padding: .4em;
	color: #039;
}


input.error { 
	border: 1px solid red;
	float:bottom;
}

label.error {
	color: red;
	float:left;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.ui-datepicker-trigger { 
	position: relative; 
	top: -24px;
	left: 220px;
}

#lista {
	width:150px;
}
	
</style>


<!--[if lte IE 9]>
	<style type="text/css">
		#lista { 
			width:210px;
		}
	
	</style>
<![endif]-->


<script>
	$(document).ready(function(){
	
		//Deshabilita campos no editables
		$('#producto').attr("disabled", true);
		$('#subProducto').attr("disabled", true);
			
		//Define restricciones de ingreso de datos
		$('#nombreApellido').validCampoFranz(' abcdefghijklmnñopqrstuvwxyzáéiou');
		$('#correo').validCampoFranz('abcdefghijklmnñopqrstuvwxyzáéiou0123456789@_-.'); 
		$('#telefono').validCampoFranz('0123456789'); 
	
		//Definiendo reglas y mensajes para la validación
		var reglas = {  
			 nombreApellido: {required:true, maxlength:80},  
			 correo: 		 {required:true, email:true,  maxlength:80},  
			 telefono: 		 {digits:true,   maxlength:25}
		}; 
		
		var mensajes = {  
			nombreApellido: {required:"Nombres y apellido es requerido"},
			correo:			{required:"Correo es requerido"},  
			telefono:		{required:"Teléfono es requerido", digits:"Teléfono inválido, solo debe ingresar dígitos"} 
		}; 

		//Validacion campos entrada de formulario
		$('#formulario').validate({
                rules: reglas,
				messages:mensajes  
		});

		//Envia datos del cliente al servidor de correos
		$("#btnEnviarCorreo").click(function(event){
			
		     // Obtiene validador
	        var validator    = new jQueryValidatorWrapper("formulario", reglas, mensajes);
			
            if (!validator.validate())
                return false;
			
			//objeto a enviar
			var object = {};
			
			//Arma datos a enviar
			object.nombreApellido =$("#nombreApellido").val();
			object.correo =$("#correo").val();
			object.telefono = $("#telefono").val();
 
			//Convierte a formato JSON
			var json = JSON.stringify(object);
			
			//Envia correo al servidor
			$.ajax({
				type: 'post',
				url : './enviarCorreoServlet?jsonRequest='+ json,
				beforeSend: function () {
					$("#panel-proceso").html('<label><img src="imagenes/calculadora/carga.gif"/>Enviando correo...</label>');
				},
				success : function(mensaje_respuesta) {
									
					//Muestra mensaje de notificación
					$("#panel-proceso").html('');
					alert(mensaje_respuesta);
					
					//Limpia valores de formulario
					$('#nombreApellido').val("");
					$('#correo').val("");
					$('#telefono').val("");
					
					window.history.back(-1);
					return false;
					
				},
				error : function(mensaje_respuesta) {
					$("#panel-proceso").html('');
					alert(mensaje_error);
					parent.history.back();
					return false;
				}
			});
		});
		
		//Boton "Regresar"
		$("#btnRegresar").click(function(event){
			parent.history.back();
			return false;
		});
		
		
		
	});
</script>

</head>

<body>
	<div align="center">
	
		<!-- Inicio Cabecera -->
		<table width="1000" border="0" cellspacing="0" cellpadding="0" background="imagenes/fondo_cabecera.jpg">
			<tr>
				<td width="14" rowspan="2"></td>
				<td width="480" height="114" align="center"><a
					href="index.htm"><img src="imagenes/fantasma.gif"
						alt="ir a la página principal" width="460" height="90" border="0" /></a></td>
				<td rowspan="2" align="right" valign="top"><table border="0"
						cellspacing="0" cellpadding="0">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="textb"><a href="index.htm" class="textb">Inicio</a><span
								class="text">&nbsp;|&nbsp;</span><a
								href="c-transparencia-04.htm" class="textb">Preguntas
									frecuentes</a><span class="text">&nbsp;|&nbsp;</span><a
								href="c-agencias.htm" class="textb">Red de agencias</a><span
								class="text">&nbsp;|&nbsp;</span><a href="mapasitio.htm"
								class="textb">Mapa web</a><span class="text">&nbsp;|&nbsp;</span><a
								href="http://mail.cajaluren.com.pe" class="textb">Correo web</a></td>
							<td width="22"></td>
						</tr>
					</table> <br />
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="10"></td>
							<td></td>
						</tr>
						<tr>
							<td align="right" class="text_9c"><div align="right">
									<script type="text/javascript">fecha();</script>
									<span class="text">&nbsp;|&nbsp;</span>
									<script type="text/javascript">hora();</script>
								</div></td>
							<td width="22"></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="5"></td>
			</tr>
		</table>
		<!-- Fin de Cabecera -->

		<!-- Inicio Menú de Opciones -->
		<table width="1000" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="imagenes/div_menu_01.gif" width="34" height="30" /></td>
				<td><a href="ahorros.htm" target="_top"><img
						src="imagenes/bt_menu_01_ahorros.gif" width="82" height="30"
						border="0" /></a></td>
				<td><img src="imagenes/div_menu_02.gif" width="26" height="30" /></td>
				<td><a href="prestamos.htm" target="_top"><img
						src="imagenes/bt_menu_02_prestamos.gif" width="82" height="30"
						border="0" /></a></td>
				<td><img src="imagenes/div_menu_02.gif" width="26" height="30" /></td>
				<td><a href="servicios.htm"><img
						src="imagenes/bt_menu_03_servicios.gif" width="86" height="30"
						border="0" /></a></td>
				<td><a href="conocenos.htm"><img src="imagenes/bt_menu_04_conocenos.gif" width="92" height="30" border="0" /></a></td>
				<td><img src="imagenes/div_menu_02.gif" width="26" height="30" /></td>
				<td><a href="tarjeta.htm"><img
						src="imagenes/bt_menu_05_tarjeta.gif" width="131" height="30"
						border="0" /></a></td>
				<td><img src="imagenes/div_menu_02.gif" width="26" height="30" /></td>
				<td><a href="calculadora.htm"><img
						src="imagenes/calculadora/calculadora_menu.gif" width="131" height="30"
						border="0" /></a></td>
				<td width="551" align="left"><img
					src="imagenes/div_menu_03.gif" width="136" height="30" /></td>
			</tr>
		</table>
		<!--Fin Menú de Opciones -->

		<!-- Inicio Seccion Cuerpo -->
		<table width="1000" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<!-- Seccion Izquierda. Principal-->
				<td width="796" valign="top">
				
					<!-- Borde de Seccion Principal-->
					<table width="796" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="15"><img
									src="imagenes/calculadora/top_left_contenido.gif" width="15"
									height="8" /></td>
								<td width="781" height="8" bgcolor="#99CCCC"></td>
							</tr>
					</table>
					
					<!-- Fondo de Seccion Principal-->
					<table width="796" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="6"></td>
							<td width="8" align="center" bgcolor="#99CCCC">&nbsp;</td>
							
							<!-- Inicio Opciones de Seccion Principal-->
							<td bgcolor="#99cc00">
								<table width="774" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#f0f1f1">
									<tr>
										<td width="10" height="10"></td>
										<td></td>
										<td width="10"></td>
									</tr>
									<tr>
										<td></td>
										<td>
											<table width="754" border="0" cellpadding="0"cellspacing="0" bgcolor="#FFFFFF">
												<tr>
													<td height="300" align="center" valign="top">
														<!-- Imagen de Seccion Principal-->
														<table width="750" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td height="1"></td>
															</tr>
															<tr>
																<td><img
																	src="imagenes/calculadora/banner_central.png" width="750"
																	height="184" /></td>
															</tr>
															<tr>
																<td height="1"></td>
															</tr>
														</table>
														
														<!-- Menu de Seccion Principal: Aqui se incluiran los cambios-->
														<table width="750" border="0" cellspacing="0" cellpadding="0" style="margin-left:25px;">
															<tr>
																<td>
																	<div style="margin-top:40px;color:#50b5d0;font-family:Arial,Helvetica,sans-serif;font-size:16px;float:left"><b>Solicitud de Visita</b></div><br>
																	<div style="margin-top:10px;width:725px;border:1px solid #039;float:left"></div>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												
												<tr>
													<td>
														<!--Seccion con panel de datos para la solicitud de visita-->																
															<div id="panel-datos" style="margin-left:25px;">	
																<form method="post" id="formulario" name="formulario">
																
																	<!--Tabla con panel de datos para la solicitud de visita-->
																	<!-- <table width="750" border="0" cellspacing="0" cellpadding="0"> -->
																	<table>
																		<tr style="float:left;margin-left:1px;">
																			<td id="lista"><label for="producto" style="float:left;"><b>Producto :<br> </label></td>
																			<td><input type="text" name="producto" id="producto" size="60" class="text ui-widget-content" value="<%=request.getSession().getAttribute("nombreProducto")%>" style="margin-bottom:5px;border:none"/></td>
																		</tr>
																	</table>
																	
																	<table>
																		<tr style="float:left;margin-left:1px;">
																			<td id="lista"><label for="subProducto" style="float:left;"><b>Sub-Producto :<br> </label></td>
																			<td><input type="text" name="subProducto" id="subProducto" size="60" class="text ui-widget-content" value="<%=request.getSession().getAttribute("nombreSubProducto")%>" style="margin-bottom:5px;border:none"/></td>
																		</tr>
																	</table>
																	
																	<table>
																		<tr style="float:left;margin-left:1px;">
																			<td id="lista"><label for="nombreApellido" style="float:left;"><b>Nombre y Apellidos :<br></label></td>
																			<td><input type="text" name="nombreApellido" id="nombreApellido" size="60" maxlength="80" class="requeried nombreApellido text ui-widget-content" style="margin-bottom:5px;"/></td>
																			<td ><label style="float:left;color:red;"><b>*</label></td>
																		</tr>
																	</table>	
																	
																	<table>
																		<tr style="float:left;margin-left:1px;">
																			<td id="lista"><label for="correo" style="float:left;"><b>Correo :<br></label></td>
																			<td><input type="text" name="correo" id="correo" size="30" maxlength="80"  class="requeried correo text ui-widget-content" style="margin-bottom:5px;"/></td>
																			<td ><label style="float:left;color:red;"><b>*</label></td>
																		</tr>
																	</table>
																	
																	<table>	
																		<tr style="float:left;margin-left:1px;">
																			<td id="lista"><label for="telefono" style="float:left;"><b>Teléfono :<br></label></td>
																			<td><input type="text" name="telefono" id="telefono" size="30" maxlength="25" class="text ui-widget-content" style="margin-bottom:5px;"/></td>
																			<td></td>
																		</tr>
																	
																	</table>
																	<table width="750" border="0" cellspacing="0" cellpadding="0">	
																		<tr>
																			<td></td>
																			<td width="10">

																			</td>
																		</tr>
																	</table>
																	
																	
																	<table>
																		<tr>
																			<td width="700">
																				<div id="panel-proceso" style="width:200px; margin-bottom:20px;margin-top:20px;">
																				</div>
																			</td>
																			<td>
																				<div id="panel-botones" style="width:200px; margin-bottom:20px;margin-top:20px;">
																					<a id="btnEnviarCorreo" href="#" style="width:100px;float:left;"><img src="imagenes/calculadora/enviar_a.jpg" border="0" width="95" onmouseover="this.src='imagenes/calculadora/enviar_b.jpg'" onmouseout="this.src='imagenes/calculadora/enviar_a.jpg'"/></a>
																					<a id="btnRegresar" href="#" style="width:100px;float:right;"><img src="imagenes/calculadora/regresar_a.jpg" border="0"  width="95" onmouseover="this.src='imagenes/calculadora/regresar_b.jpg'" onmouseout="this.src='imagenes/calculadora/regresar_a.jpg'"/></a>
																				</div>	
																			</td>
																			<td></td>
																		</tr>
																	
																	</table>
																	
																	

																</form>
															</div>

													</td>
													
													
													<td height="100">
													</td>
												</tr>
												
												<tr>
											</table>
										</td>
										<td></td>
									</tr>
									<tr>
										<td height="10"></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</td>
							<!-- Fin de Opciones de Seccion Principal-->
							
							<td width="8" bgcolor="#99CCCC"></td>
						</tr>
					</table>
				</td>
				
				<!-- Seccion Derecha. Opciones adicionales, mision , vision-->
				<td align="left" valign="top" background="imagenes/fondo_der_cont2.gif">
					<table width="203" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="8"></td>
							<td width="177">
								
								<!-- Imagen: Nueva Tarjeta Señor de Luren -->
								<table width="177" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="8"></td>
									</tr>
									<tr>
										<td align="center" valign="middle" background="imagenes/top_der.gif">
											<table width="177"
												border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td height="10"></td>
												</tr>
												<tr>
													<td><a href="tarjeta.htm"><img
															src="imagenes/b-lurencash.jpg" alt="mas información"
															width="177" height="94" border="0" /></a></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								
								<!-- Opciones adicionales : Conocenos mas, Transparencia, Tarifario-->
								<table width="177" border="0" cellpadding="0" cellspacing="0" background="imagenes/fondo_der.gif" class="text">
									<tr>
										<td width="8" height="20"></td>
										<td width="6"><img src="imagenes/vineta.gif" width="6"
											height="8" /></td>
										<td width="6"></td>
										<td><a href="conocenos.htm" target="_top" class="text">Conocenos
												más</a></td>
									</tr>
									<tr>
										<td height="1" colspan="4" bgcolor="#e9e7e2"></td>
									</tr>
									<tr>
										<td height="20"></td>
										<td><img src="imagenes/vineta.gif" width="6" height="8" /></td>
										<td></td>
										<td><a href="c-transparencia.htm" target="_top"
											class="text">Transparencia</a></td>
									</tr>
									<tr>
										<td height="1" colspan="4" bgcolor="#e9e7e2"></td>
									</tr>
									<tr>
										<td height="20"></td>
										<td><img src="imagenes/vineta.gif" width="6" height="8" /></td>
										<td></td>
										<td><a href="c-transparencia-02.htm" target="_top"
											class="text">Tarifario</a></td>
									</tr>
									<tr>
										<td height="15" bgcolor="#FFFFFF"></td>
										<td bgcolor="#FFFFFF"></td>
										<td bgcolor="#FFFFFF"></td>
										<td bgcolor="#FFFFFF"></td>
									</tr>
								</table>
								
								<!-- Inicio Marco: Mision- Vision -->
								<table width="177" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td background="imagenes/marco_der3.gif"><table
												width="177" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="9"></td>
													<td height="26" align="left" valign="bottom"><img
														src="imagenes/txt-mision.gif" width="40" height="16" /></td>
												</tr>
											</table></td>
									</tr>
								</table>
								
								<!-- Inicio Mision Vision -->
								<table width="177" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td background="imagenes/marco_der.gif"><table
												width="177" border="0" cellpadding="0" cellspacing="0"
												style="background-image: url(imagenes/fondo_der.gif); background-position: top; background-repeat: repeat-x;">
												<tr>
													<td width="9" height="6"></td>
													<td></td>
												</tr>
												<tr>
													<td></td>
													<td align="left" valign="bottom" class="text_i"
														style="padding-right: 2px;"><span class="text_i"
														style="padding-right: 2px;"><span class="text_i"
															style="padding-right: 2px;"><span class="text_i"
																style="padding-right: 2px;">&quot;Contamos con un
																	Equipo de Profesionales dedicados a Desarrollar
																	Productos Financieros adaptados a los requerimientos de
																	nuestros clientes.&quot; </span></span></span></td>
												</tr>
												<tr>
													<td></td>
													<td height="16"></td>
												</tr>
											</table>
											<table width="177" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="9"></td>
													<td height="26" align="left" valign="bottom"><img
														src="imagenes/txt-vision.gif" width="37" height="16" /></td>
												</tr>
											</table>
											<table width="177" border="0" cellpadding="0" cellspacing="0"
												background="imagenes/fondo_der.gif">
												<tr>
													<td width="9" height="6"></td>
													<td></td>
												</tr>
												<tr>
													<td></td>
													<td align="left" valign="bottom" class="text_i"
														style="padding-right: 2px;"><span class="text_i"
														style="padding-right: 2px;"><span class="text_i"
															style="padding-right: 2px;">&quot;Ser reconocidos
																como una entidad Financiera Confiable, Rentable y
																Comprometida con sus clientes.&quot;</span></span></td>
												</tr>
												<tr>
													<td height="12"></td>
													<td></td>
												</tr>
											</table></td>
									</tr>
								</table>
								<!--Fin Mision Vision -->

								<!-- Inicio Banner Transparencia -->
								<table width="177" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="54" align="center" valign="bottom"><a
											href="c-transparencia_new.htm" target="_blank"><img
												src="imagenes/banner_transparencia.gif" width="156"
												height="34" border="0" /></a><br /></td>
									</tr>
								</table></td>
								
							<td width="18"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- Fin Seccion Cuerpo -->

		<!-- Fondo Pie Pagina -->
		<table width="1000" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="imagenes/calculadora/pie_left_contenido2.gif"
					width="15" height="29" /></td>
				<td width="781" background="imagenes/calculadora/div2.gif"></td>
				<td width="204" height="29"
					background="imagenes/pie_rig_contenido.gif">&nbsp;</td>
			</tr>
		</table>
		
		<!-- Pie Pagina -->
		<table width="1000" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="15" align="left">&nbsp;</td>
				<td width="35" align="left"><img src="imagenes/logo_pie.gif"
					width="35" height="30" /></td>
				<td align="left" class="text_9b">© Copyright 2008. Caja “Señor
					de Luren”. Todos los derechos reservados. Central telefónica: <span
					class="text_9">056-581400 </span>Anexo 123 | Fax:<span
					class="text_9"> 056-223492</span> | Email: <a
					href="mailto:cajaluren@cajaluren.com.pe" class="text_9">cajaluren@cajaluren.com.pe</a>
				</td>
			</tr>
		</table>
		
		<br /> <br />
	</div>

</body>
</html>