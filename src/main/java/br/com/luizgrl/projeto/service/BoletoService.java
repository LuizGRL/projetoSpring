package br.com.luizgrl.projeto.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.luizgrl.projeto.domain.PagamentoBoleto;

@Service
public class BoletoService {

    public void preencherPagamentoBoleto(PagamentoBoleto pagamentoBoleto, Date moment){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(moment);
        calendar.add(Calendar.DAY_OF_MONTH,7);
        pagamentoBoleto.setDueDate(calendar.getTime());
    }
    
}
