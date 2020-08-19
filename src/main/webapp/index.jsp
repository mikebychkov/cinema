<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Hello, world!</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

    <script>

        function getSelectedSeat() {
            let radios = document.getElementsByName('place');
            for (let i = 0; i < radios.length; i++) {
                if (radios[i].checked) {
                    return radios[i].value;
                }
            }
            return -1;
        }

        function uploadHallData() {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/cinema/hall',
                dataType: 'json'
            }).done(function (data) {
                let content = "";
                for (let k in data) {
                    content += "<tr>";
                    content += "<th>" + k + "</th>";
                    for (let c in data[k]) {
                        let seatData = data[k][c];
                        let dis = seatData.accId > 0 ? " disabled=\"disabled\" " : "";
                        let color = seatData.accId > 0 ? " bgcolor=\"red\" " : "";
                        content += "<td " + color + ">";
                        content += "<input type=\"radio\" name=\"place\" value=\"" + seatData.id + "\" " +
                            dis + " > " +
                            "Ряд " + seatData.row + ", Место " + seatData.number;
                        content += "</td>";
                    }
                    content += "</tr>";
                }
                $('#tabledata').html(content);
            })
        }

        uploadHallData();
        setInterval(function() {uploadHallData()}, 20000);

    </script>

</head>
<body>

<div class="container">
    <div class="row pt-3">
        <h4>
            Бронирование места на сеанс
        </h4>
        <table class="table table-bordered" id="table">
            <thead>
            <tr>
                <th style="width: 120px;">Ряд / Место</th>
                <th>1</th>
                <th>2</th>
                <th>3</th>
            </tr>
            </thead>
            <tbody id="tabledata">
            </tbody>
        </table>
    </div>
    <div class="row float-right">
        <button type="button" class="btn btn-success"
                onclick="location.href='<%=request.getContextPath()%>/payment?id=' + getSelectedSeat()">Оплатить
        </button>
    </div>
</div>

</body>
</html>
