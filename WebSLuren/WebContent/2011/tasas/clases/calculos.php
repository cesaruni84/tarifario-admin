<?php
class calculos
{
   public function calcula_seg_inc_tAnual($seg_inc_tMensual)
   {  
   $seguro_incendio_tAnual=(pow(1+($seg_inc_tMensual/100),12))-1;
//	return $seguro_incendio_tAnual*100; cambio al porcentaje -  *100
	return $seguro_incendio_tAnual; 
   }
   
   public function calcula_m_f_segIncendio($seguro_incendio_tAn,$monto) 
   {
     $m_f_segIncendio=($seguro_incendio_tAn/100)*$monto;
//     $m_f_segIncendio=($seguro_incendio_tAn)*$monto;
	return $m_f_segIncendio; 
   }
   
   public function calcula_Seg_Desgravamen_tAnual($seg_inc_tMensual)
   {  
    $seguro_desg_tA=(pow((1+($seg_inc_tMensual/100)),12))-1;
	return $seguro_desg_tA*100; 
   } 

   public function calcula_iy($TEA,$var8,$combo2)
   { 
   $iy=(pow((1+$TEA/100+$var8/100),1/12))-1;
   
//   $iy=(pow((1+($TEA/100)+($var8/100)),(1/12)))-1;
   if($combo2==5){
//   $iy=(pow((1+$TEA/100+$var8/100),1/1.5))-1;
//   $iy=(pow((1+(($TEA/100)+($var8/100))),1/1.5))-1;


	$x1=$TEA/100;
	$x2=$var8/100;
   $iy=pow((1+$x1+$x2),$_POST['txt_plazo']/360)-1;
//   $iy=pow((1+$x1+$x2),1/1.5)-1;
//   $iy=pow((1+$x1+$x2),$plazo/360)-1;
   }
   if($combo2==11){
//   $iy=pow((1+$TEA/100),1/4);
   $iy=pow(((1+$TEA)/100),(1/4))-1; //duda
   }
   if($combo2==13 ||$combo2==15||$combo2==19){
//   $iy=pow((1+$TEA/100),1/12);
   $iy=pow((1+($TEA/100)),(1/12))-1; //duda
   }
   /*else{
   $iy=(pow((1+$TEA/100+$var8/100),1/12))-1;
   }*/
	return $iy; 
   }
    
   public function calcula_cuota_sin_ajustar($var14,$iy,$cuotas)
   {  
   if(pow(1+$iy,$cuotas)-1>0){
	   $cuota_sin_ajustar=$var14*((pow(1+$iy,$cuotas)*$iy)/(pow(1+$iy,$cuotas)-1));
	   return $cuota_sin_ajustar;
	}else {
		return NULL;
		}
    	 

   } 
   /***************************************************************/
   public function calcula_fecha_fin_pGracia($fecha_desembolso,$periodo_gracia)
   {
   $fecha_fin_per_gracia=$fecha_desembolso+$periodo_gracia;
	return $fecha_fin_per_gracia; 
   }
   
   public function calcula_interes_p_gracia($prestamo,$TEA,$seguro_desgravamen_tAnual,$periodo_gracia)
   {
   $interes_p_gracia=$prestamo*(pow((1+($TEA/100)+($seguro_desgravamen_tAnual/100)),($periodo_gracia/360))-1);
	return $interes_p_gracia; 
   }
   
   public function calcula_monto_capitalizado($prestamo,$interes_período_gracia)
   {
   $monto_cap=$prestamo+$interes_período_gracia;
	return $monto_cap; 
   }
   
	public function restaFechas($dFecIni, $dFecFin)
	{
		$dFecIni = str_replace("-","",$dFecIni);
		$dFecIni = str_replace("/","",$dFecIni);
		$dFecFin = str_replace("-","",$dFecFin);
		$dFecFin = str_replace("/","",$dFecFin);
	
		@ereg( "([0-9]{1,2})([0-9]{1,2})([0-9]{2,4})", $dFecIni, $aFecIni);
		@ereg( "([0-9]{1,2})([0-9]{1,2})([0-9]{2,4})", $dFecFin, $aFecFin);
	
		$date1 = mktime(0,0,0,$aFecIni[2], $aFecIni[1], $aFecIni[3]);
		$date2 = mktime(0,0,0,$aFecFin[2], $aFecFin[1], $aFecFin[3]);
	
		return round(($date2 - $date1) / (60 * 60 * 24));
	}

	public function formateo($long,$var){
	$dia = str_pad(substr($var, 0, $long), 2, 0, STR_PAD_LEFT);
	return $dia;
	}
	
