package resource;

/**
 * An interface specifying properties all objects which stores Resources need to have
 */
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
     *  Add the intended resource to the map which the content belongs to
     *
     * @param content: The content that the resource contains, normally a link
     * @param point: The point which the resource contains
     * @param description: The brief description for the resource
     */
    void addResource(String content, int point, String description);

    /**
     * Delete the intended content from the map which the content belongs to, throw an
     * exception if the post does not exist in the map
     *
     * @param id The id of the main. resource to be deleted
     * @throws PostNotFoundException the exception is thrown
     */
    void deleteResource(String id) throws HasResource.PostNotFoundException;

}
