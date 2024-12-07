package com.matrix.interfaces;

import com.matrix.Matrix;
import com.matrix.MatrixOperationException;

/**
 * Функциональный интерфейс для операций над матрицами.
 */
public interface iMatrixOperation {
    /**
     * Применяет операцию к двум матрицам.
     *
     * @param m1 Первая матрица.
     * @param m2 Вторая матрица.
     * @return Результирующая матрица.
     * @throws MatrixOperationException Если операция не может быть выполнена (например, несовместимые размеры матриц).
     */
    Matrix apply(Matrix m1, Matrix m2) throws MatrixOperationException;
}
