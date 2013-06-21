// JavaScript Document
// Se inicializa el seleccionador de fechas o datepicker del formulario
// calendar.gif se encuentra dentro del proyecto comprimido 
$(document).ready(function(){

			
	
	//Deshabilita campos no editables
	$('#producto').attr("disabled", true);
	$('#subProducto').attr("disabled", true);	
	
	//Eventos de botones

	//Busqueda de tasas
	$( "#btnBusqueda").button().click(function( event ) {
		buscarTasa();
	});

	//Limpiar tasa
	$( "#btnLimpiar").button().click(function( event ) {
		limpiarGrillaTasa();
	});

	//Cerrar sesi�n
	$( "#btnCerrar").button().click(function( event ) {
		cerrarSesion();
	});

	//Agregar rango tasa
	$( "#btnAgregar").button().click(function( event ) {
		agregarRangoTasa();
	});

	//Modificar rango tasa
	$( "#btnModificar").button().click(function( event ) {
		modificarRangoTasa();
	});

	//Eliminar rango tasa
	$( "#btnEliminar").button().click(function( event ) {
		eliminarRangoTasa();
	});

	//Por defecto no se muestra estas opciones
	$( "#opciones-grilla").hide();

});


//Selecciona Centro Comercial
function buscarTasa(){

	//Obtiene valores de filtro
	var idTipoPersona = $("#tipoPersona").val();
	var idMoneda = $("#moneda").val();
	var currentrow = 0;
	
	//Validando filtros
	if (idTipoPersona== '1' | idTipoPersona == '2'){
		if(idMoneda == '1' | idMoneda == '2'){
		
			//Limpiar Grilla
			$("#grillaPanel").GridUnload();
			
			//Muestra botones
			$( "#opciones-grilla").show();

			//Carga grilla con las tasas correspondientes para filtros seleccionados
			$.ajax({
				type: 'get',
				scriptCharset: 'utf-8',
				data: 'idTipoPersona='+ idTipoPersona + '&' + 'idMoneda=' + idMoneda,
				url : './consultarTasasServlet',
				success : function(lista) {
					
					//Convierte string a json
					var json = $.parseJSON(lista);
					
					//Construye grilla dinamica
					jQuery("#grillaPanel").jqGrid({	
						datatype: "local",
						//width: '100%',
						//height: '100%',
						width: 750,
						height: 800,
						rowNum: 200,
						colNames:['Selecci&oacuten','Rango','Desde', 'Hasta','Desde','Hasta','Descripcion','Min (%)','Max (%)','Unidad'],
						colModel:[
							{name:'seleccion', index:'seleccion', width:80, sorttype:'int' ,formatter: 'checkbox' ,align: 'center', edittype: "checkbox",formatoptions: {disabled: false}},
							{name:'rango',index:'rango', align:'center' ,width:50},
							{name:'desde',index:'desde', align:'right' ,width:125 ,formatter:'number', formatoptions:{decimalSeparator:",",thousandsSeparator: ",", decimalPlaces:0}},
							{name:'hasta',index:'hasta', align:'right' ,width:125 ,formatter:'number', formatoptions:{decimalSeparator:",",thousandsSeparator: ",", decimalPlaces:0}},
							{name:'plazoMin',index:'plazoMin', align:'center' ,width:75},
							{name:'plazoMax',index:'plazoMax', align:'center' ,width:75},
							{name:'descripcion',index:'descripcion', width:100, editable:true},
							{name:'tasaMin', index:'tasaMin', align:'right' ,width:100},
							{name:'tasaMax', index:'tasaMax', align:'right' ,width:100},
							{name:'unidadPlazo', index:'unidadPlazo' ,width:1,hidden:true}
						],
						viewrecords: true,
						gridview: true,
						sortname: 'descripcion',
					   	grouping:true,
					   	groupingView : {
					   		groupField : ['descripcion'],
					   		groupColumnShow : true,
					   		groupOrder : ['asc'] ,
					   		groupText : ['<b>{0}</b>']
					   	},
						onSelectRow: function(id,status){
							var ch =  jQuery(this).find('#'+id+' input[type=checkbox]').prop('checked');
							if(ch) {
									if (currentrow>0){
										jQuery(this).find('#'+currentrow+' input[type=checkbox]').prop('checked',false);
									}
									jQuery(this).find('#'+id+' input[type=checkbox]').prop('checked',false);
									//jQuery(this).find('#'+currentrow+' input[type=checkbox]').prop('checked',false);
							} else {
									jQuery(this).find('#'+id+' input[type=checkbox]').prop('checked',true);
									jQuery(this).find('#'+currentrow+' input[type=checkbox]').prop('checked',false);
									//jQuery(this).find('#'+currentrow+' input[type=checkbox]').prop('align','center');	
							}
							
							rowChecked=1;
							currentrow=id;
						},
						beforeSelectRow: function(rowid, e) {
							return ($(this).getGridParam('selrow') != rowid);
					  }
					});
					
					//Agrupando columnas
					jQuery("#grillaPanel").jqGrid('setGroupHeaders', {
						  useColSpanStyle: true, 
						  groupHeaders:[
							{startColumnName: 'desde', numberOfColumns: 2, titleText: 'Montos'},
							{startColumnName: 'plazoMin', numberOfColumns: 2, titleText: 'Plazos'},
							{startColumnName: 'tasaMin', numberOfColumns: 2, titleText: 'Tasas'},
						  ]	
						});
					
					//Carga data a grilla
					$(json).each(function(i,val){
						jQuery("#grillaPanel").jqGrid('addRowData',i+1,json[i]);
					});
					
					//Evento para checkbox
					$("#grillaPanel").find('input[type=checkbox]').each(function() {
						$(this).change( function(){
							var colid = $(this).parents('tr:last').attr('id');
							if( $(this).is(':checked')) {
							   $("#grillaPanel").jqGrid('setSelection', colid );
							   $(this).prop('checked',true);
							   //jQuery(this).find('#'+id+' input[type=checkbox]').prop('checked',true);
							}else{
								$("#grillaPanel").jqGrid('setSelection', colid );
							}
							//currentrow=colid;
							return true;
						});
					});
													
					
					//Elimina boton cerrar por defecto.
					$('.ui-jqgrid-titlebar-close','#grillaPanel').remove();
						
				},
				error : function(mensaje_error) {
					alert("Error al obtener informacion de la base de datos. Codigo 2");
				}
			});
			
		}else{
			alert('Debe seleccionar una moneda!!');
		}
	}else{
		alert('Debe seleccionar un tipo de persona!!');
	}
	


}

