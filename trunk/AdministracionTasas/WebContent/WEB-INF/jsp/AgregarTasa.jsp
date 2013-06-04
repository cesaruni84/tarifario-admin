<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.srluren.admin.services.impl.MonedaServicesImpl"%>
<%@ page import="org.srluren.admin.services.impl.TipoPersonaServicesImpl"%>
<%@ page import="org.srluren.admin.beans.hb.Producto,java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta charset="utf-8">
<title>Agregar Rango Tasa - Se�or de Luren</title>
<link rel="stylesheet" href="resources/ui/themes/jquery.ui.all.css">
<link href="css/estilos.css" rel="stylesheet" type="text/css">
<script src="resources/ui/js/jquery-1.8.0.js"></script>
<script src="js/funciones.js" type="text/javascript"></script>
<script src="js/validCampoFranz.js" type="text/javascript"></script>
<script src="resources/ui/js/jquery.ui.core.js"></script>
<script src="resources/ui/js/jquery.ui.widget.js"></script>
<script src="resources/ui/js/jquery.ui.button.js"></script>
<script src="resources/ui/js/jquery.ui.position.js"></script>
<script src="resources/ui/js/jquery.ui.dialog.js"></script>
<script src="resources/ui/js/json2.js"></script>
<script src="js/jquery.geturlparam.js"></script>
<script src="fancybox/js/jquery.fancybox.pack.js" type="text/javascript" ></script>
<script src="js/jquery.form.js" type="text/javascript"></script>

<!-- JQuery Validate -->
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/messages_es.js" type="text/javascript"></script>
<script src="js/additional-methods.js" type="text/javascript"></script>

<!-- Librerias jqgrid -->
<link rel="stylesheet" type="text/css" media="screen" href="jqgrid/css/ui.jqgrid.css" />
<script src="jqgrid/js/i18n/grid.locale-es.js" type="text/javascript"></script>
<script src="jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>

<!-- FancyBox Ajax -->
<script src="fancybox/js/jquery.fancybox.pack.js" type="text/javascript"></script>
<link rel="stylesheet" href="fancybox/css/jquery.fancybox.css" type="text/css" media="screen" />

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

	

</style>
<script>
	$(document).ready(function(){
	
		//Deshabilita campos no editables
		$('#producto_id').attr("disabled", true);
		$('#subProducto_id').attr("disabled", true);
		$('#tipoPersona_id').attr("disabled", true);
		$('#moneda_id').attr("disabled", true);
		
		
		//Define restricciones de ingreso de datos
		$('#mtoMinimo').validCampoFranz('0123456789.');
		$('#mtoMaximo').validCampoFranz('0123456789.');
		$('#valorTasaMin').validCampoFranz('0123456789.');
		$('#valorTasaMax').validCampoFranz('0123456789.');
		$('#plazoInicial').validCampoFranz('0123456789');
		$('#plazoFinal').validCampoFranz('0123456789');
		
		
		//Evento "grabar datos"
		$("#grabar").button().click(function(event){
			
			//URL
			var url_action = './grabarRangoTasaServlet';
			
			//Manejo del evento Submit
			$("form[id='formulario']").ajaxForm({
				clearForm: false,
				success:function(response, statusText, xhr, $form){
					
					//Obteniendo respuesta
					var json = $.parseJSON(response);
					var codigo = json.cod_retorno;
					var mensaje = json.msj_retorno;
					
					//Muestra mensaje
					alert(mensaje);

					//Según la respuesta, cierra ventana
					if(codigo == 0){
						//Cierra ventana
						parent.$.fancybox.close();
					}

				
				}
			}); 
			
			//Actualizando el url para ejecutar evento submit
			$("form[name='formulario']").attr("action",url_action);
			$("form[name='formulario']").submit();

		});

		//Evento "cancelar"
		$("#cancelar").button().click(function(event){
			parent.$.fancybox.close();
		});
		
		var mensajes = {  
			valorTasaMin: {lessThanEqualDouble:"El valor de tasa minima debe ser menor o igual a la tasa máxima"},
			plazoInicial: {lessThanEqualInt:"El valor del plazo minima debe ser menor o igual al plazo máximo"}
		}; 
		
		//Validacion campos entrada de formulario
		 $("#formulario").validate({
                rules: {
                    mtoMinimo: {
                        required: true,
						number: true,
                        maxlength: 12	
                    },
                    mtoMaximo: {
                        required: true,
						number: true,
                        maxlength: 12	
                    },
                    valorTasaMin: {
                        required: true,
                        number: true,
                        maxlength: 5,
						lessThanEqualDouble:'#valorTasaMax'
                    },
                    valorTasaMax: {
                        required: true,
                        number: true,
                        maxlength: 5
                    },
                    plazoInicial: {
                        required: true,
                        number: true,
                        min: 1,
						lessThanEqualInt:'#plazoFinal'
                    },
                    plazoFinal: {
                        required: true,
                        number: true,
                        min:1
                    }
    
                },
				messages:mensajes
		});
		
		
	});
