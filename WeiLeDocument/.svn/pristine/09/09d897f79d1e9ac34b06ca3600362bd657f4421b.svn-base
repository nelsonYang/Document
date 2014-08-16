package com.yidhuo.weiledoc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "dataTableBean")
@RequestScoped
public class DataTableBean implements Serializable{
    private List<ResultCodeEntity> resultCodeList;
    public DataTableBean() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("/errorCode.properties");
        Properties properties = new Properties();
        ResultCodeEntity resultCodeEntity;
        try {
            properties.load(is);
            resultCodeList = new ArrayList<ResultCodeEntity>(64);
            Set<Map.Entry<Object,Object>> resultCodeEntry = properties.entrySet();
            for(Map.Entry<Object,Object> entry : resultCodeEntry){
                resultCodeEntity = new ResultCodeEntity();
                resultCodeEntity.setKey((String)entry.getKey());
                resultCodeEntity.setValue((String)entry.getValue());
                resultCodeList.add(resultCodeEntity);
            }
            Collections.reverse(resultCodeList);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * @return the resultCodeList
     */
    public List<ResultCodeEntity> getResultCodeList() {
        return resultCodeList;
    }

    /**
     * @param resultCodeList the resultCodeList to set
     */
    public void setResultCodeList(List<ResultCodeEntity> resultCodeList) {
        this.resultCodeList = resultCodeList;
    }
}
