package iitm.apl.MazeGenerator;

import java.awt.event.WindowEvent;


class WindowListener extends java.awt.event.WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
