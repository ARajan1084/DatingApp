import javax.swing.*;
import java.awt.*;

public class DashboardWindow extends JFrame {

    private Person myPerson;

    public DashboardWindow() {
        createView();
        setSize(new Dimension(700, 500));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createView() {
        JPanel dashboard = new JPanel();
        getContentPane().add(dashboard);
    }

    private Panel eastPanel() {
        return null; // TODO: fix
    }

    private Panel westPanel() {
        return null; // TODO: fix
    }
}
