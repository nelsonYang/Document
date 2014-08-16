package com.yidhuo.weiledoc;

import com.framework.annotation.Field;
import com.framework.annotation.ServiceConfig;
import com.framework.entity.annotation.EntityConfig;
import com.framework.entity.annotation.FieldConfig;
import com.framework.entity.handler.EntityHandler;
import com.framework.entity.handler.EntityHandlerImpl;
import com.framework.entity.handler.FieldHandler;
import com.framework.entity.handler.FieldHandlerImpl;
import com.framework.enumeration.CryptEnum;
import com.framework.enumeration.FieldTypeEnum;
import com.framework.enumeration.LoginEnum;
import com.framework.enumeration.TerminalTypeEnum;
import com.framework.service.api.Service;
import com.framework.utils.parser.ClassParser;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class GenerateDOC {
  
    private static final Map<String, ServiceConfigBean> serviceConfigMap = new HashMap<String, ServiceConfigBean>(128, 1);
    private static final Map<String,String> fieldMap = new HashMap<String,String>();
    public  static Map<String, ServiceConfigBean> getServiceConfigMap(){
        generateDoc();
        return serviceConfigMap;
    }
    public static  void generateDoc() {
        String[] packageNames = {"com.daxia"};
        List<String> classNameList = new ClassParser().parse(packageNames);
        setFieldMap(classNameList);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ServiceConfig serviceConfig;
        String act;
        String[] parameters;
        String[] minorParameters;
        CryptEnum requestSecurityType;
        CryptEnum responseSecurityType;
        String[] returnParameters;
        LoginEnum loginEnum;
        String fieldName;
        String description;
        String defaultValue;
        FieldTypeEnum fieldTypeEnum;
        TerminalTypeEnum terminalTypeEnum;
        ServiceConfigBean serviceConfigBean;
        String fieldDescription;
        FieldInfoBean fieldInfoBean;
        for (String className : classNameList) {
            try {
                Class clazz = classLoader.loadClass(className);
                if (clazz.isAnnotationPresent(ServiceConfig.class) && Service.class.isAssignableFrom(clazz)) {
                    serviceConfigBean = new ServiceConfigBean();
                    serviceConfig = (ServiceConfig) clazz.getAnnotation(ServiceConfig.class);
                    act = serviceConfig.act();
                    description = serviceConfig.description();
                    parameters = serviceConfig.importantParameters();
                    minorParameters = serviceConfig.minorParameters();
                    requestSecurityType = serviceConfig.requestEncrypt();
                    responseSecurityType = serviceConfig.responseEncrypt();
                    returnParameters = serviceConfig.returnParameters();
                    loginEnum = serviceConfig.requireLogin();
                    Field[] fields = serviceConfig.validateParameters();
                    terminalTypeEnum = serviceConfig.terminalType();
                    ArrayList<FieldInfoBean> importParametersList = new ArrayList<FieldInfoBean>(parameters.length);
                    ArrayList<FieldInfoBean> minorParametersList = new ArrayList<FieldInfoBean>(minorParameters.length);
                    for (String parameter : parameters) {
                        fieldInfoBean = new FieldInfoBean();
                        for (Field field : fields) {
                            fieldName = field.fieldName();
                            if (parameter.equals(fieldName)) {
                                defaultValue = field.defaultValue();
                                fieldTypeEnum = field.fieldType();
                                fieldDescription = field.description();
                                fieldInfoBean.setName(fieldName);
                                fieldInfoBean.setDefaultValue(defaultValue);
                                fieldInfoBean.setDesc(fieldDescription);
                                fieldInfoBean.setType(fieldTypeEnum.toString());
                                fieldInfoBean.setDefaultValue("");
                                importParametersList.add(fieldInfoBean);
                                break;
                            }
                        }
                    }
                    for (String parameter : minorParameters) {
                        fieldInfoBean = new FieldInfoBean();
                        for (Field field : fields) {
                            fieldName = field.fieldName();
                            if (parameter.equals(fieldName)) {
                                defaultValue = field.defaultValue();
                                fieldTypeEnum = field.fieldType();
                                fieldDescription = field.description();
                                fieldInfoBean.setName(fieldName);
                                fieldInfoBean.setDesc(fieldDescription);
                                fieldInfoBean.setType(fieldTypeEnum.toString());
                                fieldInfoBean.setDefaultValue(defaultValue);
                                minorParametersList.add(fieldInfoBean);
                                break;
                            }
                        }
                    }
                    ArrayList<FieldInfoBean> returnParametersList = new ArrayList<FieldInfoBean>(returnParameters.length);
                    for (String returnParameter : returnParameters) {
                        fieldInfoBean = new FieldInfoBean();
                        fieldInfoBean.setName(returnParameter);
                        fieldInfoBean.setDesc(fieldMap.get(returnParameter) == null ? "" : fieldMap.get(returnParameter));
                        fieldInfoBean.setType("string");
                        fieldInfoBean.setDefaultValue("");
                        returnParametersList.add(fieldInfoBean);
                    }
                    serviceConfigBean.setAct(act);
                    serviceConfigBean.setDescription(description);
                    serviceConfigBean.setRequestCrypt(requestSecurityType.toString());
                    serviceConfigBean.setResponseCrypt(responseSecurityType.toString());
                    serviceConfigBean.setTerminalType(terminalTypeEnum.toString());
                    serviceConfigBean.setRequireLogin(loginEnum.toString());
                    serviceConfigBean.setImportParametersList(importParametersList);
                    serviceConfigBean.setMiorParametersList(minorParametersList);
                    serviceConfigBean.setReturnParametersList(returnParametersList);
                    serviceConfigMap.put(act, serviceConfigBean);
                }
            } catch (Exception ex) {
            }
        }
    }
    
    private static void setFieldMap(List<String> clazzList){
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            for (String className : clazzList) {
                Class<?> clazz = (Class<?>) classLoader.loadClass(className);
                if (clazz.isAnnotationPresent(EntityConfig.class)) {
                    java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
                    for (java.lang.reflect.Field field : fields) {
                        int modifier = field.getModifiers();
                        if (!Modifier.isStatic(modifier)) {
                            if (field.isAnnotationPresent(FieldConfig.class)) {
                                FieldConfig fieldConfig = (FieldConfig) field.getAnnotation(FieldConfig.class);
                               fieldMap.put(fieldConfig.fieldName(), fieldConfig.description());
                            }
                        }
                    }
                    
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        
        }
    
    }
}
