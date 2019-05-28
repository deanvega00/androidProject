<?php
session_start();
include ("./../database/connect.php");
$name=$_POST["name"];
$code=$_POST["code"];
$detail=$_POST["Detail"];


$query="Select * from tbl_law where law_name='{$name}'";
	$query= mysqli_query($conn,$query);
if ($query->num_rows > 0) {
	echo "<script>
								window.location.href='./../../page-law.php';
								alert('Law already exist!');
							</script>";
}else{
   
	

$query="INSERT INTO tbl_law(category_id,law_name,law_detail) VALUES('{$code}','{$name}','{$detail}')";							
	$query = mysqli_query($conn,$query);
	
$auditid=$_SESSION['id'];
$auditname = $_SESSION['username'] . ' has created new Law '.$name;
$auditdate=date("Ymdhis");
$query="INSERT INTO tbl_audit(user_id,action,datecreated,admin) VALUES('{$auditid}','{$auditname}','{$auditdate}','1')";
$query = mysqli_query($conn,$query);


echo '<script>window.location.href="./../../page-law.php";
		alert("New Law is added");
</script>';
}


?>