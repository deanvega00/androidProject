<?php
session_start();
include ("./../database/connect.php");
$id=$_GET['id'];
$name=$_POST["Firstname"];
$lastname=$_POST["Lastname"];
$address=$_POST["address"];
$birthday=$_POST["birthday"];
$contact=$_POST["phone"];
$password=$_POST["password"];

if ($password == ""){

	$query="UPDATE tbl_admin SET name ='$name' , lastname ='$lastname',address='$address',contact='$contact',birthday='$birthday',active=1 where id='$id'";	
}
else{
	$password = password_hash($password, PASSWORD_DEFAULT);
$query="UPDATE tbl_admin SET name ='$name' , lastname ='$lastname',address='$address',contact='$contact',birthday='$birthday',password='$password',active=1 where id='$id'";	
}
		
$query = mysqli_query($conn,$query);
header("Location: ./../../page-admin.php");
?>