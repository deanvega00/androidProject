<?php
//connection fiel here
include'config.php';

//variable declare here

$category_id=$_POST['category_id'];



if ($category_id==null){

	$res=array("status"=>0,"message"=>"check parameter");

}else{

	$check=mysqli_query($conn,"delete from  tbl_categoryname where category_id='$category_id'");

	if($check){

		$res=array("status"=>1,"message"=>"category delete successfully");

	}else{

		$res=array("status"=>0,"message"=>"error occur");

	}

}

echo json_encode($res);

?>