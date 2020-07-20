<?php
require 'loadtemplate.php';
require 'dbconnection.php';

/* 
Validate Blank spaces in form 
require "validation.js";

Goes in form code before method="POST":

onsubmit="return validateAllFields()"

*/

ob_start();
?>


<div id="content">

<form action="create.php"  method="POST">
	<fieldset>
	<legend>Info:</legend>
	<!-- Questionnaire Info -->
	<p><label class="qinfo" for="qtitle">Title:</label> <input type="text" id="qtitle" name="qtitle" /></p>
	<p><label class="qinfo" for="qauthor">Author:</label> <input type="text" id="qauthor" name="qauthor" /></p>
	<p><label class="qinfo" for="qdesc">Description:</label> <textarea name="qdesc" id="qdesc" rows="2" cols="50"> </textarea></p>
	</fieldset>
	
	<!-- Questions -->
	<hr>
	<div id = "q1">
	<p><label class="question" for="questone">Question 1</label> <input type="text" id="questone" name="questone" cols="500"/></p>
	
	<p><label class="option" for="option1a">Option A</label> <input type="text" id="option1a" name="option1a" /></p>
	<p><label class="option" for="option1b">Option B</label> <input type="text" id="option1b" name="option1b" /></p>
	<p><label class="option" for="option1c">Option C</label> <input type="text" id="option1c" name="option1c" /></p>
	<p><label class="option" for="option1d">Option D</label> <input type="text" id="option1d" name="option1d" /></p>
	<p><label class="option" for="option1e">Option E</label> <input type="text" id="option1e" name="option1e" /></p>
	</div>
	
	<hr>
	
	<div id = "q2">
	<p><label class="question" for="questtwo">Question 2</label> <input type="text" id="questtwo" name="questtwo" /></p>
	
	<p><label class="option" for="option2a">Option A</label> <input type="text" id="option2a" name="option2a" /></p>
	<p><label class="option" for="option2b">Option B</label> <input type="text" id="option2b" name="option2b" /></p>
	<p><label class="option" for="option2c">Option C</label> <input type="text" id="option2c" name="option2c" /></p>
	<p><label class="option" for="option2d">Option D</label> <input type="text" id="option2d" name="option2d" /></p>
	<p><label class="option" for="option2e">Option E</label> <input type="text" id="option2e" name="option2e" /></p>
	</div>
	
	<hr>
	
	<div id = "q3">
	<p><label class="question" for="questthree">Question 3</label> <input type="text" id="questthree" name="questthree" /></p>
	
	<p><label class="option" for="option3a">Option A</label> <input type="text" id="option3a" name="option3a" /></p>
	<p><label class="option" for="option3b">Option B</label> <input type="text" id="option3b" name="option3b" /></p>
	<p><label class="option" for="option3c">Option C</label> <input type="text" id="option3c" name="option3c" /></p>
	<p><label class="option" for="option3d">Option D</label> <input type="text" id="option3d" name="option3d" /></p>
	<p><label class="option" for="option3e">Option E</label> <input type="text" id="option3e" name="option3e" /></p>
	</div>
	
	<hr>
	
	<input type="submit" value="Create" name="submit" />
	</p>
</form>


</div>



<?php
/********************/
/* Database Inserts */
/********************/


$database1 = new DatabaseTable($pdo,'Questionnaires', 'Questionnaire_ID');


