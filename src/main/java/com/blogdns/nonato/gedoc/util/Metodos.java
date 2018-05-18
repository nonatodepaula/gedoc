/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogdns.nonato.gedoc.util;

import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RaimundoNonato
 */
public class Metodos {
    
    private static final GregorianCalendar CALENDARIO = new GregorianCalendar();
    
    public static final Color VERDE_PETROBRAS = new java.awt.Color( 0, 133, 66 );
    public static final Color AMARELO_PETROBRAS = new java.awt.Color(253,200,0);
    
    public static final int DIA_DO_ANO = CALENDARIO.get(GregorianCalendar.DAY_OF_YEAR);
    public static final int HORA_DO_DIA = CALENDARIO.get(GregorianCalendar.HOUR_OF_DAY);
    public static final int DIA_DA_SEMANA = CALENDARIO.get(GregorianCalendar.DAY_OF_WEEK);
    public static final int SEMANA_DO_ANO = CALENDARIO.get(GregorianCalendar.WEEK_OF_YEAR);
    
    private static final String DATA_REFERENCIA = "12/10/2016";
    
    public static void ocultaColuna(JTable jTable, int coluna) {
        
        jTable.getColumnModel().getColumn( coluna ).setMaxWidth( 0 );  
        jTable.getColumnModel().getColumn( coluna ).setMinWidth( 0 );  
        jTable.getTableHeader().getColumnModel().getColumn( coluna ).setMaxWidth( 0 );  
        jTable.getTableHeader().getColumnModel().getColumn( coluna ).setMinWidth( 0 );
    }
    
    public static String criptografaSenha(String senha) {  
        try {  
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");  
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));  
               
