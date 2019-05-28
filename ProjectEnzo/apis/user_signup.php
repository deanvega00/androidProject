<?php
//database connection here
include 'config.php';

//declare all variables here
$id = isset($_POST['studid']) ? $_POST['studid'] : '';
$email = isset($_POST['email']) ? $_POST['email'] : '';
$password = isset($_POST['password']) ? $_POST['password'] : '';
$name = isset($_POST['name']) ? $_POST['name'] : '';
$lastname = isset($_POST['lastname']) ? $_POST['lastname'] : '';
$birthday = isset($_POST['birthday']) ? $_POST['birthday'] : '';
$mobile = isset($_POST['mobile']) ? $_POST['mobile'] : '';

$res = array();
//check null values
if ($id==null ||$email == null || $password == null || $name == null || $lastname == null || $mobile == null || $birthday == null) {
    $res = array('status' => 0, 'message' => 'check parameter');
} else {

    $sql = "SELECT * FROM tbl_masterlist where id= '{$id}'";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $sql = "SELECT * FROM tbl_register where id ='{$id}'";
            $result1 = $conn->query($sql);

            $sql = "SELECT * FROM tbl_register where email ='{$email}'";
            $result2 = $conn->query($sql);

            if ($result1->num_rows == 0) {
                if ($result2->num_rows == 0) {


                    $query = "INSERT INTO tbl_register(id,name,lastname,address,email,contact,birthday,password,datecreated) ";
                    $query .= "VALUE('{$id}','{$name}','{$lastname}','{$name}','{$email}','{$mobile}','{$birthday}','{$password}','{$birthday}')";
                    mysqli_query($conn, $query);
                    if ($query) {
                        $res = array('status' => 1, 'message' => 'insert successfully');
                    } else {
                        $res = array('status' => 0, 'message' => 'error occur');
                    }
                }else{
                    $res = array('status' => 0, 'message' => 'Email already exist');
                    break;

                }
            } else {
                $res = array('status' => 0, 'message' => 'Student Id already exist');
                break;
            }
        }
    } else {
        $res = array('status' => 0, 'message' => 'Student Id is incorrect');

    }

}
echo json_encode($res);
?>