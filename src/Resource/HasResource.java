package Resource;


public interface HasResource {
    class PostNotFoundException extends Exception {
        public PostNotFoundException () {
            super();
        }
    }

    HasResource.PostNotFoundException ABSENT = new HasResource.PostNotFoundException();
    void add(String content,int point, String description);
    void delete(String id) throws HasResource.PostNotFoundException;
}
