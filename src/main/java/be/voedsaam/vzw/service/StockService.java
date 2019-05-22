package be.voedsaam.vzw.service;

import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.commons.CRUDService;

import java.util.List;

public interface StockService extends CRUDService<Stock> {

    List<Stock> listAllByUser(String name);
}
