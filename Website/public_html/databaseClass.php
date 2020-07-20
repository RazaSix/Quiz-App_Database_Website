<?php

class DatabaseTable {
private $table;
private $pdo;
private $primaryKey;
public function __construct($pdo, $table, $primaryKey) {
	$this->pdo = $pdo;
	$this->table = $table;
	$this->primaryKey = $primaryKey;
}

public function save($record) {
	$test = $this->insert($record);
	if(!$test){
		$this->update($record);
	}
}


public function insert($record) {
	$keys = array_keys($record);
	$values = implode(', ', $keys);
	$valuesWithColon = implode(', :', $keys);
	
	$query = 'INSERT INTO ' . $this->table . ' (' . $values . ') VALUES (:' . $valuesWithColon . ')';
	$stmt = $this->pdo->prepare($query);
	
	$success = $stmt->execute($record);
	return $success;
}

public function update($record, $keyid) {
	$query = 'UPDATE ' . $this->table . ' SET ';
	$parameters = [];
	foreach ($record as $key => $value) {
		$parameters[] = $key . ' = :' .$key;
	}
	
	$query .= implode(', ', $parameters);
	$query .= ' WHERE ' . $this->primaryKey . ' = :primaryKey';
	$record['primaryKey'] = $keyid;
	$stmt = $this->pdo->prepare($query);
	
	$stmt->execute($record);
}

public function find($field =null, $value =null, $operator = '=') {
	if($value==null){
		$query = 'SELECT * FROM ' .$this->table;
	}
	else
	{
		$query = 'SELECT * FROM ' . $this->table . ' WHERE '.$field. ' ' . $operator. ' :value';
		//echo $query;
	}
	
	$stmt = $this->pdo->prepare($query);
	$criteria = [
	'value' => $value
	];
	if(!$stmt->execute($criteria))
	{
		var_dump($stmt->errorinfo());
	}
	else
	{
		return $stmt->fetchAll();
		//return $stmt->fetchAll(PDO::FETCH_COLUMN, 0
	}
}

public function delete($field, $value) {
	$stmt = $this->pdo->prepare('DELETE FROM ' . $this->table . ' WHERE ' . $field . ' = :value');
	$criteria = [
	'value' => $value
	];
	$stmt->execute($criteria);
	return $stmt->fetch();
}

}
 ?>