
function fecha() {

var mydate=new Date()
var year=mydate.getYear()
if (year < 1000)
year+=1900
var day=mydate.getDay()
var month=mydate.getMonth()
var daym=mydate.getDate()
if (daym<10)
daym="0"+daym
var dayarray=new Array("Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado")
var montharray=new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre")
document.write(dayarray[day]+" "+daym+" de "+montharray[month]+" de "+year)

}


function hora(){
Stamp = new Date();
year = Stamp.getYear();
if (year < 2000) year = 1900 + year;
	var Hours;
	var Mins;
	var Time;
	Hours = Stamp.getHours();
	if (Hours >= 12) {
	Time = " P.M.";
	}
	else {
	Time = " A.M.";
	}
	if (Hours > 12) {
	Hours -= 12;
	}
	if (Hours == 0) {
	Hours = 12;
	}
	Mins = Stamp.getMinutes();
	if (Mins < 10) {
	Mins = "0" + Mins;
	}
document.write( Hours + ":" + Mins + Time);
}


