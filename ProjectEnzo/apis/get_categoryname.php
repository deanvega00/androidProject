<?php
include'config.php';

//$sql = "SELECT * FROM tbl_category";
//$result = $conn->query($sql);

$result=mysqli_query($conn,"select * from tbl_category");
// while($row=mysqli_fetch_array($result)){
// 	$array['result'][]=array('category_code'=>$row['category_code'],'category_id'=>$row['category_id']);

// }

while($row=mysqli_fetch_array($result)){
	$array[]=array('category_code'=>$row['category_code'],
		                     'category_id'=>$row['category_id'],
  'category_name'=>$row['category_name'],
		                 );

}


if(!empty($array)){
$res=array('status'=>1,'result'=>$array);
}else{
	$res=array('status'=>0,'result'=>'no data found for this category');
}
echo json_encode($res);
?>