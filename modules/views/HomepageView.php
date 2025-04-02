<?php
namespace modules\views;

/**
 * Class HomepageView
 *
 * Vue pour afficher la page d'accueil.
 */
class HomepageView {
    /**
     * Affiche la vue de la page d'accueil.
     *
     * @return void
     */
    public function show() {
        include 'Header.php';

        echo <<<HTML
        <main class="homepage-container">
            <section class="hero-section">
                <div class="hero-content">
                    <h1>Bienvenue sur notre plateforme</h1>
                    <p class="hero-subtitle">Découvrez nos paniers frais, produits de qualité et suivez vos commandes</p>
                    <div class="cta-buttons">
                        <a href="index.php?page=paniers" class="cta-button primary">Voir nos paniers</a>
                        <a href="index.php?page=produits" class="cta-button secondary">Découvrir nos produits</a>
                    </div>
                </div>
            </section>

            <section class="features-section">
                <h2>Pourquoi choisir notre service ?</h2>
                <div class="features-grid">
                    <div class="feature-card">
                        <i class="fas fa-leaf"></i>
                        <h3>Produits Frais</h3>
                        <p>Des produits locaux et de saison, récoltés à maturité</p>
                    </div>
                    <div class="feature-card">
                        <i class="fas fa-truck"></i>
                        <h3>Livraison Rapide</h3>
                        <p>Recevez vos commandes en 24h dans nos points relais</p>
                    </div>
                    <div class="feature-card">
                        <i class="fas fa-euro-sign"></i>
                        <h3>Prix Justes</h3>
                        <p>Des prix équitables pour les producteurs et les consommateurs</p>
                    </div>
                </div>
            </section>

            <section class="how-it-works">
                <h2>Comment ça marche ?</h2>
                <div class="steps">
                    <div class="step">
                        <div class="step-number">1</div>
                        <h3>Choisissez</h3>
                        <p>Sélectionnez vos paniers ou produits préférés</p>
                    </div>
                    <div class="step">
                        <div class="step-number">2</div>
                        <h3>Commandez</h3>
                        <p>Passez votre commande en quelques clics</p>
                    </div>
                    <div class="step">
                        <div class="step-number">3</div>
                        <h3>Récupérez</h3>
                        <p>Retirez votre commande dans le relais de votre choix</p>
                    </div>
                </div>
            </section>
        </main>

        <link rel="stylesheet" href="/_assets/styles/Homepage.css">
        HTML;

        include 'Footer.php';
    }
}