package Resource;


import User.UserInfo;

public interface HasResource {
    class PostNotFoundException extends Exception {
        public PostNotFoundException () {
            super();
        }
    }

    HasResource.PostNotFoundException ABSENT = new HasResource.PostNotFoundException();
    void addResource(String content, int point, String description);
    void deleteResource(String id) throws HasResource.PostNotFoundException;
}
