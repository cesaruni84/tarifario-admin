<script type="text/javascript">
<?php
if( isset($_POST['combo2']) && $_POST['combo2']!="Seleccione una opcion")
{
$cCodPro="SELECT codigo_cuenta FROM tipo_cuenta WHERE id=" . $_POST['combo2'];
$qCodPro=$Query->mQuery($cCodPro,$Link);
$aCodPro=$Query->fArray($qCodPro);
//echo $aCodPro['codigo_cuenta'];
	switch($aCodPro['codigo_cuenta'])
	{
		case 128:
?>
		var monto_menor = 500;
		var monto_mayor = 100000;
		var p_graciaMax = 2;
		var p_graciaMin = 0;
		var numCuotaMax = 72; // era 72 cuotas
//		var numCuotaMax = 48; // era 72 cuotas
		var numCuotaMin = 13;
		var teaMin=26.82;
		var teaMax=101.22;
		
		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});
		});
<?php
		break;
		case 184:
?>
		var monto_menor = 300;
		var monto_mayor = 20000;
		var p_graciaMax = 0;
		var p_graciaMin = 0;
		var numCuotaMax = 1;
		var numCuotaMin = 1;
		var teaMin=26.82;
		var teaMax=101.22;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});
		});
<?php
		break;
		case 190:
?>
		var monto_menor = 500;
		var monto_mayor = 100000;
		var p_graciaMax = 2;
		var p_graciaMin = 0;
		var numCuotaMax = 72; // era 72 cuotas
//		var numCuotaMax = 48; // era 72 cuotas
		var numCuotaMin = 13;
		var teaMin=19.56;
		var teaMax=42.58;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});
		});
<?php
		break;
		case 191:
?>
		var monto_menor = 300;
		var monto_mayor = 20000;
		var p_graciaMax = 6;
		var p_graciaMin = 0;
		var numCuotaMax = 12;
		var numCuotaMin = 2;
		var teaMin=26.82;
		var teaMax=101.22;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});
		});
<?php
		break;
		case 192:
?>
		var monto_menor = 300;
		var monto_mayor = 200000;
		var p_graciaMax = 6;
		var p_graciaMin = 0;
		var numCuotaMax = 12;
		var numCuotaMin = 2;
		var teaMin=26.82;
		var teaMax=101.22;
		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});
		});
<?php
		break;
		case 193:
?>
		var monto_menor = 300;
		var monto_mayor = 20000;
		var p_graciaMax = 6;
		var p_graciaMin = 0;
/*		var numCuotaMax = 2; // era 72 cuotas son como maximo 2
		var numCuotaMin = 0; //era 13 cuotas son como maximo 0
*/		
		var numCuotaMax = 48; // era 72 cuotas son como maximo 2
		var numCuotaMin = 13; //era 13 cuotas son como maximo 0
		var teaMin=26.82;
		var teaMax=101.22;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 195:
?>
		var monto_menor = 300;
		var monto_mayor = 20000;
		var p_graciaMax = 0;
		var p_graciaMin = 0;
		var numCuotaMax = 1;
		var numCuotaMin = 1;
		var teaMin=34.49;
		var teaMax=101.22;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 208:
?>
		var monto_menor = 5000;
		var monto_mayor = 600000;
		var p_graciaMax = 6;
		var p_graciaMin = 0;
//		var numCuotaMax = 48;// antes 120
		var numCuotaMax = 72;// antes 120
		var numCuotaMin = 13;
		var teaMin=14.03;
		var teaMax=51.11;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 220:
?>
		var monto_menor = 200;
		var monto_mayor = 30000;
		var p_graciaMax = 2;
		var p_graciaMin = 0;
		var numCuotaMax = 12;
		var numCuotaMin = 2;
		var teaMin=19.56;
		var teaMax=42.58;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 221:
?>
		var monto_menor = 200;
		var monto_mayor = 100000;
		var p_graciaMax = 2;
		var p_graciaMin = 0;
		var numCuotaMax = 12;
		var numCuotaMin = 2;
		var teaMin=26.82;
		var teaMax=101.22;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 222:
?>
		var monto_menor = 300;
		var monto_mayor = 300000;
		var p_graciaMax = 2;
		var p_graciaMin = 0;
//		var numCuotaMax = 48; // era 72 cuotas
		var numCuotaMax = 72; // era 72 cuotas
		var numCuotaMin = 13;
		var teaMin=26.82;
		var teaMax=101.22;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 226:
