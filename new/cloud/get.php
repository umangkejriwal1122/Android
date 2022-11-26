<?php
$host = "localhost";
$user = "id17920358_umang";
$pass = "Dhbsi%P*36e?Rjh>";
$db = "id17920358_myandroid";

$con = mysqli_connect($host,$user,$pass,$db);

$query = "select * from contacts";

$result = mysqli_query($con,$query);

$data = array();

while($row = mysqli_fetch_array($result))
{
	array_push($data,array('id'=>$row['id'],
			'name'=>$row['name'],'mob'=>$row['mobile']));
}
echo json_encode(array('mydata'=>$data));

?>