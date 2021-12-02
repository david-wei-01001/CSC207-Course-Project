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
    public void mainMenuReturn(){
        System.out.println("Enter anything to return to Main Menu.");
    }

    public void rewardPoints(){
        System.out.println("Your reward point: " + userManager.getCurrentUserInfo().getRewardPoints());
    }

    public void resourcePage(){
        System.out.println("Resource: 1.My Resource, 2.Download Resource, 3.Create Resource, " +
                "0. Main Menu");
    }

    public void incorrectInput(){
        System.out.println( "Incorrect input, please try again.");
    }

    public void currentResource(){
        System.out.println( "Your resource: " + userManager.getCurrentUserInfo().getMapOfResource());
    }

    public void resourceChoose(){
        System.out.println( "Please choose the resource you want to download:" +
                this.resourceManager.getMapOfResource());
    }

    public void insufficientPoints(){
        System.out.println( "Sorry, you do not have enough points");
    }

    public void downloadSuccessfully(){
        System.out.println( "You have successfully download this resource");
    }

    public void resourceDescription(){
        System.out.println("Please write the description of the resource you want to create");
    }

    public void resourcePoints(){
        System.out.println("Please write the required points of the resource you want to create");
    }

    public void resourceContents(){
        System.out.println("Please write the content of the resource you want to create");
    }

    public void resourceCreateSuccessfully(){
        System.out.println( "You have successfully created this resource");
    }


}
