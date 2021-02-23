package com.user.Exception;

/**
 * FEBS系统内部异常
 *
 * @author MrBird
 */
public class YdyException extends RuntimeException  {

    private static final long serialVersionUID = -994962710559017255L;

    public YdyException(String message) {
        super(message);
    }
}
