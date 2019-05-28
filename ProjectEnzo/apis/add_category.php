<?php
//connection fiel here
include'config.php';

//variable declare here

$category_name=$_POST['category_name'];

$category_detail=$_POST['category_detail'];

$category=$_POST['category'];


if($category_name==null || $category_detail==null || $category==null){

	$res=array("status"=>0,"message"=>"check parameter");

}else{

	$check=mysqli_query($conn,"insert into tbl_categoryname(category,category_name,category_detail)values('$category','$category_name','$category_detail')");

	if($check){

		$res=array("status"=>1,"message"=>"category post successfully");

	}else{

		$res=array("status"=>0,"message"=>"error occur");

	}

}

echo json_encode($res);

?>