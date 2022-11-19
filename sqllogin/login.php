<?php 

include 'config.php';
session_start();

if (isset($_POST['submit'])) {
    $email = $_POST['email'];
    $password =($_POST['password']);

    $sql = "SELECT * FROM testwebsite WHERE email='$email' and password='$password'";
    $result = mysqli_query($conn, $sql);
    if ($result->num_rows > 0) {
        while( $row = mysqli_fetch_assoc( $result ) ) {
            echo "<pre style='color:black';>Username: {$row['email']} <br> {$row['password']}</pre>";        
        }

    } else {
        echo "<script>alert('Woops! Email or Password is Wrong.')</script>";
    }
}      

?>
