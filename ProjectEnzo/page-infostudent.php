<?php 
session_start();
include ("./page/database/connect.php");
include ('./include/page-header.php');
if (!isset($_SESSION["id"])){header("location: ./page-login.php");}

$code = null;

if(isset($_POST["code"]))
{
$code = $_POST["code"];
}

$search=null;
if(isset($_POST["txtsearch"]))
{
$search = $_POST["txtsearch"];
}
?>


    <div id="right-panel" class="right-panel">
        <div class="breadcrumbs">
            <div class="col-sm-4">
                <div class="page-header float-left">
                    <div class="page-title">
                        <h1>Dashboard</h1>
						 
                    </div>
                </div>
            </div>
           <div class="col-sm-8">
                <div class="page-header float-right">
                    <div class="page-title">
                        <ol class="breadcrumb text-right">
                            <li><a href="#">Dashboard</a></li>
                            <li class="active">Student Token</li>
                        </ol>
                    </div>
                </div>
            </div>  
        </div>
	

        <div class="content mt-3">
                <div class="animated fadeIn">
                <div class="row">

              <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header">
                            
<strong class="card-title">Student Token</strong>
<form method="POST" action="">
                        <input type="text" name="txtsearch" placeholder="Search">
										<input type="submit" class="btn btn-primary btn-sm" name="search" value="Search">
					</form>


<button type="button" class="btn btn-success btn-sm float-right" data-toggle="modal" data-target="#addModal">
<i class="fa fa-pencil-square-o"></i>Add
</button>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                              <thead>
                                <tr>
                                  <th scope="col">User Id</th>
                                  <th scope="col">Name</th>
                                  <th scope="col">Course</th>
								<th scope="col">Token</th>
                                
                                </tr>
                              </thead>
                              <tbody>
                              <?php
							  
							  if($search == ''){
										$sql="SELECT * FROM tbl_masterlist";
								
								
								}
								else{
									$sql="SELECT * FROM tbl_masterlist
										where lastname like '%".$search."%'";
								}
								
									$search='';
							  
                              
                                  $result = $conn->query($sql);
                                    if ($result->num_rows > 0) {
                                        while($row = $result->fetch_assoc()) {
                                            $id =$row["id"];
                                            $name=$row["name"].' '.$row["lastname"]; ;
                                                                
                                            $course=$row["course"];
                                            $token=$row["tokenid"];   
                                          
                                             

									
                                                echo '<tr>';                                    
                                                echo"<td>{$id}</td>";
                                                echo"<td>{$name}</td>";
                                                echo"<td>{$course}</td>";
                                                echo"<td>{$token}</td>";                                  
                                               
                                                echo'</tr>';
                                        }
                                    }
                                   ?>
                              </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                </div>
            </div>


        </div>
    </div>
<?php 
include ('./page/modal/modal_student.php');
include ('./include/page-footer.php');
?>


   