graphql-java-kickstart

https://blog.csdn.net/wanghao112956/article/details/103023364

GraphQL 提供了一个能够同时解决这两个问题的方案. 它允许客户端在一个请求里指明所需要的数据，还可以实现在一个请求里发送多个查询。
它工作起来更像是 RPC 服务，它不用固定的 verb，而是使用 命名查询（Query） 和 命名修改（Mutation） 的方式。这就让业务编写的
控制权回到了它应有的地方，API 接口的开发者来确定哪些接口行为是被允许的， API 接口的使用者在运行时动态指明他们想要什么数据。

定义 Schema
GraphQL Tools 可以通过处理 GraphQL Schema 文件来生成正确的结构对象，并绑定到对应的 bean 对象上。这些schema文件只要以
“.graphqls” 扩展名结尾并存在于 classpath里，Spring Boot GraphQL starter 就可以自动找到这些 schema 文件，所以我们
完全可以把这些文件按模块划分管理。 但我们只能有一个根查询，也必须有一个根查询的定义。而 Mutation 定义可以没有或者有一个。这
个限制是源于 GraphQL Schema 规则，而不是因为 Java 无法实现。

根查询解析器
根查询需要通过在Spring里定义特殊的 java bean 对象，从而来处理不同的字段查询。它不像 schema 的定义文件，这些bean对象可以有
多个。我们只需要实现 GraphQLQueryResolver 接口，然后在 schema 里的每个字段都有相对应名字的属性或函数就可以了。
这些属性或函数会按如下的规则顺序查找：
1、name                                 users => users
2、is仅当该字段是个 Boolean 变量时          exist => isExist
3、get                                  user  => getUser
如果schema里对应字段有定义参数的话，这些函数也需要按照对应的顺序来定义（像 count 与 offset），函数参数最后可以有一个可选的
DataFetchingEnvironment 类型的参数，来获取一些上下文信息。 这些函数的返回值也需要和 schema 里对应起来。所有的原生类型
String，Int，List，等等 都可以和相应的 Java 类型对应起来。

通过 Bean 对象来映射 GraphQL 类型
在 GraphQL 服务里，无论是在根节点还是任何一个结构里，所有复杂的类型都可以对应上 Java Bean 对象。每一个 GraphQL 类型只能
有一个 Java 类来对应，但 Java 的类名并不一定要和 GraphQL 里的类型名一样 Java bean 里的属性名会被映射成 GraphQL 返回数
据的字段名

复杂对象的字段解析
有时候，一个字段的数据并不能直接访问，它有可能涉及到数据库查询，复杂的计算，或者一些别的什么情况。 GraphQL Tools 有一套机制来
处理这些场景 它可以用 Spring 的 Bean 对象来为这些普通 Bean 提供数据。

我们通过使用普通 Bean 名字后面加上 Resolver ，然后再实现 GraphQLResolver 接口，就可以使用 Spring Bean 来为普通 Bean 提
供额外的字段解析，然后在 Spring Bean里的方法都需要遵循上面的命名规则，唯一的区别是这些方法的第一个参数会是普通Bean对象。 如果字
段同时存在于普通 Bean 和 Resolver上的话，Resolver上的会被优先采用。

这些 Resolver 会被 Spring 上下文加载，所以这可以使得我们可以使用很多 Spring 的策略，比方说注入 DAO。

和上面一样，如果客户端并没有请求对应的字段的话，GraphQL 将不会去获取相应的数据。这就意味着如果客户端去获取了一个 Post 但没有要请
求 author 字段，那么 Resolver 里的 getAuthor() 方法将不会被调用，从而相应的 DAO 请求也不会发出。

有关 GraphiQL
GraphQL 经常会和 GraphiQL 一起使用，GraphiQL 是一个可以直接和 GraphQL 服务交互的 UI 界面，可以执行查询和修改请求。

http://localhost:8080/graphiql
查询示例
==================================
// 员工查询
query {
  employee(id: "1234567890") {
    id
    firstName
    lastName
    department {
      name
      organization {
        name
      }
    }
  }
}
// 按列表查询
query {
  users {
    id,
    nickname
  }
}
// 按参数查询
query {
  user(nickname: "a1") {
    id,
    description,
    password,
    sex,
    mail,
    car {
      id,
      name,
      color,
    },
    bike {
      id,
      name
    }
  }
}
// 分页查询
query {
  userPage(query:"", page: 1, size: 9) {
    empty
    first
    last
    number
    numberOfElements
    size
    totalElements
    totalPages
    content{
      id
    }
  }
}
// 添加数据
mutation {
    addUser(mail:"11", nickname: "testuser", password: "111", description: "test") {
      code,
      msg
    }
}
// 删除数据
mutation {
    deleteUser(id: "2") {
      code,
      msg
    }
}
// 更新数据
mutation {
  updateUser(id: "1", mail: "11", nickname: "testuser", description: "test") {
    id,
    nickname,
    description
  }
}
// 按实体类添加数据
mutation {
  addUserByInput(input: {mail: "testuser2@test.com", nickname: "testuser2", password: "123456", description: "Helloworld"}) {
    id,
    nickname,
    description
  }
}








