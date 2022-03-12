<h1 align="center">RAPPORT IPO</h1>

<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/fr/thumb/7/71/Logo_ESIEE_Paris.svg/1200px-Logo_ESIEE_Paris.svg.png" />
</p>

<h1 align="center">2022</h1>
<br>
<h1 align="center"><a href="https://perso.esiee.fr/~diouyc/ZuulGOTYEdition"> Zuul GOTY Edition</a></h1>

<div style="page-break-after: always;"></div>

## Sommaire

- [Information générales](#information-générales)
  - [Auteur](#auteur)
  - [Thème](#thème)
  - [Scénario](#scénario)
  - [Plan](#plan)
  - [Scénario détaillé](#scénario-détaillé)
  - [Détail des lieux, items, personnages](#details-des-lieux)
  - [Situations gagnantes et perdantes](#situations-gagnantes-et-perdantes)
  - [Énigmes, mini-jeux, combats](#énigmes,-mini-jeux,-combats)
  - [Commentaires](#commentaires)
- [Réponses aux exercices](#réponses-aux-exercices)
- [Mode d'emploi](#)
- [Déclaration anti-plagiat](#)

<div style="page-break-after: always;"></div>

## Information générales

### Auteur

Charly **DIOUY**
E1 ESIEE PARIS
Groupe 1

### Thème

Un chevalier se retrouve coincé dans un donjon, il doit vaincre tout les boss pour s'échapper

### Scénario

Edward se retrouve bloqué dans un dongeon après s'être caché pendant une bataille, si il veut sortir il va devoir resoudre les énigmes de ce mysterieux dongeon.

### Plan

<p align="center">
  <img src="images/plan.png" />
</p>

### Scénario détaillé

Edward est un chevalier, durant une bataille il decide de s'aventurer dans les Terres Hostilles, un lieu que tous savaient dangereux. Vous incarnerez Edward et vous vivrez l'aventure avec lui. Il se retrouve fasse a une porte par terre, elle semble rouillée et ne pas avoir servi depuis plusieurs années. Une épaisse brume émane de cette porte. Edward décide de rentrer. Il arrive dans un dongeon et la porte disparait derrière lui. Il rencontre Garret, un marchant qui est resté enfermé ici et qui a perdu sa compagne dans ce dongeon. Il vous explique que le dongeon est ensorcelé par Hazelgash, un mage très puissant. Il vous informe aussi qu'avant de pouvoir accéder a la salle du mage il faudra vaincre ses deux serviteurs, un géant et un roi déchu, Warmog et Viego. Ils sont moins puissant que Hazelgash mais ils ont chacuns leurs points forts et leurs points faibles. Ça sera à vous de les trouver pour obtenir les artefacts qui seront utiles pour sortir de ce dongeon.

### Détail des lieux, items, personnages

- #### Lieux

  - Lobby
  - Salle du boss 1
  - Salle du boss 2
  - Salle du boss 3
  - Catacombes
  - Salle du trésor
  - Exterieur (debut du jeu, cinematique ???)

- #### Items

  - Armure de Warmog
  - Lame du Roi Déchu
  - Gantelet cryopyrique
  - Alliance de la défunte

- #### Personnages
  - Edward
  - Warmog le Geant
  - Viego le Roi Déchu
  - Hazelgash le mage Cryopyrique
  - Garret le marchant
  - Loryna la défunte
  - Wyatt le narrateur

### Situations gagnantes et perdantes

- #### Situations gagnantes

  - Rassembler les 3 artefacts
  - Rendre le marchant heureux

- #### Situations perdantes
  - Mourir contre un boss
  - Faire quelque chose d'immoral

### Énigmes, mini-jeux, combats

- #### Enigmes

  - Pourquoi le marchant est méchant au debut
  -

- #### Mini-jeux

  - pas encore d'idée
  -

- #### Combats
  - Combat contre les boss
  - Combat contre le marchant ?? (pas sur)

### Commentaires

Il y a encore beaucoup de travail pour tout finaliser

<div style="page-break-after: always;"></div>

## Réponses aux exercices

#### Exercice 7.0

Réalisation du site internet pour Zuul a l'adresse suivante : [Zuul GOTY Edition](https://perso.esiee.fr/~diouyc/ZuulGOTYEdition)

#### Exercice 7.5

La création de la méthode `printLocationInfo()` dans la classe `Game` permet d'éviter la duplication de code. En effet, il y a, à 2 reprises, le même segment de code (`goRoom()` et `printWelcome()`) pour informer le joueur de sa postion et les sorties disponibles. La création de `printLocationInfo()` permet de remplacer chaque occurence par un appel à la procédure, cela facilite aussi grandement les modifications futures car il y aura juste ce passage de code à modifier si on veut changer le texte pour les informations des salles.

```java
private void printLocationInfo()
{
    System.out.println("You are "+aCurrentRoom.getDescription());
        System.out.print("Exits : ");
        if(aCurrentRoom.aNorthExit != null){
            System.out.print("north");
        }
        if(aCurrentRoom.aEastExit != null){
            System.out.print("east");
        }
        if(aCurrentRoom.aSouthExit != null){
            System.out.print("south");
        }
        if(aCurrentRoom.aWestExit != null){
            System.out.print("west");
        }
        System.out.println();
}
```

Les modifications suivantes ont donc été effectuées dans `goRoom()` et dans `printWelcome()`

```java
public class Game
{
    [...]

    private void goRoom(final Command pDirection)
    {

        [...]

        else
        {
            this.aCurrentRoom=vNextRoom;
            printLocationInfo();
        }
    }

    private void printWelcome()
    {
        System.out.println("Welcome to the World of Zuul !");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        printLocationInfo();
    }

}
```

#### Exercice 7.6

La création de l'accesseur `getExit()` dans la classe `Room` permet de réduire le couplage, et de respecter un principe fondamental d'une bonne conception de classe qui est _l'encapsulation_. Cela nous permet de passer les attributs de la classe en privé. Le code passe de

```java
public class Room
{
    public String   aDescription;
    public Room     aNorthExit;
    public Room     aEastExit;
    public Room     aSouthExit;
    public Room     aWestExit;

    [...]
}
```

à

```java
public class Room
{
    private String   aDescription;
    private Room     aNorthExit;
    private Room     aEastExit;
    private Room     aSouthExit;
    private Room     aWestExit;

    [...]

    public Room getExit(String pDirection){
        if(pDirection.equals("north")){
            return aNorthExit;
        }
        if(pDirection.equals("east")){
            return aEastExit;
        }
        if(pDirection.equals("south")){
            return aSouthExit;
        }
        if(pDirection.equals("west")){
            return aWestExit;
        }
        return null;
    }
}
```

Il y a aussi des modifications à faire dans la classe `Game`, pour remplacer un appel aux attributs par l'accesseur `getExit()` qui sont :

```java
public class Game
{

    [...]

    private void goRoom(final Command pDirection){

        [...]

        Room vNextRoom = aCurrentRoom.getExit(vDirection);

        [...]
    }

    [...]

    private void printLocationInfo()
    {
        System.out.println("You are "+aCurrentRoom.getDescription());
        System.out.print("Exits : ");
        if(aCurrentRoom.getExit("north") != null){  // précédemment if(aCurrentRoom.aNorthExit != null){
            System.out.print("north ");
        }
        if(aCurrentRoom.getExit("east") != null){   // précédemment if(aCurrentRoom.aEastExit != null){
            System.out.print("east ");
        }
        if(aCurrentRoom.getExit("south") != null){  // précédemment if(aCurrentRoom.aSouthExit != null){
            System.out.print("south ");
        }
        if(aCurrentRoom.getExit("west") != null){   // précédement if(aCurrentRoom.aWestExit != null){
            System.out.print("west ");
        }
        System.out.println();
    }
}
```

#### Exercice 7.7

La création de la méthode `getExitString()` qui nous retournera la chaine de caractère contenant toutes les sorties, doit être créée dans la classe `Room` car celle-ci gère tout ce qui réfère aux salles.

```java
public String getExitString()
    {
        String vExit = "Exits : ";

        if (this.aNorthExit!=null)
        {
            vExit += "north ";
        }

        if (this.aSouthExit!=null)
        {
            vExit += "south ";
        }

        if (this.aEastExit!=null)
        {
            vExit += "east ";
        }

        if (this.aWestExit!=null)
        {
            vExit += "west ";
        }
        return vExit;
    }
```

L'affichage se passe dans la classe `Game` avec la methode `printLocationInfo()` précédemment crée.

```java
private void printLocationInfo()
{
    System.out.println("You are "+currentRoom.getDescription());
    System.out.println(aCurrentRoom.getExitString());
}
```

#### Exercice 7.8

Modification des attributs de la classe `Room` pour les mettre dans une `HashMap`, pour ce faire il faut écrire en haut de la classe `import java.util.HashMap;` et pour la créer `private HashMap<String, Room> aExits;`. Le code de la classe `Room` est donc comme suit

```java
import java.util.HashMap;

public class Room
{
    private HashMap<String, Room> aExits;

    public Room(final String pDescription) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String,Room>();
    }

    public void setExit(final String pDirec, Room pNeighbor){
        aExits.put(pDirec, pNeighbor);
    }

    public Room getExit(String pDirection){
        return aExits.get(pDirection);
    }

    public String getExitString()
    {
        String vExit = "Exits : "+this.aExits;
        return vExit;
    }
}
```

Des changements sont aussi effectués dans la classe `Game` pour les mettres aux formats de nos accesseurs modifiés dû à la `HashMap`

```java
public class Game
{

    [...]

    private void createRooms(){

        [...]

        vOutside.setExit("north", vLobby);

        vCatacombs.setExit("west", vBoss2Room);

        vLobby.setExit("north", vBoss1Room);
        vLobby.setExit("east", vBoss2Room);
        vLobby.setExit("west", vBoss3Room);

        vTreasure.setExit("east", vBoss3Room);

        vBoss1Room.setExit("south", vLobby);

        vBoss2Room.setExit("east", vCatacombs);
        vBoss2Room.setExit("west", vLobby);

        vBoss3Room.setExit("west", vTreasure);
        vBoss3Room.setExit("east", vLobby);

        [...]
    }

    [...]

}
```

#### Exercice 7.8.1

L'ajout d'un déplacement vertical entre l'extérieur et le dongeon est réalisé. Les directions sont donc maintenant : `north`, `east`, `south`, `west` et `down`

```java
vOutside.setExits("down", vLobby);
```

#### Exercice 7.9

La méthode `keySet()` permet d'associer des valeurs arbitraires à des clés.

```java
import java.util.Set;
```

Ainsi, elle peut associer le mot de commande à la clé qui ouvrira l'accès à la `HashMap` contenant toutes les pièces. **Exemple :** pour le lobby, les sorties sont `north`, `east` et `west`. La clé nord sur la commande `go north` permettra d'accéder à la salle au nord du lobby.

#### Exercice 7.10

La methode `getExitString()` permet de récupérer toutes les sorties d'une pièce sous forme d'une `String`

```java
String vReturnString = "Exits : ";
```

Cette ligne crée une variable `vReturnString` de type `String` qui contient la chaine de caractères `"Exits : "`

```java
Set<String> vKeys = aExits.keySet();
```

Celle-ci crée une variable `vKeys` de type `Set<String>`. Les éléments contenus sont les clés de la `HashMap aExits` sous forme d'une liste de `String` qui ne peut pas comporter 2 fois le même élément.

```java
for(String vExit : vKeys){
    vReturnString += " " + vExit;
}
```

Ce bout de code est une boucle `for each` qui parcours la liste des clés stockées. `vExit` est une variable qui prend la valeur de la prochaine clé de la `HashMap`à chaque fois que la boucle se répète, ce qui fait que `vReturnString` obtient toutes les sorties disponibles dans la `String`.

```java
return vReturnString;
```

La derniere partie du code retourne la chaine de caractères possédant toutes les sorties de la salle.

#### Exercice 7.11

La fonction `getLongDescription()` retourne une chaine de caractères informant le joueur de sa position actuelle et des sorties de la salle

```java
public String getLongDescription(){
    return "You are " + aDescription + ".\n" + getExitString();
}
```

Dans la classe `Game` on effectue donc des modifications

```java
private void printLocationInfo() {
    System.out.println(aCurrentRoom.getLongDescription());
}
```

#### Exercice 7.12

Les objets créés au lancement du programme sont imagés dans ce diagramme

<p align="center">
  <img src="images/DiagrammeObjet.png" />
</p>

#### Exercice 7.13

Lorsque la commande `go` est exécutée, l'objet courrant change, c'est à dire, passer d'une salle à l'autre

#### Exercice 7.14

On ajoute la commande `look` dans la classe `CommandWords` avec cette ligne

```java
this.aValidCommands[3] = "look";
```

Une fois fait, il faut créer une methode pour la commande `look` qui nous permettra d'avoir les informations sur la salle et ses sorties autant de fois que nous le souhaitons

```java
private void look(){
    printLocationInfo();
}
```

Nous devons ajouter les lignes de code ci-dessous dans la fonction `processCommand()` de la classe `Game` pour que la méthode fonctionne lorsqu'elle est saisie au clavier

```java
else if(pCommand.getCommandWord().equals("look")){
    this.look();
}
```

#### Exercice 7.15

On refait la même chose pour la commande `eat`. C'est à dire ajout de la commande `eat` dans la classe `CommandWords`

```java
this.aValidCommands[4] = "eat";
```

Création d'une méthode pour la commande `eat`

```java
private void eat(){
    System.out.println("You have eaten now and you are not hungry any more.");
}
```

Ajout des lignes de code ci-dessous dans la fonction `processCommand()` de la classe `Game` pour que la méthode fonctionne lorsqu'elle est saisie au clavier

```java
else if(pCommand.getCommandWord().equals("eat")){
    this.eat();
}
```

#### Exercice 7.16

On crée une méthode `showAll()` dans la classe `CommandWords` pour regrouper toutes les commandes dans une `String`

```java
public void showAll(){
    for(String vCommand : aValidCommands){
        System.out.print(vCommand + " ");
    }
    System.out.println();
}
```

Ensuite, dans la classe `Parser`, on crée une méthode `showCommands()` pour afficher toutes les commandes

```java
public void showCommands(){
    aValidCommands.showAll();
}
```

Puis nous remplaçons le texte écrit "en dur" par la methode `showCommands()` afin de le rendre dynamique. Lorsque nous voudrons ajouter d'autres commandes, il s'adaptera automatiquement

```java
private void printHelp() {

    [...]

    System.out.println("Your command words are:");
    aParser.showCommands();
}
```

#### Exercice 7.17

Si nous voulons ajouter une autre commande il faudra modifier la classe `Game` en ajoutant la methode de la commande et ajouter la ligne permantant l'exécution dans `processCommand()`

```java
else if(pCommand.getCommandWord().equals("command")){
    this.command(); // command signifie une commande générale pouvant être intégrée au jeu
}
```

#### Exercice 7.18

Dans la classe `CommandWords` la méthode `showAll()` devient la fonction `getCommandList()` et subit les modifications ci-dessous

```java
public String getCommandList()
{
    StringBuilder sCommands = new StringBuilder();
    for(int i = 0; i < aValidCommands.length; i++) {
        sCommands.append( aValidCommands[i] + "  " );
    }
    return sCommands.toString();
}
```

La méthode `showCommands()` est aussi modifiée par une fonction `getCommandString()`

```java
public String getCommandString()
{
    return this.aValidCommands.getCommandList();
}
```

Les modifications sont légères dans `printHelp()` il y a juste à remplacer `aParser.showCommands()` par `aParser.getCommandString()`

```java
private void printHelp() {

    [...]

    aParser.getCommandString();
}
```

#### Exercice 7.18.1

Les deux projets sont vraiment similaires ce qui est logique car j'ai suivi les exercices demandés. Néanmoins il faut noter deux differences notables qui sont l'ajout des commandes : `look` et `eat`

#### Exercice 7.18.2

Objet mutable qui peut donc changer au cours du temps. On peut construire petit à petit une chaîne de caractère, avec la méthode `append()` qui ajoute des caractères à la suite des autres, et toString qui retourne la chaîne.
[source](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html)

#### Exercice 7.18.3

La recherche d'images est plutôt compliquée pour trouver ce qui me plait. Je vais m'orienter vers la création des decors pour le jeu.

#### Exercice 7.18.4

Le titre du jeu est : Zuul GOTY Edition

#### Exercice 7.18.5

Nous devons créer une `HashMap<>()` qui contiendra toutes les pieces

```java
private HashMap<String, Room> aRooms;
```

Nous l'initialisons ensuite dans `createRooms()`

```java
this.aRooms = new HashMap<String, Room>();
```

Puis nous devons ajouter les salles dedans avec `put()`

```java
aRooms.put("Outside", vOutside);
aRooms.put("Catacombs", vCatacombs);
aRooms.put("Lobby", vLobby);
aRooms.put("Treasure", vTreasure);
aRooms.put("Boss1Room", vBoss1Room);
aRooms.put("Boss1Room", vBoss2Room);
aRooms.put("Boss1Room", vBoss3Room);
```

#### Exercice 7.18.6

Cette modification change beaucoup de chose dans le jeu car nous rajoutons une interface graphique. Nous ajoutons une fonction `getImageName()` et un attribut `aImageName` dans la classe `Room`.

```java
public class Room {

    [...]

    private String aImageName;

    public Room(final String pDescription, final String pImage) {

        [...]

        this.aImageName = pImage;
    }

    [...]

    public String getImageName() {
        return this.aImageName;
    }
```

Ensuite la classe `Parser` n'a plus besoin de `Scanner`

La classe `Game` devient la classe `GameEngine`. Le changement majeur est qu'avec la nouvelle interface on n'"imprime" plus dans le terminal mais sur la fenêtre du jeu avec cette ligne

```java
this.aGui.println("exemple");
```

La méthode `printLocationInfo()` change pour afficher les images de la salle. Cela évite la duplication de code

```java
private void printLocationInfo() {
        this.aGui.println(aCurrentRoom.getLongDescription());
        if (this.aCurrentRoom.getImageName() != null) {
            this.aGui.showImage(this.aCurrentRoom.getImageName());
        }
    }
```

La nouvelle classe `UserInterface` sert à créer une interface graphique pour le jeu, pour le moment elle contient une zone de texte, une zone d'affichage et une zone pour mettre une image.
Nous effectuons quand même des modifications par rapport à la version qui nous est donnée pour les `import` et éviter les `*` on recherche tout ce qu'on doit importer. Faire cela allège le code car il n'a pas besoin de tout avoir de certains package.

```java
import java.net.URL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
```

#### Exercice 7.18.7

`addActionListener()` est une méthode qui ajoute un "listener", une interface qui permet de réagir (via des méthodes pour se déplacer par exemples) suite à un événement survenu dans le jeu.
La méthode `actionPerformed()` est appelée lorsqu'une commande est entrée et appelle la méthode `processCommand()` pour l'exéctuer.

#### Exercice 7.18.8

Nous devons créer un bouton pour cette exercice, pour cela il faut importer le package

```java
import javax.swing.JButton;
```

Il faut ensuite déclarer l'attribut

```java
private JButton aButton;
```

Puis nous l'initialisons dans la méthode `createGUI()` avec la ligne ci-dessous

```java
this.aButton = new JButton("quit"); // to create a quit button
```

Une fois cela fait il faut l'ajouter sur le `JPanel`. Pour se faire on doit écrire

```java
vPanel.add(this.aButton, BorderLayout.WEST); // EAST and WEST are available
```

Le bouton a juste une interface graphique pour le moment, il faut donc lui ajouter un `ActionListener()`

```java
this.aButton.addActionListener(this);
```

Pour finir, il faut modifier la méthode `actionPerformed` pour que que le bouton exectute la commande qui lui est renseignée

```java
public void actionPerformed(final ActionEvent pE) {
        // check the type of action
        if (pE.getActionCommand() != null) {
            this.aEngine.interpretCommand(pE.getActionCommand());
            this.aEntryField.setText(""); // to reset entry field
        } else {
            this.processCommand(); // never suppress this line
        }
    }
```

#### Exercice 7.19

La mise en place du pattern **Model-View-Controller** (MVC) est un modèle dans la conception de logiciels. Cette séparation permet une meilleure répartition du travail et une maintenance améliorée. Ce modèle est découpé en 3 parties :

- Model (modèle) : gère les données et la logique métier.
- View (vue) : gère la disposition et l'affichage.
- Controller (contrôleur) : achemine les commandes des parties "model" et "view".

Utiliser le MVC permet donc d'avoir un programme plus propre, quitte à prendre plus de temps cela permet une évolution beaucoup plus simple car tout le programme est bien découpé en partie simple à comprendre.

#### Exercice 7.19.2

Toutes les images sont déplacées dans le dossier `gameImages` qui stockera toutes les images du jeu. Pour ajouter une image au jeu il faut maintenant rajouter le nom du fichier suivi d'un `/`

```java
vOutside = new Room("outside the dungeon", "gameImages/outside.png");
```

#### Exercice 7.20

Il nous faut créer une classe `Item` qui s'occupera des objets de notre jeux

```java
public class Item {
    private String aName;
    private double aPrice;
    private double aWeight;
    private String aDescription;

    public Item(final String pName, final double pPrice, final double pWeight, final String pDescription) {
        this.aName = pName;
        this.aPrice = pPrice;
        this.aWeight = pWeight;
        this.aDescription = pDescription;
    }
}
```

les accesseurs sont aussi créés, ils pourraient être utile par la suite.
Ensuite dans la classe `Room`, car les objets y seront stockés, nous devons l'initialiser. Nous choisirons ici l'utilisation d'une `HashMap` pour faciliter l'ajout de plusieurs objets dans le futur. Cette exercice demande l'ajout d'un seul objet, c'est donc ce que nous ferons.

```java
private HashMap<String, Item> aItems;

public Room(..) {

        [...]

        this.aItems = new HashMap<String, Item>();

    }
```

Pour ajouter des objets il nous faut une méthode pour le faire. Nous en créons une nommée `setItems()`

```java
public void setItems(final String pName, final Item pItem){
    aItems.put(pName, pItem);
}
```

Nous ajoutons une fonction presque identique à `getExitString()` pour obtenir une chaine de caractère contenant tout les objets présents

```java
public String getItemString() {
    String vReturnString = "Items : ";
    Set<String> vKeys = aItems.keySet();
    for (String vItem : vKeys) {
        vReturnString += " " + vItem;
    }
    return vReturnString;
}
```

Une fois cela fait nous pouvons nous attaquer à la mise en place d'un objet dans une pièce, nous allons donc modifier la classe `GameEngine`.
Dans la méthode `createRoom()` nous allons pouvoir ajouter des objets grâce aux lignes ci-dessous

```java
public void createRoom(){
    Item vTest = new Item("TestItem", 10, 5, "This is a test item");
    vOutside.setItems("Test", vTest);
}
```

Ces deux lignes servent à créer un `Item` puis de le mettre dans la pièce en question, ici nous créons un objet `Test` que nous mettons dans la pièce `vOutside`
Il faut maintenant modifier la fonction `getLongDescription()` de la classe `Room` pour afficher les objets disponibles dans la salle. Comment savoir si la piece contient un objet ? Nous utiliserons un `boolean isEmpty()`

```java
public boolean isEmpty() {
    return this.aItems.isEmpty();
}
```

D'après la javadoc cette méthode me parait être la plus simple pour savoir si un objet est présent ou pas car `*.isEmpty()` retourne `true` si la `HashMap` est vide.
La dernière étape est donc de modifier `getLongDescription()`

```java
public String getLongDescription() {
    if (this.isEmpty()) {
        return "You are " + aDescription + ".\n" +
                getExitString() + "\n" +
                "No item here.";
    }else{
        return "You are " + aDescription + ".\n" +
                getExitString() + "\n" +
                getItemString();
    }
}
```

#### Exercice 7.21

Il faut que la classe `Item` produise la chaine de caractère qui décrit l'objet, c'est pour cela que l'on doit effectuer quelques modifications. Nous redéfinissons la fonction `toString()` dans la classe `Item`

```java
@Override public String toString(){
    return this.aName +
            ", price : "+ this.aPrice +
            ", weight : " +this.aWeight + "kg, " +
            this.aDescription;
}
```

#### Exercice 7.21.1

Pour faciliter la recupération du nom nous allons créer un acceceur ressemblant à `getExit()`

```java
public Item getItemName(String pName) {
    return aItems.get(pName);
}
```

Pour regarder un objet nous allons créer une méthode `lookItem()` dans la classe `GameEngine`

```java
private void lookItem(final Command pItem){
    String vItemName = pItem.getSecondWord();

    Item vItem = aCurrentRoom.getItemName(vItemName);

    if(vItem == null){
        this.aGui.println("I dont know what do you mean");
    }else{
        this.aGui.println(vItem.toString());
    }
}
```

Puis nous allons remplacer

```java
else if (vCommandWord.equals("look")) {
    if (vCommand.hasSecondWord()) {
        this.aGui.println("I don't know how to look here");
    }
```

Par

```java
else if (vCommandWord.equals("look")) {
    if (vCommand.hasSecondWord()) {
        this.lookItem(vCommand);
    }
```

#### Exercice 7.22

La possibilité d'ajouter plusieurs objets avait déja été implémenté pendant les exercices précedents. La méthode qui permettait de placer les objets à juste changer de nom pour être conforme au cahier des charges, passant de `setItems()` à `addItem()`

#### Exercice 7.22.1

Une `HashMap` a été utilisée car c'est, à mon avis, le plus approprié pour réaliser cet exercice puisqu'elle nous permet d'ajouter un nombre infini d'objet, c'est dynamique (comparé à un tableau où nous aurions dû modifier la taille à chaque objet rajouté ou supprimé de la salle) et c'est aussi une collection que nous avons vu dans les exercices précédents. Tout cela ajouté au fait que nous pouvons retrouver directement grâce à la méthode `get()` étant donnée que les objets sont stocké sous une chaine de caractères qui est leur noms et d'une clé qui est l'objet.

#### Exercice 7.22.2

Tout les objets actuels du jeu sont implémentés, deux objets factices sont rajoutés pour répondre au cahier des charges

```java
Item vTest = new Item("TestItem", 10, 5, "This is a test item"); // Name, price, weight, desc
Item vTest2 = new Item("TestItem2", 20, 10, "This is a test item 2"); // Name, price, weight, desc
vOutside.addItem("Test", vTest);
vOutside.addItem("Test2", vTest2);

Item vWarmogArmor = new Item("Warmog's Armor", 0, 40, "This is the armor of Warmog the Giant");
vBoss1Room.addItem("Warmog's_Armor", vWarmogArmor);

Item vBOTRK = new Item("Blade Of The Ruined King", 0, 20,
                        "This is the blade of Viego, it weighs nothing compared to its burden");
vBoss2Room.addItem("Blade_Of_The_Ruined_King", vBOTRK);

Item vFrostFireGauntlet = new Item("Frostfire Gauntlet", 0, 10, "This is the last artefact of the dungeon");
vBoss3Room.addItem("Frostfire_Gauntlet", vFrostFireGauntlet);

Item vWeddingRing = new Item("Wedding Ring", 0, 0, "This is a wedding ring, it's will be usefull");
vCatacombs.addItem("Wedding_ring", vWeddingRing);
```

#### Exercice 7.23

Nous devons implémenter une nouvelle commande donc il faut commencer par l'ajouter dans la classe `CommandsWord`

```java
public CommandWords() {
    this.aValidCommands = new String[6];

    [...]

    this.aValidCommands[5] = "back";
}
```

Puis la créer dans la classe `Game`. Pour ce faire nous avons besoin de nous "souvenir" de la salle précédente, nous créons donc un attribut `aPreviousRoom`

```java
private Room aPreviousRoom;
```

Nous devons ensuite placer cette attribut pour qu'il prenne la valeur de la salle actuelle avant que nous changions de pièce dans la méthode `goRoom()`

```java
private void goRoom(.) {
    if (.) {

        [...]

    }
    aPreviousRoom = aCurrentRoom;

    [...]

    if (.)

        [...]

    else {

        [...]

    }
}
```

L'avant dernière étape est de créer la méthode pour la commande `back`. La pièce actuelle prend la valeur de la pièce précédente qui nous permet donc de revenir en arrière. Pour finir la méthode nous affichons les informations de la salle.

```java
private void back(){
    aCurrentRoom = aPreviousRoom;
    printLocationInfo();
}
```

Nous devons, pour finir cette implementation, ajouter la commande dans la méthode `interpretCommand()`

```java
else if (vCommandWord.equals("back")) {
    if (vCommand.hasSecondWord()) {
        this.aGui.println("it's impossible");;
    } else {
        this.back();
    }
}
```

#### Exercice 7.24

La commande fonctionne très bien pour revenir en arrière lorsqu'on se déplace une fois, si on ajoute un second mot le programme reagit correctement et nous affiche qu'il est impossible de combiner un autre mot avec `back`. Si on utilise `back` sans avoir bouger le programme crash. Pour corriger cela nous devons apporter quelques modifications dans la méthode `interpretCommand()`

```java
else if (vCommandWord.equals("back")) {
    if (vCommand.hasSecondWord()) {
        this.aGui.println("it's impossible");;
    }else if(aPreviousRoom == null){ // add this if block to be sure previous room is not null
        this.aGui.println("you cant do that"); // send to the player it's impossible to back here because it's the first room
    }else {
        this.back();
    }
```

#### Exercice 7.25

Quand on se déplace plusieurs fois et qu'on fait la commande `back` autant de fois. Cela nous ramène uniquement dans la salle précédente et pas à la première salle du jeu.

#### Exercice 7.26

Nous voulons donc régler le problème de l'exercice précédent, pour ce faire nous allons utiliser une nouvelle classe Java qui est `Stack<>()`. Pour la déclarer, comme d'habitude, en haut de la classe `GameEngine`

```java
private Stack<Room> aPreviousRooms;
```

Vu que c'est une classe Java il faut aussi l'importer

```java
import java.util.Stack;
```

Ensuite on l'initialise dans le constructeur

```java
public GameEngine() {

    [...]

    this.aPreviousRooms = new Stack<Room>();
    }
```

Puis on commence les modifications dans `goRoom()`

```java
aPreviousRooms.push(aCurrentRoom);
```

Cette ligne permet de remplir la pile avec la salle actuelle et cela s'actualise avec chaque pièce car le code de `goRoom()` s'éxécute à chaque fois que nous changeons de salle. La dernière modification à effectuer est dans la méthode `back()`

```java
private void back(Command pCommand){
    if (pCommand.hasSecondWord()) {
        this.aGui.println("back doesn't need a second word");;
    }else if(aPreviousRooms.empty()){
        this.aGui.println("you can't back");
    }else {
        Room vPreviousRoom = aPreviousRooms.pop();
        aCurrentRoom = vPreviousRoom;
        printLocationInfo();
    }
}
```

J'ai aussi effectué une modification dans `interpretCommand()` je trouvais cela plus logique de tout gérer depuis la commande `back`. C'est à dire, le cas où il y a 2 mots et celui où il n'y a pas d'autre salle dans la pile, qu'on esssaye d'effectuer `back` au démarrage du jeu. Les modifications dans `interpretCommand()` sont donc les suivantes :

```java
else if (vCommandWord.equals("back")) {
    this.back(vCommand);
}
```

Nous remplaçons tout le gros bloc par un appel à la méthode

#### Exercice 7.27

Il serait pertinent de tester les commandes utilisateur, de se deplacer dans toutes les salles, d'essayer de regarder dans les pieces voir si tout s'affiche bien, de regarder les objets et d'utiliser la commande `back` pour voir si elle réagit bien dans toutes les circonstances.

#### Exercice 7.28

Pour automatiser les textes il serait intéressant de créer un fichier comportant toutes les commandes à tester. La classe `GameEngine` devra être modifiée pour insérer la possiblité d'avoir un script de test. Il faudra aussi ajouter dans `CommandWord` un nouveau mot comme `test` par exemple, qui acceptera un second mot qui pourrait être le nom du fichier test

#### Exercice 7.28.1

Nous devons donc implémenter une commande de test, pour se faire nous ajoutons une nouvelle commande dans la classe `CommandWords`

```java
public CommandWords() {
    this.aValidCommands = new String[7];

    [...]

    this.aValidCommands[6] = "test";
}
```

Ensuite nous créons notre méthode `test()` qui prendra en paramètre un second mot qui sera le nom du fichier.

```java
private void test(Command pFile) {
    if (pFile.hasSecondWord()) {
        try{
            String vFile = pFile.getSecondWord();
            Scanner vScanner = new Scanner(new File(vFile+".txt"));
            String vCommand = vScanner.nextLine();
            while (vScanner.hasNextLine()) {
                interpretCommand(vCommand);
                vCommand = vScanner.nextLine();
            }
        }catch(final FileNotFoundException pE){
            this.aGui.println(pE.getMessage());
        }
    } else {
        this.aGui.println("This command need a second word");
    }
}
```

L'utilisation d'un `try` et `catch()` permet de recupérer l'erreur que cela génére, de la mettre dans un message et ainsi éviter un bug du programme, ce qui serait une aubaine pour une commande de test.
Pour finir il faut ajouter la commande dans `interpretCommand()`

```java
else if (vCommandWord.equals("test")) {
    this.test(vCommand);
}
```

#### Exercice 7.28.2

Création de 3 fichers de test

court.txt

```txt
go down
look
go north
look Warmog's_Armor
LastLine
```

allCommands.txt

```txt
help
back
back back
eat
azerty
look
look Test
look Test2
go up
go down
go up
back back
look item
go north
look Warmog's_Armor
go south
go east
look Blade_Of_The_Ruined_King
go east
look Wedding_ring
go west
go west
go west
look Frostfire_Gauntlet
go west
back
back
back
back
back
back
back
back
back
back
quit
LastLine
```

fastEnd.txt

```txt
go down
go north
look Warmog's_Armor
go south
go east
look Blade_Of_The_Ruined_King
go east
look Wedding_ring
go west
go west
go west
look Frostfire_Gauntlet
go east
LastLine
```

l'ajout d'une ligne `LastLine` a été nécessaire pour le bon fonctionnement, car la boucle s'effectue tant qu'il y a une encore une ligne, ce qui passait la dernière commande a éxécuter

Découverte d'un bug lorsque nous rentrions une commande inconnue comme `azerty` par exemple. La zone où nous rentrions le texte ne se vidait pas après une commande inconnue, les modifications ont donc été effectuées dans la méthode `interpretCommand()`

```java
try {
    String vCommandWord = vCommand.getCommandWord();
    if (vCommandWord.equals("help")){
        this.printHelp();
    }else if (vCommandWord.equals("go")){
        this.goRoom(vCommand);
    }else if (vCommandWord.equals("quit")) {
        if (vCommand.hasSecondWord()){
            this.aGui.println("Quit what?");
        }else{
            this.endGame();
        }
    } else if (vCommandWord.equals("look")) {
        if (vCommand.hasSecondWord()) {
            this.lookItem(vCommand);
        } else {
            this.look();
        }
    } else if (vCommandWord.equals("eat")) {
        this.eat();
    } else if (vCommandWord.equals("back")) {
        this.back(vCommand);
    } else if (vCommandWord.equals("test")) {
        this.test(vCommand);
    }
    } catch (Exception pE) {
        System.out.println(pE.getMessage()); // use try catch to avoid error
    }
}
```

#### Exercice 7.29

Création d'une nouvelle classe `Player`. Nous prenons toutes les commandes associées au joueur et nous les mettons dans la classe, car c'est le joueur qui doit utiliser les commandes

```java
import java.util.Stack;

public class Player {
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private String aName;
    private UserInterface aGui;
    private Parser aParser;

    public Player(final Room pCurrentRoom, final String pName) {
        this.aCurrentRoom = pCurrentRoom;
        this.aName = pName;
        this.aPreviousRooms = new Stack<Room>();
        this.aParser = new Parser();
    }

    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }

    public void setRoom(final Room pNextRoom) {
        this.aCurrentRoom = pNextRoom;
    }

    public Stack<Room> getPreviousRooms() {
        return this.aPreviousRooms;
    }

    /**
     * GUI Constructor
     *
     * @param pGui UserInterface
     */
    public void setGUI(UserInterface pGui) {
        this.aGui = pGui;
    }

    // User Command

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    protected void printHelp() {
        this.aGui.println("You are lost. You leave the fight.");
        this.aGui.println("You wander around the dungeon.");
        this.aGui.println("");
        this.aGui.println("Your command words are : " + aParser.getCommandString());
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     *
     * @param pDirection use for check if there are second word
     */
    protected void goRoom(final Command pDirection) {
        if (!pDirection.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            this.aGui.println("Go where?");
            return;
        }
        this.getPreviousRooms().push(this.getCurrentRoom());

        String vDirection = pDirection.getSecondWord();

        // Try to leave current room.
        Room vNextRoom = this.getCurrentRoom().getExit(vDirection);

        if (vNextRoom == null)
            this.aGui.println("There is no door!");
        else {
            this.setRoom(vNextRoom);
            printLocationInfo();
        }
    }

    /**
     * This method print the info of the room with the exit when you wrote in a chat
     */
    protected void look(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.lookItem(pCommand);
        } else {
            printLocationInfo();
        }
    }

    /**
     * This method print the description of the item passed as a param
     *
     * @param pItem to get the item name
     */
    protected void lookItem(final Command pItemName) {
        String vItemName = pItemName.getSecondWord();

        Item vItem = this.getCurrentRoom().getItemName(vItemName);

        if (vItem == null) {
            this.aGui.println("I dont know what do you mean");
        } else {
            this.aGui.println(vItem.toString());
        }
    }

    /**
     * This method is just a simple command
     */
    protected void eat() {
        this.aGui.println("You have eaten now and you are not hungry any more.");
    }

    /**
     * This method is to return to the previous room to the starting room
     *
     * @param pCommand to be sure there is no second word
     */
    protected void back(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("it's impossible");
        } else if (this.getPreviousRooms().empty()) { // was aPreviousRooms.empty()
            this.aGui.println("you cant back");
        } else {
            Room vPreviousRoom = this.getPreviousRooms().pop(); // was aPreviousRooms.pop()
            this.setRoom(vPreviousRoom); // was aCurrentRoom = vPreviousRoom
            printLocationInfo();
        }
    }

    /**
     * This method print the info of the room with the exit when you enter on it
     */
    protected void printLocationInfo() {
        this.aGui.println(this.getCurrentRoom().getLongDescription());
        if (this.getCurrentRoom().getImageName() != null) {
            this.aGui.showImage(this.getCurrentRoom().getImageName());
        }
    }

}
```

Nous devons déclarer le joueur dans la classe `GameEngine`

```java
private Player aPlayer;
```

Puis l'initialiser dans le constructeur

```java
public GameEngine() {

    [...]

    this.createPlayer();

    [...]

}
```

Le constructeur fait appelle à la méthode `createPlayer()` ci-dessous

```java
private void createPlayer() {
    this.aPlayer = new Player(aRooms.get("Outside"), "Edward");
}
```

Nous devons aussi initialiser l'interface graphique du joueur

```java
public void setGUI(.) {

    [...]

    this.aPlayer.setGUI(pUserInterface;

    [...]

}
```

Les modifications dans la classe `GameEngine` sont principalement dans la méthode `interpretCommand()`

```java
public void interpretCommand(.) {

    [...]

    try {
        String vCommandWord = vCommand.getCommandWord();
        if (vCommandWord.equals("help")) {
            this.aPlayer.printHelp();
        } else if (vCommandWord.equals("go")) {
            this.aPlayer.goRoom(vCommand);
        } else if (vCommandWord.equals("quit")) {
            if (vCommand.hasSecondWord()) {
                this.aGui.println("Quit what?");
            } else {
                this.endGame();
            }
        } else if (vCommandWord.equals("look")) {
            this.aPlayer.look(vCommand);
        } else if (vCommandWord.equals("eat")) {
            this.aPlayer.eat();
        } else if (vCommandWord.equals("back")) {
            this.aPlayer.back(vCommand);
        } else if (vCommandWord.equals("test")) {
            this.test(vCommand);
        }
    } catch (Exception pE) {
            // use try catch to avoid error
    }
}
```

#### Exercice 7.30

Implémentation de deux nouvelles commandes `take` et `drop` pour prendre et lacher des objets, pour se faire, comme d'habitude nous devons les rajouter dans la classe `CommandWords`

```java
public CommandWords() {
    this.aValidCommands = new String[9];

    [...]

    this.aValidCommands[7] = "take";
    this.aValidCommands[8] = "drop";
    }
```

Nous créons dorénavent les commandes dans la classe `Player`. Nous créons une `HashMap aInventory` pour stocker les objets, nous les utilisons depuis le début du projet donc nous avons maintenant aquis certains automatismes

```java
private HashMap<String, Item> aInventory;

public Player(.) {

    [...]

    this.aInventory = new HashMap<String, Item>();
}
```

Nous nous attaquons maintenant à la création des commandes. Les deux sont assez similaires, `take` est l'inverse de `drop` c'est pour cela que les codes se ressemble, nous commençons par créer une `String` et un `Item` pour que ce soit plus simple dans la manipulation de notre `HashMap`. Nous effectuons ensuite les tests pour savoir si il y à bien un deuxième mot puis si l'objet est bien dans la salle pour `take` et si le joueur a bien l'objet pour `drop`. Lorsque les tests sont effectués, on peut soit prendre, soit lacher l'objet. Nous utiliserons ici un `StringBuilder` comme vu lors d'un précédent exercice pour générer la chaine de caractère de l'inventaire du joueur puis l'afficher. Pour finir nous retirons l'objet de la salle pour la commande `take` ou de l'inventaire du joueur lorsque celui-ci pose l'objet

```java
protected void take(final Command pItemName) {
    String vItemName = pItemName.getSecondWord();
    Item vItem = this.aCurrentRoom.getItemName(vItemName);
    if (!pItemName.hasSecondWord()) {
        this.aGui.println("What do you want to take");
    } else if (vItem == null) {
        this.aGui.println("This is not here");
    } else {

        this.aInventory.put(vItemName, vItem);

        StringBuilder vInventoryBuilder = new StringBuilder( "your inventory : " );
        Set<String> vKeys = aInventory.keySet();
        for ( String vS : vKeys )vInventoryBuilder.append( " " + vS );
        String vInventory = vInventoryBuilder.toString();
        this.aGui.println(vInventory);
        this.aCurrentRoom.removeItem(vItemName);
    }
}

protected void drop(final Command pItemName) {
    String vItemName = pItemName.getSecondWord();
    Item vItem = this.aInventory.get(vItemName);
    if (!pItemName.hasSecondWord()) {
        this.aGui.println("What do you want to drop");
    } else if (vItem == null) {
        this.aGui.println("You dont have this");
    } else {

        this.aInventory.remove(vItemName);

        StringBuilder vInventoryBuilder = new StringBuilder( "your inventory : " );
        Set<String> vKeys = aInventory.keySet();
        for ( String vS : vKeys )vInventoryBuilder.append( " " + vS );
        String vInventory = vInventoryBuilder.toString();
        this.aGui.println(vInventory);
        this.aCurrentRoom.addItem(vItemName, vItem);
    }
}
```

Pour finir dans la classe `GameEngine` nous ajoutons les deux nouvelles commandes à la méthode `interpretCommand()`

```java
else if (vCommandWord.equals("take")) {
    this.aPlayer.take(vCommand);
} else if (vCommandWord.equals("drop")) {
    this.aPlayer.drop(vCommand);
}
```

#### Exercice 7.31

L'exercice précédent réponds aussi à cet exercice.
Les fichier de test sont mis à jour

#### Exercice 7.31.1

Nous créons ici une nouvelle classe `ItemList` qui permettra de mutualiser la gestion des items
Dans un premier temps la classe `ItemList`

```java
import java.util.HashMap;
import java.util.Set;

public class ItemList {
    private HashMap<String, Item> aItems;

    public ItemList(){
        this.aItems = new HashMap<String, Item>();
    }

    public Item getItemName (final String pName){
        return this.aItems.get(pName);
    }

    /**
     * This String get all the item in the String
     *
     * @return a string describing the room's items, for example
     *         "Items : sword shield"
     */
    public String getItemString() {
        String vItemString = "Items :";
        Set<String> vKeys = aItems.keySet();
        for (String vItem : vKeys) {
            vItemString += " " + vItem;
        }
        return vItemString;
    }

    public String getInventoryString(){
        if (this.aItems.isEmpty()){
            return "your inventory is empty";
        } else {
            StringBuilder vInventoryBuilder = new StringBuilder("your inventory : ");
            Set<String> vKeys = aItems.keySet();
            for (String vS : vKeys){
                vInventoryBuilder.append(" " + vS);
            }
            return vInventoryBuilder.toString();
        }
    }

    public ItemList getItemList(){
        return this;
    }

    public void addItem (final String pName, final Item pItem){
        this.aItems.put(pName, pItem);
    }

    public void removeItem (final String pName, final Item pItem){
        this.aItems.remove(pName, pItem);
    }

    public boolean isEmpty(){
        return this.aItems.isEmpty();
    }
}
```

Nous changeons donc quelques méthodes dans `Room` et dans `Player` pour faire des appels aux méthodes de la classe `ItemList` tel que `addItem()`, `removeItem()` et `getItemString()` par exemple

```java
public void addItem(final String pName, final Item pItem) {
    this.aItems.addItem(pName, pItem);
}

public void removeItem(final String pName, final Item pItem) {
    this.aItems.removeItem(pName, pItem);
}

public String getItemString(){
    return this.aItems.getItemString();
}
```

Dans la classe `Player` les commandes `take` et `drop` sont modifiées pour utiliser la classe `ItemList`

```java
protected void take(final Command pItemName) {
    String vItemName = pItemName.getSecondWord();
    Item vItem = this.aCurrentRoom.getItemName(vItemName);
    if (!pItemName.hasSecondWord()) {
        this.aGui.println("What do you want to take");
    } else if (vItem == null) {
        this.aGui.println("This is not here");
    } else {

        this.aInventory.addItem(vItemName, vItem);
        this.aCurrentRoom.removeItem(vItemName, vItem);
        this.aGui.println(this.aInventory.showInventory());
        printLocationInfo();
    }
}

protected void drop(final Command pItemName) {
    String vItemName = pItemName.getSecondWord();
    Item vItem = this.aInventory.getItemName(vItemName);
    if (!pItemName.hasSecondWord()) {
        this.aGui.println("What do you want to drop");
    } else if (vItem == null) {
        this.aGui.println("You dont have this");
    } else {

        this.aInventory.removeItem(vItemName, vItem);
        this.aCurrentRoom.addItem(vItemName, vItem);
        this.aGui.println(this.aInventory.showInventory());
        printLocationInfo();
    }
}
```

#### Exercice 7.32

Nous ajoutons une contrainte de poids que le joueur peut porter, cela va se répercuter sur les objets et nous allons commencer par la classe `ItemList`. Nous ajoutons un nouvel attribut `aWeight` et nous l'initialisons dans le constructeur

```java
private int aWeight;

public ItemList() {

    [...]

    this.aWeight = 0;
}
```

Nous créons ensuite un accesseur

```java
public double getWeight(){
    return this.aWeight;
}
```

Puis nous créons les méthodes pour gérer le poids du joueur et l'afficher

```java
public void addWeight(final double pWeight){
    this.aWeight += pWeight;
}

public void removeWeight(final double pWeight){
    this.aWeight -= pWeight;
}

public String showWeight(){
    String vWeightString = "Weight : " + this.getWeight() + "kg / 20kg";
    return vWeightString;
}
```

Viens maintenant le tour de la classe `Player`. Nous ajoutons un attribut `aMaxWeight` et nous l'initialisons dans le constructeur à 20. Nous créons ensuite un getter

```java
public int getMaxWeight(){
    return this.aMaxWeight;
}
```

Puis nous modifions les méthodes `take` et `drop` déjà existante

```java
protected void take(.) {

    [...]

    else if (this.aInventory.getWeight()+vItem.getWeight() > this.aMaxWeight){
        this.aGui.println("Your inventory is full. Drop some items");
    } else {

        [...]

        this.aInventory.addWeight(vItem.getWeight());

        [...]

        this.aGui.println(this.aInventory.showWeight());

        [...]
    }
}

protected void drop(final Command pItemName) {

        [...]

        else {

            [...]

            this.aInventory.removeWeight(vItem.getWeight());

            [...]

            this.aGui.println(this.aInventory.showWeight());

            [...]
        }
    }
```

Nous ajoutons une condition pour la commande `take` pour vérifier si l'inventaire n'est pas rempli

#### Exercice 7.33

Nous ajoutons une nouvelle commande pour voir l'inventaire du joueur

```java
public CommandWords() {
    this.aValidCommands = new String[10];

    [...]

    this.aValidCommands[9] = "inventory";
}
```

Nous créons une méthode `showInventory()`

```java
protected void showInventory() {
    this.aGui.println(this.aInventory.getInventoryString());
    this.aGui.println(this.aInventory.getWeightString());
}
```

Puis nous ajoutons la commande dans `interpretCommand()`

```java
public void interpretCommand(.) {

    [...]

    try {

        [...]

        else if (vCommandWord.equals("inventory")) {
            this.aPlayer.showInventory();
        }

    [...]

    }
```

#### Exercice 7.34

Modification de la commande `eat` pour rajouter un cookie magique qui double la taille de l'inventaire lorsqu'on le mange

```java
protected void eat(final Command pCommand) {
    String vItemName = pCommand.getSecondWord();
    Item vItem = this.aInventory.getItemName(vItemName);
    if (!pCommand.hasSecondWord()) {
        this.aGui.println("what do you want to eat");
    } else {
        if (vItemName.equals("Cookie") && this.aInventory.getItemList().contain(vItem)) {
            this.aMaxWeight *= 2;
            this.aInventory.removeItem(vItemName, vItem);
            this.aInventory.removeWeight(vItem.getWeight());
            this.aGui.println("You eat a cookie");
            showInventory();
        } else{
            this.aGui.println("You cant eat that");
        }
    }
}
```

Mise a jour du code permettant d'avoir le poids maximum de l'inventaire. Nous ne pouvions le gérer depuis la classe `ItemList` le poids maximum écrit en "dur" de `getWeightString()` est donc supprimé et des modifications sont effectuées dans la classe `Player` sur la méthode `showInventory()`

```java
protected void showInventory() {
    this.aGui.println(this.aInventory.getInventoryString());
    this.aGui.println(this.aInventory.getWeightString() + this.aMaxWeight + "kg");
}
```

---

Découverte d'un petit problème d'affichage lorsque nous prenions un objet dans une salle et qu'il n'y en avais donc plus. La correction à effectuer est dans la classe `Room` sur les méthodes `getItemString()` et `getLongDescription()`. Au lieu de laisser gérer la seconde méthode pour savoir si il y a un objet dans la pièce où pas, il est plus logique de déléguer cela à la méthode `getItemString`

```java
public String getItemString() {
    if (this.aItems.isEmpty()) {
        return "No item here.";
    } else {
        return this.aItems.getItemString();
    }
}
```

```java
public String getLongDescription() {
    return "You are " + aDescription + ".\n" +
            getExitString() + "\n" +
            getItemString();
}
```

---

#### Exercice 7.34.1

Mise à jour du fichier `allCommands.txt` avec les nouvelles commandes. Le fichier `fastEnd.txt` n'est pas mis à jour car des fonctionnalités ne sont pas implémenter pour finir le jeu actuellement, à voir si c'est le cas dans les prochains exercices du cahier des charges ou si il faudra le faire soit même

#### Exercice 7.34.2

Re-génération des deux javadoc, mise à jour des documentations sur le site

---

Refonte de la partie qui gère les items dans la classe `GameEngine` ajout d'une méthode `createItems()` qui s'occupera de créer les objets et de les placer dans les salles

```java
private void createItems() {
    Item vTest = new Item("TestItem", 10, 5, "This is a test item"); // Name, price, weight, desc
    Item vTest2 = new Item("TestItem2", 20, 10, "This is a test item 2"); // Name, price, weight, desc
    this.aRooms.get("Outside").addItem("Test", vTest);
    this.aRooms.get("Outside").addItem("Test2", vTest2);

    Item vWarmogArmor = new Item("Warmog's Armor", 0, 40, "This is the armor of Warmog the Giant");
    this.aRooms.get("Boss1Room").addItem("Warmog's_Armor", vWarmogArmor);

    Item vBOTRK = new Item("Blade Of The Ruined King", 0, 20,
            "This is the blade of Viego, it weighs nothing compared to its burden");
    this.aRooms.get("Boss2Room").addItem("Blade_Of_The_Ruined_King", vBOTRK);

    Item vFrostFireGauntlet = new Item("Frostfire Gauntlet", 0, 10, "This is the last artefact of the dungeon");
    this.aRooms.get("Boss3Room").addItem("Frostfire_Gauntlet", vFrostFireGauntlet);

    Item vWeddingRing = new Item("Wedding Ring", 0, 0, "This is a wedding ring, it's will be usefull");
    this.aRooms.get("Catacombs").addItem("Wedding_ring", vWeddingRing);

    Item vMagicCookie = new Item("Cookie", 0, 0, "This is a magic cookie");
    this.aRooms.get("Treasure").addItem("Cookie", vMagicCookie);
}
```

Cette méthode est appelé dans le constructeur de la classe `GameEngine`

---

#### Exercice 7.35

Une refonte de la classe `CommandWords` est demandée dans cette exercice. L'ajout d'une enum `CommandWord` est effectuée pour répondre au cahier des charges

```java
public enum CommandWord{
    // A value for each command word, plus one for unrecognised
    // commands.
    HELP, GO, QUIT, LOOK, EAT, BACK, TEST, TAKE, DROP, INVENTORY, UNKNOWN;
}
```

Cette enum sert à lister les commandes possible pour l'utilisateur

```java
import java.util.HashMap;
import java.util.Set;

public class CommandWords {
    // a constant array that will hold all valid command words
    private HashMap<String, CommandWord> aValidCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        this.aValidCommands = new HashMap<String, CommandWord>();
        this.aValidCommands.put("go", CommandWord.GO);
        this.aValidCommands.put("help", CommandWord.HELP);
        this.aValidCommands.put("quit", CommandWord.QUIT);
        this.aValidCommands.put("look", CommandWord.LOOK);
        this.aValidCommands.put("eat", CommandWord.EAT);
        this.aValidCommands.put("back", CommandWord.BACK);
        this.aValidCommands.put("test", CommandWord.TEST);
        this.aValidCommands.put("take", CommandWord.TAKE);
        this.aValidCommands.put("drop", CommandWord.DROP);
        this.aValidCommands.put("inventory", CommandWord.INVENTORY);
    } // CommandWords()

    /**
     * Check whether a given String is a valid command word.
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return aValidCommands.containsKey(aString);
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param pCommandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String pCommandWord)
    {
        CommandWord vCommand = aValidCommands.get(pCommandWord);
        if(vCommand != null) {
            return vCommand;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * @return a String of all valid commands.
     */
    public String getCommandList() {
        String vCommandString = "";
        Set<String> vKeys = aValidCommands.keySet();
        for (String vCommand : vKeys) {
            vCommandString += " " + vCommand;
        }
        return vCommandString;
    }
} // CommandWords
```

La classe `CommandWords` est entierement refaite pour fonctionner avec cette nouvelle enum. De légères modifications sont apportées à la classe `Command` pour que celle ci prenne en compte l'enum que nous venons de créer

```java
public class Command {
    private CommandWord aCommandWord;

    [...]

    public Command(final CommandWord pCommandWord, .) {
        this.aCommandWord = pCommandWord;

        [...]

    }

    public CommandWord getCommandWord() {
        return this.aCommandWord;
    }

    [...]

    public boolean isUnknown() {
        return this.aCommandWord == CommandWord.UNKNOWN;
    }
}
```

Une petite modification est apportée à la classe `Parser` sur la fonction `getCommand()`

```java
public Command getCommand(final String pInputLine) {

    [...]

    return new Command(aCommandWords.getCommandWord(vWord1), vWord2);
}
```

Deux modifications sont aussi apportées dans la classe `UserInterface`. Après avoir initialisé la classe `Parser` dans le constructeur de l'interface utilisateur nous effectuons les modifications suivante :

```java
public void actionPerformed(final ActionEvent pE) {
    // check the type of action
    if (pE.getActionCommand() != null) {
        this.aEngine.interpretCommand(aParser.getCommand(pE.getActionCommand()));

        [...]

}

private void processCommand() {

    [...]

    this.aEngine.interpretCommand(aParser.getCommand(vInput));
}
```

Le plus gros changement est dans la classe `GameEngine`

```java
public void interpretCommand(final Command pCommandLine) {

    this.aGui.println("> " + aGui.getEntryField());

    CommandWord vCommandWord = pCommandLine.getCommandWord();

    if (pCommandLine.isUnknown()) {
        this.aGui.println("I don't know what you mean...");
    }

    try {

        if (vCommandWord == CommandWord.HELP) {
            this.aPlayer.printHelp();
        } else if (vCommandWord == CommandWord.GO) {
            this.aPlayer.goRoom(pCommandLine);
        } else if (vCommandWord == CommandWord.QUIT) {
            if (pCommandLine.hasSecondWord()) {
                this.aGui.println("Quit what?");
            } else {
                this.endGame();
            }
        } else if (vCommandWord == CommandWord.LOOK) {
            this.aPlayer.look(pCommandLine);
        } else if (vCommandWord == CommandWord.EAT) {
            this.aPlayer.eat(pCommandLine);
        } else if (vCommandWord == CommandWord.BACK) {
            this.aPlayer.back(pCommandLine);
        } else if (vCommandWord == CommandWord.TEST) {
            this.test(pCommandLine);
        } else if (vCommandWord == CommandWord.TAKE) {
            this.aPlayer.take(pCommandLine);
        } else if (vCommandWord == CommandWord.DROP) {
            this.aPlayer.drop(pCommandLine);
        } else if (vCommandWord == CommandWord.INVENTORY) {
            this.aPlayer.showInventory();
        }

        [...]
    }
}
```

Pour finir, on met à jour la commande de test (interpretCommand) prenait une `String` en paramètre et maintenant une `Command` donc nous créons une variable qui obtient la commande de la `String` entrée en paramètre

```java
private void test(Command pFile) {
    if (pFile.hasSecondWord()) {
        try {

            [...]

            while (vScanner.hasNextLine()) {
                Command vCommand = aParser.getCommand(vCommandString);
                interpretCommand(vCommand);
                vCommandString = vScanner.nextLine();
            }

        [...]

        }
}
```

Création d'une fonction `getEntryField()` qui permet au joueur de savoir quelle commande il a rentré car avec les modifications de la méthode `interpretCommand()` l'affichage était `> Command@6c3c90c9` donc la fonction vient corriger ceci

```java
public String getEntryField() {
    return this.aEntryField.getText();
}
```

Problème détecté lorsque nous utilisons la commande test, la commande rentrée est la bonne mais pas la commande affichée. Une correction est en cours de recherche

#### Exercice 7.35.1

Changement dans la méthode `interpretCommand()`. Nous enlevons les `if`, `else` au profit d'un `switch`

```java
public void interpretCommand(final Command pCommandLine) {

        [...]

        try {

            switch (vCommandWord) {
                case HELP:
                    this.aPlayer.printHelp();
                    break;

                case GO:
                    this.aPlayer.goRoom(pCommandLine);
                    break;

                case QUIT:
                    if (pCommandLine.hasSecondWord()) {
                        this.aGui.println("Quit what?");
                    } else {
                        this.endGame();
                    }
                    break;

                case LOOK:
                    this.aPlayer.look(pCommandLine);
                    break;

                case EAT:
                    this.aPlayer.eat(pCommandLine);
                    break;

                case BACK:
                    this.aPlayer.back(pCommandLine);
                    break;

                case TEST:
                    this.test(pCommandLine);
                    break;

                case TAKE:
                    this.aPlayer.take(pCommandLine);
                    break;

                case DROP:
                    this.aPlayer.drop(pCommandLine);
                    break;

                case INVENTORY:
                    this.aPlayer.showInventory();
                    break;

                default:
                    this.aGui.println("I don't know what you mean...");
                    break;
            }

        }

        [...]

    }
```

#### Exercice 7.37

Pour traduire les commandes, grâce à l'enum `CommandWord` précédement crée il suffit de modifier

```java
this.aValidCommands.put("help", CommandWord.HELP);
```

par

```java
this.aValidCommands.put("aide", CommandWord.HELP);
```

N'étant pas utile pour mon jeu, tout en anglais, la fonctionnalité optionnelle n'est pas implémenté

#### Exercice 7.38

Une fois la modification précédente effectuée, le message de bienvenue disait toujours `... Type 'help' if you need help.`

#### Exercice 7.40 - 7.41

L'ajout de la nouvelle version de l'enum `CommandWord` nous permet de modifier notre méthode `printWelcome()` pour qu'elle affiche le mot de commande `help` traduit

```java
private void printWelcome() {

    [...]

    this.aGui.println("Type '"+ CommandWord.HELP.toString() +"' if you need help.");

    [...]
}
```

#### Exercice 7.41.1

Version incorporée à l'exercice précédent

#### Exercice 7.41.2

Javadoc à jour et mise sur le site

#### Exercice 7.42

Nous créons un nouvel attribut, qui aura pour valeur, le nombre de pas maximum que le joueur peut effectuer. Nous créons l'accesseur de cet attribut

```java
public int getMovement(){
    return this.aMovement;
}
```

Nous effectuons ensuite la modification dans la méthode `goRoom()` et `back()`

```java
protected void goRoom(final Command pDirection) {

    [...]

    } else {

        [...]

        aMovement -= 1;
    }
}

[...]

protected void back(final Command pCommand) {

    [...]

    } else {

        [...]

        aMovement -=1;
    }
}
```

Puis nous ajoutons une condition à la méthode `processCommand()` pour que quand le nombre de pas est atteint, le joueur ne puisse plus rentrer de commande

```java
public void interpretCommand(final Command pCommandLine) {

    [...]

    if (aPlayer.getMovement() <= 0) {
        this.gameOver();
    } else {
        try {

            [...]

        }catch (Exception pE) {
            [...]
        }
    }
}

private void gameOver(){
    this.aGui.println("game over.");
    this.aGui.enable(false);
}
```

#### Exercice 7.42.1

A FAIRE

#### Exercice 7.42.2

//TODO

Une IHM plus poussée est développée.

```java
private void createGUI() {
    this.aMyFrame = new JFrame();
    this.aMyFrame.setTitle("Zuul GOTY Edition");
    // this.aMyFrame.setSize(650, 950);
    this.aMyFrame.setResizable(false);
    this.aMyFrame.setBackground(Color.DARK_GRAY);

    this.aEntryField = new JTextField(34);

    ImageIcon aQuitIcon = new ImageIcon("gameImages/quit.png");
    ImageIcon aGameIcon = new ImageIcon("gameImages/game.png");

    this.aQuitButton = new JButton("quit", aQuitIcon);
    this.aNorthButton = new JButton("north ▲");
    this.aEastButton = new JButton("east ▶");
    this.aSouthButton = new JButton("south ▼");
    this.aWestButton = new JButton("◀ west");
    this.aUpButton = new JButton("up △");
    this.aDownButton = new JButton("down ▽");
    this.aBackButton = new JButton("back ↺");
    this.aHelpButton = new JButton("help ?");
    this.aDropButton = new JButton("drop");
    this.aTakeButton = new JButton("take");
    this.aFireButton = new JButton("fire");
    this.aChargeButton = new JButton("charge");

    this.aMyFrame.setIconImage(aGameIcon.getImage());

    this.aLog = new JTextArea();
    this.aLog.setEditable(false);
    this.aLog.setLineWrap(true);
    this.aLog.setWrapStyleWord(true);
    this.aLog.setMargin(new Insets(10, 10, 10, 10));
    JScrollPane vListScroller = new JScrollPane(this.aLog);
    vListScroller.setPreferredSize(new Dimension(200, 200));
    vListScroller.setMinimumSize(new Dimension(100, 100));

    JPanel vImagePanel = new JPanel();
    this.aImage = new JLabel();

    vImagePanel.setLayout(new BorderLayout()); // ==> only five places
    vImagePanel.add(this.aImage, BorderLayout.CENTER);

    JPanel vTextPanel = new JPanel();

    vTextPanel.setLayout(new BorderLayout());
    vTextPanel.add(vListScroller, BorderLayout.CENTER);
    vTextPanel.add(this.aEntryField, BorderLayout.SOUTH);

    JPanel vButtonPanel1 = new JPanel();
    JPanel vCenterButtonPanel = new JPanel();

    vCenterButtonPanel.setLayout(new GridLayout(1, 2));
    vCenterButtonPanel.setBackground(Color.DARK_GRAY);
    vCenterButtonPanel.add(this.aUpButton);
    vCenterButtonPanel.add(this.aDownButton);

    vButtonPanel1.setLayout(new BorderLayout());
    vButtonPanel1.setBackground(Color.DARK_GRAY);
    vButtonPanel1.add(this.aNorthButton, BorderLayout.NORTH);
    vButtonPanel1.add(this.aWestButton, BorderLayout.WEST);
    vButtonPanel1.add(this.aSouthButton, BorderLayout.SOUTH);
    vButtonPanel1.add(this.aEastButton, BorderLayout.EAST);
    vButtonPanel1.add(vCenterButtonPanel, BorderLayout.CENTER);

    JPanel vButtonPanel2 = new JPanel();
    vButtonPanel2.setLayout(new GridLayout(3, 3));
    vButtonPanel2.setBackground(Color.DARK_GRAY);
    vButtonPanel2.add(this.aBackButton);
    vButtonPanel2.add(this.aHelpButton);
    vButtonPanel2.add(this.aQuitButton);
    vButtonPanel2.add(this.aDropButton);
    vButtonPanel2.add(this.aTakeButton);
    vButtonPanel2.add(this.aFireButton);
    vButtonPanel2.add(this.aChargeButton);

    JPanel vPanel = new JPanel();
    vPanel.setLayout(new GridBagLayout());
    GridBagConstraints vGridBagLayout = new GridBagConstraints();
    vGridBagLayout.weightx = 1;
    vGridBagLayout.weighty = 1;

    vGridBagLayout.gridx = 0;
    vGridBagLayout.gridwidth = 2;
    vGridBagLayout.gridheight = 2;
    vGridBagLayout.gridy = 0;
    vGridBagLayout.fill = GridBagConstraints.BOTH;
    vPanel.add(vImagePanel, vGridBagLayout);

    vGridBagLayout.gridx = 0;
    vGridBagLayout.gridwidth = 1;
    vGridBagLayout.gridheight = 1;
    vGridBagLayout.gridy = 2;
    vPanel.add(vButtonPanel1, vGridBagLayout);

    vGridBagLayout.gridx = 1;
    vGridBagLayout.gridwidth = 1;
    vGridBagLayout.gridheight = 1;
    vGridBagLayout.gridy = 2;
    vPanel.add(vButtonPanel2, vGridBagLayout);

    vGridBagLayout.gridx = 2;
    vGridBagLayout.gridwidth = 1;
    vGridBagLayout.gridheight = 3;
    vGridBagLayout.gridy = 0;
    vPanel.add(vTextPanel, vGridBagLayout);

    Font vFont = new Font("Monospaced", Font.PLAIN, 14);

    this.aLog.setFont(vFont);
    this.aLog.setForeground(Color.white);
    this.aLog.setBackground(Color.darkGray);

    this.aMyFrame.getContentPane().add(vPanel, BorderLayout.CENTER);

    [...]
} // createGUI()
```

Les boutons permettant d'effectuer toutes les commandes sont ajoutées, et une réorganisation des différents éléments est mise en place.
L'ajout d'une nouvelle méthode permettant d'ecrire le texte comme si quelqu'un l'écrivait est ajoutée

```java
public void slowPrint(final String pText) {

    for (char c : pText.toCharArray()) {
        String vMessage = Character.toString(c);

        this.aLog.append(vMessage);
        this.aLog.setCaretPosition(this.aLog.getDocument().getLength());

        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

Puis ajout d'un narrateur pour le message de bienvenue

```java
public void playWelcomeSound() {
    try {
        AudioInputStream vAudioInputStream = AudioSystem
                .getAudioInputStream(new File("gameSounds/welcome.wav").getAbsoluteFile());
        Clip vClip = AudioSystem.getClip();
        vClip.open(vAudioInputStream);
        vClip.start();
    } catch (Exception ex) {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
    }
}
```

#### Exercice 7.43

La pièce `Outside` a une seule sortie, une fois celle ci prise on ne peux revenir en arrière. Il faut juste pour cela vider la pile de la commande `back`. Je décide pour cet exercice de créer une classe `Door` qui me permettra de savoir si la porte est piégée ou pas

```java
public class Door {
    private boolean aIsTrap;

    public Door(final boolean pTrap) {
        this.aIsTrap = pTrap;
    }

    public boolean isTrap() {
        return this.aIsTrap;
    }
}
```

Ensuite dans la classe `GameEngine`, je crée une methode `createDoor()`

```java
private void createDoor(){
    Door vTrapLobby = new Door(true);
    this.aRooms.get("Lobby").addDoor("up", vTrapLobby);
}
```

Nous ajoutons la méthode `addDoor()` précédement utilisée et une fonction `getDoor()`

```java
public void addDoor(final String pDirection, final Door pDoor) {
    this.aDoors.put(pDirection, pDoor);
}

public Door getDoor(final String pDirection) {
    return this.aDoors.get(pDirection);
}
```

Puis les dernières modifications sont dans la classe `Player`

```java
protected void goRoom(final Command pDirection) {

    [...]

    Door vDoor = this.getCurrentRoom().getDoor(vDirection);

    else if (vDoor != null) {
        if (vDoor.isTrap()) {
            this.aGui.println("its a trap");
            clearStack();
            return;
        }

    [...]

    }
```

La méthode `clearStack()` sert à vider la pile

```java
public void clearStack() {
    this.aPreviousRooms.clear();
}
```

Cette modification permet de bloquer le retour dans la piece précédente si le joueur effectue la commande `go up` avant `back`. Une réflexion est en cours pour corriger cela.

#### Exercice 7.44

Nous devons ici créer un téléporteur, c'est à dire une sorte d'`Item`. Lorsqu'il est chargé dans une salle, quand le joueur l'active, le téléporteur doit le ramener à la salle où il a été chargé. Pour ce faire, nous créons une nouvelle classe `Beamer` qui est donc une sorte d'`Item`

```java
public class Beamer extends Item {
    private boolean aIsCharged;
    private Room aRoomCharged;

    public Beamer() {
        super("teleporter", 10, 5, "teleport where it's charged");
        this.aIsCharged = false;
        this.aRoomCharged = null;
    }

    public void charge(final Room pRoom) {
        this.aIsCharged = true;
        this.aRoomCharged = pRoom;
    }

    public Room fire() {
        this.aIsCharged = false;
        Room vRoom = this.aRoomCharged;
        this.aRoomCharged = null;
        return vRoom;
    }

    public boolean isCharged() {
        return this.aIsCharged;
    }
}
```

Nous ajoutons donc ce nouvel objet dans le jeu, dans la classe `GameEngine`

```java
private void createItems() {

    [...]

    Beamer vBeamer = new Beamer();
    this.aRooms.get("Outside").addItem("teleporter", vBeamer);
}
```

Le téléporteur est, pour le moment, dans la première salle pour faciliter les tests.
Nous devons maintenant ajouter les deux fonctionnalités, `charge` et `fire` dans la classe `Player`

```java
public void charge() {
    Beamer vBeamer = (Beamer) this.aInventory.getItemName("teleporter");
    if (this.aInventory.contain(vBeamer)) {
        vBeamer.charge(this.aCurrentRoom);
        this.aGui.println("Teleporter is charged");
    } else {
        this.aGui.println("You dont have teleporter");
    }
}

public void fire() {
    Beamer vBeamer = (Beamer) this.aInventory.getItemName("teleporter");
    if (this.aInventory.contain(vBeamer)) {
        if (vBeamer.isCharged()) {
            this.setRoom(vBeamer.fire());
            this.aInventory.removeItem("teleporter", vBeamer);
            this.aGui.println("You have been teleported. Teleporter is destroyed");
            printLocationInfo();
            showInventory();
        } else {
            this.aGui.println("teleporter isnt loaded.");
        }
    } else {
        this.aGui.println("You dont have teleporter.");
    }
}
```

Puis pour finir nous ajoutons l'utilisation des commandes dans la méthode `interpretCommand()` de la classe `GameEngine` et nous créons les mots de commande dans l'enum `CommandWord`

```java
case CHARGE:
    this.aPlayer.charge();
    break;

case FIRE:
    this.aPlayer.fire();
    break;
```

```java
CHARGE("charge"), FIRE("fire");
```

#### Exercice 7.45.1

Mise a jour des fichiers test

#### Exercice 7.45.2

Les javadoc sont mise à jour sur le site

#### Exercice 7.46

Ajout de deux nouvelles classe pour réaliser cet exercice. Une classe `RoomRandomizer` qui cherchera une salle aléatoire dans toutes les salles possibles

```java
import java.util.HashMap;
import java.util.Random;

public class RoomRandomizer {
    private HashMap<String, Room> aAllRooms;
    private Object[] aRoomsArrayList;

    public RoomRandomizer() {
    }

    public Room findRandomRoom() {
        Random vRandom = new Random();
        int vRandomIntInArray = vRandom.nextInt(this.aRoomsArrayList.length);
        return (Room) this.aRoomsArrayList[vRandomIntInArray];
    }

    public void setRandom(final HashMap<String, Room> pAllRooms) {
        this.aAllRooms = pAllRooms;
        this.aRoomsArrayList = this.aAllRooms.values().toArray();
    }
}
```

Et une classe `TransporterRoom` qui elle, recupérera la salle aléatoire et lui attribura comme sortie

```java
public class TransporterRoom extends Room {

    private RoomRandomizer aRoomRandomizer;

    public TransporterRoom(String pDescription, String pImage, final RoomRandomizer pRoomRandomizer) {
        super(pDescription, pImage);
        this.aRoomRandomizer = pRoomRandomizer;
    }

    @Override
    public Room getExit(final String pDirection) {
        return this.aRoomRandomizer.findRandomRoom();
    }
}
```

Puis dans la classe `GameEngine` il y a des ajouts pour que cette pièce soit dans le jeux.
On ajoute un nouvel attribut

```java
private RoomRandomizer aRandomRoom;
```

Puis on l'initialise dans la pièces dans `createRooms()`

```java
vTestRoom = new TransporterRoom("This is a test room", "gameImages/test.gif", this.aRandomRoom);
```

#### Exercice 7.46.1

Nous mettons à jour la classe `RoomRandomizer` pour ajouter la commande alea

```java
import java.util.HashMap;
import java.util.Random;

public class RoomRandomizer {

    [...]

    private boolean aAlea;

    public RoomRandomizer() {
        this.aAlea = false;
    }

    [...]

    public void setAlea(Room pRoom) {
        this.aRoomsArrayList = new Object[] { pRoom };
    }

    [...]

    public boolean isAlea() {
        return this.aAlea;
    }
}
```

Puis après avoir ajouté la commande dans la classe `CommandWord` nous nous attaquons à la classe `GameEngine`. Cela permet de mettre une piece pour enlever l'aléatoire de la sortie de la `TransporterRoom`

```java
private void alea(final Command pRoom) {
    if (!aTest) {
        this.aGui.println("You need to be in test mode");
    }
    if (pRoom.hasSecondWord() && !aRandomRoom.isAlea()) {
        Room vRoom = aRooms.get(pRoom.getSecondWord());
        if (vRoom == null) {
            System.out.println("Room dont found");
        }
        aRandomRoom.setAlea(vRoom);
    } else {
        aRandomRoom.setRandom(aRooms);
    }
}
```

#### Exercice 7.46.2

L'héritage est utilisé pour la classe `Beamer` c'est une sorte d'`Item`

#### Exercice 7.46.3

Mise à jour de la javadoc

#### Exercice 7.46.4

Mise à jour de la javadoc sur le site
