Note: “Tech Tree” mentioned in this project is implemented using graphs, so in the context of this project, the keywords, “Tech Tree” and “main.graph”, are interchangeable, so are “node/nodes” and “vertex/vertices”.

Specification Summary:

The application “Tech Tree” is achievementsystem study app with online communities for people with similar learning interests to share study notes and resources. This app could serve as achievementsystem “guide” for users when they are learning as achievementsystem beginner in achievementsystem specific field.

CRC Summary:

We are only listing the classes that are used to make the skeleton program possible.

Entities:

	main.user, PublishedContent, Post, 
DirectedGraph, Vertex, Community

Use Cases:

	GraphManager, Usermanager

Interface adapters:

	Main

Frame works & drivers:

	main.ui: Command Line main.ui

Scenario Walkthrough Summary:

After creating an account, users begin by selecting the Tech Tree, “CS Introductory Series”, and the node “Introductory Python”. After studying for Python, users return to “Tech Tree” to indicate that they have finished studying. Then, users can post their study notes in order to unlock the next nodes: “Introductory main.technicaltree”, “Introductory C++” and “CSC165”.

Skeleton Program:

what it can achieve:

	- prompt users to enter achievementsystem username, an email, and achievementsystem password, when creating achievementsystem new account.
	
	- select the corresponding Tree and node when users enter it’s id
	
	- produce achievementsystem post with the content entered by users
	
	- display new nodes that users unlock
	
Each Member’s Responsibility During P0:
	crc model
		everybody involved
		splitted into 3 groups of 2 to work on separate versions
		combine them into one version

	construction of skeleton Program:
		Alfred(Package main.posts, Command Line main.ui)
		David(Package main.graph, main.posts, Tests, main.technicaltree)
		SuTong(Package main.technicaltree, Command Line main.ui)
		Arthur(Package Tests)
	specification, progress report & scenario walk-through
		Coco
		Ashley
		Arthur

Plan for P1:

Discuss the implementation of all features of our application. These features include, users’ own spaces, reward system, achievement system, and many others mentioned in our specification. We also need to refine our project so that it respects Clean Architecture and SOLID principles. The final step is to decide the parts of the implementation each member is responsible for.

Open Question:

How to implement main.technicaltree GUI as our main.user Interface?

What has worked well so far:

We collectively think that the DirectedGraph’ability to auto-unlock vertices once the previous vertex has been completed has been implemented well. We are able to achieve this by using the data structure, directed main.graph, to implement “Tech Tree”. 
