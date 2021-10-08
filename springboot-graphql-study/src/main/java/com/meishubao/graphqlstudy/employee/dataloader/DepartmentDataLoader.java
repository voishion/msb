package com.meishubao.graphqlstudy.employee.dataloader;

import com.meishubao.graphqlstudy.common.dataloader.DataLoaderKey;
import com.meishubao.graphqlstudy.common.dataloader.DataLoaderWrapper;
import com.meishubao.graphqlstudy.employee.entity.Department;
import com.meishubao.graphqlstudy.employee.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.BatchLoaderEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * @author lilu
 */
@Slf4j
@Component
public class DepartmentDataLoader extends DataLoaderWrapper<Integer, Department> {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public DataLoaderKey getKey() {
        return DataLoaderKey.Department;
    }

    @Override
    public CompletionStage<Map<Integer, Department>> load(Set<Integer> keys, BatchLoaderEnvironment environment) {
        log.info("load:{}", keys);
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.departmentService.list(keys).stream().collect(Collectors.toMap(item -> item.getId(), item -> item));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return null;
        }, dataLoaderThreadPoolTaskExecutor);
    }

}
