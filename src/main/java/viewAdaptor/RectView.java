package viewAdaptor;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.VertexView;

/**
 * Created by guichi on 29/04/2015.
 */
public class RectView extends VertexView{
    private Object userObject;

    public RectView( Object userObject) {
        super(userObject);
        this.userObject = userObject;
    }


    @Override
    public String toString() {
        return userObject.toString();
    }
}
