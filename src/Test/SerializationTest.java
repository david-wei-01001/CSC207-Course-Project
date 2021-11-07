package Test;

import Graph.DirectedGraph;
import GraphBuilders.GraphArchitect;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationTest {


    public static void main(String[] args) throws Exception {

        GraphArchitect graphArchitect = new GraphArchitect();
        DirectedGraph csIntroductorySeriesTreeToSave = graphArchitect.setBuilderAndBuildGraph("Introductory CS Series");

        /**
         * creating the file to save the particular instance of DirectedGraph to, and creating the
         * ObjectOutputStream object that saves it.
         */
        String fileName = "Serialization.bin";
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
        os.writeObject(csIntroductorySeriesTreeToSave);
        os.close();

        /**
         * reading the instance of DirectedGraph
         */
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
        DirectedGraph csIntroductorySeriesTreeToRead = (DirectedGraph) is.readObject(); //read object
        System.out.println(csIntroductorySeriesTreeToRead.getVertices());
        is.close();


    }
}
