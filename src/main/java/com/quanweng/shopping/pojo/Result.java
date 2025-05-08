package com.quanweng.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

        /*
         * 前后端交互统一响应结果
         * code 响应码
         * msg 响应信息
         * data 返回数据
         * */
        private Integer code;
        private String msg;
        private Object data;
        public static Result success(){
            return new Result(1,"success",null);
        }
        public static Result success(Object data){
            return new Result(1,"success",data);
        }
        public static Result error(String msg){
            return new Result(0,msg,null);
        }

}
