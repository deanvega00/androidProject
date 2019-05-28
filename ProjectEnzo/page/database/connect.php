<?php

$servername = "localhost";
$dbname = "u819514852_droid";

$username = "root";
$password = "";

//$username = "u819514852_droid";
//$password = "K1yOnvPjJmlW";


//$username = "u819514852_droid";
//$password = "K1yOnvPjJmlW";
//$dbname = "u819514852_droid";
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

?>