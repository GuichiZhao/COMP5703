package viewAdaptor;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;

import javax.swing.tree.MutableTreeNode;

/**
 * Created by guichi on 29/04/2015.
 */
public class RectCell extends DefaultGraphCell{
    public RectCell(Object o, AttributeMap attributeMap) {
        super(o, attributeMap);
    }

    public RectCell(Object o, AttributeMap attributeMap, MutableTreeNode[] mutableTreeNodes) {
        super(o, attributeMap, mutableTreeNodes);
    }
}
