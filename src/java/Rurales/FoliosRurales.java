/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rurales;

import conn.ConectionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Americo
 */
public class FoliosRurales extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ConectionDB con = new ConectionDB();
        try {
            try {
                if (request.getParameter("accion").equals("eliminaRequerimiento")) {
                    con.conectar();
                    con.insertar("delete from tb_unireqrurales where F_IdReq='" + request.getParameter("F_IdReq") + "'");
                    con.cierraConexion();
                    response.sendRedirect("facturacionRural/cargaRequerimento.jsp");
                }

                //-------------------------------------------------------------------------------------------------
                if (request.getParameter("accion").equals("generarRemisionesGlobal")) {

                    String FecEnt = request.getParameter("FecEnt");
                    String Clave = "", FolioLote = "";
                    int piezas = 0, existencia = 0, diferencia = 0, X = 0, FolioFactura = 0, FolFact = 0, Tipo = 0, Org = 0;

                    try {

                        con.conectar();
                        //consql.conectar();

                        con.insertar("delete from tb_lotetemp");
                        con.insertar("insert into tb_lotetemp select * from tb_lote");
                        /*ResultSet Fechaa = con.consulta("SELECT STR_TO_DATE(" + FechaE + ", '%d/%m/%Y')");
                         while (Fechaa.next()) {
                         FechaE = Fechaa.getString("STR_TO_DATE(" + FechaE + ", '%d/%m/%Y')");
                         }*/
                        ResultSet FolioFact = con.consulta("SELECT F_IndGlobal FROM tb_indice");
                        while (FolioFact.next()) {
                            FolioFactura = Integer.parseInt(FolioFact.getString("F_IndGlobal"));
                        }
                        FolFact = FolioFactura + 1;
                        con.actualizar("update tb_indice set F_IndGlobal='" + FolFact + "'");

                        ResultSet rset_cantidad = con.consulta("SELECT F_ClaPro,F_CajasReq as cajas, F_PiezasReq as piezas, F_IdReq, F_ClaUni, F_FecEnt FROM tb_unireqrurales WHERE F_FecEnt='" + FecEnt + "' and F_Status='0'");
                        while (rset_cantidad.next()) {
                            Clave = rset_cantidad.getString("F_ClaPro");
                            String ClaUni = rset_cantidad.getString("F_ClaUni");
                            int cajasReq = Integer.parseInt(rset_cantidad.getString("cajas"));
                            int piezasReq = Integer.parseInt(rset_cantidad.getString("piezas"));
                            int pzxCaja = 0;
                            ResultSet rsetCP = con.consulta("select F_Pzs from tb_pzxcaja where F_ClaPro = '" + Clave + "' ");
                            while (rsetCP.next()) {
                                pzxCaja = rsetCP.getInt(1);
                            }
                            piezas = (pzxCaja * cajasReq) + piezasReq;
                        //piezas = Integer.parseInt(rset_cantidad.getString("CANTIDAD"));

                            //INICIO DE CONSULTA MYSQL
                            ResultSet r_Org = con.consulta("SELECT F_ClaOrg FROM tb_lotetemp WHERE F_ClaPro='" + Clave + "' GROUP BY F_ClaOrg ORDER BY F_ClaOrg+0");
                            while (r_Org.next()) {
                                Org = Integer.parseInt(r_Org.getString("F_ClaOrg"));

                                if (Org == 1) {
                                    ResultSet FechaLote = con.consulta("SELECT L.F_FecCad AS F_FecCad,L.F_FolLot AS F_FolLot,(L.F_ExiLot) AS F_ExiLot, M.F_TipMed AS F_TipMed, M.F_Costo AS F_Costo, L.F_Ubica AS F_Ubica, C.F_ProVee AS F_ProVee, F_ClaLot,F_IdLote FROM tb_lotetemp L INNER JOIN tb_medica M ON L.F_ClaPro=M.F_ClaPro INNER JOIN tb_compra C ON L.F_FolLot=C.F_Lote WHERE L.F_ClaPro='" + Clave + "' AND L.F_ExiLot>'0' GROUP BY L.F_IdLote ORDER BY L.F_FecCad,L.F_IdLote ASC");
                                    while (FechaLote.next()) {
                                        FolioLote = FechaLote.getString("F_FolLot");
                                        String IdLote = FechaLote.getString("F_IdLote");
                                        existencia = Integer.parseInt(FechaLote.getString("F_ExiLot"));
                                        ResultSet rset2 = con.consulta("select sum(F_Cant) from tb_facttemp where F_IdLot = '" + IdLote + "' and F_StsFact!=5");
                                        while (rset2.next()) {
                                            existencia = existencia - rset2.getInt(1);
                                        }
                                        Tipo = Integer.parseInt(FechaLote.getString("F_TipMed"));
                                        if (existencia > 0) {
                                            if (piezas > existencia) {
                                                diferencia = piezas - existencia;
                                                con.actualizar("UPDATE tb_lotetemp SET F_ExiLot='0' WHERE F_IdLote='" + IdLote + "'");
                                                con.insertar("insert into tb_factrurtemp values('" + FolFact + "','" + rset_cantidad.getString("F_IdReq") + "','" + ClaUni + "','" + IdLote + "','" + existencia + "','" + rset_cantidad.getString("F_FecEnt") + "','0','0','')");
                                                piezas = diferencia;
                                            } else {
                                                diferencia = existencia - piezas;
                                                con.actualizar("UPDATE tb_lotetemp SET F_ExiLot='" + diferencia + "' WHERE F_IdLote='" + IdLote + "'");
                                                if (piezas > 0) {
                                                    con.insertar("insert into tb_factrurtemp values('" + FolFact + "','" + rset_cantidad.getString("F_IdReq") + "','" + ClaUni + "','" + IdLote + "','" + piezas + "','" + rset_cantidad.getString("F_FecEnt") + "','0','0','')");
                                                    con.actualizar("UPDATE tb_lotetemp SET F_ExiLot='" + diferencia + "' WHERE F_IdLote='" + IdLote + "'");
                                                }
                                                piezas = 0;
                                            }
                                        }
                                    }
                                } else {
                                    ResultSet FechaLote = con.consulta("SELECT L.F_FecCad AS F_FecCad,L.F_FolLot AS F_FolLot,(L.F_ExiLot) AS F_ExiLot, M.F_TipMed AS F_TipMed, M.F_Costo AS F_Costo, L.F_Ubica AS F_Ubica, C.F_ProVee AS F_ProVee, F_ClaLot,F_IdLote FROM tb_lotetemp L INNER JOIN tb_medica M ON L.F_ClaPro=M.F_ClaPro INNER JOIN tb_compra C ON L.F_FolLot=C.F_Lote WHERE L.F_ClaPro='" + Clave + "' AND L.F_ExiLot>'0' GROUP BY L.F_IdLote ORDER BY L.F_IdLote,L.F_FecCad ASC");
                                    while (FechaLote.next()) {
                                        FolioLote = FechaLote.getString("F_FolLot");
                                        String IdLote = FechaLote.getString("F_IdLote");
                                        existencia = Integer.parseInt(FechaLote.getString("F_ExiLot"));
                                        ResultSet rset2 = con.consulta("select sum(F_Cant) from tb_facttemp where F_IdLot = '" + IdLote + "' and F_StsFact!=5");
                                        while (rset2.next()) {
                                            existencia = existencia - rset2.getInt(1);
                                        }
                                        Tipo = Integer.parseInt(FechaLote.getString("F_TipMed"));
                                        if (existencia > 0) {
                                            if (piezas > existencia) {
                                                diferencia = piezas - existencia;
                                                con.actualizar("UPDATE tb_lotetemp SET F_ExiLot='0' WHERE F_IdLote='" + IdLote + "'");

                                                con.insertar("insert into tb_factrurtemp values('" + FolFact + "','" + rset_cantidad.getString("F_IdReq") + "','" + ClaUni + "','" + IdLote + "','" + existencia + "','" + rset_cantidad.getString("F_FecEnt") + "','0','0','')");
                                                piezas = diferencia;
                                            } else {
                                                diferencia = existencia - piezas;
                                                con.actualizar("UPDATE tb_lotetemp SET F_ExiLot='" + diferencia + "' WHERE F_IdLote='" + IdLote + "'");

                                                if (piezas >= 1) {
                                                    con.insertar("insert into tb_factrurtemp values('" + FolFact + "','" + rset_cantidad.getString("F_IdReq") + "','" + ClaUni + "','" + IdLote + "','" + piezas + "','" + rset_cantidad.getString("F_FecEnt") + "','0','0','')");
                                                    con.actualizar("UPDATE tb_lotetemp SET F_ExiLot='" + diferencia + "' WHERE F_IdLote='" + IdLote + "'");
                                                }
                                                piezas = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            con.actualizar("update tb_unireqrurales set F_Status='2' where F_IdReq='" + rset_cantidad.getString("F_IdReq") + "'");
                        }
                        //con.actualizar("delete * FROM tb_unireq WHERE F_ClaUni='" + ClaUni + "' and F_FecCarg = CURDATE()");
                        con.cierraConexion();
                        //consql.cierraConexion();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println(e.getLocalizedMessage());
                    }
                    response.sendRedirect("facturacionRural/generarRuta.jsp");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
