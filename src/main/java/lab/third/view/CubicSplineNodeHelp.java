package lab.third.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import lab.LabsNode;

import java.io.IOException;

public class CubicSplineNodeHelp implements LabsNode {
    @Override
    public Node draw() {
        try {
            return FXMLLoader.<Parent>load(CubicSplineNodeHelp.class.getResource("/GUI/integration/view/IntegrationView.fxml"));
        } catch (IOException e) {
            return new Label("Cannot load integration view ");
        }

    }
}