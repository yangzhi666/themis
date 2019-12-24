package io.themis.engine.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.themis.engine.DispatcherEngine;
import io.themis.metadata.TaskMetaData;
import io.themis.store.impl.MysqlTaskProcessStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program: themis
 * @description: 任务调度器实现
 * @author: yangzhi
 * @create: 2019-12-16 16:08
 */
@Component
public class TaskDispatcherEngine implements DispatcherEngine {
    @Autowired
    private MysqlTaskProcessStore taskProcessStore;
    @Resource
    private MqSignalMachine mqSignalMachine;


    /**
     * 创建任务责任链，并且返回第一个任务
     *
     * @param taskList
     * @return
     */
    @Override
    public void createChain(List<TaskMetaData> taskList) {
        taskProcessStore.insertAllTask(taskList);
    }

    /**
     * 输入任务完成信号
     *
     * @param metaData 任务元信息
     */
    @Override
    public void receivedCompletedSignal(TaskMetaData metaData) {
        // receivedCompletedSignal
        taskProcessStore.updateCompletedByTaskId(metaData.getTaskId());
        // 指派下一个任务节点
        TaskMetaData nextTask = this.getNextTask(metaData);
        if (null != nextTask) {
            this.sendTask(nextTask);
        }
    }

    /**
     * 指派任务
     *
     * @param metaData 任务元信息
     */
    @Override
    public void sendTask(TaskMetaData metaData) {
        mqSignalMachine.send(metaData.getHandlerSlot(), JSON.parseObject(metaData.getContent(), new TypeReference<Map<String, Object>>(){}));
    }

    /**
     * 获取任务责任链下一个任务
     *
     * @param metaData
     * =@return
     */
    @Override
    public TaskMetaData getNextTask(TaskMetaData metaData) {
        return taskProcessStore.getNextTask(metaData.getTaskId());
    }
}