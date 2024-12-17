package net.laserdiamond.burningminesofbelow.util.file;

import com.google.gson.*;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;

import java.io.*;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Json config file class for storing information about assets of this mod</li>
 * <li>Acts as the superclass of all Config classes for this program/mod</li>
 * <li>Because the fields for the aggregated objects are protected, all inherited classes will contain the same aggregation relationships</li>
 * @author Allen Malo
 */
public abstract class JsonConfig {

    /**
     * The name of the file
     */
    protected final String fileName; // JsonConfig has-a String

    /**
     * The file that will be written to
     */
    protected final File file; // JsonConfig has-a File

    /**
     * The root {@link JsonObject} that will be written to and read from in the file.
     */
    protected JsonObject jsonObject; // JsonConfig has-a JsonObject

    /**
     * Creates a new Json config file
     * @param fileName The name of the file
     */
    public JsonConfig(String fileName)
    {
        this.fileName = fileName;
        this.file = new File(modFilePath() + fileName + ".json");

        this.createFile(); // Create the file

        try (FileReader fileReader = new FileReader(this.file)) // Try-with-resources to close BufferedReader regardless of exceptions being thrown
        {
            this.jsonObject = this.createJsonNotNull(new Gson().fromJson(fileReader, JsonObject.class)); // Get existing json object from file

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
                fileWriter.write(this.jsonObjectPrettyPrint(this.jsonObject)); // Write the json object to the file
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
        try (FileWriter fileWriter = new FileWriter(this.file)) // Use try-with-resources to make sure Buffered Writer closes
        {
            fileWriter.write(this.jsonObjectPrettyPrint(this.jsonObject)); // Write the json object to the file in pretty print
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
        if (jsonObject.get(key) == null) // Is there something at the path?
        {
            jsonObject.addProperty(key, value); // Assign the path the value
            return true; // Added! Return true
        }
        return false; // Nothing added. Return false
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
        if (jsonObject.get(key) == null) // Is there something at the path?
        {
            jsonObject.addProperty(key, value); // Assign the path the value
            return true; // Added! Return true
        }
        return false; // Nothing added. Return false
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
        if (jsonObject.get(key) == null) // Is there something at the path?
        {
            jsonObject.add(key, jsonElement); // Assign the path the json element
            return true; // Added! Return true
        }
        return false; // Nothing added. Return false
    }

    /**
     * Creates a new {@link JsonObject} if the given is null.
     * This method should be called before writing to the {@link JsonObject}
     * @param jsonObject The {@link JsonObject} to instantiate, if null
     * @return A new {@link JsonObject} if the given is null. Returns itself if not null
     */
    public JsonObject createJsonNotNull(JsonObject jsonObject)
    {
        if (jsonObject == null) // Is the json object null?
        {
            jsonObject = new JsonObject(); // Assign a new instance
        }
        return jsonObject; // Return itself (or the new instance if null)
    }

    /**
     * Formats the {@link JsonObject}'s {@code toString()} method into pretty-print, making it more readable.
     * @param object The {@link JsonObject} to create pretty-print for
     * @return The {@link String} of the {@link JsonObject} in pretty-print format.
     */
    public String jsonObjectPrettyPrint(JsonObject object)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement prettyJson = JsonParser.parseString(object.toString());
        return gson.toJson(prettyJson);
    }

}
