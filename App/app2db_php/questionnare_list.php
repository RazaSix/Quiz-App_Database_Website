<?php
/* Selecting Available Questionnaires */
$database1 = new DatabaseTable($pdo,'Questionnaires', 'Questionnaire_ID');
$database2 = new DatabaseTable($pdo,'Creators', 'Creator_ID');



//Select auto generated questionnaire id
$select = $database1->find();


foreach($select as $row) {

	$qid = $row['Questionnaire_ID'];
	$qtitle = $row['Title'];
	$qdesc = $row['Description'];
	$qcreator = $row['Creator_ID'];
	$qdate_created = $row['Date_Created'];
	
	$postedDate = date("d M Y h:i:s", strtotime($qdate_created));
	$date_rand = date("d");
	
	//Hiding the questionnaire id by multiplying by a number
	$nuqid = $qid * $date_rand;
	//$nuqid = $qid * 34;

	$select_creators = $database2->find('Creator_ID',$qcreator);

	
	foreach($select_creators as $rowline) {

		$fname = $rowline['First_Name'];
		$sname = $rowline['Last_Name'];
	}

	echo '<div class="qblocks"> <a class="qlinks" href="quiz.php?q='.$nuqid.'"><p>Title: '.$qtitle.'<br>
	Created by: '.$qcreator.' on '.$postedDate.'<br>
	Description: '.$qdesc.'<br>
	Created by: '.$fname.' '.$sname.'</p></a></div>';
	
	
}