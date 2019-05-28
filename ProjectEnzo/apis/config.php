<?php

$servername = "localhost";
$username = "u819514852_droid";
$password = "K1yOnvPjJmlW";
$dbname = "u819514852_droid";
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

?>