if (isset($_POST['submit'])) 
{

/**************************/
/* Questionnaires Details */
/**************************/

date_default_timezone_set('Europe/London');
$dt = new DateTime();
$now =  $dt->format('Y-m-d H:i:s');

$details = [
			'title' => $_POST['qtitle'],
			'description' => $_POST['qdesc'],
			'date_created' => $now,
			'creator_id' => 111			
		];

$inserts  = $database1->insert($details);




/*******************/
/* Questions Insert */
/*******************/
$database2 = new DatabaseTable($pdo,'Questions', 'Question_ID');

//Select auto generated questionnaire id
$selectid = $database1->find('Title', $_POST['qtitle']);
$qid='';
foreach($selectid as $row) {
	$qid = $row['Questionnaire_ID'];
}

//var_dump($selectid);
//Insert each question with the correct questionnaire ID
$questionone = [
		'questionnaire_id' => $qid,
		'text' => $_POST['questone']
	];
$qinsertone  = $database2->insert($questionone);



$questiontwo = [
		'questionnaire_id' => $qid,
		'text' => $_POST['questtwo']
	];

$qinserttwo  = $database2->insert($questiontwo);


$questionthree = [
		'questionnaire_id' => $qid,
		'text' => $_POST['questthree']
	];
$qinsertthree  = $database2->insert($questionthree);
	


/********************/
/* Question Options */
/********************/

//Select auto generated question id
//Not using database functions due to customisation need for query.

//Selecting Question 1
$results = $pdo->prepare('SELECT * FROM Questions WHERE Text = :text AND Questionnaire_ID = :f_qid');

	$criteria = [
		'text' => $_POST['questone'],
		'f_qid' => $qid
	];

	$results->execute($criteria);

	while ($row = $results->fetch()) {
		$question1id = $row['Question_ID'];
	}


//Selecting Question 2
$results = $pdo->prepare('SELECT * FROM Questions WHERE Text = :text AND Questionnaire_ID = :f_qid');

	$criteria = [
		'text' => $_POST['questtwo'],
		'f_qid' => $qid
	];

	$results->execute($criteria);

	while ($row = $results->fetch()) {
		$question2id = $row['Question_ID'];
	}


//Selecting Question 3
$results = $pdo->prepare('SELECT * FROM Questions WHERE Text = :text AND Questionnaire_ID = :f_qid');

	$criteria = [
		'text' => $_POST['questthree'],
		'f_qid' => $qid
	];

	$results->execute($criteria);

	while ($row = $results->fetch()) {
		$question3id = $row['Question_ID'];
	}


//Insert each option with the correct question id
$database3 = new DatabaseTable($pdo,'Question_Options', 'Q_Option_ID');

//echo 'Q1 Option: '.$question1id;
//Question 1 Options
$option1a = [
		'question_id' => $question1id,
		'text' => $_POST['option1a'],
		'option_letter' => 'A'
	];

$optioninsert1a  = $database3->insert($option1a);

$option1b = [
		'question_id' => $question1id,
		'text' => $_POST['option1b'],
		'option_letter' => 'B'
	];

$optioninsert1b  = $database3->insert($option1b);

$option1c = [
		'question_id' => $question1id,
		'text' => $_POST['option1c'],
		'option_letter' => 'C'
	];

$optioninsert1c  = $database3->insert($option1c);

$option1d = [
		'question_id' => $question1id,
		'text' => $_POST['option1d'],
		'option_letter' => 'D'
	];

$optioninsert1d  = $database3->insert($option1d);

$option1e = [
		'question_id' => $question1id,
		'text' => $_POST['option1e'],
		'option_letter' => 'E'
	];

$optioninsert1e  = $database3->insert($option1e);
//-----------------

//Question 2 options
$option2a = [
		'question_id' => $question2id,
		'text' => $_POST['option2a'],
		'option_letter' => 'A'
	];

$optioninsert2a  = $database3->insert($option2a);

$option2b = [
		'question_id' => $question2id,
		'text' => $_POST['option2b'],
		'option_letter' => 'B'
	];

$optioninsert2b  = $database3->insert($option2b);

$option2c = [
		'question_id' => $question2id,
		'text' => $_POST['option2c'],
		'option_letter' => 'C'
	];

$optioninsert2c  = $database3->insert($option2c);

$option2d = [
		'question_id' => $question2id,
		'text' => $_POST['option2d'],
		'option_letter' => 'D'
	];

$optioninsert2d  = $database3->insert($option2d);

$option2e = [
		'question_id' => $question2id,
		'text' => $_POST['option2e'],
		'option_letter' => 'E'
	];

$optioninsert2e  = $database3->insert($option2e);
//-----------------

//Question 3 options
$option3a = [
		'question_id' => $question3id,
		'text' => $_POST['option3a'],
		'option_letter' => 'A'
	];

$optioninsert3a  = $database3->insert($option3a);

$option3b = [
		'question_id' => $question3id,
		'text' => $_POST['option3b'],
		'option_letter' => 'B'
	];

$optioninsert3b  = $database3->insert($option3b);

$option3c = [
		'question_id' => $question3id,
		'text' => $_POST['option3c'],
		'option_letter' => 'C'
	];

$optioninsert3c  = $database3->insert($option3c);

$option3d = [
		'question_id' => $question3id,
		'text' => $_POST['option3d'],
		'option_letter' => 'D'
	];

$optioninsert3d  = $database3->insert($option3d);

$option3e = [
		'question_id' => $question3id,
		'text' => $_POST['option3e'],
		'option_letter' => 'E'
	];

$optioninsert3e  = $database3->insert($option3e);
//-----------------

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//Add option letters in question options table
//Manual input in 
/*$option3e = [
		'question_id' => $questionid,
		'text' => $_POST['option3e']
		'option_leeter' => 'E'!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	];*/

}



/************************/
/* Loading Page Content */
/************************/
$content = ob_get_clean();

$templateVars = [
	'myTitle' => 'Magic3Q',
	'myHeader' => 'Create',
	'content' => $content
];

// Load the template with the page variables
$pageLayout = loadTemplate('layout.php', $templateVars);

echo $pageLayout;
?>