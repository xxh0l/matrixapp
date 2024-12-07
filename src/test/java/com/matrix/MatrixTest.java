package com.matrix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    private static final Logger logger = LogManager.getLogger(MatrixTest.class);


    @Test
    void testConstructorWithDimensions() {
        Matrix matrix = new Matrix(2, 3);
        assertEquals(2, matrix.getRows());
        assertEquals(3, matrix.getCols());
        logger.info("Тест testConstructorWithDimensions выполнен.");
    }

    @Test
    void testConstructorWithData() {
        double[][] data = {{1, 2}, {3, 4}};
        Matrix matrix = new Matrix(data);
        assertArrayEquals(data, matrix.getData());
        logger.info("Тест testConstructorWithData выполнен.");
    }

    @Test
    void testAdd() throws MatrixOperationException {
        Matrix matrix1 = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix matrix2 = new Matrix(new double[][]{{5, 6}, {7, 8}});
        Matrix result = matrix1.add(matrix2);
        assertArrayEquals(new double[][]{{6, 8}, {10, 12}}, result.getData());
        logger.info("Тест testAdd выполнен.");
    }

    @Test
    void testAddInvalidDimensions() {
        Matrix matrix1 = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix matrix2 = new Matrix(new double[][]{{1, 2, 3}});
        assertThrows(MatrixOperationException.class, () -> matrix1.add(matrix2));
        logger.info("Тест testAddInvalidDimensions выполнен.");
    }

    @Test
    void testSubtract() throws MatrixOperationException {
        Matrix matrix1 = new Matrix(new double[][]{{5, 6}, {7, 8}});
        Matrix matrix2 = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix result = matrix1.subtract(matrix2);
        assertArrayEquals(new double[][]{{4, 4}, {4, 4}}, result.getData());
        logger.info("Тест testSubtract выполнен.");
    }

    @Test
    void testSubtractInvalidDimensions() {
        Matrix matrix1 = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix matrix2 = new Matrix(new double[][]{{1, 2, 3}});
        assertThrows(MatrixOperationException.class, () -> matrix1.subtract(matrix2));
        logger.info("Тест testSubtractInvalidDimensions выполнен.");
    }

    @Test
    void testMultiplyMatrices() throws MatrixOperationException {
        Matrix matrix1 = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix matrix2 = new Matrix(new double[][]{{5, 6}, {7, 8}});
        Matrix result = matrix1.multiply(matrix2);
        assertArrayEquals(new double[][]{{19, 22}, {43, 50}}, result.getData());
        logger.info("Тест testMultiplyMatrices выполнен.");
    }

    @Test
    void testMultiplyMatricesInvalidDimensions() {
        Matrix matrix1 = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix matrix2 = new Matrix(new double[][]{{1}, {2}, {3}});
        assertThrows(MatrixOperationException.class, () -> matrix1.multiply(matrix2));
        logger.info("Тест testMultiplyMatricesInvalidDimensions выполнен.");
    }


    @Test
    void testMultiplyScalar() throws MatrixOperationException {
        Matrix matrix1 = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix matrix2 = new Matrix(new double[][]{{4, 3}, {2, 1}});
        Matrix result = matrix1.multiply(matrix2);
        assertArrayEquals(new double[][]{{8, 5}, {20, 13}}, result.getData());
        logger.info("Тест testMultiplyScalar выполнен.");
    }

    @Test
    void testDeterminant() throws MatrixOperationException {
        Matrix matrix = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double determinant = matrix.determinant();
        assertEquals(-2.0, determinant);
        logger.info("Тест testDeterminant выполнен.");
    }

    @Test
    void testDeterminantOneByOne() throws MatrixOperationException {
        Matrix matrix = new Matrix(new double[][]{{5}});
        double determinant = matrix.determinant();
        assertEquals(5.0, determinant);
        logger.info("Тест testDeterminantOneByOne выполнен.");
    }

    @Test
    void testDeterminantThreeByThree() throws MatrixOperationException {
        Matrix matrix = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        double determinant = matrix.determinant();
        assertEquals(0.0, determinant);
        logger.info("Тест testDeterminantThreeByThree выполнен.");
    }

    @Test
    void testDeterminantNonSquareMatrix() {
        Matrix matrix = new Matrix(new double[][]{{1, 2}, {3, 4}, {5, 6}});
        assertThrows(MatrixOperationException.class, matrix::determinant);
        logger.info("Тест testDeterminantNonSquareMatrix выполнен.");
    }

    @Test
    void testSubMatrix() {
        Matrix matrix = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix subMatrix = matrix.subMatrix(1, 1);
        assertArrayEquals(new double[][]{{1, 3}, {7, 9}}, subMatrix.getData());
        logger.info("Тест testSubMatrix выполнен.");
    }

    @Test
    void testSubMatrixEdgeCases() {
        Matrix matrix = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix subMatrix1 = matrix.subMatrix(0, 0);
        assertArrayEquals(new double[][]{{5, 6}, {8, 9}}, subMatrix1.getData());
        Matrix subMatrix2 = matrix.subMatrix(2, 2);
        assertArrayEquals(new double[][]{{1, 2}, {4, 5}}, subMatrix2.getData());
        logger.info("Тест testSubMatrixEdgeCases выполнен.");
    }
}