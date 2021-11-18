package Posts;

import User.User;

public interface HasPublishedContents {

    class PostNotFoundException extends Exception {
        public PostNotFoundException () {
            super();
        }
    }

   PostNotFoundException ABSENT = new PostNotFoundException ();

    String addPublishedContent(String content, User creator);

    void deletePublishedContent(String id) throws  PostNotFoundException;
}
