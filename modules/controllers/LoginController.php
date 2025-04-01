<?php

namespace modules\controllers;

require_once __DIR__ . '/../models/LoginModel.php';
require_once __DIR__ . '/../views/LoginView.php';

use modules\models\LoginModel;
use modules\views\LoginView;

class LoginController {
    private $loginModel;

    public function __construct() {
        $this->loginModel = new LoginModel();
    }

    public function execute() {
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $username = $_POST['username'];
            $password = $_POST['password'];

            $user = $this->loginModel->authenticate($username, $password);

            if ($user) {
                session_start();
                $_SESSION['user'] = $user;
                header('Location: /?page=homepage');
                exit();
            } else {
                echo 'Invalid username or password.';
            }
        }

        (new LoginView())->show();
    }
}