function limpiarGrillaTasa(){

	//Limpiar Grilla de resultados
	$("#grillaPanel").GridUnload();

	//Limpiando combos: valores por defecto
	$("#tipoPersona option[value='0']").attr("selected",true);
	$("#moneda option[value='0']").attr("selected",true);
	$( "#opciones-grilla").hide();

}

function cerrarSesion(idLocal){

	var respuesta = confirm('Desea cerrar su sesion actual?');

	if (respuesta){
		//Cierra sesi�n
		window.location.href='./cerrarSesionServlet';
	}

}


function agregarRangoTasa(){
	
	
	//Obtiene valores de filtro
	var idTipoPersona = $("#tipoPersona").val();
	var idMoneda = $("#moneda").val();
	
	//Obtiene parametros a enviar
	var cantidad = $("#grillaPanel").getGridParam("reccount");
	var mtoAnterior=0;
	var valTasaAnteriorMin=0;
	var valTasaAnteriorMax=0;
	
	if(cantidad > 0){
		var row = jQuery("#grillaPanel").jqGrid('getRowData',cantidad);
		mtoAnterior = row.desde;
		valTasaAnteriorMin = row.tasaMin;
		valTasaAnteriorMax = row.tasaMax;
	}
	
	
	
	// Seleccionar: Agregar tasa
	$.fancybox({  
		'autoScale'     : false,  
		'transitionIn'      : 'none',
		'transitionOut'     : 'none',
		 closeBtn  : false,
		'width'         : 685,
		'height'        : 565,
		'speedIn'		: 500,
		'speedOut'		: 300,
		'centerOnScroll': true,
		'padding' 		: 5,
		'href'			: './AgregarTasa.jsp?mtoAnterior='+mtoAnterior + '&' + 'valTasaAnteriorMin='+ valTasaAnteriorMin+ '&' + 'valTasaAnteriorMax='+ valTasaAnteriorMax + '&'+'idTipoPersona='+idTipoPersona + '&' + 'idMoneda=' +idMoneda ,
		'type'          : 'iframe',
		'afterClose'     : function() {
		
								//Recargar grilla
								buscarTasa();
							}
	});


}


