package interfaceadapter;

import achievementsystem.AchievementManager;
import communitysystem.CommunityLibrary;
import communitysystem.CommunityList;
import constants.Achievements;
import constants.TreeIdMap;
import graph.DirectedGraph;
import graph.GraphManager;
import jsonreadwriter.WholeReadWriter;
import resource.ResourceManager;
import rewardsystem.RewardManager;
import user.UserList;
import user.UserManager;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: JavaDoc
 */
public class SystemInOut {
    private final UserManager userManager;
    private final GraphManager graphManager;
    private final RewardManager rewardManager;
    private final AchievementManager achievementManager;
    private final CommunityLibrary communityLibrary;
    private final ResourceManager resourceManager;
    private final Scanner scanner = new Scanner(System.in);
    private final Presenter presenter;
    private final TreeIdMap idMap;

    public SystemInOut() throws Exception {
        graphManager = new GraphManager();
        userManager = new UserManager();
        userManager.addNewUser("alfred", "@", "123");
        resourceManager = new ResourceManager();
        resourceManager.addDefault();
        rewardManager = new RewardManager();
        achievementManager = new AchievementManager();
        communityLibrary = new CommunityLibrary();
        graphManager.addBuiltInGraph(communityLibrary);
        presenter = new Presenter(userManager, resourceManager, graphManager, communityLibrary);
        idMap = graphManager.getIdMap();
    }

    public void run() {
        load();
        logIn();
        mainMenu();
        scanner.close();
    }

