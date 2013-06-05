<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.sim.credito.bean.CreditoCronograma,java.util.*"%>
<%@ page import="org.sim.credito.bean.Cuota"%>
<%@ page import="org.sim.credito.resources.Constantes"%>
<%@ page import="org.sim.credito.util.Utilitario"%>
<jsp:useBean id="creditoCronograma" scope="page" class="org.sim.credito.bean.Credito" />
<jsp:useBean id="creditoConsumo" scope="page" class="org.sim.credito.bean.Credito" />
	
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
<script src="resources/ui/js/jquery.ui.core.js"></script>
<script src="resources/ui/js/jquery.ui.widget.js"></script>
<script src="resources/ui/js/jquery.ui.button.js"></script>
<script src="resources/ui/js/jquery.ui.mouse.js"></script>
<script src="resources/ui/js/jquery.ui.draggable.js"></script>
<script src="resources/ui/js/jquery.ui.position.js"></script>
<script src="resources/ui/js/jquery.ui.dialog.js"></script>
<script src="resources/ui/js/jquery.ui.effect.js"></script>
<script src="resources/ui/js/jquery.ui.datepicker.js"></script>
<script src="resources/ui/js/json2.js"></script>
<script src="Scripts/javascripts.js" type="text/javascript"></script>
<script src="Scripts/javascripts2.js" type="text/javascript"></script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>

<link rel="stylesheet" href="resources/ui/themes/jquery.ui.css" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/pro_dropdown_3.css" />
<script src="js/stuHover.js" type="text/javascript"></script>

<!-- JQuery Print Area -->
<script src="js/jquery.jqprint-0.3.js" type="text/javascript"></script>

<!-- Impresion -->
<link rel="stylesheet" type="text/css" href="resources/ui/themes/printAll.css" media="print"/>


<style>
body {
	font-size: 78%;
}

select,.ui-select-menu {
	float: left;
	margin-right: 10px;
}

label,input {
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
}

label.error {
	color: red;
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

#imagen_srluren {
 display: none;
}


#fecha_hora {
 display: none;
}

#etiquetaProducto {
 display: none;
}
	
</style>

<script>
	$(document).ready(function(){
	
		//Colores para tabla cronograma
		$('table#tablaCronograma').styleTable({
			th_bgcolor: '#85D0DB', //Color para la cabecera
			th_color: '#ffffff',
			th_border_color: '#85D0DB',
			tr_color :  '#1A4672',   //Color para el contenido de las filas
			tr_odd_bgcolor: '#ECF6FC',
			tr_even_bgcolor: '#ffffff',
			tr_border_color: '#95BCE2',
			tr_hover_bgcolor: '#BCD4EC',
			font_name: 'Arial, Helvetica, sans-serif',
			font_size: '11'
			
		});
		
		//Deshabilita campos no editables
		$('#subProducto_res').attr("disabled", true);
		$('#montoCredito_res').attr("disabled", true);
		$('#moneda_res').attr("disabled", true);
		$('#valorTasa_res').attr("disa	bled", true);
		$('#numCuotas_res').attr("disabled", true);
		$('#subProducto_res').attr("disabled", true);
		$('#tipoPersona_res').attr("disabled", true);
		$('#formaPago_res').attr("disabled", true);	
		$('#formaPago_res').attr("disabled", true);	
		$('#valorTEA_res').attr("disabled", true);	
		$('#valorTCEA_res').attr("disabled", true);
		$('#totalIntereses_res').attr("disabled", true);
		$('#fechaDesembolso_res').attr("disabled", true);
		$('#seguroDesgrav_res').attr("disabled", true);
		$('#seguroTodoRiesgo_res').attr("disabled", true);
		$('#totalIntereses').attr("disabled", true);
	
		//Accion Imprimir
		$("#btnImprimir").click(function(){
			$("#opciones-imprimir").dialog("open");
			return false;
		});
		
		//Configuracion de Opciones de Impresión
		$( "#opciones-imprimir" ).dialog({
			resizable: false,
			autoOpen: false,
			draggable: true,
			width:'350',
            minHeight: 120,
            maxHeight: 200,
			modal: true,
			title: 'Confirmación',
			buttons: {
				"PDF": function() {
					$( this ).dialog( "close" );
					
					//Genera reporte en PDF
					window.location.href='./genRepCredPymeServlet';
				},
				"Impresora": function() {
					$("#panel-control").jqprint();
					$( this ).dialog( "close" );
				},
				"Cancelar": function() {
					$( this ).dialog( "close" );
				}
			}
		});
		
		//Accion Regresar
		$("#btnRegresar").click(function(){
			parent.history.back();
			return false;
		});
		
	});
