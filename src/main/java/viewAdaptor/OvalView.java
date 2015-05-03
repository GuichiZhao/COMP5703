package viewAdaptor;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by guichi on 14/04/2015.
 */
public class OvalView extends VertexView{


    public static OvalRenderer renderer=new OvalRenderer();
    private Object userObject;

    public OvalView(Object o) {

        super(o);
        userObject=o;
    }


    @Override
    public Point2D getPerimeterPoint(EdgeView edgeView, Point2D source, Point2D p)

    {
        if (getRenderer() instanceof OvalRenderer)
        {
            return ((OvalRenderer) getRenderer()).getPerimeterPoint(this,source,p);
        }
        else
        return super.getPerimeterPoint(edgeView, source, p);
    }

    @Override
    public CellViewRenderer getRenderer() {
        return renderer;
    }

    static class OvalRenderer extends VertexRenderer
    {
        @Override
        public void paint(Graphics graphics) {
            if (gradientColor!=null&&!preview&&isOpaque())
            {
                setOpaque(false);
                Graphics2D g2d= (Graphics2D) graphics;
                g2d.setPaint(new GradientPaint(0,0,getBackground(),getWidth(),getHeight(),gradientColor,true));
                g2d.drawOval(0, 0, getWidth(), getHeight());
                g2d.fillOval(0,0,getWidth(),getHeight());

            }
            super.paint(graphics);
            paintSelectionBorder(graphics);

        }
    }


    @Override
    public String toString() {
        return userObject.toString();
    }
}
