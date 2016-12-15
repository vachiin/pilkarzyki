<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Piłkarzyki 10 (epoch edition)</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style type="text/css">
        body {
            background: linear-gradient(#f0f0f0, #909090);
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
<form:form id="form_id" action="#" method="post" modelAttribute="model">
    <div style="width: 100%; overflow: hidden;">
        <div style="border: 2px aqua; width: 200px; float: left;">
            Kto gra: <br/>
            <ul>
                <form:checkboxes path="checkedPlayers" items="${model.allPlayers}" delimiter="<br/>"/>
            </ul>
        </div>
        <div style="border: 2px aqua; margin-left: 320px;">
            Wylosowano: <br/>
            <table>
                <tr>
                    <td>Biały obrona</td>
                    <td><input type="text" id="bo" title="bo" readonly value="${model.table.whiteDefense.nick}"/></td>
                    <td>Niebieski obrona</td>
                    <td><input type="text" id="no" title="no" readonly value="${model.table.blueDefense.nick}"/></td>
                </tr>
                <tr>
                    <td>Biały atak</td>
                    <td><input type="text" id="ba" title="ba" readonly value="${model.table.whiteAttack.nick}"/></td>
                    <td>Niebieski atak</td>
                    <td><input type="text" id="na" title="na" readonly value="${model.table.blueAttack.nick}"/></td>
                </tr>
            </table>
        </div>
        <input type="submit" value="Losuj" name="action"/>
        <input type="submit" value="Odśwież" name="action"/>
    </div>
    <div>
        <c:if test="${not empty model.historyEntries}">
            Dane ostatnich losowań:<br/>
            <div>Twój session ID:&nbsp;${model.sessionId}</div>
            <table border="3">
                <thead>
                <tr>
                    <th>numer</th>
                    <th>data</th>
                    <th>Biała obrona</th>
                    <th>Biały atak</th>
                    <th>Niebieska obrona</th>
                    <th>Niebieski atak</th>
                    <th>session ID</th>
                </tr>
                </thead>
                <c:forEach items="${model.historyEntries}" var="historyEntry" end="9">
                    <tr>
                        <td>${historyEntry.generation.genInt}</td>
                        <td>${historyEntry.generation.genDateString}</td>
                        <td style="background-color: ${historyEntry.table.whiteDefense.color}">${historyEntry.table.whiteDefense}</td>
                        <td style="background-color: ${historyEntry.table.whiteAttack.color}">${historyEntry.table.whiteAttack}</td>
                        <td style="background-color: ${historyEntry.table.blueDefense.color}">${historyEntry.table.blueDefense}</td>
                        <td style="background-color: ${historyEntry.table.blueAttack.color}">${historyEntry.table.blueAttack}</td>
                        <td>${historyEntry.sessionId}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
    <div>
        <c:if test="${not empty model.statistics.allPlayers}">
            <table border="3">
                <thead>
                <tr>
                    <th>Gracz</th>
                    <th>Biały obrona</th>
                    <th>Biały atak</th>
                    <th>Niebieski obrona</th>
                    <th>Niebieski atak</th>
                </tr>
                </thead>
                <c:forEach items="${model.statistics.allPlayers}" var="plyr">
                    <tr>
                        <td>${plyr}</td>
                        <td>${model.statistics.getPercentWhiteDefense(plyr)}%</td>
                        <td>${model.statistics.getPercentWhiteAttack(plyr)}%</td>
                        <td>${model.statistics.getPercentBlueDefense(plyr)}%</td>
                        <td>${model.statistics.getPercentBlueAttack(plyr)}%</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</form:form>
</body>
</html>