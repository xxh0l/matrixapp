package com.matrix;

import com.matrix.interfaces.iMatrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс, представляющий матрицу и реализующий операции над ней.
 */
public class Matrix extends BaseMatrix implements iMatrix {
    private static final Logger logger = LogManager.getLogger(Matrix.class);

    public Matrix(int rows, int cols) {
        super(rows, cols);
    }

    public Matrix(double[][] data) {
        super(data);
    }

    @Override
    public Matrix add(Matrix other) throws MatrixOperationException {
        checkDimensions(other, "сложения");

        double[][] resultData = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultData[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        logger.info("Операция сложения матриц выполнена.");
        return createMatrixFromData(resultData);
    }

    @Override
    public Matrix subtract(Matrix other) throws MatrixOperationException {
        checkDimensions(other, "вычитания");

        double[][] resultData = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultData[i][j] = this.data[i][j] - other.data[i][j];
            }
        }
        logger.info("Операция вычитания матриц выполнена.");
        return createMatrixFromData(resultData);
    }

    @Override
    public Matrix multiply(Matrix other) throws MatrixOperationException {
        if (this.cols != other.rows) {
            throw new MatrixOperationException("Число столбцов первой матрицы должно совпадать с числом строк второй матрицы.");
        }

        double[][] resultData = new double[this.rows][other.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < this.cols; k++) {
                    resultData[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        logger.info("Операция умножения матриц выполнена.");
        return createMatrixFromData(resultData);
    }

    @Override
    public double determinant() throws MatrixOperationException {
        if (rows != cols) {
            throw new MatrixOperationException("Матрица должна быть квадратной.");
        }

        if (rows == 1) {
            return data[0][0];
        }

        if (rows == 2) {
            return data[0][0] * data[1][1] - data[0][1] * data[1][0];
        }

        double det = 0;
        for (int i = 0; i < cols; i++) {
            det += Math.pow(-1, i) * data[0][i] * subMatrix(0, i).determinant();
        }
        logger.info("Определитель матрицы вычислен.");
        return det;
    }

    protected Matrix subMatrix(int row, int col) {
        double[][] subData = new double[rows - 1][cols - 1];
        int subRow = 0;
        for (int i = 0; i < rows; i++) {
            if (i == row) {
                continue;
            }
            int subCol = 0;
            for (int j = 0; j < cols; j++) {
                if (j == col) {
                    continue;
                }
                subData[subRow][subCol] = data[i][j];
                subCol++;
            }
            subRow++;
        }
        return createMatrixFromData(subData);
    }
}