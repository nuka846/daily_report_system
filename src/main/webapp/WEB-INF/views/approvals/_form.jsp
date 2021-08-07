<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${report.employee.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="reportDay" type="date" />
                    <td><fmt:formatDate value='${reportDay}' pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${report.content}" /></pre></td>
                </tr>
                <tr>
                </tr>
            </tbody>
       </table>
       <label for="${AttributeConst.REP_APPROV_FLAG.getValue()}">承認状態</label><br />
<select name="${AttributeConst.REP_APPROV_FLAG.getValue()}">
    <option value="${AttributeConst.APPROV_ON.getIntegerValue()}"<c:if test="${report.approvalFlag == AttributeConst.APPROV_ON.getIntegerValue()}"> selected</c:if>>承認</option>
    <option value="${AttributeConst.APPROV_OFF.getIntegerValue()}"<c:if test="${report.approvalFlag == AttributeConst.APPROV_OFF.getIntegerValue()}"> selected</c:if>>未承認</option>
</select>
<input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
