package com.yidhuo.weiledoc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 *
 *
 */
@ManagedBean(name = "serviceConfigDetail")
@RequestScoped
public final class ServiceConfigDetailBean implements Serializable {

    private final Map<String, ServiceConfigBean> serviceConfigMap = GenerateDOC.getServiceConfigMap();
    private List<FieldInfoBean> importParameterList = new ArrayList<FieldInfoBean>();
    private List<FieldInfoBean> minorParameterList = new ArrayList<FieldInfoBean>();
    private List<FieldInfoBean> returnParameterList = new ArrayList<FieldInfoBean>();
    private ServiceConfigBean serviceConfigBean;

    public ServiceConfigDetailBean() {
        this.action();
    }

    public void action() {
        String act = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("act");
        System.out.println("act=" + act);
        serviceConfigBean = serviceConfigMap.get(act);
        this.setImportParameterList(serviceConfigBean.getImportParametersList());
        this.setMinorParameterList(serviceConfigBean.getMiorParametersList());
        this.setReturnParameterList(serviceConfigBean.getReturnParametersList());

    }

    /**
     * @return the importParameterList
     */
    public List<FieldInfoBean> getImportParameterList() {
        return importParameterList;
    }

    /**
     * @param importParameterList the importParameterList to set
     */
    public void setImportParameterList(List<FieldInfoBean> importParameterList) {
        this.importParameterList = importParameterList;
    }

    /**
     * @return the minorParameterList
     */
    public List<FieldInfoBean> getMinorParameterList() {
        return minorParameterList;
    }

    /**
     * @param minorParameterList the minorParameterList to set
     */
    public void setMinorParameterList(List<FieldInfoBean> minorParameterList) {
        this.minorParameterList = minorParameterList;
    }

    /**
     * @return the returnParameterList
     */
    public List<FieldInfoBean> getReturnParameterList() {
        return returnParameterList;
    }

    /**
     * @param returnParameterList the returnParameterList to set
     */
    public void setReturnParameterList(List<FieldInfoBean> returnParameterList) {
        this.returnParameterList = returnParameterList;
    }

    /**
     * @return the serviceConfigBean
     */
    public ServiceConfigBean getServiceConfigBean() {
        return serviceConfigBean;
    }

    /**
     * @param serviceConfigBean the serviceConfigBean to set
     */
    public void setServiceConfigBean(ServiceConfigBean serviceConfigBean) {
        this.serviceConfigBean = serviceConfigBean;
    }
}
