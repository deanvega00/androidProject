<?php
include'config.php';
$stdId=$_POST['stdId'];
$result=mysqli_query($conn,"select * from tbl_favorites where stdId='$stdId'");
while($row=mysqli_fetch_array($result)){
$id= $row['law_id'];
		$sql=mysqli_query($conn,"select * from tbl_law where id='$id'");
	if(mysqli_num_rows($sql)>0){

         $row1=mysqli_fetch_array($sql);


	$array[]=array(
		            'category_name'=>$row1['law_name'],
                	 'category_detail'=>$row1['law_detail'],
                     'id'=>$row1['id'],
	                 'category'=>$row1['category_id']
                 );

}
}
if(!empty($array)){
$res=array('status'=>1,'result'=>$array);
}else{
	$res=array('status'=>0,'result'=>'no data found for this category');
}
echo json_encode($res);
?>