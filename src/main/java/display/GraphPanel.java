package display;

import model.Implicant;
import model.Varieable;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import reader.*;

import org.jgraph.JGraph;
import org.jgraph.graph.*;
import util.MyViewFactory;
import viewAdaptor.OvalCell;
import viewAdaptor.OvalView;
import viewAdaptor.RectCell;
import viewAdaptor.RectView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * Created by guichi on 28/03/2015.
 */
public class GraphPanel extends JFrame {
    private final Random generator = new Random();
    private FileScanner fileScanner;
    private  String var="";
    private Map<String,Implicant> variableToImplicant=new HashMap<>();

    public GraphPanel() {

        JTextArea infoArea=new JTextArea("Info Area",10,15);
        infoArea.setEditable(false);
        //info.setColumns(100);
        //info.append("hahaha");
        add(infoArea, BorderLayout.SOUTH);


        try {
            fileScanner = new FileScanner("input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        GraphModel model = new DefaultGraphModel();
        GraphLayoutCache view = new GraphLayoutCache(model, new MyViewFactory());


        JGraph graph = new JGraph(model, view);
        graph.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                double x = e.getX();
                double y = e.getY();
                try {
                    CellView c = graph.getLeafViewAt(x, y);
                    if (c instanceof OvalView) {
                        var = c.toString();
                        Implicant g=variableToImplicant.get(var);
                        infoArea.setText(g.getPositiveVariable()+"\n will be efficient independently");
                    }
                    else if (c instanceof RectView)
                    {
                        var=c.toString();
                        Implicant g=variableToImplicant.get(var);
                        infoArea.setText(g.getPositiveVariable()+"will be efficient \n on condition that"+
                            g.getNegativeVariable()+"will not present"
                        );
                    }
                }
                catch (Exception ex)
                {

                }

            }
        });

//        graph.getModel().addGraphModelListener(new GraphModelListener() {
//            @Override
//            public void graphChanged(GraphModelEvent graphModelEvent) {
//
//            }
//        });


        List<DefaultGraphCell> cellList = new ArrayList<>();
        List<DefaultGraphCell> nodeList=new ArrayList<>();
        List<DefaultEdge> edgesList = new ArrayList<>();




        while (fileScanner.hasNext())

