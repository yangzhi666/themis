package io.themis.store;

import io.themis.metadata.TaskMetaData;

import java.util.List;

/**
 * @program: themis
 * @description: 任务处理数据库持久化接口
 * @author: yangzhi
 * @create: 2019-12-19 17:09
 */
public abstract class TaskProcessStore {

    /**
     * 插入整条链路的节点信息
     * @param list
     */
    public abstract void insertAllTask(List<TaskMetaData> list);


    
}
