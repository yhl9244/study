package solr;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by suneee on 2018/8/28.
 */
public class Account {

    @Field
    private String id;

    @Field
    private String name;

    @Field
    private Float money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }
}
