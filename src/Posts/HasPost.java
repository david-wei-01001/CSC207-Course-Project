package Posts;

public interface HasPost {
    class PostNotFoundException extends Exception {
        public PostNotFoundException (){
            super();
        }
    }

   PostNotFoundException ABSENT = new PostNotFoundException ();
    void add(PublishedContents content);
    void delete(String id) throws  PostNotFoundException;
}
