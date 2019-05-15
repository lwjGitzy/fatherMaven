package com.jk.controller;


import com.jk.model.TypeBean;
import com.jk.service.MainService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("solr")
public class MainController {
    @Autowired
    private MainService mainService;
    @Autowired
    private SolrClient client;

    /**
     * 新增/修改 索引
     * 当 id 存在的时候, 此方法是修改(当然, 我这里用的 uuid, 不会存在的), 如果 id 不存在, 则是新增
     * @return
     */
    @RequestMapping("add")
    public String add() {
        //存进mysql数据库
       /* TypeBean typeBean = new TypeBean();
       typeBean.setName("sss");
        mainService.addType(typeBean);*/

        //存进solr
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id",uuid);
            doc.setField("name","22");//先输出“”

            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
             * 下面都是一样的
             */

            client.add("core1", doc);
            //client.commit();
            client.commit("core1");
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }
    /**
     * 根据id删除索引
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public String delete(String id)  {
        try {
            client.deleteById("core1",id);
            client.commit("core1");

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "error";
    }
    /**
     * 删除所有的索引
     * @return
     */
    @RequestMapping("deleteAll")
    public String deleteAll(){
        try {

            client.deleteByQuery("core1","*:*");
            client.commit("core1");

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    /**
     * 根据id查询索引
     * @return
     * @throws Exception
     */
    @RequestMapping("getById")
    public String getById() throws Exception {
        SolrDocument document = client.getById("core1", "3");
        System.out.println(document);
        return document.toString();
    }
    @RequestMapping(value="search")
    @ResponseBody
    public Map<String,Object> userlist( TypeBean beans,Integer page,Integer rows) throws IOException, SolrServerException {
        //返回的参数map
        Map<String,Object> mSolr=new HashMap<String,Object>();
        //查询的耳集合
        List<TypeBean> beanslist=new ArrayList<>();
        //查询参数的对象SolrQuery
        SolrQuery params = new SolrQuery();
        //判断关键词是否为空
        if(!"".equals(beans.getName() ) && beans.getName() != null ){
            //不为空关键词为前台传递的参数
            params.set("q", beans.getName());
        }else{
            //为空查询所有
            params.set("q", "*:*");
        }
        //默认查询的字段
        params.set("df", "name");
        //默认返回的字段
        params.set("fl", "id,name");
        // 高亮字段
        params.addHighlightField("name");
        //分页
        if(page==null){
            params.setStart(0);
        }else {
            params.setStart((page-1)*rows);
        }
        if(rows==null){
            params.setRows(5);
        }else {
            params.setRows(rows);
        }


        //高亮
        //打开开关
        params.setHighlight(true);
        //设置前缀
        params.setHighlightSimplePre("<span style='color:red'>");
        //设置后缀
        params.setHighlightSimplePost("</span>");
        //solr查询返回的对象QueryResponse
        QueryResponse queryResponse = client.query("core1",params);
        //查询返回的真正结果
        SolrDocumentList results = queryResponse.getResults();
        //查询总条数
        long numFound = results.getNumFound();
        //高亮显示的内容
        Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();
        //循环遍历结果把查询内容放到list集合中
        for (SolrDocument result : results) {
            TypeBean type =new TypeBean();
            String highname="";
            //获得高亮内容
            Map<String, List<String>> map = highlight.get(result.get("id"));
            //获得高亮内容的list
            List<String> list = map.get("name");
            //判断是否为空
            if(list==null){
                //如果为空没有高亮
                highname=(String)result.get("name");
            }else{
                //不为空有高亮
                highname=list.get(0);
            }
            //依次把字段放到product对象中

            type.setId(Integer.valueOf(  (String) result.get("id"))   );
            type.setName(highname);
            beanslist.add(type);
          /*  user.setId((String)result.get("id"));
            user.setProduct_num((int)result.get("product_num"));
            user.setProduct_price((long)result.get("product_price"));
            user.setProduct_title(highname);
            userslist.add(user);*/

        }
        //把条数和查询结果放到map中
        mSolr.put("total",numFound);
        mSolr.put("rows",beanslist);
        return mSolr;
    }
}


