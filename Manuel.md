# Manouel d'outilisaçione

## Installation

Ce manuel contient les informations nécessaires à l'installation et à l'exécution du jeu Nukemap en local. Il vous sera fourni un jar du jeu client nommé Nukemap.jar ainsi que le serveur sous forme de 4 fichiers javascript (server.js, score.js, package.json, et package-lock.json).

Bien évidemment, comme le jeu est une application java et le serveur et une application javascript, il vous faudra préalablement installer la dernière version de java : https://www.java.com/fr/download/ et respéctivement la dernière version LTS de nodejs : https://nodejs.org/en/download/

Une fois ces prérequis remplis, il vous sera possible de les executer.

## Démarrage du serveur

La procédure de lancement du serveur est la suivante :

1. Installer le package manager npm : https://www.npmjs.com/
2. Placer les fichiers server.js, score.js et package.json dans un dossier commun puis exécuter npm install depuis le terminal
3. Exécuter npm start pour lancer le serveur

----> capture

## Démarrage du client

Le client se lance simplement en ouvrant un terminal dans le dossier courant et en tapant 'java -jar Nukemap.jar', ou en double cliquant sur le .jar.

-----> capture

## Règles du jeu

----> page 5 du rapport

## Commandes du jeu

1. Pour se déplacer, il faut utiliser les touches fléchées droite, gauche, haut, et bas 
2. La barre d'espace est utilisée pour poser une bombe, celle-ci explosent au bout de 3 secondes et détruiront les blocs cassables, ennemis, et joueurs adverses.