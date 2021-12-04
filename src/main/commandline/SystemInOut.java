package commandline;

import achievementsystem.AchievementManager;
import constants.Achievements;
import graph.GraphManager;
import communitysystem.CommunityLibrary;
import graph.Vertex;
import jsonreadwriter.WholeReadWriter;
import resource.ResourceManager;
import rewardsystem.RewardManager;
import user.UserManager;
import constants.Exceptions;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class SystemInOut {
    private UserManager userManager;
    private GraphManager graphManager;
    private RewardManager rewardManager;
    private AchievementManager achievementManager;
    private CommunityLibrary communityLibrary;
    private ResourceManager resourceManager;
    private Scanner scanner = new Scanner(System.in);
    private Presenter presenter;

    public SystemInOut() throws Exception {
        graphManager = new GraphManager();
        userManager = new UserManager();
        userManager.addNewUser("alfred", "@", "123");
        resourceManager = new ResourceManager();
        resourceManager.addDefault();
        rewardManager = new RewardManager();
        achievementManager = new AchievementManager();
        graphManager.addBuiltInGrpah();
        presenter = new Presenter(userManager, resourceManager, graphManager);
    }

    public void run() {
        // load();
        logIn();
        mainMenu();
        scanner.close();
    }

    public void mainMenu() {
            presenter.mainMenuOptions();
            String input = scanner.nextLine();

            while (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("exit"))) {
                presenter.incorrectInput();
                input = scanner.nextLine();
            }

            switch (input) {
                case "1":
                    try {
                        technicalTreeMainPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    resourcePage();
                    break;
                case "3":
                    achievementPage();
                    break;
                case "exit":
                    exitProgram();
                    break;
            }
    }

    private void achievementPage() {
        presenter.achievementPage();
        presenter.mainMenuReturn();
        String input = scanner.nextLine();
        mainMenu();
    }

    private void resourcePage() {
        presenter.rewardPoints();
        presenter.resourcePage();
        String input = scanner.nextLine();
        while (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("0"))) {
            presenter.incorrectInput();
            input = scanner.nextLine();
        }

        switch (input) {
            case "1":
                myResource();
                break;
            case "2":
                downloadResources();
                break;
            case "3":
                createResource();
                break;
            case "0":
                mainMenu();
        }
    }



    private void myResource() {
        presenter.currentResource();
        presenter.mainMenuReturn();
        String input = scanner.nextLine();
        mainMenu();
        }

    private void downloadResources() {
        presenter.resourceChoose();
        String content = scanner.nextLine();
        while(resourceManager.downloadResource(content).equals("Sorry, you do not have enough points")){
            presenter.insufficientPoints();
            presenter.resourceChoose();
            content = scanner.nextLine();
        }
        while(!resourceManager.downloadResource(content).equals("Sorry, you do not have enough points")){
            presenter.downloadSuccessfully();
            resourceManager.downloadResource(content);
            presenter.mainMenuReturn();
            String input = scanner.nextLine();
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
        String input = scanner.nextLine();
        mainMenu();
    }


    private void technicalTreeMainPage() throws Exception {
        presenter.technicalTreeMainPage();
        String input = scanner.nextLine();

        while (!graphManager.getAllGraphs().containsKey(input) && !input.equals("main")) {
            presenter.incorrectInput();
            input = scanner.nextLine();
        }

        if (input.equals("main")){
            mainMenu();
        }

        else {
            technicalTreePage(input);
        }

    }

    private void technicalTreePage(String treeId) throws Exception {
        graphManager.setCurrentGraph(treeId);
        presenter.technicalTreeDisplayCurrentGraph();

        presenter.technicalTreePage();
        String input = scanner.nextLine();
        while (!graphManager.getCurrentGraph().availableVertex().containsKey(input) && !input.equals("main")){
            presenter.incorrectInput();
            input = scanner.nextLine();
        }

        if (input.equals("main")){
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
        while (!input.equals("Yes")){
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
        communityLibrary.createPost(publishedContent, achievementManager, rewardManager);
        boolean achievementAwarded = achievementManager.requestAchievement(
                Achievements.ARRAY_OF_POST_THRESHOLDS,
                Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT,
                userManager.getListOfPostId().size());
        if (achievementAwarded) {
            rewardManager.addRewardPoint(
                    Achievements.MAP_POST_THRESHOLDS_TO_REWARD.get(userManager.getListOfPostId().size()));
        }
        presenter.publishPostSuccessful();
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
                    userManager.incrementTotalLogins();
                }
                break;
            case Presenter.TWO:
                if (!register()) {
                    logIn();
                } else {
                    userManager.incrementTotalLogins();
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
     * @param username
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
        while (!email.contains(Presenter.AT)) {
            presenter.incorrectEmail();
            email = scanner.nextLine();
            if (email.equals(Presenter.RETURN)) {
                return email;
            }
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
//        try {
//            save();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.exit(0);
    }

    private void save() throws IOException {
        WholeReadWriter.saveToFile("user.json",
                "community.json",
                userManager.getMapOfUser(),
                communityLibrary.getMapOfCommunity());
    }

    private void load() {
        try {
            WholeReadWriter.readFromFile("user.json",
                    "community.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
