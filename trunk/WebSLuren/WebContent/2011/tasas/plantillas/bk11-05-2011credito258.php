   <?php
    $num_cuota=0;
    $num=0; //para sacar la fecha anterior en mi array
    $total_amort_cap=0;
    $total_amort_interes=0;
    $total_seguro_desgrav=0;
    $total_cuota=0;
    
    for($i=1;$i<=$_POST["txt_plazo"]; $i++){
    $num=$num+1;
                     
        $num_cuota=$num_cuota+1;						//columna1
   								  
        if($mes>9){										//colum2
        $fec_pago="$dia_primeraCuota-$mes-$anio";
        }else{
        $fec_pago="$dia_primeraCuota-0$mes-$anio";
        }

        if($mes==12){
        $anio=$anio+1;
        $mes=1;
        }else{
        $mes=$mes+1;
        }
		
		
        $almacena_fechas[$num]=$fec_pago;

        if ($num==1){									//colum3
        $num_dias=($calculos->restaFechas( $fecha_fin_per_gracia,$fecha2)) ;
        }else{
			if($num>=2){
				if($num_cuota<=$_POST["txt_plazo"]){
				$num_dias=($calculos->restaFechas($almacena_fechas[$num-1],$almacena_fechas[$num]));
				}else{
				$num_dias=0;
        		}
        	}
        }
		
        if($num==1){									//colum4
        $saldo_cap=$var14;
			}else{
			if($num>=2){
				if($num_cuota<=$_POST["txt_plazo"]){
				$saldo_cap=$almacena_saldoCap[$num-1]-$almacena_amortCapital[$num-1];
				}else{
				$saldo_cap=0;
				}
			}
        }
        $saldo_cap=$calculos->redondeado ($saldo_cap,2);
                         
        if( $num_cuota<=$_POST["txt_plazo"]){							//  colum6
			$amort_int=$saldo_cap*(pow((1+($_POST["txt_TEA"]/100)),($num_dias/360))-1);
			}else{
			$amort_int=0;
        }
        $amort_int=$calculos->redondeado ($amort_int,2);
        $total_amort_interes=$total_amort_interes+$amort_int; 
		
        $seg_degr =$saldo_cap*(pow(1+($var8 /100),$num_dias/360)-1); 	//  colum7
        $seg_degr=$calculos->redondeado ($seg_degr,2);
        $total_seguro_desgrav=$total_seguro_desgrav+$seg_degr;
                              			 
        if($num_cuota<=$_POST["txt_plazo"]){ 							//  colum5
        $amor_cap=$var18-$amort_int-$seg_degr;
        }else{
        $amor_cap=0;
        }
        $amor_cap=$calculos->redondeado ($amor_cap,2);
        $total_amort_cap=$total_amort_cap+$amor_cap;	
			     
        if ($num_cuota<=$_POST["txt_plazo"]){							//colum 8
        $cuota= $amor_cap+$amort_int+$seg_degr;
        }else{
        $cuota=0;
        }
        $cuota=$calculos->redondeado ($cuota,2);;
        $total_cuota=$total_cuota+$cuota;

    $almacena_saldoCap[$num]=$saldo_cap;
    $almacena_amortCapital[$num]=$amor_cap;
	
    }//cierra for
    
    $total_amort_interes=$calculos->redondeado ($total_amort_interes, 2) ;
    $total_seguro_desgrav=$calculos->redondeado ($total_seguro_desgrav, 2) ;
    $total_amort_cap=$calculos->redondeado ($total_amort_cap,2) ;
    $total_cuota=$calculos->redondeado ($total_cuota,2) ;
	                  							
$diferencia=$var14-$total_amort_cap;              //var para la iteracion de las sgtes tablas
$diferencia=$calculos->redondeado ($diferencia,0);

$tea_mensual=(pow((1+$_POST["txt_TEA"]/100+$var8/100),1/12)-1)*100;
$tea_mensual=$calculos->redondeado ($tea_mensual, 2);

$actualizacion=$diferencia/pow((1+$tea_mensual/100),$_POST["txt_plazo"]);
$actualizacion=$calculos->redondeado ($actualizacion, 2);

$prorrateo=$calculos->calcula_cuota_sin_ajustar($actualizacion,$iy,$_POST["txt_plazo"]);
$prorrateo=$calculos->redondeado ($prorrateo, 2);

