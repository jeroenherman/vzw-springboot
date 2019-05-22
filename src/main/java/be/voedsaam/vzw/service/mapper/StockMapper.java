package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.business.repository.StockRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StockMapper extends AbstractMapper<Stock, StockDTO> {

    private StockRepository stockRepository;
    @Autowired
    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public StockDTO mapToDTO(Stock b) {
       if (b == null)
        return null;
       StockDTO d = new StockDTO();
       d.setId(b.getId());
       d.setLocation(b.getLocation());
       d.setName(b.getName());
       d.setUsers(b.getUsers().stream().map(u -> u.getFullName()).collect(Collectors.toList()));
       //
       if (b.getProducts().size()>1)
           d.setEmptyStock(false);
       else d.setEmptyStock(true);

       if (b.getUsers().size()>1)
           d.setNoUsers(false);
       else
           d.setNoUsers(true);
       return  d;
    }

    @Override
    public Stock mapToObj(StockDTO d) {
        if (d==null)
        return null;
        Stock b = new Stock();
        Optional<Stock> o = Optional.empty();
        if (d.getId() !=null)
            o = stockRepository.findById(d.getId());

        if (o.isPresent())
            b = o.get();

        b.setId(d.getId());
        b.setLocation(d.getLocation());
        b.setName(d.getName());
        return b;
    }
}
