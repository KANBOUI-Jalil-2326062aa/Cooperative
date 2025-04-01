<?php

namespace modules\controllers;

require __DIR__ . '/../views/HomepageView.php';

use modules\views\HomepageView;

class HomepageController {
    public function execute() {
        (new HomepageView())->show();
    }
}