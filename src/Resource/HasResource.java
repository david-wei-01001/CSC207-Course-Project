package Resource;


import User.User;

public interface HasResource {
    class PostNotFoundException extends Exception {
        public PostNotFoundException () {
            super();
        }
    }

    HasResource.PostNotFoundException ABSENT = new HasResource.PostNotFoundException();
    void add(String content,int point, String description, User creator);
    void delete(String id) throws HasResource.PostNotFoundException;
}
