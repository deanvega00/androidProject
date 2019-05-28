<?php 
session_start();
include ("./Page/database/connect.php");
include ('./include/page-header.php');
if (!isset($_SESSION["id"])){header("location: ./page-login.php");}

?>

    <div id="right-panel" class="right-panel">
        <div class="breadcrumbs">
            <div class="col-sm-12">
                <div class="page-header">
                    <div class="page-title">
                      
                    </div>
                      <center>
<img src="images/logo2.png" height="200px" width="200px"><br>
<h1>Tarlac Sate University College of Law and Criminal Justice</h1></center><hr>

	<div>
<center><h2 style="color:red;">Goal</h2><br></center>
<p style="text-align:justify; margin-right: 40px; color:black;">The field of criminology is a study of crime and the various agencies of justice as they operate and react to crime, criminals and the victims. it is therefore the mission of the College of Criminal Justice Education – Criminology program to provide the community with professionally competent and morally upright graduates who can deliver efficient and effective services in crime prevention, crime detection and investigation, law enforcement, and the custody and the rehabilitation of offenders. The College of Criminal Justice Education – Criminology Department is envisioned to be actively and continually involved in producing graduates who have the knowledge and skills in addressing the problem of criminality in the country and the competence to meet the challenge of globalization in the field of criminology. (Extracted from CMO 21 s.2005) 
	
</p>
<center><h2 style="color:red;">Objectives</h2><br></center>
<p style="text-align:justify; margin-right: 40px; color:black;">
   1. Provide quality instructions and guided experience through qualified and competent instructors by which students learn knowledge and skills essential to the practice of criminology;<br><br>
2. Promote leadership, integrity, discipline, love and respect to environment, accountability and responsibility to university, community and country;<br><br>
3. Provide students a broad socio-cultural background and understanding through the outcomes-based education program; and<br><br>
4. Conduct research studies, extension program and community development that will strengthen the practice of criminology profession in the country and to help the community in the maintenance of peace and order.
    
    
</p>
	</div>


                </div>
            </div>
          
        </div>

        <div class="content mt-12">
		</div>
	</div>
<?php 

include ('./include/page-footer.php');
?>

      
   