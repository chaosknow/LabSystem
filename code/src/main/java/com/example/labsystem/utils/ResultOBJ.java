package com.example.labsystem.utils;

import lombok.Data;

@Data
public class ResultOBJ {
    private Integer code;
    private String msg;
    private Object data;

    public ResultOBJ(Integer code) {
        this.code = code;
    }

    public ResultOBJ(Integer code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public ResultOBJ(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static final ResultOBJ ADD_SUCCESS = new ResultOBJ(SYSConsstant.CODE_SUCCESS, SYSConsstant.ADD_SUCCESS);
    public static final ResultOBJ ADD_ERROR = new ResultOBJ(SYSConsstant.CODE_ERROR, SYSConsstant.ADD_ERROR);

    public static final ResultOBJ UPDATE_SUCCESS = new ResultOBJ(SYSConsstant.CODE_SUCCESS, SYSConsstant.UPDATE_SUCCESS);
    public static final ResultOBJ UPDATE_ERROR = new ResultOBJ(SYSConsstant.CODE_ERROR, SYSConsstant.UPDATE_ERROR);

    public static final ResultOBJ DEL_SUCCESS = new ResultOBJ(SYSConsstant.CODE_SUCCESS, SYSConsstant.DEL_SUCCESS);
    public static final ResultOBJ DEL_ERROR = new ResultOBJ(SYSConsstant.CODE_ERROR, SYSConsstant.DEL_ERROR);

    public static final ResultOBJ LOGIN_SUCCESS = new ResultOBJ(SYSConsstant.CODE_SUCCESS, SYSConsstant.LOGIN_SUCCESS);
    public static final ResultOBJ LOGIN_ERROR = new ResultOBJ(SYSConsstant.CODE_ERROR, SYSConsstant.LOGIN_ERROR);
}
