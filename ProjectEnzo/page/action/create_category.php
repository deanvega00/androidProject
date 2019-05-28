<?php
session_start();
include ("./../database/connect.php");

$id=$_GET['id'];
$name=$_POST["name"];
$code=$_POST["code"];

$query="INSERT INTO tbl_category(category_code,category_name) VALUES('{$code}','{$name}')";							
	$query = mysqli_query($conn,$query);


$auditid=$_SESSION['id'];
$auditname = $_SESSION['username'] . ' has created new Category '.$name;
$auditdate=date("Ymdhis");
$query="INSERT INTO tbl_audit(user_id,action,datecreated,admin) VALUES('{$auditid}','{$auditname}','{$auditdate}','1')";
$query = mysqli_query($conn,$query);



echo '<script>window.location.href="./../../page-category.php";
		alert("You have created an category");
</script>'
?>