package jsonreadwriter;

import communitysystem.CommunityList;

import java.io.*;

/**
 * A class responsible for reading and writing communities for json file
 */
public class CommunityReadWriter implements ReadWriter {

    /**
     * Writes the community to file at filePath.
     *
     * @param filePath      the file to write the records to
     * @param communityList stores the list of communities to be serialized
     * @throws IOException the exception is thrown
     */
    @Override
    public void saveToFile(String filePath, Object communityList) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(communityList);
        output.close();
    }


    /**
     * Store the users to file at filePath.
     *
     * @param filePath file where the user list is stored
     * @return list of communities
     * @throws IOException the exception is thrown
     */
    @Override
    public CommunityList readFromFile(String filePath) throws IOException, ClassNotFoundException {

        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        CommunityList communityList = (CommunityList) input.readObject();
        input.close();
        return communityList;
    }
}
