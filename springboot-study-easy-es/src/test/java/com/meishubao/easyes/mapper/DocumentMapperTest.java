package com.meishubao.easyes.mapper;

import com.meishubao.easyes.domain.entity.Document;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.dromara.easyes.core.conditions.update.LambdaEsUpdateWrapper;
import org.dromara.easyes.core.core.EsWrappers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class DocumentMapperTest {

    private final DocumentMapper documentMapper;

    @Autowired
    DocumentMapperTest(DocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    @Test
    public void testCreateIndex() {
        // 测试创建索引,框架会根据实体类及字段上加的自定义注解一键帮您生成索引 需确保索引托管模式处于manual手动挡(默认处于此模式),若为自动挡则会冲突
        boolean success = documentMapper.createIndex();
        Assertions.assertTrue(success);
    }

    @Test
    public void testInsert() {
        // 测试插入数据
        Document document = new Document();
        document.setTitle("老汉");
        document.setContent("推*技术过硬");
        document.setDocNumber(3);
        document.setCreateTime(LocalDateTime.now());
        document.setUpdateTime(LocalDateTime.now().plusMinutes(5));
        int successCount = documentMapper.insert(document);
        System.out.println(successCount);
    }

    @Test
    public void testSelect() {
        // 测试查询 写法和MP一样 可以用链式,也可以非链式 根据使用习惯灵活选择即可
        String title = "老汉";
        Document document = EsWrappers.lambdaChainQuery(documentMapper)
                .eq(Document::getTitle, title)
                .one();
        System.out.println(document);
        Assertions.assertEquals(title, document.getTitle());
    }

    @Test
    public void testUpdate() {
        // 测试更新 更新有两种情况 分别演示如下:
        // case1: 已知id, 根据id更新 (为了演示方便,此id是从上一步查询中复制过来的,实际业务可以自行查询)
        String id = "N5GyiIoB_7m_gTqKqPQa";
        String title1 = "隔壁老王";
        Document document1 = new Document();
        document1.setId(id);
        document1.setTitle(title1);
        documentMapper.updateById(document1);

        // case2: id未知, 根据条件更新
        LambdaEsUpdateWrapper<Document> wrapper = new LambdaEsUpdateWrapper<>();
        wrapper.eq(Document::getTitle, title1);
        Document document2 = new Document();
        document2.setTitle("隔壁老李");
        document2.setContent("推*技术过软");
        documentMapper.update(document2, wrapper);

        // 关于case2 还有另一种省略实体的简单写法,这里不演示,后面章节有介绍,语法与MP一致
    }

    @Test
    public void testDelete() {
        // 测试删除数据 删除有两种情况:根据id删或根据条件删
        // 鉴于根据id删过于简单,这里仅演示根据条件删,以老李的名义删,让老李心理平衡些
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        String title = "隔壁老李";
        wrapper.eq(Document::getTitle, title);
        int successCount = documentMapper.delete(wrapper);
        System.out.println(successCount);
    }

    // 每种类型的使用都写一个demo
    // https://www.easy-es.cn/pages/17ea0a/#%E4%BC%98%E5%8A%BF%E5%AF%B9%E6%AF%94

    /**
     * 场景一: 嵌套and的使用
     */
    @Test
    public void testNestedAnd() {
        // 下面查询条件等价于MySQL中的 select * from document where doc_number in (1, 2) and (title = '老汉' or title = '推*')
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.in(Document::getDocNumber, 1, 2)
                .and(w -> w.eq(Document::getTitle, "老汉").or().eq(Document::getTitle, "推*"));
        List<Document> documents = documentMapper.selectList(wrapper);
    }

    /**
     * 场景二: 拼接and的使用
     */
    @Test
    public void testAnd() {
        // 下面查询条件等价于MySQL中的 select * from document where title = '老汉' and content like '推*'
        // 拼接and比较特殊,因为使用场景最多,所以条件与条件之间默认就是拼接and,所以可以直接省略,这点和MP是一样的
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Document::getTitle, "老汉")
                .match(Document::getContent, "推*");
        List<Document> documents = documentMapper.selectList(wrapper);
    }

    /**
     * 场景二: 嵌套or的使用
     */
    @Test
    public void testNestedOr() {
        // 下面查询条件等价于MySQL中的 select * from document where doc_number = 1 or (title = '老汉' and author = '糟老头子')
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Document::getDocNumber, 1)
                .or(i -> i.eq(Document::getTitle, "老汉").eq(Document::getAuthor, "糟老头子"));
        List<Document> documents = documentMapper.selectList(wrapper);
    }

    /**
     * 场景三: 拼接or的使用
     */
    @Test
    public void testOr() {
        // 下面查询条件等价于MySQL中的 select * from document where title = '老汉' or title = '痴汉'
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Document::getTitle, "老汉")
                .or()
                .eq(Document::getTitle, "痴汉");
        List<Document> documents = documentMapper.selectList(wrapper);
    }

    /**
     * 场景四: 嵌套filter的使用 其实和场景一一样,只不过filter中的条件不计算得分,无法按得分排序,查询性能稍高
     */
    @Test
    public void testNestedFilter() {
        // 下面查询条件等价于MySQL中的 select * from document where doc_number in (1, 2) and (title = '老汉' or title = '推*')
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.in(Document::getDocNumber, 1, 2)
                .filter(w -> w.eq(Document::getTitle, "老汉").or().eq(Document::getTitle, "推*"));
        List<Document> documents = documentMapper.selectList(wrapper);
    }

    /**
     * 场景五: 拼接filter的使用 filter中的条件不计算得分,无法按得分排序,查询性能稍高
     */
    @Test
    public void testFilter() {
        // 下面查询条件等价于MySQL中的 select * from document where title = '老汉'
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.filter().eq(Document::getTitle, "老汉");
        List<Document> documents = documentMapper.selectList(wrapper);
    }

    /**
     * 场景六: 嵌套mustNot的使用
     */
    @Test
    public void testNestedNot() {
        // 下面查询条件等价于MySQL中的 select * from document where title = '老汉' and (size != 18 and age != 18)
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Document::getTitle, "老汉")
                .not(i -> i.eq(Document::getSize, 18).eq(Document::getAge, 18));
        List<Document> documents = documentMapper.selectList(wrapper);
    }

    /**
     * 场景六: 拼接not()的使用
     */
    @Test
    public void testNot() {
        // 下面查询条件等价于MySQL中的 select * from document where title = '老汉' and  size != 18
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Document::getTitle, "老汉")
                .not()
                .eq(Document::getSize, 18);
        List<Document> documents = documentMapper.selectList(wrapper);
    }

    // ======


    @Test
    public void testMatch(){
        // 会对输入做分词,只要所有分词中有一个词在内容中有匹配就会查询出该数据,无视分词顺序
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.match(Document::getContent,"技术");
        List<Document> documents = documentMapper.selectList(wrapper);
        System.out.println(documents.size());
    }

    @Test
    public void testMatchPhrase() {
        // 会对输入做分词，但是需要结果中也包含所有的分词，而且顺序要求一样,否则就无法查询出结果
        // 例如es中数据是 技术过硬,如果搜索关键词为过硬技术就无法查询出结果
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.matchPhrase(Document::getContent, "技术");
        List<Document> documents = documentMapper.selectList(wrapper);
        System.out.println(documents);
    }

    @Test
    public void testMatchAllQuery() {
        // 查询所有数据,类似mysql select all.
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.matchAllQuery();
        List<Document> documents = documentMapper.selectList(wrapper);
        System.out.println(documents);
    }

    @Test
    public void testMatchPhrasePrefixQuery() {
        // 前缀匹配查询 查询字符串的最后一个词才能当作前缀使用
        // 前缀 可能会匹配成千上万的词,这不仅会消耗很多系统资源,而且结果的用处也不大,所以可以提供入参maxExpansions,若不写则默认为50
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.matchPhrasePrefixQuery(Document::getMultiField, "乌拉巴拉", 10);
        List<Document> documents = documentMapper.selectList(wrapper);
        System.out.println(documents);
    }

    @Test
    public void testMultiMatchQuery() {
        // 从多个指定字段中查询包含老王的数据
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.multiMatchQuery("老王", Document::getTitle, Document::getContent, Document::getAuthor, Document::getMultiField);

        // 其中,默认的Operator为OR,默认的minShouldMatch为60% 这两个参数都可以按需调整,我们api是支持的 例如:
        // 其中AND意味着所有搜索的Token都必须被匹配,OR表示只要有一个Token匹配即可. minShouldMatch 80 表示只查询匹配度大于80%的数据
        // wrapper.multiMatchQuery("老王",Operator.AND,80,Document::getCustomField,Document::getContent);

        List<Document> documents = documentMapper.selectList(wrapper);
        System.out.println(documents.size());
        System.out.println(documents);
    }

    @Test
    public void queryStringQuery() {
        // 从所有字段中查询包含关键词老汉的数据
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.queryStringQuery("老汉");
        List<Document> documents = documentMapper.selectList(wrapper);
        System.out.println(documents);
    }

    @Test
    public void prefixQuery() {
        // 查询创建者以"隔壁"打头的所有数据  比如隔壁老王 隔壁老汉 都能被查出来
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.prefixQuery(Document::getAuthor, "隔壁");
        List<Document> documents = documentMapper.selectList(wrapper);
        System.out.println(documents);
    }

    @Test
    public void like() {
        // 等价于MySQL中 like %汉推% 像老汉推...就可以被查出来
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.like(Document::getTitle, "汉推");
        List<Document> documents = documentMapper.selectList(wrapper);
        System.out.println(documents);
    }

    @Test
    public void likeLeft() {
        // 等价于MySQL中 like %汉 像老汉就可以被查出来
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.likeLeft(Document::getTitle, "汉");
        List<Document> documents = documentMapper.selectList(wrapper);
        System.out.println(documents);
    }

    @Test
    public void likeRight() {
        // 等价于MySQL中 like 老%  像老汉就可以被查出来
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.likeRight(Document::getTitle, "老");
        List<Document> documents = documentMapper.selectList(wrapper);
        System.out.println(documents);
    }




}