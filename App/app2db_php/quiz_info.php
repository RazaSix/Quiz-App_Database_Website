<?php


//Getting the correct id by dividing by matching number //
$date_rand = date("d");



$qid = $_GET['q']/$date_rand;
//Changing of id in url would not match in database & return 0 results
echo '!!!!!!!!!!!!!QUIZ!!!!!!!!!!!!!!!!';

$database1 = new DatabaseTable($pdo,'Questionnaires', 'Questionnaire_ID');
$databaseb = new DatabaseTable($pdo,'Creators', 'Creator_ID');
//Select auto generated questionnaire id
$select = $database1->find('Questionnaire_ID',$qid);

foreach($select as $row) {
	$qid = $row['Questionnaire_ID'];
	$qtitle = $row['Title'];
	$qdesc = $row['Description'];
	$qcreator = $row['Creator_ID'];
	$qdate_created = $row['Date_Created'];
	
	$postedDate = date("d M Y h:i:s", strtotime($qdate_created));
	
	
	$select_creators = $databaseb->find('Creator_ID',$qcreator);

	
	foreach($select_creators as $rowline) {

		$fname = $rowline['First_Name'];
		$sname = $rowline['Last_Name'];
	}

	echo '<div><p>Title: '.$qtitle.'<br>
	Created by: '.$qcreator.' on '.$postedDate.'<br>
	Description: '.$qdesc.'<br>
	Created by: '.$fname.' '.$sname.'</p>';
	
}




//Get Questions from Table
// Stores the question IDs in an array
$database2 = new DatabaseTable($pdo,'Questions', 'Question_ID');
$questionsdb = $database2->find('Questionnaire_ID',$qid);

$questionlist = array();
$questionIDlist = array();
$num = 0;

foreach($questionsdb as $rowa) {
	
	$questionid = isset($rowa['Question_ID']) ? $rowa['Question_ID'] : '';
	$qtext = isset($rowa['Text']) ? $rowa['Text'] : '';
	
	//Arrays to hold the questions and their IDs
	$questionlist[$num] = $qtext;
	$questionIDlist[$num] = $questionid;
	$num++;
}
//echo 'QLIST:';
//var_dump($questionlist);


//Get Question 1 Options
// Gets the first question ID from array at position zero
$database3 = new DatabaseTable($pdo,'Question_Options', 'Q_Option_ID');
$optionsdb = $database3->find('Question_ID',$questionIDlist[0]);

$question1options = array();
$q1OptionIDlist = array();
$countone = 0;

foreach($optionsdb as $rowb) {
	
	$q_optionid1 = isset($rowb['Q_Option_ID']) ? $rowb['Q_Option_ID'] : '';
	$optiontext = isset($rowb['Text']) ? $rowb['Text'] : '';
	
	
	$question1options[$countone] = $optiontext;
	$q1OptionIDlist[$countone] = $q_optionid1;
	$countone++;
}

//echo 'OPTION LIST:';
//var_dump($question1options);

//Get Question 2 Options
$database4 = new DatabaseTable($pdo,'Question_Options', 'Q_Option_ID');
$optionsdb = $database4->find('Question_ID',$questionIDlist[1]);

$question2options = array();
$q2OptionIDlist = array();
$countoneb = 0;

foreach($optionsdb as $rowc) {
	
	$q_optionid2 = isset($rowc['Q_Option_ID']) ? $rowc['Q_Option_ID'] : '';
	$optiontext = isset($rowc['Text']) ? $rowc['Text'] : '';
	
	
	$question2options[$countoneb] = $optiontext;
	$q2OptionIDlist[$countoneb] = $q_optionid2;
	$countoneb++;
}



//Get Question 3 Options
$database5 = new DatabaseTable($pdo,'Question_Options', 'Q_Option_ID');
$optionsdc = $database5->find('Question_ID',$questionIDlist[2]);

$question3options = array();
$q3OptionIDlist = array();
$countonec = 0;

foreach($optionsdc as $rowd) {
	
	$q_optionid3 = isset($rowd['Q_Option_ID']) ? $rowd['Q_Option_ID'] : '';
	$optiontext = isset($rowd['Text']) ? $rowd['Text'] : '';
	
	
	$question3options[$countonec] = $optiontext;
	$q3OptionIDlist[$countonec] = $q_optionid3;
	$countonec++;
}

// For keeping the quiz ID as a GET variable in the URL
$old_qid = $qid*$date_rand;

?>