	public function redondeado ($numero, $decimales) 
	{
	$num=number_format($numero, $decimales);
	
	$factor = pow(10, $decimales);
	return (round($numero*$factor)/$factor); 
	
	//return round($numero,2);
	}
	
	public function tir($f0,$f1,$f2,$f3,$f4,$f5,$f6,$f7,$f8,$f9,$f10,$taza){
	$taza_ant=0;
	$taza_ult=0;
	$vpn=($f0)+($f1/(1+$taza))+($f2/pow((1+$taza),2))+($f3/pow((1+$taza),3))+($f4/pow((1+$taza),4))+($f5/pow((1+$taza),5))+($f6/pow((1+$taza),6))+($f7/pow((1+$taza),7))+($f8/pow((1+$taza),8))+($f9/pow((1+$taza),9))+($f10/pow((1+$taza),10));
	echo $vpn."inicial <br>";
	
	if ($vpn>0){
	echo "entro";
	for ($x=$taza;$vpn>0; $x+=.0001){
	$vpn=($f0)+($f1/(1+$x))+($f2/pow((1+$x),2))+($f3/pow((1+$x),3))+($f4/pow((1+$x),4))+($f5/pow((1+$x),5))+($f6/pow((1+$x),6))+($f7/pow((1+$x),7))+($f8/pow((1+$x),8))+($f9/pow((1+$x),9))+($f10/pow((1+$x),10));
	$taza_ant=$x-.0001;
	$taza_ult=$x;
	if($x>1){
	$taza_ult="NA";
	return $taza_ult;
	}
	echo $vpn."-".$x."<br>";
	}
	/*echo "taza anterior".$taza_ant."<br>";
	echo "taza final".$taza_ult."<br>";*/
	return $taza_ult*100;
	}
	else
	{
	for ($x=$taza;$vpn<0; $x-=.0001){
	$vpn=($f0)+($f1/(1+$x))+($f2/pow((1+$x),2))+($f3/pow((1+$x),3))+($f4/pow((1+$x),4))+($f5/pow((1+$x),5))+($f6/pow((1+$x),6))+($f7/pow((1+$x),7))+($f8/pow((1+$x),8))+($f9/pow((1+$x),9))+($f10/pow((1+$x),10));
	$taza_ant=$x+.0001;
	$taza_ult=$x;
	
	if($x<-1){
	$taza_ult="NA";
	return $taza_ult;
	
	}
	echo $vpn."-".$x."<br>";
	}
	/*echo "taza anterior".$taza_ant."<br>";
	echo "taza final".$taza_ult."<br>";*/
	return $taza_ult*100;
	}
	
	}//fin de la funcion
	
public function traducefecha($fecha)
    {
    $fecha= strtotime($fecha); // convierte la fecha de formato mm/dd/yyyy a marca de tiempo
    $diasemana=date("w", $fecha);// optiene el número del dia de la semana. El 0 es domingo
       switch ($diasemana)
       {
       case "0":
          $diasemana="Domingo";
          break;
       case "1":
          $diasemana="Lunes";
          break;
       case "2":
          $diasemana="Martes";
          break;
       case "3":
          $diasemana="Miércoles";
          break;
       case "4":
          $diasemana="Jueves";
          break;
       case "5":
          $diasemana="Viernes";
          break;
       case "6":
          $diasemana="Sábado";
          break;
       }
    $dia=date("d",$fecha); // día del mes en número
    $mes=date("m",$fecha); // número del mes de 01 a 12
       switch($mes)
       {
       case "01":
          $mes="Ene";
          break;
       case "02":
          $mes="Feb";
          break;
       case "03":
          $mes="Mar";
          break;
       case "04":
          $mes="Abr";
          break;
       case "05":
          $mes="May";
          break;
       case "06":
          $mes="Jun";
          break;
       case "07":
          $mes="Jul";
          break;
       case "08":
          $mes="Ago";
          break;
       case "09":
          $mes="Sep";
          break;
       case "10":
          $mes="Oct";
          break;
       case "11":
          $mes="Nov";
          break;
       case "12":
          $mes="Dic";
          break;
       }
    $ano=date("Y",$fecha); // optenemos el año en formato 4 digitos
   // $fecha= $diasemana.", ".$dia." de ".$mes." de ".$ano; // unimos el resultado en una unica cadena
	$fecha= $dia.'-'.$mes.'-'.date("y",$fecha);
    return $fecha; //enviamos la fecha al programa
	}
    


}
?>