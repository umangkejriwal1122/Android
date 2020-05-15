<?php
$host = "localhost";   //hostname
$db = "id13619181_umang";   ///database name
$user = "id13619181_umang_user";  //user name
$pass = "Pass@11223344";    //password

$con = mysqli_connect($host,$user,$pass,$db);

$sql = "select * from user";

$res = mysqli_query($con,$sql);  ###execute the query

$result = array();   ##declared a blank array

while($row=mysqli_fetch_array($res)){
	array_push($result,array('id'=>$row['id'],
							'name'=>$row['name'],
							'age'=>$row['age']));
}
echo json_encode(array('data'=>$result));
mysqli_close($con );
?>