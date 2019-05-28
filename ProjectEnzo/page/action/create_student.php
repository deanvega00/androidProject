<?php
session_start();
include ("./../database/connect.php");
$id=$_POST["studid"];
$name=$_POST["Firstname"];
$lastname=$_POST["Lastname"];
$address=$_POST["address"];
$birthday=$_POST["birthday"];
$contact=$_POST["phone"];
$email=$_POST["email"];
$password=$_POST["password"];
$course=$_POST["course"];
$datecreated=date("Y-m-d H:i");


$date1 = new DateTime('now');
$date2 = $date1->diff(new DateTime($birthday));


$date1 = new DateTime('now');
$date2 = $date1->diff(new DateTime($birthday));
if ($date2->y >= 18) {
$sql = "SELECT * FROM tbl_masterlist where id= '{$id}'";
         $result = $conn->query($sql);
            if ($result->num_rows > 0) {
              while($row = $result->fetch_assoc()) {
              			$sql = "SELECT * FROM tbl_register where id ='{$id}'";
									$result1 = $conn->query($sql);
				       					if ($result1->num_rows == 0) {
				              					$query ="INSERT INTO tbl_register(id,name,lastname,address,email,contact,birthday,password,datecreated,course) "; 
												$query .="VALUE('{$id}','{$name}','{$lastname}','{$address}','{$email}','{$contact}','{$birthday}','{$password}','{$datecreated}','{$course}')";
												mysqli_query($conn,$query);
												
												$auditid=$_SESSION['id'];
												$auditname = $_SESSION['username'] . ' has created new Student '.$name;
												$auditdate=date("Ymdhis");
												$query="INSERT INTO tbl_audit(user_id,action,datecreated,admin) VALUES('{$auditid}','{$auditname}','{$auditdate}','1')";
												$query = mysqli_query($conn,$query);
													echo '<script>alert("New student is added");
											</script>';
				              				}

                   }

}}else{
	echo '<script>alert("Error");
											</script>';
}

 header("Location: ./../../page-student.php");
?>