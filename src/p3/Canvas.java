package p3;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * Clase de utilidad para representar gráficamente los grafos.
 */
public class Canvas extends JPanel
{
    private final Graph graph, tree;
    private FontRenderContext frc = new FontRenderContext(null, true, true);

    private int xmin = Integer.MAX_VALUE,
            ymin = Integer.MAX_VALUE,
            xmax = Integer.MIN_VALUE,
            ymax = Integer.MIN_VALUE;

    private Canvas(Graph g, Graph t)
    {
        graph = g;
        tree  = t;
        getRectangle(g);

        if(t != null)
            getRectangle(t);
    }

    private void getRectangle(Graph g)
    {
        for(Vertex v : g.vertices())
        {
            if(xmin > v.x)
                xmin = v.x;

            if(ymin > v.y)
                ymin = v.y;

            if(xmax < v.x)
                xmax = v.x;

            if(ymax < v.y)
                ymax = v.y;
        }
    }

    @Override protected void paintComponent(Graphics g)
    {
        ((Graphics2D)g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        int width  = xmax - xmin,
                height = ymax - ymin;

        double sx = getWidth() / (width * 11. / 10.),
                sy = getHeight() / (height * 11. / 10.),
                s  = Math.min(sx, sy);

        Graphics2D g2 = (Graphics2D)g;
        g2.scale(s, s);

        g2.translate((getWidth() / s - width) / 2. - xmin,
                (getHeight() / s - height) / 2. + ymax);

        paintEdges(g2, graph, s, 2, Color.white);
        paintWeights(g2, s);

        if(tree != null)
        {
            paintEdges(g2, tree, s, 7, Color.green);
            paintTreeWeight(g2, s);
        }
    }

    private void paintEdges(Graphics2D g, Graph t, double s, int w, Color c)
    {
        float ws = (float)(w / s),
                d  = (float)(16 / s),
                d2 = d / 2;

        g.setColor(c);
        g.setStroke(new BasicStroke(ws));

        for(Edge e : t.edges())
        {
            Vertex u = e.getU(),
                    v = e.getV();

            g.drawLine(u.x, -u.y, v.x, -v.y);
            g.fill(new Ellipse2D.Double(u.x - d2, -u.y - d2, d, d));
            g.fill(new Ellipse2D.Double(v.x - d2, -v.y - d2, d, d));
        }
    }

    private void paintWeights(Graphics2D g, double s)
    {
        g.setColor(Color.yellow);
        Font f = g.getFont();
        f = f.deriveFont((float)(22 / s));
        g.setFont(f);

        for(Edge e : graph.edges())
        {
            Vertex u = e.getU(),
                    v = e.getV();

            String w = Integer.toString(e.getW());
            Rectangle2D r = f.getStringBounds(w, frc);
            LineMetrics m = f.getLineMetrics(w, frc);

            double cx = ( u.x + v.x) / 2.,
                    cy = (-u.y - v.y) / 2.,
                    dx = v.y - u.y,
                    dy = v.x - u.x,
                    dh = Math.sqrt(dx * dx + dy * dy);

            dx /= dh;
            dy /= dh;
            // Ahora (dx,dy) es un vector normalizado
            // perpendicular a la arista.

            if(dx < 0)
                dx *= r.getWidth() * 1.5;
            else
                dx *= m.getDescent();

            if(dy < 0)
                dy *= m.getDescent() * 1.5;
            else
                dy *= m.getHeight();

            GlyphVector gv = f.createGlyphVector(frc, w);
            g.drawGlyphVector(gv, (float)(cx + dx), (float)(cy + dy));
        }
    }

    private void paintTreeWeight(Graphics2D g, double s)
    {
        int total = 0;

        for(Edge e : tree.edges())
            total += e.getW();

        g.setColor(Color.yellow);
        String str = "Peso total:  "+ total;

        GlyphVector gv = g.getFont().createGlyphVector(frc, str);
        g.drawGlyphVector(gv, xmin, -ymin);
    }

    /**
     * Dibuja el grafo indicado.
     * @param g Grafo a dibujar.
     */
    public static void paint(Graph g)
    {
        paint(g, null);
    }

    /**
     * Dibuja un grafo y su árbol de recubrimiento mínimo.
     * @param g Grafo a dibujar.
     * @param t Árbol de recubrimiento mínimo.
     */
    public static void paint(Graph g, Graph t)
    {
        EventQueue.invokeLater(() ->
        {
            Canvas canvas = new Canvas(g, t);
            JFrame frame = new JFrame("Kruskal");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.getContentPane().add(canvas);
            frame.setVisible(true);
        });
    }

} // JPanel
