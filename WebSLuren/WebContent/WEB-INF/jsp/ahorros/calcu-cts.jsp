<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<script src="js/messages_es.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.validate.wrapper.js" type="text/javascript"></script>
<script src="js/validCampoFranz.js" type="text/javascript"></script>
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
<script src="js/stuHover.js" type="text/javascript"></script>

<style>
body {
	font-size: 78%;
}

select,.ui-select-menu {
	float: left;
	margin-right: 10px;
}

label,input{
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
	
</style>

<script>
	$(document).ready(function(){
		
		//Deshabilita campos no editables
		$('#subProducto').attr("disabled", true);
		
		
		//Define restricciones de ingreso de datos
		$('#importeDeposito').validCampoFranz('0123456789.');
		$('#plazoDeposito').validCampoFranz('0123456789');
		
		//Definiendo reglas y mensajes para la validación
		var reglas = {  
			 importeDeposito: {required:true, number:true, max: 999999999999},  
			 plazoDeposito: {required:true, number:true},
		}; 
		
		var mensajes = {  
			importeDeposito: {required:"Debe ingresar un importe para el depósito ", number:"Importe del depósito inválido, solo debe ingresar números"},  
			plazoDeposito:{required:"Debe ingresar un plazo para el depósito", number:"Plazo del depósito inválido, solo debe ingresar números"},  
		}; 
		
		
		//Validacion campos entrada de formulario
		$('#formulario').validate({
                rules: reglas,
				messages:mensajes  
		});
		
		//Envia datos para calcular cronograma de pagos
		$("#btnAhorro").click(function(){
			
			
		     // Obtiene validador
	        var validator    = new jQueryValidatorWrapper("formulario", reglas, mensajes);
			
            if (!validator.validate())
                return false;
		
			//objeto a enviar
			var object = {};
			
			//Arma datos a enviar
			object.subProducto =$("#subProducto").val();
			object.importeDeposito =$("#importeDeposito").val();
			object.moneda = $("#moneda").val();
			object.plazoDeposito =$("#plazoDeposito").val();
			
			//Convierte a formato JSON
			var json = JSON.stringify(object);

			//Envia informacion al servidor para generar el cronograma
			window.location.href =  './genSimCTSServlet?jsonRequest='+ json;	

		});
		
	});
</script>

</head>

<body>
	<div align="center">
	
		<%
			//Obtiene objeto de sesion
			HttpSession sesion_actual = request.getSession(true);
			SubProductoServicesImpl subProductoServicesImpl = new SubProductoServicesImpl();
			ProductoServicesImpl productoServicesImpl = new ProductoServicesImpl();
			
			//Obtiene nemonico SubProducto y Producto
			String nemSubProducto = request.getParameter("nemSubProducto");
			String nemProducto = request.getParameter("nemProducto");
			
			//Obtiene datos de subproducto y producto de BD
			SubProducto subProducto = subProductoServicesImpl.buscarSubProductoNemonico(nemSubProducto);
			Producto producto = productoServicesImpl.buscarProductoNemonico(nemProducto);
			
			//Guarda datos en sesisón
			sesion_actual.setAttribute("nemProducto", nemProducto);
			sesion_actual.setAttribute("nombreProducto", producto.getNombre());
			sesion_actual.setAttribute("nemSubProducto", nemSubProducto);
			sesion_actual.setAttribute("nombreSubProducto", subProducto.getNombre());
			sesion_actual.setAttribute("idSubProducto", subProducto.getIdSubProducto());
		
		%>
	
	
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
													<td height="400" align="center" valign="top">
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
																	<div align="left" style="margin-top:20px;color:#50b5d0;font-family:Arial,Helvetica,sans-serif;font-size:16px;"><b>Simulador de Créditos - Ahorros</b></div><br>
																	<div align="left" style="margin-top:5px; width:725px;border:1px solid #039"></div>
																	<div align="left" style="margin-top:10px;color:#039;font-family:Arial,Helvetica,sans-serif;">Seleccione el tipo de crédito que necesita</div><br>
																	<div id="opciones_productos">
																
																		<!--Menu de productos-->
																		<ul id="nav">
																			<!--Credito Microempresa-->
																			<li class="top"><a href="#" id="btnCreMicro" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_microempresa_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_microempresa_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_microempresa_a.jpg'"/></span></a>
																				<ul class="sub" style="margin-top:50px">
																					<li><a href="calcu-cremicro.htm?nemProducto=CREMICRO&nemSubProducto=ACTF">Activo Fijo</a></li>
																					<li><a href="calcu-cremicro.htm?nemProducto=CREMICRO&nemSubProducto=CAPT">Capital Trabajo</a></li>
																				</ul>
																			</li>
																			
																			<!--Credito Consumo-->
																			<li class="top"><a href="#" id="btnCreConsu" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_consumo_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_consumo_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_consumo_a.jpg'"/></span></a>
																				<ul class="sub" style="margin-top:50px">
																					<li><a href="calcu-crecons.htm?nemProducto=CRECONS&nemSubProducto=DIRE">Consumo Directo</a></li>
																					<li><a href="calcu-crecons.htm?nemProducto=CRECONS&nemSubProducto=DPLA">Consumo descuento por planilla</a></li>
																					<li><a href="calcu-crecons.htm?nemProducto=CRECONS&nemSubProducto=GAPF">Consumo con Garantía de PF</a></li>
																				</ul>										
																			</li>
																			
																			<!--Credito PYMG-->
																			<li class="top"><a href="#" id="btnCrePMYG" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_pmyg_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_pmyg_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_pmyg_a.jpg'"/></span></a>
																				<ul class="sub" style="margin-top:50px">
																					<li><a href="calcu-crepmyg.htm?nemProducto=CREPMYG&nemSubProducto=ACTF1">Activo Fijo</a></li>
																					<li><a href="calcu-crepmyg.htm?nemProducto=CREPMYG&nemSubProducto=CAPT1">Capital Trabajo</a></li>
																				</ul>											
																			</li>
																			
																			<!--Credito Agrícola-->
																			<li class="top"><a href="#" id="btnCreAgri" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_agricola_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_agricola_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_agricola_a.jpg'"/></span></a>
																				<ul class="sub" style="margin-top:50px">
																					<li><a href="calcu-creagri.htm?nemProducto=CRECAGRI&nemSubProducto=ACTF2">Activo Fijo</a></li>
																					<li><a href="calcu-creagri.htm?nemProducto=CRECAGRI&nemSubProducto=CAPT2">Capital Trabajo</a></li>
																				</ul>										
																			</li>
																			
																			<!--Credito Hipotecario-->
																			<li class="top"><a href="#" id="btnCreHipo" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_hipotecario_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_hipotecario_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_hipotecario_a.jpg'"/></span></a>
																				<ul class="sub" style="margin-top:50px">
																					<li><a href="calcu-crehipo-mivv.htm?nemProducto=CREHIPO&nemSubProducto=MIVV">Mi Vivienda</a></li>
																					<li><a href="calcu-crehipo-tepp.htm?nemProducto=CREHIPO&nemSubProducto=TEPP">Techo Propio</a></li>
																				</ul>										
																			</li>
																		
																			<!--Credito KIVA-->
																			<li class="top"><a href="#" id="btnCreKIVA" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_kiva_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_kiva_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_kiva_a.jpg'"/></span></a>
																				<ul class="sub">
																					<li><a href="calcu-crekiva.htm?nemProducto=CREKIVA&nemSubProducto=NOBA">Crédito para No Bancarizados</a></li>
																					<li><a href="calcu-crekiva.htm?nemProducto=CREKIVA&nemSubProducto=ESTD">Crédito para Estudiantes</a></li>
																					<li><a href="calcu-crekiva.htm?nemProducto=CREKIVA&nemSubProducto=AGSA">Crédito de Agua y Saneamiento</a></li>
																					<li><a href="calcu-crekiva.htm?nemProducto=CREKIVA&nemSubProducto=MICA">Crédito Micro Agrícola</a></li>
																				</ul>									
																			</li>
																			
																			<!--Ahorros-->
																			<li class="top"><a href="#" id="btnAhorros" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_ahorros_b.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_ahorros_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_ahorros_b.jpg'"/></span></a>
																				<ul class="sub" style="margin-top:50px">
																					<li><a href="calcu-ahorro.htm?nemProducto=AHOR&nemSubProducto=AHOCORR">Ahorros Corrientes</a></li>
																					<li><a href="calcu-ahorro-plazo.htm?nemProducto=AHOR&nemSubProducto=DEPLZO">Depósitos a Plazo</a></li>
																					<li><a href="calcu-ahorro.htm?nemProducto=AHOR&nemSubProducto=ORDPAGO">Órdenes de Pago</a></li>
																					<li><a href="calcu-cts.htm?nemProducto=AHOR&nemSubProducto=CTS">CTS</a></li>
																				</ul>										
																			</li>
																			
																		</ul>
																	</div>
																</td>
	
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
														</table>
													</td>
												</tr>
												
												<tr>
													<td>
														<!--Seccion con panel de datos para el calculo de credito-->																
															<div id="panel-datos" style="margin-left:25px;margin-top: 15px">	
																<form method="post" id="formulario" name="formulario">
																	<!--Tabla con panel de datos para el calculo de credito-->
																	<table width="750" border="0" cellspacing="0" cellpadding="0">

																		<tr>
																			<td><label for="subProducto" style="float:left;"><b>Sub-Producto :<br> </label></td>
																			<td><input type="text" name="subProducto" id="subProducto" size="30" class="text ui-widget-content" value="<%=request.getSession().getAttribute("nombreSubProducto")%>" style="margin-bottom:5px;border:none"/></td>
																			<td></td>
																		</tr>
																	
																		<tr>
																			<td width="200"><label for="importeDeposito" style="margin-top:5px;float:left;"><b>Importe a Depositar :<br></label></td>
																			<td><input type="text" name="importeDeposito" id="importeDeposito" size="30" maxlength="12" class="required importeDeposito text ui-widget-content"/></td>
																			<td></td>
																		</tr>
																		
																		<tr>
																			<td><label for="moneda" style="float:left;"><b>Moneda :<br>
																				</label></td>
																			<td align="left"><select name="moneda" id="moneda"
																				class="text ui-widget-content"
																				style="margin-bottom:5px;height: 25px; width: 210px; color: #039; font-family: Arial, Helvetica, sans-serif;">
																					<option value="1" selected="selected">Soles</option>
																					<option value="2" >Dólares</option>	
																			</select></td>
																			<td></td>
																		</tr>
																	
																		<tr>
																			<td><label for="plazoDeposito" style="float:left;"><b>Plazo Depósito(días): <br> </label></td>
																			<td><input type="text" name="plazoDeposito" id="plazoDeposito" size="30" maxlength="4" class="required plazoDeposito text ui-widget-content" style="margin-bottom:5px;"/></td>
																			<td></td>
																		</tr>

																		<tr height="10">
																			<td></td>
																			<td>
																				<div id="panel-botones"  style="margin-bottom:20px;margin-top:20px;float:left;">
																					<a id="btnAhorro" href="#" ><img src="imagenes/calculadora/btn_calcular_a.jpg" width="100" border="0" onmouseover="this.src='imagenes/calculadora/btn_calcular_b.jpg'" onmouseout="this.src='imagenes/calculadora/btn_calcular_a.jpg'"></a>
																				</div>
																			</td>
																			<td></td>
																		</tr>
																	</table>
																</form>
															</div>

													</td>
													<td height="10">
													</td>
												</tr>
												
												<tr>
											</table>
										</td>
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