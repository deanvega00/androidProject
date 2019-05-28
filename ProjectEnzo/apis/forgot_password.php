<?php
//database connection here
include'config.php';

//declare all variables here
$email=$_POST['email'];

$code=$_POST['otp'];
//check null values
if($email==null || $code==null)
{
	$res=array('status'=>0,'message'=>'check parameter');
}
else
{   
	$result=mysqli_query($conn,"select * from tbl_register where email='$email'");
	if(mysqli_num_rows($result)>0){
        
	    $update=mysqli_query($conn,"update tbl_register set  forgot_code='$code' where email='$email'"); 

		$res=array('status'=>1,'message'=>'successfully send otp on your email address');
	}else{
		$res=array('status'=>0,'message'=>'email not exist');
	}
	
}
echo json_encode($res);
?>