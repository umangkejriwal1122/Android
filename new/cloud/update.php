<?php
$host = "localhost";
$user = "id17920358_umang";
$pass = "Dhbsi%P*36e?Rjh>";
$db = "id17920358_myandroid";

$con = mysqli_connect($host,$user,$pass,$db);

if(isset($_GET['na']) && isset($_GET['mo'])){
	$name = $_GET['na'];
	$mob = $_GET['mo'];

	$query = "update contacts set mobile='$mob' where name = '$name'";

	if(mysqli_query($con,$query)){
		echo "Data Updated";
	}
	else{
		echo "Update Failed";
	}
}
else{
	echo "Empty Data";
}
?>