<?php
session_start();
include ("./../database/connect.php");
$id=$_SESSION['id'];
$name=$_POST["fname"];
$lastname=$_POST["lname"];
$address=$_POST["address"];
$birthday=$_POST["bday"];
$contact=$_POST["contact"];
$password=$_POST["password"];

if ($password == ""){

	$query="UPDATE tbl_admin SET name ='$name' , lastname ='$lastname',address='$address',contact='$contact',birthday='$birthday',active=1 where id='$id'";	
}
else{
	$password = password_hash($password, PASSWORD_DEFAULT);
$query="UPDATE tbl_admin SET name ='$name' , lastname ='$lastname',address='$address',contact='$contact',birthday='$birthday',password='$password',active=1 where id='$id'";	



}
		
$query = mysqli_query($conn,$query);

$_SESSION['id']=$id;
$_SESSION['name']=$name;
$_SESSION['lastname']=$lastname;
$_SESSION['address']=$address;
$_SESSION['contact']=$contact;
$_SESSION['birthday']=$birthday;
header("Location: ./../../page-profile.php");
?>