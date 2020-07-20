<?php
require 'loadtemplate.php';

ob_start();
?>




<div id="content">
<p><!--Blank -->&nbsp;</p>
User Home page. Will have controls for create, edit and view questionnaire data.
<p><!--Blank -->&nbsp;</p>

<div class="createBtn"> 
<a id = "myButton" href="create.php">CREATE QUESTIONNAIRE</a>
</div>
<div class="createBtn"> 
<a id = "myButton" href="view.php">VIEW QUESTIONNAIRES</a>
</div>


</div>
<?php
$content = ob_get_clean();

$templateVars = [
	'myTitle' => 'Magic3Q',
	'myHeader' => 'Magic3Q',
	'content' => $content
];

// Load the template with the page variables
$pageLayout = loadTemplate('layout.php', $templateVars);

echo $pageLayout;
?>