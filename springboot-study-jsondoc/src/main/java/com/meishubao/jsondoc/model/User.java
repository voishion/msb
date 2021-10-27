package com.meishubao.jsondoc.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "username")
public class User {

	private Long id;

	private String username;

}
