<?php
//connection fiel here
include'config.php';

//variable declare here

$category_id=$_POST['category_id'];

$category_name=$_POST['category_name'];

$category_detail=$_POST['category_detail'];

$category=$_POST['category'];


if($category_name==null || $category_detail==null || $category==null || $category_id==null){

	$res=array("status"=>0,"message"=>"check parameter");

}else{

	$check=mysqli_query($conn,"update  tbl_categoryname set category_name='$category_name',category_detail='$category_detail',category='$category'  where category_id='$category_id'");

	if($check){

		$res=array("status"=>1,"message"=>"update successfully");

	}else{

		$res=array("status"=>0,"message"=>"error occur");

	}

}

echo json_encode($res);

?>