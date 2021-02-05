<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" /> <link href="${jstlCss}" rel="stylesheet" /> <script type="text/javascript"
src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> <title>Liste Articles</title>
</head>
<body>
<div class="container mt-5">
<div class="card">
<div class="card-header"> Liste des Articles
</div>
<div class="card-body">
<table class="table table-striped"> <tr>
   <th>ID</th><th>Nom Article</th><th>Prix</th><th>Date
   Création</th><th>Suppression<th>Edition</th>
</tr>
<c:forEach items="${articles}" var="p">
              <tr>
                 <td>${p.idArticle }</td>
                 <td>${p.nomArticle }</td>
                 <td>${p.prixArticle }</td>
<td><fmt:formatDate pattern="dd/MM/yyyy" value="${p.dateCreation}" /></td>
<td><a onclick="return confirm('Etes-vous sûr ?')" href="supprimerArticle?id=${p.idArticle }">Supprimer</a></td>
<td><a href="modifierArticle?id=${p.idArticle }">Edit</a></td> </tr>
            </c:forEach>
         </table>
     </div>
   </div>
   </div>
   </body>
   </html>