function modificarRangoTasa(){

	//Obtiene el id de la fila seleccionada
	var id = jQuery("#grillaPanel").jqGrid('getGridParam','selrow');
	
	if (id)	{
		//Obtiene valor de toda la fila 
		var row = jQuery("#grillaPanel").jqGrid('getRowData',id);
		var idRango = row.rango;
		var tasaMin =row.tasaMin;
		var tasaMax =row.tasaMax;
		var mtoMinimo =row.desde;
		var mtoMaximo =row.hasta;
		var plazoMin = row.plazoMin;
		var plazoMax = row.plazoMax;
		var unidadPlazo= row.unidadPlazo;
			
		// Seleccionar: Modificar tasa
		$.fancybox({  
			'autoScale'     	: false,  
			'transitionIn'      : 'elastic',
			'transitionOut'     : 'elastic',
			 closeBtn  		: false,
			'width'         : 705,
			'height'        : 575,
			'speedIn'		: 500,
			'speedOut'		: 300,
			'padding' 		: 5,
			'centerOnScroll': true,
			'href'			: './ModificarTasa.jsp?idRango='+idRango + '&' + 'tasaMin='+tasaMin + '&'+ 'tasaMax='+tasaMax + '&'+ 'plazoMin='+plazoMin + '&'+ 'plazoMax='+plazoMax + '&' + 'mtoMinimo='+mtoMinimo+ '&'+ 	'mtoMaximo='+mtoMaximo+ '&' +'unidadPlazo='+ unidadPlazo,
			'type'          : 'iframe',
			'afterClose'    : function() {

									//Recargar grilla
									buscarTasa();
									return;
								}
		});
		
	} else { 
		alert("Debe seleccionar un registro!!");
	}
	
	
}

//Selecciona Opciones de Panel de Control
function eliminarRangoTasa(){

	//Obtiene el id de la fila seleccionada
	var id = jQuery("#grillaPanel").jqGrid('getGridParam','selrow');
	
	if (id)	{
		
		//Confirmaci�n de eliminar
		var respuesta = confirm("Desea eliminar el registro seleccionado?");
		if (respuesta){

			//Obtiene valor de toda la fila 
			var row = jQuery("#grillaPanel").jqGrid('getRowData',id);
			var idRango = row.rango;
			var plazoInicial = row.plazoMin;
			var plazoFinal = row.plazoMax;
			var unidadPlazo = row.unidadPlazo;
			
			// Elimina registros asincronamente.
			$.ajax({
				type: 'post',
				url : './eliminarRangoTasaServlet?idRango='+idRango+'&'+'plazoInicial='+plazoInicial+'&'+ 'plazoFinal='+ plazoFinal + '&'+ 'unidadPlazo='+ unidadPlazo,
				success : function(msj) {
					
					//Muestra mensaje de confirmarci�n
					alert("Rango(s) eliminado(s) correctamente.");
					
					//Recarga valores de grilla
					buscarTasa();
				},
				error : function(mensaje_error) {
					alert(mensaje_error);
				}
			});
		}
		
	} else { 
		alert("Debe seleccionar un registro!!");
	}


}