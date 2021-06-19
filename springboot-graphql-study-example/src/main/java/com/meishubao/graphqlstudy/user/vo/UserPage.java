package com.meishubao.graphqlstudy.user.vo;

import cn.hutool.core.util.PageUtil;
import com.meishubao.graphqlstudy.common.response.Page;
import com.meishubao.graphqlstudy.user.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author lilu
 */
@Data
public class UserPage extends Page {

    /** 内容 **/
    List<User> content;

    @Override
    public UserPage build(Integer number, Integer size, Integer totalElements, List content) {
        super.fill(number, totalElements, PageUtil.totalPage(totalElements, size));
        this.content = content;
        return this;
    }

}
