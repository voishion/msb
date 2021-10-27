package com.meishubao.jsondoc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.meishubao.jsondoc.documentation.DocumentationConstants;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "books")
@EqualsAndHashCode(of = "name")
@ApiObject(name = "Author", group = DocumentationConstants.GROUP_LIBRARY, description = "代表了一个作家，每隔作家都有一个书本<code>Book</code>清单")
public class Author {

	@ApiObjectField(format = "AU-0001", description = "作者编号", order = 1)
	private Long id;

	@ApiObjectField(required = true, description = "姓名[0-美术, 1-写字]", allowedvalues = {"0", "1"}, order = 2)
	private String name;

	@JsonIgnore
	@ApiObjectField(description = "书本清单", order = 0)
	private List<Book> books = new ArrayList<Book>();
	
	public void addBook(Book book) {
		this.getBooks().add(book);
		book.setAuthor(this);
	}
	
}
