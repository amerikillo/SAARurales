<%-- 
    Document   : index
    Created on : 17/02/2014, 03:34:46 PM
    Author     : Americo
--%>

<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="conn.*" %>
<!DOCTYPE html>
<%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddhhmmss"); %>
<%java.text.DateFormat df2 = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
<%java.text.DateFormat df3 = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
<%
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    DecimalFormatSymbols custom = new DecimalFormatSymbols();
    custom.setDecimalSeparator('.');
    custom.setGroupingSeparator(',');
    formatter.setDecimalFormatSymbols(custom);
    HttpSession sesion = request.getSession();
    String usua = "";
    if (sesion.getAttribute("nombre") != null) {
        usua = (String) sesion.getAttribute("nombre");
    } else {
        response.sendRedirect("index.jsp");
    }
    ConectionDB con = new ConectionDB();

    String Clave = "";
    try {
        Clave = request.getParameter("Clave");
    } catch (Exception e) {

    }
    if (Clave == null) {
        Clave = "";
    }
    String CB = "";
    try {
        CB = request.getParameter("CB");
    } catch (Exception e) {

    }
    if (CB == null) {
        CB = "";
    }
    String qryLote = "";
    try {
        System.out.println(request.getParameter("accion"));
        if (request.getParameter("accion").equals("CB")) {
            con.conectar();
            ResultSet rset = con.consulta("select F_ClaPro from tb_cb where F_Cb = '" + request.getParameter("CB") + "' group by F_ClaPro");
            while (rset.next()) {
                Clave = rset.getString(1);
            }
            con.cierraConexion();
            qryLote = ("select F_ClaPro, F_ClaLot, DATE_FORMAT(F_FecCad, '%d/%m/%Y') from tb_lote where F_Cb = '" + request.getParameter("CB") + "' group by F_FecCad, F_ClaLot");
        }
        if (request.getParameter("accion").equals("Clave")) {
            qryLote = ("select F_ClaPro, F_ClaLot, DATE_FORMAT(F_FecCad, '%d/%m/%Y') from tb_lote where F_ClaPro = '" + request.getParameter("Clave") + "' group by F_FecCad, F_ClaLot");
            CB = "";
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Estilos CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" href="css/cupertino/jquery-ui-1.10.3.custom.css" />
        <link href="css/navbar-fixed-top.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.css">
        <!---->
        <title>SIALSS</title>
    </head>
    <body>
        <div class="container">
            <h1>SIALSS</h1>
            <h4>Módulo - Sistema de Administración de Almacenes (SAA)</h4>
            <!--div class="navbar navbar-default">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="main_menu.jsp">Inicio</a>
                    </div>
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Entradas<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="captura.jsp">Entrada Manual</a></li>
                                    <li><a href="compraAuto2.jsp">Entrada Automática OC ISEM</a></li>
                                    <li><a href="reimpresion.jsp">Reimpresión de Compras</a></li>
                                    <li><a href="ordenesCompra.jsp">Órdenes de Compras</a></li>
                                    <li><a href="kardexClave.jsp">Kardex Claves</a></li>
                                    <li><a href="Ubicaciones/Consultas.jsp">Ubicaciones</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Facturación<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="requerimiento.jsp">Carga de Requerimiento</a></li>
                                    <li><a href="factura.jsp">Facturación Automática</a></li>
                                     <li><a href="reimp_factura.jsp">Administrar Remisiones</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Catálogos<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="medicamento.jsp">Catálogo de Medicamento</a></li>
                                    <li><a href="catalogo.jsp">Catálogo de Proveedores</a></li>
                                    <li><a href="marcas.jsp">Catálogo de Marcas</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Fecha Recibo<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="Entrega.jsp">Fecha de Recibo en CEDIS</a></li>     
                                    <li><a href="historialOC.jsp">Historial OC</a></li>                                  
                                </ul>
                            </li>
            <!--li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">ADASU<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="../captura.jsp">Captura de Insumos</a></li>
                    <li class="divider"></li>
                    <li><a href="../catalogo.jsp">Catálogo de Proveedores</a></li>
                    <li><a href="../reimpresion.jsp">Reimpresión de Docs</a></li>
                </ul>
            </li>
            
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href=""><span class="glyphicon glyphicon-user"></span> <%=usua%></a></li>
            <li class="active"><a href="index.jsp"><span class="glyphicon glyphicon-log-out"></span></a></li>
        </ul>
    </div><!--/.nav-collapse >
</div>
</div-->
            <hr/>
        </div>
        <div style="width: 90%; margin: auto;">
            <h3>
                Kardex de Insumo
            </h3>

            <div class="panel panel-primary">
                <div class="panel-heading">
                    Criterios de Búsqueda
                </div>
                <div class="panel-body">
                    <form name="FormKardex" action="kardexClave.jsp" method="Post">
                        <div class="row">
                            <h4 class="col-sm-1">C.B.</h4>
                            <div class="col-sm-2">
                                <input class="form-control" name="CB" id="CB" type="text" value="<%=CB%>"/>
                            </div>
                            <div class="col-sm-2">
                                <button class="btn btn-primary" name="accion" value="CB">Por Código de Barras</button>
                            </div> 
                            <h4 class="col-sm-1">Clave</h4>
                            <div class="col-sm-2">
                                <input class="form-control" name="Clave" id="Clave" type="text" value="<%=Clave%>"/>
                            </div>
                            <div class="col-sm-2">
                                <button class="btn btn-primary" name="accion" value="Clave">Por Clave</button>
                            </div>
                        </div>
                        <div class="row">
                            <h4 class="col-sm-1">Lote</h4>
                            <div class="col-sm-2">
                                <select class="form-control" name="Lote" id="Lote" onchange="cambiaLoteCadu(this);">
                                    <option value="">-Lote-</option>
                                    <%
                                        try {
                                            con.conectar();
                                            ResultSet rsetLote = con.consulta(qryLote);
                                            while (rsetLote.next()) {
                                    %>
                                    <option><%=rsetLote.getString(2)%></option>
                                    <%
                                            }
                                            con.cierraConexion();
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    %>
                                </select>
                            </div>
                            <h4 class="col-sm-1">Caducidad</h4>
                            <div class="col-sm-2">
                                <select class="form-control" name="Cadu" id="Cadu">
                                    <option value="">-Caducidad-</option>
                                    <%
                                        try {
                                            con.conectar();
                                            ResultSet rsetLote = con.consulta(qryLote);
                                            while (rsetLote.next()) {
                                    %>
                                    <option><%=rsetLote.getString(3)%></option>
                                    <%
                                            }
                                            con.cierraConexion();
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="col-sm-5">
                                <button class="btn btn-primary" name="accion" value="Buscar">Buscar</button>
                                <%
                                    try {
                                        if (!request.getParameter("Lote").equals("")) {
                                %>
                                <a class="btn btn-success" a href="gnrKardexClave.jsp?Clave=<%=Clave%>&Lote=<%=request.getParameter("Lote")%>&Cadu=<%=request.getParameter("Cadu")%>">Generar reporte de Trazabilidad</a>
                                <%
                                        }
                                    } catch (Exception e) {

                                    }
                                %>
                            </div>
                        </div>
                    </form>
                    <hr/>
                    <h3>Clave: <%=Clave%></h3>
                    <h4>Lote: 
                        <%
                            if (request.getParameter("Lote") != null) {
                                out.println(request.getParameter("Lote"));
                            }
                        %>
                    </h4>
                    <h4>Caducidad: 
                        <%
                            if (request.getParameter("Cadu") != null) {
                                out.println(request.getParameter("Cadu"));
                            }
                        %>
                    <%
                        try {
                            con.conectar();
                            ResultSet rset = con.consulta("select F_DesPro from tb_medica where F_ClaPro = '" + Clave + "'");
                            while (rset.next()) {
                                out.println("<h4>" + rset.getString(1) + "</h4>");
                            }
                            con.cierraConexion();
                        } catch (Exception e) {

                        }
                    %>
                    <br/>
                    <h4>Existencia Actual</h4>
                    <%
                        try {
                            con.conectar();
                            ResultSet rset = con.consulta("select SUM(F_ExiLot) from tb_lote where F_ClaPro = '" + Clave + "' and F_ClaLot ='" + request.getParameter("Lote") + "' and F_FecCad = STR_TO_Date('" + request.getParameter("Cadu") + "', '%d/%m/%Y')  and F_ExiLot !=0");
                            while (rset.next()) {
                                String Total = "0";
                                Total = rset.getString(1);
                                if (Total == null) {
                                    Total = "0";
                                }
                    %>
                    <h4>Total: <%=formatter.format(Integer.parseInt(Total))%></h4>
                    <br/>
                    <%
                            }
                            con.cierraConexion();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    %>
                    <div style="width:100%; margin: auto" class="panel panel-success panel-body table-responsive">
                        <h3>Ingresos / Egresos</h3>
                        <table class="table table-bordered table-striped" width="100%" id="kardexTab2">
                            <thead> 
                                <tr>
                                    <td>No. Mov</td>
                                    <td>Usuario</td>
                                    <td>Documento</td>
                                    <td>Remisión</td>
                                    <td>Proveedor</td>
                                    <td>Factura</td>
                                    <td>Punto de Entrega</td>
                                    <td>Concepto</td>
                                    <td>Clave</td>
                                    <td>Lote</td>
                                    <td>Caducidad</td>
                                    <td>Cantidad</td>
                                    <td>Ubicacion</td>
                                    <td>Fecha</td>
                                    <td>Hora</td>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    try {
                                        con.conectar();
                                        ResultSet rset = con.consulta("select m.F_User, m.F_ConMov, c.F_DesCon, m.F_ProMov, l.F_ClaLot,  DATE_FORMAT(l.F_FecCad, '%d/%m/%Y'), (m.F_CantMov*m.F_SigMov), m.F_CostMov, u.F_DesUbi, DATE_FORMAT(m.F_FecMov, '%d/%m/%Y'), m.F_hora, m.F_DocMov, m.F_IdMov FROM tb_movinv m, tb_coninv c, tb_ubica u, tb_lote l WHERE m.F_ConMov = c.F_IdCon AND m.F_UbiMov = u.F_ClaUbi AND m.F_LotMov = l.F_FolLot and m.F_ProMov = '" + Clave + "' and l.F_ClaLot ='" + request.getParameter("Lote") + "' and l.F_FecCad=STR_TO_Date('" + request.getParameter("Cadu") + "', '%d/%m/%Y') and m.F_ConMov!=1000 GROUP BY m.F_IdMov ORDER BY m.F_IdMov");
                                        while (rset.next()) {
                                            String Documento = "", Cliente = "", Provoeedor = "", FactRemi = "";
                                            if (rset.getString(2).equals("1")) {
                                                ResultSet rset2 = con.consulta("select F_OrdCom, F_Provee from tb_compra where F_ClaDoc = '" + rset.getString(12) + "' ");
                                                while (rset2.next()) {
                                                    Documento = rset2.getString(1);
                                                    ResultSet rset3 = con.consulta("select F_NomPro from tb_proveedor where F_ClaProve = '" + rset2.getString(2) + "' ");
                                                    while (rset3.next()) {
                                                        Provoeedor = rset3.getString(1);
                                                    }
                                                }
                                            }
                                            if (rset.getString(2).equals("51")) {
                                                ResultSet rset2 = con.consulta("select F_NomCli, F_ClaDoc from tb_factura f, tb_uniatn u where u.F_ClaCli = f.F_ClaCli and F_ClaDoc = '" + rset.getString(12) + "' group by F_ClaDoc ");
                                                while (rset2.next()) {
                                                    Cliente = rset2.getString(1);
                                                    FactRemi = rset2.getString(2);
                                                }
                                            }
                                            if (rset.getString(2).equals("4")) {
                                                ResultSet rset2 = con.consulta("select F_OrdCom, F_Provee from tb_compra where F_ClaDoc = '" + rset.getString(12) + "' group by F_ClaDoc ");
                                                while (rset2.next()) {
                                                    Documento = rset2.getString(1);
                                                    ResultSet rset3 = con.consulta("select F_NomPro from tb_proveedor where F_ClaProve = '" + rset2.getString(2) + "' ");
                                                    while (rset3.next()) {
                                                        Provoeedor = rset3.getString(1);
                                                    }
                                                }
                                                rset2 = con.consulta("select F_NomCli, F_ClaDoc from tb_factura f, tb_uniatn u where u.F_ClaCli = f.F_ClaCli and F_ClaDoc = '" + rset.getString(12) + "' group by F_ClaDoc ");
                                                while (rset2.next()) {
                                                    Cliente = rset2.getString(1);
                                                    FactRemi = rset2.getString(2);
                                                }
                                            }
                                            if (rset.getString(2).equals("3")) {
                                                ResultSet rset2 = con.consulta("select F_NomCli, F_ClaDoc from tb_factura f, tb_uniatn u where u.F_ClaCli = f.F_ClaCli and F_ClaDoc = '" + rset.getString(12) + "' group by F_ClaDoc ");
                                                while (rset2.next()) {
                                                    Cliente = rset2.getString(1);
                                                    FactRemi = rset2.getString(2);
                                                }
                                            }
                                %>
                                <tr>
                                    <td><%=rset.getString("F_IdMov")%></td>
                                    <td><%=rset.getString(1)%></td>
                                    <td><%=Documento%></td>
                                    <td>
                                        <%
                                            if (!Documento.equals("")) {
                                                ResultSet rset2 = con.consulta("select F_FolRemi from tb_compra where F_ClaPro = '" + rset.getString("F_ProMov") + "' and F_ClaDoc = '" + rset.getString("F_DocMov") + "' group by F_ClaDoc ");
                                                while (rset2.next()) {
                                                    out.println(rset2.getString("F_FolRemi"));
                                                }
                                                //out.println(rset.getString("F_FolRemi"));
                                            }
                                        %>
                                    </td>
                                    <td><%=Provoeedor%></td>
                                    <td><%=FactRemi%></td>
                                    <td><%=Cliente%></td>
                                    <td><%=rset.getString(3)%></td>
                                    <td><%=rset.getString(4)%></td>
                                    <td><%=rset.getString(5)%></td>
                                    <td><%=rset.getString(6)%></td>
                                    <td><%=formatter.format(rset.getInt(7))%></td>
                                    <td><%=rset.getString(9)%></td>
                                    <td><%=rset.getString(10)%></td>
                                    <td><%=rset.getString(11)%></td>
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


                    <div style="width:100%; margin: auto" class="panel panel-body panel-danger table-responsive">
                        <h3>Redistribución en Almacén</h3>
                        <table class="table table-bordered table-striped" width="100%" id="kardexTab">
                            <thead> 
                                <tr>
                                    <td>No. Mov</td>
                                    <td>Usuario</td>

                                    <td>Concepto</td>
                                    <td>Clave</td>
                                    <td>Lote</td>
                                    <td>Caducidad</td>
                                    <td>Cantidad</td>
                                    <td>Ubicacion</td>
                                    <td>Fecha</td>
                                    <td>Hora</td>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    try {
                                        con.conectar();
                                        ResultSet rset = con.consulta("select m.F_User, m.F_ConMov, c.F_DesCon, m.F_ProMov, l.F_ClaLot,  DATE_FORMAT(l.F_FecCad, '%d/%m/%Y'), (m.F_CantMov*m.F_SigMov), m.F_CostMov, u.F_DesUbi, DATE_FORMAT(m.F_FecMov, '%d/%m/%Y'), m.F_hora, m.F_DocMov, com.F_FolRemi, m.F_IdMov FROM tb_movinv m, tb_coninv c, tb_ubica u, tb_lote l, tb_compra com WHERE l.F_FolLot = com.F_Lote and m.F_ConMov = c.F_IdCon AND m.F_UbiMov = u.F_ClaUbi AND m.F_LotMov = l.F_FolLot and m.F_ProMov = '" + Clave + "' and l.F_ClaLot ='" + request.getParameter("Lote") + "' and l.F_FecCad=STR_TO_Date('" + request.getParameter("Cadu") + "', '%d/%m/%Y') and (m.F_ConMov=1000 or m.F_ConMov=1) GROUP BY m.F_IdMov ORDER BY m.F_IdMov");
                                        while (rset.next()) {
                                            String Documento = "", Cliente = "", Provoeedor = "", FactRemi = "";
                                            if (rset.getString(2).equals("1")) {
                                                ResultSet rset2 = con.consulta("select F_OrdCom, F_Provee from tb_compra where F_ClaDoc = '" + rset.getString(12) + "' ");
                                                while (rset2.next()) {
                                                    Documento = rset2.getString(1);
                                                    ResultSet rset3 = con.consulta("select F_NomPro from tb_proveedor where F_ClaProve = '" + rset2.getString(2) + "' ");
                                                    while (rset3.next()) {
                                                        Provoeedor = rset3.getString(1);
                                                    }
                                                }
                                            }
                                            if (rset.getString(2).equals("51")) {
                                                ResultSet rset2 = con.consulta("select F_NomCli, F_ClaDoc from tb_factura f, tb_uniatn u where u.F_ClaCli = f.F_ClaCli and F_ClaDoc = '" + rset.getString(12) + "' ");
                                                while (rset2.next()) {
                                                    Cliente = rset2.getString(1);
                                                    FactRemi = rset2.getString(2);
                                                }
                                            }
                                            if (rset.getString(2).equals("3")) {
                                                ResultSet rset2 = con.consulta("select F_NomCli, F_ClaDoc from tb_factura f, tb_uniatn u where u.F_ClaCli = f.F_ClaCli and F_ClaDoc = '" + rset.getString(12) + "' ");
                                                while (rset2.next()) {
                                                    Cliente = rset2.getString(1);
                                                    FactRemi = rset2.getString(2);
                                                }
                                            }
                                %>
                                <tr>
                                    <td><%=rset.getString("F_IdMov")%></td>
                                    <td><%=rset.getString(1)%></td>

                                    <td><%=rset.getString(3)%></td>
                                    <td><%=rset.getString(4)%></td>
                                    <td><%=rset.getString(5)%></td>
                                    <td><%=rset.getString(6)%></td>
                                    <td><%=formatter.format(rset.getInt(7))%></td>
                                    <td><%=rset.getString(9)%></td>
                                    <td><%=rset.getString(10)%></td>
                                    <td><%=rset.getString(11)%></td>
                                </tr>
                                <%
                                        }
                                        con.cierraConexion();
                                    } catch (Exception e) {

                                    }
                                %>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
        <br><br><br>
        <div class="navbar navbar-fixed-bottom navbar-inverse">
            <div class="text-center text-muted">
                GNK Logística || Desarrollo de Aplicaciones 2009 - 2014 <span class="glyphicon glyphicon-registration-mark"></span><br />
                Todos los Derechos Reservados
            </div>
        </div>
    </body>
    <!-- 
    ================================================== -->
    <!-- Se coloca al final del documento para que cargue mas rapido -->
    <!-- Se debe de seguir ese orden al momento de llamar los JS -->
    <script src="js/jquery-1.9.1.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery-ui-1.10.3.custom.js"></script>
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.bootstrap.js"></script>
    <script>
                                    $(document).ready(function () {
                                        $('#kardexTab').dataTable();
                                        $('#kardexTab2').dataTable();
                                    });

                                    function cambiaLoteCadu(elemento) {
                                        var indice = elemento.selectedIndex;
                                        document.getElementById('Cadu').selectedIndex = indice;
                                    }
    </script>
</html>

