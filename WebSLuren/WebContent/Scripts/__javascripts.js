//
function pviiObjbg(obj, new_color) { //v2.1 by Project VII
	obj.style.backgroundColor=new_color
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_showHideLayers() { //v6.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}


var win = null;
function NewWindow(mypage,myname,w,h,scroll,resi){
LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
settings =
'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable='+resi+''
win = window.open(mypage,myname,settings,resi)

if(win.window.focus){win.window.focus();}
}

// END OF LINE


function bNavegador() {
  if( navigator.appName )
  {
	if( navigator.appName == "Microsoft Internet Explorer")  return 1;
	if( navigator.appName == "Netscape")  return 2;
  }
  return 0;
}

var contador = 1;

function Abre(sVentana)
{

  var w=640, h=480;
  var windowName = new String(contador);
  windowName = "v" + windowName;
  var x = bNavegador();
  if (window.screen && window.screen.availHeight) {
	h = window.screen.availHeight - 63; // 63
	if( x==2 ) h = h - 11;
	w = window.screen.availWidth - 4;
  }

  window.open(sVentana, windowName, "status=yes,resizable=yes,toolbar=no,scrollbars=yes,top=0,left=0,width=" + w + ",height=" + h, 1 );
  contador = contador + 1;
}

function toggle(ImgID,DivID)
	{		
		var div = document.getElementById(DivID);
		var GetImg = document.getElementById(ImgID);
		
		if(div.style.display == "none")
		{
			div.style.display = "inline";
			//GetImg.src = "expand.gif";
		}
		else
		{
			div.style.display = "none";
			//GetImg.src = "collapse.gif";
/*			if(div.style.display == "none")
				{
				div.style.display = "block";
				}
			else
				{
				div.style.display = "none";
				}
*/
		}
	}
	
function validar_busqueda()
{
	if(document.form_busq.criterio.value=="")
	{
		alert("Ingrese su criterio de búsqueda");	
		document.form_busq.criterio.focus();
	}else{
		document.form_busq.submit();		
	}
		
}


function Validar()
{  var max=400;

  if (form.apellidos.value == "")

  { alert("Por favor escriba su apellido"); form.apellidos.focus();  return;}

   if (form.nombres.value == "")

  { alert("Por favor escriba su nombre"); form.nombres.focus();  return;}


   if (form.email.value == "")

  { alert("Por favor escriba su email"); form.email.focus();  return;}

   if (form.email.value.indexOf('@', 0) == -1 ||

      form.email.value.indexOf('.', 0) == -1)

  { alert("Error, Email invalido"); form.email.focus(); return; }


   if (form.fono1.value == "")

  { alert("Por favor escriba un telefono de contacto"); form.fono1.focus();  return;}


document.form.submit()

}

function onKeyPress () {
  var keycode;
  if (window.event) keycode = window.event.keyCode;
  else if (e) keycode = e.which;
  else return true;
  if (keycode == 13) {
    alert("Please clic on send button");
    return false
  }
return true
}
document.onkeypress = onKeyPress;


function Reset()

{ document.form.reset()
}

function imprimir() {
  if (window.print)
    window.print()
  else
    alert("Disculpe, su navegador no soporta esta opcion.");}

