package posts;

import user.User;

/**
 * An interface specifying properties all objects which stores PublishedContents need to have
 */
public interface HasPublishedContents {

    /**
     * Create a specific exception for PostNotFoundException
     */
    class PostNotFoundException extends Exception {
        public PostNotFoundException() {
            super();
        }
    }

    /**
     * Name the exception by ABSENT
     */
    PostNotFoundException ABSENT = new PostNotFoundException();

    /**
     * Add the intended content to the map which the content belongs to
     *
     * @param content the content of the PublishedContent being added
     * @param creator the creator of the PublishedContent being added
     * @return the id of the PublishedContent added.
     */
    String addPublishedContent(String content, User creator);

    /**
     * Delete the intended content from the map which the content belongs to, throw an
     * exception if the post does not exist in the map
     *
     * @param id the id of the PublishedContent being deleted
     * @throws PostNotFoundException if no PublishedContent with the inputted id was found.
     */
    void deletePublishedContent(String id) throws PostNotFoundException;
}
