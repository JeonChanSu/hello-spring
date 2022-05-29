<!DOCTYPE HTML>

<%@page import="hello.hellospring.dao.MemberDao" %>
<html xmlns:th="http://www.thymeleaf.org">


<body>
<form method="post">
    <div class="container">

        <div>
            <table>
                <thead>
                <tr>
                    <th>#</th>
                    <th>이름</th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="member : ${members}">
                    <td th:text="${member.id}"></td>
                    <td th:text="${member.name}"></td>
                </tr>
                </tbody>
            </table>


        </div>

        <div class="form-group">
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" placeholder="삭제할 번호를 입력하세요">
        </div>

        <button type="submit">삭제</button>

    </div>
</form>
</body>
</html>