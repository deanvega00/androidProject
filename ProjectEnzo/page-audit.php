<?php 
session_start();
include ("./page/database/connect.php");
include ('./include/page-header.php');
if (!isset($_SESSION["id"])){header("location: ./page-login.php");}

$search=null;
$search2=null;

if(isset($_POST["txtsearch"]))
{
$search = $_POST["txtsearch"];
}
if(isset($_POST["txtsearch2"]))
{
$search = $_POST["txtsearch2"];
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
                            <li class="active">Admin Audit Trail</li>
                        </ol>
                    </div>
                </div>
            </div>  
        </div>


        <div class="content mt-3">
                <div class="animated fadeIn">
                <div class="row">

              <div class="col-lg-6">
                    <div class="card">
                        <div class="card-header">
                            
<strong class="card-title">Audit Trail</strong>
 <form method="POST" action="">
                        <input type="text" name="txtsearch" placeholder="Search">
										<input type="submit" class="btn btn-primary btn-sm" name="search" value="Search">
					</form> 
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                              <thead>
                                <tr>
                                  <th scope="col">#</th>
                                  <th scope="col">ID</th>
                                  <th scope="col">Action</th>
                                  <th scope="col">Date</th>
								
                                </tr>
                              </thead>
                              <tbody>
                              <?php
                               
								
								if($search == ''){
										$sql="SELECT * FROM tbl_audit where admin=1";
								
								
								}else{
									$sql="SELECT * FROM tbl_audit
										where action like '%".$search."%' and admin=1";
								}
								
									$search='';
									
                                $result = $conn->query($sql);
                                  $result = $conn->query($sql);
                                    if ($result->num_rows > 0) {
                                        while($row = $result->fetch_assoc()) {
                                            $id =$row["id"];
                                            $userid=$row["user_id"];
                                            $action=$row["action"];   
                                            $datecreated=$row["datecreated"];
                                            
                                                echo '<tr>';                                    
                                                echo"<td>{$id}</td>";
                                                echo"<td>{$userid}</td>";
                                                echo"<td>{$action}</td>"; 
												echo"<td>{$datecreated}</td>";
                                                echo'</tr>';
                                        }
                                    }
                                   ?>
                              </tbody>
                            </table>
                        </div>
                    </div>
                </div>
				
				              <div class="col-lg-6">
                    <div class="card">
                        <div class="card-header">
                            
<strong class="card-title">Audit Trail</strong>
 <form method="POST" action="">
                        <input type="text" name="txtsearch2" placeholder="Search">
										<input type="submit" class="btn btn-primary btn-sm" name="search" value="Search">
					</form> 
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                              <thead>
                                <tr>
                                  <th scope="col">#</th>
                                  <th scope="col">ID</th>
                                  <th scope="col">Action</th>
                                  <th scope="col">Date</th>
								
                                </tr>
                              </thead>
                              <tbody>
                              <?php
                               
								
								if($search2 == ''){
										$sql="SELECT * FROM tbl_audit where admin =0";
								
								
								}else{
									$sql="SELECT * FROM tbl_audit
										where action like '%".$search2."%' and admin =0";
								}
								
									$search='';
									
                                $result = $conn->query($sql);
                                  $result = $conn->query($sql);
                                    if ($result->num_rows > 0) {
                                        while($row = $result->fetch_assoc()) {
                                            $id =$row["id"];
                                            $userid=$row["user_id"];
                                            $action=$row["action"];   
                                            $datecreated=$row["datecreated"];
                                            
                                                echo '<tr>';                                    
                                                echo"<td>{$id}</td>";
                                                echo"<td>{$userid}</td>";
                                                echo"<td>{$action}</td>"; 
												echo"<td>{$datecreated}</td>";
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


   