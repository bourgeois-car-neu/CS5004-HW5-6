# Board Game Arena Planner Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.

If you are using mermaid markup to generate your class diagrams, you may edit this document in the sections below to insert your markup to generate each diagram. Otherwise, you may simply include the images for each diagram requested below in your zipped submission (be sure to name each diagram image clearly in this case!)


## (INITIAL DESIGN): Class Diagram 

Include a UML class diagram of your initial design for this assignment. If you are using the mermaid markdown, you may include the code for it here. For a reminder on the mermaid syntax, you may go [here](https://mermaid.js.org/syntax/classDiagram.html)

### Provided Code

Provide a class diagram for the provided code as you read through it.  For the classes you are adding, you will create them as a separate diagram, so for now, you can just point towards the interfaces for the provided code diagram.
```mermaid
---
title: BoardGame
---
classDiagram
    direction LR
    GameList --|> IGameList
    Planner --|> IPlanner
    IPlanner --> GameData : use
    ConsoleApp --> IPlanner: use
    ConsoleApp --> IGameList: use
    BoardGame --> GameData : use
    class BoardGame {
        - name String
        - id int
        - minPlayers int
        - maxPlayers int
        - maxPlayTime int
        - minPlayTime int
        - difficulty double
        - rank int
        - averageRating double
        - yearPublished int
        + BoardGame(String, int x7, double x2)
        + getName() String
        + getId() int
        + getMinPlayers() int
        + getMaxPlayers() int
        + getMaxPlayTime() int
        + getMinPlayTime() int
        + getDifficulty() double
        + getRank() int
        + getRating() double
        + getYearPublished() int
        + toStringWithInfo(col: GameData) String
        + toString() String
        + equals(obj: Object) boolean
        + hashCode() int
        + main(args: String) void
    }
    
    class GameList {
        + GameList()
        + getGameNames() List<String>
        + clear() void
        + count() int
        + saveGame(filename: String) void
        + addToList(str: String, filtered: Stream<BoardGame>) void
        + removeFromList(str: String) void
    }
    
    class Planner {
        + Planner(games: Set<BoardGame>)
        + filter(filter: String) Stream<BoardGame>
        + filter(filter: String, sortOn: GameData) Stream<BoardGame>
        + filter(filter: String, sortOn: GameData, ascending: boolean) Stream<BoardGame>
        + reset() void
    }

    class Operations {
        + operator String
        - Operations(operator: String) operator
        + getOperator() String
        + fromOperator(operator: String) Operations
        + getOperatorFromStr(str: String) Operations
    }
    
    class GameData {
        - columnName String
        - GameData(columnName: String)
        + getColumnName() String
        + fromColumnName(columnName: String) GameData
        + fromString(name: String) GameData
    }
    class GameLoader {
        - GamesLoader()
        + loadGamesFile(filename: String) Set<BoardGame>
        - toBoardGame(line: String, columnMap Map<GameData, Integer>) BoardGame
        - processHeader(header: String) Map<GameData, Integer>
    }
    
    class ConsoleApp {
        + ConsoleApp(gameList: IGameList, planner: IPlanner)
        + start() void
        - randomNumber() void
        - processHelp() void
        - processFilter() void
        - printFilterStream(games: Stream<BoardGame>, sortON: GameData) void
        - processListCommands() void
        - printCurrentList() void
        - nextCommand() ConsoleText
        - remainder() String
        - getInput(format: String, args: Object) String
        - printOutput(format: String, args: Object) void
        + toString() String
        + fromString(str: String) ConsoleText
        
    }
    class IPlanner {
        <<interface>>
        + filter(filter: String) Stream
        + filter(filter: String sortOn: GameData) Stream
        + filter(filter: String, sortOn: GameData, ascending: boolean) Stream
    }

    class IGameList {
        <<interface>>
        + ADD_ALL: String = "all"
        + getGameNames() List
        + count() int
    }
    
```


### Your Plans/Design

Create a class diagram for the classes you plan to create. This is your initial design, and it is okay if it changes. Your starting points are the interfaces. 





## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test 1..
2. Test 2..




## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

For the final design, you just need to do a single diagram that includes both the original classes and the classes you added. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.





## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning to information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 
