package com.yidhuo.weiledoc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "SingleDetailServlet", urlPatterns = {"/SingleDetailServlet"})
public class SingleDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String act = request.getParameter("act");
        try {
            ServiceConfigBean bean = new DataGridBean().getServiceConfigMap().get(act);
            StringBuilder divBuilder = new StringBuilder(200);
            divBuilder.append("<div style='font-weight: bold;font-size: 20px;text-align:left'>actionName:").append(act).append("</div>");
            divBuilder.append("<div style='font-weight: bold;font-size: 20px;text-align:left'>description:").append(bean.getDescription()).append("</div>");
            divBuilder.append("<div style='font-weight: bold;font-size: 20px;text-align:left'>request加密:").append(bean.getRequestCrypt()).append("</div>");
            divBuilder.append("<div style='font-weight: bold;font-size: 20px;text-align:left'>response加密:").append(bean.getResponseCrypt()).append("</div>");
            divBuilder.append("<div style='font-weight: bold;font-size: 20px'>必要参数<div>");
            divBuilder.append("<table border='1' style='width=100%;font-weight: bold;font-size: 40px;border-collapse:collapse'>");
            divBuilder.append("<th width=25%>").append("名称").append("</th>");
            divBuilder.append("<th width=25%>").append("类型").append("</th>");
            divBuilder.append("<th width=25%>").append("描述").append("</th>");
            divBuilder.append("<th width=25%>").append("默认值").append("</th>");
            for (FieldInfoBean fieldInfo : bean.getImportParametersList()) {
                divBuilder.append("<tr>");
                divBuilder.append("<td>").append(fieldInfo.getName()).append("</td>");
                divBuilder.append("<td>").append(fieldInfo.getType()).append("</td>");
                divBuilder.append("<td>").append(fieldInfo.getDesc()).append("</td>");
                divBuilder.append("<td>").append(fieldInfo.getDefaultValue()).append("</td>");
                divBuilder.append("</tr>");
            }
            divBuilder.append("</table>");
            divBuilder.append("<div style='font-weight: bold;font-size: 20px'>可选参数<div>");
            divBuilder.append("<table border='1' style='width=100%;font-weight: bold;font-size: 40px;border-collapse:collapse'>");
            divBuilder.append("<th width=25%>").append("名称").append("</th>");
            divBuilder.append("<th width=25%>").append("类型").append("</th>");
            divBuilder.append("<th width=25%>").append("描述").append("</th>");
            divBuilder.append("<th width=25%>").append("默认值").append("</th>");
            for (FieldInfoBean fieldInfo : bean.getMiorParametersList()) {
                divBuilder.append("<tr>");
                divBuilder.append("<td>").append(fieldInfo.getName()).append("</td>");
                divBuilder.append("<td>").append(fieldInfo.getType()).append("</td>");
                divBuilder.append("<td>").append(fieldInfo.getDesc()).append("</td>");
                divBuilder.append("<td>").append(fieldInfo.getDefaultValue()).append("</td>");
                divBuilder.append("</tr>");
            }
            divBuilder.append("</table>");
            divBuilder.append("<div style='font-weight: bold;font-size: 20px'>返回参数<div>");
            divBuilder.append("<table border='1' style='width=100%;font-weight: bold;font-size: 20px;border-collapse:collapse'>");
            divBuilder.append("<th width=25%>").append("名称").append("</th>");
            divBuilder.append("<th width=25%>").append("类型").append("</th>");
            divBuilder.append("<th width=25%>").append("描述").append("</th>");
            divBuilder.append("<th width=25%>").append("默认值").append("</th>");
            for (FieldInfoBean fieldInfo : bean.getReturnParametersList()) {
                divBuilder.append("<tr>");
                divBuilder.append("<td>").append(fieldInfo.getName()).append("</td>");
                divBuilder.append("<td>").append("string").append("</td>");
                divBuilder.append("<td>").append("").append("</td>");
                divBuilder.append("<td>").append("").append("</td>");
                divBuilder.append("</tr>");
            }
            divBuilder.append("</table>");
            out.print(divBuilder.toString());
            out.flush();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
