package Posts;

public interface HasPost {
    class PostNotFoundException extends Exception {
        public PostNotFoundException () {
            super();
        }
    }

   PostNotFoundException ABSENT = new PostNotFoundException ();
    void add(String content);
    void delete(String id) throws  PostNotFoundException;
}
