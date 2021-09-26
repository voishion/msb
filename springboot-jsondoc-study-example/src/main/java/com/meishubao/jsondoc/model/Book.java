package com.meishubao.jsondoc.model;

import lombok.*;
import com.meishubao.jsondoc.documentation.DocumentationConstants;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ApiObject(name = "Book", group = DocumentationConstants.GROUP_LIBRARY, description = "代表一本书，每本书都有一个作者<code>Author</code>和相应的价格")
public class Book {

	@ApiObjectField(description = "编号", order = 4)
	private Long id;

	@ApiObjectField(description = "标题", order = 2)
	private String title;

	@ApiObjectField(description = "作者", order = 1)
	private Author author;

	@ApiObjectField(required = true, format = "必须是double类型", description = "价格")
	private Double price;
}
