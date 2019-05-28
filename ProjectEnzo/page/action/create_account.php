<?php
session_start();
include ("./../database/connect.php");

date_default_timezone_set('Asia/Manila');
$date= date('m d y h i') ; 
$tokentime=date('h');
$tokentime1= date('i');
$tokentime2=date('m') + date('d')+ date('y') +date('i');
$token = 'project' .  $tokentime .'phcl'. $tokentime1 . 'token' . $tokentime2;





$name=$_POST["Firstname"];
$lastname=$_POST["Lastname"];
$address=$_POST["address"];
$birthday=$_POST["birthday"];
$contact=$_POST["phone"];
$username=$_POST["Username"];
$password=$_POST["password"];
$mytoken= $_POST['token'];

if (strlen($password)> 7){
If($token =$mytoken){
	
$auditid=$_SESSION['id'];
$auditname = $_SESSION['username'] . ' has created account for '.$name.' '.$lastname;
$auditdate=date("Ymdhis");
$query="INSERT INTO tbl_audit(user_id,action,datecreated,admin) VALUES('{$auditid}','{$auditname}','{$auditdate}','1')";
$query = mysqli_query($conn,$query);

$password = password_hash($password, PASSWORD_DEFAULT);
$query="INSERT INTO tbl_admin(name,lastname,address,contact,birthday,username,password,active) VALUES('{$name}','{$lastname}','{$address}','{$contact}','{$birthday}','{$username}','{$password}',1)";							
$query = mysqli_query($conn,$query);
	
	echo '<script>window.location.href="./../../page-admin.php";
		alert("You have created an account");
</script>';
	
}
else
{
	echo '<script>
		alert("Invalid Token");
</script>';
}

}
else
{
	echo '<script>
		alert("Password must have eight characters");
</script>';
}

	



?>