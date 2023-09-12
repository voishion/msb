package com.meishubao.java8.tree;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lilu
 */
public class TreeTest {

    public static void main(String[] args) {
        List<PtDept> list = new ArrayList<>();
        list.add(new PtDept(1, 0, "A1"));
        list.add(new PtDept(2, 1, "A2"));
        list.add(new PtDept(20, 1, "A20", 2));
        list.add(new PtDept(21, 1, "A21", 1));
        list.add(new PtDept(22, 1, "A22", 0));
        list.add(new PtDept(3, 2, "A3"));
        list.add(new PtDept(4, 3, "A4"));
        list.add(new PtDept(5, 4, "A5"));

        TreeNodeConfig config = new TreeNodeConfig();

        List<Tree<Integer>> treeList = TreeUtil.build(list, 0, config, (object, tree) -> {
            tree.setId(object.id);
            tree.setParentId(object.pid);
            tree.putExtra("name", object.getName());
        });
        System.out.println(JSON.toJSONString(treeList));

        config.setIdKey("id");//默认为id可以不设置
        config.setParentIdKey("pid");//默认为parentId可以不设置
        config.setWeightKey("priority");//排序字段
        config.setDeep(Integer.MAX_VALUE);//最大递归深度
        treeList = TreeUtil.build(list, 0, config, (object, tree) -> {
            tree.putAll(BeanUtil.beanToMap(object));
        });
        String jsonString = JSON.toJSONString(treeList);
        System.out.println(jsonString);

        list = buildTree(list, 0);
        jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);

        list = convertTreeToList(list);
        list = buildTree1(list, 0);
        jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);

        list = JSON.parseArray(jsonString, PtDept.class);
        System.out.println(JSON.toJSONString(list));

        eachTree(list);
        System.out.println(JSON.toJSONString(list));
    }

    private static List<PtDept> convertTreeToList(List<PtDept> list) {
        List<PtDept> target = new ArrayList<>();
        convertTreeToList(target, list);
        return target;
    }

    private static void convertTreeToList(List<PtDept> target, List<PtDept> source) {
        for (PtDept dept : source) {
            target.add(dept);
            if (CollUtil.isNotEmpty(dept.getChildren())) {
                List<PtDept> children = new ArrayList<>(dept.getChildren());
                dept.getChildren().clear();
                convertTreeToList(target, children);
            }
        }
    }

    /**
     * 构建树型结构
     *
     * @param list 列表
     * @param rootId 最顶层父ID值
     * @return 返回树型结构
     */
    private static List<PtDept> buildTree(List<PtDept> list, Serializable rootId) {
        Map<Integer, List<PtDept>> parentIdMap = new HashMap<>();
        list.forEach(item -> {
            List<PtDept> children = parentIdMap.getOrDefault(item.getPid(), new ArrayList<>());
            children.add(item);
            parentIdMap.put(item.getPid(), children);
        });
        list.forEach(item -> item.setChildren(parentIdMap.getOrDefault(item.getId(), new ArrayList<>()).stream().sorted(Comparator.comparing(PtDept::getPriority)).collect(Collectors.toList())));
        return list.stream()
                .filter(v -> Objects.equals(rootId, v.getPid()))
                .sorted(Comparator.comparing(PtDept::getPriority))
                .collect(Collectors.toList());
    }

    /**
     * 构建树型结构【推荐方式】
     *
     * @param list 列表
     * @param rootId 最顶层父ID值
     * @return 返回树型结构
     */
    private static List<PtDept> buildTree1(List<PtDept> list, Serializable rootId) {
        Map<Integer, List<PtDept>> parentIdMap = list.stream().collect(Collectors.groupingBy(PtDept::getPid));
        list.forEach(item -> item.setChildren(parentIdMap.getOrDefault(item.getId(), new ArrayList<>()).stream().sorted(Comparator.comparing(PtDept::getPriority)).collect(Collectors.toList())));
        return list.stream().filter(v -> Objects.equals(rootId, v.getPid())).sorted(Comparator.comparing(PtDept::getPriority)).collect(Collectors.toList());
    }

    private static void eachTree(List<PtDept> list) {
        for (PtDept dept : list) {
            dept.setName(dept.getName() + ".E");
            if (CollUtil.isNotEmpty(dept.getChildren())) {
                eachTree(dept.getChildren());
            }
        }
    }

    @Data
    @NoArgsConstructor
    private static class PtDept {
        private Integer id;
        private Integer pid;
        private String name;
        private Integer priority;
        private List<PtDept> children = new ArrayList<>();

        public PtDept(Integer id, Integer pid, String name) {
            this(id, pid, name, 0);
        }
        public PtDept(Integer id, Integer pid, String name, Integer priority) {
            this.id = id;
            this.pid = pid;
            this.name = name;
            this.priority = priority;
        }
    }

}
