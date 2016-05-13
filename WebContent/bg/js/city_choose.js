var list=[
	{"name":"中国", 
	"value":"86", 
	"province":[
		{"name":"浙江", 
		 "value":"310000", 
		 "city":
    	 [
			{"name":"杭州","value":"hangzhou"}, 
			{"name":"温州","value":"wenzhou"}, 
			{"name":"宁波","value":"ningbo"}
	 	 ]
		}, 
		{"name":"湖北", 
		 "value":"430000", 
		 "city":
	     [
			{"name":"武汉","value":"wuhan"}, 
			{"name":"黄冈","value":"huanggang"}, 
			{"name":"襄阳","value":"xiangyang"}
		 ]
		}, 
		{"name":"河北", 
		 "value":"100000", 
		 "city":
	     [
	     	{"name":"邯郸","value":"handan"}, 
			{"name":"保定","value":"baoding"}, 
			{"name":"石家庄","value":"shijiazhuang"}
		 ]
		}, 
		{"name":"湖南", 
		 "value":"440000", 
		 "city":
	     [
		 	{"name":"长沙","value":"changsha"}, 
			{"name":"株洲","value":"zhuzhou"}, 
			{"name":"衡阳","value":"hengyang"}
		 ]
		}
	]
   }
]; 

function init(){ 
var _country=document.getElementById("country"); 
for(var e in list){ 
var opt_1=new Option(list[e].name,list[e].value); 
_country.add(opt_1); 
} 
} 



function toProvince(){ 
var _country=document.getElementById("country"); 
var _province=document.getElementById("province"); 
var _city=document.getElementById("city"); 
var v_country=_country.value; 

_province.options.length=1; 
_city.options.length=1; 

for(var e in list){ 
if(list[e].value==v_country){ 
for( var p in list[e].province){ 
var opt_2=new Option(list[e].province[p].name,list[e].province[p].value); 
_province.add(opt_2); 

} 
break; 
} 
} 
} 


function toCity(){ 
var _country=document.getElementById("country"); 
var _province=document.getElementById("province"); 
var _city=document.getElementById("city"); 

var v_country=_country.value; 
var v_province=_province.value; 

//_province.options.length=1; 
_city.options.length=1; 


for(var e in list){ 
if(list[e].value==v_country){ 
for( var p in list[e].province){ 
//alert(list[e].province[p].value); 
if(list[e].province[p].value==v_province){ 
// alert(list[e].province[p].value); 
for(var cc in list[e].province[p].city){ 
var opt_3=new Option(list[e].province[p].city[cc].name,list[e].province[p].city[cc].value); 
_city.add(opt_3); 
} 

return; 
} 


} 
break; 
} 
} 
}