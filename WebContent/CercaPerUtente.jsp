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
	
<%servlet.aggiornaAttrSel(request, response);%>
	
<%servlet.aggiornaStanze(request, response);%>

	}
</script>

</head>
<title>Cerca Stanza</title>
<!-- appena viene caricata la pagina si vanno ad aggiornare le liste, per prima quella delle attrezzature -->
<body leftmargin="0" topmargin="0" onload="aggiornaListe()">
	<!-- pulsante per uscire -->
	<form action="CercaServlet" method="GET">
		<fieldset>
			<table>
				<tbody>
					<tr>
						<td><input name="esci" type="submit" value="Esci"></td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
	<!-- form per aggiungere le attrezzature delle stanze da ricercare -->
	<form action="CercaServlet" method="GET">
		<fieldset>
			<legend> Scegliere le attrezzature per ricercare la stanza o selezionarle per eliminarle</legend>
			<table align="center">
				<tbody>
					<tr>
						<td><select name="attrezzatura">
								<option label=Attrezzatura></option>
								<c:forEach var="attr" items="${listaAttrezzatura}">
									<option><c:out value="${attr}" /></option>
								</c:forEach>
						</select></td>
						<td><input type="number" name="minimo" min="0" max="1000"
							step="1" placeholder="Min"></td>
						<td><input type="number" name="massimo" min="0" max="1000"
							step="1" placeholder="Max"></td>
						<td>
							<fieldset>
								<table>
									<tbody>
										<tr>
											<td><input name="aggAttr" type="submit" value="Aggiungi"></td>
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
			<table id="TabAttrezzatura" align="center">
				<thead>
					<tr>
						<th width=25% align="center">Attrezzatura</th>
						<th width=25% align="center">Minimo</th>
						<th width=25% align="center">Massimo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="attrSel" items="${AttrSelezionata}">
						<tr>
							<td width=25% align="center"><c:out
									value="${attrSel.getNome()}" /></td>
							<td width=25% align="center"><c:out
									value="${attrSel.getMin()}" /></td>
							<td width=25% align="center"><c:out
									value="${attrSel.getMax()}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</form>
	<!-- form della tabella delle stanze trovate -->
	<form action="CercaServlet" method="GET">
		<fieldset>
			<legend> Le stanze che contengono le attrezzature
				selezionate saranno visualizzate qui sotto: </legend>
			<table id="TabStanze" align="center">
				<thead>
					<tr>
						<th width=25% align="center">Stanza</th>
						<th width=25% align="center">Edificio</th>
						<th width=25% align="center">Piano</th>
						<th width=25% align="center">Tipo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stanza" items="${Stanze}">
						<tr>
							<td width=25% align="center"><c:out
									value="${stanza.getNome()}" /></td>
							<td width=25% align="center"><c:out
									value="${stanza.getEdificio()}" /></td>
							<td width=25% align="center"><c:out
									value="${stanza.getPiano()}" /></td>
							<td width=25% align="center"><c:out
									value="${stanza.getTipo()}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<table align="center">
				<tr>
				<th>Selezionare una tra le stanze per visualizzarne i dettagli: </th>
					<td><select name="stanzeSel">
							<option label=Stanze></option>
							<c:forEach var="stanze" items="${StanzeSelezionate}">
								<option><c:out value="${stanze}" /></option>
							</c:forEach>
					</select></td>
					<td><input name="Ok" type="submit" value="Ok"></td>
				</tr>
			</table>
		</fieldset>
	</form>
	<!-- form della tabella di tutte le attrezzature della stanza cliccata -->
	<form action="CercaServlet" method="GET">
		<table id="TabAttrStanza" align="center">
			<thead>
				<tr>
					<th width=25% align="center">Attrezzatura</th>
					<th width=25% align="center">Quantità</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="attrStanza" items="${AttrezzaturaStanza}">
					<tr>
						<td width=25% align="center"><c:out
								value="${attrStanza.getAttr()}" /></td>
						<td width=25% align="center"><c:out
								value="${attrStanza.getQuantita()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>

</body>
</html>