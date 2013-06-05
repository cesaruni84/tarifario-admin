//valida números
function valida_numero(numero)
{
if (!/^([0-9])*$/.test(numero)){
		return false;
}else{
		return true;
	}
}

function comas(cadena)
{
	
	var xd = document.getElementById(cadena).value;
	var pat=/,/
	if(pat.test(xd))
	{
			alert("Usted a ingresado una ',' solo estan permitido puntos");
			document.getElementById(cadena).value = xd.replace(",", ".");

	}	
}
function limpiar()
{
		document.form1.reset();
		document.form1.txt_monto.focus();
}


function validarCaso258(){
	f=document.form1;
	
	if(f.txt_intervalo.value*f.txt_plazo.value>360){
		alert("Usted ha excedido los 360 días de duración total del crédito");
		f.txt_intervalo.value="";
		f.txt_plazo.value="";
		f.txt_intervalo.focus();
		return false;
	}
	
	if(!valida_numero(f.txt_intervalo.value)){
		alert("Por favor ingrese un intervalo válido");
		f.txt_intervalo.value="";
		f.txt_intervalo.focus();
		return false;
	}
	if((f.txt_intervalo.value<30) || (f.txt_intervalo.value>180)){
		alert("Por favor ingrese un intervalo en el rango de 30 a 180");
		f.txt_intervalo.value="";
		f.txt_intervalo.focus();
		return false;
	}
	if(f.txt_micro.value==""){
		alert("Por favor ingrese un microseguro");
		f.txt_micro.value="";
		f.txt_micro.focus();
		return false;
	}
	if((f.txt_micro.value<0) || (f.txt_micro.value>1)){
		alert("Por favor ingrese un microseguro válido ( 0 ó 1 )");
		f.txt_micro.value="";
		f.txt_micro.focus();
		return false;
	}
	
	if(f.txt_monto.value==""){
		alert("Por favor ingrese un monto ");
		f.txt_monto.value="";
		f.txt_monto.focus();
		return false;
	}
	if(f.txt_monto.value<=-1){
		alert("Por favor ingrese un monto positivo");
		f.txt_monto.value="";
		f.txt_monto.focus();
		return false;
	}
	/*if(!valida_numero(f.txt_prestamo.value)){
		alert("INGRESE UN PRESTAMO VÁLIDO");
		f.txt_prestamo.value="";
		f.txt_prestamo.focus();
		return false;
	}*/
	if(f.txt_prestamo.value==""){
		alert("Por favor ingrese un prestamo ");
		f.txt_prestamo.value="";
		f.txt_prestamo.focus();
		return false;
	}
	if(f.txt_prestamo.value<=-1){
		alert("Por favor ingrese un prestamo positivo");
		f.txt_prestamo.value="";
		f.txt_prestamo.focus();
		return false;
	}
	/*if(!valida_numero(f.txt_TEA.value)){
		alert("INGRESE UN TEA VÁLIDO");
		f.txt_TEA.value="";
		f.txt_TEA.focus();
		return false;
	}*/
	if(f.txt_TEA.value==""){
		alert("Por favor ingrese un TEA ");
		f.txt_TEA.value="";
		f.txt_TEA.focus();
		return false;
	}
	if(f.txt_TEA.value<=-1){
		alert("Por favor ingrese un TEA positivo");
		f.txt_TEA.value="";
		f.txt_TEA.focus();
		return false;
	}
	/*if(!valida_numero(f.txt_s_i_Tmensual.value)){
		alert("INGRESE UN SEGURO CONTRA INCENDIO MENSUAL VÁLIDO");
		f.txt_s_i_Tmensual.value="";
		f.txt_s_i_Tmensual.focus();
		return false;
	}*/
	if(f.txt_s_i_Tmensual.value==""){
		alert("Por favor ingrese un seguro contra incendios(tasa mensual)");
		f.txt_s_i_Tmensual.value="";
		f.txt_s_i_Tmensual.focus();
		return false;
	}
	if(f.txt_s_i_Tmensual.value<=-1){
		alert("Por favor ingrese un seguro contra incendios(tasa mensual) positivo");
		f.txt_s_i_Tmensual.value="";
		f.txt_s_i_Tmensual.focus();
		return false;
	}/*
	if(!valida_numero(f.txt_s_d_tMensual.value)){
		alert("INGRESE UN Seguro de Desgravamen Tasa Mensual VÁLIDO");
		f.txt_s_d_tMensual.value="";
		f.txt_s_d_tMensual.focus();
		return false;
	}*/
	if(f.txt_s_d_tMensual.value==""){
		alert("Por favor ingrese un seguro de desgravamen(tasa mensual)");
		f.txt_s_d_tMensual.value="";
		f.txt_s_d_tMensual.focus();
		return false;
	}
	if(f.txt_s_d_tMensual<=-1){
		alert("Por favor ingrese un seguro de desgravamen (tasa mensual) positivo");
		f.txt_s_d_tMensual.value="";
		f.txt_s_d_tMensual.focus();
		return false;
	}
	/*if(!valida_numero(f.txt_plazo.value)){
		alert("INGRESE UN NÚMERO DE CUOTAS VÁLIDO");
		f.txt_plazo.value="";
		f.txt_plazo.focus();
		return false;
	}*/
	if(f.txt_plazo.value==""){
		alert("Por favor ingrese un número de cuotas");
		f.txt_plazo.value="";
		f.txt_plazo.focus();
		return false;
	}
	if(f.txt_plazo.value<=-1){
		alert("Por favor ingrese un número de cuotas positivo");
		f.txt_plazo.value="";
		f.txt_plazo.focus();
		return false;
	}
	if(!valida_numero(f.txt_plazo.value)){
		alert("Por favor ingrese un número de cuotas válido");
		f.txt_plazo.value="";
		f.txt_plazo.focus();
		return false;
	}
	
	if((f.txt_plazo.value<2) || (f.txt_plazo.value>12)){
		alert("Por favor ingrese un número de cuotas en el rango de 30 a 180");
		f.txt_plazo.value.value="";
		f.txt_plazo.value.focus();
		return false;
	}
	/*if(!valida_numero(f.txt_p_gracia.value)){
		alert("INGRESE UN PERÍODO DE GRACIA VÁLIDO ");
		f.txt_p_gracia.value="";
		f.txt_p_gracia.focus();
		return false;
	}*/
	if(f.txt_p_gracia.value==""){
		alert("Por favor ingrese un período de gracia");
		f.txt_p_gracia.value="";
		f.txt_p_gracia.focus();
		return false;
	}
	if(!valida_numero(f.txt_p_gracia.value)){
		alert("Por favor ingrese un período de gracia válido");
		f.txt_p_gracia.value="";
		f.txt_p_gracia.focus();
		return false;
	}
	if(f.txt_p_gracia.value<=-1){
		alert("Por favor ingrese un período de gracia positivo");
		f.txt_p_gracia.value="";
		f.txt_p_gracia.focus();
		return false;
	}	
	f.validado2.value="on";
	f.submit();
}








