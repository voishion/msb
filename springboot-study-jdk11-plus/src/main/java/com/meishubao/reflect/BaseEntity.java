package com.meishubao.reflect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * BaseEntity
 *
 * @author lilu
 */
@Data
@Log4j2
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseEntity implements Serializable {

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    protected BaseEntity insert() {
        setCreateTime(LocalDateTime.now());
        return this;
    }

    protected BaseEntity update() {
        setUpdateTime(LocalDateTime.now());
        return this;
    }

    protected <T> T clone(Class<T> targetClass) {
        if (this.getClass().equals(targetClass)) {
            return (T) this;
        }
        T target;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
        BeanUtils.copyProperties(this, target);
        return target;
    }

    protected <T> T append(Object append) {
        BeanUtils.copyProperties(append, this, loadNullPropertyNames(append));
        return (T) this;
    }

    private String[] loadNullPropertyNames(Object source) {
        Set<String> names = Sets.newHashSet();
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        for (PropertyDescriptor descriptor : beanWrapper.getPropertyDescriptors()) {
            Object value = beanWrapper.getPropertyValue(descriptor.getName());
            if (value == null) {
                names.add(descriptor.getName());
            }
        }
        return names.toArray(new String[0]);
    }

}
