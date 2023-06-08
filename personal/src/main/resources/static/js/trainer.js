$(document).ready(function(){

$.urlParam = function(name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null) {
        return null;
    } else {
        return results[1] || 0;
    }
}

let idx = $.urlParam('idx');

console.log(idx);

for(var a=1; a<24; a++) {

$('#time'+a).click(function() {
const option= $('#date').val();
const button=$(this).text();

var a = option+"T"+button;
console.log(a);
                        $.ajax({
                            type: "GET" ,
                            async: false ,
                            url: "/main/matching?time="+a+"&idx="+idx,
                            timeout: 3000,
                            success: function(res) {
                            console.log(123);
                            alert(res);
                            }
                            ,error: function(res) {
                            console.log(1234);
                            }
                            });
});


}


$.get('/main/trainer-find?idx='+idx, function(response) {
                    const data = response;
                    console.log("this is data"+data);
                    console.log(Object.keys(data));
                    console.log(Object.values(data));
                    $('#trainer').empty();
                                if (Object.keys(data).length > 0){
                                    var tb= $("<table />");
                                var idx = Object.values(data)[0];
                                var trainerId = Object.values(data)[1];
                                var trainerPw = Object.values(data)[2];
                                var location = Object.values(data)[3];
                                var name = Object.values(data)[4];
                                var profile = Object.values(data)[5]
                                var center = Object.values(data)[6];
                                var availableFrom = Object.values(data)[7];
                                var availableTo = Object.values(data)[8];
                                var createdAt = Object.values(data)[11];
                                var updatedAt = Object.values(data)[12];
                                var avF = parseInt(availableFrom);
                                var avT = parseInt(availableTo);

                                        var innerHtml = "";

                                        innerHtml += "<tr>";
                                        innerHtml += "<td>"+"선생님:"+name+"</td>";
                                        innerHtml += "<hr/>";
                                        innerHtml += "<td>"+"센터:"+center+"</td>";
                                        innerHtml += "<td>"+"선택 시작 시간:"+availableFrom+"~"+"</td>";
                                        innerHtml += "<td>"+"선택 마감 시간:"+availableTo+"</td>";
                                        innerHtml +="</tr>";

                                        tb.append(innerHtml);

                                    $("#trainer").append(tb);

                                    for(var a=avF; a<avT+1; a++) {
                                    $('#time'+a).css({"background-color": "blue", "color":"white"});
                                    }




                                }
                });


});