package com.matrix.interfaces;

import com.matrix.Matrix;
import com.matrix.MatrixOperationException;

/**
 * Интерфейс для загрузчика матриц из файла.
 */
public interface iMatrixLoader {
    /**
     * Загружает матрицу из указанного файла.
     *
     * @param file Файл, из которого нужно загрузить матрицу.
     * @return Загруженная матрица.
     * @throws java.io.IOException           Если произошла ошибка чтения файла.
     * @throws MatrixOperationException      Если данные в файле не соответствуют формату матрицы или если матрица некорректна (например, не прямоугольная).
     */
    Matrix loadFromFile(java.io.File file) throws java.io.IOException, MatrixOperationException;
}