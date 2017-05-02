package io.hiar.recognize;

import java.util.List;

/**
 * @Author lerry
 * @Datetime 02/05/2017 16:42
 */
public class RecognizeResult
{
    private int retCode;
    private String comment;
    private List<Target> items;

    public int getRetCode()
    {
        return retCode;
    }

    public void setRetCode(int retCode)
    {
        this.retCode = retCode;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public List<Target> getItems()
    {
        return items;
    }

    public void setItems(List<Target> items)
    {
        this.items = items;
    }
}
