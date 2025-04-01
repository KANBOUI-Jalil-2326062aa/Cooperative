<?php

namespace modules\views;

class LoginView {
    public function show(){
        echo '<link rel="stylesheet" href="/_assets/styles/Login.css">';
        echo '<div class="login-container">';
        echo '  <h2>Connexion</h2>';
        echo '  <form class="login-form" action="index.php?page=login" method="post">';
        echo '    <div class="form-group">';
        echo '      <label for="username">Nom d\'utilisateur</label>';
        echo '      <input type="text" id="username" name="username" required>';
        echo '    </div>';
        echo '    <div class="form-group">';
        echo '      <label for="password">Mot de passe</label>';
        echo '      <input type="password" id="password" name="password" required>';
        echo '    </div>';
        echo '    <input type="submit" class="login-submit" value="Se connecter">';
        echo '  </form>';
        echo '</div>';
    }
}
?>