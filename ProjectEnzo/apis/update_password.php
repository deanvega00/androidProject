<?php
//database connection here
include'config.php';

//declare all variables here

$email=$_POST['email'];

$password=$_POST['password'];

$otp=$_POST['otp'];
//check null values
if($email==null || $otp==null || $password==null)
{
	$res=array('status'=>0,'message'=>'check parameter');
}
else
{   
	$result=mysqli_query($conn,"select * from tbl_register where email='$email' and forgot_code='$otp'");
	if(mysqli_num_rows($result)>0){
        
	    $update=mysqli_query($conn,"update tbl_register set  password='$password' where email='$email'"); 

		$res=array('status'=>1,'message'=>'successfully change your password');
	}else{
		$res=array('status'=>0,'message'=>'you have not enter correct email and otp');
	}
	
}
echo json_encode($res);
?>