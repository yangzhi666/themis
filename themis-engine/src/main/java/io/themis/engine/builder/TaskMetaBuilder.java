package io.themis.engine.builder;

import io.themis.metadata.TaskMetaData;

import java.util.ArrayList;
import java.util.List;

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
    public void addNode(String handlerSlot) {
        addNode(handlerSlot, null);
    }

    /**
     * addNode
     * @param handlerSlot
     * @param content
     */
    public void addNode(String handlerSlot, String content) {
        nodeInfoList.add(new NodeInfo(handlerSlot, content));
    }

    /**
     * build
     * @return
     */
    public List<TaskMetaData> build () {


        return null;
    }

    class NodeInfo {
        private String handlerSlot;

        private String content;

        public NodeInfo(String handlerSlot, String content) {
            this.handlerSlot = handlerSlot;
            this.content = content;
        }

        public String getHandlerSlot() {
            return handlerSlot;
        }

        public NodeInfo setHandlerSlot(String handlerSlot) {
            this.handlerSlot = handlerSlot;
            return this;
        }
    }
}