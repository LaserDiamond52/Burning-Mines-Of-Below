package net.laserdiamond.burningminesofbelow.util.file;

import com.google.gson.JsonObject;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;

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
        this.file = new File(modFilePath() + fileName + ".json");
        this.jsonObject = new JsonObject();
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

            fileWriter.write(this.jsonObject.toString()); // Write the json object to the file
            if (this.file.createNewFile())
            {
                BurningMinesOfBelow.LOGGER.info("Created File: " + this.fileName); // File was created
                return true;
            } else
            {
                BurningMinesOfBelow.LOGGER.info("Couldn't create file: " + this.fileName); // File was not created
                return false;
            }
        } catch (IOException e)
        {
            BurningMinesOfBelow.LOGGER.info("ERROR TO CREATE FILE: " + this.fileName + "!"); // Exception creating file
            e.printStackTrace();
        }
        return false;
    }

}
