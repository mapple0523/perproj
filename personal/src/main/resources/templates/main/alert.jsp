<%@page language="java" contextType="text/html; charset=euc-kr" pageEncoding="euc-kr"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="euc-kr">
<title>alertTest</title>
</head>
<body>
	<script type = "text/javascript">
    	var message = "${message}";
        var url = "${url}";
        alert(message);
	</script>
</body>
</html>