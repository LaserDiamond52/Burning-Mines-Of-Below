package net.laserdiamond.burningminesofbelow.util.file;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;

import java.io.*;

/**
 * Json config file class for storing information about assets of this mod
 */
public abstract class JsonConfig {

    /**
     * The name of the file
     */
    protected final String fileName;

    /**
     * The file that will be written to
     */
    protected final File file;

    /**
     * The {@link JsonObject} that will be written to and read from in the file
     */
    protected JsonObject jsonObject;

    /**
     * Creates a new Json config file
     * @param fileName The name of the file
     */
    public JsonConfig(String fileName) {
        this.fileName = fileName;
        this.file = new File(modFilePath() + fileName + ".json");

        this.createFile(); // Create the file

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

    /**
     * Writes the json object to the file. This should be called at the end of each subclass constructor
     * @return True if the object was successfully written to the file. Returns false if an {@link IOException} was thrown
     */
    public boolean writeJsonToFile()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))) // Use try-with-resources to make sure Buffered Writer closes
        {
            bw.write(this.jsonObject.toString()); // Write the json object to the file
            return true; // Success! Return true
        } catch (IOException e) // Catch IOException
        {
            BurningMinesOfBelow.LOGGER.info("ERROR WRITING JSON OBJECT: " + this.jsonObject.toString() + " TO FILE " + this.file + "!");
            e.printStackTrace();
        }
        return false; // Couldn't write to file. Return false
    }

    /**
     * Writes the double to the Json object only if a value for it does not exist at the designated path
     * @param jsonObject The json object to write to
     * @param key The path to write the double to
     * @param value The double to write to the json object
     * @return True if the value was successfully added to the json object. Returns false if a value is already present
     */
    public boolean toJsonNotNull(JsonObject jsonObject, String key, double value)
    {
        if (jsonObject.get(key) == null)
        {
            jsonObject.addProperty(key, value);
            return true;
        }
        return false;
    }

    /**
     * Writes the float to the Json object only if a value for it does not exist at the designated path
     * @param jsonObject The json object to write to
     * @param key The path to write the float to
     * @param value The double to write to the json object
     * @return True if the value was successfully added to the json object. Returns false if a value is already present
     */
    public boolean toJsonNotNull(JsonObject jsonObject, String key, float value)
    {
        if (jsonObject.get(key) == null)
        {
            jsonObject.addProperty(key, value);
            return true;
        }
        return false;
    }


    /**
     * Writes a json element to the json object only if one does not exist at the designated path.
     * @param jsonObject The json object to write to
     * @param key The path to write the json element to
     * @param jsonElement The json element to write to the json object
     * @return True if the json element was successfully added to the json object. Returns false if a json element is already present.
     */
    public boolean toJsonNotNull(JsonObject jsonObject, String key, JsonElement jsonElement)
    {
        if (jsonObject.get(key) == null)
        {
            jsonObject.add(key, jsonElement);
            return true;
        }
        return false;
    }

}