</script>

</head>

<body>
	<div align="center">
	
		<%
			//Obtiene objeto de sesion
			HttpSession sesion_actual = request.getSession(true);
		
			//Guarda fecha y hora en sesión			
			sesion_actual.setAttribute("fecha", (String) "Fecha: "+  Utilitario.getFechaSistema());
			sesion_actual.setAttribute("hora",  (String) "Hora: " +  Utilitario.getHoraSistema());
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
							
							<!-- Opciones de Seccion Principal-->
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
													<td height="500" align="center" valign="top">
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
																	<div id="opciones_productos" style="margin-top:10px">
																		<!--Menu de productos-->
																		<ul id="nav">
																			<!--Credito Microempresa-->
																			<li class="top"><a href="#" id="btnCreMicro" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_microempresa_a.jpg" width="80" border="0" onmouseover="this.src='imagenes/calculadora/btn_cred_microempresa_b.jpg'" onmouseout="this.src='imagenes/calculadora/btn_cred_microempresa_a.jpg'"/></span></a>
																			</li>
																			
																			<!--Credito Consumo-->
																			<li class="top"><a href="#" id="btnCreConsu" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_consumo_a.jpg" width="80" border="0" onmouseover="this.src='imagenes/calculadora/btn_cred_consumo_b.jpg'" onmouseout="this.src='imagenes/calculadora/btn_cred_consumo_a.jpg'"/></span></a>									
																			</li>
																			
																			<!--Credito PYMG-->
																			<li class="top"><a href="#" id="btnCrePMYG" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_pmyg_b.jpg" width="80" border="0" onmouseover="this.src='imagenes/calculadora/btn_cred_pmyg_b.jpg'" onmouseout="this.src='imagenes/calculadora/btn_cred_pmyg_b.jpg'"/></span></a>
																				<ul class="sub" style="margin-top:50px">
																					<li><a href="calcu-crepmyg.htm?nemProducto=CREPMYG&nemSubProducto=ACTF1">Activo Fijo</a></li>
																					<li><a href="calcu-crepmyg.htm?nemProducto=CREPMYG&nemSubProducto=CAPT1">Capital Trabajo</a></li>
																				</ul>											
																			</li>
																			
																			<!--Credito Agrícola-->
																			<li class="top"><a href="#" id="btnCreAgri" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_agricola_a.jpg" width="80" border="0" onmouseover="this.src='imagenes/calculadora/btn_cred_agricola_b.jpg'" onmouseout="this.src='imagenes/calculadora/btn_cred_agricola_a.jpg'"/></span></a>									
																			</li>
																			
																			<!--Credito Hipotecario-->
																			<li class="top"><a href="#" id="btnCreHipo" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_hipotecario_a.jpg" width="80" border="0" onmouseover="this.src='imagenes/calculadora/btn_cred_hipotecario_b.jpg'" onmouseout="this.src='imagenes/calculadora/btn_cred_hipotecario_a.jpg'"/></span></a>									
																			</li>
																		
																			<!--Credito KIVA-->
																			<li class="top"><a href="#" id="btnCreKIVA" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_kiva_a.jpg" width="80" border="0" onmouseover="this.src='imagenes/calculadora/btn_cred_kiva_b.jpg'" onmouseout="this.src='imagenes/calculadora/btn_cred_kiva_a.jpg'"/></span></a>								
																			</li>
																			
																			<!--Ahorros-->
																			<li class="top"><a href="#" id="btnAhorros" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_ahorros_a.jpg" width="80" border="0" onmouseover="this.src='imagenes/calculadora/btn_ahorros_b.jpg'" onmouseout="this.src='imagenes/calculadora/btn_ahorros_a.jpg'"/></span></a>									
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
														<div id="panel-control" style="margin-top:-120px;">
															<!--Cuadro de Resumen-->
															<div id="imagen_srluren">
																<table width="750" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td><img src="imagenes/fondo_cabecera.jpg" width="750" height="120"/></td>
																	</tr>
																</table>
															</div>
															<div id="opciones-imprimir" class="dialog confirm">¿Desea imprimir documento o guardar en PDF?</div>														
															<div id="panel-resumen" style="margin-left:22px">

																<table id="etiquetaProducto" style="margin-right:81px">
																	<tr>
																		<td id="fila"><label for="producto_res1" style="float:left;"><b>Producto :<br> </label></td>
																		<td id="producto"><input type="text" name="producto_res1" id="producto_res1" size="30" class="text ui-widget-content" value="<%=request.getSession().getAttribute("nombreProducto")%>" style="margin-bottom:5px;border:none"/></td>
																	</tr>
																</table>

																<table style="margin-right:81px">
																	<tr>
																		<td width="200"><label for="subProducto_res" style="float:left;"><b>Sub-Producto :<br> </label></td>
																		<td><input type="text" name="subProducto_res" id="subProducto_res" size="30" class="text ui-widget-content" value="<%=request.getSession().getAttribute("nombreSubProducto")%>" style="margin-bottom:5px;border:none"/></td>
																		<td width="210"></td><td></td>
																	</tr>
																
																	<tr>
																		<td><label for="montoCredito_res" style="margin-top:5px;float:left;"><b>Monto: <br></label></td>
																		<td><input type="text" name="montoCredito_res" id="montoCredito_res" size="30" class="text ui-widget-content" value="<%=request.getAttribute("montoPrestamo")%>" style="border:none"></td>
																		<td><label for="tipoPersona_res" style="float:left;"><b>TipoPersona: <br></label></td>
																		<td><input type="text" name="tipoPersona_res" id="tipoPersona_res" size="30" class="text ui-widget-content" value="<%=request.getAttribute("tipoPersona")%>" style="border:none"></td>
																	</tr>
																	
																	<tr>
																		<td><label for="moneda_res" style="float:left;"><b>Moneda: <br></label></td>
																		<td><input type="text" name="moneda_res" id="moneda_res" size="30" class="text ui-widget-content" value="<%=request.getAttribute("moneda")%>" style="border:none"></td>
																		<td><label for="numCuotas_res" style="float:left;"><b>Número de Cuotas:<br> </label></td>
																		<td><input type="text" name="numCuotas_res" id="numCuotas_res" size="30" class="text ui-widget-content" value="<%=request.getAttribute("numCuotas")%>" style="margin-bottom:5px;border:none"/></td>
																	</tr>
																

																	<tr>
																		<td><label for="formaPago_res" style="float:left;"><b>Forma de Pago: <br></label></td>
																		<td><input type="text" name="formaPago_res" id="formaPago_res" size="30" class="text ui-widget-content" value="<%=request.getAttribute("formaPago")%>" style="margin-bottom:5px;border:none"/></td>
																		<td><label for="fechaDesembolso_res" style="float:left;"><b>Fecha Desembolso:<br> </label></td>
																		<td><input type="text" name="fechaDesembolso_res" id="fechaDesembolso_res" size="30" class="text ui-widget-content" value="<%=request.getAttribute("fechaDesembolso")%>" style="margin-bottom:5px;border:none"></td>
																	</tr>

																	<tr>
																		<td><label for="valorTEA_res" style="float:left;"><b>TEA :<br></label></td>
																		<td><input type="text" name="valorTEA_res" id="valorTEA_res" size="30" class="text ui-widget-content" value="<%=request.getAttribute("valorTEA")%>" style="margin-bottom:5px;border:none"/></td>
																		<td><label for="seguroDesgrav_res" style="float:left;"><b>Seguro Desgravamen: <br></label></td>
																		<td><input type="text" name="seguroDesgrav_res" id="seguroDesgrav_res" size="30" class="text ui-widget-content" value="<%=request.getAttribute("seguroDesgravamen")%>" style="margin-bottom:5px;border:none"/></td>
																	</tr>
																	
																	<tr>
																		<td><label for="totalIntereses" style="float:left;"><b>Intereses :<br></label></td>
																		<td><input type="text" name="totalIntereses" id="totalIntereses" size="30" class="text ui-widget-content" value="<%=request.getAttribute("totalIntereses")%>" style="margin-bottom:5px;border:none"/></td>
																		<td><label for="valorTCEA_res" style="float:left;"><b>TCEA :<br> </label></td>
																		<td><input type="text" name="valorTCEA_res" id="valorTCEA_res" size="30" class="text ui-widget-content" value="<%=request.getAttribute("valorTCEA")%>" style="margin-bottom:5px;border:none"></td>
																	</tr>
															
																	<tr>

																		<td></td><td></td>
																	</tr>
				
																</table>
																
																<div id="opciones-cronograma" align="right" style="float:right;margin-right:15px;">
																	<a id="btnVisita" href="solicitud-visita.htm"><img src="imagenes/calculadora/btn_contactar_a.jpg" width="100" border="0" onmouseover="this.src='imagenes/calculadora/btn_contactar_b.jpg'" onmouseout="this.src='imagenes/calculadora/btn_contactar_a.jpg'"></a>
																	<a id="btnImprimir" href="#"><img src="imagenes/calculadora/btn_imprimir_a.jpg" width="100" border="0" onmouseover="this.src='imagenes/calculadora/btn_imprimir_b.jpg'" onmouseout="this.src='imagenes/calculadora/btn_imprimir_a.jpg'"></a>
																	<a id="btnRegresar" href="#"><img src="imagenes/calculadora/regresar_a.jpg" width="100" border="0" onmouseover="this.src='imagenes/calculadora/regresar_b.jpg'" onmouseout="this.src='imagenes/calculadora/regresar_a.jpg'"></a>
																</div>
													
															</div>
															
															<!-- Solo aparecerá en modo impresion -->
															<div id="fecha_hora">
																<table width="750" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td height="10"></td>
																		<td></td>
																	</tr>
																	<tr>
																		<td align="right">
																			<div align="left">
																				<input type="text" style="border:none;" value="<%=request.getSession().getAttribute("fecha")%>"/>
																			</div>
																		</td>
																		
																		<td align="center">
																			<div align="center">
																				<input type="text" style="border:none;" value="<%=request.getSession().getAttribute("hora")%>"/>
																			</div>
																		</td>
																	</tr>
																	<tr>
																		<td height="10"></td>
																		<td></td>
																	</tr>
																</table>
															</div>
															
															<!--Tabla con el cronograma-->
															<div id="panel-cronograma">
																<table id="tablaCronograma" border="0" cellpadding="4" cellspacing="0" align="center" width="100%">
																	<thead>
																		<th align="center" width="30">ITEM</th>
																		<th align="center" width="80">FECHA</th>
																		<th align="center" width="80">SALDO CAPITAL</th>
																		<th align="center" width="80">CAPITAL</th>
																		<th align="center" width="80">INTERESES</th>
																		<th align="center" width="60">SEG. DESGRAV.</th>
																		<th align="center" width="60">SEG. DIVINO</th>
																		<th align="center" width="80">CUOTA</th>
																		<th align="center" width="60">ITF</th>
																		<th align="center" width="80">CUOTA FINAL</th>
																	</thead>
																	
																	<%
																		//Html a devolver
																		String htmlListaCuotas = "";
																		
																		//Obtiene lista de cuotas
																		List<Cuota> listaCuotasCredito = (List<Cuota>)request.getAttribute("listaCuotasCredito");
																		
																		//Recorre List
																		Iterator<Cuota> iter = listaCuotasCredito.iterator();
				
																		while (iter.hasNext()) {
																			Cuota cuota = (Cuota) iter.next();
																			
																			//Item
																			String valor = "<td align='center'>"+  cuota.getSecuencialCuota() + "</td>";
																			
																			//Fecha desembolso
																			valor = valor + "<td align='center'>"+  cuota.getFechaPagoCuota() + "</td>";
																			
																			//Saldo Capital
																			valor = valor + "<td align='center'>"+  Utilitario.formatearMontosMiles(cuota.getSaldoCapital(), Constantes.FORMATO_MONTOS_VISTA)  + "</td>";
																			
																			//Capital
																			valor = valor + "<td align='center'>"+  Utilitario.formatearMontosMiles(cuota.getMontoCapital(), Constantes.FORMATO_MONTOS_VISTA)  + "</td>";
																			
																			//Interes
																			valor = valor + "<td align='center'>"+  Utilitario.formatearMontosMiles(cuota.getInteresCuota(), Constantes.FORMATO_MONTOS_VISTA) + "</td>";
																			
																			//Seg Desgrav
																			valor = valor + "<td align='center'>"+  Utilitario.formatearMontosMiles(cuota.getSeguroDesgravamenCuota(), Constantes.FORMATO_MONTOS_VISTA) + "</td>";
																			
																			//Seg. Divino
																			valor = valor + "<td align='center'>"+  Utilitario.formatearMontosMiles(cuota.getDivinoSeguroCuota(), Constantes.FORMATO_MONTOS_VISTA)  + "</td>";
																		
																			//SubTotal Cuota
																			valor = valor + "<td align='center'>"+  Utilitario.formatearMontosMiles(cuota.getValorSubCuotaFinal(), Constantes.FORMATO_MONTOS_VISTA) + "</td>";
																			
																			//ITF
																			valor = valor + "<td align='center'>"+  Utilitario.formatearMontosMiles(cuota.getValorITFCuota(), Constantes.FORMATO_MONTOS_VISTA) + "</td>";
																			
																			//Cuota final
																			valor = valor + "<td align='center'>"+  Utilitario.formatearMontosMiles(cuota.getValorCuotaFinal(), Constantes.FORMATO_MONTOS_VISTA) + "</td>";
																			
																			//Genera html dinamico
																			String hmtlCuota = "<tr>" + valor + "</tr>";
																			htmlListaCuotas = htmlListaCuotas + hmtlCuota;
																		}
																		
																		//Retorna html
																		out.println(htmlListaCuotas);

																	%>
										
																	<thead>
																		<th align="center" width="30"></th>
																		<th align="center" width="30">TOTALES</th>
																		<th align="center" width="80"></th>
																		<th align="center" width="80"><% out.println(request.getAttribute("totalAmortizaciones"));%></th>
																		<th align="center" width="80"><% out.println(request.getAttribute("totalIntereses"));%></th>
																		<th align="center" width="60"><% out.println(request.getAttribute("totalSegDesgravamen"));%></th>
																		<th align="center" width="60"><% out.println(request.getAttribute("totalSegDivino"));%></th>
																		<th align="center" width="80"><% out.println(request.getAttribute("totalCuotas"));%></th>
																		<th align="center" width="60"><% out.println(request.getAttribute("totalITF"));%></th>
																		<th align="center" width="80"><% out.println(request.getAttribute("totalCuotaFinal"));%></th>
																	</thead>
																	
																</table>
																
																<div style="margin-top:20px;margin-bottom:20px;margin-left:10px">
																	<label style="color:red">* Las cuotas son referenciales, sujetas a calificación y a la fecha de desembolso del crédito.</label>
																</div>
																
															</div>

														</div>

													</td>
												</tr>
												
												
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
				<td align="left" class="text_9b">© Copyright 2008. Caja Señor
					de Luren. Todos los derechos reservados. Central telefónica: <span
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