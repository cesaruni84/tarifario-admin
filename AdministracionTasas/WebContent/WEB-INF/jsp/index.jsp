<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es"> <!--<![endif]-->
    <head>
        <meta charset="UTF-8" />
        <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
        <title>Administrador de Tasas - Señor de Luren</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Ingreso a Intranet - Señor de Luren" />
        <meta name="author" content="AETOS PERU S.A.C." />
		
		<!--Librerias JQuery UI -->
		<link rel="stylesheet" href="resources/ui/themes/jquery.ui.all.css">
		<link href="css/estilos.css" rel="stylesheet" type="text/css">
		<script src="resources/ui/js/jquery-1.8.0.js"></script>
		<script src="js/jquery-1.6.1.min.js" type="text/javascript"></script>
		<script src="resources/ui/js/jquery.ui.core.js"></script>
		<script src="resources/ui/js/jquery.ui.widget.js"></script>
		<script src="resources/ui/js/jquery.ui.button.js"></script>
		<script src="resources/ui/js/jquery.ui.dialog.js"></script>
		<script src="js/login.js" type="text/javascript"></script>
		
		<style>
			body {
				font-size: 85%;
			}

			select,.ui-select-menu {
				float: left;
				margin-right: 10px;
			}

			label,input {
				color: #039;
				font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana", "sans-serif";
			}

			input.text {
				margin-bottom: 12px;
				padding: .4em;
				color: #039;
			}
			
			input.submit {
				margin-bottom: 12px;
				font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana", "sans-serif";
				font-size: 1.2em;
				color: #039;
			}

			fieldset {
				padding: 0;
				border: 0;
				margin-top: 25px;
			}

			h1 {
				font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana", "sans-serif";
				font-size: 1.4em;
				color: #039;
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

    </head>
	
	<header align="center">
		<div id="logo" ><img src="./imagenes/logo-srluren.gif" align="top"></div>
	</header>
		
    <body>
        <div class="container" align="center" style="margin-top:300px;">
            <section>				
                <div id="container_demo">
						<!-- Inicio Sección de logueo-->
                        <div id="login_div">
							<tr><div align="center" style="width:400px;border:1px solid #000;"></div></tr>
                            <form  id="formLogin" name="formLogin" method="post" onsubmit="return true;">
                                <h1>WEB DE ADMINISTRACION DE TASAS</h1>
								<table>
									<tr> 
										<td><label for="username" width="400"> Usuario :<br></label></td>
										<td><input id="username" name="username" required="required" type="text" size="30" placeholder="Ingrese nombre de usuario" class="text ui-widget-content ui-corner-all"/></td>
									</tr>
									<tr> 
										<td><label for="password" width="400"> Clave :<br></label></td>
										<td><input id="password" name="password" required="required" type="password"  size="30" placeholder="Ingrese su clave" class="text ui-widget-content ui-corner-all"/></td>
									</tr>
									<tr height="15">
									<tr>
										<td></td>
										<td><input type="button" value="Ingresar" id="btnIngresar"></input></td>
									</tr>
									<tr height="15">
										<!--<div id="ajax_loading">
											<img align="absmiddle" src="./imagenes/spinner.gif">&nbsp;Validando datos con el servidor...
										</div>-->
								</table>
								<label id="mensaje_login" style="color:#FF0000;"></label>
                            </form>
							<tr><div align="center" style="width:400px;border:1px solid #fff;"></div></tr>

                        </div>

                </div>  
            </section>
        </div>
		
    </body>
</html>