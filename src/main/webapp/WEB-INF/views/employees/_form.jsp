<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br/>
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br/>
        </c:forEach>
    </div>
</c:if>
<label for="${AttributeConst.EMP_CODE.getValue()}">社員番号</label><br/>
<input type="text" name="${AttributeConst.EMP_CODE.getValue()}" value="${employee.code}" />
<br /><br />

<label for="${AttributeConst.EMP_NAME.getValue()}">氏名</label><br />
<input type="text" name="${AttributeConst.EMP_NAME.getValue()}" value="${employee.name}" />
<br /><br />

<label for="${AttributeConst.EMP_PASS.getValue()}">パスワード</label><br />
<input type="password" name="${AttributeConst.EMP_PASS.getValue()}" />
<br /><br />

<label for="${AttributeConst.EMP_ADMIN_FLG.getValue()}">権限</label><br />
<select name="${AttributeConst.EMP_ADMIN_FLG.getValue()}">
    <option value="${AttributeConst.ROLE_GENERAL.getIntegerValue()}"<c:if test="${employee.adminFlag == AttributeConst.ROLE_GENERAL.getIntegerValue()}"> selected</c:if>>一般</option>
    <option value="${AttributeConst.ROLE_ADMIN.getIntegerValue()}"<c:if test="${employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}"> selected</c:if>>管理者</option>
</select>
<br/><br/>
<label for="${AttributeConst.DEPART_GR.getValue()}">所属部署</label><br />
<select name="${AttributeConst.DEPART_GR.getValue()}">
    <option value="${AttributeConst.GR_GENERAL.getIntegerValue()}"<c:if test="${role.departmentGr == AttributeConst.GR_GENERAL.getIntegerValue()}"> selected</c:if>>総務部</option>
    <option value="${AttributeConst.GR_SALES.getIntegerValue()}"<c:if test="${role.departmentGr == AttributeConst.GR_SALES.getIntegerValue()}"> selected</c:if>>営業部</option>
    <option value="${AttributeConst.GR_PUBLIC.getIntegerValue()}"<c:if test="${role.departmentGr == AttributeConst.GR_PUBLIC.getIntegerValue()}"> selected</c:if>>広報部</option>
</select>

<br/>
<label for="${AttributeConst.ROLE_FLAG.getValue()}">役職</label><br />
<select name="${AttributeConst.ROLE_FLAG.getValue()}">
    <option value="${AttributeConst.ROLE_MEMBER.getIntegerValue()}"<c:if test="${role.roleFlag == AttributeConst.ROLE_MEMBER.getIntegerValue()}"> selected</c:if>>メンバー</option>
    <option value="${AttributeConst.ROLE_MANAGER.getIntegerValue()}"<c:if test="${role.roleFlag == AttributeConst.ROLE_MANAGER.getIntegerValue()}"> selected</c:if>>課長</option>
    <option value="${AttributeConst.ROLE_DIRECTER.getIntegerValue()}"<c:if test="${role.roleFlag == AttributeConst.ROLE_DIRECTER.getIntegerValue()}"> selected</c:if>>部長</option>
</select>
<br /><br />
<input type="hidden" name="${AttributeConst.EMP_ID.getValue()}" value="${employee.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>
