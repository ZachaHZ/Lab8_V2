package controller;
import domain.BTree;
import domain.BTreeNode;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class GraphicBTreeController {

    @FXML
    private Pane treePane; // Contenedor donde se mostrará el árbol

    private BTree binaryTree;

    public void initialize() {
        // Inicializa tu árbol binario aquí, si es necesario
    }

    @FXML
    public void generateTree() {
        // Genera un nuevo árbol binario
        binaryTree = new BTree();

        // Agrega algunos elementos al árbol (puedes personalizar esto según tus necesidades)
        binaryTree.add(5);
        binaryTree.add(3);
        binaryTree.add(8);
        binaryTree.add(1);
        binaryTree.add(4);
        binaryTree.add(7);
        binaryTree.add(9);

        // Muestra el árbol en la interfaz gráfica
        displayTree();
    }

    private void displayTree() {
        // Borra cualquier visualización previa del árbol
        treePane.getChildren().clear();

        // Calcula la altura del árbol
        int treeHeight = calculateHeight(binaryTree.getRoot());

        // Calcula la posición de cada nivel
        double levelHeight = 100; // Altura entre niveles (ajústalo según sea necesario)

        // Calcula el ancho total del árbol (para centrarlo)
        double totalWidth = Math.pow(2, treeHeight - 1) * 100;

        // Dibuja cada nodo y sus conexiones
        for (int i = 0; i < treeHeight; i++) {
            double x = totalWidth / Math.pow(2, i) - 25; // Posición X del primer nodo en el nivel
            double y = levelHeight * i + 50; // Posición Y del nivel

            int nodesInLevel = (int) Math.pow(2, i); // Número de nodos en el nivel actual

            for (int j = 0; j < nodesInLevel; j++) {
                Circle circle = new Circle(x, y, 20); // Crea un círculo para representar el nodo
                treePane.getChildren().add(circle);

                // Dibuja las líneas que conectan este nodo con sus hijos
                if (i < treeHeight - 1) {
                    double nextLevelY = levelHeight * (i + 1) + 50;
                    Line leftLine = new Line(x, y + 20, x - totalWidth / Math.pow(2, i + 1) + 25, nextLevelY - 20);
                    Line rightLine = new Line(x, y + 20, x + totalWidth / Math.pow(2, i + 1) - 25, nextLevelY - 20);
                    treePane.getChildren().addAll(leftLine, rightLine);
                }

                x += totalWidth / Math.pow(2, i);
            }
        }
    }

    private int calculateHeight(BTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = calculateHeight(node.left);
            int rightHeight = calculateHeight(node.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
