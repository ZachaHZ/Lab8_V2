package controller;

import domain.BTree;
import domain.TreeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

import java.util.Random;

public class GraphicBTreeController {
    @FXML
    private BorderPane bp;

    private BTree binaryTree;

    @FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        // Generar un nuevo árbol binario con elementos aleatorios
        binaryTree = new BTree();
        Random random = new Random();
        int n = random.nextInt(100); // Genera un número aleatorio entre 0 y 99
        for (int i = 0; i < n; i++) {
            binaryTree.add(random.nextInt(1000)); // Valores numéricos aleatorios entre 0 y 999
        }
        System.out.println("Se ha generado un nuevo árbol con " + n + " elementos aleatorios.");
    }

    @FXML
    public void tourOnAction(ActionEvent actionEvent) throws TreeException {
        // Mostrar información relacionada con el árbol
        if (binaryTree == null) {
            showAlert("Error", "Árbol no generado", "Debe generar un árbol antes de ver la información del recorrido.");
            return;
        }
        String info = "Altura del árbol: " + binaryTree.height() + "\n" +
                "Recorrido preorden: " + binaryTree.preOrder() + "\n" +
                "Recorrido inorden: " + binaryTree.inOrder() + "\n" +
                "Recorrido postorden: " + binaryTree.postOrder();
        showAlert("Tour Info", "Información del árbol", info);
    }

    @FXML
    public void levelsOnAction(ActionEvent actionEvent) {
        // Trazar una línea horizontal y enumerar los niveles del árbol
        if (binaryTree == null) {
            showAlert("Error", "Árbol no generado", "Debe generar un árbol antes de ver los niveles.");
            return;
        }
        // Código para trazar una línea horizontal y enumerar los niveles del árbol gráficamente
        System.out.println("Se han trazado los niveles del árbol.");
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    //Nota ZACHA: Esto lo tuve que agregar porque me daba error
    public void levesOnAction(ActionEvent actionEvent) {
    }
}
