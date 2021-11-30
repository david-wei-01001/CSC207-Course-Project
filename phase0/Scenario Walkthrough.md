Phase 0 Scenario: Tech Tree

Note: 

  - “Tech Tree” mentioned in this project is implemented using graphs, so in the context of this project, the keywords, “Tech Tree” and “main.graph”, are interchangeable, so are “node/nodes” and “vertex/vertices”.
  
  - The content in the [] is what happens backstage.

A main.user registers his/her account and completes the first vertex.

  1. main.user first proceed to the registration process:
    
    - create an account by entering username, email and password

      - [the program stores this main.user information in userManager]

  2. Pick the first Tech Tree:
  
    - Select the built-in CS Tech Tree, “CS Introductory Series” by entering its id

      - [technicalGraphMaganer reads the id and presents the Tech Tree, and adds the technicalGraph, “CS Introductory Series”,  to main.user using addListOfGraph]

  3. Choose an available node to enter:

    - Select the node, “Introductory Python” by entering its id.

     - [program select the corresponding vertex with the id from availableVertex]

  4. main.user starts studying Python. When the main.user finishes studying, enter “yes” to proceed.

     - [checked by main’s completeVertex()]

  5. Program prompts the main.user to create achievementsystem post to share his/her study evidence in the node’s community:
  
    - main.user enters the content of the post
      - [Program create an instance of Post with the corresponding content]
      - [Then, add the post to the Community’s listOfPosts using addPost()]

  6. Program unlocks new nodes for the main.user:]

    - New available nodes are “C++”, “main.technicaltree” and “CSC165”.
    - [by completing the previous vertex in the main.graph using the complete() method]
