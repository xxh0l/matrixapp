package com.matrix.interfaces;

import com.matrix.Matrix;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Графический интерфейс пользователя для работы с матрицами.
 * Позволяет загружать матрицы из файлов, выполнять операции сложения, вычитания, умножения,
 * а также вычислять определитель матриц.
 */
public interface iMatrixGUI {

    /**
     * Запускает графическое приложение. Инициализирует главное окно и отображает приветственное сообщение.
     *
     * @param stage Главный этап (окно) приложения.
     * @throws IOException Если возникает ошибка при чтении файла конфигурации логгирования.
     */
    void start(Stage stage) throws IOException;


    /**
     * Создает панель инструментов с кнопками для основных действий: загрузка матриц,
     * выполнение операций, выход из приложения.
     *
     * @param stage Главное окно приложения (для открытия диалоговых окон).
     * @return Созданную панель инструментов.
     */
    ToolBar createMainToolBar(Stage stage);

    /**
     * Создает вкладку для загрузки матриц из файлов.
     *
     * @param stage Главное окно приложения (для открытия диалоговых окон выбора файлов).
     */
    void createFileTab(Stage stage);


    /**
     * Создает вкладки для отображения матриц и результата операции.
     *
     * @param matrix1       Первая матрица.
     * @param matrix2       Вторая матрица.
     * @param resultMatrix  Результирующая матрица.
     */
    void createMatrixTabs(Matrix matrix1, Matrix matrix2, Matrix resultMatrix);

    /**
     * Обрабатывает операцию с матрицами (сложение, вычитание, умножение).
     *
     * @param stage     Главное окно приложения (для отображения ошибок).
     * @param operation Объект, представляющий операцию над матрицами.
     */
    void handleMatrixOperation(Stage stage, iMatrixOperation operation);

    /**
     * Обрабатывает вычисление определителя матриц.
     *
     * @param stage Главное окно приложения (для отображения ошибок).
     */
    void handleDeterminant(Stage stage);


    /**
     * Отображает диалоговое окно с сообщением об ошибке.
     *
     * @param message Текст сообщения об ошибке.
     */
    void showErrorAlert(String message);
}