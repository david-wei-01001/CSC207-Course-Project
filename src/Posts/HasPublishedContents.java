package Posts;

import User.UserInfo;

public interface HasPublishedContents {

    class PostNotFoundException extends Exception {
        public PostNotFoundException () {
            super();
        }
    }

   PostNotFoundException ABSENT = new PostNotFoundException ();

    String addPublishedContent(String content, UserInfo creator);

    void deletePublishedContent(String id) throws  PostNotFoundException;
}
