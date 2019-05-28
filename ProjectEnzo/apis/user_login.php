<?php
//database connection here
include'config.php';

//declare all variables here
$email=$_POST['email'];
$password=$_POST['password'];

//check null values
if($email==null || $password==null)
{
	$res=array('status'=>0,'message'=>'check parameter');
}
else
{

	$sql=mysqli_query($conn,"select * from tbl_register where id='$email' and password='$password'");
	
	if(mysqli_num_rows($sql)>0){

         $row=mysqli_fetch_array($sql);

		$res=array('status'=>1,'message'=>'login successfully','name'=>$row['name'],'id'=>$row['id'],'lastname'=>$row['lastname'],'mobile'=>$row['contact'],'email'=>$row['email'],'birthday'=>$row['birthday']);

	}
	else{
		$res=array('status'=>0,'message'=>'wrong credentials');
	}
}
echo json_encode($res);
?>