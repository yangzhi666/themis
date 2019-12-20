package io.themis.store.impl;

import io.themis.metadata.TaskMetaData;
import io.themis.store.TaskProcessStore;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: themis
 * @description: mysql下的数据持久化实现
 * @author: yangzhi
 * @create: 2019-12-19 17:20
 */
@Service
public class MysqlTaskProcessStore extends TaskProcessStore {
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入整条链路的节点信息
     *
     * @param list
     */
    @Override
    public void insertAllTask(final List<TaskMetaData> list) {
        jdbcTemplate.batchUpdate(
                "INSERT INTO task_meta_data(chain_id, task_id, handler_slot, preTask_Id, next_task_id, completed_flag, content) VALUES (?, ?, ?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, list.get(i).getChainId());
                        ps.setString(2, list.get(i).getTaskId());
                        ps.setString(3, list.get(i).getHandlerSlot());
                        ps.setString(4, list.get(i).getPreTaskId());
                        ps.setString(5, list.get(i).getNextTaskId());
                        ps.setInt(6, list.get(i).getCompletedFlag());
                        ps.setString(7, list.get(i).getContent());
                    }

                    @Override
                    public int getBatchSize() {
                        return list.size();
                    }
                });
    }
}