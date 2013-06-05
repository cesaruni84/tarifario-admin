<?
if ($txt_nombre=="" || $txt_email=="")
{
		header("Location:http://www.cajaluren.com.pe/index.htm" );
}
else
{
		$fecha = date("Y-m-d h:i:s");

		$DescriptorFichero2 = fopen("boletin.csv","a");
		fputs($DescriptorFichero2,"$fecha,$txt_nombre,$txt_email\r\n");
		fclose($DescriptorFichero2);

		$sAsunto =  "Caja Señor de Luren - Boletin";


		$sMensaje = $sMensaje . "Apellidos y Nombre(s)  : $txt_nombre\n";
		$sMensaje = $sMensaje . "Email                  : $txt_email\n";
		$sMensaje = $sMensaje . "\n";
		$sMensaje = $sMensaje . "Descargue la lista de: http://www.cajaluren.com.pe/boletin.csv";

		"-------------------------------------------\n";


		mail("jorge@enjoit.com",$sAsunto, $sMensaje, "From: $nombre<$txt_email>");
		mail("jobando@cajaluren.com.pe",$sAsunto, $sMensaje, "From: $nombre<$txt_email>");

	?>

		<script>
				alert("Gracias por registrarse a nuestro boletín");
				var pagina="http://www.cajaluren.com.pe/index.htm"
				function redireccionar()
				{
				location.href=pagina
				}
setTimeout ("redireccionar()", 10);


</script>
<?

header("location: http://www.cajaluren.com.pe/index.htm");
		exit;
}
?>