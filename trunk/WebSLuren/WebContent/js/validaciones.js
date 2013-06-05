// JavaScript Document
function IsMail(YourMail)
{
	var Template = /^[a-z][a-z-_0-9.]+@[a-z-_=>0-9.]+.[a-z]{2,3}$/i //Formato de direccion de correo electronico
	
	if(YourMail.value!="") 
	{
		if (Template.test(YourMail.value)) 
		{							}
		else
		{   alert("El Email que ha Ingresado tiene Caracteres Inválidos");
			YourMail.focus();
			YourMail.select();
		}		
	}
	return false;
}

function IsDni(YourDni)
{
	var Template = /^[0-9]+$/i //Formato de direccion de correo electronico
	
	if(YourDni.value!="") 
	{
		if (Template.test(YourDni.value)) 
		{							}
		else
		{   alert("El DNI que ha Ingresado tiene Caracteres Inválidos");
			YourDni.select();			
			YourDni.focus();
			return false;
		}		

		if (YourDni.value.length==8) 
		{							}
		else
		{   alert("Tiene que ingresar los 8 digitos del DNI");
			YourDni.focus();
			YourDni.select();
			return false;
		}		
		
	}	
}

function IsPhone(YourPhone)
{
	var Template = /^[0-9- ]+$/i //Formato de alfanumerico
	
	if(YourPhone.value!="") 
	{
		if (Template.test(YourPhone.value)) 
		{							}
		else
		{   alert("El Teléfono que ha Ingresado tiene Caracteres Inválidos");
			YourPhone.focus();
			YourPhone.select();
		}		
	}
	return false;
}

function IsTexto(texto)
{
	var Template = /^[a-zA-Z ]+$/i //Formato de alfanumerico
		if(texto.value!="") 
	{
		if (Template.test(texto.value)) 
		{							}
		else
		{   alert("El Nombre que ha Ingresado tiene Caracteres Inválidos");
			texto.focus();
			texto.select();
		}		
	}		
	return false;
}

function IsDay(day)
{
	if(day.value!="") 
	{
	if ( (isNaN(day.value)) || (day.value<1) || (day.value >31) )
		{
			alert("No es un Dia Válido");
			day.focus();
			day.select();			
		}
	}
	
	return false;	
}

function IsMonth(month)
{
	if(month.value!="") 
	{
	if ( (isNaN(month.value)) || (month.value<1) || (month.value >12) )
		{
			alert("No es un Mes Válido");
			month.focus();
			month.select();
		}
	}	
	return false;	
}

function IsYear(year)
{
	if(year.value!="") 
	{
	if ( (isNaN(year.value)) || (year.value<1900) || (year.value >2000) )
		{
			alert("No es un año Válido. Ingrese entre 1900-2000");
			year.focus();
			year.select();
		}		
	}	
	return false;	
}

//abrir ventana centrada
function AbrirURL(theURL,winName,features, myWidth, myHeight, isCenter) 
{ 
  if(window.screen)if(isCenter)if(isCenter=="true"){
    var myLeft = (screen.width-myWidth)/2;
    var myTop = (screen.height-myHeight)/2;
    features+=(features!='')?',':'';
    features+=',left='+myLeft+',top='+myTop;
  }
  window.open(theURL,winName,features+((features!='')?',':'')+'width='+myWidth+',height='+myHeight);
}