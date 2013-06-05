<?php
class Query
{  
   public function conexion()
   {  
      $dbLink=mysql_connect(HOST,USER,PASS);

      if(!$dbLink)
      {  die('No se pudo conectar a la Base de Datos: '.mysql_error());
      }
      if(!mysql_select_db(DB,$dbLink))
      {  die('No se pudo conectar a la Base de Datos: "'.DB.'": '.mysql_error());
      }
	  mysql_query ("SET NAMES 'utf8'");
	  return $dbLink;
   }
   
   public function mQuery($Cad,$Link)
   {
		$query = mysql_query($Cad,$Link) or die(mysql_error());
		return $query;
   }
   
   public function fArray($query)
   {
   		$r=mysql_fetch_array($query);
		return $r;
   }
	
	public function Cconex($Link)
	{
		mysql_close($Link);
	}


}
?>