<html>
<body>
<h2>Hello World!</h2>
<script src="js/jquery-2.1.4.js"></script>

<script src="js/common.js"></script>

<script type="text/javascript">
    function ajaxInteger(){

        $.ajax({
            type: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            url: 'http://cxw.s1.natapp.cc/createTicketCode',
            data: {ia: 10010, ib: 10012},
            success: function(res){
                alert(res);
            }
        });
    }
</script>

<div>
    <button type="button" onclick="ajaxInteger()">ajaxInteger</button>
</div>

</body>

</html>
