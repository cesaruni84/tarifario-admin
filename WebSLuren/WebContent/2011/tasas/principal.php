<?php
include "clases/config.php";
include "clases/class.query.php";
include "clases/calculos.php";

$calculos = new calculos();
$Query = new Query();
$Link = $Query->conexion();
$cad="SELECT * FROM productos_activos";
$query=$Query->mQuery($cad,$Link);
	
if(isset($_POST['combo1']))
{		
$sql="select tp.codigo_cuenta, tp.id ,tp.tipo, tp.tipo_empresa from tipo_cuenta tp, productos_activos pa, producto_cuenta pc 
where tp.id=pc.codigo_cuenta
and pa.codigo=pc.codigo
and pa.codigo='".$_POST['combo1']."'";
$res=$Query->mQuery($sql,$Link);
}
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Simuladores</title>
<!--/*******************************************************************/-->
<link href="css/calendario.css" type="text/css" rel="stylesheet">
<link href="css/estilos.css" type="text/css" rel="stylesheet">
<!--/*******************************************************************/-->
<script src="js/calendar.js" type="text/javascript"></script>
<script src="js/calendar-es.js" type="text/javascript"></script>
<script src="js/calendar-setup.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="js/validacion.js"></script>
<!--/*******************************************************************/-->
<link href="css/flexcrollstyles.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="js/flexcroll.js"></script>

<script type='text/javascript' src="js/jquery.js"></script>
<script type='text/javascript' src="js/imprime.js"></script>
<script language="javascript" type="text/javascript">

	function calcular(){
		document.form1.submit();
	}
	function enviar(val)
	{
		var fr = document.getElementById("form1")
		fr.submit();//envia la info de fr
	}
	function imprime()
	{
//		$("div#mycustomscroll").printArea([options]);
//		$("div#imprimecabezera").printArea();
		$("div#imprimmm").printArea();
	}
	function imprimeformu()
	{
//		$("div#mycustomscroll").printArea([options]);
		$("div#imprimecabezera").printArea();
		//$("div#imprimmm").printArea();
	}
</script>
<style type="text/css">
#mycustomscroll {
	/* Typical fixed height and fixed width example */
	width: 900px;
	height: 550px;
	overflow: auto;
	/* IE overflow fix, position must be relative or absolute*/
	position: relative;
	background-color: #ffffff;
	/*margin: 0.3em auto;*/
	padding: 0px;
	margin-bottom:20px;
}
#imprimmm
{
	width: 880px;
	height: auto;
	padding:0; margin:0;
	position: relative;
}
.cabec
{
width:870px;
margin:0 0 0 0px; 
/*border:1px solid #FF0000*/
}
</style>
</head>
<body >
<?php require_once 'validaciones.php'; ?>

	<div class="titulo">Simuladores:</div>
	<br/>
    <div class="clear"></div>
    <form action="principal.php" method="post" id="form1" name="form1" >
