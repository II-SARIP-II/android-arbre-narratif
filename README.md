### **Rapport de projet : Application de narration interactive sur la transition écologique**

---

#### **1. Contexte du projet**
La transition écologique est un enjeu majeur du XXIe siècle. Ce projet vise à sensibiliser les utilisateurs aux problématiques environnementales tout en les engageant dans une expérience ludique et interactive. À travers une narration immersive, l'utilisateur sera confronté à des choix simulant des situations réelles ou imaginaires liées à l'écologie et au développement durable.

L'objectif principal est de permettre aux utilisateurs de mieux comprendre l'impact de leurs décisions sur l'environnement tout en découvrant des solutions concrètes pour contribuer à une transition écologique réussie.

---

#### **2. Objectif de l'application**
Créer une application mobile interactive utilisant un **arbre narratif** pour proposer une aventure autour des thèmes de l’écologie et du développement durable. À travers un scénario engageant, l'utilisateur devra faire des choix qui influenceront le déroulement et la fin de l'histoire.

L'application a un double objectif :
1. **Éducationnel :** Sensibiliser les utilisateurs à des problématiques écologiques telles que la gestion des ressources, les énergies renouvelables, ou la biodiversité.
2. **Ludique :** Offrir une expérience de jeu captivante et personnalisée.

---

#### **3. Fonctionnalités principales**
1. **Narration interactive :**
    - Une histoire principale découpée en plusieurs nœuds narratifs.
    - Chaque nœud propose un texte descriptif et des choix qui influencent la suite de l’histoire.

2. **Choix multiples :**
    - Les utilisateurs prennent des décisions (e.g., "Prioriser les énergies renouvelables" ou "Construire des infrastructures polluantes") et voient leurs conséquences dans l’histoire.

3. **Scénarios écologiques :**
    - Problématiques abordées :
        - Gestion des déchets.
        - Préservation de la biodiversité.
        - Réduction de l’empreinte carbone.
        - Énergies renouvelables vs énergies fossiles.
    - Fin alternative selon les choix (e.g., "Monde durable" vs "Catastrophe écologique").

4. **Interface intuitive :**
    - Texte narratif clair et immersif.
    - Choix affichés sous forme de boutons dynamiques.

5. **Statistiques finales :**
    - Résumé de l’impact des décisions prises sur l'environnement.
    - Suggestions pour appliquer des comportements écoresponsables dans la vie réelle.

---

#### **4. Public cible**
L’application s’adresse principalement à :
- **Étudiants et jeunes adultes** souhaitant en apprendre davantage sur les enjeux écologiques.
- **Curieux et amateurs de jeux narratifs** cherchant une expérience engageante.
- **Éducateurs** pouvant utiliser l’application comme un outil pédagogique interactif.

---

#### **5. Structure technique**
L’application est développée en suivant le modèle **MVVM** et est conçue pour être évolutive.

1. **Arbre narratif :**
    - Stocké dans un fichier **JSON** pour faciliter la mise à jour du contenu sans modifier le code.
    - Chaque nœud contient un texte narratif, des choix, et les références aux nœuds suivants.

2. **Technologies utilisées :**
    - **Langage :** Java.
    - **Architecture :** MVVM pour séparer les données, la logique, et l'interface.
    - **Interface :** XML pour la disposition des vues dans Android Studio.
    - **Gestion des données :** Utilisation d’une bibliothèque comme Gson pour charger et traiter l’arbre narratif à partir du fichier JSON.

3. **Design de l’interface utilisateur :**
    - Texte immersif pour plonger l’utilisateur dans l’histoire.
    - Boutons interactifs pour sélectionner des choix.
    - Feedback visuel sur les conséquences des décisions prises.

---

#### **6. Scénario exemple**
**Introduction :**
- L’utilisateur incarne un maire d’une petite ville confrontée à des défis écologiques. Chaque décision qu’il prend affecte l’écosystème, le bien-être des citoyens, et l’avenir de la planète.

**Exemple de nœud narratif :**
- **Texte :** "Votre ville manque cruellement d’énergie. Un investisseur propose de construire une centrale à charbon, mais une ONG locale milite pour installer des éoliennes."
- **Choix :**
    1. "Construire la centrale à charbon" → Impact rapide, mais pollution accrue.
    2. "Installer des éoliennes" → Délais plus longs, mais énergie propre.
    3. "Chercher une alternative innovante" → Investissements supplémentaires nécessaires.

**Conséquences :**
- Les choix influencent :
    - L’économie locale.
    - La qualité de l’air et la santé des habitants.
    - La réputation internationale de la ville.

---

#### **7. Points forts pédagogiques**
- **Prise de conscience :** Les utilisateurs réalisent l’impact concret de leurs décisions.
- **Flexibilité :** Possibilité d’ajouter facilement de nouveaux scénarios narratifs.
- **Accessibilité :** Une interface simple et intuitive pour tout public.

---

#### **8. Plan de développement**
1. **Phase 1 : Analyse**
    - Définir les scénarios narratifs principaux.
    - Structurer l’arbre narratif en JSON.
2. **Phase 2 : Développement technique**
    - Implémentation du modèle MVVM.
    - Création de l’interface utilisateur (UI).
    - Gestion de la navigation entre les nœuds narratifs.
3. **Phase 3 : Tests**
    - Vérification de la cohérence des choix et des scénarios.
    - Tests sur différents appareils Android.
4. **Phase 4 : Lancement**
    - Déploiement d’une version bêta pour obtenir des retours.
    - Ajout de nouvelles fonctionnalités ou scénarios selon les retours.

---

#### **9. Perspectives d’évolution**
- **Multijoueur :** Permettre à plusieurs utilisateurs de collaborer ou de rivaliser dans leurs décisions.
- **Éléments multimédias :** Ajouter des images, sons ou animations pour enrichir l’expérience.
- **Thèmes variés :** Étendre les scénarios à d’autres problématiques écologiques (e.g., migrations climatiques, exploitation des océans).

---