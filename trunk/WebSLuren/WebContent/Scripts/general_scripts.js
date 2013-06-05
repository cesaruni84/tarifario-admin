/******************************************************************************/
// General Functions

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

function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->

<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
// -->

// Browser check booleans
var	isNav = (navigator.appName.indexOf("Netscape") != -1);
var	isIE = (navigator.appName.indexOf("Microsoft") != -1);

// Cross browser object access
function getObj(name) {
	if (document.getElementById) {
		this.obj = document.getElementById(name);
	} else if (document.all) {
		this.obj = document.all[name];
	} else if (document.layers) {
		this.obj = document.layers[name];
	}
}
// trim whitespaces from beginning and end of a field
function trim(fieldValue) {
	// strips ALL whitespace (debugging only!)
	//return fieldValue.replace(/\s/g,'');
	// strips only from the beginning and end
	return fieldValue.replace(/^\s*|\s*$/g,'');
}




function showpopbox(sName,offsetX,offsetY,sLink) {
	// fix burn through
	fixBurnThru(true);

	// get the position of the associated link
	if ((sLink == null) || (sLink == "")) { sLink = sName+'_link'; }

	if (document.getElementById(sLink) != null) {
		var xPos = findPos(document.getElementById(sLink),'x');
		var yPos = findPos(document.getElementById(sLink),'y');
		// pad the position so the link will not be covered
		xPos = xPos + 5;
		yPos = yPos + 13;
	} else {
		// if there's no link to attach to, put the popup under the nav
		var xPos = 100;
		var yPos = 150;
	}
	// move elements if needed
	if (!isNaN(offsetX)) { xPos = xPos + offsetX; }
	if (!isNaN(offsetY)) { yPos = yPos + offsetY; }
	// open the embedded popup
	document.getElementById(sName).style.display = 'block';
	document.getElementById(sName).style.left = xPos + "px";
	document.getElementById(sName).style.top = yPos + "px";
	return;
}
function showinlinepopbox(sName) {
	// fix burn through
    fixBurnThru(true);
	// opens the inline popups
	document.getElementById(sName).style.display = 'block';
	return;
}
function hidepopbox(sName) {
	// Closes the popups
	document.getElementById(sName).style.display = 'none';
	// fix burn through
    fixBurnThru(false);
	return;
}
// finds the position of the click to place the popup
function findPos(obj,axis) {
	var cur = 0;
	if (obj.offsetParent) {
		while (obj.offsetParent) {
			if (axis == 'x') { cur += obj.offsetLeft }
			else { cur += obj.offsetTop }
			obj = obj.offsetParent;
		}
	} else {
		if ((axis == 'x') && (obj.x)) { cur += obj.x; }
		if ((axis == 'y') && (obj.y)) { cur += obj.y; }
	}
	return cur;
}
// fix the burnthrough on select elements in IE, ns6 and 7 do not have burn-through
// this script is duplicated in menu_settings.js as "fixBurn" for external sites (RNT, Unopi, etc)
function fixBurnThru(isHidden) {
	if (document.all) {
		var rows = document.getElementsByTagName('select');
		for( var i = 0, row; row = rows[i]; i++ ) {
			if (isHidden) {
				row.style.visibility = "hidden";
			} else {
				row.style.visibility = "visible";
			}
		}
	}
	return;
}

/******************************************************************************/
// Learn More inline pops

function expandDetails(elementName) {
	document.getElementById('brief_' + elementName).style.display = 'none';
	document.getElementById('detailed_' + elementName).style.display = 'block';
	document.getElementById('close_' + elementName).style.display = 'block';
}
function collapseDetails(elementName) {
	document.getElementById('detailed_' + elementName).style.display = 'none';
	document.getElementById('close_' + elementName).style.display = 'none';
	document.getElementById('brief_' + elementName).style.display = 'block';
}

/******************************************************************************/
// Regular pop ups

