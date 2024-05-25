package controller;

import domain.BTree;
import domain.BTreeNode;
import domain.TreeException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class bTreeOperationsController {
    BTree tree = new BTree();

    @javafx.fxml.FXML
    private BorderPane bp;

    @javafx.fxml.FXML
    private Pane treePane;

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        tree.clear();
        int n = util.Utility.getRandom(99);
        for(int i = 0; i < n; i++){
            tree.add(util.Utility.getRandom(99));
        }
        updateTree();
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        int randomValue = util.Utility.getRandom(100);
        tree.add(randomValue);
        updateTree();
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
        int randomValue = util.Utility.getRandom(100);
        try {
            boolean contains = tree.contains(randomValue);
            showAlert("Contains Node", "Tree contains " + randomValue + ": " + contains);
        } catch (TreeException e) {
            showAlert("Error", e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void nodeHeightOnAction(ActionEvent actionEvent) {
        int randomValue = util.Utility.getRandom(100);
        try {
            int height = tree.height(randomValue);
            showAlert("Node Height", "Height of node " + randomValue + ": " + height);
        } catch (TreeException e) {
            showAlert("Error", e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void treeHeightOnAction(ActionEvent actionEvent) {
        try {
            int height = tree.height();
            showAlert("Tree Height", "Height of the tree: " + height);
        } catch (TreeException e) {
            showAlert("Error", e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        int randomValue = util.Utility.getRandom(100);
        try {
            tree.remove(randomValue);
            updateTree();
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

    }

    private void updateTree() {
        treePane.getChildren().clear();
        drawTree(tree.getRoot(), 400, 30, 200);
    }

    private void drawTree(BTreeNode node, double x, double y, double xOffset) {
        if (node == null) return;

        Circle circle = new Circle(x, y, 15);
        Text text = new Text(x - 5, y + 5, node.data.toString());
        treePane.getChildren().addAll(circle, text);

        if (node.left != null) {
            double nextX = x - xOffset;
            double nextY = y + 60;
            Line line = new Line(x, y, nextX, nextY);
            treePane.getChildren().add(line);
            drawTree(node.left, nextX, nextY, xOffset / 2);
        }

        if (node.right != null) {
            double nextX = x + xOffset;
            double nextY = y + 60;
            Line line = new Line(x, y, nextX, nextY);
            treePane.getChildren().add(line);
            drawTree(node.right, nextX, nextY, xOffset / 2);
        }
    }

    private void showAlert(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
