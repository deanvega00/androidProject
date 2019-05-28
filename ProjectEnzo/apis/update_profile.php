<?php
//database connection here
include 'config.php';

//declare all variables here
$email = $_POST['email'];
$name = $_POST['name'];
$lastname = $_POST['lastname'];
$mobile = $_POST['mobile'];
$birthday = $_POST['birthday'];

//check null values
if ($email == null || $name == null || $lastname == null || $mobile == null) {
    $res = array('status' => 0, 'message' => 'check parameter');
} else {
    $result = mysqli_query($conn, "select * from tbl_register where email='$email'");

    if (mysqli_num_rows($result) > 0) {

        $update = mysqli_query($conn, "update tbl_register set name='$name',lastname='$lastname',contact='$mobile' ,birthday='$birthday' where email='$email'");

        if ($update) {
            $res = array('status' => 1, 'message' => 'profile update successfully');
        } else {
            echo $conn->error;
            $res = array('status' => 0, 'message' => 'error occur');
        }


    } else {
        $res = array('status' => 0, 'message' => 'email not match');
    }

}
echo json_encode($res);
?>