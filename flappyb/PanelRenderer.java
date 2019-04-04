package flappyb;


import javax.swing.*;
import java.awt.*;

public class PanelRenderer extends JPanel {

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        FlappyB.flappybird. repaint(g);
    }

}
