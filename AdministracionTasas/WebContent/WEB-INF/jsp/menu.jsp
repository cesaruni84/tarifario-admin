<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.srluren.admin.services.impl.ProductoServicesImpl"%>
<%@ page import="org.srluren.admin.beans.hb.Producto,java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta charset="utf-8">
<title>Menú Principal - Administraci�n de tasas</title>
<link rel="stylesheet" href="resources/ui/themes/jquery.ui.all.css">
<link href="css/estilos.css" rel="stylesheet" type="text/css">
<script src="resources/ui/js/jquery-1.8.0.js"></script>
<script src="resources/ui/js/jquery.ui.core.js"></script>
<script src="resources/ui/js/jquery.ui.widget.js"></script>
<script src="resources/ui/js/jquery.ui.button.js"></script>
<script src="resources/ui/js/jquery.ui.position.js"></script>
<script src="resources/ui/js/jquery.ui.dialog.js"></script>
<script src="resources/ui/js/json2.js"></script>

<!-- Efectos en Lista de Botones imagenes de productos -->
<link rel="stylesheet" type="text/css" href="css/pro_dropdown_3.css" />
<script src="js/stuHover.js" type="text/javascript"></script>

<style>
body {
	font-size: 75%;
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
	margin-bottom: 12px;
	padding: .4em;
	color: #039;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana", "sans-serif";
	font-size: 1.4em;
	color: #039;
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

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>

</head>


<body>
	<div class="logo" align="center" ><img src="./imagenes/imagen_principal.png" width="600"></div><br>
	<div class="cuerpo-pop" align="center">
	
		<!-- Menu de Seccion Principal: Aqui se incluiran los cambios-->
		<table width="750" border="0" cellspacing="0" cellpadding="0" style="margin-left:25px;">
			<tr>
				<td>
					<div align="left" style="margin-top:20px;color:#50b5d0;font-family:Arial,Helvetica,sans-serif;font-size:16px;"><b>ADMINISTRACION DE TASAS DE PRODUCTOS PASIVOS/ACTIVOS</b></div><br>
					<div align="left" style="margin-top:5px; width:725px;border:1px solid #039"></div>
					<div align="left" style="margin-top:10px;color:#039;font-family:Arial,Helvetica,sans-serif;">Seleccione el tipo de producto</div><br>
					<div id="opciones_productos" style="margin-top:15px;">
				
						<!--Menu de productos-->
						<ul id="nav">
							<!--Credito Microempresa-->
							<li class="top"><a href="#" id="btnCreMicro" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_microempresa_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_microempresa_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_microempresa_a.jpg'"/></span></a>
								<ul class="sub" style="margin-top:50px">
									<li><a href="grillaPrincipal?nemProducto=CREMICRO&nemSubProducto=ACTF">Activo Fijo</a></li>
									<li><a href="grillaPrincipal?nemProducto=CREMICRO&nemSubProducto=CAPT">Capital Trabajo</a></li>
								</ul>
							</li>
							
							<!--Credito Consumo-->
							<li class="top"><a href="#" id="btnCreConsu" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_consumo_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_consumo_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_consumo_a.jpg'"/></span></a>
								<ul class="sub" style="margin-top:50px">
									<li><a href="grillaPrincipal?nemProducto=CRECONS&nemSubProducto=DIRE">Consumo Directo</a></li>
									<li><a href="grillaPrincipal?nemProducto=CRECONS&nemSubProducto=DPLA">Consumo descuento por planilla</a></li>
									<li><a href="grillaPrincipal?nemProducto=CRECONS&nemSubProducto=GAPF">Consumo con Garantía de PF</a></li>
								</ul>										
							</li>
							
							<!--Credito PYMG-->
							<li class="top"><a href="#" id="btnCrePMYG" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_pmyg_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_pmyg_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_pmyg_a.jpg'"/></span></a>
								<ul class="sub" style="margin-top:50px">
									<li><a href="grillaPrincipal?nemProducto=CREPMYG&nemSubProducto=ACTF1">Activo Fijo</a></li>
									<li><a href="grillaPrincipal?nemProducto=CREPMYG&nemSubProducto=CAPT1">Capital Trabajo</a></li>
								</ul>											
							</li>
							
							<!--Credito Agrícola-->
							<li class="top"><a href="#" id="btnCreAgri" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_agricola_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_agricola_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_agricola_a.jpg'"/></span></a>
								<ul class="sub" style="margin-top:50px">
									<li><a href="grillaPrincipal?nemProducto=CRECAGRI&nemSubProducto=ACTF2">Activo Fijo</a></li>
									<li><a href="grillaPrincipal?nemProducto=CRECAGRI&nemSubProducto=CAPT2">Capital Trabajo</a></li>
								</ul>										
							</li>
							
							<!--Credito Hipotecario-->
							<li class="top"><a href="#" id="btnCreHipo" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_hipotecario_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_hipotecario_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_hipotecario_a.jpg'"/></span></a>
								<ul class="sub" style="margin-top:50px">
									<li><a href="grillaPrincipal?nemProducto=CREHIPO&nemSubProducto=MIVV">Mi Vivienda</a></li>
									<li><a href="grillaPrincipal?nemProducto=CREHIPO&nemSubProducto=TEPP">Techo Propio</a></li>
								</ul>										
							</li>
						
							<!--Credito KIVA-->
							<li class="top"><a href="#" id="btnCreKIVA" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_cred_kiva_a.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_cred_kiva_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_cred_kiva_a.jpg'"/></span></a>
								<ul class="sub">
									<li><a href="grillaPrincipal?nemProducto=CREKIVA&nemSubProducto=NOBA">Crédito para No Bancarizados</a></li>
									<li><a href="grillaPrincipal?nemProducto=CREKIVA&nemSubProducto=ESTD">Crédito para Estudiantes</a></li>
									<li><a href="grillaPrincipal?nemProducto=CREKIVA&nemSubProducto=AGSA">Crédito de Agua y Saneamiento</a></li>
									<li><a href="grillaPrincipal?nemProducto=CREKIVA&nemSubProducto=MICA">Crédito Micro Agrícola</a></li>
								</ul>									
							</li>
							
							<!--Ahorros-->
							<li class="top"><a href="#" id="btnAhorros" class="top_link"><span class="down"><img src="imagenes/calculadora/btn_ahorros_b.jpg" width="80" onmouseover="this.src='imagenes/calculadora/btn_ahorros_b.jpg'" border="0" onmouseout="this.src='imagenes/calculadora/btn_ahorros_b.jpg'"/></span></a>
								<ul class="sub" style="margin-top:50px">
									<li><a href="grillaPrincipal?nemProducto=AHOR&nemSubProducto=AHOCORR">Ahorros Corrientes</a></li>
									<li><a href="grillaPrincipal?nemProducto=AHOR&nemSubProducto=DEPLZO">Depósitos a Plazo</a></li>
									<li><a href="grillaPrincipal?nemProducto=AHOR&nemSubProducto=ORDPAGO">Órdenes de Pago</a></li>
									<li><a href="grillaPrincipal?nemProducto=AHOR&nemSubProducto=CTS">CTS</a></li>
								</ul>										
							</li>
							
						</ul>
					</div>
					<div align="left" style="margin-top:70px; width:725px;border:1px solid #039"></div>
				</td>

				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
		
</body>
</html>
