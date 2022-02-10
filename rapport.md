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

### Scénario

### Plan

### Scénario détaillé

### Détail des lieux, items, personnages

### Situations gagnantes et perdantes

### Énigmes, mini-jeux, combats

### Commentaires

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