<%-- 
    Document   : index
    Created on : 17/02/2014, 03:34:46 PM
    Author     : Americo
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="conn.*" %>
<!DOCTYPE html>
<%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddhhmmss"); %>
<%java.text.DateFormat df2 = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
<%java.text.DateFormat df3 = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
<%

    HttpSession sesion = request.getSession();
    String usua = "", tipo = "";
    if (sesion.getAttribute("nombre") != null) {
        usua = (String) sesion.getAttribute("nombre");
        tipo = (String) sesion.getAttribute("Tipo");
    } else {
        response.sendRedirect("../index.jsp");
    }
    ConectionDB con = new ConectionDB();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SIALSS</title>
        <!-- Estilos CSS -->
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" href="../css/cupertino/jquery-ui-1.10.3.custom.css" />
        <link href="../css/navbar-fixed-top.css" rel="stylesheet">
        <!---->
    </head>
    <body>
        <div class="container">

            <h1>SIALSS</h1>
            <h4>Módulo - Sistema de Administración de Almacenes (SAA)</h4>
            
            <table class="table table-condensed table-bordered table-striped" id="tablaRequerimentos">
                <thead>
                    <tr>
                        <td class="text-center">Doc</td>
                        <td class="text-center">Cliente</td>
                        <td class="text-center">Entrega</td>
                        <td class="text-center">Clave</td>
                        <td class="text-center">Cantidad</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        try {
                            con.conectar();
                            ResultSet rset = con.consulta("select r.F_IdReq, u.F_NomCli, DATE_FORMAT(r.F_FecEnt, '%d/%m/%Y'), r.F_ClaPro, r.F_PiezasReq from tb_unireqrurales r, tb_uniatn u where r.F_IdReq='"+request.getParameter("F_IdReq")+"' and r.F_ClaUni = u.F_ClaCli group by r.F_Id");
                            while (rset.next()) {
                    %>
                    <tr>
                        <td class="text-center"><%=rset.getString(1)%></td>
                        <td class="text-center"><%=rset.getString(2)%></td>
                        <td class="text-center"><%=rset.getString(3)%></td>
                        <td class="text-center"><%=rset.getString(4)%></td>
                        <td class="text-center"><%=rset.getString(5)%></td>
                    </tr>
                    <%
                            }
                            con.cierraConexion();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>

<script src="../js/jquery-1.9.1.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/jquery-ui-1.10.3.custom.js"></script>
<script src="../js/jquery.dataTables.js"></script>
<script src="../js/dataTables.bootstrap.js"></script>
<script>
                                        $(document).ready(function() {
                                            $('#tablaRequerimentos').dataTable();
                                        });
</script>
</html>
