<?php
session_start();
include ("./../database/connect.php");
		$id=$_GET['id'];
		$query="UPDATE tbl_admin SET active=0  where id = '{$id}'";
		$query = mysqli_query($conn,$query);
		
		 
$auditid=$_SESSION['id'];
$auditname = $_SESSION['username'] . ' has set admin '.$id.' inactive';
$auditdate=date("Ymdhis");
$query="INSERT INTO tbl_audit(user_id,action,datecreated,admin) VALUES('{$auditid}','{$auditname}','{$auditdate}','1')";
$query = mysqli_query($conn,$query);


 header("Location: ./../../page-admin.php");
?>

