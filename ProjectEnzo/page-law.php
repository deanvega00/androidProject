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
                        <li class="active">Category table</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">

                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
						<div class="form-group"><label for="company" class=" form-control-label">Sort Category </label>
                         <form method="POST" action="">
						 <?php
                              $sql = "SELECT * FROM tbl_category";
                                $result1 = $conn->query($sql);
                                
                                if ($result1->num_rows > 0) {
                                  echo'<select name="code" >';
								   echo '<option class="form-control" value="0">All</option>';
                                    while($row = $result1->fetch_assoc()) {
                                        $cid =$row["category_id"];
                                        $cname=$row["category_name"];
                                      
                                         echo '<option class="form-control" value="'.$cid.'">' .$cname.'</option>';
                                }}
                                    echo'</select>';
                                    
                                        ?>
										<input type="text" name="txtsearch" placeholder="Search">
										<input type="submit" class="btn btn-primary btn-sm" name="search" value="Search">
					</form>
                    </div>
                            <strong class="card-title">Law</strong>
							<button type="button" class="btn btn-success btn-sm float-right" data-toggle="modal" data-target="#addModal"><i class="fa fa-pencil-square-o"></i>Add
</button>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col" hidden>#</th>
                                        <th scope="col">Category Code</th>
                                        <th scope="col">Law Name</th>                                       
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
								<?php
								
								if($code == 0 or $code==''){
										$sql="SELECT * FROM tbl_law LEFT JOIN tbl_category ON tbl_law.category_id=tbl_category.category_id
										where law_name like '%".$search."%'";
								
								
								}else{
									$sql="SELECT * FROM tbl_law LEFT JOIN tbl_category ON tbl_law.category_id=tbl_category.category_id where tbl_category.category_id='".$code."' and law_name like '%".$search."%'";	
								}
								
								$result = $conn->query($sql);

                                if ($result->num_rows > 0) {
                                    while($row = $result->fetch_assoc()) {
                                        $id =$row["id"];
                                        $icode=$row["category_id"];
										$cacode=$row["category_code"];
                                        $name=$row["law_name"];
                                        $detail = $row["law_detail"];
                                    echo '<tr>';
                                    
                                    echo"<td hidden>{$id}</td>";
                                    echo"<td>{$cacode}</td>";
                                    echo"<td>{$name}</td>";
                                    
                                    echo'<td>';
                                     echo "<button class='btn btn-primary btn-xs' data-toggle='modal' data-target='#edit{$id}'>";
                                     echo '<i class="fa fa-pencil">';
                                     echo '</i>';
                                     echo "<button class='btn btn-danger btn-xs' data-toggle='modal' data-target='#delete{$id}'>"; 
                                     echo '<i class="fa fa-trash">';
                                     echo '</i>';
                                     echo'</button>';
    
                                        
                                   echo '</td>';
                                    include ("./page/modal/modal_lawedit.php"); 
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
include ('./page/modal/modal_law.php');
include ('./include/page-footer.php');
?>


   