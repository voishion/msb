package com.meishubao.utils.spring;

import com.google.common.collect.Lists;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author lilu
 */
public class AssertUtilsTest {

    public static void main(String[] args) {
        // 要求参数必须为非空（Not Null），否则抛出异常，不予放行
        Assert.notNull(new Object(), "参数必须为非空");

        // 要求参数必须空（Null），否则抛出异常，不予放行。
        Assert.isNull(null, "参数必须空");

        // 要求参数必须为真（True），否则抛出异常，不予『放行』。
        Assert.isTrue(true, "参数必须为真");

        // 要求参数（List/Set）必须非空（Not Empty），否则抛出异常，不予放行
        Assert.notEmpty(Lists.newArrayList("1"), "List不能为空");

        // 要求参数（String）必须有长度（即，Not Empty），否则抛出异常，不予放行
        Assert.hasLength(" ", "要求参数（String）必须有长度");

        // 要求参数（String）必须有内容（即，Not Blank），否则抛出异常，不予放行
        Assert.hasText("1", "要求参数（String）必须有内容");

        // 要求参数是指定类型的实例，否则抛出异常，不予放行
        Assert.isInstanceOf(String.class, "2", "必须为String类型");

        // 要求参数 `subType` 必须是参数 superType 的子类或实现类，否则抛出异常，不予放行
        Assert.isAssignable(List.class, Lists.newArrayList().getClass(), "必须是List的子类或实现类");
    }

}
