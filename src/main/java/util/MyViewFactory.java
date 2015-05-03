package util;

import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.VertexView;
import viewAdaptor.OvalCell;
import viewAdaptor.OvalView;
import viewAdaptor.RectCell;
import viewAdaptor.RectView;

/**
 * Created by guichi on 15/04/2015.
 */
public class MyViewFactory extends DefaultCellViewFactory{
    @Override
    protected VertexView createVertexView(Object o) {
        if (o instanceof OvalCell)
        {
            return new OvalView(o);
        }
        else if (o instanceof RectCell)
        {
            return new RectView(o);
        }
        else
        return super.createVertexView(o);
    }
}
