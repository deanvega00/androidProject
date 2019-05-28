<?php
session_start();
include ("./../database/connect.php");
$id=$_GET['id'];
$name=$_POST["Firstname"];
$lastname=$_POST["Lastname"];
$address=$_POST["address"];
$birthday=$_POST["birthday"];
$contact=$_POST["phone"];
$email=$_POST["email"];
$password=$_POST["password"];
$password = password_hash($password, PASSWORD_DEFAULT);
$query="UPDATE tbl_register SET name ='$name' , lastname ='$lastname',address='$address',contact='$contact',birthday='$birthday',password='$password',email='$email',active=1 where id='$id'";			
$query = mysqli_query($conn,$query);

$auditid=$_SESSION['id'];
$auditname = $_SESSION['username'] . ' has update student '.$name;
$auditdate=date("Ymdhis");
$query="INSERT INTO tbl_audit(user_id,action,datecreated,admin) VALUES('{$auditid}','{$auditname}','{$auditdate}','1')";
$query = mysqli_query($conn,$query);

header("Location: ./../../page-student.php");
?>