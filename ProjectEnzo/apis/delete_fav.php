<?php
//database connection here
include 'config.php';

//declare all variables here
$studid = isset($_POST['studid']) ? $_POST['studid'] : '';
$catdId = isset($_POST['catdId']) ? $_POST['catdId'] : '';

$res = array();
//check null values
if ($studid == null || $catdId == null ) {
    $res = array('status' => 0, 'message' => 'check parameter');
} else {

	$check=mysqli_query($conn,"delete from tbl_favorites where stdId='$studid' AND law_id='$catdId'");

	if($check){
                        $res = array('status' => 1, 'message' => 'deleted successfully');
                    } else {
                        $res = array('status' => 0, 'message' => 'error occur');
                    }
}
echo json_encode($res);
?>