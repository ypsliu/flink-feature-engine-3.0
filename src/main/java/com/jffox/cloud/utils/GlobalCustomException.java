package com.jffox.cloud.utils;

/**
 * Created by Frank on 2017/1/17.
 */
public class GlobalCustomException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GlobalCustomException(GlobalConf.ErrorType type) {
        this(type,null);
    }

    public GlobalCustomException(GlobalConf.ErrorType type, Throwable cause) {
        super(type.name() + ":" + type.getDesc(), cause);
    }
}
