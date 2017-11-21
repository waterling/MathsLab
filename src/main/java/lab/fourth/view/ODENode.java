package lab.fourth.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import lab.LabsNode;

import java.io.IOException;

public class ODENode implements LabsNode {
    @Override
    public Node draw() {
        try {
            return FXMLLoader.<Parent>load(ODENode.class.getResource("/lab/fourth/view/ODE.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return new Label("Cannot load integration view ");
        }

    }
}