if($_POST['combo2']==11 || $_POST['combo2']==13 || $_POST['combo2']==15|| $_POST['combo2']==19){
$prorrateo=$calculos->calcula_cuota_sin_ajustar($actualizacion,$tea_mensual/100,$_POST["txt_plazo"]);
$prorrateo=$calculos->redondeado ($prorrateo, 2);
}

$nueva_cuota=$var18+$prorrateo;
$nueva_cuota=$calculos->redondeado ($nueva_cuota, 2);
		/*echo "<br/>";
		echo "diferencia ".$diferencia;
		echo "<br/>";
		echo "tea_mensual ".$tea_mensual;
		echo "<br/>";
		echo "actualizacion ".$actualizacion;
		echo "<br/>";
		echo "prorrateo ".$prorrateo;
		echo "<br/>";
		echo "nueva_cuota ".$nueva_cuota;
		echo "<br/>";*/
if($diferencia==0){      //si a la primera dif es 0 que haga la tabla final

	$total_seg_contra_incendio=0;
	$total_microseguro=0;
	$total_subTotal=0;
	$total_TIR=0;
	?>
     <table  width="865" cellspacing="0" cellpadding="0" border="0" class="tab_micro">
		<tr class="texto_cabecera_tabla" height="60">
		<td width="47" align="center" class="borde_tabla1">N&deg; cuota</td> 
		<td width="89" align="center" class="borde_tabla1">Fecha<br /> de pago</td> 
		<td width="34" align="center" class="borde_tabla1">N&deg; de d&iacute;as</td> 
	
		<td width="86" align="center" class="borde_tabla1">Saldo<br /> capital</td> 
		<td width="88" align="center" class="borde_tabla1">Amortizaci&oacute;n <br /> intereses</td> 
		<td width="90" align="center" class="borde_tabla1">Seguro <br /> desgravamen</td> 
		<td width="87" align="center" class="borde_tabla1">Amortizaci&oacute;n <br />capital</td> 
		<td width="76" align="center" class="borde_tabla1">Seguro contra<br /> incendios</td> 
		<td width="88" align="center" class="borde_tabla1">Microseguro</td> 
		<td width="67" align="center" class="borde_tabla1">Subtotal</td> 
		<td width="40" align="center" class="borde_tabla1">ITF</td>  
		<td width="73" align="center" class="borde_tabla2">Cuota a pagar</td>
        </tr>
	<?php
	$num_cuota=0;
	$num=0;//para sacar la fecha anterior en mi array
	$total_amort_cap=0;
	$total_amort_interes=0;
	$total_seguro_desgrav=0;
	$total_cuota=0;
	
	
	//***************************************
	$primer_mes = substr($fecha2, 3, -5);//mes de la 1era cuota 0!!!
	$primer_mes=$primer_mes+0; //para que sea entero
	$primer_anio = substr($fecha2, 6, 9);//año de la 1era cuota
	
	if($primer_mes==12 and $dia_primeraCuota>31){
	$dia_primeraCuota=($dia_primeraCuota%31);
	$primer_anio=$primer_anio+1;
	$primer_mes="01";
	}
	//*****************************************
	$style=0;
	
	for($i=1;$i<=$_POST["txt_plazo"]; $i++){
	if($style==0){
		$cls="pinta1";
		$style++;	
		}else
		{
		$cls="pinta2";
		$style=0;
		}
	
	$num=$num+1;
	?>
        <tr class="<?=$cls;?>" height="30" width="47">
        <td align="center" class="borde_tabla_filas"><?php                        //columna1
        $num_cuota=$num_cuota+1;
        echo $num_cuota;
        ?>
        </td>			
        <td align="center" class="borde_tabla_filas" width="88">
 <?php
        									 
/*******************************************************************************/	 //colum2			
			
			if($primer_mes>9){
			$fec_pago="$dia_primeraCuota-$primer_mes-$primer_anio";
			//echo $fec_pago;
				}else{
				$fec_pago="$dia_primeraCuota-0$primer_mes-$primer_anio";
				//echo $fec_pago;	
			}
			
			$fmostrar=$fec_pago;
			//echo $calculos->traducefecha($fmostrar);/*imprimo la fecha de la primera cuota*/
			
		/****************VALIDACION PARA LAS CUOTAS************************************************************/
			
		
		if($primer_mes==4||$primer_mes==6||$primer_mes==9||$primer_mes==11 ){
			$mes_intervalo=round($_POST["txt_intervalo"]/30);
			$dia_intervalo=$_POST["txt_intervalo"]-30*($mes_intervalo);
			}else{
				if($primer_mes==1||$primer_mes==3||$primer_mes==5||$primer_mes==7||$primer_mes==8||$primer_mes==10||$primer_mes==12){//AQUI VAN LOS meses q tienen 31 dias
				$mes_intervalo=round($_POST["txt_intervalo"]/31);
				$dia_intervalo=$_POST["txt_intervalo"]-31*($mes_intervalo);
				}
			}
		if($primer_mes==2 && $primer_anio%4==0){
			$mes_intervalo=round($_POST["txt_intervalo"]/29);
			$dia_intervalo=$_POST["txt_intervalo"]-29*($mes_intervalo);
		}else{ 
				if($primer_mes==2 && $primer_anio%4!=0){
				$mes_intervalo=round($_POST["txt_intervalo"]/28);
				$dia_intervalo=$_POST["txt_intervalo"]-28*($mes_intervalo);
				}
			}
			
		if($_POST["txt_intervalo"]<59 ||$_POST["txt_intervalo"]>49){
			$mes_intervalo=round($_POST["txt_intervalo"]/30)-1;
			$dia_intervalo=$_POST["txt_intervalo"]-30*($mes_intervalo);
		
		}
			//$dia_intervalo=$_POST["txt_intervalo"]-30*($mes_intervalo);
			
			/*echo"<br>";
			echo "dia int ".$dia_intervalo;
			echo "mes int ".$mes_intervalo;
			echo"<br>";*/
		
			$dia_primeraCuota=$dia_primeraCuota+$dia_intervalo;
			$primer_mes=$primer_mes+$mes_intervalo;	
				
			if($primer_mes>12){//si se viene el otro año ....no pasa de 1 año:)
				$primer_anio=$primer_anio+1;
				$primer_mes=$primer_mes%12;
				/*if($primer_mes>1){
				$primer_mes=$primer_mes%12+1;
				}*/
				
			}
			if($dia_primeraCuota>30){
				if($primer_mes==4||$primer_mes==6||$primer_mes==9||$primer_mes==11){
				$dia_primeraCuota=$dia_primeraCuota%30;
				
				$primer_mes=$primer_mes+1;
				}else{
				$dia_primeraCuota=$dia_primeraCuota%31;
				$primer_mes=$primer_mes+1;
				}
			}
/*******************************************************************************/		
?>
			
        </td>
        <?php
        $almacena_fechas[$num]=$fec_pago;
        ?>
        
        <td align="center" class="borde_tabla_filas" width="34"><?php                       //colum3
        
        if ($num==1){
        $num_dias=($calculos->restaFechas( $fecha_fin_per_gracia,$fecha2)) ;
		}else{
			if($num>=2){
				if($num_cuota<=$_POST["txt_plazo"]){
				$num_dias=($calculos->restaFechas($almacena_fechas[$num-1],$almacena_fechas[$num]));
				}else{
				$num_dias=0;
				}
			}
        }
        echo $num_dias;
        ?></td> 
        
        <td align="center" class="borde_tabla_filas" width="85"> <?php 		//colum4
			if($num==1){
			$saldo_cap=$var14;
			?>
			<?php 
			}else{
				if($num>=2){
					if($num_cuota<=$_POST["txt_plazo"]){
					$saldo_cap=$almacena_saldoCap[$num-1]-$almacena_amortCapital[$num-1];
					}else{
					$saldo_cap=0;
					}
				}
			}
			$saldo_cap=$calculos->redondeado ($saldo_cap,2);
			//echo $saldo_cap;
			echo number_format($saldo_cap,2);
			?></td> 
			
			<td align="center" class="borde_tabla_filas" width="88"><?php                         //  colum6
			if( $num_cuota<=$_POST["txt_plazo"]){
			$amort_int=$saldo_cap*(pow((1+($_POST["txt_TEA"]/100)),($num_dias/360))-1);
			}else{
			$amort_int=0;
			}
			$amort_int=$calculos->redondeado ($amort_int,2);
			//echo $amort_int;
			echo number_format($amort_int,2);
			$total_amort_interes=$total_amort_interes+$amort_int;
			?></td> 
			
			<td align="center" class="borde_tabla_filas" width="91"><?php                          //  colum7
			$seg_degr =$saldo_cap*(pow(1+($var8 /100),$num_dias/360)-1);

			$seg_degr=$calculos->redondeado ($seg_degr,2);
			//echo $seg_degr;
			echo number_format($seg_degr,2);
			$total_seguro_desgrav=$total_seguro_desgrav+$seg_degr;
			?></td>
			
			<td align="center" class="borde_tabla_filas" width="88">
			<?php                                			  //  colum5
			if($num_cuota<=$_POST["txt_plazo"]){
			$amor_cap=$var18-$amort_int-$seg_degr;
			}else{
			$amor_cap=0;
			}
			$amor_cap=$calculos->redondeado ($amor_cap,2);
			//echo $amor_cap;
			echo number_format($amor_cap,2);
			$total_amort_cap=$total_amort_cap+$amor_cap;
			?></td> 
        
        <td align="center" class="borde_tabla_filas" width="77">
        <?php                                			  //  colum8
        if($num_cuota<=$_POST["txt_plazo"]){
        $seg_contra_incendio=$var6;
        }else {
        $seg_contra_incendio=0;
        }
        //echo $seg_contra_incendio;
		echo number_format($seg_contra_incendio,2);
        $total_seg_contra_incendio=$total_seg_contra_incendio+$var6;?></td>
        
        <td align="center" class="borde_tabla_filas" width="88"> 
        <?php 			
		$microseguro=$_POST["txt_micro"];    						  //  colum9                 			
        echo number_format($microseguro,2);
        $total_microseguro=$total_microseguro+$microseguro;
        ?></td>  
        
        <td align="center" class="borde_tabla_filas" width="67"> 
        <?php                                			  //  colum10
        if($num_cuota<=$_POST["txt_plazo"]){
        $subTotal=$amor_cap+$amort_int+$seg_degr+$seg_contra_incendio+$microseguro;
        //$var15=$subTotal
        }else {
        $subTotal=0;
        }
        $subTotal=$calculos->redondeado ($subTotal, 2);
        //echo $subTotal;
		echo number_format($subTotal,2);
        $total_subTotal=$total_subTotal+$subTotal;?></td> 
        
        <td align="center" class="borde_tabla_filas" width="40">
        <?php                                			  //  colum11
        $ITF= $subTotal*(0.05/100);
        $ITF=$calculos->redondeado ($ITF, 2);
        echo $ITF;
        ?></td> 
        
        <td align="center"  width="72">
        <?php                                			  //  colum12
        $cuota_a_pagar=$subTotal+$ITF;
        $cuota_a_pagar=$calculos->redondeado ($cuota_a_pagar, 2);
        //echo $cuota_a_pagar;
		echo number_format($cuota_a_pagar,2);
        ?></td> 
         
        <?php                                			  //  colum13
        /*$TIR=$subTotal;
        $TIR=$calculos->redondeado ($TIR, 2);
        echo $TIR;
        $total_TIR=$total_TIR+$TIR;
		$arr_tir[$num]=$TIR;*/
        $arr_tir[$num]=$subTotal;
        $arr_tir[$num]=$calculos->redondeado ($arr_tir[$num], 2);
       // echo $arr_tir[$num];
		
        ?> 
        
        </tr>
        <?php
	}//cierra for para las filas de la tabla
	?>
	 <tr  height="30">                                         <!--para los totales-->
		<td colspan="4" align="center"><strong>TOTALES:</strong></td>
		<td align="center" class="borde_tabla_filas "><strong><?php echo number_format($total_amort_interes,2); ?></strong></td>
		<td align="center" class="borde_tabla_filas "><strong><?php echo number_format($total_seguro_desgrav,2);?></strong></td>
		<td align="center" class="borde_tabla_filas "><strong>
	    <?php
		$total_amort_cap=$calculos->redondeado($total_amort_cap,0);
		if($var14-$total_amort_cap<-0.5){
			$total_amort_cap=($calculos->redondeado($total_amort_cap,0)-1);
		}
		echo $total_amort_cap;
		 ?>
		</strong></td>
		<td align="center" class="borde_tabla_filas "><strong><?php echo number_format($total_seg_contra_incendio,2);?></strong></td>
		<td align="center" class="borde_tabla_filas "><strong><?php echo number_format($total_microseguro,2);?></strong></td>
		<td align="center"><strong><?php echo number_format($total_subTotal,2) ;?></strong></td>
		<td colspan="2" ></td>
		</tr>
	</table>

	<?php	
}//cierra if q compara si la dif =0
else{
//*****************************************ITERACIONES***************************************************************************
	while($diferencia!=0){
		for($i=1;$i<=$_POST["txt_plazo"]; $i++){
		$num_cuota=0;
		$num=0;//para sacar la fecha anterior en mi array
		$total_amort_cap=0;
		$total_amort_interes=0;
		$total_seguro_desgrav=0;
		$total_cuota=0;
		//***************************************
		$primer_mes = substr($fecha2, 3, -5);//mes de la 1era cuota 0!!!
		$primer_mes=$primer_mes+0; 			//para que sea entero
		$primer_anio = substr($fecha2, 6, 9);//año de la 1era cuota
		
		if($primer_mes==12 and $dia_primeraCuota>31){
		$dia_primeraCuota=($dia_primeraCuota%31);
		$primer_anio=$primer_anio+1;
		$primer_mes="01";
		}
		//*****************************************
			for($i=1;$i<=$_POST["txt_plazo"]; $i++){
			//columna1
			$num=$num+1;
			$num_cuota=$num_cuota+1;					
			//colum2
			if($primer_mes>9){							
			$fec_pago="$dia_primeraCuota-$primer_mes-$primer_anio";
			}else{
			$fec_pago="$dia_primeraCuota-0$primer_mes-$primer_anio";
			}
			
			if($primer_mes==12){
			$primer_anio=$primer_anio+1;
			$primer_mes=1;
			}else{
			$primer_mes=$primer_mes+1;
			}						
			
			$almacena_fechas[$num]=$fec_pago;
			//colum3
			if ($num==1){								
			$num_dias=($calculos->restaFechas( $fecha_fin_per_gracia,$fecha2)) ;
				}else{
					if($num>=2){
						if($num_cuota<=$_POST["txt_plazo"]){
						$num_dias=($calculos->restaFechas($almacena_fechas[$num-1],$almacena_fechas[$num]));
						}else{
						$num_dias=0;
					}
				}
			}
			//colum4
			if($num==1){								
				$saldo_cap=$var14;
				}else{
					if($num>=2){
						if($num_cuota<=$_POST["txt_plazo"]){
						$saldo_cap=$almacena_saldoCap2[$num-1]-$almacena_amortCapital2[$num-1];
						}else{
						$saldo_cap=0;
					}
				}
			}
			$saldo_cap=$calculos->redondeado ($saldo_cap,2);
			//  colum6
			if( $num_cuota<=$_POST["txt_plazo"]){
			$amort_int=$saldo_cap*(pow((1+($_POST["txt_TEA"]/100)),($num_dias/360))-1);
			}else{
			$amort_int=0;
			}
			$amort_int=$calculos->redondeado ($amort_int, 2);
			$total_amort_interes=$total_amort_interes+$amort_int;
             //  colum7
			$seg_degr =$saldo_cap*(pow(1+($var8 /100),$num_dias/360)-1);
			$seg_degr=$calculos->redondeado ($seg_degr, 2);
			$total_seguro_desgrav=$total_seguro_desgrav+$seg_degr;
			//  colum5
			if($num_cuota<=$_POST["txt_plazo"]){
			$amor_cap=$nueva_cuota-$amort_int-$seg_degr;
			}else {
			$amor_cap=0;
			}
			$amor_cap=$calculos->redondeado ($amor_cap, 2);
			$total_amort_cap=$total_amort_cap+$amor_cap;
			//colum 8
			if ($num_cuota<=$_POST["txt_plazo"]){
			$cuota= $amor_cap+$amort_int+$seg_degr;
				}else{
				$cuota=0;
			}
			$cuota=$calculos->redondeado ($cuota, 2);
			$total_cuota=$total_cuota+$cuota;
			
			$almacena_saldoCap2[$num]=$saldo_cap;
			$almacena_amortCapital2[$num]=$amor_cap;
			/*guardo los datos de la ultima tabla para poder mostrarla*/
			$arr_saldo_cap[]=$saldo_cap;
			$arr_amort_capital[]=$amor_cap;
			$arr_amort_interes[]=$amort_int;
			$arr_seg_desgrav[]=$seg_degr;
			$arr_cuota[]=$cuota;
			}//cierra for para las filas de la tabla
		$total_amort_interes=$calculos->redondeado ($total_amort_interes, 2) ;//totaleeeees
		$total_seguro_desgrav=$calculos->redondeado ($total_seguro_desgrav, 2) ;
		$total_amort_cap=$calculos->redondeado ($total_amort_cap, 2) ;
		$total_cuota=$calculos->redondeado ($total_cuota,2) ;
		}//CIERRO EL FOR QUE GENERA LAS TABLAS
		
	//var para la iteracion de las sgtes tablas
	$diferencia=$var14-$total_amort_cap;
	$diferencia=$calculos->redondeado ($diferencia, 0);
	
	$tea_mensual=(pow((1+$_POST["txt_TEA"]/100+$var8/100),1/12)-1)*100;
	$tea_mensual=$calculos->redondeado ($tea_mensual, 2);
	
	$actualizacion=$diferencia/pow((1+$tea_mensual/100),$_POST["txt_plazo"]);
	$actualizacion=$calculos->redondeado ($actualizacion, 2);
	
	$prorrateo=$calculos->calcula_cuota_sin_ajustar($actualizacion,$iy,$_POST["txt_plazo"]);
	$prorrateo=$calculos->redondeado ($prorrateo, 2);
	
	if($_POST['combo2']==11 || $_POST['combo2']==13|| $_POST['combo2']==19){
$prorrateo=$calculos->calcula_cuota_sin_ajustar($actualizacion,$tea_mensual/100,$_POST["txt_plazo"]);
$prorrateo=$calculos->redondeado ($prorrateo, 2);
}
	$nueva_cuota=$nueva_cuota+$prorrateo;
	$nueva_cuota=$calculos->redondeado ($nueva_cuota,2);
	
	}//CIERRA WHILE PARA QUE TERMINE DE ITERAR
//********************************************************tabla final  ************************************************				
$total_seg_contra_incendio=0;
$total_microseguro=0;
$total_subTotal=0;

/*$dia_intervalo=0;
$mes_intervalo=0;*/

	if($diferencia==0){
		?>
        
       <table  width="865" cellspacing="0" cellpadding="0" border="0" class="tab_micro">
		<tr class="texto_cabecera_tabla" height="60">
		<td width="47" align="center" class="borde_tabla1">N&deg; cuota</td> 
		<td width="89" align="center" class="borde_tabla1">Fecha<br /> de pago</td> 
		<td width="34" align="center" class="borde_tabla1">N&deg; de d&iacute;as</td> 
	
		<td width="86" align="center" class="borde_tabla1">Saldo<br /> capital</td> 
		<td width="88" align="center" class="borde_tabla1">Amortizaci&oacute;n <br /> intereses</td> 
		<td width="90" align="center" class="borde_tabla1">Seguro <br /> desgravamen</td> 
		<td width="87" align="center" class="borde_tabla1">Amortizaci&oacute;n <br />capital</td> 
		<td width="76" align="center" class="borde_tabla1">Seguro contra<br /> incendios</td> 
		<td width="88" align="center" class="borde_tabla1">Microseguro</td> 
		<td width="67" align="center" class="borde_tabla1">Subtotal</td> 
		<td width="40" align="center" class="borde_tabla1">ITF</td>  
		<td width="73" align="center" class="borde_tabla2">Cuota a pagar</td>
        </tr>
		<?php
		$num_cuota=0;
		$num=0;//para sacar la fecha anterior en mi array
		//$total_amort_cap=0;
		$total_amort_interes=0;
		$total_seguro_desgrav=0;
		$total_cuota=0;
		
		//***************************************
		$primer_mes = substr($fecha2, 3, -5);//mes de la 1era cuota 0!!!
		$primer_mes=$primer_mes+0; //para que sea entero
		$primer_anio = substr($fecha2, 6, 9);//año de la 1era cuota
		
		if($primer_mes==12 and $dia_primeraCuota>31){
		$dia_primeraCuota=($dia_primeraCuota%31);
		$primer_anio=$primer_anio+1;
		$primer_mes="01";
		}
		//*****************************************
		$style=0;
		
	
		
		for($i=1;$i<=$_POST["txt_plazo"]; $i++){/****************************************************************/

		if($style==0){
		$cls="pinta1";
		$style++;	
		}else
		{
		$cls="pinta2";
		$style=0;
		}
				
		$num=$num+1;
		?>
			<tr class="<?=$cls;?>" height="30" width="47">
			<td align="center" class="borde_tabla_filas">
			
			<?php                        //columna1
			$num_cuota=$num_cuota+1;
			echo $num_cuota;
			?>
			</td>			
			<td align="center" class="borde_tabla_filas" width="88">
			<?php 										  //colum2
/*******************************************************************************/				
			
			if($primer_mes>9){
			$fec_pago="$dia_primeraCuota-$primer_mes-$primer_anio";
			//echo $fec_pago;
				}else{
				$fec_pago="$dia_primeraCuota-0$primer_mes-$primer_anio";
				//echo $fec_pago;	
			}
			
			$fmostrar=$fec_pago;
			echo $calculos->traducefecha($fmostrar);/*imprimo la fecha de la primera cuota*/
			
		/****************VALIDACION PARA LAS CUOTAS************************************************************/
			
		
		if($primer_mes==4||$primer_mes==6||$primer_mes==9||$primer_mes==11 ){
			$mes_intervalo=round($_POST["txt_intervalo"]/30);
			$dia_intervalo=$_POST["txt_intervalo"]-30*($mes_intervalo);
			}else{
				if($primer_mes==1||$primer_mes==3||$primer_mes==5||$primer_mes==7||$primer_mes==8||$primer_mes==10||$primer_mes==12){//AQUI VAN LOS meses q tienen 31 dias
				$mes_intervalo=round($_POST["txt_intervalo"]/31);
				$dia_intervalo=$_POST["txt_intervalo"]-31*($mes_intervalo);
				}
			}
		if($primer_mes==2 && $primer_anio%4==0){
			$mes_intervalo=round($_POST["txt_intervalo"]/29);
			$dia_intervalo=$_POST["txt_intervalo"]-29*($mes_intervalo);
		}else{ 
				if($primer_mes==2 && $primer_anio%4!=0){
				$mes_intervalo=round($_POST["txt_intervalo"]/28);
				$dia_intervalo=$_POST["txt_intervalo"]-28*($mes_intervalo);
				}
			}
			
		if($_POST["txt_intervalo"]<59 ||$_POST["txt_intervalo"]>49){
			$mes_intervalo=round($_POST["txt_intervalo"]/30)-1;
			$dia_intervalo=$_POST["txt_intervalo"]-30*($mes_intervalo);
		
		}
			//$dia_intervalo=$_POST["txt_intervalo"]-30*($mes_intervalo);
			
			/*echo"<br>";
			echo "dia int ".$dia_intervalo;
			echo "mes int ".$mes_intervalo;
			echo"<br>";*/
			
			$dia_primeraCuota=$dia_primeraCuota+$dia_intervalo;
			$primer_mes=$primer_mes+$mes_intervalo;	
			
			if($dia_primeraCuota>=30){
				if($primer_mes==4||$primer_mes==6||$primer_mes==9||$primer_mes==11){
				$dia_primeraCuota=$dia_primeraCuota%30;
				$primer_mes=$primer_mes+1;
				}else{
				$dia_primeraCuota=$dia_primeraCuota%31;
				$primer_mes=$primer_mes+1;
				}
				
			
				
			if($primer_mes>12){//si se viene el otro año ....no pasa de 1 año:)
				$primer_anio=$primer_anio+1;
				$primer_mes=$primer_mes%12;
				/*if($primer_mes>1){
				$primer_mes=$primer_mes%12+1;
				}*/
				
			}
			
			}
/*******************************************************************************/				
			?></td>
			<?php
			$almacena_fechas[$num]=$fec_pago;
			?>
			
			<td align="center" class="borde_tabla_filas" width="34"><?php                       //colum3
			
			if ($num==1){
			$num_dias=($calculos->restaFechas( $fecha_fin_per_gracia,$fecha2)) ;
			}else{
				if($num>=2){
					/*if($num_cuota<=$_POST["txt_plazo"]){
					$num_dias=($calculos->restaFechas($almacena_fechas[$num-1],$almacena_fechas[$num]));
					}else{
					$num_dias=0;
					}*/
					$num_dias=$_POST["txt_intervalo"];
				}
			}
			
			echo $num_dias;
			?></td> 
			
			<td align="center" class="borde_tabla_filas" width="85">  		
			<?php 											//colum4
			//echo $arr_saldo_cap[$i-1];
			echo number_format($arr_saldo_cap[$i-1],2);
			?></td> 
			
			<td align="center" class="borde_tabla_filas" width="88"><?php                         //  colum6
			//echo $arr_amort_interes[$i-1];
			echo number_format($arr_amort_interes[$i-1],2);
			$total_amort_interes=$total_amort_interes+$arr_amort_interes[$i-1];
			?></td> 
			
			<td align="center" class="borde_tabla_filas" width="91"><?php                          //  colum7
			//echo $arr_seg_desgrav[$i-1];
			echo number_format($arr_seg_desgrav[$i-1],2);
			$total_seguro_desgrav=$total_seguro_desgrav+$arr_seg_desgrav[$i-1];
			?></td>
			
			<td align="center" class="borde_tabla_filas" width="88">
			<?php                                			  //  colum5
			//echo $arr_amort_capital[$i-1];
			echo number_format($arr_amort_capital[$i-1],2);
			//$total_amort_cap=$total_amort_cap+$arr_amort_capital[$i-1];
			?></td> 
			
			<td align="center" class="borde_tabla_filas" width="77">
			<?php                                			  //  colum8
			if($num_cuota<=$_POST["txt_plazo"]){
			$seg_contra_incendio=$var6;
			}else {
			$seg_contra_incendio=0;
			}
			//echo $seg_contra_incendio;
			echo number_format($seg_contra_incendio,2);
			$total_seg_contra_incendio=$total_seg_contra_incendio+$var6;?></td>
			
			<td align="center" class="borde_tabla_filas" width="88"> 
			<?php 			
			$microseguro=$_POST["txt_micro"];       				//  colum9                    
			//echo $microseguro;
			echo number_format($microseguro,2);
			$total_microseguro=$total_microseguro+$microseguro;
			?></td>  
			
			<td align="center" class="borde_tabla_filas" width="67"> 
			<?php                                			  //  colum10
			if($num_cuota<=$_POST["txt_plazo"]){
			$subTotal=$arr_amort_capital[$i-1]+$arr_amort_interes[$i-1]+$arr_seg_desgrav[$i-1]+$seg_contra_incendio+$microseguro;
			//$var15=$subTotal
			}else {
			$subTotal=0;
			}
			$subTotal=$calculos->redondeado ($subTotal, 2);
			//echo $subTotal;
			echo number_format($subTotal,2);
			$total_subTotal=$total_subTotal+$subTotal;?></td> 
			
			<td align="center" class="borde_tabla_filas" width="40">
			<?php                                			  //  colum11
			$ITF= $subTotal*(0.05/100);
			$ITF=$calculos->redondeado ($ITF, 2);
			echo $ITF;
			?></td> 
			
			<td align="center" width="72">
			<?php                                			  //  colum12
			$cuota_a_pagar=$subTotal+$ITF;
			$cuota_a_pagar=$calculos->redondeado ($cuota_a_pagar, 2);
			//echo $cuota_a_pagar;
			echo number_format($cuota_a_pagar,2);
			?></td> 
			
			<?php                                			  //  colum13
			/*$TIR=$subTotal;
			$TIR=$calculos->redondeado ($TIR, 2);
			echo $TIR;
			$total_TIR=$total_TIR+$TIR;*/
			$arr_tir[$num]=$subTotal;
			$arr_tir[$num]=$calculos->redondeado ($arr_tir[$num], 2);
			//echo $arr_tir[$num];		
			?>
			
			</tr>
			<?php
		}//cierra for para las filas de la tabla
		?>
        
        <tr  height="30">                                         <!--para los totales-->
		<td colspan="4" align="center"><strong>TOTALES:</strong></td>
		<td align="center" class="borde_tabla_filas "><strong><?php echo number_format($total_amort_interes,2); ?></strong></td>
		<td align="center" class="borde_tabla_filas "><strong><?php echo number_format($total_seguro_desgrav,2);?></strong></td>
		<td align="center" class="borde_tabla_filas "><strong>
	    <?php
		$total_amort_cap=$calculos->redondeado($total_amort_cap,0);
		if($var14-$total_amort_cap<-0.5){
			$total_amort_cap=($calculos->redondeado($total_amort_cap,0)-1);
		}
		echo number_format($total_amort_cap, 2);
		 ?>
		</strong></td>
		<td align="center" class="borde_tabla_filas "><strong><?php echo number_format($total_seg_contra_incendio,2);?></strong></td>
		<td align="center" class="borde_tabla_filas "><strong><?php echo number_format($total_microseguro,2);?></strong></td>
		<td align="center"><strong><?php echo number_format($total_subTotal,2) ;?></strong></td>
		<td colspan="2" ></td>
		</tr>
		</table>
		<?php	
	}//cierra if q compara si la dif =0
}//cierro else para q no haga 2 tablas en caso q dif sea 0 en la 1era
?>
