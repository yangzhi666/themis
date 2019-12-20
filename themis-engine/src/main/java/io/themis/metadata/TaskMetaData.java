package io.themis.metadata;

/**
 * @program: themis
 * @description: 任务描述元数据
 * @author: yangzhi
 * @create: 2019-12-16 16:14
 */
public class TaskMetaData {

    /**
     * 整条任务链ID
     */
    private String chainId;

    /**
     * 当前任务节点ID
     */
    private String taskId;

    /**
     * 任务处理器的描述
     */
    private String handlerSlot;

    /**
     * 前一个任务节点ID
     */
    private String preTaskId;

    /**
     * 后一个任务节点ID
     */
    private String nextTaskId;

    /**
     * 当前节点任务是否已经完成(0,1)
     */
    private Integer completedFlag;

    /**
     * 任务内容描述
     */
    private String content;

    public TaskMetaData() {
    }

    public TaskMetaData(String chainId, String taskId, String handlerSlot, String preTaskId, String nextTaskId, Integer completedFlag, String content) {
        this.chainId = chainId;
        this.taskId = taskId;
        this.handlerSlot = handlerSlot;
        this.preTaskId = preTaskId;
        this.nextTaskId = nextTaskId;
        this.completedFlag = completedFlag;
        this.content = content;
    }

    public String getChainId() {
        return chainId;
    }

    public TaskMetaData setChainId(String chainId) {
        this.chainId = chainId;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public TaskMetaData setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getPreTaskId() {
        return preTaskId;
    }

    public TaskMetaData setPreTaskId(String preTaskId) {
        this.preTaskId = preTaskId;
        return this;
    }

    public String getNextTaskId() {
        return nextTaskId;
    }

    public TaskMetaData setNextTaskId(String nextTaskId) {
        this.nextTaskId = nextTaskId;
        return this;
    }

    public Integer getCompletedFlag() {
        return completedFlag;
    }

    public TaskMetaData setCompletedFlag(Integer completedFlag) {
        this.completedFlag = completedFlag;
        return this;
    }

    public String getContent() {
        return content;
    }

    public TaskMetaData setContent(String content) {
        this.content = content;
        return this;
    }

    public String getHandlerSlot() {
        return handlerSlot;
    }

    public TaskMetaData setHandlerSlot(String handlerSlot) {
        this.handlerSlot = handlerSlot;
        return this;
    }

    /**
     * 任务具体内容描述
     */
    class ContentMetaData {

        /**
         *  任务分发协议方式
         */
        private DistributeType distributeType = DistributeType.MQ;

        /**
         * 任务内容
         * 目前只支持字符串这一种序列化方式
         */
        private String content;

        public ContentMetaData(String content) {
            this.content = content;
        }

        public ContentMetaData(DistributeType distributeType, String content) {
            this.distributeType = distributeType;
            this.content = content;
        }

        public DistributeType getDistributeType() {
            return distributeType;
        }

        public ContentMetaData setDistributeType(DistributeType distributeType) {
            this.distributeType = distributeType;
            return this;
        }

        public String getContent() {
            return content;
        }

        public ContentMetaData setContent(String content) {
            this.content = content;
            return this;
        }
    }

    /**
     * 任务分发协议方式
     */
    enum DistributeType{
        MQ(1, "消息队列");

        private Integer code;

        private String dec;

        DistributeType(Integer code, String dec) {
            this.code = code;
            this.dec = dec;
        }

        public Integer getCode() {
            return code;
        }

        public DistributeType setCode(Integer code) {
            this.code = code;
            return this;
        }

        public String getDec() {
            return dec;
        }

        public DistributeType setDec(String dec) {
            this.dec = dec;
            return this;
        }
    }
}