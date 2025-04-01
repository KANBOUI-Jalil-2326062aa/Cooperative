# API Paniers

L'API Paniers est une API RESTful développée avec Jakarta EE qui permet de gérer les paniers d'une coopérative agricole. Chaque panier est composé de plusieurs produits (représentés par l'entité ProduitPanier), et l'API offre des opérations CRUD sur les paniers ainsi que la gestion des produits associés.

## Table des matières

- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration](#configuration)
- [Endpoints](#endpoints)
    - [Paniers](#paniers)
    - [Produits dans un Panier](#produits-dans-un-panier)
- [Exemples d'utilisation](#exemples-dutilisation)
- [Technologies utilisées](#technologies-utilisées)

## Prérequis

- Java 11 ou supérieur
- Serveur d'applications compatible Jakarta EE (ex. GlassFish)
- Base de données MySQL/MariaDB avec les tables Panier et ProduitPanier configurées.
- Maven

## Installation

Clonez le dépôt GitHub :

```bash
git clone git@github.com:Turlure-Nael-23018992/cooperative.git
```

Importez le projet dans IntelliJ IDEA (ou autre IDE).

Compilez et déployez l'application sur votre serveur GlassFish.

## Configuration

Le fichier `PanierApplication.java` définit le chemin global de l'API sous `/api`. Ainsi, l'API sera accessible via une URL du type :

```php-template
http://localhost:8080/Paniers-1.0-SNAPSHOT/api/paniers
```

## Endpoints

### Paniers

- **GET /api/paniers**
  - **Description** : Récupère la liste de tous les paniers, avec leurs informations et la liste des produits associés.
  - **Méthode** : GET
  - **URL** : `/api/paniers`
  - **Réponse exemple** :

    ```json
    [
      {
        "dateMaj": "2025-03-26",
        "dateRetrait": "2025-04-01",
        "estValide": true,
        "idPanier": "P001",
        "lieuRelais": "Relais Nord",
        "prixTotal": 22.5,
        "produitPaniers": [
          {
            "panier_id": "P001",
            "produit_id": "POM001",
            "quantite": 2
          },
          {
            "panier_id": "P001",
            "produit_id": "OEU002",
            "quantite": 1
          }
        ]
      },
      {
        "dateMaj": "2025-03-26",
        "dateRetrait": "2025-04-02",
        "estValide": false,
        "idPanier": "P002",
        "lieuRelais": "Relais Sud",
        "prixTotal": 18,
        "produitPaniers": [
          {
            "panier_id": "P002",
            "produit_id": "CAR003",
            "quantite": 2
          },
          {
            "panier_id": "P002",
            "produit_id": "FRA004",
            "quantite": 1
          }
        ]
      }
    ]
    ```

- **GET /api/paniers/{id}**
  - **Description** : Récupère les informations d'un panier spécifique ainsi que la liste des produits qui y sont associés.
  - **Méthode** : GET
  - **URL** : `/api/paniers/{id}`
  - **Exemple** : `/api/paniers/P001`

- **POST /api/paniers**
  - **Description** : Crée un nouveau panier.
  - **Méthode** : POST
  - **URL** : `/api/paniers`
  - **Corps (JSON)** :

    ```json
    [
      {
        "dateMaj": "2025-03-26",
        "dateRetrait": "2025-04-01",
        "estValide": true,
        "idPanier": "P001",
        "lieuRelais": "Relais Nord",
        "prixTotal": 22.5,
        "produitPaniers": [
          {
            "panier_id": "P001",
            "produit_id": "POM001",
            "quantite": 2
          },
          {
            "panier_id": "P001",
            "produit_id": "OEU002",
            "quantite": 1
          }
        ]
      }
    ]
    ```

  - **Réponse** : Message indiquant que le panier a été créé (HTTP 201).

- **PUT /api/paniers/{id}**
  - **Description** : Met à jour les informations d'un panier existant.
  - **Méthode** : PUT
  - **URL** : `/api/paniers/{id}`
  - **Corps (JSON)** :

    ```json
    [
      {
        "dateMaj": "2025-03-26",
        "dateRetrait": "2025-04-01",
        "estValide": true,
        "idPanier": "P001",
        "lieuRelais": "Relais Nord",
        "prixTotal": 22.5,
        "produitPaniers": [
          {
            "panier_id": "P001",
            "produit_id": "POM001",
            "quantite": 2
          },
          {
            "panier_id": "P001",
            "produit_id": "OEU002",
            "quantite": 1
          }
        ]
      }
    ]
    ```

  - **Réponse** : Message de succès (HTTP 200).

- **DELETE /api/paniers/{id}**
  - **Description** : Supprime un panier et tous ses produits associés.
  - **Méthode** : DELETE
  - **URL** : `/api/paniers/{id}`

### Produits dans un Panier

- **POST /api/paniers/{idPanier}/produits**
  - **Description** : Ajoute un produit dans le panier.
  - **Méthode** : POST
  - **URL** : `/api/paniers/{idPanier}/produits`
  - **Corps (JSON)** :

    ```json
    {
      "produit_id": "PRD01",
      "quantite": 2.5
    }
    ```

  - **Réponse** : Message indiquant que le produit a été ajouté (HTTP 200).

- **PUT /api/paniers/{idPanier}/produits/{idProduit}**
  - **Description** : Met à jour la quantité d'un produit dans le panier.
  - **Méthode** : PUT
  - **URL** : `/api/paniers/{idPanier}/produits/{idProduit}`
  - **Corps (JSON)** :

    ```json
    {
      "quantite": 3
    }
    ```

  - **Réponse** : Message de succès (HTTP 200).

- **DELETE /api/paniers/{idPanier}/produits/{idProduit}**
  - **Description** : Retire un produit du panier.
  - **Méthode** : DELETE
  - **URL** : `/api/paniers/{idPanier}/produits/{idProduit}`
  - **Réponse** : Message indiquant que le produit a été retiré (HTTP 200).

## Technologies utilisées

- Java 11+
- Jakarta EE (JAX-RS, CDI)
- MySQL pour la base de données
- Maven 
- GlassFish

