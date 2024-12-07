package com.matrix;

import com.matrix.interfaces.iMatrixGUI;
import com.matrix.interfaces.iMatrixOperation;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Класс, отвечающий за графический интерфейс пользователя (GUI) для работы с матрицами.
 * Обеспечивает взаимодействие пользователя с приложением через графические элементы.
 */
public class MatrixGUI extends Application implements iMatrixGUI {
    private static final Logger logger = LogManager.getLogger(MatrixGUI.class);

    private BorderPane root = new BorderPane();
    private TabPane tabPane = new TabPane();

    private Matrix matrix1;
    private Matrix matrix2;
    private Matrix resultMatrix;
    private MatrixLoader loader = new MatrixLoader(0, 0);

    @Override
    public void start(Stage stage) throws IOException {
        logger.info("Запуск приложения.");

        stage.setTitle("Матрицы");

        ToolBar toolBar = createMainToolBar(stage);
        Label helloLabel = createLabel("Добро пожаловать!", 24, true);

        root.setTop(toolBar);
        root.setCenter(helloLabel);
        BorderPane.setAlignment(helloLabel, Pos.CENTER);

        Scene mainScene = new Scene(root, 960, 600);
        stage.setScene(mainScene);
        stage.show();
    }

    @Override
    public ToolBar createMainToolBar(Stage stage) {
        ToolBar mainToolBar = new ToolBar();

        addButtonToToolBar(mainToolBar, "Загрузить", event -> createFileTab(stage));
        addButtonToToolBar(mainToolBar, "Сумма", event -> handleMatrixOperation(stage, Matrix::add));
        addButtonToToolBar(mainToolBar, "Разность", event -> handleMatrixOperation(stage, Matrix::subtract));
        addButtonToToolBar(mainToolBar, "Произведение", event -> handleMatrixOperation(stage, Matrix::multiply));
        addButtonToToolBar(mainToolBar, "Определитель", event -> handleDeterminant(stage));
        addButtonToToolBar(mainToolBar, "Выход", event -> {
            logger.info("Завершение приложения.");
            System.exit(0);
        });

        return mainToolBar;
    }

    /**
     * Добавляет кнопку на панель инструментов.
     * @param toolBar Панель инструментов, к которой добавляется кнопка.
     * @param text Текст кнопки.
     * @param action Действие, выполняемое при нажатии кнопки.
     */
    private void addButtonToToolBar(ToolBar toolBar, String text, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setOnAction(action);
        toolBar.getItems().add(button);
    }

    @Override
    public void createFileTab(Stage stage) {
        logger.info("Открыта вкладка загрузки файлов.");

        Tab fileTab = new Tab("Загрузка файлов");
        VBox matrixSelectionBox = createSelectionBox(stage);
        fileTab.setContent(matrixSelectionBox);

        tabPane.getTabs().setAll(fileTab);
        root.setCenter(tabPane);
    }

    @Override
    public void createMatrixTabs(Matrix matrix1, Matrix matrix2, Matrix resultMatrix) {
        logger.info("Выполнена операция создания вкладок.");

        Tab matrix1Tab = createMatrixTab("Матрица 1", matrix1);
        Tab matrix2Tab = createMatrixTab("Матрица 2", matrix2);
        Tab resultTab = createMatrixTab("Результат", resultMatrix);

        tabPane.getTabs().setAll(matrix1Tab, matrix2Tab, resultTab);
        root.setCenter(tabPane);
    }

    /**
     * Создает вкладку с отображением матрицы.
     * @param title Заголовок вкладки.
     * @param matrix Матрица для отображения.
     * @return Созданная вкладка.
     */
    private Tab createMatrixTab(String title, Matrix matrix) {
        Tab tab = new Tab(title);

        GridPane matrixGrid = createMatrixGrid(matrix.getRows(), matrix.getCols(), matrix.getData());

        ScrollPane scrollPane = new ScrollPane(matrixGrid);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        StackPane tabPane = new StackPane(scrollPane);
        StackPane.setAlignment(scrollPane, Pos.CENTER);

        tab.setContent(tabPane);

        return tab;
    }

    /**
     * Создает вкладки для отображения матриц и их определителей.
     * @param matrix1 Первая матрица.
     * @param matrix2 Вторая матрица.
     * @throws MatrixOperationException Если возникает ошибка при вычислении определителя.
     */
    private void createDeterminantTabs(Matrix matrix1, Matrix matrix2) throws MatrixOperationException {
        logger.info("Вычисление определителей.");

        Tab matrix1Tab = createMatrixTab("Матрица 1", matrix1);
        Tab matrix2Tab = createMatrixTab("Матрица 2", matrix2);
        Tab det1Tab = createDeterminantTab("Определитель 1", matrix1);
        Tab det2Tab = createDeterminantTab("Определитель 2", matrix2);

        tabPane.getTabs().setAll(matrix1Tab, matrix2Tab, det1Tab, det2Tab); // Все 4 вкладки
        root.setCenter(tabPane);
    }

    @Override
    public void handleMatrixOperation(Stage stage, iMatrixOperation operation) {
        logger.debug("Начата обработка операции с матрицами: {}", operation.toString());

        if (matrix1 == null || matrix2 == null) {
            String message = "Загрузите матрицы!";
            showErrorAlert(message);
            return;
        }

        try {
            resultMatrix = operation.apply(matrix1, matrix2);
            createMatrixTabs(matrix1, matrix2, resultMatrix);
        } catch (MatrixOperationException ex) {
            logger.error("Ошибка при выполнении операции {}: {}", operation.toString(), ex.getMessage(), ex); // Логирование с исключением
            showErrorAlert(ex.getMessage());
        }
    }

