<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<%@ page import="servlet.CercaServlet"%>
<script>
<%!CercaServlet servlet = new CercaServlet();%>
	function aggiornaListe() {
		<%servlet.listaAttr(request, response);%>
		<%servlet.resetAttrSel(request, response);%>	
	}
	
	function addAttrSel(){
		<%servlet.addAttrSel(request, response);%>
}

</script>
</head>
<title>Cerca Stanza</title>
<!-- appena viene caricata la pagina si vanno ad aggiornare le liste, per prima quella delle attrezzature -->
<body leftmargin="0" topmargin="0" onload="aggiornaListe()">
	<!-- questo form contiene i pulsanti per uscire, creare una nuova stanza e un nuovo modello -->
	<form action="CercaServlet" method="GET">
		<fieldset>
			<table>
				<tbody>
					<tr>
						<td><input name="esci" type="submit" value="Esci"></td>
						<td><input name="creaStanza" type="submit"
							value="Crea/Modifica Stanza"></td>
						<td><input name="creaModello" type="submit"
							value="Crea/Modifica Modello"></td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
	<!-- form per aggiungere le attrezzature delle stanze da ricercare -->
	<form action="CercaServlet" method="GET">
		<fieldset>
			<legend> Scegliere le attrezzature per ricercare la stanza</legend>
			<table>
				<tbody>
					<tr>
						<td><select name="attrezzatura" required>
								<option label=Attrezzatura></option>
								<c:forEach var="attr" items="${listaAttrezzatura}">
									<option><c:out value="${attr}" /></option>
								</c:forEach>
						</select></td>
						<td><input type="number" name="minimo" min="0" max="1000"
							step="1" placeholder="Min" required></td>
						<td><input type="number" name="massimo" min="0" max="1000"
							step="1" placeholder="Max" required></td>
						<td>
							<fieldset>
								<table>
									<tbody>
										<tr>
											<td><input name="aggAttr" type="submit" value="Aggiungi" onSubmit="addAttrSel()"></td>
											<td><input name="elAttr" type="submit" value="Elimina"></td>
											<td><input name="cerca" type="submit" value="Cerca"></td>
										</tr>
									</tbody>
								</table>
							</fieldset>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- lista delle attrezzature scelte -->
			<table id="TabAttrezzatura">
				<thead>
					<tr>
						<th>Attrezzatura</th>
						<th>Minimo</th>
						<th>Massimo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="attrSel" items="${AttrSelezionata}">
						<tr>
							<td><c:out value="${attrSel.getNome}" /></td>
							<td><c:out value="${attrSel.getMin}" /></td>
							<td><c:out value="${attrSel.getMax}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</form>
	<!-- form della tabella delle stanze trovate -->
	<form action="CercaServlet" method="POST">
		<table>
			<tr>
				<td></td>
			</tr>
		</table>
	</form>
	<!-- form della tabella di tutte le attrezzature della stanza cliccata -->
	<form action="CercaServlet" method="POST">
		<table>
			<tr>
				<td></td>
			</tr>
		</table>
	</form>

</body>
</html>