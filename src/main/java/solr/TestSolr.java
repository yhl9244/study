package solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suneee on 2018/8/28.
 */
public class TestSolr {

    private static final String solrUrl = "http://localhost:8983/solr/bless";

    //创建solrClient同时指定超时时间，不指定走默认配置
    private static HttpSolrClient client = new HttpSolrClient.Builder(solrUrl)
            .withConnectionTimeout(10000).withSocketTimeout(60000).build();


    public static void main(String[] args) throws Exception {
        //query();
        //add();
        //delete();
        //addBean();
        queryBean();
    }

    /**
     * solr查询
     */
    private static void query() throws Exception {
        // 封装查询参数
        SolrQuery query = new SolrQuery("*:*");
        // 需要展示内容
        query.addField("id");
        query.addField("name");
        query.addField("money");
        query.setRows(10);
        // 执行查询
        QueryResponse queryResponse = client.query(query);
        // 获取doc文档
        SolrDocumentList results = queryResponse.getResults();
        for (SolrDocument document : results){
            System.out.println(document.get("id"));
            System.out.println(document.get("name"));
            System.out.println(document.get("money"));
        }
        // 关闭连接
        client.close();
    }

    /**
     * solr新增/修改
     */
    private static void add() throws IOException, SolrServerException {
        // 创建文档doc
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", 21);
        document.addField("name", "yhl");
        document.addField("money", 1000);
        // 添加到client
        UpdateResponse updateResponse = client.add(document);
        // 提交
        client.commit();
    }

    /**
     * solr删除
     */
    private static void delete() throws IOException, SolrServerException {
        // 1. 通过id删除
        client.deleteById("21");
        // 2. 通过ids 删除
        List<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        client.deleteById(ids);
        // 3. 通过查询删除
        client.deleteByQuery("id:3");
        // 提交
        client.commit();
        // 关闭连接
        client.close();
    }

    /**
     * solr通过Bean查询
     */
    private static void queryBean() throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery("*:*");
        query.addField("id");
        query.addField("name");
        query.addField("money");
        query.setRows(5);
        QueryResponse response = client.query(query);

        List<Account> beans = response.getBeans(Account.class);
        for (Account account : beans){
            System.out.println(account.getId());
            System.out.println(account.getName());
            System.out.println(account.getMoney());
        }

        client.close();
    }

    /**
     * solr通过Bean增加
     */
    private static void addBean() throws IOException, SolrServerException {
        for (int i = 0; i < 10; i++) {
            Account account = new Account();
            account.setId(String.valueOf(i));
            account.setName("yhl" + i);
            account.setMoney(100f*i);
            UpdateResponse updateResponse = client.addBean(account);
            client.commit();
        }
        client.close();
    }
}
