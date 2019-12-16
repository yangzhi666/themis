package io.themis.engine.impl;

import io.themis.engine.DispatcherEngine;
import io.themis.metadata.TaskMetaData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: themis
 * @description: 任务调度器实现
 * @author: yangzhi
 * @create: 2019-12-16 16:08
 */
@Component
public class TaskDispatcherEngine implements DispatcherEngine {


    /**
     * 创建任务责任链，并且返回第一个任务
     *
     * @param taskList
     * @return
     */
    @Override
    public void createChain(List<TaskMetaData> taskList) {

    }

    /**
     * 输入任务完成信号
     *
     * @param metaData 任务元信息
     */
    @Override
    public void receivedCompletedSignal(TaskMetaData metaData) {

    }

    /**
     * 指派任务
     *
     * @param metaData 任务元信息
     */
    @Override
    public void sendTask(TaskMetaData metaData) {

    }

    /**
     * 获取任务责任链下一个任务
     *
     * @param metaData
     * @return
     */
    @Override
    public TaskMetaData getNextTask(TaskMetaData metaData) {

        return null;
    }
}