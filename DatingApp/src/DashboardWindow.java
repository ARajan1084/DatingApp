import javax.swing.*;
import java.awt.*;

public class DashboardWindow extends JFrame {

    private Person myPerson;

    public DashboardWindow(Person person) {
        createView();
        setSize(new Dimension(1000, 800));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createView() {
        BorderLayout layout = new BorderLayout();
    }

    private Panel eastPanel() {
        return null; // TODO: fix
    }

    private Panel westPanel() {
        return null; // TODO: fix
    }
}
