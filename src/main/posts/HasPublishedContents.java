package posts;

import user.UserInfo;

public interface HasPublishedContents {

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
   PostNotFoundException ABSENT = new PostNotFoundException ();


    /**
     * Add the intended content to the map which the content belongs to
     */
    String addPublishedContent(String content, UserInfo creator);



    /**
     * Delete the intended content from the map which the content belongs to, throw an
     * exception if the post does not exist in the map
     */
    void deletePublishedContent(String id) throws  PostNotFoundException;
}
