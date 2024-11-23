package net.laserdiamond.burningminesofbelow.util.file;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;

import java.io.*;

/**
 * Json config file class for storing information about assets of this mod
 */
public abstract class JsonConfig {

    protected final String fileName;
    protected final File file;
    protected JsonObject jsonObject;

    /**
     * Creates a new Json config file
     * @param fileName The name of the file
     */
    protected JsonConfig(String fileName) {
        this.fileName = fileName;
        this.file = new File(modFilePath() + fileName + ".json");

        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) // Try-with-resources to close BufferedReader regardless of exceptions being thrown
        {
            this.jsonObject = new Gson().fromJson(br, JsonObject.class); // Get existing json object from file
            if (this.jsonObject == null)
            {
                this.jsonObject = new JsonObject(); // Json object doesn't exist. Create new one
            }
        } catch (IOException e)
        {
            BurningMinesOfBelow.LOGGER.info("ERROR CREATING JSON OBJECT FOR FILE " + fileName);
            e.printStackTrace();
        }
    }

    /**
     * The mod's file directory for storing files
     * @return The folder path for the mod's files
     */
    private String modFilePath()
    {
        return BurningMinesOfBelow.MODID + File.separator + folderName() + File.separator;
    }

    /**
     * The folder name for the file directory
     * @return The folder name for the file
     */
    public abstract String folderName();

    /**
     * Creates the json config file
     * @return True only if a new file was created. Returns false if the file already exists or if the file couldn't be created
     */
    public final boolean createFile()
    {
        if (this.file.exists())
        {
            return false; // File exists. Don't replace
        }
        this.file.getParentFile().mkdirs();

        try (FileWriter fileWriter = new FileWriter(this.file)) // Try-with-resources to close FileWriter regardless of exceptions being thrown
        {
            if (this.file.createNewFile())
            {
                BurningMinesOfBelow.LOGGER.info("Created File: " + this.fileName); // File was created
                fileWriter.write(this.jsonObject.toString()); // Write the json object to the file
                return true;
            } else
            {
                BurningMinesOfBelow.LOGGER.info("Couldn't create file: " + this.fileName); // File was not created
                return false;
            }
        } catch (IOException e)
        {
            BurningMinesOfBelow.LOGGER.info("ERROR CREATING FILE: " + this.fileName + "!"); // Exception creating file
            e.printStackTrace();
        }
        return false;
    }

}
