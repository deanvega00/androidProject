<?php 
session_start();
include ("./page/database/connect.php");
include ('./include/page-header.php');
if (!isset($_SESSION["id"])){header("location: ./page-login.php");}
$code = null;
$search=null;
if(isset($_POST["code"]))
{
$code = $_POST["code"];
}
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
                            <li class="active">Admin table</li>
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
                            
<strong class="card-title">Administrator</strong>
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
                                  <th scope="col">#</th>
                                  <th scope="col">First Name</th>
                                  <th scope="col">Last Name</th>
                                  <th scope="col">Username</th>
								   <th scope="col">Status</th>
                                  <th scope="col">Action</th>
                                </tr>
                              </thead>
                              <tbody>
                              <?php
                               
								$account = $_SESSION['username'];
								if($search == ''){
										$sql="SELECT * FROM tbl_admin where username != '".$account ."'";
								
								
								}else{
									$sql="SELECT * FROM tbl_admin
										where username like '%".$search."%' AND username != '".$account ."'";
								}
								
							
									$search='';
									
                                $result = $conn->query($sql);
                                  $result = $conn->query($sql);
                                    if ($result->num_rows > 0) {
                                        while($row = $result->fetch_assoc()) {
                                            $id =$row["id"];
                                            $name=$row["name"];
                                            $lname=$row["lastname"];   
                                            $uname=$row["username"];
                                             $address=$row["address"];
                                            $contact=$row["contact"];   
                                            $birthday=$row["birthday"];    
												if ($row["active"]==1){
													$status="ACTIVE";
												}
												else{
													$status="INACTIVE";
												}
                                                echo '<tr>';                                    
                                                echo"<td>{$id}</td>";
                                                echo"<td>{$name}</td>";
                                                echo"<td>{$lname}</td>";
                                                echo"<td>{$uname}</td>"; 
												echo"<td>{$status}</td>";												
                                                echo'<td>';
                                                echo "<button class='btn btn-primary btn-xs' data-toggle='modal' data-target='#edit{$id}'>";
                                                echo '<i class="fa fa-pencil">';
                                                echo '</i>';
                                                echo "<button class='btn btn-danger btn-xs' data-toggle='modal' data-target='#delete{$id}'>"; 
                                                echo '<i class="fa fa-trash">';
                                                echo '</i>';
                                                echo'</button>';                                          
                                                echo '</td>';
                                                include ("./page/modal/modal_adminedit.php"); 
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
include ('./page/modal/modal_admin.php');
include ('./include/page-footer.php');
?>


   