package io.themis.engine.builder;

import io.themis.metadata.TaskMetaData;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: themis
 * @description: 任务元数据信息builder
 * @author: yangzhi
 * @create: 2019-12-20 17:17
 */
public class TaskMetaBuilder {
    private List<NodeInfo> nodeInfoList = new ArrayList<>();

    /**
     * addNode
     * @param handlerSlot
     */
    public TaskMetaBuilder addNode(String handlerSlot) {
        addNode(handlerSlot, null);
        return this;
    }

    /**
     * addNode
     * @param handlerSlot
     * @param content
     */
    public TaskMetaBuilder addNode(String handlerSlot, String content) {
        nodeInfoList.add(new NodeInfo(handlerSlot, content));
        return this;
    }

    /**
     * build
     * @return
     */
    public List<TaskMetaData> build () {
        if (CollectionUtils.isEmpty(nodeInfoList)) {
            throw new IllegalArgumentException("no any task nodes！");
        }
        // chainId
        String chainId = UUID.randomUUID().toString();
        // 构建任务链元信息
        List<TaskMetaData> metaData = new ArrayList<>();
        TaskMetaData lastTaskMetaData = null;
        for (NodeInfo nodeInfo : nodeInfoList) {
            TaskMetaData taskMetaData = new TaskMetaData()
                    .setChainId(chainId)
                    .setTaskId(UUID.randomUUID().toString())
                    .setHandlerSlot(nodeInfo.handlerSlot)
                    .setCompletedFlag(0)
                    .setContent(nodeInfo.content);
            if (lastTaskMetaData == null) {
                taskMetaData.setPreTaskId(null);
            } else {
                taskMetaData.setPreTaskId(lastTaskMetaData.getTaskId());
                lastTaskMetaData.setNextTaskId(taskMetaData.getTaskId());
            }
            lastTaskMetaData = taskMetaData;
            // add
            metaData.add(taskMetaData);
        }
        return metaData;
    }

    class NodeInfo {
        private String handlerSlot;

        private String content;

        public NodeInfo(String handlerSlot, String content) {
            this.handlerSlot = handlerSlot;
            this.content = content;
        }
    }
}