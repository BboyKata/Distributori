/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aziendadistributori;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;

/**
 *
 * @author sommovir
 */
public class CustomProgressBar extends ProgressBar{

    public CustomProgressBar(String task, long initialMax, int updateIntervalMillis, boolean continuousUpdate, String unity) {
        super(task, initialMax, updateIntervalMillis, continuousUpdate, System.err, ProgressBarStyle.COLORFUL_UNICODE_BLOCK,unity,1L,false,null,ChronoUnit.MILLIS,0L, Duration.ZERO);
    }
    
    
    
    
}
