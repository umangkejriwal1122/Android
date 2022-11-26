<?php
$host = "localhost";
$user = "id17920358_umang";
$pass = "Dhbsi%P*36e?Rjh>";
$db = "id17920358_myandroid";

$con = mysqli_connect($host,$user,$pass,$db);

if(isset($_GET['na']) && isset($_GET['mo'])){
	$name = $_GET['na'];
	$mob = $_GET['mo'];

	$query = "insert into contacts(name,mobile)values('$name','$mob')";

	if(mysqli_query($con,$query)){
		echo "Data Inserted";
	}
	else{
		echo "Insert Failed";
	}
}
else{
	echo "Empty Data";
}
?>