function validar(){
	f=document.form1;
	
	
	if(f.txt_micro.value==""){
		alert("Por favor ingrese un microseguro");
		f.txt_micro.value="";
		f.txt_micro.focus();
		return false;
	}
	if((f.txt_micro.value<0) || (f.txt_micro.value>1)){
		alert("Por favor ingrese un microseguro válido ( 0 ó 1 )");
		f.txt_micro.value="";
		f.txt_micro.focus();
		return false;
	}
	
	if(f.txt_monto.value==""){
		alert("Por favor ingrese un monto ");
		f.txt_monto.value="";
		f.txt_monto.focus();
		return false;
	}
	if(f.txt_monto.value<=-1){
		alert("Por favor ingrese un monto positivo");
		f.txt_monto.value="";
		f.txt_monto.focus();
		return false;
	}
	/*if(!valida_numero(f.txt_prestamo.value)){
		alert("INGRESE UN PRESTAMO VÁLIDO");
		f.txt_prestamo.value="";
		f.txt_prestamo.focus();
		return false;
	}*/
	if(f.txt_prestamo.value==""){
		alert("Por favor ingrese un prestamo ");
		f.txt_prestamo.value="";
		f.txt_prestamo.focus();
		return false;
	}
	if(f.txt_prestamo.value<=-1){
		alert("Por favor ingrese un prestamo positivo");
		f.txt_prestamo.value="";
		f.txt_prestamo.focus();
		return false;
	}
	/*if(!valida_numero(f.txt_TEA.value)){
		alert("INGRESE UN TEA VÁLIDO");
		f.txt_TEA.value="";
		f.txt_TEA.focus();
		return false;
	}*/
	if(f.txt_TEA.value==""){
		alert("Por favor ingrese un TEA ");
		f.txt_TEA.value="";
		f.txt_TEA.focus();
		return false;
	}
	if(f.txt_TEA.value<=-1){
		alert("Por favor ingrese un TEA positivo");
		f.txt_TEA.value="";
		f.txt_TEA.focus();
		return false;
	}
	/*if(!valida_numero(f.txt_s_i_Tmensual.value)){
		alert("INGRESE UN SEGURO CONTRA INCENDIO MENSUAL VÁLIDO");
		f.txt_s_i_Tmensual.value="";
		f.txt_s_i_Tmensual.focus();
		return false;
	}*/
	if(f.txt_s_i_Tmensual.value==""){
		alert("Por favor ingrese un seguro contra incendios(tasa mensual)");
		f.txt_s_i_Tmensual.value="";
		f.txt_s_i_Tmensual.focus();
		return false;
	}
	if(f.txt_s_i_Tmensual.value<=-1){
		alert("Por favor ingrese un seguro contra incendios(tasa mensual) positivo");
		f.txt_s_i_Tmensual.value="";
		f.txt_s_i_Tmensual.focus();
		return false;
	}/*
	if(!valida_numero(f.txt_s_d_tMensual.value)){
		alert("INGRESE UN Seguro de Desgravamen Tasa Mensual VÁLIDO");
		f.txt_s_d_tMensual.value="";
		f.txt_s_d_tMensual.focus();
		return false;
	}*/
	if(f.txt_s_d_tMensual.value==""){
		alert("Por favor ingrese un seguro de desgravamen(tasa mensual)");
		f.txt_s_d_tMensual.value="";
		f.txt_s_d_tMensual.focus();
		return false;
	}
	if(f.txt_s_d_tMensual<=-1){
		alert("Por favor ingrese un seguro de desgravamen (tasa mensual) positivo");
		f.txt_s_d_tMensual.value="";
		f.txt_s_d_tMensual.focus();
		return false;
	}
	/*if(!valida_numero(f.txt_plazo.value)){
		alert("INGRESE UN NÚMERO DE CUOTAS VÁLIDO");
		f.txt_plazo.value="";
		f.txt_plazo.focus();
		return false;
	}*/
	if(f.txt_plazo.value==""){
		alert("Por favor ingrese un número de cuotas");
		f.txt_plazo.value="";
		f.txt_plazo.focus();
		return false;
	}
	if(f.txt_plazo.value<=-1){
		alert("Por favor ingrese un número de cuotas positivo");
		f.txt_plazo.value="";
		f.txt_plazo.focus();
		return false;
	}	
	if(!valida_numero(f.txt_plazo.value)){
		alert("Por favor ingrese un número de cuotas válido");
		f.txt_plazo.value="";
		f.txt_plazo.focus();
		return false;
	}
	
	/*if(!valida_numero(f.txt_p_gracia.value)){
		alert("INGRESE UN PERÍODO DE GRACIA VÁLIDO ");
		f.txt_p_gracia.value="";
		f.txt_p_gracia.focus();
		return false;
	}*/
	if(f.txt_p_gracia.value==""){
		alert("Por favor ingrese un período de gracia");
		f.txt_p_gracia.value="";
		f.txt_p_gracia.focus();
		return false;
	}
	if(!valida_numero(f.txt_p_gracia.value)){
		alert("Por favor ingrese un período de gracia válido");
		f.txt_p_gracia.value="";
		f.txt_p_gracia.focus();
		return false;
	}
	if(f.txt_p_gracia.value<=-1){
		alert("Por favor ingrese un período de gracia positivo");
		f.txt_p_gracia.value="";
		f.txt_p_gracia.focus();
		return false;
	}	
	f.validado.value="on";
	f.submit();
}

