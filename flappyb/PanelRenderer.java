package flappyb;

import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class PanelRenderer extends JPanel {

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        FlappyB.flappybird.repaint(g);
    }

}
