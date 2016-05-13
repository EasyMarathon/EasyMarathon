<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head> 
<title>json</title> 
<link rel="stylesheet" href="bg/test.css" type="text/css" />
<script type="text/javascript">
var list=[
      	{"name":"中国", 
      	"value":"86", 
      	"province":[
      		{"name":"浙江", 
      		 "value":"310000", 
      		 "city":
          	 [
      			{"name":"杭州","value":"杭州"}, 
      			{"name":"温州","value":"温州"}, 
      			{"name":"宁波","value":"宁波"}
      	 	 ]
      		}, 
      		{"name":"湖北", 
      		 "value":"430000", 
      		 "city":
      	     [
      			{"name":"武汉","value":"wh"}, 
      			{"name":"黄冈","value":"hg"}, 
      			{"name":"襄阳","value":"xy"}
      		 ]
      		}, 
      		{"name":"河北", 
      		 "value":"100000", 
      		 "city":
      	     [
      	     	{"name":"邯郸","value":"hd"}, 
      			{"name":"保定","value":"bd"}, 
      			{"name":"石家庄","value":"sjz"}
      		 ]
      		}, 
      		{"name":"湖南", 
      		 "value":"440000", 
      		 "city":
      	     [
      		 	{"name":"长沙","value":"cs"}, 
      			{"name":"株洲","value":"zz"}, 
      			{"name":"衡阳","value":"hy"}
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
</script> 
</head> 

<body onload="init();"> 
<div id="111"></div>
<select id="country" onchange="toProvince();"> 
<option value="-1">--请选择国家---</option> 
</select> 
<select id="province" onchange="toCity();"> 
<option value="-1">--请选择省份---</option> 
</select> 
<select id="city"> 
<option value="-1">--请选择市区---</option> 
</select> 
<dl>  
            <dt>领导、老师</dt>  
  
            <dd><img src="bg/images/o1.jpg" width="100" height="100" />
            <a href="#">zzzzzzzzzzzzz</a><br>
            <a href="#">zzzzzzzzzzzzz</a><br>
            <a href="#">zzzzzzzzzzzzz</a><br>
            <a href="#">zzzzzzzzzzzzz</a><br>
            <a href="#">zzzzzzzzzzzzz</a><br>
            <a href="#">zzzzzzzzzzzzz</a><br>
            </dd>    
</dl>
</body> 
</html> 