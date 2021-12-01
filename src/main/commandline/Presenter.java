package commandline;

import resource.ResourceManager;
import user.UserManager;

public class Presenter {
    private UserManager userManager;
    private ResourceManager resourceManager;

    public Presenter(UserManager userManager, ResourceManager resourceManager) {
        this.userManager = userManager;
        this.resourceManager = resourceManager;
    }
    public String mainMenuReturn(){
        return "Enter anything to return to Main Menu.";
    }

    public String rewardPoints(){
        return "Your reward point: " + userManager.getCurrentUserInfo().getRewardPoints();
    }

    public String resourcePage(){
        return "Resource: 1.My Resource, 2.Download Resource, 3.Create Resource, " +
                "0. Main Menu";
    }

    public String incorrectInput(){
        return "Incorrect input, please try again.";
    }

    public String currentResource(){
        return "Your resource: " + userManager.getCurrentUserInfo().getMapOfResource();
    }

    public String resourceChoose(){
        return "Please choose the resource you want to download:" +
                this.resourceManager.getMapOfResource();
    }

    public String insufficientPoints(){
        return "Sorry, you do not have enough points";
    }

    public String downloadSuccessfully(){
        return "You have successfully download this resource";
    }

    public String resourceDescription(){
        return "Please write the description of the resource you want to create";
    }

    public String resourcePoints(){
        return "Please write the required points of the resource you want to create";
    }

    public String resourceContents(){
        return "Please write the content of the resource you want to create";
    }

    public String resourceCreateSuccessfully(){
        return "You have successfully created this resource";
    }


}
