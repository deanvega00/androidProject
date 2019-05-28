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

                    $query = "INSERT INTO tbl_favorites(stdId,law_id) ";
                    $query .= "VALUE('{$studid}','{$catdId}')";
                    mysqli_query($conn, $query);
                    if ($query) {
                        $res = array('status' => 1, 'message' => 'insert successfully');
                    } else {
                        $res = array('status' => 0, 'message' => 'error occur');
                    }
}
echo json_encode($res);
?>