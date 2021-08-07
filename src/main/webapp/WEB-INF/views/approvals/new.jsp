<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actApprov" value="${ForwardConst.ACT_APPROV.getValue() }"/>
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>日報の承認</h2>

        <form method="POST" action="<c:url value='?action=${actApprov}&command=${commCrt}' />">
            <c:import url="_form.jsp" />
            <button type="submit">承認処理を行う</button>
        </form>
            <a href="<c:url value='?action=${actApprov}&command=${commShow}&id=${report.id}' />">内容確認画面に戻る</a>
            <br/>
            <a href="<c:url value='?action=${actApprov}&command=${commIdx}' />">未承認日報一覧に戻る</a>
    </c:param>
</c:import>