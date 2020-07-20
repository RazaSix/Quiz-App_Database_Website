<?php
require 'loadtemplate.php';

ob_start();
?>




<div id="content">
<p><!--Blank -->&nbsp;</p>
<p><!--Blank -->&nbsp;</p>
<div class="noticeBoard">
Welcome to Magic3Q. Create questionnaires for the little app that could!!

</div>
<p><!--Blank -->&nbsp;</p>


<form action="index.php">
  Email:<br>
  <input type="text" name="email" placeholder="Email">
  <br>
  Password:<br>
  <input type="password" name="password">
  <br><br>
  <input type="submit" value="Submit">
</form> 

<a href="user_home.php">Logged In</a>

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