        {

            Implicant i = FileScanner.readLine(fileScanner.next());


            DefaultGraphCell groupRoot = new DefaultGraphCell();
//            GraphConstants.setGradientColor(groupRoot.getAttributes(),Color.cyan);
//            GraphConstants.setOpaque(groupRoot.getAttributes(), true);
            //System.out.println("AA");
            int rootX = generator.nextInt(500);
            int rootY = generator.nextInt(400);

//            GraphConstants.setBounds(groupRoot.getAttributes(),new Rectangle2D.Double(
//                    rootX,rootY,20,20
//            ));
//
//            GraphConstants.setEditable(groupRoot.getAttributes(),false);



            int initRootX = rootX;
            int initRootY = rootY;


            //add a background
            if (i.getSize() >= 2) {

                DefaultGraphCell background = new DefaultGraphCell();


                GraphConstants.setOpaque(background.getAttributes(), true);
                if (i.isAllPositive())
                    GraphConstants.setBackground(background.getAttributes(), Color.YELLOW);
                else
                    GraphConstants.setBackground(background.getAttributes(), Color.GREEN);

                GraphConstants.setBounds(background.getAttributes(),
                        new Rectangle2D.Double(initRootX, initRootY, 130, 90));

                groupRoot.add(background);
            }

//


            while (i.hasNext()) {


                Varieable tmp = i.next();

                if (tmp.isPresent()) {
                    variableToImplicant.put(tmp.getName(),i);
                    DefaultPort port = new DefaultPort();
                    DefaultPort[] ports = new DefaultPort[1];
                    ports[0] = port;

                    DefaultGraphCell cell;

                    if (i.isAllPositive()) {
                        cell = new OvalCell(tmp.getName(), null, ports);
                        GraphConstants.setGradientColor(cell.getAttributes(), Color.BLUE);
                    } else {
                        cell = new RectCell(tmp.getName(), null, ports);
                        GraphConstants.setGradientColor(cell.getAttributes(), Color.RED);
                    }

                    int tmpX;
                    int tmpY;
                    if (i.isSingle())
                    {
                        tmpX=generator.nextInt(400);
                        tmpY=generator.nextInt(400);
                        System.out.println("SIn " + i.isSingle());

                        while ( (graph.getLeafViewAt(tmpX,tmpY)!=null)||(graph.getLeafViewAt(tmpX+60,tmpY+20)!=null)
                                ||(graph.getLeafViewAt(tmpX,tmpY+20)!=null)||(graph.getLeafViewAt(tmpX+60,tmpY)!=null))
                        {

                            System.out.println("X Y " + tmpX + " " + tmpY+" \t"+graph.getLeafViewAt(tmpX,tmpY));
                            System.out.println("X Y " + tmpX + " " + tmpY+" \t"+graph.getLeafViewAt(tmpX+60,tmpY+20));


                            tmpX=generator.nextInt(400);
                            tmpY=generator.nextInt(400);

                            System.out.println("After : X Y " + tmpX + " " + tmpY+" \t"+graph.getLeafViewAt(tmpX,tmpY));
                        }

                        GraphConstants.setBounds(cell.getAttributes(),
                                new Rectangle2D.Double(tmpX, tmpY, 60, 20));
                    }
                    else {

                        //    while (graph.getLeafViewAt(tmpX,tmpY)!=null) {
                        tmpX = generator.nextInt(20) + rootX;
                        tmpY = generator.nextInt(20) + rootY;

                        GraphConstants.setBounds(cell.getAttributes(),
                                new Rectangle2D.Double(tmpX, tmpY, 60, 20));
                    }
                        GraphConstants.setOpaque(cell.getAttributes(), true);
                  //  }


//                    {
//                        GraphConstants.setBounds(cell.getAttributes(),
//                                new Rectangle2D.Double(generator.nextInt(20)+rootX, generator.nextInt(20)+rootY, 60, 20));
//                        GraphConstants.setOpaque(cell.getAttributes(), true);
//                        System.out.println("HHHH");
//                    }


                    rootX += 50;
                    rootY += 50;

                    groupRoot.add(cell);
                }
            }

            graph.getGraphLayoutCache().insert(groupRoot);
            nodeList.add(groupRoot);
            cellList.addAll(nodeList);

//




            //System.out.println("!!! " + nodeList);
            if (groupRoot.getChildren().size() > 1) {
                for (int j = 1; j < groupRoot.getChildren().size(); j++) {


                    if (j + 1 < groupRoot.getChildren().size()) {
                        DefaultEdge edge = new DefaultEdge();


                        edge.setSource(((DefaultGraphCell) groupRoot.getChildren().get(j)).getChildAt(0));
                        edge.setTarget(((DefaultGraphCell) groupRoot.getChildren().get(j + 1)).getChildAt(0));
                        // GraphConstants.setLineWidth(edge.getAttributes(),10);
                        edgesList.add(edge);
                    }


                }


            }
            if (!nodeList.isEmpty()) {
                DefaultGraphCell c = (DefaultGraphCell) nodeList.get(0).clone();
                // System.out.println(c.getAttributes()+" "+c.getChildAt(0));}

                cellList.addAll(edgesList);

                // System.out.println(i);
            }





        }

       // graph.getGraphLayoutCache().insert(nodeList.toArray());
       // graph.getGraphLayoutCache().insert(edgesList.toArray());
        add(new JScrollPane(graph), BorderLayout.NORTH);
//        JGraphFacade facade=new JGraphFacade(graph);
//        JGraphLayout layout=new JGraphSpringLayout(1);
//        layout.run(facade);
//        Map nested=facade.createNestedMap(true,true);
//        graph.getGraphLayoutCache().edit(nested);

        //com.jgraph.layout.graph.JGraphSpringLayout




   // System.out.println(variableToImplicant);

    }


    private void getValid()
    {

    }


}
