package com.aadhk.library.rx;

import java.io.Serializable;

/**
 * Created by jack on 09/12/2016.
   封装服务器返回的数据
 "code":"1",
 "msg":"查询成功",
 "data":[
         {
         "id":"1001",
         "name":"jack",
         "tel":"121312321"
         },
         {
         "id":"1001",
         "name":"jack",
         "tel":"121312321"
         }
        ............
 ]
     code: 判定是否成功，如果是false则找到msg
     msg: 如果操作失败返回的提示信息
     data: 正常操作返回的数据

     -1 表示需要登录
     0  表示无数据
     1  表示正常数据返回
 */

public class Response<T> implements Serializable {
    public String code;
    public String msg;
    public T data;

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean success(){ return code.equals("1"); }

    public boolean login(){ return code.equals("-1"); }

    public boolean fail() {return code.equals("0");}

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
