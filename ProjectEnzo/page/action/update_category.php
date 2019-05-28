<?php
session_start();
include ("./../database/connect.php");
$id=$_GET['id'];
$name=$_POST["name"];
$code=$_POST["code"];
$query="UPDATE tbl_category SET category_code ='$code' , category_name ='$name' where category_id='$id'";			
$query = mysqli_query($conn,$query);


$auditid=$_SESSION['id'];
$auditname = $_SESSION['username'] . ' has update category '.$name;
$auditdate=date("Ymdhis");
$query="INSERT INTO tbl_audit(user_id,action,datecreated,admin) VALUES('{$auditid}','{$auditname}','{$auditdate}','1')";
$query = mysqli_query($conn,$query);

header("Location: ./../../page-category.php");
?>