package com.matrix.interfaces;

import com.matrix.BaseMatrix;
import com.matrix.Matrix;
import com.matrix.MatrixOperationException;

/**
 * Интерфейс для базовой матрицы.  Представляет общую функциональность для всех матриц.
 */
public interface iBaseMatrix {

    /**
     * Возвращает количество строк в матрице.
     * @return Количество строк.
     */
    int getRows();

    /**
     * Возвращает количество столбцов в матрице.
     * @return Количество столбцов.
     */
    int getCols();

    /**
     * Возвращает двумерный массив, содержащий данные матрицы.
     * @return Двумерный массив с данными.
     */
    double[][] getData();


    /**
     * Проверяет совместимость размерностей двух матриц для заданной операции.
     *
     * @param other    Другая матрица для сравнения размерностей.
     * @param operation Название операции, для которой выполняется проверка (например, "сложение", "умножение").
     * @throws MatrixOperationException Если размерности матриц несовместимы для данной операции.
     */
    void checkDimensions(BaseMatrix other, String operation) throws MatrixOperationException;




    /**
     * Создает новую матрицу из заданного двумерного массива данных.
     * @param data Двумерный массив с данными для новой матрицы.
     * @return Новая матрица, содержащая заданные данные.
     */
    Matrix createMatrixFromData(double[][] data);
}