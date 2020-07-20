<?php
require 'loadtemplate.php';

ob_start();
?>




<div id="content">
<p><!--Blank -->&nbsp;</p>
<p><!--Blank -->&nbsp;</p>
<div class="noticeBoard">
<p>Welcome to Magic3Q. Create questionnaires for the little app that could!!</p>
<p>Welcome to Magic3Q. Create questionnaires for the little app that could!!</p>
<p>Welcome to Magic3Q. Create questionnaires for the little app that could!!</p>
<p>Welcome to Magic3Q. Create questionnaires for the little app that could!!</p>
<p>Welcome to Magic3Q. Create questionnaires for the little app that could!!</p>
<p>Welcome to Magic3Q. Create questionnaires for the little app that could!!</p>
<p>Welcome to Magic3Q. Create questionnaires for the little app that could!!</p>
</div>
<p><!--Blank -->&nbsp;</p>




</div>
<?php
$content = ob_get_clean();

$templateVars = [
	'myTitle' => 'Magic3Q',
	'myHeader' => 'Magic3Q',
	'content' => $content
];

// Load the template with the page variables
$pageLayout = loadTemplate('layout_b.php', $templateVars);

echo $pageLayout;
?>