?>
		var monto_menor = 500;
		var monto_mayor = 20000;
		var p_graciaMax = 6;
		var p_graciaMin = 0;
//		var numCuotaMax = 48; // era 72 cuotas
		var numCuotaMax = 72; // era 72 cuotas
		var numCuotaMin = 13;
		var teaMin=26.82;
		var teaMax=101.22;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 252:
?>
		var monto_menor = 1000;
		var monto_mayor = 200000;
		var p_graciaMax = 6;
		var p_graciaMin = 0;
//		var numCuotaMax = 48; // era 72 cuotas
		var numCuotaMax = 72; // era 72 cuotas
		var numCuotaMin = 13;
		var teaMin=26.82;
		var teaMax=60.1;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 258:
?>
		var monto_menor = 10000;
		var monto_mayor = 200000;
		var p_graciaMax = 6;
		var p_graciaMin = 0;
		var numCuotaMax = 12;
		var numCuotaMin = 2;
		var teaMin=26.82;
		var teaMax=60.10;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 275:
?>
		var monto_menor = 300;
		var monto_mayor = 1000;
		var p_graciaMax = 1;
		var p_graciaMin = 0;
		var numCuotaMax = 12;
		var numCuotaMin = 1;
		var teaMin=42.58;
		var teaMax=101.22;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 284:
?>
		var monto_menor = 1000;
		var monto_mayor = 200000;
		var p_graciaMax = 6;
		var p_graciaMin = 0;
		var numCuotaMax = 24;
		var numCuotaMin = 13;
		var teaMin=26.82;
		var teaMax=60.1;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 335:
?>
		var monto_menor = 500;
		var monto_mayor = 45000;
		var p_graciaMax = 2;
		var p_graciaMin = 0;
		var numCuotaMax = 12;
		var numCuotaMin = 2;
		var teaMin=19.56;
		var teaMax=60.1;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 337:
?>
		var monto_menor = 500;
		var monto_mayor = 45000;
		var p_graciaMax = 2;
		var p_graciaMin = 0;
//		var numCuotaMax = 48; // era 72 cuotas
		var numCuotaMax = 72; // era 72 cuotas
		var numCuotaMin = 13;
		var teaMin=19.56;
		var teaMax=60.1;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 349:
?>
		var monto_menor = 30000;
		var monto_mayor = 800000;
		var p_graciaMax = 0;
		var p_graciaMin = 0;
		var numCuotaMax = 1;
		var numCuotaMin = 1;
		var teaMin=26.82;
		var teaMax=60.1;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 358:
?>
		var monto_menor = 500;
		var monto_mayor = 200000;
		var p_graciaMax = 6;
		var p_graciaMin = 0;
//		var numCuotaMax = 48;// antes 120
		var numCuotaMax = 72;// antes 120
		var numCuotaMin = 13;
		var teaMin=26.82;
		var teaMax=60.1;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		case 362:
?>
		var monto_menor = 4500;
		var monto_mayor = 2600000;
		var p_graciaMax = 6;
		var p_graciaMin = 0;
		var numCuotaMax = 12;
		var numCuotaMin = 2;
		var teaMin=26.82;
		var teaMax=60.1;

		$(document).ready(function() {
			$('#prestamo1').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#prest').blur(function(){ 
				if(this.value<monto_menor || this.value>monto_mayor) { alert("Por favor ingrese un monto mayor a " + monto_menor + ", o como maximo " + monto_mayor); this.value=0; }
			});
			$('#txt_p_gracia').blur(function(){ 
				if(this.value<p_graciaMin || this.value>p_graciaMax) { alert("Por favor ingrese un periodo de gracia de " + p_graciaMin + ", o como maximo " + p_graciaMax); this.value=0; }
			});
			$('#ncuotas_p').blur(function(){ 
				if(this.value<numCuotaMin || this.value>numCuotaMax) { alert("El numero de cuotas debe ser de " + numCuotaMin + ", o como maximo " + numCuotaMax); this.value=0; }
			});
			$('#tea1').blur(function(){ 
				if(this.value<teaMin || this.value>teaMax) { alert("El monto de TEA debe ser de " + teaMin + ", o como maximo " + teaMax); this.value=""; }
			});

		});
<?php
		break;
		default: $valor_null = "Estos es un error"; break;
	}
}
?>

		$(document).ready(function() {
			$('#prestamo1').val("");
			$('#prest').val("");
			$('#txt_p_gracia').val("");
			$('#ncuotas_p').val("");
			$('#tea1').val("");
		});
</script>