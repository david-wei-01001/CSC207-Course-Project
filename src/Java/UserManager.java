package Java;


import Graph.DirectedGraph;
import contants.Exceptions;

import java.util.HashMap;
import java.util.Map;

/** TODO: add javadoc
 *
 */

public class UserManager {

    /** TODO: add explanation for the DS of listOfUsers
     * TODO: refactor setCurrentUser and removeUser later.
     *
     */
    private User currentUser;
//    private ArrayList<User> listOfUsers = new ArrayList<>();
    private Map<String, User> libraryOfUsers = new HashMap<>();




    public void addNewUser(String name, String email, String password) throws Exception {
        if (!libraryOfUsers.containsKey(name)) {
            libraryOfUsers.put(name, new User(name, email, password));
        } else {
            throw new Exception(Exceptions.USER_ALREADY_EXISTS);
        }

    }



    public void setCurrentUserTo(String name) throws Exception {
        if (libraryOfUsers.containsKey(name)) {
            this.currentUser = libraryOfUsers.get(name);
        } else {
            throw new Exception(Exceptions.CANNOT_RECOGNIZE_USER);
        }
    }



    public void removeUser(String name) throws Exception {
        if (libraryOfUsers.containsKey(name)) {
            this.currentUser = libraryOfUsers.remove(name);
        } else {
            throw new Exception(Exceptions.CANNOT_RECOGNIZE_USER);
        }
    }


    /**
     * add a graph to current user
     */
    public void addGraphToCurrentUser(DirectedGraph graph) {
        currentUser.addGraph(graph);
    }



    public void setNameOfCurrentUser(String newName) {
        currentUser.setName(newName);
    }


    public void setEmailOfCurrentUser(String newEmail) {
        currentUser.setEmail(newEmail);
    }

    public void setPasswordOfCurrentUser(String newPassword) {
        currentUser.setPassword(newPassword);
    }

    public Map<String, User> getLibraryOfUsers() {
        return libraryOfUsers;
    }



    public User getCurrentUser() {
        return currentUser;
    }
}
