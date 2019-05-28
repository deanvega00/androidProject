<?php
session_start();
include ("./../database/connect.php");
$id=$_GET['id'];
$name=$_POST["name"];
$code=$_POST["code"];
$detail=$_POST["detail"];
$query="UPDATE tbl_law SET category_id ='$code'  ,law_name ='$name', law_detail='$detail' where id='$id'";			
$query = mysqli_query($conn,$query);

$auditid=$_SESSION['id'];
$auditname = $_SESSION['username'] . ' has update law '.$name;
$auditdate=date("Ymdhis");
$query="INSERT INTO tbl_audit(user_id,action,datecreated,admin) VALUES('{$auditid}','{$auditname}','{$auditdate}','1')";
$query = mysqli_query($conn,$query);

header("Location: ./../../page-law.php");
?>