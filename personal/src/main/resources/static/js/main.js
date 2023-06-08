$(document).ready(function () {

    $('#searchButton').click(function() {
        const location= $('#searchBox').val();
        console.log(location);
        $.get('/main/find?location=' + encodeURI(location) , function(response) {
            const data = response;
            console.log(data);
            $('#trainer-list').empty();
                        if (data.length > 0){
                            var tb= $("<table />");

                            for(var i in data){
                                var index = data[i].index;
                                var idx = data[i].idx;
                                var trainerId = data[i].trainerId;
                                var trainerPw = data[i].trainerPw;
                                var location = data[i].location;
                                var name = data[i].name;
                                var profile = data[i].profile;
                                var center = data[i].center;
                                var availableFrom = data[i].availableFrom;
                                var availableTo = data[i].availableTo;
                                var createdAt = data[i].createdAt;
                                var updatedAt = data[i].updatedAt;

                                var innerHtml = "";

                                innerHtml += "<tr>";
                                innerHtml += "<td id='name'>"+name+"</td>";
                                innerHtml += "<td>"+profile+"</td>";
                                innerHtml += "<td>"+location+"</td>";
                                innerHtml += "<td>"+center+"</td>";
                                innerHtml += "<td>"+availableFrom+"</td>";
                                innerHtml += "<td>"+availableTo+"</td>";
                                innerHtml += "<td><button class='item-"+idx+"'>매칭하기</button></td>";
                                innerHtml +="</tr>";

                                tb.append(innerHtml);
                            }
                            $("#trainer-list").append(tb);

                        }
        });
        })


                $.get('/main/review', function(response) {
                    const data = response;
                    console.log(data);
                    $('#review-list').empty();
                                if (data.length > 0){
                                    var tb= $("<table />");

                                    for(var i in data){
                                        var index = data[i].index;
                                        var idx = data[i].idx;
                                        var userIdx = data[i].userIdx;
                                        var userId = data[i].userId;
                                        var trainerIdx = data[i].trainerIdx;
                                        var title = data[i].title;
                                        var contents = data[i].contents;
                                        var rating = data[i].rating;
                                        var createdAt = data[i].createdAt;
                                        var updatedAt = data[i].updatedAt;

                                        var innerHtml = "";

                                        innerHtml += "<tr>";
                                        innerHtml += "<td>"+"제목:"+title+"</td>";
                                        innerHtml += "<hr/>";
                                        innerHtml += "<td>"+"내용:"+contents+"</td>";
                                        innerHtml +="</tr>";

                                        tb.append(innerHtml);
                                    }
                                    $("#review-list").append(tb);

                                }
                });


                    $(document).on("click", "[class*='item-']", function() {
                        let index = $(this).attr('class');
                        let indexx = index.split('-')[1];
                        let name = $(this).attr('name');
                        console.log(indexx);
                        $.ajax({
                            type: "GET" ,
                            async: false ,
                            url: "/main/trainer-idx?idx="+indexx,
                            timeout: 3000,
                            success: function(data) {
                            let url= "/main/trainer-idx?idx="+indexx;
                            location.replace(url);
                            }
                        });
                    });







});