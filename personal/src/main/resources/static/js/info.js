$(document).ready(function() {
function getinfo(){
$.get('/main/user-infomation', function(response) {
                    const data = response;
                    console.log("this is data"+data);
                    $('#trainer').empty();

                    if(data.length>0){
                    var tb= $("<table />");

                    for(var i in data){
                    var idx = data[i].idx;
                    var time = data[i].time;
                    var userIdx = data[i].userIdx;
                    var trainerIdx = data[i].trainerIdx;
                    var createdAt = data[i].createdAt;
                    var updatedAt = data[i].updatedAt;

                    var innerHtml = "";
                    innerHtml += "<tr>";
                    innerHtml += "<td>"+time+"에 신청하신 트레이닝입니다."+"</td>";
                    innerHtml += "<td><button class='item-"+idx+"'>삭제하기</button></td>";
                    innerHtml +="</tr>";

                    tb.append(innerHtml);
                    }
                    $("#trainer").append(tb);

                    }
                    })
                    }
                    getinfo();

                                        $(document).on("click", "[class*='item-']", function() {
                                            let index = $(this).attr('class');
                                            let indexx = index.split('-')[1];
                                            let name = $(this).attr('name');
                                            console.log(indexx);
                                            $.ajax({
                                                type: "GET" ,
                                                async: false ,
                                                url: "/main/matching-delete?idx="+indexx,
                                                timeout: 3000
                                            });
                                            getinfo();
                                        });
                });