package viewAdaptor;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;

import javax.swing.tree.MutableTreeNode;

/**
 * Created by guichi on 15/04/2015.
 */
public class OvalCell extends DefaultGraphCell{
    public OvalCell() {
        super(null);
    }

    public OvalCell(Object o, AttributeMap attributeMap, MutableTreeNode[] mutableTreeNodes) {
        super(o, attributeMap, mutableTreeNodes);
    }

    public OvalCell(Object userObject) {
        super(userObject);




    }
}
