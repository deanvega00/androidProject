<?php
session_start();
include ("./../database/connect.php");
		$id=$_GET['id'];
		$query="DELETE from tbl_law where id = '{$id}'";
		$query = mysqli_query($conn,$query);

		$auditid=$_SESSION['id'];
$auditname = $_SESSION['username'] . ' has delete law id '.$id;
$auditdate=date("Ymdhis");
$query="INSERT INTO tbl_audit(user_id,action,datecreated,admin) VALUES('{$auditid}','{$auditname}','{$auditdate}','1')";
$query = mysqli_query($conn,$query);

		 header("Location: ./../../page-law.php");
?>