function popup(url,width,height,isFullURL,isScrollable) {
	var windowName = 'newwin'+Math.round(Math.random()*10);
	if (isFullURL != true) {
		// FullURL comes from the script tile at the top of each page
		// append it to the url passed in to get a page on this server
		url = fullURL + url;
	}
	if (isScrollable == true) {
		// This popup will have scroll bars
		isScrollable = "yes";
	} else {
		// Default
		// This popup will not have scroll bars
		isScrollable = "no";
	}
	window.open(url, windowName, 'width='+ width +',height='+ height +'toolbars=no,resizable=yes,scrollbars=' + isScrollable + ',status=no');
}
var	noPop = "0"; // when noPopup=1 do not popup window
// NS browser and will not get popup
if (isNav) { noPop=1; }

// these should be overridden on the page where the popunder is inserted
var	sPopURL = "";
var iPopWidth = 100;
var iPopHeight = 100;
var iType = "up";
var bScroll = 0;
// open the popups
function goPop() {
	if ( (sPopURL != "") && (noPop == 0) ) {
		var	popwin = window.open(sPopURL,'wwin','toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=' + bScroll + ',resizable=yes,width=' + iPopWidth + ',height=' + iPopHeight + ',screenY=100,screenX=100,left=100,top=100');
		if (popwin != null) {
			if (iType == 'up') {
				// pop up
				popwin.focus();
			} else {
				// pop under
				popwin.blur();
			}
		}
	}
}
// event handling for IE users
function handler(e)	{
	if (isIE) {
		e=window.event;
		if(e.keyCode && e.keyCode == 13) { noPop=1; }
		if(e.type== "click" ||	e.type== "dblclick") { noPop=1; }
		if(e.type== "unload") {
			if((e.clientX >	0 && e.clientX < 120) && e.clientY < 0 ) {	noPop=1; }
			goPop();
		}
		e.cancelBubble=true;
	}
}
function addhandlers(o)	{
	o.onabort=handler;
	o.onclick=handler;
	o.ondblclick=handler;
	o.onerror=handler;
	o.onsubmit=handler;
	o.onunload=handler;
	o.onkeypress=handler;
}
// NOTE: if starthandlers is present on a page, onclick events will not work for other functions
function starthandlers() {
	if (isIE) {
		addhandlers(window);
		addhandlers(document);
		for(var	d="0"; d < document.links.length; d++)
			addhandlers(document.links[d]);
		for(var	d="0"; d < document.images.length; d++)
			addhandlers(document.images[d]);
		for(var	d="0"; d < document.forms.length; d++) {
			addhandlers(document.forms[d]);
			for(var	e="0"; e < document.forms[d].elements.length; e++)
				addhandlers(document.forms[d].elements[e]);
		}
	}
}




// large accounts functions
function expand(elementName,prefix) {
	// expand a single item
	for (i = 1; i<10; i++) {
		if (document.getElementById(prefix + 'service' + elementName + i)) {
			if (document.all)
				document.getElementById(prefix + 'service' + elementName + i).style.display = 'block';
			else
				document.getElementById(prefix + 'service' + elementName + i).style.display = 'table-row';
		} else { break; }
	}
	document.getElementById('expand' + elementName).style.display = 'none';
	document.getElementById('collapse' + elementName).style.display = 'block';
}
function collapse(elementName,prefix) {
	// collapse a single item
	for (i = 1; i<10; i++) {
		if (document.getElementById(prefix + 'service' + elementName + i)) {
			document.getElementById(prefix + 'service' + elementName + i).style.display = 'none';
		} else { break; }
	}
	document.getElementById('expand' + elementName).style.display = 'block';
	document.getElementById('collapse' + elementName).style.display = 'none';
}
function expandAll(prefix) {
	//find all ids, loop thru and open them
	var trList = document.getElementsByTagName('tr');
	for (i = 0; i < trList.length; i++) {
		currentTR = trList.item(i).id;
		if (currentTR.indexOf(prefix+'service') != -1) {
			// open the item
			if (document.all)
				document.getElementById(currentTR).style.display = 'block';
			else
				document.getElementById(currentTR).style.display = 'table-row';
			// open the arrow associated with this item
			parentItem = currentTR.replace(prefix+'service','');
			parentItem = parentItem.substring(0,parentItem.length-1);
			document.getElementById('expand' + parentItem).style.display = 'none';
			document.getElementById('collapse' + parentItem).style.display = 'block';
		}
	}
	// switch the link to "Collapse All"
	document.getElementById(prefix + 'expandAll').style.display = 'none';
	document.getElementById(prefix + 'collapseAll').style.display = 'block';
}
