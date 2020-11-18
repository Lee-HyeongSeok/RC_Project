<?php
	
	$con = mysqli_connect("localhost", "root", "", "test");

	if(mysqli_connect_errno($con)){
		echo "Failed to connect to MySql : " . mysqli_connect_error();
	}

	mysqli_set_charset($con, "utf8");

	$res = mysqli_query($con, "select * from member");
	$result = array();

	while($row = mysqli_fetch_array($res)){
		array_push($result,
			array('name'=>$row['name'], 'id'=>$row['id'], 'pass'=>$row['pass']));
	}
	
	echo json_encode(array("result"=>$result));
	mysqli_close($con);
?>