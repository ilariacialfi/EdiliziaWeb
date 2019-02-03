<%@ page import="servlet.CercaServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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
	<!-- questo form contiene i pulsanti per uscire, creare una nuova stanza e un nuovo modello -->
	<form action="CercaServlet" method="GET">
		<fieldset style="border-color: #2e8b57;">
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

		<!-- form per aggiungere le attrezzature delle stanze da ricercare -->

		<fieldset style="border-color: #2e8b57;">
			<legend> Scegliere le attrezzature per ricercare la stanza o
				selezionarle per eliminarle</legend>
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
							<fieldset style="border-color: #63B063;">
								<table>
									<tbody>
										<tr>
											<td><input name="aggAttr" type="submit" value="Aggiungi" ></td>
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
					<tr bgcolor="#63B063">
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

		<!-- form della tabella delle stanze trovate -->

		<fieldset style="border-color: #2e8b57;">
			<legend> Le stanze che contengono le attrezzature
				selezionate saranno visualizzate qui sotto: </legend>
			<table id="TabStanze" align="center">
				<thead>
					<tr bgcolor="#63B063">
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
					<th>Selezionare una tra le stanze per visualizzarne i dettagli:</th>
					<td><select name="stanzeSel">
							<option label=Stanze></option>
							<c:forEach var="stanze" items="${StanzeSelezionate}">
								<option><c:out value="${stanze}" /></option>
							</c:forEach>
					</select></td>
					<td><input name="Ok" type="submit" value="Ok"></td>
				</tr>
			</table>


			<!-- form della tabella di tutte le attrezzature della stanza cliccata -->
			<table id="TabAttrStanza" align="center">
				<thead>
					<tr bgcolor="#63B063">
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
		</fieldset>
	</form>


</body>
</html>