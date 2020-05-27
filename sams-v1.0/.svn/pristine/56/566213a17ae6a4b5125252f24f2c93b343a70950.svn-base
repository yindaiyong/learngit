package com.sams.batchfile.common;


import java.util.Map;

/**
 * 任务处理接口
 * 具体业务逻辑可实现该接口
 *  T 返回值类型
 *  E 传入值类型
 * ITask<BR>
 *
 */
public interface ITask<T, E> {
    
    /**
     * 
     * 任务执行方法接口<BR>
     * 方法名：execute<BR>
     * @param e 传入对象
     * @param params 其他辅助参数
     * @return T<BR> 返回值类型
     * @exception <BR>
     * @since  2.0
     */
    T execute(String e, Map<String, Object> params);
}
