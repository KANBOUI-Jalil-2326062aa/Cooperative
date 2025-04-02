<?php

require_once __DIR__ . '/modules/controllers/LoginController.php';
require_once __DIR__ . '/modules/controllers/HomepageController.php';
require_once __DIR__ . '/modules/controllers/CommandeController.php';
require_once __DIR__ . '/modules/controllers/PaniersController.php';
require_once __DIR__ . '/modules/controllers/ProduitsController.php';

use modules\controllers\HomepageController;
use modules\controllers\LoginController;
use modules\controllers\CommandeController;
use modules\controllers\PaniersController;
use modules\controllers\ProduitsController;

$page = isset($_GET['page']) ? $_GET['page'] : 'login';

switch ($page) {
    case 'homepage':
        (new HomepageController())->execute();
        break;
    default:
        (new LoginController())->execute();
        break;
    case 'commandes':
        (new CommandeController())->execute();
        break;
    case 'paniers':
        (new PaniersController())->execute();
        break;
    case 'produits':
        (new ProduitsController())->execute();
        break;
}