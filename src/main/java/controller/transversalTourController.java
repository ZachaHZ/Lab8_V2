package controller;

import domain.BTree;
import domain.BTreeNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.List;

public class transversalTourController {

    @FXML
    private BorderPane bp;

    @FXML
    private Pane treePane;

    private BTree tree = new BTree();

    @FXML
    public void postOrderOnAction(ActionEvent actionEvent) {
        List<BTreeNode> postOrderList = tree.postOrderV();
        displayTraversal(postOrderList);
    }

    @FXML
    public void inOrderOnAction(ActionEvent actionEvent) {
        List<BTreeNode> inOrderList = tree.inOrderV();
        displayTraversal(inOrderList);
    }

    @FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        tree.clear();
        int n = util.Utility.getRandom(99);
        for (int i = 0; i < n; i++) {
            tree.add(util.Utility.getRandom(99));
        }
        updateTree();
    }

    @FXML
    public void preOrderOnAction(ActionEvent actionEvent) {
        List<BTreeNode> preOrderList = tree.preOrderV();
        displayTraversal(preOrderList);
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

    private void updateTree() {
        treePane.getChildren().clear();
        drawTree(tree.getRoot(), 400, 30, 200);
    }

    private void displayTraversal(List<BTreeNode> traversalList) {
        treePane.getChildren().clear();
        double x = 30;
        double y = 30;
        double xOffset = 30;

        for (BTreeNode node : traversalList) {
            Circle circle = new Circle(x, y, 15);
            Text text = new Text(x - 5, y + 5, node.data.toString());
            treePane.getChildren().addAll(circle, text);
            x += xOffset;
        }
    }
}