</script>

</head>
<body>
	<% 
	
		//Obtiene objeto de sesion
		HttpSession sesion_actual = request.getSession(true);
		
		//Valida sesion actual
		if (sesion_actual == null || sesion_actual.getAttribute("usuario") == null){
			
			//Redirecciona a pagina de inicio
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main.do");
			dispatcher.forward(request, response);
		}
	
	%>


	<div class="cuerpo-pop-modal" style="height:560px; width:680px;">

		<div id="dialog-form-modal">
			<form method="post" id="formulario" name="formulario">
				<table style="margin-left: 10px;">
					<br>
					<tr>
						<td width="120"><label for="producto" style="margin-top: 5px;"><b>Producto: </label></td>
						<td><input type="text" name="producto" id="producto_id" size="50" class="text ui-widget-content ui-corner-all" value="<%=request.getSession().getAttribute("filtroProducto")%>" style="border:none"/></td>
					</tr>
					<tr>
						<td width="120"><label for="subProducto" style="margin-top: 5px;"><b>Sub-Producto: </label></td>
						<td><input type="text" name="subProducto" id="subProducto_id" size="70" class="text ui-widget-content ui-corner-all" value="<%=request.getSession().getAttribute("filtroSubProducto")%>" style="border:none"/></td>
					</tr>
				</table>
				
				<table style="margin-left: 10px;">
					<tr>
						<td width="120"><label for="tipoPersona" style="margin-top: 5px;"><b>Tipo Persona: </label></td>
						<td><input type="text" name="tipoPersona" id="tipoPersona_id" size="20" class="text ui-widget-content ui-corner-all" value="<%=request.getSession().getAttribute("filtroTipoPersona")%>" style="border:none"/></td>
					</tr>
					<tr>
						<td width="120"><label for="moneda" style="margin-top: 5px;"><b>Moneda: </label></td>
						<td><input type="text" name="moneda" id="moneda_id" size="20" class="text ui-widget-content ui-corner-all" value="<%=request.getSession().getAttribute("filtroMoneda")%>" style="border:none"/></td>
					</tr>
					<tr>
						<td width="120"><label for="mtoMinimo" style="margin-top: 5px;"><b>Desde: </label></td>
						<td><input type="text" name="mtoMinimo" id="mtoMinimo" size="20" maxlength="12" class="text ui-widget-content ui-corner-all"/></td>
					</tr>
					
					<tr>
						<td width="120"><label for="mtoMaximo" style="margin-top: 5px;"><b>Hasta: </label></td>
						<td><input type="text" name="mtoMaximo" id="mtoMaximo" size="20" maxlength="12" class="text ui-widget-content ui-corner-all"/></td>
					</tr>

					<tr>
						<td width="120"><label for="valorTasaMin" style="margin-top: 5px;"><b>Tasa Min (%): </label></td>
						<td><input type="text" name="valorTasaMin" id="valorTasaMin" size="20"  maxlength="5" class="required valorTasaMin text ui-widget-content ui-corner-all"/></td>
					</tr>
					<tr>
						<td width="120"><label for="valorTasaMax" style="margin-top: 5px;"><b>Tasa Max(%): </label></td>
						<td><input type="text" name="valorTasaMax" id="valorTasaMax" size="20"  maxlength="5" class="required valorTasaMax text ui-widget-content ui-corner-all"/></td>
					</tr>
					<tr>
						<td width="120"><label for="plazoInicial" style="margin-top: 5px;"><b>Plazo Min: </label></td>
						<td><input type="text" name="plazoInicial" id="plazoInicial" size="20"  maxlength="6"  class="required plazoInicial text ui-widget-content ui-corner-all"/></td>
					</tr>
					<tr>	
						<td width="120"><label for="plazoFinal" style="margin-top: 5px;"><b>Plazo Max: </label></td>
						<td><input type="text" name="plazoFinal" id="plazoFinal" size="20" maxlength="6" class="required plazoFinal text ui-widget-content ui-corner-all"/></td>
					</tr>
					
					<tr>
						<td><label for="unidadPlazo"><b>Unidad: <br></label></td>
						<td align="left"><select name="unidadPlazo" id="unidadPlazo" class="text ui-widget-content" style="margin-bottom:5px;height: 25px; width: 165px; color: #039; font-family: Arial, Helvetica, sans-serif;">
								<option value="0" selected="selected">Días</option>
								<option value="1" >Meses</option>	
						</select></td>
						<td></td>
					</tr>
					
				</table>
				<br>
				<div style="margin-left: 10px;" align="center">
					<input type="button" style="width:90px;" name="Grabar" value="Grabar" id="grabar" size="80"/>
					<input type="button" style="width:90px;" name="Cancelar" value="Cancelar" id="cancelar" size="80"/>
				</div>
			</form>

		</div>

	</div>

</body>
</html>
