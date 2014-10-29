package org.apache.jsp.facturacionRural;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import conn.*;

public final class verConcentradoRur_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddhhmmss"); 
      out.write('\n');
java.text.DateFormat df2 = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
      out.write('\n');
java.text.DateFormat df3 = new java.text.SimpleDateFormat("dd/MM/yyyy"); 
      out.write('\n');


    HttpSession sesion = request.getSession();
    String usua = "";
    String tipo = "";
    if (sesion.getAttribute("nombre") != null) {
        usua = (String) sesion.getAttribute("nombre");
        tipo = (String) sesion.getAttribute("Tipo");
    } else {
        response.sendRedirect("index.jsp");
    }
    ConectionDB con = new ConectionDB();

    String fol_gnkl = "", fol_remi = "", orden_compra = "", fecha = "";
    try {
        if (request.getParameter("accion").equals("buscar")) {
            fol_gnkl = request.getParameter("fol_gnkl");
            fol_remi = request.getParameter("fol_remi");
            orden_compra = request.getParameter("orden_compra");
            fecha = request.getParameter("fecha");
        }
    } catch (Exception e) {

    }
    if (fol_gnkl == null) {
        fol_gnkl = "";
        fol_remi = "";
        orden_compra = "";
        fecha = "";
    }

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <!-- Estilos CSS -->\n");
      out.write("        <link href=\"css/bootstrap.css\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/cupertino/jquery-ui-1.10.3.custom.css\" />\n");
      out.write("        <link href=\"css/navbar-fixed-top.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"css/datepicker3.css\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/dataTables.bootstrap.css\">\n");
      out.write("        <!---->\n");
      out.write("        <title>SIE Sistema de Ingreso de Entradas</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <h1>SIALSS</h1>\n");
      out.write("            <h4>SISTEMA INTEGRAL DE ADMINISTRACIÓN Y LOGÍSTICA PARA SERVICIOS DE SALUD</h4>\n");
      out.write("            <div class=\"navbar navbar-default\">\n");
      out.write("                <div class=\"container\">\n");
      out.write("                    <div class=\"navbar-header\">\n");
      out.write("                        <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n");
      out.write("                            <span clss=\"icon-bar\"></span>\n");
      out.write("                            <span class=\"icon-bar\"></span>\n");
      out.write("                            <span class=\"icon-bar\"></span>\n");
      out.write("                            <span class=\"icon-bar\"></span>\n");
      out.write("                        </button>\n");
      out.write("                        <a class=\"navbar-brand\" href=\"main_menu.jsp\">Inicio</a>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"navbar-collapse collapse\">\n");
      out.write("                        <ul class=\"nav navbar-nav\">\n");
      out.write("                            <li class=\"dropdown\">\n");
      out.write("                                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Entradas<b class=\"caret\"></b></a>\n");
      out.write("                                <ul class=\"dropdown-menu\">\n");
      out.write("                                    ");

                                        if (tipo.equals("2") || tipo.equals("3") || tipo.equals("1")) {
                                    
      out.write("\n");
      out.write("\n");
      out.write("                                    <li><a href=\"captura.jsp\">Entrada Manual</a></li>\n");
      out.write("                                    <li><a href=\"compraAuto2.jsp\">Entrada Automática OC ISEM</a></li>\n");
      out.write("                                    <li class=\"divider\"></li>\n");
      out.write("                                    <li><a href=\"hh/compraAuto3.jsp\">HANDHELD | Entrada Automática OC ISEM</a></li>\n");
      out.write("                                    <li class=\"divider\"></li>\n");
      out.write("                                        ");

                                            }
                                            if (tipo.equals("2") || tipo.equals("3") || tipo.equals("5")) {
                                        
      out.write("\n");
      out.write("                                    <li><a href=\"verificarCompraAuto.jsp\">Verificar OC</a></li>\n");
      out.write("                                        ");

                                            }
                                        
      out.write("\n");
      out.write("                                    <li><a href=\"#\" onclick=\"window.open('reimpresion.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Reimpresión de Compras</a></li>\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('ordenesCompra.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Órdenes de Compras</a></li>\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('kardexClave.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Kardex Claves</a></li>\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('Ubicaciones/Consultas.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Ubicaciones</a></li>\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('creaMarbetes.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Generar Marbetes</a></li>\n");
      out.write("                                    \n");
      out.write("                                        ");

                                            if (tipo.equals("5") || tipo.equals("3")) {
                                        
      out.write("\n");
      out.write("                                    <li class=\"divider\"></li>\n");
      out.write("                                    <li><a href=\"hh/insumoNuevoRedist.jsp\">Redistribución HH</a></li>\n");
      out.write("                                    <li class=\"divider\"></li>\n");
      out.write("                                        ");

                                            }
                                        
      out.write("\n");
      out.write("                                        ");

                                            if (usua.equals("oscar")) {
                                        
      out.write("\n");
      out.write("                                    <li class=\"divider\"></li>\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('devolucionesInsumo.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Cambio Físico</a></li>\n");
      out.write("                                    <li class=\"divider\"></li>\n");
      out.write("                                        ");

                                            }
                                        
      out.write("\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('Ubicaciones/index_Marbete.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Marbete de Salida</a></li>\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('Ubicaciones/index_Marbete_resto.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Marbete de Resto</a></li>\n");
      out.write("                                    <!--li><a href=\"#\"  onclick=\"window.open('verDevolucionesEntrada.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Imprimir Devoluciones</a></li>\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('devolucionesInsumo.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Devolver</a></li-->\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"dropdown\">\n");
      out.write("                                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Facturación<b class=\"caret\"></b></a>\n");
      out.write("                                <ul class=\"dropdown-menu\">\n");
      out.write("                                    ");

                                        if (tipo.equals("7") || tipo.equals("3")) {
                                    
      out.write("\n");
      out.write("                                    <li><a href=\"requerimiento.jsp\">Carga de Requerimiento</a></li>\n");
      out.write("                                    <li><a href=\"factura.jsp\">Facturación Automática</a></li>\n");
      out.write("                                        ");

                                            }
                                        
      out.write("\n");
      out.write("                                        ");

                                            if (tipo.equals("5") || tipo.equals("3") || tipo.equals("7")) {
                                        
      out.write("\n");
      out.write("                                    <li><a href=\"validacionSurtido.jsp\">Validación Surtido</a></li>\n");
      out.write("                                    <li><a href=\"validacionAuditores.jsp\">Validación Auditores</a></li>\n");
      out.write("                                        ");

                                            }
                                        
      out.write("\n");
      out.write("                                        ");

                                            if (tipo.equals("7")) {
                                        
      out.write("\n");
      out.write("                                    <li><a href=\"remisionarCamion.jsp\">Generar Remisiones</a></li>\n");
      out.write("                                    <li><a href=\"facturacionManual.jsp\">Facturación Manual</a></li>\n");
      out.write("                                        ");

                                            }
                                        
      out.write("\n");
      out.write("                                    <li><a href=\"reimp_factura.jsp\">Administrar Remisiones</a></li>\n");
      out.write("                                    <li><a href=\"reimpConcentrado.jsp\">Reimpresión Concentrados Globales</a></li>\n");
      out.write("                                    <li><a href=\"comparativoGlobal.jsp\">Comparativo Global</a></li>\n");
      out.write("\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"dropdown\">\n");
      out.write("                                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Inventario<b class=\"caret\"></b></a>\n");
      out.write("                                <ul class=\"dropdown-menu\">\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('Ubicaciones/Inventario.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Inventario</a></li>\n");
      out.write("                                        ");

                                            if (tipo.equals("5") || tipo.equals("3") || tipo.equals("7")) {
                                        
      out.write("\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('movimientosUsuarioInventario.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Movimientos por Usuario</a></li>\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('semaforo.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Semaforización</a></li>\n");
      out.write("                                        ");

                                            }
                                        
      out.write("\n");
      out.write("                                    <li><a href=\"#\"  onclick=\"window.open('invenCiclico/nuevoInventario.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Inventario Ciclico</a></li>\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"dropdown\">\n");
      out.write("                                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Catálogos<b class=\"caret\"></b></a>\n");
      out.write("                                <ul class=\"dropdown-menu\">\n");
      out.write("                                    <li><a href=\"#\" onclick=\"window.open('medicamento.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Catálogo de Medicamento</a></li>\n");
      out.write("                                    <li><a href=\"#\" onclick=\"window.open('catalogo.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Catálogo de Proveedores</a></li>\n");
      out.write("                                    <li><a href=\"#\" onclick=\"window.open('marcas.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Catálogo de Marcas</a></li>\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"dropdown\">\n");
      out.write("                                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Reportes<b class=\"caret\"></b></a>\n");
      out.write("                                <ul class=\"dropdown-menu\">\n");
      out.write("                                    <li><a href=\"#\" onclick=\"window.open('Entrega.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Fecha de Recibo en CEDIS</a></li> \n");
      out.write("                                    <li><a href=\"#\" onclick=\"window.open('historialOC.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Historial OC</a></li>\n");
      out.write("                                    <li><a href=\"#\" onclick=\"window.open('ReporteF.jsp', '', 'width=1200,height=800,left=50,top=50,toolbar=no')\">Reporte por Fecha Proveedor</a></li>\n");
      out.write("\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                        </ul>\n");
      out.write("                        <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                            <li><a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> ");
      out.print(usua);
      out.write("</a></li>\n");
      out.write("                            <li class=\"active\"><a href=\"index.jsp\"><span class=\"glyphicon glyphicon-log-out\"></span></a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div><!--/.nav-collapse -->\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div>\n");
      out.write("                <h3>Revisión de Concentrados por Proveedor</h3>\n");
      out.write("                <h4>Seleccione</h4>\n");
      out.write("\n");
      out.write("                <br />\n");
      out.write("                <div class=\"panel panel-primary\">\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        <table class=\"table table-bordered table-striped\" id=\"datosCompras\">\n");
      out.write("                            <thead>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>No. Folio</td>\n");
      out.write("                                    <td>Punto de entrega</td>\n");
      out.write("                                    <td>Orden de Compra</td>\n");
      out.write("                                    <td>Concentrado</td>\n");
      out.write("                                    <td>Marbetes</td>\n");
      out.write("                                    <td>Excel</td>\n");
      out.write("                                    <td>Cancelar</td>\n");
      out.write("                                </tr>\n");
      out.write("                            </thead>\n");
      out.write("                            <tbody>\n");
      out.write("                                ");

                                    try {
                                        con.conectar();
                                        try {
                                            ResultSet rset = con.consulta("SELECT u.F_NomCli, DATE_FORMAT(f.F_FecEnt, '%d/%m/%Y') as FecEnt, l.F_ClaPro,	l.F_ClaLot,	DATE_FORMAT(l.F_FecCad, '%d/%m/%Y'),	(f.F_Cant+0) as F_Cant,	l.F_Ubica,	f.F_IdFact,	l.F_Cb,	p.F_Pzs,	(f.F_Cant DIV p.F_Pzs),	(f.F_Cant MOD p.F_Pzs) FROM	tb_facttemp f,	tb_lote l,	tb_uniatn u,	tb_pzxcaja p WHERE	f.F_IdLot = l.F_IdLote AND f.F_ClaCli = u.F_ClaCli AND p.F_ClaPro = l.F_ClaPro GROUP BY f.F_IdFact;");
                                            while (rset.next()) {
                                
      out.write("\n");
      out.write("                                <tr>\n");
      out.write("\n");
      out.write("                                    <td>");
      out.print(rset.getString("F_IdFact"));
      out.write("</td>\n");
      out.write("                                    <td>");
      out.print(rset.getString("F_NomCli"));
      out.write("</td>\n");
      out.write("                                    <td>");
      out.print(rset.getString("FecEnt"));
      out.write("</td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <form action=\"reimpGlobalReq.jsp\" target=\"_blank\">\n");
      out.write("                                            <input class=\"hidden\" name=\"fol_gnkl\" value=\"");
      out.print(rset.getString("F_IdFact"));
      out.write("\">\n");
      out.write("                                            <button class=\"btn btn-block btn-primary\">Imprimir</button>\n");
      out.write("                                        </form>\n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <form action=\"reimpGlobalMarbetes.jsp\" target=\"_blank\">\n");
      out.write("                                            <input class=\"hidden\" name=\"fol_gnkl\" value=\"");
      out.print(rset.getString("F_IdFact"));
      out.write("\">\n");
      out.write("                                            <button class=\"btn btn-block btn-primary\">Imprimir</button>\n");
      out.write("                                        </form>\n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <a class=\"btn btn-block btn-primary\" href=\"gnrConcentrado.jsp?fol_gnkl=");
      out.print(rset.getString("F_IdFact"));
      out.write("\" target=\"_blank\">Descargar</a>\n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <form action=\"Facturacion\" method=\"post\">\n");
      out.write("                                            ");

                                                if (usua.equals("esteban")) {
                                            
      out.write("\n");
      out.write("                                            <input class=\"hidden\" name=\"fol_gnkl\" value=\"");
      out.print(rset.getString("F_IdFact"));
      out.write("\">\n");
      out.write("                                            <button class=\"btn btn-block btn-danger\" name=\"accion\" value=\"EliminaConcentrado\" onclick=\"return confirm('Seguro de eliminar este concentrado?')\"><span class=\"glyphicon glyphicon-remove\"></span></button>\n");
      out.write("                                                ");

                                                    }
                                                
      out.write("\n");
      out.write("                                        </form>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                ");

                                            }
                                        } catch (Exception e) {

                                        }
                                        con.cierraConexion();
                                    } catch (Exception e) {

                                    }
                                
      out.write("\n");
      out.write("                            </tbody>\n");
      out.write("                        </table>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <br><br><br>\n");
      out.write("        <div class=\"navbar navbar-fixed-bottom navbar-inverse\">\n");
      out.write("            <div class=\"text-center text-muted\">\n");
      out.write("                GNK Logística || Desarrollo de Aplicaciones 2009 - 2014 <span class=\"glyphicon glyphicon-registration-mark\"></span><br />\n");
      out.write("                Todos los Derechos Reservados\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- \n");
      out.write("================================================== -->\n");
      out.write("<!-- Se coloca al final del documento para que cargue mas rapido -->\n");
      out.write("<!-- Se debe de seguir ese orden al momento de llamar los JS -->\n");
      out.write("<script src=\"js/jquery-1.9.1.js\"></script>\n");
      out.write("<script src=\"js/bootstrap.js\"></script>\n");
      out.write("<script src=\"js/jquery-ui-1.10.3.custom.js\"></script>\n");
      out.write("<script src=\"js/bootstrap-datepicker.js\"></script>\n");
      out.write("<script src=\"js/jquery.dataTables.js\"></script>\n");
      out.write("<script src=\"js/dataTables.bootstrap.js\"></script>\n");
      out.write("<script>\n");
      out.write("                                                $(document).ready(function() {\n");
      out.write("                                                    $('#datosCompras').dataTable();\n");
      out.write("                                                });\n");
      out.write("</script>\n");
      out.write("<script>\n");
      out.write("    $(function() {\n");
      out.write("        $(\"#fecha\").datepicker();\n");
      out.write("        $(\"#fecha\").datepicker('option', {dateFormat: 'dd/mm/yy'});\n");
      out.write("    });\n");
      out.write("</script>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
