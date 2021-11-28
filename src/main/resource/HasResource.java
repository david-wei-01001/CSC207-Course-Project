package resource;


public interface HasResource {
    /**
     * Create a specific exception for PostNotFoundException
     */
    class PostNotFoundException extends Exception {
        public PostNotFoundException () {
            super();
        }
    }

    /**
     * Name the exception by ABSENT
     */
    HasResource.PostNotFoundException ABSENT = new HasResource.PostNotFoundException();


    /**
     * Add the intended content to the map which the content belongs to
     */
    void addResource(String content, int point, String description);

    /**
     * Delete the intended content from the map which the content belongs to, throw an
     * exception if the post does not exist in the map
     */
    void deleteResource(String id) throws HasResource.PostNotFoundException;

}
