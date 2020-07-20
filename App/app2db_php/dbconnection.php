<?php
require 'databaseClass.php';

$server = '194.81.104.22';
$username = 's14438658';
$password = '14438658';
//The name of the schema we created earlier in MySQL workbench
$schema = 'db_csy4010';
$pdo = new PDO('mysql:dbname=' . $schema . ';host=' . $server, $username, $password);
?>