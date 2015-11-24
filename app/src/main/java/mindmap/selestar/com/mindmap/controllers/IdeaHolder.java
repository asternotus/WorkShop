package mindmap.selestar.com.mindmap.controllers;

import java.util.List;

import mindmap.selestar.com.mindmap.Constants;
import mindmap.selestar.com.mindmap.models.IdeaElement;

/**
 * Created by ASTER-NOTUS on 15.11.2015.
 */
public class IdeaHolder
{
    List<IdeaElement> listIdeas;

    public IdeaHolder(List<IdeaElement> listIdeas)
    {
        this.listIdeas = listIdeas;
    }

    public void saveIdeas(IdeaElement ideaElement)
    {
        DBManager.getInstance().addIdeaElement(ideaElement);
    }

    public List<IdeaElement> getIdeas()
    {
        for (int i = 0; i < DBManager.getInstance().getIdCount(Constants.TABLE_IDEA); i++)
        {
            listIdeas.add(DBManager.getInstance().getIdea(i));
        }

        return listIdeas;
    }
}
