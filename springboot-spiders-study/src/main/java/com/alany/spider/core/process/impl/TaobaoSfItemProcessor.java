package com.alany.spider.core.process.impl;

import com.alany.spider.bean.HttpResult;
import com.alany.spider.common.AddressType;
import com.alany.spider.common.BizEnum;
import com.alany.spider.common.SpiderProcessor;
import com.alany.spider.common.SpringContextHolder;
import com.alany.spider.core.http.HttpRequest;
import com.alany.spider.core.process.AbstractItemProcessor;
import com.alany.spider.entity.House;
import com.alany.spider.service.HouseService;
import com.alany.spider.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Slf4j
@SpiderProcessor
public class TaobaoSfItemProcessor extends AbstractItemProcessor<House> {

    private static Logger logger = LoggerFactory.getLogger(TaobaoSfItemProcessor.class);

    private final HttpRequest httpRequest;

    private final HouseService houseService;

    private static int totalPage = 0;

    public TaobaoSfItemProcessor() {
        this.httpRequest = SpringContextHolder.getBean(HttpRequest.class);
        this.houseService = SpringContextHolder.getBean(HouseService.class);
    }

    @Override
    public HttpResult request() {
        Map<String, Object> headers = new HashMap<>();
        if (userAgentList != null && !userAgentList.isEmpty()) {
            int index = new Random().nextInt(userAgentList.size());
            headers.put("User-Agent", userAgentList.get(index));
        }

        HttpResult result = httpRequest.setUrl(executeContent.getUrl()).setHeaders(headers).setUseProxy(false).doGet();
        return result;
    }

    @Override
    public List<House> parse(HttpResult result) {
        List<House> list = new ArrayList<>();
        if (result != null && StringUtils.isNotEmpty(result.getContent())) {
            try {
                Document document = Jsoup.parse(result.getContent());
                Element element = document.select("script[id=sf-item-list-data]").first();
                if (element == null) {
                    return null;
                }
                if (totalPage == 0) {
                    String totalPageStr = document.select(".page-total").first().ownText();
                    if (StringUtils.isNotBlank(totalPageStr)) {
                        totalPage = Integer.parseInt(totalPageStr);
                    }
                }
                String jsonText = element.html();
                JSONObject root = JSON.parseObject(jsonText);
                JSONArray dataArray = root.getJSONArray("data");
                if (dataArray != null && !dataArray.isEmpty()) {
                    for (int i = 0, length = dataArray.size(); i < length; i++) {
                        JSONObject item = dataArray.getJSONObject(i);

                        House entity = new House();
                        entity.setSourceName(BizEnum.tabobao.getName());
                        entity.setItemId(item.getString("id"));
                        String title = item.getString("title");
                        entity.setAddress(title);
                        entity.setCity(AddressType.regexAddress(title, AddressType.city));
                        entity.setLocation(AddressType.regexAddress(title, AddressType.county));
//                        if (title.contains("市") && title.contains("区")) {
//                            bean.setCity(title.substring(0, title.indexOf("市") + 1));
//                            bean.setLocation(title.substring(title.indexOf("市") + 1, title.indexOf("区") + 1));
//                        } else {
//                            bean.setCity("广州市");
//                        }

                        entity.setSellTotalPrice(item.getFloatValue("currentPrice"));
                        entity.setMarketTotalPrice(item.getFloatValue("marketPrice"));
                        if (entity.getMarketTotalPrice() < 1) {
                            entity.setMarketTotalPrice(item.getFloatValue("consultPrice"));
                        }
                        entity.setSellStatus(item.getString("status"));
                        Date date = new Date(item.getTimestamp("end").getTime());
                        entity.setSellDate(date);
                        entity.setItemUrl("http:" + item.getString("itemUrl"));

                        if (StringUtils.isNotBlank(entity.getItemId())) {
                            list.add(entity);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("parse json to bean meet error:", e);
            }
        }
        return list;
    }

    @Override
    public void store(List<House> list) {
        if (list != null && !list.isEmpty()) {
            houseService.batchInsert(list);
        }
    }

    @Override
    protected boolean hasMore() {
        return false;
    }
}
