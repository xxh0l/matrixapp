package com.matrix;

import com.matrix.interfaces.iBaseMatrix;

/**
 * Базовый класс для представления матрицы.
 * Содержит основные свойства матрицы (строки, столбцы, данные) и общие методы для работы с ней.
 */
public class BaseMatrix implements iBaseMatrix {
    protected double[][] data;
    protected final int rows;
    protected final int cols;

    /**
     * Конструктор для создания матрицы заданных размеров, заполненной нулями.
     *7
     * @param rows Количество строк в матрице.
     * @param cols Количество столбцов в матрице.
     */
    public BaseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    /**
     * Конструктор для создания матрицы из существующего двумерного массива.
     *
     * @param data Двумерный массив, содержащий данные для матрицы.
     *             Важно: предполагается, что все строки в массиве data имеют одинаковую длину.
     */
    public BaseMatrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, cols);
        }
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public double[][] getData() {
        return data;
    }

    @Override
    public void checkDimensions(BaseMatrix other, String operation) throws MatrixOperationException {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new MatrixOperationException("Для " + operation + " матрицы должны иметь одинаковую размерность.");
        }
    }

    @Override
    public Matrix createMatrixFromData(double[][] data) {
        return new Matrix(data);
    }
}
