<?php
$host = "localhost";
$user = "id17920358_umang";
$pass = "Dhbsi%P*36e?Rjh>";
$db = "id17920358_myandroid";
$con = mysqli_connect($host,$user,$pass,$db);
if(isset($_GET['na'])){
	$name = $_GET['na'];
	
	$query = "delete from contacts where name = '$name'";

	if(mysqli_query($con,$query)){
		echo "Data Deleted";
	}
	else{
		echo "Deletion Failed";
	}
}
else{
	echo "Empty Data";
}
?>