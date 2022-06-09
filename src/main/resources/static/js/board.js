/*
트리용 데이터(JSON)을 화면에 출력
*/

/*var option = [
    {field:"task", 		width:200},
    {field:"startdate", width:100},
    {field:"enddate", 	width:100}
];
var data = [
    {"task": "job1", "startdate": "2016-10-11", "enddate": "2016-11-11"},
    {"task": "job2", "startdate": "2016-10-11", "enddate": "2016-10-30"},
    {"task": "job3", "startdate": "2016-11-12", "enddate": "2016-12-11"}
];

window.onload = function() {
        var tree = $("#tree");
        var table = $("<table>").appendTo(tree);
        table.css({"border-collapse": "collapse", "border": "1px gray solid"});

        $.each( data, function( index, row) {
            var tr = $("<tr>").appendTo(table);
            $.each( option, function( i, fieldInfo ) {
                var td = $("<td>").appendTo(tr);
                td.html( row[fieldInfo.field]);
                td.css({"width": fieldInfo.width+"px", "border": "1px gray solid"});
            });
        });
    }*/



