package com.matrix;

import com.matrix.interfaces.iMatrixLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Класс, отвечающий за загрузку матриц из файла.
 */
public class MatrixLoader extends BaseMatrix implements iMatrixLoader {
    private static final Logger logger = LogManager.getLogger(MatrixLoader.class);

    /**
     * Конструктор для создания загрузчика матриц. Этот конструктор не используется непосредственно для загрузки,
     * а наследуется от BaseMatrix и необходим для корректной работы иерархии классов.
     *
     * @param rows Количество строк (не используется).
     * @param cols Количество столбцов (не используется).
     */
    public MatrixLoader(int rows, int cols) {
        super(rows, cols);
    }

    /**
     * Конструктор для создания загрузчика матриц. Этот конструктор не используется непосредственно для загрузки,
     * а наследуется от BaseMatrix и необходим для корректной работы иерархии классов.
     *
     * @param data Данные матрицы (не используется).
     */
    public MatrixLoader(double[][] data) {
        super(data);
    }

    @Override
    public Matrix loadFromFile(File file) throws IOException, MatrixOperationException {
        try (Scanner scanner = new Scanner(file).useLocale(Locale.US)) {
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            double[][] data = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i][j] = scanner.nextDouble();
                }
            }
            logger.info("Матрица загружена из файла: " + file.getAbsolutePath());
            return createMatrixFromData(data);
        } catch (InputMismatchException e) {
            logger.error("Ошибка при загрузке матрицы из файла {}: неверный формат файла.", file.getAbsolutePath(), e);
            throw new MatrixOperationException("Неверный формат файла матрицы."); // Добавлено исключение
        } catch (IOException e) {
            logger.error("Ошибка ввода/вывода при загрузке матрицы из файла {}: {}", file.getAbsolutePath(), e.getMessage(), e);
            throw new IOException("Ошибка чтения файла матрицы.", e); //  Добавлено исключение
        }
    }
}