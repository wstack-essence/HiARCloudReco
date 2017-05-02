package io.hiar.recognize;

/**
 * @Author lerry
 * @Datetime 02/05/2017 16:44
 */
public class Target
{
    private String targetID;
    private String name;
    private String imageURL;

    public String getTargetID()
    {
        return targetID;
    }

    public void setTargetID(String targetID)
    {
        this.targetID = targetID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }
}
