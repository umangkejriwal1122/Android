<?php

$host = "localhost";   //hostname
$db = "id13619181_umang";   ///database name
$user = "id13619181_umang_user";  //user name
$pass = "Pass@11223344";    //password

$con = mysqli_connect($host,$user,$pass,$db);

$name = $_GET["n"];
$age = $_GET["a"];

$sql = "insert into user(name,age)values('$name','$age')";

if(!isset($name) || !isset($age)) {
	echo "Please fill the data"
}
else{
	
	$query = "select * from user where name='$name'";
	
	$result = mysqli_query($con,$query);
	
	if(mysqli_num_rows($result)>0){
		echo "Name Already Exist";
	}
	else{
		if(mysqli_query($con,$sql)){
			echo "Data Inserted";
		}
		else{
			echo "Insert Failed";
		}
	}
	
	
	
}




?>