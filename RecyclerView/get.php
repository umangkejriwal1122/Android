<?php

$host = "localhost";
$db = "id16020667_mit";  // database name
$user = "id16020667_mit_user";  // database user
$pass = "mN~ue[io1\I)lsoj";   // database password

$con = mysqli_connect($host,$user,$pass,$db);

$sql = "select * from students";

$result = $con->query($sql);

if ($result->num_rows >0) {
 // output data of each row
 while($row[] = $result->fetch_assoc()) {
 
 $tem = $row;
 
 $json = json_encode($tem,JSON_UNESCAPED_UNICODE);
 
 }
 
} else {
 echo "No results";
}
 echo $json;
?>