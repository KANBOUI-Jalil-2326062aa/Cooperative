<?php

namespace modules\controllers;

require __DIR__ . '/../views/CommandeView.php';

use modules\views\CommandeView;

class CommandeController {
    public function execute() {
        (new CommandeView())->show();
    }
}