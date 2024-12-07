package com.matrix.interfaces;

import com.matrix.MatrixOperationException;
import com.matrix.Matrix;

/**
 * Интерфейс, определяющий методы для работы с матрицами.
 * Расширяет интерфейс {@link iBaseMatrix}.
 */
public interface iMatrix extends iBaseMatrix {
    /**
     * Складывает текущую матрицу с другой матрицей.
     *
     * @param other Матрица, которая будет добавлена к текущей.
     * @return Новая матрица, являющаяся результатом сложения.
     * @throws MatrixOperationException Если матрицы несовместимы для сложения (разные размерности).
     */
    Matrix add(Matrix other) throws MatrixOperationException;



    /**
     * Вычитает другую матрицу из текущей.
     *
     * @param other Матрица, которая будет вычтена из текущей.
     * @return Новая матрица, являющаяся результатом вычитания.
     * @throws MatrixOperationException Если матрицы несовместимы для вычитания (разные размерности).
     */
    Matrix subtract(Matrix other) throws MatrixOperationException;


    /**
     * Умножает текущую матрицу на другую матрицу.
     *
     * @param other Матрица, на которую будет умножена текущая.
     * @return Новая матрица, являющаяся результатом умножения.
     * @throws MatrixOperationException Если матрицы несовместимы для умножения (число столбцов первой матрицы не равно числу строк второй матрицы).
     */
    Matrix multiply(Matrix other) throws MatrixOperationException;


    /**
     * Вычисляет определитель матрицы.
     *
     * @return Значение определителя.
     * @throws MatrixOperationException Если матрица не квадратная.
     */
    double determinant() throws MatrixOperationException;

}