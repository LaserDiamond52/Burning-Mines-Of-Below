package net.laserdiamond.burningminesofbelow.util.file;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Json config file class for storing information about assets of this mod
 */
public abstract class JsonConfig {

    protected final String fileName;
    protected final File file;
    protected final JsonObject jsonObject;

    /**
     * Creates a new Json config file
     * @param fileName The name of the file
     */
    protected JsonConfig(String fileName) {
        this.fileName = fileName;
        this.file = new File(filePath() + File.separator + fileName + ".json");
        this.jsonObject = new JsonObject();
    }

    /**
     * The folder path for the file
     * @return The folder path for the file
     */
    public abstract String filePath();

    /**
     * Creates the json config file
     * @return True only if a new file was created. Returns false if the file already exists or if the file couldn't be created
     */
    public boolean createFile()
    {
        if (this.file.exists())
        {
            return false; // File exists. Don't replace
        }
        this.file.getParentFile().mkdirs();
        try
        {
            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.write(this.jsonObject.toString());
            fileWriter.close();
            if (this.file.createNewFile())
            {
                System.out.println("Created File: " + this.fileName);
                return true;
            } else
            {
                System.out.println("Couldn't create file: " + this.fileName);
                return false;
            }
        } catch (IOException e)
        {
            System.out.println("ERROR TO CREATE FILE: " + this.fileName + "!");
            e.printStackTrace();
        }
        return false;
    }

}
