# Report

Submitted report to be manually graded. We encourage you to review the report as you read through the provided
code as it is meant to help you understand some of the concepts. 

## Technical Questions

1. What is the difference between == and .equals in java? Provide a code example of each, where they would return different results for an object. Include the code snippet using the hash marks (```) to create a code block. 
   ```java
   /**
   *  == compares the location in memory 
   * .equals() compares objects based on class definition of equality 
   */
   String s1 = new String("hello");
   String s2 = new String("hello"):
   
   System.out.println(s1 == s2);        // false
   System.out.println(s1.equals(s2));  // true
   
   ```

2. Logical sorting can be difficult when talking about case. For example, should "apple" come before "Banana" or after? How would you sort a list of strings in a case-insensitive manner? 
* "Banana" would come before "apple" because Java sorts strings case sensitively.
* In a case insensitive manner, the list would be sorted alphabetically. If two words were the same like "apple" and "Apple", then they would appear in their original order.


3. In our version of the solution, we had the following code (snippet)
    ```java
    public static Operations getOperatorFromStr(String str) {
        if (str.contains(">=")) {
            return Operations.GREATER_THAN_EQUALS;
        } else if (str.contains("<=")) {
            return Operations.LESS_THAN_EQUALS;
        } else if (str.contains(">")) {
            return Operations.GREATER_THAN;
        } else if (str.contains("<")) {
            return Operations.LESS_THAN;
        } else if (str.contains("=="))...
    ```
    Why would the order in which we checked matter (if it does matter)? Provide examples either way proving your point. 
      * The order that operations is checked does matter.
      * Example: "min_players >= 4" 
        * if `contains(">")` is checked first, it would immediately match with GREATER_THAN and not check for the equals part.


4. What is the difference between a List and a Set in Java? When would you use one over the other? 
* Lists allow duplicate elements and maintain the order they were added in. 
* Sets don't allow duplicates and don't guarantee a specific order.
* Use lists when duplicates are allowed, you want to order elements, and look up an element based on index. Use sets when uniqueness in the elements matter and you want faster look up speed.


5. In GamesLoader.java, we use a Map to help figure out the columns. What is a map? Why would we use a Map here? 
* A map is an interface that stores key value pairs so you can look up a specific value using a unique key. It's similar to a dictionary in python.
* In GamesLoader.java the map is useful to separate the columns based on the header line with the contents of each column. The header line being the key and the column being the values.

6. GameData.java is actually an `enum` with special properties we added to help with column name mappings. What is an `enum` in Java? Why would we use it for this application?
* `enum` is used when you have a fixed set of predefined constants. It provides type safety by restricting the values. 
* In GameData.java `enum` represent the columns in the game data. It makes it easier to access the column names from the CSV file, without needing to know the specific column names anywhere else.


7. Rewrite the following as an if else statement inside the empty code block.
    ```java
    switch (ct) {
                // if cmd_question OR cmd_help
                case CMD_QUESTION: // same as help
                case CMD_HELP:
                    processHelp();
                    break;
                // if invalid...
                case INVALID:
                // if not listed above...
                default:
                    CONSOLE.printf("%s%n", ConsoleText.INVALID);
            }
    ``` 

    ```java
    // your code here, don't forget the class name that is dropped in the switch block. 
   if (ct == CMD_QUESTION || ct == CMD_HELP) {
        processHelp();
   } else {
        // covers both default & INVALID
        CONSOLE.printf("%s%n", ConsoleText.INVALID);
   }
    ```

## Deeper Thinking

ConsoleApp.java uses a .properties file that contains all the strings
that are displayed to the client. This is a common pattern in software development
as it can help localize the application for different languages. You can see this
talked about here on [Java Localization – Formatting Messages](https://www.baeldung.com/java-localization-messages-formatting).

Take time to look through the console.properties file, and change some of the messages to
another language (probably the welcome message is easier). It could even be a made up language and for this - and only this - alright to use a translator. See how the main program changes, but there are still limitations in 
the current layout. 

Post a copy of the run with the updated languages below this. Use three back ticks (```) to create a code block. 

```text
*******¡Bienvenido al Planificador de Board Game Arena!*******

Una herramienta para ayudar a las personas a planificar qué juegos 
quieren jugar en Board Game Arena. 

Para comenzar, ingrese su primer comando abajo, o escriba ? o help para ver las opciones de comandos.
```

Now, thinking about localization - we have the question of why does it matter? The obvious
one is more about market share, but there may be other reasons.  I encourage
you to take time researching localization and the importance of having programs
flexible enough to be localized to different languages and cultures. Maybe pull up data on the
various spoken languages around the world? What about areas with internet access - do they match? Just some ideas to get you started. Another question you are welcome to talk about - what are the dangers of trying to localize your program and doing it wrong? Can you find any examples of that? Business marketing classes love to point out an example of a car name in Mexico that meant something very different in Spanish than it did in English - however [Snopes has shown that is a false tale](https://www.snopes.com/fact-check/chevrolet-nova-name-spanish/).  As a developer, what are some things you can do to reduce 'hick ups' when expanding your program to other languages?


As a reminder, deeper thinking questions are meant to require some research and to be answered in a paragraph for with references. The goal is to open up some of the discussion topics in CS, so you are better informed going into industry. 

There are many reasons for a program to be flexible enough to localize to different languages and cultures. Aside from market share/economic influence, growth potential and internet penetration are other reasons to localize a program to a certain language. Targeting languages in growing economies through early investment can offer potential long term benefits for a company. Tailoring your program for areas with high online presence also helps you reach a wider audience [1]. 

While the top 10 languages for localization cover many of the reasons to localize a program, it's not always as simple as just translating from one language to another. One thing to consider when web translation is the technical and UI experience. Words in some languages like German or French can take up to 50% more space than the English version [2]. This can distrupt the user experience and the overall layout of a web page. Cultural fit and awareness also play a role when considering localization. It's important to take a balanced approach between the original text and it's cultural relivance. Coca-Cola is a great example of successful cultural translation. One of their most impactful marketing campaigns included creating a Chineese slogan, "Delicious Happiness", which captured the brand voice and appealed to the Chineese cultural values [3].


[1] Filip Smet, "Most Popular Languages for Translation and Localization," Global Link. Available: https://globallink.transperfect.com/blog/top-10-languages-for-website-translation-and-localization. [Accessed: 20 July. 2026]

[2] Matt Owen, "10 Common Software Localization Mistakes," Phrase. Available: https://phrase.com/blog/posts/10-common-mistakes-in-software-localization/. [Accessed: 20 July. 2026]

[3] mvptranslations, "The Role of Culture in Language and Translation," MVP Translations inc. Available: https://mvptranslations.net/the-role-of-culture-in-language-translation/. [Accessed: 20 July. 2026]