<?php
include'config.php';
$category=$_POST['category'];
$stdid=$_POST['stdid'];

$data = array();

$result=mysqli_query($conn,"select * from tbl_law where category_id='$category'");
while($row=mysqli_fetch_array($result)){
	$array[]=array('category_name'=>$row['law_name'],
		                     'category_detail'=>$row['law_detail'],
                             'id'=>$row['id'],
		                     'category'=>$row['category_id']);

}

$array1=[];
$result2=mysqli_query($conn,"select * from tbl_favorites where stdId='$stdid'");
while($row=mysqli_fetch_array($result2)){
	$array1[]=array('stdId'=>$row['stdId'],
                     'catdId'=>$row['law_id']);

}

$data['law']=$array;
$data['fav']=$array1;


if(!empty($data)){
$res=array('status'=>1,'result'=>$data);
}else{
	$res=array('status'=>0,'result'=>'no data found for this category');
}
echo json_encode($res);
?>