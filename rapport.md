<h1 align="center">RAPPORT IPO</h1>

![ESIEE PARIS](https://upload.wikimedia.org/wikipedia/fr/thumb/7/71/Logo_ESIEE_Paris.svg/1200px-Logo_ESIEE_Paris.svg.png)

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
- [Réponses aux exercices](#reponses-aux-exercices)
- [Mode d'emploi](#)
- [Déclaration anti-plagiat](#)

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

### Scénario détaillé

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

  - lorem ipsum
  - lorem ipsum

- #### Combats
  - Combat contre les boss
  - Combat contre le marchant ?? (pas sur)

### Commentaires

Il y a encore beaucoup de travail pour tout finaliser

## Réponses aux exercices

#### Exercice 7.5

La création de la methode `printLocationInfo()` dans la classe `Game` permet d'éviter la duplication de code. En effet il y a à 2 reprises le même segment de code (`goRoom()` et `printWelcome()`) pour informer le joueur de sa postion et les sorties disponibles. La création de `printLocationInfo()` permet de remplacer chaque occurence par un appel à la procedure, cela facilite aussi grandement les modifications futures car il y aura juste ce passage de code a modifier si on veut changer le texte pour les informations des salles.

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

Les modifications suivantes ont donc été effectué dans `goRoom()` et dans `printWelcome()`

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

La création de l'accesseur `getExit()` dans la classe `Room` permet de reduire le couplage, et de respecter un principe fondamental d'une bonne conception de classe qui est _l'encapsulation_. Cela nous permet de passer les attributs de la classe en privé. Le code passe de

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

Il y a aussi des modifications a faire dans la classe `Game` qui sont

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
        if(aCurrentRoom.getExit("north") != null){
            System.out.print("north ");
        }
        if(aCurrentRoom.getExit("east") != null){
            System.out.print("east ");
        }
        if(aCurrentRoom.getExit("south") != null){
            System.out.print("south ");
        }
        if(aCurrentRoom.getExit("west") != null){
            System.out.print("west ");
        }
        System.out.println();
    }
}
```

#### Exercice 7.7

La création de la méthode `getExitString()` doit etre crée dans la classe `Room` car celle ci gere tout ce qui refere aux salles. L'affichage se passe dans la classe `Game` avec la methode `printLocationInfo()` précédemment crée.

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

```java
private void printLocationInfo()
{
    System.out.println("You are "+currentRoom.getDescription());
    System.out.println(aCurrentRoom.getExitString());
}
```

#### Exercice 7.8

Modification des attributs de la classe `Room` pour les metttres dans une hashmap, pour ce faire il faut écrire en haut de la classe `import java.util.HashMap;`et pour l'initialiser `private HashMap<String, Room> aExits;`. Le code de la classe `Room` est donc comme suit

```java
import java.util.HashMap;

public class Room
{
    private HashMap<String, Room> aExits;

    public Room(final String pDescription) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String,Room>();
    }

    public void setExits(final String pDirec, Room pNeighbor){
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

et des changements dans `Game`

```java
public class Game
{

    [...]

    private void createRooms(){

        [...]

        vOutside.setExits("east", vTheatre);
        vOutside.setExits("south", vLab);
        vOutside.setExits("west", vPub);

        vTheatre.setExits("west", vOutside);

        vPub.setExits("east", vOutside);

        vLab.setExits("north", vOutside);
        vLab.setExits("east", vOffice);

        vOffice.setExits("west", vLab);

        [...]
    }

    [...]

}
```

#### Exercice 7.8.1

L'ajout d'un deplacement vertical entre l'exterieur et le dongeon est ajouté. Les directions sont donc maintenant, `north` `east` `south` `west` et `down`

```java
vOutside.setExits("down", vLobby);
```

#### Exercice 7.9

La méthode keySet() permet d'associer des valeurs arbitraires à des clés.

```java
import java.util.Set;
```

Ainsi, il peut associer le mot de commande à la clé qui ouvrira l'accès au HashMap contenant toutes les pièces. **Exemple :** pour le lobby, les sorties sont `north`, `east` et `west`. La clé nord sur la commande `go north` permettra d'accéder a la salle au nord du lobby.

#### Exercice 7.10

La methode `getExitString()` permet de récupérer toutes les sorties d'une pieces sous forme d'une `String`

```java
        String vReturnString = "Exits : ";
```

Cette ligne crée une variable `vReturnString` de type `String` qui contient la chaine de caractères `"Exits : "`

```java
        Set<String> vKeys = aExits.keySet();
```

Celle-ci crée une variable `vKeys` de type `Set<String>`. Les éléments contenus sont les clés de la `HashMap aExits` sous forme d'une liste de `String` qui ne peut pas comporter 2 fois le même élement.

```java
        for(String vExit : vKeys){
            vReturnString += " " + vExit;
        }
```

Ce bout de code est une boucle `for each` qui parcours la liste des clés stockées. `vExit` est une variable qui prend la valeur de la prochaine clé de la `HashMap`à chaque fois que la bocule se répète, ce qui fait que `vReturnString` obtiens toutes les sorties disponibles dans le `String`.

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

Dans la classe game on effectue donc des modifications

```java
    private void printLocationInfo() {
        System.out.println(aCurrentRoom.getLongDescription());
    }
```

#### Exercice 7.14

La commande look qu'on ajoute dans la classe `CommandWord` avec cette ligne 
```java
this.aValidCommands[3] = "look";
```
Une fois cela fait il faut creer une methode pour la commande look qui nous permettra d'avoir les informations sur la salle et ses sorties autant de fois qu'on veut

```java
    private void look(){
        printLocationInfo();
    }
```

Et pour que la methode fonctionne quand on la tape au clavier il faut ajouter 
```java 
    else if(pCommand.getCommandWord().equals("look")){
        this.look();
    }
```
dans `processCommand()`

#### Exercice 7.15
On refait la meme chose pour eat 
```java
    private void eat(){
        System.out.println("You have eaten now and you are not hungry any more.");
    }
```
#### Exercice 7.16

On crée une méthode `showAll()` dans la classe `CommandWords` pour regrouper toutes les commandes dans un `String`

```java
    public void showAll(){
        for(String vCommand : aValidCommands){
            System.out.print(vCommand + " ");
        }
        System.out.println();
    }
```

Ensuite dans la classe `Parser` on crée une méthode `showCommands` pour afficher toutes les commandes

```java
    public void showCommands(){
        aValidCommands.showAll();
    }
```
Puis on remplace le texte écrit en dur par la methode `showCommands()` 

```java
    private void printHelp() {
        
        [...]

        System.out.println("Your command words are:");
        aParser.showCommands();
    }   
```

#### Exercice 7.18 

Dans la classe `CommandWords` la méthode `showAll()` devient la fonction `getCommandList()` et subis quelques modifications

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

La méthode `showCommands()` est aussi modifié par une fonction `getCommandString()`

```java
    public String getCommandString()
    {
        return this.aValidCommands.getCommandList();
    } 
```

Les modifications sont légere dans `printHelp()` il y a juste à changer `aParser.showCommands()` par `aParser.getCommandString()`

```java
    private void printHelp() {
        
        [...]

        aParser.getCommandString();
    }
```

#### Exercice 7.18.1

Les deux projets sont vraiment similaire ce qui est logique car tout les exercices ont été effectué.