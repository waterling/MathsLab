package lab.third.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import lab.LabsNode;

import java.io.IOException;

public class CubicSplineNode implements LabsNode {
    @Override
    public Node draw() {
        try {
            return FXMLLoader.<Parent>load(CubicSplineNodeHelp.class.getResource("/lab/third/view/CubicSplineView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return new Label("Cannot load integration view ");
        }
    }
}
