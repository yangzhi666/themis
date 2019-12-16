package io.themis.engine;

import io.themis.metadata.TaskMetaData;

import java.util.List;

/**
 * @program: themis
 * @description: 核心调度器接口定义
 * @author: yangzhi
 * @create: 2019-12-16 15:47
 */
public interface DispatcherEngine {

    /**
     * 创建任务责任链，并且返回第一个任务
     * @param taskList
     * @return
     */
    public void createChain(List<TaskMetaData> taskList);

    /**
     * 接受任务完成信号
     * @param metaData 任务元信息
     */
    public void receivedCompletedSignal(TaskMetaData metaData);

    /**
     * 指派任务
     * @param metaData 任务元信息
     */
    public void sendTask(TaskMetaData metaData);

    /**
     * 获取任务责任链下一个任务
     * @param metaData
     * @return
     */
    public TaskMetaData getNextTask(TaskMetaData metaData);





}