            StringBuilder hexString = new StringBuilder();  
            for (byte b : messageDigest) {  
              hexString.append(String.format("%02X", 0xFF & b));  
            }  
            senha = hexString.toString();  
        } catch (UnsupportedEncodingException e) {
            return null;  
        } catch (NoSuchAlgorithmException ex) {  
            return null;
        }
          
        return senha;  
    }
    
    public static void centralizaDados(JTable jTable, int n ) {
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = n; i < jTable.getColumnCount(); i++){        
        jTable.getColumnModel().getColumn(i).setCellRenderer(centralizado);        
        }
    }
    
    public static void dadosAdireita(JTable jTable, int n ) {
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.RIGHT);
        
        for (int i = n; i < jTable.getColumnCount(); i++){        
        jTable.getColumnModel().getColumn(i).setCellRenderer(centralizado);        
        }
    }
    
    public static String retornaCalendario(String pattern) {
        
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date agora = new Date();
        
        String hoje = sdf.format(agora);
        return hoje;
    }
    
    @Deprecated
    public static String defineTurno() {
        
        int hora = HORA_DO_DIA;
        int dia = DIA_DA_SEMANA;
        String turno = null;
        
        switch (dia) {
            case 3:
                if(hora >= 0 && hora < 7) turno="18:30 h-06:30 h";
                if(hora >= 7 && hora < 19) turno="06:30 h-18:30 h";
                if(hora >= 19 && hora < 24) turno="18:30 h-04:00 h";
                break;
            case 4:
                if(hora >= 0 && hora < 4) turno="18:30 h-04:00 h";
                if(hora >= 4 && hora < 12) turno="04:00 h-12:00 h";
                if(hora >= 12 && hora < 20) turno="12:00 h-20:00 h";
                if(hora >= 20 && hora < 24) turno="20:00 h-06:30 h";
                break;
            case 5:
                if(hora >= 0 && hora < 7) turno="20:00 h-06:30 h";
                if(hora >= 7 && hora < 19) turno="06:30 h-18:30 h";
                if(hora >= 19 && hora < 24) turno="18:30 h-06:30 h";
                break;
            default:
                if(hora >= 7 && hora < 19) turno="06:30 h-18:30 h";
                if(hora >= 19 && hora < 24) turno="18:30 h-06:30 h";
                if(hora >= 0 && hora < 7) turno="18:30 h-06:30 h";
            
        }
        return turno;
    }
    
    public static String semadoDoAno() {
        
        //A linha que segue define e formata a vari�vel "semana" com o valor da semana do ano.
        String semana = String.format("%03d", SEMANA_DO_ANO);
        
        //A linha que segue define a vari�vel "ano" com o valor do ano corrente.
        String ano = Metodos.retornaCalendario("YYYY");
        
        //A linha que segue define a vari�vel "id" concatenando as vari�veis "semana" e "ano".
        String doc_ref = semana + "." + ano;
        
        //A linha que segue define o valor de retorno do atributo "id".
        return doc_ref;
        
    }
    
    public static String retornaTurno(String equipe) {
        
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
        GregorianCalendar gc = new GregorianCalendar();
        
        int hora = HORA_DO_DIA;
        String turno = null;
        
        try {
            
            gc.setTime(sdf.parse(DATA_REFERENCIA));
            
            Date agora = new Date();
            long dt1 = gc.getTimeInMillis();
            long dt2 = agora.getTime();
            
            int result = (int) ((dt2-dt1)/86400000);
            
            switch(result%=35) {
                case 0:
                    if(equipe.contentEquals("Equipe 4-Processo")) {
                        if(hora < 12) turno = "18:30 h-04:00 h";
                    }
                    if(equipe.contentEquals("Equipe 5-Processo")) {
                        if(hora < 20) turno="04:00 h-12:00 h";
                        if(hora >= 20) turno="20:00 h-06:30 h";
                    }
                    if(equipe.contentEquals("Equipe 1-Processo")) {
                        if(hora < 24) turno="12:00 h-20:00 h";
                    }
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    if(equipe.contentEquals("Equipe 5-Processo")) {
                        turno="18:30 h-06:30 h";
                    }
                    if(equipe.contentEquals("Equipe 1-Processo")) {
                        turno="06:30 h-18:30 h";
                    }
                    break;
                case 7:
                    if(equipe.contentEquals("Equipe 5-Processo")) {
                        if(hora < 12) turno = "18:30 h-04:00 h";
                    }
                    if(equipe.contentEquals("Equipe 1-Processo")) {
                        if(hora < 20) turno="04:00 h-12:00 h";
                        if(hora >= 20) turno="20:00 h-06:30 h";
                    }
                    if(equipe.contentEquals("Equipe 2-Processo")) {
                        if(hora < 24) turno="12:00 h-20:00 h";
                    }
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    if(equipe.contentEquals("Equipe 1-Processo")) {
                        turno="18:30 h-06:30 h";
                    }
                    if(equipe.contentEquals("Equipe 2-Processo")) {
                        turno="06:30 h-18:30 h";
                    }
                    break;
                case 14:
                    if(equipe.contentEquals("Equipe 1-Processo")) {
                        if(hora < 12) turno = "18:30 h-04:00 h";
                    }
                    if(equipe.contentEquals("Equipe 2-Processo")) {
                        if(hora < 20) turno="04:00 h-12:00 h";
                        if(hora >= 20) turno="20:00 h-06:30 h";
                    }
                    if(equipe.contentEquals("Equipe 3-Processo")) {
                        if(hora < 24) turno="12:00 h-20:00 h";
                    }
                    break;
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                    if(equipe.contentEquals("Equipe 2-Processo")) {
                        turno="18:30 h-06:30 h";
                    }
                    if(equipe.contentEquals("Equipe 3-Processo")) {
                        turno="06:30 h-18:30 h";
                    }
                    break;
                case 21:
                    if(equipe.contentEquals("Equipe 2-Processo")) {
                        if(hora < 12) turno = "18:30 h-04:00 h";
                    }
                    if(equipe.contentEquals("Equipe 3-Processo")) {
                        if(hora < 20) turno="04:00 h-12:00 h";
                        if(hora >= 20) turno="20:00 h-06:30 h";
                    }
                    if(equipe.contentEquals("Equipe 4-Processo")) {
                        if(hora < 24) turno="12:00 h-20:00 h";
                    }
                    break;
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                    if(equipe.contentEquals("Equipe 3-Processo")) {
                        turno="18:30 h-06:30 h";
                    }
                    if(equipe.contentEquals("Equipe 4-Processo")) {
                        turno="06:30 h-18:30 h";
                    }
                    break;
                case 28:
                    if(equipe.contentEquals("Equipe 3-Processo")) {
                        if(hora < 12) turno = "18:30 h-04:00 h";
                    }
                    if(equipe.contentEquals("Equipe 4Processo")) {
                        if(hora < 20) turno="04:00 h-12:00 h";
                        if(hora >= 20) turno="20:00 h-06:30 h";
                    }
                    if(equipe.contentEquals("Equipe 5-Processo")) {
                        if(hora < 24) turno="12:00 h-20:00 h";
                    }
                    break;
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                    if(equipe.contentEquals("Equipe 4-Processo")) {
                        turno="18:30 h-06:30 h";
                    }
                    if(equipe.contentEquals("Equipe 5-Processo")) {
                        turno="06:30 h-18:30 h";
                    }
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return turno;
    }
    
    public static String proximaEquipe(String equipe) {       
        
        List<String> listaEquipe = new ArrayList<String>();
        listaEquipe.add(0, "Equipe 1-Processo");
        listaEquipe.add(1, "Equipe 2-Processo");
        listaEquipe.add(2, "Equipe 3-Processo");
        listaEquipe.add(3, "Equipe 4-Processo");
        listaEquipe.add(4, "Equipe 5-Processo");
        
        int index = listaEquipe.indexOf(equipe);
        
        if(index==4) {
            
            return listaEquipe.get(0);
        }
        
        return listaEquipe.get(index+1);
    }
    
    public static void msgGravacaoSucesso() {
        
        JOptionPane.showMessageDialog(null,
                "Formul�rio preenchido com sucesso.",
                "Controle de Documenta��o",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void msgEdicaoSucesso() {
        
        JOptionPane.showMessageDialog(null,
                "Formul�rio Editado com sucesso.",
                "Controle de Documenta��o",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void msgErroGravacao(String getErro) {
        
        JOptionPane.showMessageDialog(null, getErro,
                "Erro ao tentar grava��o.",
                JOptionPane.ERROR_MESSAGE);
    }
    public static void msgErroPrenchimento() {
        
        JOptionPane.showMessageDialog(null,
                "Preencha todos os campos da tabela.",
                "Controle de Documenta��o",
                JOptionPane.ERROR_MESSAGE);
    }
}