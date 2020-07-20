<?php
require 'dbconnection.php';
$database6 = new DatabaseTable($pdo,'Answers', 'Answer_ID');


$op = $_POST['optionchoice'];

	$answers_set = [
		'Q_Option' => $op,
		'Question_ID' => 31,
		'Questionnaire_ID' => 21
	];
	$answers_insert1  = $database6->insert($answers_set);
	if($answers_insert1)
	{
		echo 'Success';
	}

?>
