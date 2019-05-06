package gui;

import javax.swing.*;
import java.awt.*;

public class DashboardWindow extends JFrame {

    public DashboardWindow() {
        createView();
        setSize(new Dimension(700, 500));
        setResizable(false);
    }

    private void createView() {
        JPanel dashboard = new JPanel();
        getContentPane().add(dashboard);
    }

    private void eastPanel() {

    }

    private void westPanel() {

    }
}
