<?php
require 'loadtemplate.php';
require 'dbconnection.php';

ob_start();
?>

<div id="content">
<link rel="stylesheet" href="styles/quiz.css"/>
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


<hr>
<form action=<?php echo '"quiz.php?q='.$old_qid.'"'; ?>  method="POST">
<div id="quizq1">
	<p><label class="question" for="questone">Q1:-</label> <?php echo $questionlist[0]; ?> </p>
	
	<p><label class="option" for="option1a">A</label> <input type="radio" id="option1a" name="option1" value="A"> <?php echo $question1options[0]; ?></p>
	<p><label class="option" for="option1b">B</label> <input type="radio" id="option1b" name="option1" value="B"> <?php echo $question1options[1]; ?></p>
	<p><label class="option" for="option1c">C</label> <input type="radio" id="option1c" name="option1" value="C"> <?php echo $question1options[2]; ?></p>
	<p><label class="option" for="option1d">D</label> <input type="radio" id="option1d" name="option1" value="D"> <?php echo $question1options[3]; ?></p>
	<p><label class="option" for="option1e">E</label> <input type="radio" id="option1e" name="option1" value="E"> <?php echo $question1options[4]; ?></p>
</div>
	<hr>
<div id="quizq2">
	<p><label class="question" for="questtwo">Q2:-</label> <?php echo $questionlist[1]; ?> </p>
	
	<p><label class="option" for="option2a">A</label> <input type="radio" id="option2a" name="option2" value="A"> <?php echo $question2options[0]; ?></p>
	<p><label class="option" for="option2b">B</label> <input type="radio" id="option2b" name="option2" value="B"> <?php echo $question2options[1]; ?></p>
	<p><label class="option" for="option2c">C</label> <input type="radio" id="option2c" name="option2" value="C"> <?php echo $question2options[2]; ?></p>
	<p><label class="option" for="option2d">D</label> <input type="radio" id="option2d" name="option2" value="D"><?php echo $question2options[3]; ?></p>
	<p><label class="option" for="option2e">E</label> <input type="radio" id="option2e" name="option2" value="E"> <?php echo $question2options[4]; ?></p>
</div>	
	<hr>
<div id="quizq3">
	<p><label class="question" for="questthree">Q3:-</label> <?php echo $questionlist[2]; ?> </p>
	
	<p><label class="option" for="option3a">A</label> <input type="radio" id="option3a" name="option3" value="A"> <?php echo $question3options[0]; ?></p>
	<p><label class="option" for="option3b">B</label> <input type="radio" id="option3b" name="option3" value="B"> <?php echo $question3options[1]; ?></p>
	<p><label class="option" for="option3c">C</label> <input type="radio" id="option3c" name="option3" value="C"> <?php echo $question3options[2]; ?></p>
	<p><label class="option" for="option3d">D</label> <input type="radio" id="option3d" name="option3" value="D"> <?php echo $question3options[3]; ?></p>
	<p><label class="option" for="option3e">E</label> <input type="radio" id="option3e" name="option3" value="E"> <?php echo $question3options[4]; ?></p>
</div>	
	

	<input type="submit" value="Submit" name="submit" />
	</p>
</form>




</div>


<?php
/*******************
Inserting Responses
********************/
$database6 = new DatabaseTable($pdo,'Answers', 'Answer_ID');



if (isset($_POST['submit'])) 
{
	/* Display Selections
	echo 'Questionnaire_ID: '.$qid;
	echo '<br> Question 1 ID: '.$questionIDlist[0];
	echo '<br> Question 1 Option Picked: '.$_POST['option1'];

	echo '<br> Question 2 ID: '.$questionIDlist[1];
	echo '<br> Question 2 Option Picked: '.$_POST['option2'];

	echo '<br> Question 3 ID: '.$questionIDlist[2];
	echo '<br> Question 3 Option Picked: '.$_POST['option3'];
	*/

	$answers_set1 = [
		'Q_Option' => $_POST['option1'],
		'Question_ID' => $questionIDlist[0],
		'Questionnaire_ID' => $qid
	];
	$answers_insert1  = $database6->insert($answers_set1);


	$answers_set2 = [
		'Q_Option' => $_POST['option2'],
		'Question_ID' => $questionIDlist[1],
		'Questionnaire_ID' => $qid
	];
	$answers_insert2  = $database6->insert($answers_set2);

	$answers_set3 = [
		'Q_Option' => $_POST['option3'],
		'Question_ID' => $questionIDlist[2],
		'Questionnaire_ID' => $qid
	];
	$answers_insert3  = $database6->insert($answers_set3);


	var_dump($answers_set3);
}
?>










<?php
$content = ob_get_clean();

$templateVars = [
	'myTitle' => 'Magic3Q',
	'myHeader' => 'View Questionnaires',
	'content' => $content
];

// Load the template with the page variables
$pageLayout = loadTemplate('layout.php', $templateVars);

echo $pageLayout;
?>