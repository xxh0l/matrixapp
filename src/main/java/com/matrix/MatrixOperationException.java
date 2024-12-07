package com.matrix;

/**
 * Класс, представляющий исключение, возникающее при выполнении операций над матрицами.
 *
 * Этот класс расширяет стандартный класс Exception и используется для обработки ошибок,
 * специфичных для операций с матрицами, таких как несовместимые размерности,
 * попытка вычисления определителя неквадратной матрицы и т.д.
 */
public class MatrixOperationException extends Exception {
    /**
     * Конструктор исключения MatrixOperationException.
     *
     * @param message Сообщение об ошибке, которое будет связано с этим исключением.
     */
    public MatrixOperationException(String message) {
        super(message);
    }
}