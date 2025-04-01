<?php

namespace modules\views;

class HomepageView {
    public function show() {
        include 'Header.php';
        echo '<h1>Welcome to the homepage!</h1>';
    }
}