    public void mainMenu() {
            presenter.mainMenuOptions();
            String input = scanner.nextLine();

            while (!(input.equals(Presenter.ONE) || input.equals(Presenter.TWO) || input.equals(Presenter.THREE) ||
                    input.equals(Presenter.ZERO) || input.equals(Presenter.EXIT))) {
                presenter.incorrectInput();
                input = scanner.nextLine();
            }

            switch (input) {
                case Presenter.ZERO:
                    myTreeMainPage();
                case Presenter.ONE:
                    try {
                        technicalTreeMainPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Presenter.TWO:
                    resourcePage();
                    break;
                case Presenter.THREE:
                    achievementPage();
                    break;
                case Presenter.EXIT:
                    exitProgram();
                    break;
            }
    }

    private void myTreeMainPage(){
        if(userManager.getCurrentUser().getMapOfGraph().size() == 0){
            presenter.myTreePageEmpty();
            presenter.mainMenuReturn();
            scanner.nextLine();
            mainMenu();
        }
        else{
            presenter.myTreePage();

            String input = scanner.nextLine();
            while (!userManager.getCurrentUser().getMapOfGraph().containsKey(idMap.useIdToGetName(input))
                    && !input.equals("main")) {
                presenter.incorrectInput();
                input = scanner.nextLine();
            }

            if (input.equals("main")){
                mainMenu();
            }
            else{
                try {
                    myTreePage(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void myTreePage(String treeId) throws Exception {
        technicalTreePage(treeId);
    }
    private void achievementPage() {
        presenter.achievementPage();
        presenter.mainMenuReturn();
        scanner.nextLine();
        mainMenu();
    }

    private void resourcePage() {
        presenter.rewardPoints();
        presenter.resourcePage();
        String input = scanner.nextLine();
        while (!(input.equals(Presenter.ONE) || input.equals(Presenter.TWO) || input.equals(Presenter.THREE)
                || input.equals(Presenter.ZERO))) {
            presenter.incorrectInput();
            input = scanner.nextLine();
        }

        switch (input) {
            case Presenter.ONE:
                myResource();
                break;
            case Presenter.TWO:
                downloadResources();
                break;
            case Presenter.THREE:
                createResource();
                break;
            case Presenter.ZERO:
                mainMenu();
        }
    }



    private void myResource() {
        presenter.currentResource();
        presenter.mainMenuReturn();
        scanner.nextLine();
        mainMenu();
        }

    private void downloadResources() {
        presenter.resourceChoose();
        String content = scanner.nextLine();
        while(resourceManager.downloadResource(content).equals(Presenter.INSUFFICIENT_POINTS)){
            presenter.insufficientPoints();
            presenter.resourceChoose();
            content = scanner.nextLine();
        }
        while(!resourceManager.downloadResource(content).equals(Presenter.INSUFFICIENT_POINTS)){
            presenter.downloadSuccessfully();
            resourceManager.downloadResource(content);
            presenter.mainMenuReturn();
            scanner.nextLine();
            mainMenu();
        }
    }

    private void createResource() {
        Scanner scanner = new Scanner(System.in);
        presenter.resourceDescription();
        String description = scanner.nextLine();
        presenter.resourcePoints();
        String point = scanner.nextLine();
        presenter.resourceContents();
        String content = scanner.nextLine();
        resourceManager.addResource(content, Integer.parseInt(point), description);
        presenter.resourceCreateSuccessfully();
        presenter.mainMenuReturn();
        scanner.nextLine();
        mainMenu();
    }


    private void technicalTreeMainPage() throws Exception {
        presenter.technicalTreeMainPage();
        String input = scanner.nextLine();

        while (!graphManager.getAllGraphs().containsKey(input) && !input.equals(Presenter.MAIN)) {
            presenter.incorrectInput();
            input = scanner.nextLine();
        }

        if (input.equals(Presenter.MAIN)){
            mainMenu();
        }

        else {
            DirectedGraph graph = graphManager.getAllGraphs().get(input);
            userManager.getCurrentUser().addGraph(graph);
            technicalTreePage(input);
        }

    }

    private void technicalTreePage(String treeId) throws Exception {

        DirectedGraph currGraph= null;
        if(treeId.equals("0")){
            currGraph = userManager.getCurrentUser().getMapOfGraph().get("Introductory CS Series");
        }
        else if(treeId.equals("1")){
            currGraph = userManager.getCurrentUser().getMapOfGraph().get("Introductory Makeup Steps");
        }


        if (currGraph != null){
            graphManager.updateWithPrivateGraph(currGraph);
        }


        graphManager.setCurrentGraph(treeId);

        presenter.technicalTreeDisplayCurrentGraph();

        presenter.technicalTreePage();
        String input = scanner.nextLine();
        while (!graphManager.getCurrentGraph().availableVertex().containsKey(input) && !input.equals(Presenter.MAIN)){
            presenter.incorrectInput();
            input = scanner.nextLine();
        }

        if (input.equals(Presenter.MAIN)){
            mainMenu();
        }
        else {
            String vertexName = graphManager.getCurrentGraph().availableVertex().get(input).toString();

            studyVertex(vertexName, treeId);
        }

    }

    private void studyVertex(String vertexName, String treeId) throws Exception {

        presenter.studyVertex();
        String input = scanner.nextLine();
        while (!input.equals(Presenter.YES)){
            presenter.studyVertexNotFinished();
            input = scanner.nextLine();
        }

        graphManager.setCurrentGraph(treeId);

        graphManager.complete(vertexName); // Marking the given vertex as completed


        presenter.studyVertexFinished();
        userManager.getCurrentUser().addRewardPoints(5);
        presenter.enterPublishContent();
        String publishedContent = scanner.nextLine();

        communityLibrary.setCurrentCommunity(vertexName);
        communityLibrary.createPost(publishedContent, rewardManager);
        boolean achievementAwarded = achievementManager.requestAchievement(
                Achievements.ARRAY_OF_POST_THRESHOLDS,
                Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT,
                userManager.getListOfPostId().size());
        if (achievementAwarded) {
            rewardManager.addRewardPoint(
                    Achievements.MAP_POST_THRESHOLDS_TO_REWARD.get(userManager.getListOfPostId().size()));
        }
        presenter.publishPostSuccessful();
        presenter.displayVertexCommunity(vertexName);
        presenter.nodeCompleted();
        scanner.nextLine(); // Let the user enter anything they want here to proceed

        technicalTreePage(treeId);

    }


    public void logIn() {
        String input = presenter.LoginOptions();

        input = presenter.getCorrectLoginOption(input);

        switch (input) {
            case Presenter.ONE:
                if (!signIn()) {
                    logIn();
                } else {
                    userManager.incrementTotalLogins(rewardManager, achievementManager);

                }
                break;
            case Presenter.TWO:
                if (!register()) {
                    logIn();
                } else {
                    userManager.incrementTotalLogins(rewardManager, achievementManager);
                }
                break;
            case Presenter.EXIT:
                exitProgram();
                break;
        }
    }

    public boolean signIn() {
        String username = getUsernameSignIn();
        if (username.equals(Presenter.RETURN)) {
            return false;
        }
        if (!enterPassword(username)) {
            return false;
        }
        setCurrentUser(username);
        return true;
    }

    /**
     * fully implement this.
     * @param username the username of the user
     */
    public void setCurrentUser(String username) {
        try {
            userManager.setCurrentUser(username);
            achievementManager.setCurrentUser(userManager.getCurrentUser());
            rewardManager.setCurrentUser(userManager.getCurrentUser());
            communityLibrary.setCurrentUser(userManager.getCurrentUser());
            resourceManager.setCurrentUser(userManager.getCurrentUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean enterPassword(String username) {
        String password = presenter.getNonEmptyPassword();
        while (!getCorrectPassword(username).equals(password)) {
            presenter.incorrectPassword();
            password = presenter.getNonEmptyPassword();
            if (password.equals(Presenter.RETURN)) {
                return false;
            }
        }
        return true;
    }

    public String getCorrectPassword(String username) {
        String password = null;
        try {
            password = userManager.getAUser(username).getPassword();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    private String getUsernameSignIn() {
        String username = presenter.getNonEmptyUsername();
        while (!userManager.containsUsername(username)) {
            presenter.incorrectUsername();
            username = presenter.getNonEmptyUsername();
            if (username.equals(Presenter.RETURN)) {
                return username;
            }
        }
        return username;
    }

    public String getUsernameRegister() {
        String username = presenter.getNonEmptyUsername();
        while (userManager.containsUsername(username)) {
            presenter.usernameTaken();
            username = presenter.getNonEmptyUsername();
            if (username.equals(Presenter.RETURN)) {
                return username;
            }
        }
        return username;
    }

    public String getEmailRegister() {
        presenter.getEmail();
        String email = scanner.nextLine();
        Pattern p = Pattern.compile("^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$");
        Matcher m = p.matcher(email);
        while (!m.matches()) {
            if (email.equals(Presenter.RETURN)) {
                return email;
            }
            presenter.incorrectEmail();
            email = scanner.nextLine();
            m = p.matcher(email);
        }
        return email;
    }

    public String getPasswordRegister() {
        return presenter.getNonEmptyPassword();
    }


    private boolean register() {
        String username = getUsernameRegister();
        if (username.equals(Presenter.RETURN)) {
            return false;
        }
        String email = getEmailRegister();
        if (email.equals(Presenter.RETURN)) {
            return false;
        }
        String password = getPasswordRegister();
        try {
            userManager.addNewUser(username, email, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCurrentUser(username);
        return true;
    }




    public void exitProgram() {
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * Check for the learned tree and update them into user's info storage.
     */
    public void checkForMyTree(){
        for(DirectedGraph graph: graphManager.getAllGraphs().values()){
            if(graph.isLearnedGraph()){
                user.User currUser=userManager.getCurrentUser();
                currUser.addGraph(graph);
            }
        }
    }

    private void save() throws IOException {
        checkForMyTree();
        WholeReadWriter.saveToFile("src/main/interfaceadapter/user.json",
                "src/main/interfaceadapter/community.json",
                userManager.getMapOfUser(),
                communityLibrary.getMapOfCommunity());
    }

    private void load() {
        List<Object> data;
        try {
            data = WholeReadWriter.readFromFile("src/main/interfaceadapter/user.json",
                    "src/main/interfaceadapter/community.json");
            userManager.setMapOfUser((UserList) data.get(0));
            communityLibrary.setMapOfCommunity((CommunityList) data.get(1));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