<div id="imprimecabezera">    
            
            <div>
            	
                <select class="cb1" name="combo1" onChange="enviar(this.value);" >
                <option >Seleccione una opcion</option>
                <?php
                while($fila=$Query->fArray($query))
                {    
					if( $_POST["combo1"]==$fila['codigo']){
					$sele='selected="selected"';
					}else{
					$sele="";
					}
                	?>
                	<option  value="<?= $fila['codigo']; ?>" <?= $sele; ?>><?= $fila['nombre']; ?></option>
                <?php 
				}?>
                </select>
               
                <br><br>
                
                <select class="cb2" name="combo2" onChange="enviar(this.value);" >
                <option>Seleccione una opcion</option>
                <?php
                while($fila2=$Query->fArray($res))
                {    	
					if( $_POST["combo2"]==$fila2['id']){
					$sele2='selected="selected"';
					}else{
					$sele2="";
					}	
                ?>     
                <option value="<?= $fila2['id']; ?>" <?= $sele2; ?> ><?= $fila2['codigo_cuenta'] . " - "."   ".$fila2['tipo']. "   "." - "."   ".$fila2['tipo_empresa']; ?></option>
                <?php		
                }	
                ?>        
                </select> 	             
         </div>
         
        <?php
			if(isset($_POST['combo2']))
			{
				if($_POST['combo2']!=6){
				/*	echo "combo Uno - ".$_POST['combo1'];
					echo "  combo Dos - ".$_POST['combo2'];
					echo "<br>";*/
				$fecha_desem=@$_POST["txt_fec_desem"];
				$dia_desem = substr($fecha_desem, 0, 2);
				$mes_desem = substr($fecha_desem, 3, -5);
				$anio_desem = substr($fecha_desem, 6, 9);
				
				$diaSuma=$dia_desem+@$_POST["txt_p_gracia"];//para el txt 12
				$mes_desem=$mes_desem+0;
					if($mes_desem==12 and $diaSuma>31){
						$diaSuma=($diaSuma%31);
						$anio_desem=$anio_desem+1;
						$mes_desem="01";
					}
				$var12=$diaSuma;//bota solo el num del esa fecha
				
				$fecha_fin_per_gracia=$calculos->formateo(2,$diaSuma)."-".$calculos->formateo(2,$mes_desem)."-".$anio_desem;
				
				$fecha2=@$_POST["txt_f_primera_cuota"];/*estan string!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
				$dia_primeraCuota=substr($fecha2, 0, 2);
				$mes = substr($fecha2, 3, -5);//mes de la 1era cuota 0!!!
				$mes=$mes+0; //para que sea entero
				$anio = substr($fecha2, 6, 9);//año de la 1era cuota
				
					if($mes==12 and $dia_primeraCuota>31){
						$dia_primeraCuota=($dia_primeraCuota%31);
						$anio=$anio+1;
						$mes="01";
					} 
					?>


					<table align="left" width="400" cellpadding="4" cellspacing="0" border="0" id="tab">

                        <tr>
                        <td width="230" align="left">Microseguro (0 &oacute; 1)</td>
                        <td width="170" align="left"><input class="textos" type="text" name="txt_micro" value="<?= @$_POST["txt_micro"]?>"/></td>
                        </tr>
                        <tr>
                        <td width="230" align="left">Monto de Construcci&oacute;n</td>
                        <td width="170" align="left"><input class="textos" type="text" name="txt_monto" value="<?= @$_POST["txt_monto"]?>"  id="monto1" onKeyUp="comas(this.id)"/></td>
                        </tr>
                        
                        <tr>
                        <td align="left">Pr&eacute;stamo</td>
                        <td align="left"><input class="textos" type="text" name="txt_prestamo" value="<?= @$_POST["txt_prestamo"]?>" id="prestamo1" onKeyUp="comas(this.id)"/></td>
                        </tr>
                        
                        <tr>
                        <td align="left">Tasa de Interés Efectiva anual (TEA).</td>
                        <td align="left"><input class="textos" type="text" name="txt_TEA" value="<?= @$_POST["txt_TEA"]?>"  id="tea1" onKeyUp="comas(this.id)"/>%&nbsp;*</td>
                        </tr>
                        
                        <tr>
                        <td align="left">Seguro contra Todo Riesgo Tasa Mensual</td>
                        <td align="left"><input class="textos" type="text" name="txt_s_i_Tmensual" value="<?= @$_POST["txt_s_i_Tmensual"]?>"  id="imensual1" onKeyUp="comas(this.id)"/>%&nbsp;*</td>
                        </tr>
                        
                        <tr>
                        <td align="left">Seguro de Desgravamen Tasa Mensual</td>
                        <td align="left"><input class="textos" type="text" name="txt_s_d_tMensual" value="<?= @$_POST["txt_s_d_tMensual"]?>"  id="dmensual1" onKeyUp="comas(this.id)"/>%&nbsp;*</td>
                        </tr>
                        
                        <tr>
                        <td align="left">Plazo (cuotas)</td>
                        <td align="left"><input class="textos" type="text" name="txt_plazo" value="<?= @$_POST["txt_plazo"]?>" id="ncuotas_p" /></td>
                        </tr>
                        
                        <tr>
                        <td align="left">Fecha de desembolso</td>
                        <td align="left">
                        <?php /*?> <input  type="text" name="txt_fec_desem"   value="<?= @$_POST["txt_fec_desem"]?>"/><?php */?>
                        <input type="text" name="txt_fec_desem" id="txt_fec_desem" class="textos" value="<?= @$_POST["txt_fec_desem"]?>" readonly /> 
                        <img src="ima/calendario.png" width="16" height="16" border="0" title="Fecha Inicial" id="lanzador">
                        <!-- script que define y configura el calendario--> 
                        <script type="text/javascript"> 
                           Calendar.setup({ 
                            inputField     :    "txt_fec_desem",     // id del campo de texto 
                             ifFormat     :     "%d-%m-%Y",     // formato de la fecha que se escriba en el campo de texto 
                             button     :    "lanzador"     // el id del botón que lanzará el calendario 
                        }); 
                        </script>         
                        </td>
                        </tr>
                        
                        <tr>
                        <td align="left">Periodo de gracia (dias)</td>
                        <td align="left"><input class="textos" type="text" name="txt_p_gracia" id="txt_p_gracia" value="<?= @$_POST["txt_p_gracia"]?>"/></td>
                        </tr>
                        
                        <tr>
                        <td align="left">Fecha de pago de la primera cuota</td>
                        <td align="left">
                        <?php /*?><input  type="text" name="txt_f_primera_cuota" value="<?= @$_POST["txt_p_gracia"]?>"/><?php */?>
                        <input type="text" name="txt_f_primera_cuota" id="txt_f_primera_cuota" class="textos" value="<?= @$_POST["txt_f_primera_cuota"]?>" readonly /> 
                        <img src="ima/calendario.png" width="16" height="16" border="0" title="Fecha Inicial" id="lanzador2">
                        <!-- script que define y configura el calendario--> 
                        <script type="text/javascript"> 
                           Calendar.setup({ 
                            inputField     :    "txt_f_primera_cuota",     // id del campo de texto 
                             ifFormat     :     "%d-%m-%Y",     // formato de la fecha que se escriba en el campo de texto 
                             button     :    "lanzador2"     // el id del botón que lanzará el calendario 
                        }); 
                        </script>          
                        </td>
                        </tr>
                        
                        <tr>
                            <td>
                            
                            <p class="leyenda">
                            NOTA:<?= "<br/>";?>
                            -En caso de ingresar decimales, ingresar "." *<?= "<br/>";?>
                            -No ingresar espacios en blanco.<?= "<br/>";?>
                            -No ingresar  n&uacute;meros como texto.<?= "<br/>";?>
                            -Las cuotas son referenciales, sujetas a calificación y a la fecha de desembolso del crédito.


                            </p>
                
                            </td>
                        
                        </tr>
                        
                       <tr >
                        <td colspan="2"> 
                        <p class="leyenda">Para visualizar el cronograma de pagos presione "calcular"</p>
                        </td>
                        </tr>
                        
                        <tr>
                        <td colspan="2" align="center" height="40px">
                        <input name="btn_calcular" type="button" value="calcular" onClick="validar();">
                        <input type="hidden" value="off" name="validado"/>
                        </td>
                        </tr>    
                       
                        </tr>
					</table><!--//////////////////////////////////**********************************************-->
</div>				
				 
				<div class="clear"></div>
<!--
<div class="micabezera">
     <table align="left" width="" cellspacing="0" cellpadding="0" border="0" class="tab_micro">
		<tr class="texto_cabecera_tabla" height="60">
		<td width="47" align="center" class="borde_tabla1">N&deg; cuota</td> 
		<td width="87" align="center" class="borde_tabla1">Fecha<br /> de pago</td> 
		<td width="34" align="center" class="borde_tabla1">N&deg; de d&iacute;as</td> 
	
		<td width="84" align="center" class="borde_tabla1">Saldo<br /> capital</td> 
		<td width="87" align="center" class="borde_tabla1">Amortizaci&oacute;n <br /> intereses</td> 
		<td width="89" align="center" class="borde_tabla1">Seguro <br /> desgravamen</td> 
		<td width="87" align="center" class="borde_tabla1">Amortizaci&oacute;n <br />capital</td> 
		<td width="76" align="center" class="borde_tabla1">Seguro contra<br /> incendios</td> 
		<td width="86" align="center" class="borde_tabla1">Microseguro</td> 
		<td width="67" align="center" class="borde_tabla1">Subtotal</td> 
		<td width="40" align="center" class="borde_tabla1">ITF</td>  
		<td width="73" align="center" class="borde_tabla2">Cuota a pagar</td>
        </tr>
	</table>
</div>
-->
			<!--CALCULOS!!!!!!!!!!!! -->  
			<?php
			$var5= $calculos->calcula_seg_inc_tAnual(@$_POST["txt_s_i_Tmensual"]);
			
			$var6= $calculos->calcula_m_f_segIncendio($var5,@$_POST["txt_monto"]);

			$var8= $calculos->calcula_Seg_Desgravamen_tAnual(@$_POST["txt_s_d_tMensual"]);
			
			$var13=$calculos->calcula_interes_p_gracia(@$_POST["txt_prestamo"],@$_POST["txt_TEA"],$var8,@$_POST["txt_p_gracia"]);
			
			$var14= $calculos->calcula_monto_capitalizado(@$_POST["txt_prestamo"],$var13 );
	
			//$var15=501.87;								//no se usaaaa
	//PARA EL TIR!!!!!*********************************************************************
	 /*       $var16=3.08;			//multiplicar por 100 es %!!!
			
			$var17=(pow(1+($var16/100),12)-1)*100;
			if($_POST['combo2']==5){
			$var17=(pow(1+($var16/100),1.5)-1)*100;
			}
			if($_POST['combo2']==6){
			$var17=(pow(1+($var16/100),1)-1)*100;
			}
			if($_POST['combo2']==11){
			$var17=(pow(1+($var16/100),4)-1)*100;
			}*/
			$iy=$calculos->calcula_iy(@$_POST["txt_TEA"],$var8,$_POST['combo2']);
			$var18=$calculos->calcula_cuota_sin_ajustar($var14,$iy,@$_POST["txt_plazo"]);
			$var18=$calculos->redondeado ($var18, 2);
			$var18=$calculos->calcula_cuota_sin_ajustar($var14,@$_POST["txt_TEA"]/100,@$_POST["txt_plazo"]);
			$var18=$calculos->redondeado ($var18, 2);
			
			
			//echo "<strong>CUOTA SIN AJUSTAR</strong>  ".$var18;	
			$primer_mes=$mes;//salvamos el mes y el año para las iterac de las otras tablas!!!!!!!!!!!!!!!!!!!
			$primer_anio=$anio;
			
			}else{/******************************************************************************************************************************CASO 258******/
			$fecha_desem=@$_POST["txt_fec_desem"];
				$dia_desem = substr($fecha_desem, 0, 2);
				$mes_desem = substr($fecha_desem, 3, -5);
				$anio_desem = substr($fecha_desem, 6, 9);
				
				$diaSuma=$dia_desem+@$_POST["txt_p_gracia"];//para el txt 12
				$mes_desem=$mes_desem+0;
					if($mes_desem==12 and $diaSuma>31){
						$diaSuma=($diaSuma%31);
						$anio_desem=$anio_desem+1;
						$mes_desem="01";
					}
				$var12=$diaSuma;//bota solo el num del esa fecha
				
				$fecha_fin_per_gracia=$calculos->formateo(2,$diaSuma)."-".$calculos->formateo(2,$mes_desem)."-".$anio_desem;
				
				$fecha2=@$_POST["txt_f_primera_cuota"];/*estan string!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
				$dia_primeraCuota=substr($fecha2, 0, 2);
				$mes = substr($fecha2, 3, -5);//mes de la 1era cuota 0!!!
				$mes=$mes+0; //para que sea entero
				$anio = substr($fecha2, 6, 9);//año de la 1era cuota
				
					if($mes==12 and $dia_primeraCuota>31){
						$dia_primeraCuota=($dia_primeraCuota%31);
						$anio=$anio+1;
						$mes="01";
					} 

					?>
					<table align="left" width="400" cellpadding="4" cellspacing="0" border="0" id="tab">

					<tr>
					<td width="230" align="left">Microseguro (0 &oacute; 1)</td>
					<td width="170" align="left"><input class="textos" type="text" name="txt_micro" value="<?= @$_POST["txt_micro"]?>"/></td>
					</tr>
					<tr>
					<td width="230" align="left">Monto de Construcci&oacute;n</td>
					<td width="170" align="left"><input class="textos" type="text" name="txt_monto" value="<?= @$_POST["txt_monto"]?>" id="monto" onKeyUp="comas(this.id)"/></td>
					</tr>
					
					<tr>
					<td align="left">Pr&eacute;stamo</td>
					<td align="left"><input class="textos" type="text" name="txt_prestamo" value="<?= @$_POST["txt_prestamo"]?>" id="prest" onKeyUp="comas(this.id)"/></td>
					</tr>
					
					<tr>
					<td align="left">TEA</td>
					<td align="left"><input class="textos" type="text" name="txt_TEA" value="<?= @$_POST["txt_TEA"]?>" id="tea1" onKeyUp="comas(this.id)"/>%&nbsp;*</td>
					</tr>
					
					<tr>
					<td align="left">Seguro contra Incendio Tasa Mensual</td>
					<td align="left"><input class="textos" type="text" name="txt_s_i_Tmensual" value="<?= @$_POST["txt_s_i_Tmensual"]?>" id="i_mensual" onKeyUp="comas(this.id)"/>%&nbsp;*</td>
					</tr>
					
					<tr>
					<td align="left">Seguro de Desgravamen Tasa Mensual</td>
					<td align="left"><input class="textos" type="text" name="txt_s_d_tMensual" value="<?= @$_POST["txt_s_d_tMensual"]?>" id=" d_mensual" onKeyUp="comas(this.id)"/>%&nbsp;*</td>
					</tr>
					
					<tr>
					<td align="left">Plazo (cuotas)</td>
					<td align="left"><input class="textos" type="text" name="txt_plazo" value="<?= @$_POST["txt_plazo"]?>" id="ncuotas_p" /></td>
					</tr>
					
					<tr>
					<td align="left">Intervalo de días entre las cuotas(30 a 180 dias)</td>
					<td align="left"><input class="textos" type="text" name="txt_intervalo" value="<?= @$_POST["txt_intervalo"]?>"/></td>
					</tr>
					
					<tr>
					<td align="left">Fecha de desembolso</td>
					<td align="left">
					<?php /*?> <input  type="text" name="txt_fec_desem"  value="<?= @$_POST["txt_fec_desem"]?>"/><?php */?>
					<input type="text" name="txt_fec_desem" id="txt_fec_desem" class="textos" value="<?= @$_POST["txt_fec_desem"]?>" readonly /> 
					<img src="ima/calendario.png" width="16" height="16" border="0" title="Fecha Inicial" id="lanzador">
					<!-- script que define y configura el calendario--> 
					<script type="text/javascript"> 
					   Calendar.setup({ 
						inputField     :    "txt_fec_desem",     // id del campo de texto 
						 ifFormat     :     "%d-%m-%Y",     // formato de la fecha que se escriba en el campo de texto 
						 button     :    "lanzador"     // el id del botón que lanzará el calendario 
					}); 
					</script>         
					</td>
					</tr>
					
					<tr>
					<td align="left">Periodo de gracia (dias)</td>
					<td align="left"><input class="textos" type="text" name="txt_p_gracia" id="txt_p_gracia" value="<?= @$_POST["txt_p_gracia"]?>"/></td>
					</tr>
					
					<tr>
					<td align="left">Fecha de pago de la primera cuota</td>
					<td align="left">
					<?php /*?><input  type="text" name="txt_f_primera_cuota" value="<?= @$_POST["txt_p_gracia"]?>"/><?php */?>
					<input type="text" name="txt_f_primera_cuota" class="textos" id="txt_f_primera_cuota" value="<?= @$_POST["txt_f_primera_cuota"]?>" readonly /> 
					<img src="ima/calendario.png" width="16" height="16" border="0" title="Fecha Inicial" id="lanzador2">
					<!-- script que define y configura el calendario--> 
					<script type="text/javascript"> 
					   Calendar.setup({ 
						inputField     :    "txt_f_primera_cuota",     // id del campo de texto 
						 ifFormat     :     "%d-%m-%Y",     // formato de la fecha que se escriba en el campo de texto 
						 button     :    "lanzador2"     // el id del botón que lanzará el calendario 
					}); 
					</script>          
					</td>
					</tr>
                    <tr>
                            <td colspan="2">
                            
                            <p class="leyenda">
                            NOTA:<?= "<br/>";?>
                            -En caso de ingresar decimales, ingresar "." *<?= "<br/>";?>
                            -No ingresar espacios en blanco.<?= "<br/>";?>
                            -No ingresar  n&uacute;meros como texto.
                            </p>
                
                            </td>
                        
                    </tr>
                    <tr >
                    <td colspan="2">
                    <p class="leyenda">Para visualizar el cronograma de pagos presione "calcular"</p>
                    </td>
                    </tr>
					<tr>
                        <td colspan="2" align="center" height="40">
                        <input name="btn_calcular" type="button" value="calcular" onClick="validarCaso258();">
                        <input type="hidden" value="off" name="validado2"/>
                        </td>
					</tr>    
					
				</table><!--//////////////////////////////////**********************************************-->

</div>
				<div class="clear"></div>
<!--
<div class="micabezera">
     <table align="left" width="" cellspacing="0" cellpadding="0" border="0" class="tab_micro">
		<tr class="texto_cabecera_tabla" height="60">
		<td width="47" align="center" class="borde_tabla1">N&deg; cuota</td> 
		<td width="87" align="center" class="borde_tabla1">Fecha<br /> de pago</td> 
		<td width="34" align="center" class="borde_tabla1">N&deg; de d&iacute;as</td> 
	
		<td width="84" align="center" class="borde_tabla1">Saldo<br /> capital</td> 
		<td width="87" align="center" class="borde_tabla1">Amortizaci&oacute;n <br /> intereses</td> 
		<td width="89" align="center" class="borde_tabla1">Seguro <br /> desgravamen</td> 
		<td width="87" align="center" class="borde_tabla1">Amortizaci&oacute;n <br />capital</td> 
		<td width="76" align="center" class="borde_tabla1">Seguro contra<br /> incendios</td> 
		<td width="86" align="center" class="borde_tabla1">Microseguro</td> 
		<td width="67" align="center" class="borde_tabla1">Subtotal</td> 
		<td width="40" align="center" class="borde_tabla1">ITF</td>  
		<td width="73" align="center" class="borde_tabla2">Cuota a pagar</td>
        </tr>
	</table>
</div>
-->

			
			<!--CALCULOS!!!!!!!!!!!! -->  
			<?php
			$var5= $calculos->calcula_seg_inc_tAnual(@$_POST["txt_s_i_Tmensual"]);
			
			$var6= $calculos->calcula_m_f_segIncendio($var5,@$_POST["txt_monto"]);
			
			$var8= $calculos->calcula_Seg_Desgravamen_tAnual(@$_POST["txt_s_d_tMensual"]);
			
			$var13=$calculos->calcula_interes_p_gracia(@$_POST["txt_prestamo"],@$_POST["txt_TEA"],$var8,@$_POST["txt_p_gracia"]);
			
			$var14= $calculos->calcula_monto_capitalizado(@$_POST["txt_prestamo"],$var13 );
	
			//$var15=501.87;								//no se usaaaa
	//PARA EL TIR!!!!!*********************************************************************
	 /*       $var16=3.08;			//multiplicar por 100 es %!!!
			
			$var17=(pow(1+($var16/100),12)-1)*100;
			if($_POST['combo2']==5){
			$var17=(pow(1+($var16/100),1.5)-1)*100;
			}
			if($_POST['combo2']==6){
			$var17=(pow(1+($var16/100),1)-1)*100;
			}
			if($_POST['combo2']==11){
			$var17=(pow(1+($var16/100),4)-1)*100;
			}*/
			$iy=$calculos->calcula_iy(@$_POST["txt_TEA"],$var8,$_POST['combo2']);
//			$var18=$calculos->calcula_cuota_sin_ajustar($var14,$iy,@$_POST["txt_plazo"]);
			$rd_iy=$calculos->redondeado ($iy, 2);
			$var18=$calculos->calcula_cuota_sin_ajustar($var14,$rd_iy,@$_POST["txt_plazo"]);
			$var18=$calculos->redondeado ($var18, 2);
			/*if($_POST['combo2']==6){*/
			$var18=$calculos->calcula_cuota_sin_ajustar($var14,@$_POST["txt_TEA"]/100,@$_POST["txt_plazo"]);
			$var18=$calculos->redondeado ($var18, 2);
			
			
			//echo "<strong>CUOTA SIN AJUSTAR</strong>  ".$var18;	
			$primer_mes=$mes;//salvamos el mes y el año para las iterac de las otras tablas!!!!!!!!!!!!!!!!!!!
			$primer_anio=$anio;
			}
			
			//}
				switch(@$_POST['combo2']){
					case 1: 
					if(@$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break; 
					
					case 2: 
					if(@$_POST["validado"]=="on"){
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					
					}break; 
					case 3: 
					if(@$_POST["validado"]=="on"){
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";	
					}break; 
					case 4 :
					if(@$_POST["validado"]=="on"){
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";	
					}break; 
					case 5: 
					if( @$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					
					}break; 
					case 6: 
					if( @$_POST["validado2"]=="on"){
					echo "<div id='mycustomscroll' class='flexcroll'>";
					include_once "plantillas/credito258.php";
					echo "</div>";	
					}break; 
					case 7: 
					if( @$_POST["validado"]=="on"){
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break; 
					case 8: 
					if( @$_POST["validado"]=="on"){
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break; 
					case 9: 
					if(@$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 10: 
					if( @$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 11: 
					if( @$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 12: 
					if( @$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 13: 
					if( @$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 14: 
					if( @$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 15: 
					if( @$_POST["validado"]=="on"){
						
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 16: 
					if(@$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 17: 
					if( @$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 18: 
					if(@$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 19: 
					if( @$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 20: 
					if( @$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;
					case 21: 
					if( @$_POST["validado"]=="on"){	
					echo "<div id='mycustomscroll' class='flexcroll'>";
					echo "<div id='imprimmm'>";
						include_once "plantillas/micro0.php";
					echo "</div>";
					echo "</div>";
					}break;                
				}//cierra switch
			}//fin dl if combo2
    	?> 
     <?php if(@$_POST["validado"]=="on" || @$_POST["validado2"]=="on"){
	 
	?>   
    <div align="left">   
    <div class="leyenda" align="left">*Para imprimir el cronograma pulsar "Imprimir cronograma".</div>
    <br/>
    <input   type="button" name="imprimir" value="Imprimir cronograma" onclick="imprime();"> 
    <br/><br/>
    <div class="leyenda" align="left">*Para imprimir el formulario llenado pulsar "Imprimir formulario".</div>
    <br/>
    <input  type="button" name="imprimirformu" value="Imprimir formulario" onclick="imprimeformu();"> 
    </div>
    <?php 
	 }
	?>
    </form>
</body>
</html>
