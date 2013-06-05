<?php 
function ob_option_select($link, $columna, $tabla){
	$c="SELECT DISTINCT ". $columna ." FROM ".$tabla." WHERE ".$columna."!=''";
	$q=mysql_query($c, $link);
		echo '<option value="" selected="selected">Seleccionar</option>';
	while($r=mysql_fetch_array($q)){
		echo '<option value="'. mostrar_texto($r[$columna]) .'">'. mostrar_texto($r[$columna]) .'</option>';
	}
}
/*
function ob_option_select($link, $columna, $tabla){
	$c="SELECT mco_code, ". $columna ." FROM ".$tabla." WHERE ".$columna."!='' group by ". $columna ."";
	$q=mysql_query($c, $link);
		echo '<option value="" selected="selected">Seleccionar</option>';
	while($r=mysql_fetch_array($q)){
		echo '<option value="'. mostrar_texto($r['mco_code']) .'">'.  mostrar_texto($r['mco_code']).'---'.mostrar_texto($r[$columna]) .'</option>';
	}
}
*/
function ob_option_select_p_museable($link, $columna, $tabla){
	$c="SELECT DISTINCT ". $columna ." FROM ".$tabla." WHERE ".$columna."!=''";
	$q=mysql_query($c, $link);
		echo '<option value="" selected="selected">Seleccionar</option>';
	while($r=mysql_fetch_array($q)){
		if($r[$columna]=="S")
		{ $opc="SI"; }
		else{ $opc="NO"; }
		echo '<option value="'. mostrar_texto($r[$columna]) .'">'. $opc .'</option>';
	}
}

function ob_image_catalog($link, $columna, $tabla, $titulo, $url, $error=""){
	$c="SELECT ".$titulo.', '. $columna ." FROM ".$tabla." WHERE mco_public=1";
	$q=mysql_query($c, $link);
	$n=mysql_num_rows($q);
	if($n!=0)
	{
		while($r=mysql_fetch_array($q)){
			
			if($r[$columna]=="")
			{ 
				if($error=="")
				{ $ruta=ERROR_IMAGE .'sin-imagen-small.gif'; }
				else{ $ruta=$error .'sin-imagen-small.gif';	}
				
			}else{ $ruta=$url .'small/F1_'. $r[$columna]; }
			
			$image[]='<img src="'. $ruta .'" alt="'.$r[$titulo].'" width="300" height="230">';
		}
	}else{
		$image = array();
	}
	
	return $image;
}
?>