    @Override
    public void handleDeterminant(Stage stage) {
        logger.info("Нажата кнопка 'Определитель'.");

        if (matrix1 == null || matrix2 == null) {
            String message = "Загрузите матрицы!";
            showErrorAlert(message);
            return;
        }

        try {
            createDeterminantTabs(matrix1, matrix2);
        } catch (Exception ex) {
            showErrorAlert("Ошибка при вычислении определителя.");
        }
    }

    /**
     * Создает вкладку с отображением определителя матрицы.
     * @param title Заголовок вкладки.
     * @param matrix Матрица, определитель которой нужно отобразить.
     * @return Созданная вкладка.
     * @throws MatrixOperationException  Если возникает ошибка при вычислении определителя.
     */
    private Tab createDeterminantTab(String title, Matrix matrix) throws MatrixOperationException {
        Tab tab = new Tab(title);

        double determinant = matrix.determinant();
        DecimalFormat df = new DecimalFormat("#.##");

        Label determinantLabel = createLabel("Определитель: " + df.format(determinant), 20, true);
        determinantLabel.setAlignment(Pos.CENTER);

        StackPane pane = new StackPane(determinantLabel);
        pane.setAlignment(Pos.CENTER);
        tab.setContent(pane);

        return tab;
    }

    /**
     * Создает GridPane для отображения матрицы.
     * @param rows Количество строк в матрице.
     * @param cols Количество столбцов в матрице.
     * @param data Данные матрицы.
     * @return GridPane с отображением матрицы.
     */
    private GridPane createMatrixGrid(int rows, int cols, double[][] data) {
        GridPane matrixGrid = new GridPane();
        matrixGrid.setAlignment(Pos.CENTER);
        matrixGrid.setHgap(10);
        matrixGrid.setVgap(10);
        matrixGrid.setPadding(new Insets(10));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Label cell = new Label(String.valueOf(data[i][j]));
                cell.setPrefWidth(50);
                cell.setPrefHeight(30);
                cell.setAlignment(Pos.CENTER);
                matrixGrid.add(cell, j, i);
            }
        }
        return matrixGrid;
    }

    /**
     * Создает VBox для выбора файлов матриц.
     * @param stage Stage приложения.
     * @return VBox с элементами выбора файлов.
     */
    private VBox createSelectionBox(Stage stage) {
        VBox matrixSelectionBox = new VBox(15);
        matrixSelectionBox.setAlignment(Pos.CENTER);
        matrixSelectionBox.getChildren().add(createLabel("Загрузка файлов", 24, true));

        File[] selectedFiles = new File[2];

        for (int i = 1; i <= 2; i++) {
            HBox row = new HBox(15);
            row.setAlignment(Pos.CENTER);

            Label matrixLabel = createLabel("Матрица " + i, 18);
            Button chooseButton = new Button("Выбрать файл");

            final int matrixIndex = i;
            chooseButton.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Выберите файл матрицы " + matrixIndex);
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                File selectedFile = fileChooser.showOpenDialog(stage);
                if (selectedFile != null) {
                    matrixLabel.setText("Матрица " + matrixIndex + ": " + selectedFile.getName());
                    selectedFiles[matrixIndex - 1] = selectedFile;
                }
            });

            row.getChildren().addAll(matrixLabel, chooseButton);
            matrixSelectionBox.getChildren().add(row);
        }

        Button confirmButton = new Button("Подтвердить");
        confirmButton.setPrefWidth(225);
        confirmButton.setPrefHeight(25);

        matrixSelectionBox.getChildren().add(confirmButton);
        matrixSelectionBox.setAlignment(Pos.CENTER);

        confirmButton.setOnAction(e -> {
            if (selectedFiles[0] != null && selectedFiles[1] != null) {
                try {
                    matrix1 = loader.loadFromFile(selectedFiles[0]);
                    matrix2 = loader.loadFromFile(selectedFiles[1]);

                    logger.info("Матрицы успешно загружены.");

                    Label helloLabel = createLabel("Матрицы добавлены!", 24, true);
                    root.setCenter(helloLabel);
                    BorderPane.setAlignment(helloLabel, Pos.CENTER);
                } catch (IOException | MatrixOperationException ex) {
                    showErrorAlert(ex.getMessage());
                }
            } else {
                showErrorAlert("Выберите оба файла!");
            }
        });

        return matrixSelectionBox;
    }

    /**
     * Создает метку с заданным текстом и размером шрифта.
     * @param text Текст метки.
     * @param fontSize Размер шрифта.
     * @return Созданная метка.
     */
    private Label createLabel(String text, int fontSize) {
        return createLabel(text, fontSize, false);
    }

    /**
     * Создает метку с заданным текстом, размером шрифта и флагом жирного шрифта.
     * @param text Текст метки.
     * @param fontSize Размер шрифта.
     * @param bold Флаг жирного шрифта.
     * @return Созданная метка.
     */
    private Label createLabel(String text, int fontSize, boolean bold) {
        Label label = new Label(text);

        if (bold) {
            label.setStyle("-fx-font-size: " + fontSize + "px;-fx-font-weight: bold;");
        } else {
            label.setStyle("-fx-font-size: " + fontSize + "px;");
        }

        return label;
    }

    @Override
    public void showErrorAlert(String message) {
        logger.error("Ошибка: " + message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}