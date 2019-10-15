/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jerson
 */
public class JeDate {

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/YYYY");

    //nos devuelve la fecha formateada
    //para saber cuanto tiempo a pasado de una fecha a otra
    public static String getTime(Date fechaPublicación){
        Date fechaActual = new Date();
        //1s = 1000ms
        long timeSeconds = (fechaActual.getTime() -
                fechaPublicación.getTime()) / 1000; //pasamos a segundos
        int seconds = (int)(timeSeconds % 60);
        long minutes = (timeSeconds / 60) % 60;
        long hours = (timeSeconds / 3600) % 24;
        long days = timeSeconds / (24 * 3600);
    
        if(days > 0){
            return "Hace "+days+" día"+(days>1?"s":"");
        }else if(hours > 0){
            return "Hace "+hours+" hora"+(hours>1?"s":"");
        }else if(minutes > 0){
            return "Hace "+minutes+" minuto"+(minutes>1?"s":"");
        }else if(timeSeconds<=60){
            return "Hace instantes";
        }else{
            return FORMAT.format(fechaPublicación);
        }
    }
    
}
