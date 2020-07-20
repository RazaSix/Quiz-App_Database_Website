<!DOCTYPE html>
<html>
	<head>
	<title>
	
	<?php
		echo $myTitle;
	?>
	</title>
	
	<link rel="stylesheet" href="styles/main.css" media="screen" />
	<link rel="stylesheet" href="styles/mobile.css" media="screen and (max-width: 800px)" /> 
	</head>
	<body id="clicked">
		
		<header>
		<div id="content_header"><?php	echo '<h2>'.$myHeader.'</h2>';	?>
			
		
		</div>

		<nav>
				<ul>
				  <li class= "links"><a class="link_colour" href="index.php">Home</a></li>
				  <li class= "links"><a class="link_colour" href="about.php">About</a></li>
			</ul>
			</nav>
			<a href = "#clicked" class="shownav" ></a>
			<a href = "#" class="hidenav" ></a>
			
		</header>
		
			<main>
				<?php echo $content; ?>
			</main>
			
	
		<footer>
			&copy; 
				Footer
				<?php 
				date_default_timezone_set("Europe/London");
				echo date("Y"); 
				?> 
		</footer>
	</body>
</html>