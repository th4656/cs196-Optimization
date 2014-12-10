<?php
$file_path = "uploads/";

$file_path = $file_path . basename( $_FILES['uploaded_file']['name']);
$file_name = basename($_FILES['uploaded_file']['name'], '.' . $info['extension']);
$withoutExt = preg_replace('/\\.[^.\\s]{3,4}$/', '', $file_name);
if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {
	exec("tesseract $file_path " . "uploads/$withoutExt");
	exec("sed '/^$/d' uploads/$withoutExt" . ".txt" . " > uploads/$withoutExt" . "_NoBlankLines.txt");
	exec("sleep 60 && rm -rf uploads/*");
} else{
	echo "fail";
}
?>
